package webapi;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import bean.Bean;


public class GetUrl {
	
	public static void main(String[] args){
		Bean b = new Bean();
		ArrayList<String> url = new ArrayList<String>();
		url.add("Evening Star, The (1996)");
		url.add("Ghost and Mrs. Muir, The (1947)");
		
		b.setMovietitle(url);
		
		GetUrl g = new GetUrl();
		b=g.getUrl(b);
		for(int i=0;i<url.size();i++){
			System.out.println(b.getMovieurl());
		}
	}
	
	public  Bean getUrl(Bean b) {
			System.out.println("inside");
		      String content = null;
		      ArrayList<String> url = new ArrayList<String>();
		      ArrayList<String> title= b.getMovietitle();
		      
		      
		      for(int i=0;i<title.size();i++){
		    	  System.out.println("am printing" + title.get(i));
		      }
		      for(int i=0;i<title.size();i++){
		     String link="http://192.168.1.6:5000/getImage/"+title.get(i);
		   System.out.println(link);
		    try {
				URL url1 = new URL(link);
				URLConnection con = url1.openConnection();
				 content = IOUtils.toString(new InputStreamReader(
				                        url1.openStream()));
				 System.out.println(content);
				url.add(content);
				System.out.println("hello");
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
		      }
		      b.setMovieurl(url);
		    return b; 
	}
	 
		
		}

	


