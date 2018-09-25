package books.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import books.model.Book;

import java.io.IOException;

import books.Main;
public class BooksViewController {

	@FXML
	private TableView<Book> bookTable;
	
	@FXML
	private TableColumn<Book, String> bookNameColumn;
	
	@FXML
	private TableColumn<Book,String> bookAuthorColumn;
	
	@FXML
	private Label bookLabel;
	
	@FXML
	private Label authorLabel;
	
	@FXML 
	private Label curPag;
	
	@FXML
	private Label totPag;
	
	
	
	private Main main;
	
	public BooksViewController() {}
	
	@FXML
	private void initialize() {
        // Initialize the person table with the two columns.
        bookNameColumn.setCellValueFactory(cellData -> cellData.getValue().getBookProperty());
        bookAuthorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
        
        showBookDetails(null);
        
        bookTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable,oldValue,newValue) -> showBookDetails(newValue));
    }
	
	private void showBookDetails(Book book)
	{
		if(book!=null)
		{
			bookLabel.setText(book.getBook());
			authorLabel.setText(book.getAuthor());
			curPag.setText(String.valueOf(book.getCurrent()));
			totPag.setText(String.valueOf(book.getTotal()));	
			
			
			
		}
		else
		{
			bookLabel.setText("");
			authorLabel.setText("");
			curPag.setText("");
			totPag.setText("");	
			
		}
	}
	
	@FXML
	private void handleDeletePerson()
	{
		int index = bookTable.getSelectionModel().getSelectedIndex();
		if(index>=0)
		{
		bookTable.getItems().remove(index);
		}
		
	}
	
	@FXML
	private void openPanel() throws IOException
	{
	   Book temp = new Book();
	   boolean okClicked = main.openAddPanel(temp);
	   if(okClicked)
	   {
		   main.getBookData().add(temp);
	   }
	}
	
	@FXML
	private void editPanel() throws IOException
	{
		Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
		if(selectedBook !=null)
		{
			boolean okClicked = main.openAddPanel(selectedBook);
			if (okClicked) {	 
	          showBookDetails(selectedBook);
	         
	        }
			
		}
		
	}
	
	public void setMainApp(Main main)
	{
		this.main = main;
		
		bookTable.setItems(main.getBookData());
	}
	
	
	
	
	
}
