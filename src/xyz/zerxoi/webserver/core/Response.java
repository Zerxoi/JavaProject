package xyz.zerxoi.webserver.core;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class Response {
    private final String CRLF = "\r\n";
    private final String SPACE = " ";
    private StringBuffer startLine;
    private StringBuffer headers;
    private StringBuffer body;
    private OutputStream os;
    private int len;

    public Response(Socket socket) throws IOException {
        os = socket.getOutputStream();
        startLine = new StringBuffer();
        headers = new StringBuffer();
        body = new StringBuffer();
    }

    private void createStartLine(int code) {
        startLine.append("HTTP/1.1").append(SPACE).append(code).append(SPACE);
        switch (code) {
            case 200:
                startLine.append("OK");
                break;
            case 404:
                startLine.append("Not Found");
            case 500:
                startLine.append("Internal Server Error");
                break;
        }
        startLine.append(CRLF);
    }

    private void crateHeaders() {
        headers.append("Date:").append(SPACE).append(new Date()).append(CRLF);
        headers.append("Server:").append(SPACE).append("Kaguya/1.1").append(CRLF);
        headers.append("Content-Type:").append(SPACE).append("text/html").append(CRLF);
        headers.append("Content-Length:").append(SPACE).append(len).append(CRLF);
    }

    public Response append(String str) {
        body.append(str);
        len += str.getBytes().length;
        return this;
    }

    public Response appendln(String str) {
        body.append(str).append(CRLF);
        len += (str + CRLF).getBytes().length;
        return this;
    }

    public void send(int code) throws IOException {
        createStartLine(code);
        crateHeaders();
        String str = startLine.append(headers).append(CRLF).append(body).toString();
        os.write(str.getBytes());
    }
}