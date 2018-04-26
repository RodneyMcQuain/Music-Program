import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.scene.image.ImageView;

/**
 * Class objects represent music. Containing fields artist, album, genre, year, cover art, features, rating, tracks, discs, and date added.
 * @author Rodney McQuain
 */
public class Music implements Serializable, Comparable<Music> {
	private String artist;
	private String album;
	private String genre;
	private String year;
	private ImageView coverArt;
	private String features;
	private int rating;
	private int tracks;
	private int discs;
	private Calendar dateAdded;

	public Music(String artist, String album, Calendar dateAdded) {
		this.artist = artist;
		this.album = album;
		this.dateAdded = dateAdded;
	}
	
	public Music(String artist, String album, String genre, String year, ImageView coverArt, String features, int rating, int tracks, int discs, Calendar dateAdded) {
		this.artist = artist;
		this.album = album;
		this.genre = genre;
		this.year = year;
		this.coverArt = coverArt;
		this.features = features;
		this.rating = rating;
		this.tracks = tracks;
		this.discs = discs;
		this.dateAdded = dateAdded;
	}

	public int compareTo(Music m) {
		return artist.compareTo(m.getArtist());
	}
	
	public void compareByDateAdded() {
		
	}
	
	public String toString() {
		return artist + " - " + album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ImageView getCoverArt() {
		return coverArt;
	}
	
	public void setCoverArt(ImageView coverArt) {
		this.coverArt = coverArt;
	}
	
	public String getFeatures() {
		return features;
	}
	
	public void setFeatures(String features) {
		this.features = features;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getTracks() {
		return tracks;
	}

	public void setTracks(int tracks) {
		this.tracks = tracks;
	}

	public int getDiscs() {
		return discs;
	}

	public void setDiscs(int discs) {
		this.discs = discs;
	}

	public Calendar getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Calendar dateAdded) {
		this.dateAdded = dateAdded;
	}
}
