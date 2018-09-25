package books;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import books.model.*;
import books.view.*;


public class Main extends Application {

	private static Stage primaryStage;
	private BorderPane mainLayout;
	

	private ObservableList<Book> bookData = FXCollections.observableArrayList();

	public ObservableList<Book> getBookData() {
        return bookData;
    }
	
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
		
	public Main()
	{
		bookData.add(new Book("Default","Data"));	
		
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("WPAIO");
		
		showMainView();
		showCenterView();
		
		
		
	}
	
	
	
	public boolean openAddPanel(Book book) throws IOException
	{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddingPanel.fxml"));
		AnchorPane addLayout = (AnchorPane) loader.load();
		
		Stage panelStage = new Stage();
		panelStage.setTitle("Add New Book");
		panelStage.initModality(Modality.WINDOW_MODAL);
		panelStage.initOwner(primaryStage);
		
		Scene addScene = new Scene(addLayout);
		panelStage.setScene(addScene);
		
		
		AddingPanelController controller = loader.getController();
			
		controller.initStage(panelStage);
		controller.initMain(this);
		controller.setUpBook(book);
	
		panelStage.showAndWait();
		
		return controller.returnClicked();
		
		
	}
	
	
	
	private void showMainView() throws IOException 	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		
		MainViewController controller = loader.getController();
		controller.setMain(this);
		
		
		
		Scene scene = new Scene(mainLayout);
		Image ico = new Image("file:ico.png");
		primaryStage.getIcons().add(ico);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		File file = getPersonFilePath();
	    if (file != null) {
	        loadPersonDataFromFile(file);
	    }
	}
	
	private void showCenterView() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/BooksView.fxml"));
		BorderPane bookLayout = loader.load();
		
		mainLayout.setCenter(bookLayout);
		
		BooksViewController controller = loader.getController();
		if(controller!=null)
		{
		controller.setMainApp(this);
		}
	}
	
	public File getPersonFilePath()
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if(filePath !=null)
		{
			return new File(filePath);
		}
		else return null;
	}

	public void setPersonFilePath(File file)
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if(file !=null)
		{
			prefs.put("filePath", file.getPath());
			primaryStage.setTitle("WPAIO - " + file.getName());
		}
		else {
			prefs.remove("filePath");
			primaryStage.setTitle("WPAIO");
					
		}
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public void loadPersonDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(BookListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        BookListWrapper wrapper = (BookListWrapper) um.unmarshal(file);

	        bookData.clear();
	        bookData.addAll(wrapper.getBooks());

	        // Save the file path to the registry.
	        setPersonFilePath(file);

	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}

	
	public void savePersonDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(BookListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        BookListWrapper wrapper = new BookListWrapper();
	        wrapper.setBooks(bookData);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        setPersonFilePath(file);
	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
}
