package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import webapi.GetUrl;
import webapi.HelloWorld;
import bean.Bean;

public class RecoGenre {
	static Bean a1 = new Bean();

	public static HashMap<String, Double> userRatings = new HashMap<String, Double>();
	public static HashMap<String, String> movies = new HashMap<>();
	public static HashMap<String, HashMap<String, Double>> moviesGenre = new HashMap<>();
	public static HashMap<String, Double> moviesGenreInner = new HashMap<>();
	public static HashMap<String, String> moviesAndGenre = new HashMap<>();

	public static Bean doRecoGenre(String user) throws IOException {

		RecommendGenre userObject = new RecommendGenre();
		RecoGenre myobj = new RecoGenre();

		Random rand = new Random();

		BufferedReader br = new BufferedReader(
				new FileReader(
						"D:\\Eclipse_Luna_WS\\MovieRecommender\\WebContent\\datafiles\\movies.txt"));

		String line;
		while ((line = br.readLine()) != null) {
			String[] chars = line.split("::");
			String movieID = chars[0];
			String movieName = chars[1];
			String movieAndGenre = chars[2];
			movies.put(movieID, movieName);
			moviesAndGenre.put(movieID, movieAndGenre);
		}

		HashMap<String, Double> outputRatings = myobj.getUserRating(user);

		int rand1 = rand.nextInt(6);
		System.out.println(rand1);
		String a = outputRatings.keySet().toArray()[rand1].toString();
		
		System.out.println(outputRatings);
	
		HashMap<String, HashMap<String, Double>> outGenre = myobj
				.loadMovieGenre();
		
		System.out.println(a);

		a1 = userObject.recommend(a);
		return a1;
	}

	HashMap<String, Double> getUserRating(String user) throws IOException {
		BufferedReader brR = new BufferedReader(
				new FileReader(
						"D:\\Eclipse_Luna_WS\\MovieRecommender\\WebContent\\datafiles\\ratings.txt"));
		String lineR;
		int temp = 1;
		while ((lineR = brR.readLine()) != null) {
			String[] charsR = lineR.split("::");
			String userID = charsR[0];
			String movieID = charsR[1];
			Double rating = Double.valueOf(charsR[2]);
			if (userID.equals(user)) {
				userRatings.put(movieID, rating);
			}

		}

		return Sort.sortByValue(userRatings);
	}

	HashMap<String, HashMap<String, Double>> loadMovieGenre()
			throws IOException {
		BufferedReader brR = new BufferedReader(
				new FileReader(
						"D:\\Eclipse_Luna_WS\\MovieRecommender\\WebContent\\datafiles\\movies.txt"));
		String lineR;
		double genreValue;
		int temp = 1;
		while ((lineR = brR.readLine()) != null) {
			genreValue = 5;
			String[] charsR = lineR.split("::");
			String movieID = charsR[0];
			String movieName = charsR[1];
			String genre = charsR[2];
			String[] genreSplit = genre.split("\\|");
			double genreValueFinal = genreValue / genreSplit.length;
			
			moviesGenreInner = new HashMap<>();
			for (int i = 0; i < genreSplit.length; i++) {
				moviesGenreInner.put(genreSplit[i], genreValueFinal);
			}
			moviesGenre.put(movieID, moviesGenreInner);

		}

		return moviesGenre;

	}

}

class RecommendGenre {
	Bean b = new Bean();

	Bean recommend(String userMovie) {

		HashMap<String, Double> recommendations = new HashMap<>();
		HashMap<String, Double> nearest;
		ArrayList<String> nearestNb = new ArrayList<>();
		ArrayList<Double> nearestDist = new ArrayList<>();
		nearest = computeNearestNeighbour(userMovie);
		System.out.println(nearest);
		for (Map.Entry<String, Double> temp : nearest.entrySet()) {
			nearestNb.add(temp.getKey());
			nearestDist.add(temp.getValue());

		}

		for (Map.Entry<String, Double> n : nearest.entrySet()) {

			if (!RecoGenre.userRatings.containsKey(n.getKey())) {

				recommendations.put(n.getKey(), n.getValue());
			}

		}

		HashMap<String, Double> reco = Sort.sortByValueAsc(recommendations);
		int count = 0;
		ArrayList<String> movietitle = new ArrayList<String>();
		ArrayList<String> genre = new ArrayList<String>();
		for (Map.Entry<String, Double> outp : reco.entrySet()) {
			if (count < 10) {
				movietitle.add(RecoGenre.movies.get(outp.getKey()));
				genre.add(RecoGenre.moviesAndGenre.get(outp.getKey()));
				count++;
			} else {
				break;
			}

		}
		b.setMovietitle(movietitle);
		b.setMoviegenre(genre);
		HelloWorld h = new HelloWorld();
		try {
			h.getUrl(b);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return b;

	}

	double manhattan(HashMap<String, Double> genreValue1,
			HashMap<String, Double> genreValue2) {
		double distance = 0;
		for (Map.Entry<String, Double> k1 : genreValue1.entrySet()) {
			for (Map.Entry<String, Double> k2 : genreValue2.entrySet()) {
				if (k1.getKey().equals(k2.getKey())) {

					distance = Math.abs(k1.getValue() - k2.getValue());
				} else {
					distance = 1000;
				}

			}
		}

		return distance;
	}

	HashMap<String, Double> computeNearestNeighbour(String userMovie) {
		ArrayList<String> nb = new ArrayList<>();
		ArrayList<Double> nb1 = new ArrayList<>();

		HashMap<String, Double> nbDist = new HashMap<>();
		for (Map.Entry<String, HashMap<String, Double>> om : RecoGenre.moviesGenre
				.entrySet()) {
			if (!om.getKey().equals(userMovie)) {

				HashMap<String, Double> gen1 = RecoGenre.moviesGenre
						.get(userMovie);
				HashMap<String, Double> gen2 = RecoGenre.moviesGenre.get(om
						.getKey());

				double distance = manhattan(gen1, gen2);
				if (distance != 1000) {
					nbDist.put(om.getKey(), new Double(distance));
				}

			}

		}

		HashMap<String, Double> nbDist1 = Sort.sortByValueAsc(nbDist);
		return nbDist1;

	}

}

class Sort {

	@SuppressWarnings("unchecked")
	static HashMap<String, Double> sortByValue(HashMap<String, Double> unsortMap) {
		List list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
					Map.Entry<String, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		HashMap<String, Double> sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Double> entry = (Map.Entry<String, Double>) it
					.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	@SuppressWarnings("unchecked")
	static HashMap<String, Double> sortByValueAsc(
			HashMap<String, Double> unsortMap) {
		@SuppressWarnings("rawtypes")
		List list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1,
					Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		HashMap<String, Double> sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Double> entry = (Map.Entry<String, Double>) it
					.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
