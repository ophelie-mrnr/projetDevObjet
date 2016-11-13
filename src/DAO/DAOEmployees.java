package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Employees;

public class DAOEmployees extends DAO<Employees> {

	public DAOEmployees(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public boolean create(Employees obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Employees "
   					+ "VALUES (obj.getEmployeeNumber(),"
   					+ "obj.getLastName(),"
   					+ "obj.getFirstName(),"
   					+ "obj.getEmail(),"
   					+ "obj.getOfficeCode(),"
   					+ "obj.getReportsTo(),"
   					+ "obj.getJobTitle()");
		}
		catch(Exception e){
			return false;
	}
		return true;
	}

	@Override
	public boolean delete(Employees obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE employeeNumber ="
							+ obj.getEmployeeNumber());
			return true;
		}
		catch (SQLException e) {
            e.printStackTrace();
            return false;
	}
	}

	@Override
	public boolean update(Employees obj) {
		try {
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET lastName = '" + obj.getLastName() + "'"
                					+ ", fisrtName = '"+obj.getFirstName()+"'"
                					+ ", email = '"+obj.getEmail()+"'"
                					+ ", officeCode = '"+ obj.getOfficeCode()+"'"
                					+ ", reportsTo = '"+ obj.getReportsTo()+"'"
                					+ ", JobTitle = '"+obj.getJobTitle()
                					+" WHERE employeeNumber = '" + obj.getEmployeeNumber()+"'"
                 );
		obj = this.find(obj.getEmployeeNumber());
		return true;
    } catch (SQLException e) {
            e.printStackTrace();
            return false;
    }
}	
	@Override
	public Employees find(int id) {
		Employees employees = new Employees();

		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Employees where employeeNumber = " + id);
			if(result.first())
				employees = new Employees(
						id,
						result.getString("lastName"),
						result.getString("firstName"),
						result.getString("email"),
						result.getString("officeCode"),
						result.getInt("reportsTo"),
						result.getString("jobTitle")
						);



		} catch(SQLException e){
			e.printStackTrace();
		}
		return employees;

	}

	@Override
	public Employees read(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}




