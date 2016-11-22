package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.Orders;

public class DAOOrders extends DAO<Orders> {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	public DAOOrders(Connection conn) {
		super(conn);
	}

	public boolean create(Orders obj) {		
		
		try{
			LOGGER.log(Level.INFO, "Requete INSERT");
			
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Orders "
						+ "VALUES (obj.getOrderNumber(),"
						+ "obj.getOrderDate(),"
						+ "obj.getRequiredDate(),"
						+ "obj.getShippedDate(),"
						+ "obj.getStatus()"
						+ "obj.getComments()"
						+ "obj.getCustomerNumber()");
			 
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			return false;
	}
		return true;
	}

	public boolean delete(Orders obj) {
		
		try{
			LOGGER.log(Level.INFO, "Requete DELETE");
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE orderNumber ="
							+ obj.getOrderNumber());
			return true;
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
		}
	}

	public boolean update(Orders obj) {
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;

		try {
			LOGGER.log(Level.INFO, "Requete UPDATE");
			
			pstmt1 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET orderDate = ? WHERE orderNumber = ? ");
			pstmt1.setString(1, obj.getOrderDate());
			pstmt1.setLong(2, obj.getOrderNumber());
			pstmt1.executeUpdate();
			
			pstmt2 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET requiredDate = ? WHERE orderNumber = ? ");
			pstmt2.setString(1, obj.getRequiredDate());
			pstmt2.setLong(2, obj.getOrderNumber());
			pstmt2.executeUpdate();
			
			pstmt3 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET shippedDate = ? WHERE orderNumber = ? ");
			pstmt3.setString(1, obj.getShippedDate());
			pstmt3.setLong(2, obj.getOrderNumber());
			pstmt3.executeUpdate();
		
			pstmt4 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET getStatus = ? WHERE orderNumber = ? ");
			pstmt4.setString(1, obj.getStatus());
			pstmt4.setLong(2, obj.getOrderNumber());
			pstmt4.executeUpdate();
			
			pstmt5 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET comments = ? WHERE orderNumber = ? ");
			pstmt5.setString(1, obj.getComments());
			pstmt5.setLong(2, obj.getOrderNumber());
			pstmt5.executeUpdate();
			
			pstmt6 = ((Connection) this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_UPDATABLE)).prepareStatement("UPDATE Customers SET customerNumber = ? WHERE orderNumber = ? ");
			pstmt6.setLong(1, obj.getCustomerNumber());
			pstmt6.setLong(2, obj.getOrderNumber());
			pstmt6.executeUpdate();
							
			
			
			/*
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET orderDate = '" + obj.getOrderDate()+ "'"
                					+ ", requiredDate = '"+obj.getRequiredDate()+"'"
                					+ ", shippedDate = '"+obj.getShippedDate()+"'"
                					+ ", getStatus = '"+obj.getStatus()+"'"
                        			+ ", comments = '"+obj.getComments()+"'"
                        			+ ", customerNumber = '"+obj.getCustomerNumber()
                					+" WHERE orderNumber = '" + obj.getOrderNumber()+"'"
                 );
                 */
		obj = this.find(obj.getOrderNumber());
		return true;
    } catch (SQLException e) {
    		LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
    }
}	

	public Orders find(int id) {
		Orders order = new Orders();

		try{
			
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Orders where orderNumber = " + id);
			if(result.first())
				order = new Orders(
						id,
						result.getString("orderDate"),
						result.getString("requiredDate"),
						result.getString("shippedDate"),
						result.getString("status"),
						result.getString("comments"),
						result.getInt("customerNumber")
						);

		} catch(SQLException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return order;
	}

	public Orders read(String id) {
		return null;
	}
}
