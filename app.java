import javafx.stage.Stage;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.layout.VBox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class app extends Application {
	private Stage theStage;
	private ScrollPane mainMenuScrollPane = new ScrollPane();

	private Scene mainMenuScene, addMusicScene, importMusicScene, specifiedMusicScene, exportMusicScene, errorScene;	
	ArrayList<Music> musicList = new ArrayList<Music>();
	private int musicObjLocation = 0;

	//Main Menu
	private Button btAddMusicMainMenu = new Button("Add Music");
	private Button btImportMusicMainMenu = new Button("Import Music");
	private Button btExportMusicMainMenu = new Button("Export Music");
	private Button btCreateCollectionMainMenu = new Button("Create Collection");
	private Label lblYourMusic = new Label("Your Music");
	private Label lblListenTo = new Label("Take a Listen to: ");
	private Label lblRandomMusic = new Label();
	
	//Add Music
	private Button btAdd = new Button("Add");
	private TextField tfArtist = new TextField();
	private TextField tfAlbum = new TextField();
	private Button btAddMusicToMainMenu = new Button("Main Menu");
	private Label lblStatusOfAdd = new Label();
	
	//Import Music
	private ComboBox<String> cbField1 = new ComboBox<>();
	private ComboBox<String> cbField2 = new ComboBox<>();
	private TextField tfDelimeter = new TextField();
	private FileChooser fcImportFile = new FileChooser();
	private Button btChooseFile = new Button("Choose File");
	private TextField tfImportFile = new TextField();
	private Button btImport = new Button("Import");
	private Button btImportMusicToMainMenu = new Button("Main Menu");
	private Label lblStatusOfImport = new Label();
	
	//Specified Music
	private Label lblArtistAlbumTitle = new Label();
	private TextField tfArtistHl = new TextField();
	private TextField tfAlbumHl = new TextField();
	private TextField tfYear = new TextField();
	private TextField tfGenre = new TextField();
	//private ImageView tfCoverArt = new ImageView();
	private TextField tfRating = new TextField();
	private TextField tfFeatures = new TextField();
	private TextField tfTracks = new TextField();
	private TextField tfDiscs = new TextField();
	private Button btApplyChanges = new Button("Apply Changes");
	private Label lblStatusOfSpecifiedMusic = new Label();
	private Button btSpecifiedMusicToMainMenu = new Button("Main Menu");
	
	//Export Music
	private Button btExport = new Button("Export");
	private TextField tfExportFilename = new TextField();
	private DirectoryChooser dcExportFile = new DirectoryChooser();
	private Button btChooseDirectory = new Button("Choose File Destination");
	private Label lblStatusOfExport = new Label();
	private Button btRemoveSpecified = new Button("Remove");
	private Button btExportMusicToMainMenu = new Button("Main Menu");
	
	//Create Collection
	private Button btCreateCollection = new Button("Create Collection");
	
	//Detailed View
	private Button btDetailedView = new Button("Detailed View");
	
	//Recently Added
	
	
	//Error
	private Label lblErrorType = new Label();
	
    @Override
	public void start(Stage primaryStage) {
    	theStage = primaryStage;
    	
    	deserialize();
    	printMusic();
    	randomMusic();
    	
    	mainMenuSetup();
    	addMusicSetup();
    	importMusicSetup();
    	specifiedMusicSetup();
    	exportMusicSetup();
    	errorSetup();
    	
        primaryStage.setTitle("Music Program");
	    primaryStage.setScene(mainMenuScene);
	    primaryStage.show();
    }
    
    public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(musicList);
			out.close();
			fileOut.close();
		} catch (IOException ioe2) {
			theStage.setScene(errorScene);
			lblErrorType.setText("Error while serializing");
			ioe2.printStackTrace();
		}
    }
    public void deserialize() {
    	ObjectInputStream in = null;
    	
		try {
			FileInputStream fileIn = new FileInputStream("data.ser");
			File file = new File("data.txt");
			file.createNewFile();
			in = new ObjectInputStream(fileIn);
			musicList = (ArrayList) in.readObject();
			in.close();
			fileIn.close();
		} catch (EOFException eof) {
			musicList.add(new Music("sd4h5%@x!b", "sd4h5%@x!b", Calendar.getInstance()));
			serialize();
		} catch (IOException | ClassNotFoundException ioe) {
			theStage.setScene(errorScene);
			lblErrorType.setText("Error while deserializing");
		}
    }
    
    public void mainMenuSetup() {
    	GridPane mainMenuPane = new GridPane();
    	mainMenuPane.setPrefSize(10, 300);
    	BorderPane mainMenuBorderPane = new BorderPane();
    	Label lblMainMenu = new Label("Your Music");
    	mainMenuPane = new GridPane();
    	mainMenuPane.setHgap(10);
    	mainMenuPane.setVgap(10);
    	mainMenuPane.setPadding(new Insets(10, 10, 10, 10));
    	lblMainMenu.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	GridPane.setHalignment(lblMainMenu, HPos.CENTER);
    	GridPane.setHalignment(btAddMusicMainMenu, HPos.CENTER);
		GridPane.setHalignment(btImportMusicMainMenu, HPos.CENTER);
		GridPane.setHalignment(btExportMusicMainMenu, HPos.CENTER);
		GridPane.setHalignment(btCreateCollectionMainMenu, HPos.CENTER);
		GridPane.setHalignment(lblYourMusic, HPos.CENTER);
		GridPane.setHalignment(lblListenTo, HPos.CENTER);
		GridPane.setHalignment(lblRandomMusic, HPos.CENTER);
		
		BorderPane.setAlignment(lblMainMenu, Pos.CENTER);
		BorderPane.setMargin(lblMainMenu, new Insets(10, 10, 10, 10));
		mainMenuBorderPane.setTop(lblMainMenu);
		
		mainMenuPane.add(btAddMusicMainMenu, 0, 0);
    	btAddMusicMainMenu.setOnAction(e -> mainMenuToAddMusic(e));

		mainMenuPane.add(btImportMusicMainMenu, 0, 1);
    	btImportMusicMainMenu.setOnAction(e -> mainMenuToImportMusic(e));

		mainMenuPane.add(btExportMusicMainMenu, 0, 2);
    	btExportMusicMainMenu.setOnAction(e -> mainMenuToExportMusic(e));

    //	mainMenuPane.add(btCreateCollectionMainMenu, 0, 3);
    	
		//mainMenuPane.add(lblYourMusic, 0, 6);
		
		mainMenuScrollPane.setFitToWidth(true);
	//	mainMenuScrollPane.setPrefSize(300, 200);
		mainMenuScrollPane.setPrefSize(300, 500);
		
		GridPane bottomOfMainMenuPane = new GridPane();
		Label lblRandMusic = new Label(lblListenTo.getText() + lblRandomMusic.getText());
		bottomOfMainMenuPane.add(lblRandMusic, 0, 0);
		bottomOfMainMenuPane.setAlignment(Pos.CENTER);
		bottomOfMainMenuPane.setPadding(new Insets(10, 10, 10, 10));

		mainMenuBorderPane.setRight(mainMenuScrollPane);
		mainMenuBorderPane.setBottom(bottomOfMainMenuPane);
    	mainMenuBorderPane.setCenter(mainMenuPane);

		mainMenuScene = new Scene(mainMenuBorderPane, 420, 460);
		mainMenuScene.getStylesheets().add("stylesheet.css");
    }
    
    public void addMusicSetup() {
    	GridPane addMusicPane = new GridPane();
    	addMusicPane.setHgap(10);
		addMusicPane.setVgap(10);
		addMusicPane.setPadding(new Insets(10, 10, 10, 10));
    	Label lblAddMusic = new Label("Add Music");
    	lblAddMusic.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	
    	addMusicPane.add(lblAddMusic, 0, 0);
    	addMusicPane.add(tfArtist, 0, 1);
    	addMusicPane.add(new Label("-"), 1, 1);
    	addMusicPane.add(tfAlbum, 2, 1);
    	addMusicPane.add(btAdd, 0, 2);
    	btAdd.setOnAction(e -> addMusic(e));
    	addMusicPane.add(btAddMusicToMainMenu, 0, 3);
    	addMusicPane.add(lblStatusOfAdd, 1, 3);
    	btAddMusicToMainMenu.setOnAction(e -> addMusicToMainMenu(e));
//    	if (isSerialized)
//			addMusicPane.add(new Label("Albums succesfully added"), 0, 5);
//    	else 
//			addMusicPane.add(new Label("Failed to read or write to the file"), 0, 5);
    	addMusicScene = new Scene(addMusicPane, 500, 200);
    	addMusicScene.getStylesheets().add("stylesheet.css");
    }
    
    public void importMusicSetup() {
    	GridPane importMusicPane = new GridPane();
    	importMusicPane.setHgap(10);
    	importMusicPane.setVgap(10);
    	importMusicPane.setPadding(new Insets(10, 10, 10, 10));
    	Label lblImportMusic = new Label("Import Music");
    	lblImportMusic.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	importMusicPane.add(lblImportMusic, 0, 0);
    	importMusicPane.add(btChooseFile, 1, 1);
    	importMusicPane.add(tfImportFile, 0, 1);
    	tfImportFile.setPrefWidth(135);
    	btChooseFile.setOnAction(e -> {
    		File importedFile = fcImportFile.showOpenDialog(theStage);
    		tfImportFile.setText(importedFile.toString());
    	});
    	cbField1.getItems().addAll("<Artist>", "<Album>");
    	cbField1.setValue("<Artist>");
    	cbField1.setPrefWidth(135);
    	importMusicPane.add(cbField1, 0, 2);
    	tfDelimeter.setPrefWidth(25);
    	importMusicPane.add(tfDelimeter, 1, 2);
    	tfDelimeter.setText(" - ");			
    	cbField2.getItems().addAll("<Album>", "<Artist>");
    	cbField2.setValue("<Album>");
    	cbField2.setPrefWidth(135);
    	importMusicPane.add(cbField2, 2, 2);
    	importMusicPane.add(btImport, 0, 3);
    	btImport.setOnAction(e -> importMusic(e));
    	importMusicPane.add(btImportMusicToMainMenu, 0, 4);
    	importMusicPane.add(lblStatusOfImport, 1, 4);
    //	importMusicPane.add(new Label("Add spaces to the delimiter as needed"), 0, 5);
    	btImportMusicToMainMenu.setOnAction(e -> importMusicToMainMenu(e));
    	
    	importMusicScene = new Scene(importMusicPane, 600, 300);
    	importMusicScene.getStylesheets().add("stylesheet.css");
    }
    public void specifiedMusicSetup() {
    	GridPane specifiedMusicPane = new GridPane();
    	specifiedMusicPane.setHgap(10);
    	specifiedMusicPane.setVgap(10);
    	specifiedMusicPane.setPadding(new Insets(10, 10, 10, 10));
    	lblArtistAlbumTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

    	specifiedMusicPane.add(lblArtistAlbumTitle, 0, 0);
    	specifiedMusicPane.add(new Label("Artist:"), 0, 1);	
    	specifiedMusicPane.add(tfArtistHl, 1, 1);
    	specifiedMusicPane.add(new Label("Album:"), 0, 2);	
    	specifiedMusicPane.add(tfAlbumHl, 1, 2);
    	specifiedMusicPane.add(new Label("Year:"), 0, 3);	
    	specifiedMusicPane.add(tfYear, 1, 3);
    	specifiedMusicPane.add(new Label("Genre:"), 0, 4);	
    	specifiedMusicPane.add(tfGenre, 1, 4);
    	specifiedMusicPane.add(new Label("Rating:"), 0, 5);	
    	specifiedMusicPane.add(tfFeatures, 1, 5);
    	specifiedMusicPane.add(new Label("Features:"), 0, 6);	
    	specifiedMusicPane.add(tfRating, 1, 6);
    	specifiedMusicPane.add(new Label("Tracks:"), 0, 7);	
    	specifiedMusicPane.add(tfTracks, 1, 7);
    	specifiedMusicPane.add(new Label("Discs:"), 0, 8);	
    	specifiedMusicPane.add(tfDiscs, 1, 8);
    	specifiedMusicPane.add(btApplyChanges, 0, 9);
    	btApplyChanges.setOnAction(e -> setSpecifiedMusic());
    	specifiedMusicPane.add(btRemoveSpecified, 1, 9);
    	btRemoveSpecified.setOnAction(e -> removeSpecifiedMusic(musicObjLocation));
    	specifiedMusicPane.add(btSpecifiedMusicToMainMenu, 0, 10);
    	btSpecifiedMusicToMainMenu.setOnAction(e -> specifiedMusicToMainMenu(e));
    	specifiedMusicPane.add(lblStatusOfSpecifiedMusic, 1, 10);
    	
    	specifiedMusicScene = new Scene(specifiedMusicPane, 500, 500);
    	specifiedMusicScene.getStylesheets().add("stylesheet.css");
    }
    public void exportMusicSetup() {
    	GridPane exportMusicPane = new GridPane();
    	exportMusicPane.setHgap(10);
    	exportMusicPane.setVgap(10);
    	exportMusicPane.setPadding(new Insets(10, 10, 10, 10));
    	Label lblExportMusic = new Label("Export Music");
    	lblExportMusic.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    	exportMusicPane.add(lblExportMusic, 0, 0);
    	exportMusicPane.add(tfExportFilename, 0, 1);
    	exportMusicPane.add(btChooseDirectory, 1, 1);
    	btChooseDirectory.setOnAction(e -> {
    		File exportFileDirectory = dcExportFile.showDialog(theStage);
    		tfExportFilename.setText(exportFileDirectory.toString());
    	});
    	exportMusicPane.add(btExport, 0, 2);
    	btExport.setOnAction(e -> exportMusic());
    	exportMusicPane.add(btExportMusicToMainMenu, 0, 3);
    	exportMusicPane.add(lblStatusOfExport, 1, 3);
    	btExportMusicToMainMenu.setOnAction(e -> exportMusicToMainMenu(e));
    	
    	exportMusicScene = new Scene(exportMusicPane, 500, 500);
    	exportMusicScene.getStylesheets().add("stylesheet.css");
    }
    public void errorSetup() {
    	GridPane errorPane = new GridPane();
    	errorPane.add(lblErrorType, 0, 0);
    	errorPane.add(new Label("Please restart the program"), 0, 1);
    	
    	errorScene = new Scene (errorPane, 500, 500);
    }
    
    public void printMusic() {
    	Collections.sort(musicList);    	
//************************************************************************************************************************************
//    	Collections.sort(musicList, new Comparator<Music>() {
//    		public int compare(Music m1, Music m2) {
//    			return m1.getDateAdded().compareTo(m2.getDateAdded());
//    		}
//    	});
    	
    	VBox root = new VBox();
		
		Hyperlink[] hlMusic = new Hyperlink[musicList.size()];
		for (int i = 0; i < hlMusic.length; i++) {
			if (musicList.get(i).toString() != "sd4h5%@x!b - sd4h5%@x!b") {
				hlMusic[i] = new Hyperlink(); 
				hlMusic[i].setText((i + 1) + ". " + musicList.get(i).toString());
				root.getChildren().addAll(hlMusic[i]);
			
				hlMusic[i].setOnAction(e -> mainMenuToSpecifiedMusic(e, hlMusic));
			}
		}
		mainMenuScrollPane.setContent(root);

    }
    public void randomMusic() {
    	Random rand = new Random();
    	Music randomMusic = musicList.get(rand.nextInt(musicList.size()));
    	lblRandomMusic.setText(randomMusic.toString());
    }
    
    
    public void addMusic(ActionEvent e) {
		Music artistAlbum = null;
    	String artist = tfArtist.getText();
    	String album = tfAlbum.getText();
    	lblStatusOfAdd.setText("");
    	
		if (artist.length() != 0 && album.length() != 0) {
			Calendar dateAdded = Calendar.getInstance();

			artistAlbum = new Music(artist, album, dateAdded);
			musicList.add(artistAlbum);
	    	Alert alAddMusic = new Alert(AlertType.INFORMATION);
	    	alAddMusic.setTitle("Add Music");
	    	alAddMusic.setHeaderText(null);
	    	alAddMusic.setContentText("Your music was added");
	    	alAddMusic.showAndWait();
		//	lblStatusOfAdd.setText("Adding was sucessful");
		}
		else {
	    	Alert alAddMusic = new Alert(AlertType.ERROR);
	    	alAddMusic.setTitle("Add Music");
	    	alAddMusic.setHeaderText(null);
	    	alAddMusic.setContentText("There was an error while adding your music, make sure both fields have content.");
	    	alAddMusic.showAndWait();
			//lblStatusOfAdd.setText("Adding was unsucessful, add to the fields");
			return;
		}	
			
		serialize();
    }
    public void importMusic(ActionEvent e) {
    	String delimiter = tfDelimeter.getText();
    	Scanner inFile = null;
    	String artistAlbum;
    	Music musicObj = null;
    	String[] splitArtistAlbum = null;
    	String importFilename = tfImportFile.getText();
    	File importFile = new File(importFilename);
    	
    	if (importFile.length() == 0) {
    		lblStatusOfImport.setText("File empty");
    		return;
    	}
    	
    	try {
    		inFile = new Scanner(importFile);
		} catch (FileNotFoundException fnfe) {
			lblStatusOfImport.setText("That is not a valid file");
			fnfe.printStackTrace();
		}
    	
		Calendar dateAdded = Calendar.getInstance();
    	if (cbField1.getValue() == "<Artist>" && cbField2.getValue() == "<Album>") {
    		while (inFile.hasNext()) {
    			artistAlbum = inFile.nextLine();
    			splitArtistAlbum = artistAlbum.split("\\" + delimiter);
    			musicObj = new Music(splitArtistAlbum[0], splitArtistAlbum[1], dateAdded);
    			musicList.add(musicObj);
    		}
	    	Alert alImportMusic = new Alert(AlertType.INFORMATION);
	    	alImportMusic.setTitle("Import Music");
	    	alImportMusic.setHeaderText(null);
	    	alImportMusic.setContentText("Your music was imported.");
	    	alImportMusic.showAndWait();
    		//lblStatusOfImport.setText("Importing sucessful");
    	}
    	else if (cbField1.getValue() == "<Album>" && cbField2.getValue() == "<Artist>") {
    		while (inFile.hasNext()) {
    			artistAlbum = inFile.nextLine();
    			splitArtistAlbum = artistAlbum.split("\\" + delimiter);
    			musicObj = new Music(splitArtistAlbum[1], splitArtistAlbum[0], dateAdded);
    			musicList.add(musicObj);
    		}
	    	Alert alImportMusic = new Alert(AlertType.INFORMATION);
	    	alImportMusic.setTitle("Import Music");
	    	alImportMusic.setHeaderText(null);
	    	alImportMusic.setContentText("Your music was imported.");
	    	alImportMusic.showAndWait();
    		//lblStatusOfImport.setText("Importing sucessful");
    	}
    	else {
	    	Alert alImportMusic = new Alert(AlertType.INFORMATION);
	    	alImportMusic.setTitle("Import Music");
	    	alImportMusic.setHeaderText(null);
	    	alImportMusic.setContentText("There was an error while adding your music, be sure that the 2 dropdowns are different");
	    	alImportMusic.showAndWait();
    		//lblStatusOfImport.setText("Importing unsuccesful, be sure that the 2 dropdowns are different");
    		return;
    	}
    		
    	serialize();
    }
    public void exportMusic() {
    	String exportDirectory = tfExportFilename.getText();
    	File exportFile = new File(exportDirectory + "\\Your Music.txt");
    	FileWriter fwritter = null;
		try {
			fwritter = new FileWriter(exportFile);
		} catch (IOException ioe) {
	    	Alert alImportMusic = new Alert(AlertType.ERROR);
	    	alImportMusic.setTitle("Export Music");
	    	alImportMusic.setHeaderText(null);
	    	alImportMusic.setContentText("Not able to write to file");
	    	alImportMusic.showAndWait();
			//lblStatusOfExport.setText("Not able to write to file");
		}
    	PrintWriter outFile = new PrintWriter(fwritter);
    	
    	for(int i = 0; i < musicList.size(); i++)
    		outFile.println(musicList.get(i).toString());
    	
    	outFile.close();
    	Alert alImportMusic = new Alert(AlertType.INFORMATION);
    	alImportMusic.setTitle("Export Music");
    	alImportMusic.setHeaderText(null);
    	alImportMusic.setContentText("Exported to " + exportDirectory + "\\Your Music.txt");
    	alImportMusic.showAndWait();
		//lblStatusOfExport.setText("Exported to " + exportDirectory + "\\Your Music.txt");
    }
    
    public void recentlyAddedMusic() {
    	Collections.sort(musicList, new Comparator<Music>() {
    		public int compare(Music m1, Music m2) {
    			return m1.getDateAdded().compareTo(m2.getDateAdded());
    		}
    	});
    	
    	
    	
    }
    
    public void getSpecifiedMusic() {
		lblArtistAlbumTitle.setText(musicList.get(musicObjLocation).toString());
		tfArtistHl.setText(musicList.get(musicObjLocation).getArtist());
		tfAlbumHl.setText(musicList.get(musicObjLocation).getAlbum());
		tfYear.setText(musicList.get(musicObjLocation).getYear());
		tfGenre.setText(musicList.get(musicObjLocation).getGenre());
		tfRating.setText(Integer.toString(musicList.get(musicObjLocation).getRating()));
		tfFeatures.setText(musicList.get(musicObjLocation).getFeatures());
		tfTracks.setText(Integer.toString(musicList.get(musicObjLocation).getTracks()));
		tfDiscs.setText(Integer.toString(musicList.get(musicObjLocation).getDiscs()));
    }
    public void setSpecifiedMusic() {
    	musicList.get(musicObjLocation).setArtist(tfArtistHl.getText());
		musicList.get(musicObjLocation).setAlbum(tfAlbumHl.getText());
		musicList.get(musicObjLocation).setYear(tfYear.getText());
		musicList.get(musicObjLocation).setGenre(tfGenre.getText());
		musicList.get(musicObjLocation).setRating(Integer.parseInt(tfRating.getText()));
		musicList.get(musicObjLocation).setFeatures(tfFeatures.getText());
		musicList.get(musicObjLocation).setTracks(Integer.parseInt(tfTracks.getText()));
		musicList.get(musicObjLocation).setDiscs(Integer.parseInt(tfDiscs.getText()));
		
		lblStatusOfSpecifiedMusic.setText("Changes were applied");
		serialize();
    }
    public void removeSpecifiedMusic(int musicObjLocation) {
    	musicList.remove(musicObjLocation);
    	
    	Alert alRemoveMusic = new Alert(AlertType.INFORMATION);
    	alRemoveMusic.setTitle("Remove Music");
    	alRemoveMusic.setHeaderText(null);
    	alRemoveMusic.setContentText("The music was removed");
    	alRemoveMusic.showAndWait();
		//lblStatusOfSpecifiedMusic.setText("The music was removed");
    	serialize();
    	
    	deserialize();
    	printMusic();
    	theStage.setScene(mainMenuScene);
    }
    
    public void mainMenuToAddMusic(ActionEvent e) {
        if (e.getSource() == btAddMusicMainMenu)
            theStage.setScene(addMusicScene);
    }
    public void mainMenuToImportMusic(ActionEvent e) {
    	if (e.getSource() == btImportMusicMainMenu)
    		theStage.setScene(importMusicScene);
    }
    public void mainMenuToSpecifiedMusic(ActionEvent e, Hyperlink[] hlMusic) {
    	for (int i = 0; i < hlMusic.length; i++) {
        	if (e.getSource() == hlMusic[i]) {
        		musicObjLocation = i;
        		getSpecifiedMusic();
        		theStage.setScene(specifiedMusicScene);
        	}
    	}
    }
    public void mainMenuToExportMusic(ActionEvent e) {
    	if (e.getSource() == btExportMusicMainMenu)
    		theStage.setScene(exportMusicScene);
    }
    
    
    public void addMusicToMainMenu(ActionEvent e) {
    	if (e.getSource() == btAddMusicToMainMenu) {
    		theStage.setScene(mainMenuScene);
        	deserialize();
        	printMusic();
    	}
    }
    public void importMusicToMainMenu(ActionEvent e) {
    	if (e.getSource() == btImportMusicToMainMenu) {
    		theStage.setScene(mainMenuScene);
        	deserialize();
        	printMusic();
    	}	
    }
    public void exportMusicToMainMenu(ActionEvent e) {
    	if (e.getSource() == btExportMusicToMainMenu) {
    		theStage.setScene(mainMenuScene);
        	deserialize();
        	printMusic();
    	}
    }
    public void specifiedMusicToMainMenu(ActionEvent e) {
    	if (e.getSource() == btSpecifiedMusicToMainMenu) {
    		theStage.setScene(mainMenuScene);
        	deserialize();
        	printMusic();
    	}
    }
    
	public static void main(String[] args) {
		Application.launch(args);
	}

}
