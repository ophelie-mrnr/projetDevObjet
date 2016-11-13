package DAO;
import java.sql.Connection;

public class DAOFactory {

	    protected static final Connection conn = MaConnexion.getInstance();

	    public static DAO getCustomersDAO(){
	    	return new DAOCustomers(conn);
	    }

	    public static DAO getEmployeesDAO(){
	    	return new DAOEmployees(conn);
	    }

	    public static DAO getOfficesDAO(){
	    	return new DAOOffices(conn);
	    }

	    public static DAO getOrderdetailsDAO(){
	    	return new DAOOrderdetails(conn);
	    }

	    public static DAO getOrdersDAO(){
	    	return new DAOOrders(conn);
	    }

	    public static DAO getPaymentsDAO(){
	    	return new DAOPayments(conn);
	    }

	    public static DAO getProductlinesDAO(){
	    	return new DAOProductlines(conn);
	    }

	    public static DAO getProductsDAO(){
	    	return new DAOProducts(conn);
	    }
}
