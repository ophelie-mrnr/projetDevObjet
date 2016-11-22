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
		
		PreparedStatement stmt = null;
		try{
			
			LOGGER.log(Level.INFO, "Requete INSERT");
			
			stmt = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("INSERT INTO Customers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setLong(1, obj.getCustomerNumber());
			stmt.setString(2, obj.getCustomerName());
			stmt.setString(3, obj.getContactLastName());
			stmt.setString(4, obj.getContactFirstName());
			stmt.setString(5, obj.getPhone());
			stmt.setString(6, obj.getCity());
			stmt.setString(7, obj.getState());
			stmt.setString(8, obj.getPostalCode());
			stmt.setString(9, obj.getCountry());
			stmt.setLong(10, obj.getSalesRepEmployeeNumber());			
			
			stmt.execute();
			
			/*
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
			 */	
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
			
			pstmt = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			               ResultSet.CONCUR_UPDATABLE)).prepareStatement("DELETE FROM customers WHERE CustomerNumber = ?");		               	
					
			 pstmt.setLong( 1, obj.getCustomerNumber()); 
			 pstmt.execute();
			
			/*
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE CustomerNumber ="
							+ obj.getCustomerNumber());
			*/
			return true;
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
	}
	}

	public boolean update(Customers obj) {

		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt8 = null;
		PreparedStatement pstmt9 = null;
		
		try{
			LOGGER.log(Level.INFO, "Requete UPDATE");
			
			pstmt1 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET customerName = ?");		               	
			 pstmt1.execute();
			pstmt1.setString(1, obj.getCustomerName());
			
			pstmt2 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET customerLastName = ?");		               	
			pstmt2.execute();			
			pstmt2.setString(1, obj.getContactLastName());
			
			pstmt3 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET customerFirstName = ?");		               	
			pstmt3.execute();
			pstmt3.setString(1, obj.getContactFirstName());
			
			pstmt4 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET phone = ?");		               	
			pstmt4.execute();
			pstmt4.setString(1, obj.getPhone());
			
			pstmt5 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET city = ?");		               	
			pstmt5.execute();
			pstmt5.setString(1, obj.getCity());
			
			pstmt6 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET state = ?");		               	
			pstmt6.execute();
			pstmt6.setString(1, obj.getState());
			
			pstmt7 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET postalCode = ?");		               	
			pstmt7.execute();
			pstmt7.setString(1, obj.getPostalCode());
			
			pstmt8 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET country = ?");		               	
			pstmt8.execute();
			pstmt8.setString(1, obj.getCountry());
			
			pstmt9 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET salesRepEmployeeNumber = ?");		               	
			pstmt9.execute();
			pstmt9.setLong(1, obj.getSalesRepEmployeeNumber());	
			 
			
			 
			 /*
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
                 */
			 
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



