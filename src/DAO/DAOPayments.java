package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.Payments;

public class DAOPayments extends DAO<Payments> {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	public DAOPayments(Connection conn) {
		super(conn);
	}


	public boolean create(Payments obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Payments "
						+ "VALUES (obj.getCustomerNumber(),"
						+ "obj.getCheckNumber(),"
						+ "obj.getPaymentDate(),"
						+ "obj.getAmount()");
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			return false;
	}
		return true;
	}

	public boolean delete(Payments obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE customerNumber ="
							+ obj.getCustomerNumber());
			return true;
		}
		catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
		}
	}

	public boolean update(Payments obj) {
		try {
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET checkNumber = '" + obj.getCheckNumber()+ "'"
                					+ ", paymentDate = '"+obj.getPaymentDate()+"'"
                					+ ", amount = '"+obj.getAmount()+"'"
                					+" WHERE customerNumber = '"+obj.getCustomerNumber()+"'"
                 );
		obj = this.find(obj.getCustomerNumber());
		return true;
    } catch (SQLException e) {
    		LOGGER.log(Level.SEVERE, "Exception occur", e);
            e.printStackTrace();
            return false;
    }
}	

	public Payments find(int id) {
		Payments payment = new Payments();

		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Payments where customerNumber = " + id);
			if(result.first())
				payment = new Payments(
						id,
						result.getString("checkNumber"),
						result.getString("paymentDate"),
						result.getDouble("amount")
						);

		} catch(SQLException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return payment;

	}

	public Payments read(String id) {
		return null;
	}
}
