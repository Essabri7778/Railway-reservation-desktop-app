package application.controllers;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;



import application.outils.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField log_up;

    @FXML
    private PasswordField password_up;
    
    @FXML
    private Button login_btn;
    
    public int ID;
    

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private DbConnection database=new DbConnection();
    
    String statu="no";
    int id; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	login_btn.setOnAction(e->{
    		Login();
    	});
    }
    
 
    public void Login(){
    	if(log_up.getText().equals("") || password_up.getText().equals("") ) {
    		Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("Some fields are messing");
			alert.showAndWait();
    	}
    	else {
    		String sql = "Select * from costumers where email = ? and passworde = ?";
            try {
            	conn = database.getConnection();
                pst = conn.prepareStatement(sql);
                pst.setString(1,log_up.getText());
                pst.setString(2,password_up.getText());
                rs = pst.executeQuery();
                
                if (rs.next()){
                	if(rs.getString(7).equals("admin")) {
                		FXMLLoader loader =new FXMLLoader();
                		loader.setLocation(getClass().getResource("../view/DashboardTrain.fxml"));
                		TrainController controller =new TrainController() ;
                		loader.setController(controller);
                		loader.load();
                		Scene scene =new Scene(loader.getRoot());
                		Stage stage =(Stage)login_btn.getScene().getWindow();
                		stage.setScene(scene);
                		stage.show();
                	}
                	if(rs.getString(7).equals("client")){
                		
                		try {
                			ID = rs.getInt(1);
                			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ReservationForm.fxml"));
                			ReservationFormeController controller =new ReservationFormeController() ;
                    		loader.setController(controller);
                    		loader.load();
                    		ReservationFormeController reservationFormeController = loader.getController();
                			reservationFormeController.setLabelId(ID);
                			
                			Scene scene =new Scene(loader.getRoot());
                    		Stage stage =(Stage)login_btn.getScene().getWindow();
                    		stage.setScene(scene);
                    		stage.show();
                		}catch(Exception e) {
                			e.printStackTrace();
                		}
                			
                		
                		
                	
                			
                	}
                	
                	
                }else {
                	Alert alert=new Alert(AlertType.ERROR);
    			alert.setTitle(null);
    			alert.setHeaderText(null);
    			alert.setContentText("Email or password is not correct");
    			alert.showAndWait();
    			}

            }catch(Exception e){

            }
    	}
    }
}
