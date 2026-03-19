package bankSoft;

public class Account {
	private String accountNumber;
	private String ownerName;
	private double balance;
	
	public static final double MIN_BALANCE = 50000;
	public static final double TRANSFERFEE_RATE = 0.02;
	
	public Account(String accNumber, String ownerName, double initBalance)
	{
		if(accNumber == null || accNumber.trim().isEmpty())
		{
			throw new IllegalArgumentException("Account number must not be empty.");
		}
		
		if(ownerName == null || ownerName.trim().isEmpty())
		{
			throw new IllegalArgumentException("Owner name must not be empty.");
		}
		
		if(initBalance < MIN_BALANCE)
		{
			throw new IllegalArgumentException("Account must have more than " + MIN_BALANCE + "VND");
		}
		this.accountNumber = accNumber;
		this.ownerName = ownerName;
		this.balance = initBalance;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	public String getAccountNumber()
	{
		return this.accountNumber;
	}
	public String getOwnerName()
	{
		return this.ownerName;
	}
	
	public void deposit(double amount)
	{
		if (amount <= 0)
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Error: invalid deposit amount! Amount must be greater than zero");
			System.out.println("Deposit is rejected\n");
		}
		else
		{
			this.balance += amount;
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Deposit success. Current balance: " + this.balance + "\n");
		}
	}
	
	public void withdraw(double amount)
	{
		if(amount <= 0)
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Error: invalid withdrawal amount! Amount must be greater than zero");
			System.out.println("Withdrawal is rejected\n");
			return;
		}
		if(this.balance - amount < MIN_BALANCE)
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Error: insufficient balance! withdrawing will lower your balance to under the required minimum balance");
			System.out.println("Withdrawal is rejected\n");
			return;
		}
		
		this.balance -= amount;
		System.out.println("-----Account user: " + this.ownerName + "-----");
		System.out.println("Withdrawal success. Current balance: " + this.balance + "\n");
	}
	
	public void transfer(Account receiver, double amount)
	{
		if (receiver == null) 
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
	        System.out.println("Error! Receiver account does not exist.\n");
	        return;
	    }
		if(amount <= 0)
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Error: invalid transfer amount! Amount must be greater than zero");
			System.out.println("Transfer is rejected\n");
			return;
		}
		if(this.balance - amount * (1 + TRANSFERFEE_RATE) < MIN_BALANCE)
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Error: Insufficient funds! Your balance must cover the transfer amount plus fee");
			System.out.println("Transfer is rejected\n");
			return;
		}
		
		double fee = amount * TRANSFERFEE_RATE;
		this.balance -= amount + fee;
		receiver.balance += amount;
		System.out.println("-----Account user: " + this.ownerName + "-----");
		System.out.println("Transaction successful: " + this.ownerName + " -> " + receiver.ownerName);
		System.out.println("Amount transferred: " + amount + " | Fee: " + fee);
		System.out.println("Balance: " + this.balance + "\n");
		
	}
	
	public void payBill(String billName, double amount)
	{
		if (billName == null || billName.trim().isEmpty()) 
		{
	        System.out.println("Error! Bill name must not be empty.\n");
	        return;
	    }
		if(amount <= 0)
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Error: invalid amount! Amount must be greater than zero");
			System.out.println("Bill payment is rejected\n");
			return;
		}
		if(this.balance - amount < MIN_BALANCE)
		{
			System.out.println("-----Account user: " + this.ownerName + "-----");
			System.out.println("Error: Insufficient funds! Your balance must cover the bill");
			System.out.println("Bill payment is rejected\n");
			return;
		}
		
		this.balance -= amount;
		System.out.println("-----Account user: " + this.ownerName + "-----");
		System.out.println("Bill payment successful");
		System.out.println("Service: " + billName);
		System.out.println("Balance: " + this.balance + "\n");
	}
	
	public void printInfo() {
		System.out.println("----------------------------------------------------------------------------");
	    System.out.println("Account [" + accountNumber + "] | Owner: " + ownerName + " | Balance: " + balance);
	    System.out.println("----------------------------------------------------------------------------\n");
	}
	
}
