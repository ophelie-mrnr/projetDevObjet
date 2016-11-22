package DAO;
import java.sql.Connection;
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
		try {
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
