package application.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import application.outils.DbConnection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StationModel {
	
	private IntegerProperty station_id;
    private StringProperty station_name;
    private static int index=1;
	private DbConnection database=new DbConnection();
    
    
    
	public StationModel(String station_name) {
		super();
		this.station_name =new SimpleStringProperty(station_name);
	}
	
	public StationModel() {
		
	}
	public int creatStation(String SName) {
		
		int affectedLine = 0;
        String sqlQuery = "insert into station(station_id,station_name) values(? ,?)";
        String sqlQuery1 = "select max(station_id) from station";
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        try {
        	con = database.getConnection();
        	stmt1 = con.prepareStatement(sqlQuery1);
        	rs=stmt1.executeQuery();
        	rs.next();
        	index=rs.getInt(1);
        	stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,index+1);
            stmt.setString(2,SName);
            affectedLine=stmt.executeUpdate();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
		return affectedLine;
		
		
	}
	
    public int deleteStation(int id){
    	int affectedLine=0;
        
        String sqlQuery = "delete from station where station_id= ?";
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,id);
            affectedLine=stmt.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return affectedLine;

    }
    
    //update
    
    //get all stations
 public ResultSet getAllStations() {
        
        String sqlQuery = "select * from station";
        ResultSet allStations = null;
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            allStations = stmt.executeQuery();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStations;

    }
 //one station
 
 public ResultSet getStation(int id) {
 	
     String sqlQuery = "select * from station where station_id=?";
     ResultSet station = null;
     try {
     	Connection con = database.getConnection();
         PreparedStatement stmt = con.prepareStatement(sqlQuery);
         stmt.setInt(1,id);
         station = stmt.executeQuery();
         

     } catch (Exception e) {
         e.printStackTrace();
     }
     return station;
 }

 
 //getters
   public IntegerProperty station_idProperty() {
	   return station_id;}
 
   public StringProperty station_nameProperty() {
	   return station_name;}
 
	public int getStation_id() {
		return station_id.get();
	}

	public String getStation_name() {
		return station_name.get();
	}
}
