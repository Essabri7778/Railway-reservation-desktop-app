package application.outils;

import java.sql.*;


public class DbConnection {
    public Connection databaseLink;

    public Connection getConnection() throws SQLException {
        
        if(databaseLink == null || databaseLink.isClosed()) {
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser="system";
        String dbPassword="20010803";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            databaseLink= DriverManager.getConnection(url,dbUser,dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
		return databaseLink;
    }

    public void close(Connection connect,PreparedStatement pstmt, ResultSet rs) {
    	try {
    		if(connect !=null)
    			connect.close();
    		if(pstmt !=null)
    			pstmt.close();
    		if(rs !=null)
    			rs.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    public void close(Connection connect,PreparedStatement pstmt) {
    	try {
    		close(connect,pstmt,null);
    	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void close(PreparedStatement pstmt) {
    	try {
    		close(null,pstmt,null);
    	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
}
