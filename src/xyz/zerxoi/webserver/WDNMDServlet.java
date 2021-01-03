package xyz.zerxoi.webserver;

import java.io.IOException;
import java.io.InputStream;

import xyz.zerxoi.webserver.core.Request;
import xyz.zerxoi.webserver.core.Response;
import xyz.zerxoi.webserver.core.Servlet;

public class WDNMDServlet implements Servlet{
    @Override
    public void service(Request req, Response resp) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("xyz/zerxoi/webserver/wdnmd.html");
        
        try {
            resp.append(new String(is.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
