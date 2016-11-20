package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.*;

public class GO {

	public GO(){
		// TODO Auto-generated method stub
		
		new CreationBDD();
	    DAOCustomers dao_customer = new DAOCustomers(MaConnexion.getInstance());
	    
	    /* TESTS
		int iddumec;
		iddumec = dao_customer.find(167).getCustomerNumber();
		System.out.println("L'id est :"+ iddumec);
		
		DAOProducts dao_products = new DAOProducts(MaConnexion.getInstance());
		String productName = dao_products.read("S10_1949").getProductName();
		System.out.println("product name : "+productName);
		String BuyPrice = ""+dao_products.read("S10_1949").getBuyPrice();
		System.out.println("prix : "+BuyPrice);
		*/
		
		java.sql.Statement state;
		try {
			
			state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT productCode FROM Products";
	        ResultSet res =state.executeQuery(query);	
	        ArrayList<String> liste = new ArrayList<String>(); 
	        
	        
	        while (res.next()){
	        	String resultat = res.getString("productCode");
	        	liste.add(resultat);
	        }	
	        
	        for(int i = 0; i < liste.size(); i++){
	       //     System.out.println("�l�ment � l'index " + i + " = " + liste.get(i));
	        }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
