package application.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

import javax.swing.*;

import application.outils.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {
    @FXML
    private TextField cin_up;

    @FXML
    private Button cree_up;

    @FXML
    private TextField email_up;

    @FXML
    private TextField nom_up;
    @FXML
    private Hyperlink login_btn;

    @FXML
    private PasswordField pass_up;
    @FXML
    private TextField tele_up;
    
    private DbConnection database=new DbConnection();
    private static int index=1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public void add_users() {
    	if(cin_up.getText().equals("") || email_up.getText().equals("") || nom_up.getText().equals("") || pass_up.getText().equals("") || tele_up.getText().equals("")) {
    		Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("some fields are missing");
			alert.showAndWait();
    	}
    	else {
        String sql = "insert into costumers (id_costumer,cin,name_costumer,email,tele,passworde) values (?,?,?,?,?,?)";
        String sqlQuery1 = "select max(id_costumer) from costumers";
        PreparedStatement stmt1 = null;
        try{
        	conn = database.getConnection();
        	stmt1 = conn.prepareStatement(sqlQuery1);
        	rs=stmt1.executeQuery();
        	rs.next();
        	index=rs.getInt(1);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, index+1);
            pst.setString(2,cin_up.getText());
            pst.setString(3,nom_up.getText());
            pst.setString(4,email_up.getText());
            pst.setString(6,pass_up.getText());
            pst.setString(5,tele_up.getText());
            pst.execute(); 
            	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setTitle(null);
    			alert.setHeaderText(null);
    			alert.setContentText("operation effectué avec succee");
    			alert.showAndWait();
    			
    			
            
            
        } catch(Exception e) {
        	e.printStackTrace();
        	Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("operation echouée");
			alert.showAndWait();

        }}
    }
    private void switchLogin() {
    	FXMLLoader loader =new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/LoginView.fxml"));
		LoginController controller =new LoginController() ;
		loader.setController(controller);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene =new Scene(loader.getRoot());
		Stage stage =(Stage)login_btn.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	cree_up.setOnAction(e->{
    		add_users();
    	});
    	
    	login_btn.setOnAction(e->{
    		switchLogin();
    	});
    }
}
