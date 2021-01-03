package xyz.zerxoi.webserver;

import xyz.zerxoi.webserver.core.Request;
import xyz.zerxoi.webserver.core.Response;
import xyz.zerxoi.webserver.core.Servlet;

public class LoginServlet implements Servlet {
    @Override
    public void service(Request req, Response resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.append("<html>").append("<head>").append("<meta charset=\"UTF-8\">").append("<title>").append("Response")
                .append("</title>").append("</head>").append("<body>").append("<p>").append("RegisterServlet")
                .append("</p>").append("<p>").append("Method: " + req.getMethod()).append("</p>").append("<p>")
                .append("Query: " + req.getQuery()).append("</p>").append("<p>").append("URL: " + req.getUrl())
                .append("</p>").append("<h1>").append("用户名: " + username).append("</h1>").append("<h1>")
                .append("密码: " + password).append("</h1>").append("</body>").append("</html>");
    }
}
