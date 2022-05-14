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

	public boolean update(Book book) {
		String query = "UPDATE Book SET bookname = ?, publisher = ?, price = ? WHERE bookid = ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getPublisher());
			pstmt.setInt(3, book.getPrice());
			pstmt.setInt(4, book.getBookId());

			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println("도서를 성공적으로 수정하였습니다.");
				return true;
			} else {
				System.out.println("도서를 수정하지 못했습니다.");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(Book book) {
		String query = "INSERT INTO Book (bookid,bookname,publisher,price) VALUES (?,?,?,?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, book.getBookId());
			pstmt.setString(2, book.getBookName());
			pstmt.setString(3, book.getPublisher());
			pstmt.setInt(4, book.getPrice());
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
			pstmt.setString(1, "%" + pattern + "%");
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

	public boolean delete(Book book) {
		String query = "DELETE FROM Book WHERE bookid = ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, book.getBookId());
			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println("도서를 정상적으로 삭제하였습니다");
				return true;
			} else
				System.out.println("해당 번호의 도서가 없습니다!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(int id) {
		String query = "DELETE FROM Book WHERE bookid = ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, id);
			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println("도서를 정상적으로 삭제하였습니다");
				return true;
			} else
				System.out.println("해당 번호의 도서가 없습니다!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
