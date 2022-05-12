package Example;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Customer {
	Connection con;

	public Customer() {
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

	private void sqlRun() {
		String query = "SELECT * FROM Customer"; /* SQL 문 */
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Custid \tName \tAddress \tPhone");
			while (rs.next()) {
				System.out.print(rs.getInt(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t" + rs.getString(3));
				System.out.println("\t" + rs.getString(4));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void findInf(String name) {
		Scanner scanner = new Scanner(System.in);
		String query = "SELECT name,address FROM Customer WHERE name = '" + name + "'";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			/*
			 * while(rs.next()) { System.out.println("이름 : " + rs.getString(1));
			 * System.out.println("주소 : " + rs.getString(2)); }
			 */
			if (rs.next()) {
				System.out.println("이름 : " + rs.getString(1));
				System.out.println("주소 : " + rs.getString(2));
				System.out.print("[" + name + "]" + "고객이 존재합니다. 주소를 수정하시겠습니까? (Y/N) : ");
				String answer = scanner.nextLine();
				if (answer.equals("y") || answer.equals("Y")) {
					System.out.print("수정할 주소를 입력하세요 : ");
					String address = scanner.nextLine();
					query = "UPDATE Customer SET address = ? WHERE name = ?";
					try (PreparedStatement pstmt = con.prepareStatement(query)) {
						pstmt.setString(1, address);
						pstmt.setString(2, name);
						int res = pstmt.executeUpdate();
						if (res > 0)
							System.out.println("주소가 성공적으로 수정되었습니다." + " (" + address + ")");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			scanner.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Customer customer = new Customer();
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 고객의 이름을 입력하세요 : ");
		String answer = scanner.next();
		customer.findInf(answer);
		//customer.sqlRun();
		scanner.close();
	}

}
