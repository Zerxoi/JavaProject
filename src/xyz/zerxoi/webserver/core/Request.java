package xyz.zerxoi.webserver.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private InputStream is;
    private BufferedReader br;
    private String method;
    private String url;
    private String query;
    private String version;
    private Map<String, List<String>> queryMap;
    final String CRLF = "\r\n";
    final String SPACE = " ";

    public Request(Socket socket) throws IOException {
        is = socket.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        parseRequest();
        parseQuery();
    }

    private void parseRequest() throws IOException {
        String startLine = br.readLine();
        String[] requestParams = null;
        if (startLine != null) {
            requestParams = startLine.split(SPACE);
            method = requestParams[0];
            int index;
            if ((index = requestParams[1].indexOf("?")) > 0) {
                query = requestParams[1].substring(index + 1);
                url = requestParams[1].substring(0, index);
            } else {
                url = requestParams[1];
            }
            version = requestParams[2];
        }
        String line;
        // String type = null;
        int length = 0;
        while ((line = br.readLine()) != null && !line.equals("")) {
            String[] kv = line.split(":");
            if (kv[0].equalsIgnoreCase("content-length")) {
                length = Integer.parseInt(kv[1].trim());
            } 
            // else if (kv[0].equalsIgnoreCase("content-type")) {
            //     type = kv[1];
            // }
        }
        // byte[] buf = new byte[length];
        // is.read(buf);
        // TODO: BufferedReader 读取字节
        char[] buf = new char[length];
        br.read(buf);
        // System.out.println(type);
        // System.out.println(length);
        // System.out.println(buf);
        // System.out.println(new String(buf));
        if (method != null && method.equals("POST")) {
            String str = new String(buf);
            if (query == null)
                query = str;
            else
                query += "&" + str;
        }
    }

    private void parseQuery() throws UnsupportedEncodingException {
        if (query != null) {
            String[] qParams = query.split("&");
            queryMap = new HashMap<>();
            for (String q : qParams) {
                String[] kv = q.split("=");
                kv = Arrays.copyOf(kv, 2);
                List<String> v;
                if ((v = queryMap.get(kv[0])) != null) {
                    v.add(kv[1] == null ? null : URLDecoder.decode(kv[1], "UTF-8"));
                } else {
                    v = new ArrayList<>();
                    v.add(kv[1] == null ? null : URLDecoder.decode(kv[1], "UTF-8"));
                    queryMap.put(kv[0], v);
                }
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getQuery() {
        return query;
    }

    public String getVersion() {
        return version;
    }

    public String getParameter(String key) {
        List<String> list = queryMap.get(key);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    public List<String> getParameterList(String key) {
        return queryMap.get(key);
    }
}
