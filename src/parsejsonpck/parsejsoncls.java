package parsejsonpck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.*;

public class parsejsoncls {
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		String API_KEY = "";
		JSONObject json = readJsonFromUrl(
				"https://api.themoviedb.org/3/search/movie?/page=2/&query=q&api_key=" 
		+ API_KEY +
		"&language=en-US");
		JSONArray list = json.getJSONArray("results");
		for (int i = 0; i < list.length(); i++) {
			JSONObject elem = list.getJSONObject(i);
			String title = elem.getString("title");
			String overview = elem.getString("overview");
			double vote_average = elem.getDouble("vote_average");
			System.out.println("Title: " + title + "\n");
			System.out.println("Overview: " + overview + "\n");
			System.out.println("Vote Average: " + vote_average + "\n\n");
		}
	}

}
