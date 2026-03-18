package bankSoft;

public class Account {
	String accountNumber;
	String ownerName;
	double balance;
	
	static final double MIN_BALANCE = 50000;
	static final double TRANSFERFEE_RATE = 0.02;
	
	Account(String accNumber, String ownerName, double initBalance)
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
	
	double getBalance()
	{
		return this.balance;
	}
	String getAccountNumber()
	{
		return this.accountNumber;
	}
	String getOwnerName()
	{
		return this.ownerName;
	}
	
	
}
