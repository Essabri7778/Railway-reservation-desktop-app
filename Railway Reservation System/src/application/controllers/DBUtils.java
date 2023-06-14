package application.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBUtils {
	
	
public static void changeScene(ActionEvent event, String fxmlFile, String stationDepart,String stationArrive,String classe, int adult,int child,int id) {
		
		Parent root = null;
		
		
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
				root = loader.load();
				ReservationClientController reseClientController = loader.getController();
				reseClientController.displaybillets(stationDepart, stationArrive, classe, adult, child, id);
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		//stage.setScene(new Scene(root));
		stage.setScene(scene);
		stage.show();
	}


public static void changeScene2(ActionEvent event, String fxmlFile, int id) {
	
	Parent root = null;
	
	
		try {
			FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
			root = loader.load();
			ReservationFormeController reservationFormeController = loader.getController();
			reservationFormeController.setLabelId(id);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	Scene scene = new Scene(root);
	//stage.setScene(new Scene(root));
	stage.setScene(scene);
	stage.show();
}


}
