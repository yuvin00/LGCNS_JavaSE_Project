package workshop.account.entity;

public class Account {
	private String custId;
	private String acctId;
	private int balance;
	
	//Method 선언
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public int getBalance() {
		return balance;
	}
	
	public void deposit(int amount) {
		//this.balance = this.balance + amount;
		this.balance += amount; 
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
	}
	

}
