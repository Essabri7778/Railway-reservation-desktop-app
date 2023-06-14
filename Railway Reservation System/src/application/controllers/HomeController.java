package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;


public class HomeController implements Initializable{
  

    @FXML
    private Button inscription_btn;

    @FXML
    private Button login_btn;

    @FXML
    private Hyperlink reservation_btn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		login_btn.setOnAction(e->{
			try {
				switchToLogin();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		reservation_btn.setOnAction(e->{
			try {
				switchToReservation();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});
		inscription_btn.setOnAction(e->{
			try {
				switchToSingUp();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		});
	}
	
	@FXML
	void switchToLogin() throws IOException {
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/LoginView.fxml"));
		LoginController controller =new LoginController() ;
		loader.setController(controller);
		loader.load();
		Scene scene =new Scene(loader.getRoot());
		Stage stage =(Stage)login_btn.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToSingUp() throws IOException {
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/InscriptionForm.fxml"));
		InscriptionController controller =new InscriptionController() ;
		loader.setController(controller);
		loader.load();
		Scene scene =new Scene(loader.getRoot());
		Stage stage =(Stage)login_btn.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToReservation() throws IOException {
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/ReservationForm.fxml"));
		ReservationClientController controller =new ReservationClientController();
		loader.setController(controller);
		loader.load();
		Scene scene =new Scene(loader.getRoot());
		Stage stage =(Stage)reservation_btn.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}

}
