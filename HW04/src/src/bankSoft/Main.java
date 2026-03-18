
package bankSoft;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account johns = new Account("AAA-0123-9293", "John Henry", 100000);
		System.out.println("New account created: ");
		System.out.println("Account Number: " + johns.accountNumber);
		System.out.println("Account owner: " + johns.ownerName);
		System.out.println("Balance: " + johns.balance);
		

	}

}
