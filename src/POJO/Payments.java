package POJO;

public class Payments {

	private int customerNumber;
	private String checkNumber;
	private String paymentDate;
	private double amount;


	public Payments(){}

	public Payments(int cuN, String chN, String pD, double a ){
		this.customerNumber = cuN;
		this.checkNumber = chN;
		this.paymentDate = pD;
		this.amount =a;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


}
