package xyz.zerxoi.webserver.core;

import java.io.InputStream;
import java.net.Socket;

public class Dispatcher implements Runnable {
    private Socket socket;

    public Dispatcher(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Request req = new Request(socket);
            Response resp = new Response(socket);

            ServletContext context = ServletParser.parse("xyz/zerxoi/webserver/servlet.xml");
            if (req.getUrl() != null && req.getUrl().equals("/")) {
                InputStream is = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("xyz/zerxoi/webserver/index.html");
                resp.append(new String(is.readAllBytes()));
                resp.send(200);
            }
            String clz = context.getClz(req.getUrl());
            Servlet servlet = null;
            if (clz != null) {
                servlet = (Servlet) Class.forName(clz).getConstructor().newInstance();
                if (servlet != null) {
                    servlet.service(req, resp);
                    resp.send(200);
                } else {
                    InputStream is = Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("xyz/zerxoi/webserver/500.html");
                    resp.append(new String(is.readAllBytes()));
                    resp.send(500);
                }
            } else {
                InputStream is = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("xyz/zerxoi/webserver/404.html");
                resp.append(new String(is.readAllBytes()));
                resp.send(404);
            }
            // 连接建立后， 浏览器似乎是同同意端口再次建立连接， accept 方法接收不了新的连接， 所以要断开
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}