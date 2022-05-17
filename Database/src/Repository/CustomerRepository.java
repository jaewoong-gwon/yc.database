package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Customer;

public class CustomerRepository {
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

	public List<Customer> selectAllCustomer() {
		String query = "SELECT * FROM Customer ORDER BY custid";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query);
				ResultSet myResultSet = pstmt.executeQuery();) {
			List<Customer> customer = new ArrayList<Customer>();

			while (myResultSet.next()) {
				int custId = myResultSet.getInt("custid");
				String name = myResultSet.getString("name");
				String address = myResultSet.getString("address");
				String phone = myResultSet.getString("phone");

				customer.add(new Customer(custId, name, address, phone));
			}
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Customer> search(String name) {
		String query = "SELECT * FROM Customer WHERE name = ? ORDER BY custid";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			List<Customer> customer = new ArrayList<Customer>();
			pstmt.setString(1, name);
			ResultSet myResultSet = pstmt.executeQuery();

			while (myResultSet.next()) {
				customer.add(
						new Customer(myResultSet.getInt(1), name, myResultSet.getString(3), myResultSet.getString(4)));
			}
			return customer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void display(List<Customer> customer) {
		System.out.println("Custid\t Name\t Address\t Phone");
		for(Customer c : customer) {
			System.out.println(c.getCustid() + "\t " + c.getName() + "\t " + c.getAddress() + "\t " + c.getPhone());
		}
	}

	public void updateAddress(String address, Integer custId) {
		String query = "UPDATE Customer SET address = ? WHERE custid = ?";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setString(1, address);
			pstmt.setInt(2, custId);

			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println("주소가 성공적으로 수정되었습니다." + "( " + address + " )");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
