package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.Productlines;

public class DAOProductlines extends DAO<Productlines> {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	public DAOProductlines(Connection conn) {
		super(conn);
	}
		
	public boolean create(Productlines obj) {

		PreparedStatement stmt = null;
		
		try{
			LOGGER.log(Level.INFO, "Requete INSERT");
			
			stmt = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("INSERT INTO Productlines VALUES (?, ?)");
			stmt.setString(1, obj.getProductLine());
			stmt.setString(2, obj.getTextDescription());	
			
			stmt.execute();
			/*
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Productlines "
						+ "VALUES (obj.getProductLine(),"
						+ "obj.getTextDescription()");
			 */
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			return false;
	}
		return true;
	}

	public boolean delete(Productlines obj) {
		
		try{
			LOGGER.log(Level.INFO, "Requete DELETE");
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE productLine ="
							+ obj.getProductLine());
			return true;
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
		}
	}

	public boolean update(Productlines obj) {
		
		try {
			LOGGER.log(Level.INFO, "Requete UPDATE");
			
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET textDescription = '" + obj.getTextDescription()+ "'"
                					+" WHERE productLine = '"+obj.getProductLine()+"'"
                	
                 );
		obj = this.read(obj.getProductLine());
		return true;
    } catch (SQLException e) {
    		LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
    }
}	

	public Productlines find(int id) {
		return null;
	}

	public Productlines read(String id) {
		Productlines productline = new Productlines();

		try{
			
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Productlines where productLine = " + id);
			if(result.first())
				productline = new Productlines(
						id,
						result.getString("textDescription")
						);

		} catch(SQLException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return productline;
	}
}
