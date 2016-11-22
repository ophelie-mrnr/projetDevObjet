package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.*;

public class GO {
	
	private static final Logger LOGGER = Logger.getLogger("myLogger");

	public GO(){
		
		new CreationBDD();		
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
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}

	}

}
