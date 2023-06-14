package application.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.outils.DbConnection;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TrajetModel {
	private IntegerProperty trajet_id;
    private StringProperty from_station;
    private StringProperty time_depart;
    private StringProperty to_station;
    private StringProperty time_arrive;
    private StringProperty train_name;
	private IntegerProperty firstC_price;
    private IntegerProperty secondC_price;
    private IntegerProperty percentage_child;
    private DoubleProperty prix;
	private DbConnection database=new DbConnection();
	private static int index=1;
	
	public TrajetModel (){}
	public TrajetModel( String time_depart,String time_arrive,
			 String train_name, double prix
			) {

		this.train_name = new SimpleStringProperty(train_name);
		this.prix = new SimpleDoubleProperty(prix);
		this.time_arrive=new SimpleStringProperty(time_depart);
		this.time_depart=new SimpleStringProperty(time_arrive);
	}
	public TrajetModel( int trajet_id, String from_station, String to_station,String time_depart,String time_arrive,
			 String train_name, int firstC_price,
			int secondC_price, int percentage_child) {
		super();
		this.trajet_id = new SimpleIntegerProperty(trajet_id);
		this.from_station = new SimpleStringProperty(from_station);
		this.to_station = new SimpleStringProperty(to_station);
		this.train_name = new SimpleStringProperty(train_name);
		this.firstC_price = new SimpleIntegerProperty(firstC_price);
		this.secondC_price = new SimpleIntegerProperty(secondC_price);
		this.percentage_child = new SimpleIntegerProperty(percentage_child);
		this.time_arrive=new SimpleStringProperty(time_depart);
		this.time_depart=new SimpleStringProperty(time_arrive);
	}
	
	
	public int creatTrajet( String from_station, String to_station,String time_depart,String time_arrive,
			String train_name, String firstC_price,
			String secondC_price, String percentage_child) {
		int affectedLine = 0;
        String sqlQuery = "insert into trajet(TRAJET_ID,STATION_DEPART,STATION_ARRIVE,HEURE_DEPART,HEURE_ARRIVE,TRAIN_NAME,PRIX_FIRST_CLASS,PRIX_SECOND_CLASS,POURCENTAGE_ENFANT) values(? ,? ,? ,? , ?, ?, ?, ?, ?)";
        String sqlQuery1 = "select max(trajet_id) from trajet";
        Connection con=null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        PreparedStatement stmt1 = null;
        try {
        	con = database.getConnection();
        	stmt1 = con.prepareStatement(sqlQuery1);
        	rs=stmt1.executeQuery();
        	rs.next();
        	index=rs.getInt(1);
        	stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,index+1);
            stmt.setString(2,from_station);
            stmt.setString(3,to_station);
            stmt.setString(4,time_depart);
            stmt.setString(5,time_arrive);
            stmt.setString(6,train_name);
            stmt.setInt(7,Integer.parseInt(firstC_price));
            stmt.setInt(8,Integer.parseInt(secondC_price));
            stmt.setInt(9,Integer.parseInt(percentage_child));
            affectedLine=stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
		return affectedLine;
	}
		
	
	public int deleteTrajet(int id) {
		int affectedLine=0;
        String sqlQuery = "delete from trajet where trajet_id= ?";
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
	public int updateTrajet(int trajet_id,String from_station, String to_station,String time_depart,String time_arrive,
			String train_name, String firstC_price,
			String secondC_price, String percentage_child
			) {
	int affectedLine=0;
        
        String sqlQuery = "UPDATE trajet SET STATION_DEPART=? ,STATION_ARRIVE=? ,HEURE_DEPART=?,HEURE_ARRIVE=? ,TRAIN_NAME=? ,PRIX_FIRST_CLASS=? ,PRIX_SECOND_CLASS=? ,POURCENTAGE_ENFANT=?  WHERE trajet_id=?";
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            
            stmt.setString(1,from_station);
            stmt.setString(2,to_station);
            stmt.setString(3,time_depart);
            stmt.setString(4,time_arrive);
            stmt.setString(5,train_name);
            stmt.setInt(6,Integer.parseInt(firstC_price));
            stmt.setInt(7,Integer.parseInt(secondC_price));
            stmt.setInt(8,Integer.parseInt(percentage_child));
            System.out.println(trajet_id);
            stmt.setInt(9,trajet_id);
            affectedLine=stmt.executeUpdate();
          
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return affectedLine;
		
	}
	public ResultSet getTrajet(int id) {
		String sqlQuery = "select * from trajet where trajet_id=?";
        ResultSet Trajet = null;
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,id);
            Trajet = stmt.executeQuery();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Trajet;
		
	}
	public ResultSet getAllTrajets() {
		String sqlQuery = "select * from trajet";
        ResultSet allTrajets = null;
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            allTrajets = stmt.executeQuery();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTrajets;
		
	}
	//finde tarjet client
	public ResultSet findeTrajetModel(String From_station,String To_station) {
		String sqlQuery = "select * from trajet where station_depart=? and station_arrive=?";
		ResultSet Trajets = null;
        Connection con;
		try {
			con = database.getConnection();
			PreparedStatement stmt = con.prepareStatement(sqlQuery);
			stmt.setString(1,From_station);
			stmt.setString(2,To_station);
			Trajets = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println( Trajets);
		return Trajets;

	}
	


//getters
public IntegerProperty Trajet_idProperty() {
	return trajet_id;
}

public StringProperty From_stationProperty() {
	return from_station;
}

public StringProperty To_stationProperty() {
	return to_station;
}

public StringProperty Train_nameProperty() {
	return train_name;
}

public IntegerProperty FirstC_priceProperty() {
	return firstC_price;
}

public IntegerProperty SecondC_priceProperty() {
	return secondC_price;
}

public IntegerProperty Percentage_childProperty() {
	return percentage_child;
}

public StringProperty Time_departProperty() {
	return time_depart;
}
public StringProperty Time_arriveProperty() {
	return time_arrive;
}

public DoubleProperty PrixProperty() {
	return prix;
}

//
public double getPrix() {
	return prix.get();
}

public int getTrajet_id() {
	return trajet_id.get();
}

public String getFrom_station() {
	return from_station.get();
}

public String getTo_station() {
	return to_station.get();
}

public String getTrain_name() {
	return train_name.get();
}

public int getFirstC_price() {
	return firstC_price.get();
}

public int getSecondC_price() {
	return secondC_price.get();
}

public int getPercentage_child() {
	return percentage_child.get();
}
public String getTime_depart() {
	return time_depart.get();
}
public String getTime_arrive() {
	return time_arrive.get();
}
}