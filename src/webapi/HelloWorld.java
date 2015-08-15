package webapi;

import java.util.*;
import java.io.*;

import bean.Bean;

public class HelloWorld {

	public Bean getUrl(Bean b) throws IOException {

		ArrayList<String> title = b.getMovietitle();

		ArrayList<String> url = new ArrayList<String>();
		for (int i = 0; i < title.size(); i++) {

			Runtime rt = Runtime.getRuntime();
			String movie = title.get(i);
			movie = movie.toLowerCase();

			movie = "\"" + movie + "\"";
			System.out.println(movie);
			Process pr = rt.exec("c:/python27/python c:/python27/poster.py"
					+ " " + movie);

			BufferedReader bfr = new BufferedReader(new InputStreamReader(
					pr.getInputStream()));
			String line = "";

			line = bfr.readLine();
			if (line == null) {
				line = "http://www.pioneergardens.com/wp-content/uploads/2012/09/NO-IMAGE-AVAILABLE-ICON-web1.jpg";
				url.add(line);
			} else {
				url.add(line);
			}

		}

		b.setMovieurl(url);
		return b;
	}
}
