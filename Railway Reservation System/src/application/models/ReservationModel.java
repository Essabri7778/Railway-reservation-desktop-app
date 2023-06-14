package application.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import application.outils.DbConnection;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReservationModel {
	
	private IntegerProperty id_reservation;
	private StringProperty from_station;
	private StringProperty to_station;
	private StringProperty date_arrive;
	private StringProperty date_depart;
	private StringProperty cin_costumer;
	private StringProperty classTrain;
	private StringProperty train;
	private IntegerProperty nbr_passenger;
	private StringProperty num_seats;
	private StringProperty date_journey;
	private StringProperty date_reservation;
	private DoubleProperty prix; 
	private static int index=1;
	private DbConnection database=new DbConnection();
	
	public ReservationModel(){}
	
	
	public ReservationModel(int id_reservation, String from_station,String date_depart,String date_arrive, 
			String to_station,String cin_costumer,
			String classTrain, String train, int nbr_passenger, String num_seats,
			String date_journey, String date_reservation, double prix) {
		super();
		this.id_reservation = new SimpleIntegerProperty(id_reservation);
		this.from_station = new SimpleStringProperty(from_station);
		this.to_station = new SimpleStringProperty(to_station);
		this.date_arrive=new SimpleStringProperty(date_arrive);
		this.date_depart=new SimpleStringProperty(date_depart);
		this.cin_costumer = new SimpleStringProperty(cin_costumer);
		this.classTrain = new SimpleStringProperty(classTrain);
		this.train = new SimpleStringProperty(train);
		this.nbr_passenger = new SimpleIntegerProperty(nbr_passenger);
		this.num_seats = new SimpleStringProperty(num_seats);
		this.date_journey = new SimpleStringProperty(date_journey);
		this.date_reservation = new SimpleStringProperty(date_reservation);
		this.prix =new SimpleDoubleProperty(prix);
	}
	
	
	


	public int creatReservation(String from_station, String to_station,String date_arrive,String date_depart,String costumer_cin ,
			 String train,
		 double prix) 
	{
		int affectedLine = 0;
		String sqlQuery="insert into reservation(id_reservation,from_station,to_station,cin_costumer,train_name,date_reservation,prix,date_depart,date_arrive) values(?,?,?,?,?,?,?,?,?)";
		String sqlQuery1 = "select max(id_reservation) from reservation";
		Connection con=null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        PreparedStatement stmt1 = null;
        try {
        	SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        	Date date = new Date();
        	con = database.getConnection();
        	stmt1 = con.prepareStatement(sqlQuery1);
        	rs=stmt1.executeQuery();
        	rs.next();
        	index=rs.getInt(1);
        	stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,index+1);
            stmt.setString(2,from_station);
            stmt.setString(3,to_station);
            stmt.setString(4,costumer_cin );
            stmt.setString(5,train);
            stmt.setString(6,s.format(date));
            stmt.setDouble(7,prix);
            stmt.setString(9,date_arrive);
            stmt.setString(8,date_depart);
            affectedLine=stmt.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();}
        
		return affectedLine;
		
	}
	
	public int deleteReservation(int id) {
		int affectedLine=0;
        String sqlQuery = "delete from reservation where id_reservation= ?";
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


	private String toString(LocalDate now) {
		// TODO Auto-generated method stub
		return ""+now+"";
	}
	
	public ResultSet getReservation(int id) {
		String sqlQuery = "select * from reservation where id_reservation=?";
        ResultSet reservation = null;
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1,id);
            reservation = stmt.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
		
	}
	public ResultSet getAllReseravations() {
		String sqlQuery = "select * from reservation";
        ResultSet allReseravations = null;
        try {
        	Connection con = database.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlQuery);
            allReseravations = stmt.executeQuery();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allReseravations;
		
	}
	
	//getters
	public int getId_reservation() {
		return id_reservation.get();
	}
	public String getFrom_station() {
		return from_station.get();
	}
	public String getTo_station() {
		return to_station.get();
	}
	public String getCin_costumer() {
		return cin_costumer.get();
	}
	public String getClassTrain() {
		return classTrain.get();
	}
	public String getTrain() {
		return train.get();
	}
	public int getNbr_passenger() {
		return nbr_passenger.get();
	}
	public String getNum_seats() {
		return num_seats.get();
	}
	public String getDate_journey() {
		return date_journey.get();
	}
	public String getDate_reservation() {
		return date_reservation.get();
	}
	public double getPrix() {
		return prix.get();
	}
	public String getDate_arrive() {
		return date_arrive.get();
	}


	public String getDate_depart() {
		return date_depart.get();
	}
	
	//properties
	public IntegerProperty Id_reservationProperty() {
		return id_reservation;
	}
	public StringProperty From_stationProperty() {
		return from_station;
	}
	public StringProperty To_stationProperty() {
		return to_station;
	}

	public StringProperty Cin_costumerProperty() {
		return cin_costumer;
	}


	public StringProperty ClassTrainProperty() {
		return classTrain;
	}


	public StringProperty TrainProperty() {
		return train;
	}


	public IntegerProperty Nbr_passengerProperty() {
		return nbr_passenger;
	}


	public StringProperty Num_seatsProperty() {
		return num_seats;
	}


	public StringProperty Date_journeyProperty() {
		return date_journey;
	}


	public StringProperty Date_reservationProperty() {
		return date_reservation;
	}


	public DoubleProperty PrixProperty() {
		return prix;
	}
	public StringProperty Date_arriveProperty() {
		return date_arrive;
	}


	public StringProperty Date_departProperty() {
		return date_depart;
	}

}
