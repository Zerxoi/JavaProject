package xyz.zerxoi.webserver.core;

public interface Servlet {
    void service(Request req, Response resp);
}
