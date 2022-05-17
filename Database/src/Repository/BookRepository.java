package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Book;

public class BookRepository {
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			return DriverManager.getConnection(url, "c##madang", "madang");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Book> selectAllBook() {
		String query = "SELECT * FROM Book ORDER BY bookid";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query);
				ResultSet myResultSet = pstmt.executeQuery();) {
			List<Book> books = new ArrayList<Book>();

			while (myResultSet.next()) {
				int bookId = myResultSet.getInt("bookid");
				String bookName = myResultSet.getString("bookname");
				String publisher = myResultSet.getString("publisher");
				int price = myResultSet.getInt("price");

				books.add(new Book(bookId, bookName, publisher, price));
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
