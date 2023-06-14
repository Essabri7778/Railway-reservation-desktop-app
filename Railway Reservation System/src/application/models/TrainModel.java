package application.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.outils.DbConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TrainModel {
	
	private StringProperty train_id;
    private StringProperty train_name;
    private StringProperty train_statut;
	private StringProperty FC_nbr_seats;
    private StringProperty SC_nbr_seats;
    DbConnection database=new DbConnection();
    
    public TrainModel(String train_id,  String train_statut,String train_name, String fC_nbr_seats, String sC_nbr_seats) {
		super();
		this.train_id = new SimpleStringProperty(train_id);
		this.train_name = new SimpleStringProperty(train_name);
		this.train_statut = new SimpleStringProperty(train_statut);
		FC_nbr_seats = new SimpleStringProperty(fC_nbr_seats);
		SC_nbr_seats =new SimpleStringProperty( sC_nbr_seats);
		
	}
    public TrainModel( String train_name) {
    	this.train_name = new SimpleStringProperty(train_name);
    }
    
    public TrainModel() {
	}
    //ajouter un train :la valeur de retour pour afficher que l'operation s'est bien effectuer
    public int creatTrain(String id,String tName,String tStatus,String FC_nbr_seats,String SC_nbr_seats){
        
        int affectedLine = 0;
        String sqlQuery = "insert into train(train_id,train_statut,train_name,first_class_nbr_seats,second_class_nbr_seats) values(? ,? ,? ,? ,?)";
        Connection con=null;
        PreparedStatement stmt = null;
        try {
        	con = database.getConnection();
        	stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,Integer.parseInt(id));
            stmt.setString(2,tStatus);
            stmt.setString(3,tName);
            stmt.setInt(4,Integer.parseInt(FC_nbr_seats));
            stmt.setInt(5,Integer.parseInt(SC_nbr_seats));
            affectedLine=stmt.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
		return affectedLine;

    }
    
    //supprimer un train
    public int deleteTrain(String id){
    	int affectedLine=0;
        
        String sqlQuery = "delete from train where train_id= ?";
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,Integer.parseInt(id));
            affectedLine=stmt.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return affectedLine;

    }
    
    //mis a jour d'un train
    public int updateTrain(String id,String tName,String tStatus,String FC_nbr_seats,String SC_nbr_seats){
    	int affectedLine=0;
        
        String sqlQuery = "UPDATE train SET train_statut=? ,train_name=? , first_class_nbr_seats=? , second_class_nbr_seats=?  WHERE train_id=?";
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            
            stmt.setString(1,tStatus);
            stmt.setString(2,tName);
            stmt.setInt(3,Integer.parseInt(FC_nbr_seats));
            stmt.setInt(4,Integer.parseInt(SC_nbr_seats));
            stmt.setInt(5,Integer.parseInt(id));
            affectedLine=stmt.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return affectedLine;

    }
    public ResultSet getAllTrains() {
        
        String sqlQuery = "select * from train";
        ResultSet allTrains = null;
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            allTrains = stmt.executeQuery();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTrains;

    }
    
    public ResultSet getTrain(String id) {
    	
        String sqlQuery = "select * from train where train_id=?";
        ResultSet Train = null;
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,Integer.parseInt(id));
            Train = stmt.executeQuery();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Train;
    }
    
    public void searchTrain(){

    }
    
    
    
    
    //getters 
    public StringProperty Train_idProperty() {
    	return train_id;
    }
    public StringProperty Train_nameProperty() {
    	return train_name;
    }
    public StringProperty Train_statutProperty() {
    	return train_statut;
    }
    public StringProperty FC_nbr_seatsProperty() {
    	return FC_nbr_seats;
    }
    public StringProperty SC_nbr_seatsProperty() {
    	return SC_nbr_seats;
    }
	public String getTrain_id() {
		return train_id.get();
	}

	public String getTrain_name() {
		return train_name.get();
	}

	public String getTrain_statut() {
		return train_statut.get();
	}

	public String getFC_nbr_seats() {
		return FC_nbr_seats.get();
	}

	public String getSC_nbr_seats() {
		return SC_nbr_seats.get();
	}


}
