package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.Customers;

public class DAOCustomers extends DAO<Customers> {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	public DAOCustomers(Connection conn) {
		super(conn);
	}

	public boolean create(Customers obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Customers "
					+ "VALUES (obj.getCustomerNumber(),"
					+ "obj.getCustomerName(),"
					+ "obj.getContactLastName(),"
					+ "obj.getContactFirstName(),"
					+ "obj.getPhone(),"
					+ "obj.getCity(),"
					+ "obj.getState(),"
					+ "obj.getPostalCode(),"
					+ "obj.getCountry(),"
					+ "obj.getSalesRepEmployeeNumber()");
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			return false;
	}
		return true;
	}

	public boolean delete(Customers obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE CustomerNumber ="
							+ obj.getCustomerNumber());
			return true;
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
	}
	}

	public boolean update(Customers obj) {
		try {
            this .connect
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET customerName = '" + obj.getCustomerName() + "'"
                					+ ", contactLastName = '"+obj.getContactLastName()+"'"
                					+ ", contactFirstName = '"+obj.getContactFirstName()+"'"
                					+ ", phone = '"+ obj.getPhone()+"'"
                					+ ", city = '"+ obj.getCity()+"'"
                					+ ", state = '"+obj.getState()+"'"
                					+ ", postalCode = '"+obj.getPostalCode()+"'"
                					+ ", country = '"+obj.getCountry()+ "'"
                					+ ", salesRepEmployeeNumber = '"+obj.getSalesRepEmployeeNumber()
                					+" WHERE customerNumber = '" + obj.getCustomerNumber()+"'"
                 );
		obj = this.find(obj.getCustomerNumber());
		return true;
    } catch (SQLException e) {
    	LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
    }
}


	public Customers find(int id) {

		Customers customers = new Customers();

		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Customers where CustomerNumber = " + id);
			if(result.first())
				customers = new Customers(
						id,
						result.getString("customerName"),
						result.getString("contactLastName"),
						result.getString("contactFirstName"),
						result.getString("phone"),
						result.getString("city"),
						result.getString("state"),
						result.getString("postalCode"),
						result.getString("country"),
						result.getInt("salesRepEmployeeNumber")
						);
		} catch(SQLException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return customers;

	}


	public Customers read(String id) {
		return null;
	}
}



