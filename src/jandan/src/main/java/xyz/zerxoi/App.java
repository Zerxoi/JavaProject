package xyz.zerxoi;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) {
        Encoder encoder = Base64.getEncoder();
        String baseUrl = "http://jandan.net/ooxx/";
        String url = baseUrl;
        int page;
        int len;
        byte[] buf = new byte[8192];
        try {
            do {
                Document document = Jsoup.connect(url).userAgent("Chrome/85.0.4183.121").get();
                Elements elements = document.select("li[id^=comment]");
                page = parseCurrentPage(document);
                System.out.println(page);
                for (Element element : elements) {
                    String id = element.attr("id").substring(8);
                    Elements es = element.select("a.view_img_link");
                    for (int i = 0; i < es.size(); i++) {
                        String img = "http:" + es.get(i).attr("href");
                        String ext = img.substring(img.lastIndexOf('.'));
                        FileOutputStream fos = new FileOutputStream("D:/xxoo/" + id + "_" + i + ext);
                        URL imgUrl = new URL(img);
                        InputStream is = imgUrl.openStream();
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.close();
                        is.close();
                    }
                }
                url = baseUrl + encoder.encodeToString(new String("-" + --page).getBytes());
            } while (page > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int parseCurrentPage(Document document) {
        String pageStr = document.selectFirst(".current-comment-page").text();
        int page = Integer.parseInt(pageStr.substring(1, pageStr.length() - 1));
        return page;
    }
}
