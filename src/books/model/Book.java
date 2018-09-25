package books.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {

	private final StringProperty bookName;
	private final StringProperty bookAuthor;
	private final IntegerProperty curPages;
	private final IntegerProperty totPages;
	
	private final BooleanProperty compl;
	
	
	
	
	public Book()
	{
		this.bookName = new SimpleStringProperty("");
		this.bookAuthor = new SimpleStringProperty("");
		
		curPages = new SimpleIntegerProperty(0);
		totPages = new SimpleIntegerProperty(0);
		compl = new SimpleBooleanProperty(false);
	}
	public Book(String _name,String _author)
	{
		this.bookName = new SimpleStringProperty(_name);
		this.bookAuthor = new SimpleStringProperty(_author);
		
		curPages = new SimpleIntegerProperty(0);
		totPages = new SimpleIntegerProperty(100);		
		compl = new SimpleBooleanProperty(false);
	}
	
	public String getBook()
	{
		return bookName.get();
	}
	public void setBook(String name)
	{
		bookName.set(name);
	}
	public StringProperty getBookProperty()
	{
		return bookName;
	}
	
	public String getAuthor()
	{
		return bookAuthor.get();
	}
	
	public void setAuthor(String name)
	{
		bookAuthor.set(name);
	}
	public StringProperty getAuthorProperty()
	{
		return bookAuthor;
	}
	
	public int getCurrent()
	{
		return curPages.get();
	}
	public void setCurrent(int cur)
	{
		curPages.set(cur);
	}
	public IntegerProperty getCurrentProperty()
	{
		return curPages;
	}
	public int getTotal()
	{
		return totPages.get();
	}
	public void setTotal(int tot)
	{
		totPages.set(tot);
	}
	public IntegerProperty getTotalProperty()
	{
		return totPages;
	}
	public BooleanProperty getCompl() {
		return compl;
	}
	public boolean getBool() {
		return compl.get();
	}
	public void setBool(boolean bool)
	{
		compl.set(bool);
	}
	
}
