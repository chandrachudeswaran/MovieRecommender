package webapi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import bean.Bean;

public class TestWeb {
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	public static ArrayList<String> getURL(Bean b) {
		ArrayList<String> links = new ArrayList<String>();
		      ArrayList<String> movieTitleAndYear= b.getMovietitle();
		      int size=movieTitleAndYear.size();
		      for(int i=0;i<size;i++){
		    	  int movieYearOpenBracesIndex = movieTitleAndYear.get(i).indexOf("(");
			      int movieYearCloseBracesIndex = movieTitleAndYear.get(i).indexOf(")");
			      String movieYear = movieTitleAndYear.get(i).substring(movieYearOpenBracesIndex + 1, movieYearCloseBracesIndex);
			      String title=movieTitleAndYear.get(i).substring(0,movieYearOpenBracesIndex );
			      title=replaceAtTheEnd(title);
		try {
			String url = readJsonFromUrl("http://www.omdbapi.com/?t='"+title+"'&y=&plot=short&r=json");
			links.add(url);
			System.out.println(url);
		} catch (JSONException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		      }
		      return links;
		    }
	public static String replaceAtTheEnd(String input){
	    input = input.replaceAll("\\s+$", "");
	    return input;
	}
	
	 public static String readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      
		      String img = json.getString("Poster");
		      //System.out.println(json.toString());
		      
		      //System.out.println(img);
		      return img;
		    } finally {
		      is.close();
		    }
		  }
		}

	


