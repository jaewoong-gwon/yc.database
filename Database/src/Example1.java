import java.sql.*;
import java.util.Scanner;

import javax.lang.model.element.ExecutableElement;

public class Example1 {
	Connection con;

	public Example1() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "c##madang"; /* 12버전 이상은 c##을 붙인다. */
		String pwd = "madang";

		try { /* 드라이버를 찾는 과정 */
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 클래스 이름의 JBDC 드라이버를 로딩,오라클 db 사용중.
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

	private void sqlRun() {
		String query = "SELECT * FROM Book"; /* SQL 문 */
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("BOOK NO \tBOOK NAME \t\tPUBLISHER \tPRICE");
			while (rs.next()) {
				System.out.print("\t" + rs.getInt(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t\t" + rs.getString(3));
				System.out.println("\t" + rs.getInt(4));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void s(String name) { String query =
	 * "SELECT name FROM Customer WHERE name = ?"; try(PreparedStatement pstmt =
	 * con.prepareStatement(query)){ pstmt.setString(1,name); System.out.println();
	 * ResultSet rs = pstmt.executeQuery(query); while(rs.next()) {
	 * System.out.println(rs.getString(1)); } }catch(SQLException e) {
	 * e.printStackTrace(); } }
	 */

	private void find(String name) {
		String query = "SELECT name,address FROM Customer WHERE name = '" + name + "'"; /* SQL 문 */
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("이름 : " + rs.getString(1));
				System.out.println("주소 : " + rs.getString(2));
			}
			
		
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Example1 ex1 = new Example1();
		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 고객의 이름을 입력하세요 : ");
		String r = sc.next();
		ex1.find(r);
		sc.close();
	}
}