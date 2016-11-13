package POJO;

public class Employees {

	private int employeeNumber;
	private String lastName;
	private String firstName;
	private String email;
	private String officeCode;
	private int reportsTo;
	private String jobTitle;

	public Employees(){}

	public Employees(int eN, String lN, String fN, String e, String oC, int rT, String jT){
		this.employeeNumber = eN;
		this.lastName = lN;
		this.firstName = fN;
		this.email = e;
		this.officeCode = oC;
		this.reportsTo = rT;
		this.jobTitle = jT;

	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}



}
