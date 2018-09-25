package books.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import books.Main;
import books.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddingPanelController {

	@FXML
	private TextField bookField;
	
	@FXML
	private TextField authorField;
	
	@FXML
	private TextField curField;
	
	@FXML
	private TextField totField;
	
	private boolean okClicked = false;
	private Book book;
	private Main main;
	
	private Stage addStage;
	
	public boolean returnClicked()
	{
		return okClicked;
	}
	
	public void initStage(Stage stage)
	{
		this.addStage = stage;
	}
		
	public void initMain(Main main)
	{
		this.main = main;
	}
	
	public void setUpBook(Book book)
	{
		this.book = book;
		
		bookField.setText(book.getBook().toString());
		authorField.setText(book.getAuthor().toString());
		curField.setText(Integer.toString(book.getCurrent()));
		totField.setText(Integer.toString(book.getTotal()));
		
		
	}
	
	
	private boolean inputOk()
	{
		int op1,op2;
		if(bookField.getText().toString() == "") return false;
		if(authorField.getText().toString() == "") return false;
		try {
		     op1 = Integer.parseInt(curField.getText());
		} catch (NumberFormatException e) {
		    
		    return false;
		}
		try {
		     op2 = Integer.parseInt(totField.getText());
		} catch (NumberFormatException e) {
	
		    return false;
		}
		
		return true;
		
		
	}
	@FXML
	private void handleOk()
	{
		if(inputOk() == true)
		{
		book.setAuthor(authorField.getText().toString());
		book.setBook(bookField.getText().toString());
		book.setCurrent(Integer.valueOf(curField.getText()));
		book.setTotal(Integer.valueOf(totField.getText()));
		
		if(Integer.valueOf(book.getCurrent()) == Integer.valueOf(book.getTotal()))
		{
			book.setBool(true);
		}
		
		
		
		if(Integer.valueOf(curField.getText()) > Integer.valueOf(totField.getText()))
		{
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("ERROR");
	        alert.setHeaderText("Current page higher.");
	        alert.setContentText("Current page can't be higher than total page.");

	        alert.showAndWait();
	        
	        okClicked = false;
		
		
	 }
		else
		{
		okClicked=true;
		addStage.close();
		}
		}
	}
	
	
	@FXML
	private void closeStage()
	{
		addStage.close();
	}
	
	
}
