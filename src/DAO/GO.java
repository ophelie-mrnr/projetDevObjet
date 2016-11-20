package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.*;

public class GO {

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
			e.printStackTrace();
		}

	}

}
