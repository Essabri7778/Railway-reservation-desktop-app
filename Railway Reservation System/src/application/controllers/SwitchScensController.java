package application.controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SwitchScensController  {
	
	
	public void switchScene(String value,Button switchButton) {
		
		if(value.equals("train")) {
			FXMLLoader loader =new FXMLLoader();
    		loader.setLocation(getClass().getResource("../view/DashboardTrain.fxml"));
    		TrainController controller =new TrainController() ;
    		loader.setController(controller);
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Scene scene =new Scene(loader.getRoot());
    		Stage stage =(Stage)switchButton.getScene().getWindow();
    		stage.setScene(scene);
    		stage.show();
		}
		
		if(value.equals("trajet")) {
			FXMLLoader loader =new FXMLLoader();
    		loader.setLocation(getClass().getResource("../view/DashboardTrajet.fxml"));
    		TrajetController controller =new TrajetController() ;
    		loader.setController(controller);
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Scene scene =new Scene(loader.getRoot());
    		Stage stage =(Stage)switchButton.getScene().getWindow();
    		stage.setScene(scene);
    		stage.show();
		}
		
		if(value.equals("reservation")) {
			FXMLLoader loader =new FXMLLoader();
    		loader.setLocation(getClass().getResource("../view/DashboardReservation.fxml"));
    		ReservationDashboardController controller =new ReservationDashboardController() ;
    		loader.setController(controller);
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Scene scene =new Scene(loader.getRoot());
    		Stage stage =(Stage)switchButton.getScene().getWindow();
    		stage.setScene(scene);
    		stage.show();
		}
		
		if(value.equals("client")) {
			FXMLLoader loader =new FXMLLoader();
    		loader.setLocation(getClass().getResource("../view/dashClient.fxml"));
    		ClientDashboardController controller =new ClientDashboardController() ;
    		loader.setController(controller);
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Scene scene =new Scene(loader.getRoot());
    		Stage stage =(Stage)switchButton.getScene().getWindow();
    		stage.setScene(scene);
    		stage.show();
		}
		
		if(value.equals("deconnexion")) {
			FXMLLoader loader =new FXMLLoader();
    		loader.setLocation(getClass().getResource("../view/Home.fxml"));
    		HomeController controller =new HomeController () ;
    		loader.setController(controller);
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Scene scene =new Scene(loader.getRoot());
    		Stage stage =(Stage)switchButton.getScene().getWindow();
    		stage.setScene(scene);
    		stage.show();
		}
		
		
	}



}
