package books.model;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Books")
public class BookListWrapper {

	
	

	    private List<Book> books;

	    @XmlElement(name = "Book")
	    public List<Book> getBooks() {
	        return books;
	    }

	    public void setBooks(List<Book> books) {
	        this.books = books;
	    }
	}
	
	

