package database.model;

public class Customer {
	Integer custId;
	String name;
	String address;
	String phone;
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Customer(int custId,String name,String address,String phone) {
		this.custId = custId;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
}
