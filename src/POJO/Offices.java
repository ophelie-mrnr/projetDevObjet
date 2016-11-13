package POJO;

public class Offices {

	private String officeCode;
	private String city;
	private String phone;
	private String addressLine;
	private String state;
	private String country;
	private String postalCode;


	public Offices(){}

	public Offices(String oC, String ci, String p, String aL, String s, String co, String pC){
		this.officeCode = oC;
		this.city = ci;
		this.phone = p;
		this.addressLine = aL;
		this.state = s;
		this.country = co;
		this.postalCode = pC;

	}

	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}

