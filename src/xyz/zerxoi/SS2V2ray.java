package xyz.zerxoi;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SS2V2ray {
	private static final String singlePattern = "[0-9|a-f|A-F]";
	private static final String pattern = singlePattern + singlePattern + singlePattern + singlePattern;

	public static void main(String[] args) throws IOException {
		if (args.length < 1)
			return;
		for (int i = 0; i < args.length; i++) {
			URL url = new URL(args[i]);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer sb = new StringBuffer();
			String s;
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			

			JSONArray jsonArray = JSON.parseArray(unicodeToCn(sb.toString()));
			JSONArray arr = new JSONArray();
			for (int j = 0; j < jsonArray.size(); j++) {
				JSONObject obj = new JSONObject();
				JSONObject jsonObject = (JSONObject) jsonArray.get(j);
				obj.put("configVersion", 2);
				obj.put("address", jsonObject.getString("server"));
				obj.put("port", Integer.parseInt(jsonObject.getString("server_port")));
				obj.put("id", jsonObject.getString("password"));
				obj.put("alterId", 0);
				obj.put("security", jsonObject.getString("method"));
				obj.put("remarks", jsonObject.getString("remarks"));
				obj.put("configType", 3);
				arr.add(obj);
			}
			Writer w = new FileWriter("v2ray.json");
			w.write(arr.toString());
			w.close();
		}
	}

	public static String unicodeToCn(String str) {
		StringBuilder sb = new StringBuilder();
		int length = str.length();
		for (int i = 0; i < length;) {
			String tmpStr = str.substring(i);
			if (isStartWithUnicode(tmpStr)) {
				sb.append(ustartToCn(tmpStr));
				i += 6;
			} else {
				sb.append(str.substring(i, i + 1));
				i++;
			}
		}
		return sb.toString();
	}

	private static String ustartToCn(final String str) {
		StringBuilder sb = new StringBuilder().append("0x").append(str.substring(2, 6));
		Integer codeInteger = Integer.decode(sb.toString());
		int code = codeInteger.intValue();
		char c = (char) code;
		return String.valueOf(c);
	}

	private static boolean isStartWithUnicode(final String str) {
		if (null == str || str.length() == 0) {
			return false;
		}
		if (!str.startsWith("\\u")) {
			return false;
		}
		if (str.length() < 6) {
			return false;
		}
		String content = str.substring(2, 6);

		boolean isMatch = Pattern.matches(pattern, content);
		return isMatch;
	}
}
