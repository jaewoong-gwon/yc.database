package Model;

public class Book {
	Integer bookid;
	String bookname;
	String publisher;
	Integer price;
	public Book(Integer bookid, String bookname, String publisher, Integer price) {
		this.bookid = bookid;
		this.bookname = bookname;
		this.publisher = publisher;
		this.price = price;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
