package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Offices;

public class DAOOffices extends DAO<Offices> {

	public DAOOffices(Connection conn) {
		super(conn);
	}

	public boolean create(Offices obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Offices "
						+ "VALUES (obj.getOfficeCode(),"
						+ "obj.getCity(),"
						+ "obj.getPhone(),"
						+ "obj.getAddressLine(),"
						+ "obj.getState(),"
						+ "obj.getCountry(),"
						+ "obj.getPostalCode()");
		}
		catch(Exception e){
			return false;
	}
		return true;
	}

	public boolean delete(Offices obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE officeCode ="
							+ obj.getOfficeCode());
			return true;
		}
		catch (SQLException e) {
            e.printStackTrace();
            return false;
		}
	}

	public boolean update(Offices obj) {
		try {
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
			e.printStackTrace();
		}
		return offices;
	}
}
