
package bankSoft;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ----------------Account creation---------------------
		Account johns = new Account("AAA-0123-9293", "John Henry", 100000);
		System.out.println("New account created: ");
		johns.printInfo();
		
		try
		{
			Account bad = new Account("ABCD-1234", "Luke Bad", 10000);
			bad.printInfo();
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Creation failed: " + e.getMessage());
		}
		
		try 
		{
		    Account bad2 = new Account("AAA-0001", "", 100000);
		    bad2.printInfo();
		} 
		catch (IllegalArgumentException e) 
		{
		    System.out.println("Creation failed: " + e.getMessage());
		}

		
		Account user1 = new Account("AAA-123-4566", "Mark", 500000);
		Account user2 = new Account("AAA-013-2929", "Quang", 900000);
		
		user1.printInfo();
		user2.printInfo();
		
		// --------------- Deposit Money ---------------
		user1.deposit(0);
		user1.deposit(100000);
		
		user1.printInfo();
		user2.printInfo();
		
		// --------------- Withdraw Money ---------------
		user2.withdraw(0);
		user2.withdraw(1000000);
		user1.withdraw(450000);
		
		user1.printInfo();
		user2.printInfo();
		
		// --------------- Transfer Money ---------------
		user1.transfer(user2, 300000);
		user2.transfer(user1, 300000);
		
		user1.printInfo();
		user2.printInfo();
		
		// --------------- Pay bills --------------------
		
		user1.payBill("Dinner at Dorsia", 10000000);
		user2.deposit(3000000);
		user2.payBill("New shoes", 2700000);
		
		System.out.println("\n--- FINAL BALANCES ---");
		user1.printInfo();
		user2.printInfo();
	}

}
