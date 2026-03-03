package mainCode;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		
		int height = keyboard.nextInt();
		
		Triangle a = new Triangle(height);
		
		a.createTriangle();
		a.printTriangle();

	}

}
