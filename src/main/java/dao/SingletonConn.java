package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConn {
	private static Connection conn;
	
	/**
	 * le block static est le premeier qui va etre executer lorsque la classe est chargée en mémoire
	 * pour creer un objet une seul fois le meilleur moyen est de le crer en un block static
	 */
	
	static {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_catal","root","");
		
		
		} catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to connect to the database", e);
	    }
	
		}
	public static Connection getConn() {
		return conn;
		}
	
	
	}
	
	


