package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import POJO.CreationBDD;

public class MaConnexion {

	static Connection connect = null;
	public static final String url = "jdbc:mysql://localhost/schemadevobj" ;

	private MaConnexion(){
		try{
			connect = DriverManager.getConnection(url, CreationBDD.USER, CreationBDD.PASS);
		}catch (SQLException e){
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
