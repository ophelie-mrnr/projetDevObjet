package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import POJO.CreationBDD;

public class MaConnexion {
	
	private static final Logger LOGGER = Logger.getLogger("myLogger");

	static Connection connect = null;
	public static final String url = "jdbc:mysql://localhost/schemadevobj" ;

	private MaConnexion(){
		try{
			connect = DriverManager.getConnection(url, CreationBDD.USER, CreationBDD.PASS);
		}catch (SQLException e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
	}
	public static Connection getInstance(){
		if(connect == null){
			new MaConnexion();
		}
		return connect;
	}
}
