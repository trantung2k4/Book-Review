package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import dal.DBContext;

public class Test {
	
	public static void main(String[] args) {
		
		DBContext dbContext = new DBContext();
        try {
            Connection conn = (Connection) dbContext.getConnection();
            if (conn != null) {
                System.out.println("Connection successful!");
                conn.close(); // Always close the connection after testing
            } else {
                System.out.println("Connection failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
