package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	
	public Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:sqlserver://Miisu:1433;databaseName=GameDACS1;integratedSecurity=true", "sa",
				"Hiepbgbg1");
		return con;
	}
	
	public int getScoreBird(String username) {
		
		
		try {
			Connection conn = getConnect();
			String sql = "select scoreBird from account where username= ?";
			PreparedStatement sttm = conn.prepareStatement(sql);
			sttm.setString(1, username);
			
			ResultSet rs = sttm.executeQuery();
			
			rs.next();
			
			return rs.getInt("scoreBird");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static void main(String[] args) {
		DBConnect con = new DBConnect();
		System.out.println(con.getScoreBird("admin"));
	}
}
