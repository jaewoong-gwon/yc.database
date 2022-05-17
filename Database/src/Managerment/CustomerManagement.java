package Managerment;

import java.util.List;
import java.util.Scanner;

import Model.Customer;
import Repository.CustomerRepository;

public class CustomerManagement {

	public static void main(String[] args) {
		CustomerRepository cr = new CustomerRepository();
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 고객의 이름을 입력하세요 : ");
		String name = scanner.nextLine();
		List<Customer> customer = cr.search(name);
		if (customer.size() == 1) {
			cr.display(customer);
			System.out.print("[ " + name + " ]" + "고객이 존재합니다. 주소를 수정하시겠습니까? (Y/N) : ");
			String answer = scanner.nextLine();
			if (answer.equals("Y") || answer.equals("y") || answer.equals("ㅛ")) {
				System.out.print("수정할 주소를 입력하세요 : ");
				String address = scanner.nextLine();
				cr.updateAddress(address, customer.get(0).getCustid());
			} else {
				System.out.println("시스템을 종료합니다!");
			}
		} else if (customer.size() >= 2) {
			cr.display(customer);
			System.out.print("[ " + name + " ]" + "고객이 두명 이상 존재합니다. 수정하려는 고객의 번호를 입력하세요 : ");
			Integer custid = scanner.nextInt();
			scanner.nextLine();
			System.out.print("[ " + name + " ]" + "고객이 존재합니다. 주소를 수정하시겠습니까? (Y/N) : ");
			String answer = scanner.nextLine();
			if (answer.equals("Y") || answer.equals("y") || answer.equals("ㅛ")) {
				System.out.print("수정할 주소를 입력하세요 : ");
				String address = scanner.nextLine();
				cr.updateAddress(address, custid);
			} else {
				System.out.println("시스템을 종료합니다!");
			}

		} else {
			System.out.println("[ " + name + " ]" + "고객이 존재하지 않습니다!");
		}
		scanner.close();
	}

}
