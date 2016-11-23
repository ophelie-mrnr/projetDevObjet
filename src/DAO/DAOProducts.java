package DAO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

			LOGGER.log(Level.INFO, "Requete INSERT");

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
			LOGGER.log(Level.INFO, "Requete DELETE");

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM products WHERE productCode ="
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

		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;

		try{
			
			LOGGER.log(Level.INFO, "Requete UPDATE");

			/*pstmt1 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Products SET productName = ? WHERE productCode = ");
			pstmt1.execute();
			pstmt1.setString(1, obj.getProductName());
			pstmt1.setString(2, obj.getProductCode());

			pstmt2 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Products SET productLine = ? WHERE productCode = ?");
			pstmt2.execute();
			pstmt2.setString(1, obj.getProductLine());
			pstmt2.setString(2, obj.getProductCode());

			pstmt3 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Products SET productVendor = ? WHERE productCode = ?");
			pstmt3.execute();
			pstmt3.setString(1, obj.getProductVendor());
			pstmt3.setString(2, obj.getProductCode());

			pstmt4 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Products SET productDescription = ? WHERE productCode = ?");
			pstmt4.execute();
			pstmt4.setString(1, obj.getProductDescription());
			pstmt4.setString(2, obj.getProductCode());

			pstmt5 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Products SET quantityInStock = ? WHERE productCode = ?");
			pstmt5.execute();
			pstmt5.setLong(1, obj.getQuantityInStock());
			pstmt5.setString(2, obj.getProductCode());

			pstmt6 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Products SET buyPrice = ? WHERE productCode = ?");
			pstmt6.execute();
			pstmt6.setLong(1, (long) obj.getBuyPrice());
			pstmt6.setString(2, obj.getProductCode());

			pstmt7 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Products SET MSRP = ? WHERE productCode = ?");
			pstmt7.execute();
			pstmt7.setLong(1, (long) obj.getMSRP());
			pstmt7.setString(2, obj.getProductCode());
*/
		

			
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
                			+ ", MSRP = "+obj.getMSRP()			
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

