package POJO;

import javax.swing.ImageIcon;

public class Products {

	private String productCode;
	private String productName;
	private String productLine;
	private ImageIcon photo;
	private String productVendor;
	private String productDescription;
	private int quantityInStock;
	private double buyPrice;
	private double MSRP;

	public Products(){}

	public Products(String pC, String pN, String pL, ImageIcon p, String pV, String pD,
	int qIS, double bP, double M){
		this.productCode = pC;
		this.productName = pN;
		this.productLine = pL;
		this.photo = p;
		this.productVendor = pV;
		this.productDescription = pD;
		this.quantityInStock = qIS;
		this.buyPrice = bP;
		this.MSRP = M;

	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public ImageIcon getPhoto() {
		return photo;
	}

	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getMSRP() {
		return MSRP;
	}

	public void setMSRP(double mSRP) {
		MSRP = mSRP;
	}

}
