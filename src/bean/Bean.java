package bean;

import java.util.ArrayList;
import java.util.HashMap;

public class Bean {
	
	ArrayList<String> movietitle;
	ArrayList<String> moviegenre;
	ArrayList<String> movieurl;
	HashMap<String,Double> reco;
	
	
	
	public ArrayList<String> getMovieurl() {
		return movieurl;
	}
	public void setMovieurl(ArrayList<String> movieurl) {
		this.movieurl = movieurl;
	}
	public HashMap<String, Double> getReco() {
		return reco;
	}
	public void setReco(HashMap<String, Double> reco) {
		this.reco = reco;
	}
	public ArrayList<String> getMovietitle() {
		return movietitle;
	}
	public void setMovietitle(ArrayList<String> movietitle) {
		this.movietitle = movietitle;
	}
	public ArrayList<String> getMoviegenre() {
		return moviegenre;
	}
	public void setMoviegenre(ArrayList<String> moviegenre) {
		this.moviegenre = moviegenre;
	}
	
	
	

}
