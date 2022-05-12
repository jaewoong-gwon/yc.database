package database.repository.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.model.Customer;

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

	public List<Customer> selectAllCustomer(){
		String query = "SELECT * FROM Customer ORDER BY custid";
		try(PreparedStatement pstmt = getConnection().prepareStatement(query);
				ResultSet myResultSet = pstmt.executeQuery();){
			List<Customer> customer = new ArrayList<Customer>();
			
		while(myResultSet.next()) {
			int custId = myResultSet.getInt("custid");
			String name = myResultSet.getString("name");
			String address = myResultSet.getString("address");
			String phone = myResultSet.getString("phone");
			
			customer.add(new Customer(custId,name,address,phone));
		}
		return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	public boolean update(String address) {
		String query = "UPDATE Customer SET address = ?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(query)){
			pstmt.setString(1,address);
			int res = pstmt.executeUpdate();

			
			if(res > 0) {
				System.out.println("주소를 성공적으로 수정하였습니다.");
				return true;
			}
			else {
				System.out.println("주소를 수정하지 못했습니다.");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
