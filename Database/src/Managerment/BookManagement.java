package Managerment;

import java.util.List;
import Model.Book;
import Repository.BookRepository;

public class BookManagement {

	public static void main(String[] args) {
		BookRepository br = new BookRepository();
		List<Book> books = br.selectAllBook();
		for(Book book : books) {
			System.out.println("");
		}
	}

}
