package connectDB;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectDB {
	public static Connection con =  null ;
	public static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance(){
		return instance;
	}
	
	public void connect() throws SQLException{
		
		String url ="jdbc:sqlserver://localhost:1433;databasename=QLLK";
		String user ="sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	
	public void disconnect() {
		if(con != null) 
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static Connection getConnection() {
		return con;
	}
}
