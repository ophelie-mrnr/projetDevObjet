package DAO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import POJO.Products;

public class DAOProducts extends DAO<Products> {
	
	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	BufferedImage img;
	
	public DAOProducts(Connection conn) {
		super(conn);
	}

	public boolean create(Products obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Products "
   					+ "VALUES (obj.getProductCode(),"
   					+ "obj.getProductName()"
   					+ "obj.getProductLine()"
   					+ "obj.getPhoto()"
   					+ "obj.getProductVendor()"
   					+ "obj.getProductDescription()"
   					+ "obj.getQuantityInStock()"
   					+ "obj.getBuyPrice()"
   					+ "obj.getMSRP()");
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			return false;
	}
		return true;
	}

	public boolean delete(Products obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE productCode ="
							+ obj.getProductCode());
			return true;
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
		}
	}

	public boolean update(Products obj) {
		try {

			System.out.println("BEGIN UPDATE");
            this.connect
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Products SET productName = '" + obj.getProductName()+ "'"
                			+ ", productLine = '"+obj.getProductLine()+"'"
        					// + ", photo = '"+obj.getPhoto()+"'"
        					+ ", productVendor = '"+obj.getProductVendor()+"'"
                			+ ", productDescription = '"+obj.getProductDescription()+"'"
                			+ ", quantityInStock = '"+obj.getQuantityInStock()+"'"
                			+ ", buyPrice = '"+obj.getBuyPrice()+"'"
                			+ ", MSRP = "+obj.getMSRP()			// probleme : il y avait une ' en trop alors que c'est un double
                			+" WHERE productCode = '"+obj.getProductCode()+"'"
                			
                 );
		obj = this.read(obj.getProductLine());
		return true;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
    	}
	}

	public Products find(int id) {
		return null;
	}

	public Products read(String id) {
		Products product = new Products();
		ImageIcon icon = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Products where productCode = '" + id+ "';");
			if(result.first()){
				if (result.getBlob("photo") != null){
					Blob blob = result.getBlob("photo");

					//Transform Blob into ImageIcon
					BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
					icon = new ImageIcon(ImageIO.read(is));
				}
				
			product = new Products(
					id,
					result.getString("productName"),
					result.getString("productLine"),
					icon,
					result.getString("productVendor"),
					result.getString("productDescription"),
					result.getInt("quantityInStock"),
					result.getDouble("buyPrice"),
					result.getDouble("MSRP")
					);
			}
		} catch(SQLException | IOException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return product;
	}
}

