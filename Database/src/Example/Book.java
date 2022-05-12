package Example;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Book {
	Connection con;

	// 생성자, DB와 연결함
	public Book() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "c##madang"; /* 12버전 이상은 c##을 붙인다. */
		String pwd = "madang";

		try { /* 드라이버를 찾는 과정 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비 ...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 메뉴 출력 메소드
	public void display() {
		System.out.println("--------------------");
		System.out.println("0.모든 도서 보기");
		System.out.println("1.도서 찾기");
		System.out.println("2.도서등록하기");
		System.out.println("3.도서지우기");
		System.out.println("4.도서 수정");
		System.out.println("5.메인메뉴");
		System.out.println("6.프로그램 종료");
		System.out.println("--------------------");
		System.out.print("원하는 번호를 선택하세요 : ");
	}

	// 도서 출력 메소드
	public void sqlRun() {
		String query = "SELECT * FROM Book"; /* SQL 문 */
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("도서번호 : \t제목 : \t\t출판사 : \t가격:");
			while (rs.next()) {
				System.out.print("\t" + rs.getInt(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t\t" + rs.getString(3));
				System.out.println("\t" + rs.getInt(4));
			}

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 도서 찾는 메소드
	public void find() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("도서의 이름을 입력하세요 : ");
		String bookname = scanner.nextLine();
		String query = "SELECT * FROM Book WHERE bookname = ?";
		System.out.println("도서번호 : \t제목 : \t\t출판사 : \t가격:");
		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, bookname);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print("\t" + rs.getInt(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t\t" + rs.getString(3));
				System.out.println("\t" + rs.getInt(4));
			}
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// scanner.close();
	}

	// 도서 등록 메소드
	public void insert() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("도서번호 : ");
		int bookId = Integer.parseInt(scanner.nextLine()); // bookId 입력받기
		System.out.print("제목 : ");
		String bookName = scanner.nextLine(); // bookName 입력받기
		System.out.print("출판사 : ");
		String publisher = scanner.nextLine(); // publisher 입력받기
		System.out.print("가격 : ");
		int price = Integer.parseInt(scanner.nextLine()); // price 입력 받기
		String query = "INSERT INTO Book(bookid,bookname,publisher,price) VALUES (?,?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, bookId);
			pstmt.setString(2, bookName);
			pstmt.setString(3, publisher);
			pstmt.setInt(4, price);
			int res = pstmt.executeUpdate();

			if (res > 0)
				System.out.println("도서를 정상적으로 등록하였습니다.");

			// con.close();
		} catch (SQLException e) {
			System.out.println("도서 등록에 실패하였습니다. - " + e.getLocalizedMessage().trim());
			// e.printStackTrace();
		}

		// scanner.close();
	}

	public void delete() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("삭제할 도서번호 : ");
		int bookId = Integer.parseInt(scanner.nextLine());
		String query = "DELETE FROM Book WHERE bookid = ?";
		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, bookId);
			int res = pstmt.executeUpdate();

			if (res > 0)
				System.out.println("도서를 정상적으로 삭제하였습니다.");
			else
				System.out.println("해당 번호의 도서가 없습니다");
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 도서 수정 메소드
	public void update() {

	}

	public void exit() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
