package Example;

import java.util.Scanner;

public class BookMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Book book = new Book();
		boolean start = true;
		while (start) {
			book.display();
			int answer = scanner.nextInt();
			switch (answer) {
			case 0:
				book.sqlRun();
				break;
			case 1:
				book.find();
				break;
			case 2:
				book.insert();
				break;
			case 3:
				book.delete();
				break;
			case 5:
				book.display();
				break;
			case 6:
				scanner.close();
				book.exit();
				start = false;
				break;
			}
		}

	}

}
