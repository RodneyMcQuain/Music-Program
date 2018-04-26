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
 * Class objects represent music. Containing information, such as artist, album, genre, year, and more.
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

	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//
//		Music artistAlbum = null;
//		ArrayList<Music> musicList = new ArrayList<Music>();
//
//		
//		String artist, album;
//		System.out.println("How many entries?");
//		int countEntries = 0;
//
//		int amountOfEntries = input.nextInt();
//		input.nextLine();
//
//		for(int i = 1 + countEntries; i <= amountOfEntries + countEntries; i++) {
//			if (i <= countEntries)
//				continue;
//			
//			System.out.println("Enter the artist and album formatted as 'artist album'");
//			artist = input.next();
//			album = input.next();
//			artistAlbum = new Music(artist, album);
//			musicList.add(artistAlbum);
//		}		
//		try {
//			FileOutputStream fileOut = new FileOutputStream("data.txt");
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			out.writeObject(musicList);
//			out.close();
//			fileOut.close();
//		} catch (IOException ioe2) {
//			ioe2.printStackTrace();
//		}
		
		
		
		
		
//		Scanner input = new Scanner(System.in);
//		HashMap<Integer, Music> hm = null;
//
//		Music artistAlbum;
//		int countEntries = 0;
//		
//		try {
//			FileInputStream fileIn = new FileInputStream("1.txt");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			hm = (HashMap) in.readObject();
//			Set set = hm.entrySet();
//			Iterator iterator = set.iterator();
//			while(iterator.hasNext()) {
//				Map.Entry mentry = (Map.Entry)iterator.next();
//				countEntries++;
//			}
//			in.close();
//			fileIn.close();
//		} catch (IOException ioe1) {
//	          ioe1.printStackTrace();
//	          return;
//		} catch (ClassNotFoundException c) {
//			System.out.println("Music class not found");
//        	c.printStackTrace();
//        	return;
//		}
//		String artist, album;
//		System.out.println("How many entries?");
//		int amountOfEntries = input.nextInt();
//		input.nextLine();
//
//		for(int i = 1 + countEntries; i <= amountOfEntries + countEntries; i++) {
//			if (i <= countEntries)
//				continue;
//			
//			System.out.println("Enter the artist and album formatted as 'artist - album'");
//			artistAlbum = input.nextLine();
//			artist = input.next();
//			album = input.next();
//			artistAlbum = new Music(artist, album);
//			hm.put(i, artistAlbum);
//		}
//		
//		try {
//			FileOutputStream fileOut = new FileOutputStream("1.txt");
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			out.writeObject(hm);
//			out.close();
//			fileOut.close();
//		} catch (IOException ioe2) {
//			ioe2.printStackTrace();
//		}
//
//		hm.forEach((key, value) -> System.out.println(key + ": " + value));
//      
//		input.close();
//		System.exit(0);
	}

}
