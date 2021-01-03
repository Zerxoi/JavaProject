package xyz.zerxoi.webserver;

import xyz.zerxoi.webserver.core.Request;
import xyz.zerxoi.webserver.core.Response;
import xyz.zerxoi.webserver.core.Servlet;

public class RegisterServlet implements Servlet {
    @Override
    public void service(Request req, Response resp) {
        String username = req.getParameter("username");
        String wife = null;
        switch (req.getParameter("wife")) {
            case "0":
                wife = "石原里美";
                break;
            case "1":
                wife = "新垣结衣";
                break;
            case "2":
                wife = "桥本环奈";
                break;
        }
        resp.append("<html>").append("<head>").append("<meta charset=\"UTF-8\">").append("<title>").append("Response")
                .append("</title>").append("</head>").append("<body>").append("<p>").append("RegisterServlet")
                .append("</p>").append("<p>").append("Method: " + req.getMethod()).append("</p>").append("<p>")
                .append("Query: " + req.getQuery()).append("</p>").append("<p>").append("URL: " + req.getUrl())
                .append("</p>").append("<h1>").append("用户名: " + username).append("</h1>").append("<h1>")
                .append("老婆: " + wife + "(宁能要点脸吗?)").append("</h1>").append("</body>").append("</html>");
    }
}
