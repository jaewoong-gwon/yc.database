package database.repository.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.model.Book;

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
	public boolean update() {
		return false;
	}

	public boolean insert(int bookId, String bookName, String publisher, int price) {
		String query = "INSERT INTO Book (bookid,bookname,publisher,price) VALUES (?,?,?,?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, bookId);
			pstmt.setString(2, bookName);
			pstmt.setString(3, publisher);
			pstmt.setInt(4, price);
			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println("도서를 정상적으로 등록하였습니다");
				return true;
			}

		} catch (SQLException e) {
			System.out.println("도서 등록에 실패했습니다! - " + e.getLocalizedMessage().trim());
		}
		return false;
	}

	public List<Book> search(String pattern) {
		String query = "SELECT * FROM Book WHERE bookname LIKE ? OR publisher LIKE ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setString(1,"%" + pattern + "%");
			pstmt.setString(2, "%" + pattern + "%");

			ResultSet myResultSet = pstmt.executeQuery();
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
