package POJO;

public class Productlines {

	private String productLine;
	private String textDescription;

	public Productlines(){}

	public Productlines(String pL, String tD){
		this.productLine = pL;
		this.textDescription = tD;
	}


	public String getProductLine() {
		return productLine;
	}


	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}


	public String getTextDescription() {
		return textDescription;
	}


	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}


}
