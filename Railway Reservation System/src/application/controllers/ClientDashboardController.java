package application.controllers;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import application.models.CostumerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
public class ClientDashboardController implements Initializable{
	@FXML
    private Button DashboardDeconnexion;

    @FXML
    private Button DashboardClient;

    @FXML
    private Button DashboardReservation;

    @FXML
    private Button DashboardTrain;

    @FXML
    private Button DashboardTrajet;


		    @FXML
		    private ResourceBundle resources;

		    @FXML
		    private URL location;
	        
		    // buttons
		    @FXML
		    private Button AddBtn;

		    @FXML
		    private Button ClearBtn;  
		    
		    @FXML
		    private Button DeleteBtn;
		    
		    @FXML
		    private Button UpdateBtn;
	        
		    // TextField
		    @FXML
		    private TextField CustumerTele;

		    @FXML
		    private TextField CustumerCIN;

		    @FXML
		    private TextField CustumerEmail;

		    @FXML
		    private TextField CustumerName;
		    
		    @FXML
		    private TextField SearchField;

		    @FXML
		    private PasswordField CustumerPassword;

		    // table and columns
		    
		    @FXML
		    private TableView<CostumerModel> CustumersTable=new TableView<CostumerModel>() ;

		    @FXML
		    private TableColumn<CostumerModel,String> ColumnTele=new TableColumn<CostumerModel,String>();

		    @FXML
		    private TableColumn<CostumerModel,String> ColumnCIN=new TableColumn<CostumerModel,String>();

		    @FXML
		    private TableColumn<CostumerModel,String> ColumnEmail=new TableColumn<CostumerModel,String>();

		    @FXML
		    private TableColumn<CostumerModel,String> ColumnName=new TableColumn<CostumerModel,String>();

		    @FXML
		    private TableColumn<CostumerModel,String> ColumnPass=new TableColumn<CostumerModel,String>();
		    
		    // private FXMLLoader loader;
			private CostumerModel Custumer = new CostumerModel();

	    
	    
	    //********************* REMPLISSAGE DU FORMUMAIRE D'APRES LE CHAMPS SELECTIONNE ********************************
	  	 
	    @FXML
	  	
	    void rowClicked(MouseEvent event) {
	  		CustumerCIN.setDisable(true);
	  		CostumerModel clickedCustumer = CustumersTable.getSelectionModel().getSelectedItem();
	  		CustumerCIN.setText(String.valueOf(clickedCustumer.getCIN()));
	  		CustumerName.setText(String.valueOf(clickedCustumer.getName()));
	  		CustumerEmail.setText(String.valueOf(clickedCustumer.getEmail()));
	  		CustumerTele.setText(String.valueOf(clickedCustumer.getTele()));
	  		CustumerPassword.setText(String.valueOf(clickedCustumer.getPassword()));
	      }
	    
	  	//*********************** INITIALISATION DES COLONNES DU TABLE DES CUSTUMERS ***********************************
	  	private void initCustumerTable() {
	  		ColumnCIN.setCellValueFactory(new PropertyValueFactory<CostumerModel,String>("CIN"));
	  		ColumnName.setCellValueFactory(new PropertyValueFactory<CostumerModel,String>("name"));
	  		ColumnEmail.setCellValueFactory(new PropertyValueFactory<CostumerModel,String>("email"));
	  		ColumnTele.setCellValueFactory(new PropertyValueFactory<CostumerModel,String>("tele"));
	  		ColumnPass.setCellValueFactory(new PropertyValueFactory<CostumerModel,String>("password"));	
	  	}
	  	

	  	//*********************************** AFFICHAGE DES DONNEES EXISTANTS *******************************
	  	private void displayCustumers() {
	  		
	  		ObservableList<CostumerModel> list=FXCollections.observableArrayList();
	  		ResultSet rs= Custumer.AllCostumers();
	  		System.out.println(rs);
	  		try {
	  			while(rs.next()) {
	  				list.add(new CostumerModel(rs.getString("CIN"),rs.getString("name"),rs.getString("email"),rs.getString("tele"),rs.getString("password")));
	  			}
	  			
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		}
	  		initCustumerTable();
	  		CustumersTable.setItems(list);
	  	}
	  	
