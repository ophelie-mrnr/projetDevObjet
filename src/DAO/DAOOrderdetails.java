package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.Orderdetails;

public class DAOOrderdetails extends DAO<Orderdetails> {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	public DAOOrderdetails(Connection conn) {
		super(conn);
	}

	public boolean create(Orderdetails obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Orderdetails "
						+ "VALUES (obj.getOrderNumber(),"
						+ "obj.getProductCode(),"
						+ "obj.getQuantityOrdered(),"
						+ "obj.getPriceEach(),"
						+ "obj.getOrderLineNumber()");
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			return false;
	}
		return true;
	}

	public boolean delete(Orderdetails obj) {
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

	public boolean update(Orderdetails obj) {
		try {
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET productCode = '" + obj.getProductCode()+ "'"
                					+ ", quantityOrdered = '"+obj.getQuantityOrdered()+"'"
                					+ ", priceEach = '"+obj.getPriceEach()+"'"
                					+ ", orderLineNumber = '"+obj.getOrderLineNumber()
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

	public Orderdetails find(int id) {
		Orderdetails orderdetails = new Orderdetails();

		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Orderdetails where orderNumber = " + id);
			if(result.first())
				orderdetails = new Orderdetails(
						id,
						result.getString("productCode"),
						result.getInt("quantityOrdered"),
						result.getDouble("priceEach"),
						result.getInt("orderLineNumber")
						);

		} catch(SQLException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return orderdetails;

	}

	public Orderdetails read(String id) {
		return null;
	}
}
