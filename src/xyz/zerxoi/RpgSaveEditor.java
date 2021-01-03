package xyz.zerxoi;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RpgSaveEditor {
    public static void main(String[] args) throws IOException {
        Reader txt = new InputStreamReader(RpgSaveEditor.class.getClassLoader().getResourceAsStream("a.txt"));
        Reader json = new InputStreamReader(RpgSaveEditor.class.getClassLoader().getResourceAsStream("Armors.json"));
        
        CharArrayWriter caw = new CharArrayWriter();
        char[] buf = new char[2048];
        int len = 0;
        while ((len = txt.read(buf)) != -1) {
            caw.write(buf, 0, len);
        }
        String res = caw.toString();
        res = res.substring(1, res.length() - 1);
        StringBuilder sb = new StringBuilder().append("{");
        String[] kvs = res.split(",");

        caw.reset();
        while ((len = json.read(buf)) != -1) {
            caw.write(buf, 0, len);
        }
        JSONArray jsonArray = JSON.parseArray(caw.toString());

        int j = 0;
        String[] kv = kvs[j].split(":");
        for (int i = 1; i < jsonArray.size(); i++) {
            if (((JSONObject)jsonArray.get(i)).getString("name").equals("")) {
                continue;
            }

            if (j < kvs.length - 1 && Integer.parseInt(kv[0].substring(1, kv[0].length() - 1)) == i) {
                if (Double.parseDouble(kv[1]) != 1.0 && !kv[0].equals("\"@c\"")) {
                    sb.append(kv[0] + ":999.0,");
                } else {
                    sb.append(kv[0] + ":" + kv[1] + ",");
                }
                j++;
                kv = kvs[j].split(":");
            } else {
                sb.append("\"" + i + "\":999.0,");
            }
        }
        sb.append(kv[0] + ":" + kv[1] + "}");
        System.out.println(sb.toString());
    }
}