	     //****************************************** ADD CUSTUMERS ************************************************
	 
	  	
	  	public void addCustumer() throws SQLException {
	  		if(CustumerName.getText()=="" ||  CustumerCIN.getText()=="" || CustumerTele.getText()=="" || CustumerEmail.getText()=="" || CustumerPassword.getText()=="") {
	  			//class alert
	  			Alert alert=new Alert(AlertType.ERROR);
	  			alert.setTitle(null);
	  			alert.setHeaderText(null);
	  			alert.setContentText("Some fields are missing");
	  			alert.showAndWait();
	  		}
	  		else {
	  			if((Custumer.getCustumer(CustumerCIN.getText())).next()) {
	  				//class alert
	  				Alert alert=new Alert(AlertType.ERROR);
	  				alert.setTitle(null);
	  				alert.setHeaderText(null);
	  				alert.setContentText("CIN deja utilise !");
	  				alert.showAndWait();
	  			}
	  			else {
	  				Custumer.CreateCostumer(CustumerCIN.getText(), CustumerName.getText(), CustumerEmail.getText(), CustumerTele.getText(), CustumerPassword.getText());
	  				CustumerCIN.setText("");
	  				CustumerName.setText("");
	  				CustumerEmail.setText("");
	  				CustumerTele.setText("");
	  				CustumerPassword.setText("");
	  				
	  				
	  			}
	  		}
	  	}

		//*************************************** UPDATE CUSTUMERS ***********************************************
	  	public void UpdateCustumer() throws SQLException {
	  		if(CustumerName.getText()=="" ||  CustumerCIN.getText()=="" || CustumerTele.getText()=="" || CustumerEmail.getText()=="" || CustumerPassword.getText()=="") {
	  			//class alert
	  			Alert alert=new Alert(AlertType.ERROR);
	  			alert.setTitle(null);
	  			alert.setHeaderText(null);
	  			alert.setContentText("Some fields are missing");
	  			alert.showAndWait();
	  		}
	  		else {
	  				Custumer.UpdateCostumer(CustumerName.getText(), CustumerCIN.getText(), CustumerEmail.getText(), CustumerTele.getText(), CustumerPassword.getText());
	  				Clear();
	  				//refresh ne fonction pas. pas le meme objet 
	  			}
	  		}
	     //****************************************** DELETE CUSTUMERS *************************************************
	  	 private void DeleteCustumerController() {
	  		CostumerModel selected = new CostumerModel();
	  		selected = CustumersTable.getSelectionModel().getSelectedItem();
	  		String CIN = selected.getCIN();
	  		Custumer.DeleteCostumer(CIN);
	  	 }
	  	 
	  	//****************************************** SEARCH CUSTUMERS *************************************************
	  	 /*private void SearchCustumer() {
	   		
	   	 }*/
	  	 
	  	 
	  	 //************************************ CLEAR ALL *********************************************************
	 	 private void Clear() {
	 		CustumerCIN.setText("");
	 		CustumerName.setText("");
	 		CustumerTele.setText("");
	 		CustumerEmail.setText("");
	 		CustumerPassword.setText("");
	 	 }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			displayCustumers();
			AddBtn.setOnAction(e->{
				try {
					addCustumer();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CustumerCIN.setDisable(false);
				displayCustumers();
				Clear();
			});
			
			UpdateBtn.setOnAction(e->{
				try {
					UpdateCustumer();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				displayCustumers();
				CustumerCIN.setDisable(false);
			});
			DeleteBtn.setOnAction(e->{
				DeleteCustumerController();
				displayCustumers();
				CustumerCIN.setDisable(false);
				Clear();
				
			});
			DashboardTrajet.setOnAction(e->{
				new SwitchScensController().switchScene("trajet",DashboardTrajet);
				
			});
			DashboardTrain.setOnAction(e->{
				new SwitchScensController().switchScene("train",DashboardTrain);
				
			});
			DashboardReservation.setOnAction(e->{
				new SwitchScensController().switchScene("reservation",DashboardReservation);
				
			});
			DashboardDeconnexion.setOnAction(e->{
				new SwitchScensController().switchScene("deconnexion", DashboardDeconnexion);
			 });
		}
}
