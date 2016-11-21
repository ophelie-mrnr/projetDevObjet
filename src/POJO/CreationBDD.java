package POJO;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreationBDD {
	
	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	public static final String DB_URL = "jdbc:mysql://localhost/" ;

	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver" ;
	public static final String USER = "root" ;
	public static final String PASS = "" ;
	
	public static Statement stmt1 = null;

	public CreationBDD(){

		Connection conn = null ;
		Statement stmt = null ;

		try {
			//STEP 1: Register JDBC driver
			Class. forName( "com.mysql.jdbc.Driver" );
			//STEP 2: Open a connection
			System. out.println("Connexion à la base de données en cours");
			System. out.println(" ");
			conn = DriverManager. getConnection( DB_URL, USER, PASS);
			System.out.println("Le connexion est etablie ");
			//STEP 3: Execute a query
			System. out.println("Creation de la base de données en cours");
			System. out.println(" ");
			stmt = conn.createStatement();

			//suppression de la base de donnï¿½e
			String q = "DROP DATABASE IF EXISTS schemadevobj";
			stmt.executeUpdate(q);
			System. out.println("La base de donnees est bien supprimee !");


			String sq = "CREATE DATABASE schemadevobj" ;
			stmt.executeUpdate(sq);
			System. out.println( "La base de donnees est bien cree !" );

			String DB_URL1 = "jdbc:mysql://localhost/schemadevobj" ;

			System.out.println("Creation des tables");
			Connection conn1 = DriverManager. getConnection( DB_URL1, USER, PASS);
			stmt1 = conn1.createStatement();

			String sqlDeactivateCheck = "/*!40014 SET@OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS,FOREIGN_KEY_CHECKS=0 */;" ;

		    String sql1 = "CREATE TABLE customers ("+
		            "customerNumber int(11) NOT NULL PRIMARY KEY,"+
		            "customerName varchar(50) NOT NULL,"+
		            "contactLastName varchar(50) NOT NULL,"+
		            "contactFirstName varchar(50) NOT NULL,"+
		            "phone varchar(50) NOT NULL,"+
		            "city varchar(50) NOT NULL,"+
		            "state varchar(50) DEFAULT NULL,"+
		            "postalCode varchar(15) DEFAULT NULL,"+
		            "country varchar(50) NOT NULL,"+
		            "salesRepEmployeeNumber int(11) DEFAULT NULL,"+
		            "KEY salesRepEmployeeNumber (salesRepEmployeeNumber),"+
		            "CONSTRAINT customers_fk_emplnumb FOREIGN KEY (salesRepEmployeeNumber)"+
		            "REFERENCES employees (employeeNumber)"+
		             ") ENGINE=InnoDB DEFAULT CHARSET=utf8";


		    String sql2 = "CREATE TABLE `offices` ("+
		            "`officeCode` varchar(10) NOT NULL PRIMARY KEY,"+
		            "`city` varchar(50) NOT NULL,"+
		            "`phone` varchar(50) NOT NULL,"+
		            "`addressLine` varchar(50) NOT NULL,"+
		            "`state` varchar(50),"+
		            "`country` varchar(50) NOT NULL,"+
		            "`postalCode` varchar(15) DEFAULT NULL"+
		            ")ENGINE=InnoDB DEFAULT CHARSET=utf8; ";


		    String sql3 = "CREATE TABLE `employees` ("+
		            "`employeeNumber` INT(11) NOT NULL,"+
		            "`lastName` varchar(50) NOT NULL,"+
		            "`firstName` varchar(50) NOT NULL,"+
		            "`email` varchar(100) NOT NULL,"+
		            "`officeCode` varchar(10) NOT NULL,"+
		            "`reportsTo` int(11),"+
		            "`jobTitle` varchar(50) DEFAULT NULL,"+
		            "PRIMARY KEY (`employeeNumber`),"+
		            "KEY `officeCode` (`officeCode`),"+
		            "CONSTRAINT `employees_fk_officeCode` FOREIGN KEY (`officeCode`)REFERENCES `offices` (`officeCode`)"+
		            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";


		    String sql4 = "CREATE TABLE `payments` ("+
		            "`customerNumber` INT(11) NOT NULL,"+
		            "`checkNumber` varchar(50) NOT NULL,"+
		            "`paymentDate` DATE NOT NULL,"+
		            "`amount` double NOT NULL,"+
		            "PRIMARY KEY (`checkNumber`,`customerNumber`),"+
		            "CONSTRAINT `payments_fk_customerNumber` FOREIGN KEY (`customerNumber`)REFERENCES `customers` (`customerNumber`)"+
		            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";


		    String sql5 = "CREATE TABLE `orders`("+
		    		"`orderNumber` INT(11) NOT NULL,"+
		            "`orderDate` DATE NOT NULL,"+
		            "`requiredDate` DATE NOT NULL,"+
		            "`shippedDate` DATE,"+
		            "`status` VARCHAR(15) NOT NULL,"+
		            "`comments` TEXT  NULL,"+
		            "`customerNumber` INT(11) NOT NULL,"+
		            "PRIMARY KEY (`orderNumber`),"+
		            "KEY `customerNumber` (`customerNumber`),"+
		            "CONSTRAINT `orders_fk_customerNumber` FOREIGN KEY (`customerNumber`)REFERENCES `customers` (`customerNumber`)"+
		            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		    
		    String sql6 = "CREATE TABLE `orderdetails`("+
		    		"`orderNumber` INT(11) NOT NULL,"+
		            "`productCode` VARCHAR(15) NOT NULL,"+
		            "`quantityOrdered` INT(11) NOT NULL,"+
		            "`priceEach` DOUBLE NOT NULL,"+
		            "`orderLineNumber` SMALLINT(15) NOT NULL,"+
		            "PRIMARY KEY (`orderNumber`, `productCode`),"+
		            "CONSTRAINT `orderdetails_fk_product` FOREIGN KEY (`productcode`)REFERENCES `products` (`productCode`)"+
		            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		    
     	    String sql7 = "CREATE TABLE `products`("+
		    		"`productCode` VARCHAR(15) NOT NULL,"+
		            "`productName` VARCHAR(70) NOT NULL,"+
		            "`productLine` VARCHAR(50) NOT NULL,"+
		            "`photo` BLOB,"+
		            "`productVendor` VARCHAR(50) NOT NULL,"+
		            "`productDescription` TEXT  NULL,"+
		            "`quantityInStock` SMALLINT(50) NOT NULL,"+
		            "`buyPrice` DOUBLE NOT NULL,"+
		            "`MSRP` DOUBLE NOT NULL,"+
		            "PRIMARY KEY (`productCode`),"+
		            "KEY `productLine` (`productLine`),"+
		            "CONSTRAINT `products_fk_productlines` FOREIGN KEY (`productLine`)REFERENCES `productlines` (`productLine`)"+
		            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

     	    
		    String sql8 = "CREATE TABLE `productlines`("+
		    		"`productLine` VARCHAR(50) NOT NULL,"+
		            "`textDescription` VARCHAR(4000) NOT NULL,"+
		            "PRIMARY KEY (`productLine`)"+
		            ")ENGINE=InnoDB DEFAULT CHARSET=utf8; ";


		    String sqlReactivateCheck= "/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;" ;

		    stmt1.executeUpdate(sqlDeactivateCheck);
		    stmt1.executeUpdate(sql2);
		    stmt1.executeUpdate(sql3);
		    stmt1.executeUpdate(sql1);
		    stmt1.executeUpdate(sql4);
		    stmt1.executeUpdate(sql5);
		    stmt1.executeUpdate(sql8);
		    stmt1.executeUpdate(sql7);
		    stmt1.executeUpdate(sql6);
		    stmt1.executeUpdate(sqlReactivateCheck);


		    String pathFile = "SampleData2016.sql" ;
		    InputStream ips;
		    ips = new FileInputStream(pathFile);
		    InputStreamReader ipsr= new InputStreamReader(ips);
		    BufferedReader br= new BufferedReader(ipsr);
		    String line;
		    while ((line=br.readLine())!= null ){
		    if (line.length() !=0){
		    if (line.substring(0,2).equals("/*") == false){
		    	System. out.println(line);
		    	stmt1.executeUpdate(line);
		    	}
		    }
		    }
		    br.close();

		} catch (SQLException se){
			//Handle errors for JDBC
			LOGGER.log(Level.SEVERE, "Exception occur", se);
			se.printStackTrace();
		} catch (Exception e){
			//Handle errors for Class.forName
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (stmt!= null )
					stmt.close();
			} catch (SQLException se2){ 
				LOGGER.log(Level.SEVERE, "Exception occur", se2);

			} // nothing we can do
			try {
				if (conn!= null )
					conn.close();
			} catch (SQLException se){
				LOGGER.log(Level.SEVERE, "Exception occur", se);
				se.printStackTrace();
			} //end finally try
		} //end try
		System. out.println( "Goodbye!" );
	}

}