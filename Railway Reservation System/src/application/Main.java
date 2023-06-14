package application;
	

import application.controllers.ReservationFormeController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.controllers.HomeController;
import application.controllers.ReservationDashboardController;
import application.models.CostumerModel;
import application.models.ReservationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
		
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader =new FXMLLoader();
			loader.setLocation(getClass().getResource("view/Home.fxml"));
			HomeController controller =new HomeController() ;
			loader.setController(controller);
			loader.load();
			Scene scene =new Scene(loader.getRoot());
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)  {
		/*SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		try {
			java.sql.Time timeValue = new java.sql.Time(formatter.parse("2:00").getTime());
			System.out.println(timeValue);
			Date currentDate=new Date();
			SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm");
			System.out.println(timeFormat.format(currentDate));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//new CostumerModel().CreateCostumer("mmmm","essabri","@","06","pkoi");
		//new ReservationModel().creatReservation("Mekens","Rabat","8:00","9:00","D2345","1","Atlas430",2,"12,13","12-09-2022",180.0);
		launch(args);

	}
}
