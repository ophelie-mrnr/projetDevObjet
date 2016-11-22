package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.Offices;

public class DAOOffices extends DAO<Offices> {
	
	private static final Logger LOGGER = Logger.getLogger("myLogger");

	public DAOOffices(Connection conn) {
		super(conn);
	}

	public boolean create(Offices obj) {
		
		PreparedStatement stmt = null;
		
		try{
			LOGGER.log(Level.INFO, "Requete INSERT");
			
			stmt = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("INSERT INTO Offices VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getOfficeCode());
			stmt.setString(2, obj.getCity());
			stmt.setString(3, obj.getPhone());
			stmt.setString(4, obj.getAddressLine());
			stmt.setString(5, obj.getState());
			stmt.setString(6, obj.getCountry());
			stmt.setString(7, obj.getPostalCode());			
			
			stmt.execute();
			/*
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Offices "
						+ "VALUES (obj.getOfficeCode(),"
						+ "obj.getCity(),"
						+ "obj.getPhone(),"
						+ "obj.getAddressLine(),"
						+ "obj.getState(),"
						+ "obj.getCountry(),"
						+ "obj.getPostalCode()");
			 */
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			return false;
	}
		return true;
	}

	public boolean delete(Offices obj) {
		try{
			LOGGER.log(Level.INFO, "Requete DELETE");
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE officeCode ="
							+ obj.getOfficeCode());
			return true;
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
		}
	}

	public boolean update(Offices obj) {
		try {
			LOGGER.log(Level.INFO, "Requete UPDATE");
			
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET city = '" + obj.getCity() + "'"
                					+ ", phone = '"+obj.getPhone()+"'"
                					+ ", addressLine = '"+obj.getAddressLine()+"'"
                					+ ", state = '"+ obj.getState()+"'"
                					+ ", country = '"+ obj.getCountry()+"'"
                					+ ", postalCode = '"+obj.getPostalCode()
                					+" WHERE employeeNumber = '" + obj.getOfficeCode()+"'"
                 );
		obj = this.read(obj.getOfficeCode());
		return true;
    } catch (SQLException e) {
    	LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
    }
}	

	public Offices find(int id) {
		return null;
	}

	public Offices read(String id) {
		Offices offices = new Offices();

		try{
			
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Offices where officeCode = " + id);
			if(result.first())
				offices = new Offices(
						id,
						result.getString("city"),
						result.getString("phone"),
						result.getString("addressLine"),
						result.getString("state"),
						result.getString("country"),
						result.getString("postalCode")
						);

		} catch(SQLException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return offices;
	}
}
