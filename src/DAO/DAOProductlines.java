package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Productlines;

public class DAOProductlines extends DAO<Productlines> {

	public DAOProductlines(Connection conn) {
		super(conn);
	}
		
	public boolean create(Productlines obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_UPDATABLE).executeUpdate("INSERT INTO Productlines "
						+ "VALUES (obj.getProductLine(),"
						+ "obj.getTextDescription()");
		}
		catch(Exception e){
			return false;
	}
		return true;
	}

	public boolean delete(Productlines obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM customers WHERE productLine ="
							+ obj.getProductLine());
			return true;
		}
		catch (SQLException e) {
            e.printStackTrace();
            return false;
		}
	}

	public boolean update(Productlines obj) {
		try {
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Customers SET textDescription = '" + obj.getTextDescription()+ "'"
                					+" WHERE productLine = '"+obj.getProductLine()+"'"
                	
                 );
		obj = this.read(obj.getProductLine());
		return true;
    } catch (SQLException e) {
            e.printStackTrace();
            return false;
    }
}	

	public Productlines find(int id) {
		return null;
	}

	public Productlines read(String id) {
		Productlines productline = new Productlines();

		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Productlines where productLine = " + id);
			if(result.first())
				productline = new Productlines(
						id,
						result.getString("textDescription")
						);

		} catch(SQLException e){
			e.printStackTrace();
		}
		return productline;
	}
}
