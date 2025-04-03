package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    
     public Connection getConnection()throws Exception {
    	 
        String url = "";
        
        if(instance == null || instance.trim().isEmpty())
              url = "jdbc:sqlserver://AnhTung-LapTop\\SQLSERVER:1433;databaseName=RING;encrypt=true;trustServerCertificate=true";
      
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }   
     

    private final String instance="";//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "sa";
    private final String password = "1";
}
