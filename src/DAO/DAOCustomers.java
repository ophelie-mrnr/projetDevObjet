package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
			
			LOGGER.log(Level.INFO, "Requete INSERT");			
			
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
		
		PreparedStatement pstmt = null;
		
		try{
			LOGGER.log(Level.INFO, "Requete DELETE");
			
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

		/*PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt8 = null;
		PreparedStatement pstmt9 = null;*/
		
		try{
			LOGGER.log(Level.INFO, "Requete UPDATE");
			
			/*pstmt1 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET customerName = ? WHERE customerNumber = ?");		               	
			 pstmt1.executeUpdate();
			pstmt1.setString(1, obj.getCustomerName());
			pstmt1.setLong(2, obj.getCustomerNumber());
			
			pstmt2 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET customerLastName = ? WHERE customerNumber = ?");		               	
			pstmt2.executeUpdate();			
			pstmt2.setString(1, obj.getContactLastName());
			pstmt2.setLong(2, obj.getCustomerNumber());
			
			pstmt3 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET customerFirstName = ? WHERE customerNumber = ?");		               	
			pstmt3.executeUpdate();;
			pstmt3.setString(1, obj.getContactFirstName());
			pstmt3.setLong(2, obj.getCustomerNumber());
			
			pstmt4 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET phone = ? WHERE customerNumber = ?");		               	
			pstmt4.executeUpdate();
			pstmt4.setString(1, obj.getPhone());
			pstmt4.setLong(2, obj.getCustomerNumber());
			
			pstmt5 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET city = ? WHERE customerNumber = ?");		               	
			pstmt5.executeUpdate();
			pstmt5.setString(1, obj.getCity());
			pstmt5.setLong(2, obj.getCustomerNumber());
			
			pstmt6 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET state = ? WHERE customerNumber = ?");		               	
			pstmt6.executeUpdate();
			pstmt6.setString(1, obj.getState());
			pstmt6.setLong(2, obj.getCustomerNumber());
			
			pstmt7 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET postalCode = ? WHERE customerNumber = ?");		               	
			pstmt7.executeUpdate();
			pstmt7.setString(1, obj.getPostalCode());
			pstmt7.setLong(2, obj.getCustomerNumber());
			
			pstmt8 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET country = ? WHERE customerNumber = ?");		               	
			pstmt8.executeUpdate();
			pstmt8.setString(1, obj.getCountry());
			pstmt8.setLong(2, obj.getCustomerNumber());
			
			pstmt9 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET salesRepEmployeeNumber = ? WHERE customerNumber = ?");		               	
			pstmt9.executeUpdate();
			pstmt9.setLong(1, obj.getSalesRepEmployeeNumber());	
			pstmt9.setLong(2, obj.getCustomerNumber());
			 
			*/
			 
			 
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



