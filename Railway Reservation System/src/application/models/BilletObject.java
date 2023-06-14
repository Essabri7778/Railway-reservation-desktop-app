package application.models;

public class BilletObject {
	
	public String time_depart;
	public String time_arrive;
	public String station_depart;
	public String station_arrive;
	public String train_name;
	public String date_journey;
	public String classe;
	public double prix;
	
	
	public BilletObject() {
		this.time_depart = null;
		this.time_arrive = null;
		this.station_depart = null;
		this.station_arrive = null;
		this.train_name = null;
		this.date_journey = null;
		this.classe = null;
		this.prix = 0.0;
	}

}
