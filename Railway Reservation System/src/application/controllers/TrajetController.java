package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.models.StationModel;
import application.models.TrainModel;
import application.models.TrajetModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TrajetController implements Initializable  {
	//dashboard navigation
	@FXML
    private Button DashboardClient;
    @FXML
    private Button DashboardDeconnexion;
    @FXML
    private Button DashboardManager;
    @FXML
    private Button DashboardReservation;
    @FXML
    private Button DashboardTrain;
    @FXML
    private Button DashboardTrajet;
    
    //button
	@FXML
    private Button add_btn;
    @FXML
    private Button add_station_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private Button update_btn;
    
   //combobox
    @FXML
    private ComboBox<String> station_arrive_combo;
    @FXML
    private ComboBox<String> station_depart_combo;
    @FXML
    private ComboBox<String> train_name_combo;
    
//fields
    @FXML
    private TextField percentage_text;
    @FXML
    private TextField tarif_first_class_text;  
    @FXML
    private TextField tarif_second_class_text;
    @FXML
    private TextField time_arrive_text;
    @FXML
    private TextField time_depart_text;
  
    
    
    //table
    @FXML
    private TableView<TrajetModel> trajet_table;
    @FXML
    private TableColumn<TrajetModel, Integer> trajet_id_column=new TableColumn<TrajetModel, Integer>();
    @FXML
    private TableColumn<TrajetModel, Integer> percentage_column=new TableColumn<TrajetModel, Integer>();
    @FXML
    private TableColumn<TrajetModel, String> station_arrive_column=new TableColumn<TrajetModel, String>() ;
    @FXML
    private TableColumn<TrajetModel, String> station_depart_column=new TableColumn<TrajetModel, String> ();
    @FXML
    private TableColumn<TrajetModel, Integer> tarif_firstC_column=new TableColumn<TrajetModel, Integer>();
    @FXML
    private TableColumn<TrajetModel, Integer> tarif_secondC_column=new TableColumn<TrajetModel, Integer>() ;
    @FXML
    private TableColumn<TrajetModel, String> time_arrive_column=new TableColumn<TrajetModel, String>();
    @FXML
    private TableColumn<TrajetModel, String> time_depart_column=new TableColumn<TrajetModel, String>();
    @FXML
    private TableColumn<TrajetModel, String> train_name_column;

    
    private FXMLLoader loader;
	private TrajetModel TrajetRepository=new TrajetModel();
	private StationModel StationRepository=new StationModel();
	private TrainModel TrainRepository=new TrainModel();
	int trajet_id;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTrajetTable();
		displayTrajets();
		add_station_btn.setOnAction(e->{
			showAddStationForm();
		});
		add_btn.setOnAction(e->{
			addTrajetController();
		});
		
		station_depart_combo.setOnMouseClicked(e->{
			showStation(1);
		});
		station_arrive_combo.setOnMouseClicked(e->{
			showStation(2);
		});
		train_name_combo.setOnMouseClicked(e->{
			showTrain();
		});
		delete_btn.setOnAction(e->{
			deleteTrajetController();
		});
		
		update_btn.setOnAction(e->{
			updateTrajetController();
		});
		
		DashboardTrain.setOnAction(e->{
			new SwitchScensController().switchScene("train",DashboardTrain);
			
		});
		DashboardReservation.setOnAction(e->{
			new SwitchScensController().switchScene("reservation",DashboardReservation);
			
		});
		DashboardClient.setOnAction(e->{
			new SwitchScensController().switchScene("client",DashboardClient);
			
		});
		 DashboardDeconnexion.setOnAction(e->{
			new SwitchScensController().switchScene("deconnexion", DashboardDeconnexion);
			
		});
		
		
		
		
	}
	
	//add station form
	private void showAddStationForm() {
			try {
				loader =new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/StationForm.fxml"));
				StationController controller =new StationController() ;
				loader.setController(controller);
				loader.load();
				Scene scene =new Scene(loader.getRoot());
				Stage stage =new Stage();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
	private void deleteTrajetController() {
		TrajetModel selected=new TrajetModel ();
		if((selected =trajet_table.getSelectionModel().getSelectedItem()) != null) {
			int ID=selected.getTrajet_id();
			TrajetRepository.deleteTrajet(ID);
			 displayTrajets();
			 clearTrajetForm();
		}
		else {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("you should select an element");
			alert.showAndWait();
		}
	}
	
	
	//fonction pour remplire le formulaire avec les donnes du champ selectionne
		@FXML
	    void rowClicked(MouseEvent event) {
			TrajetModel clickedTrajet=trajet_table.getSelectionModel().getSelectedItem();
			tarif_first_class_text.setText(String.valueOf(clickedTrajet.getFirstC_price()));
			tarif_second_class_text.setText(String.valueOf(clickedTrajet.getSecondC_price()));
			time_arrive_text.setText(String.valueOf(clickedTrajet.getTime_arrive()));
			time_depart_text.setText(String.valueOf(clickedTrajet.getTime_depart()));
			percentage_text.setText(String.valueOf(clickedTrajet.getPercentage_child()));
			train_name_combo.setValue(clickedTrajet.getTrain_name());
			station_depart_combo.setValue(clickedTrajet.getFrom_station());
			station_arrive_combo.setValue(clickedTrajet.getTo_station());
			trajet_id=clickedTrajet.getTrajet_id();
	    }
	private void updateTrajetController() {
		
			if(percentage_text.getText().equals("") ||  time_depart_text.getText().equals("") || time_arrive_text.getText().equals("")|| tarif_second_class_text.getText().equals("") || tarif_first_class_text.getText().equals("") || station_depart_combo.getValue()==null ||station_arrive_combo.getValue()==null|train_name_combo.getValue()==null) {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("Some fields are missing");
				alert.showAndWait();
			}
			else {
				TrajetRepository.updateTrajet(trajet_id,station_depart_combo.getValue(), station_arrive_combo.getValue(), time_depart_text.getText(), time_arrive_text.getText(), train_name_combo.getValue(),tarif_first_class_text.getText(),tarif_second_class_text.getText(),percentage_text.getText());
				displayTrajets();
				clearTrajetForm();
			}	
	}
	
	
	private void addTrajetController() {
		if(percentage_text.getText().equals("") ||  time_depart_text.getText().equals("") || time_arrive_text.getText().equals("") || tarif_second_class_text.getText().equals("")|| tarif_first_class_text.getText().equals("") || station_depart_combo.getValue()==null ||station_arrive_combo.getValue()==null||train_name_combo.getValue()==null ) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("Some fields are missing");
			alert.showAndWait();
		}
		else {
				TrajetRepository.creatTrajet(station_depart_combo.getValue(), station_arrive_combo.getValue(), time_depart_text.getText(), time_arrive_text.getText(), train_name_combo.getValue(),tarif_first_class_text.getText(),tarif_second_class_text.getText(),percentage_text.getText());
				displayTrajets();
				clearTrajetForm();
				}
		}
	
	
	
	private void clearTrajetForm() {
		percentage_text.setText("");
		time_depart_text.setText("");
		time_arrive_text.setText("");
		tarif_second_class_text.setText("");
		tarif_first_class_text.setText("");
		train_name_combo.setValue(null);
		station_depart_combo.setValue(null);
	    station_arrive_combo.setValue(null);
		
		
	}
	
	private void showStation(int value) {
		ResultSet rs=StationRepository.getAllStations();
		if(value==1) {
			ObservableList<String> listD=FXCollections.observableArrayList();
			try {
				while(rs.next()) {
					listD.add(rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();}
			station_depart_combo.setItems(listD);
			return;
			}
		if(value==2) {
			ObservableList<String> listA=FXCollections.observableArrayList();
			try {
				while(rs.next()){
					listA.add(rs.getString(2));
				}
			}catch (SQLException e) {
				e.printStackTrace();}
			station_arrive_combo.setItems(listA);
		}
	}
	
	
	private void showTrain() {
		ObservableList<String> listD=FXCollections.observableArrayList();
		ResultSet rs=TrainRepository.getAllTrains();
		try {
			while(rs.next()) {
				listD.add(rs.getString(3));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		train_name_combo.setItems(listD);
		//train_name_combo.getSelectionModel().clearSelection();
	}
	
	private void initTrajetTable() {
		trajet_id_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,Integer>("trajet_id"));
		station_depart_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("from_station"));
		station_arrive_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("to_station"));
		train_name_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("train_name"));
		time_arrive_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("time_arrive"));
		time_depart_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("time_depart"));
		tarif_firstC_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,Integer>("firstC_price"));
		tarif_secondC_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,Integer>("secondC_price"));
		percentage_column.setCellValueFactory(new PropertyValueFactory<TrajetModel,Integer>("percentage_child"));
		
		
		
	}
	
	private void displayTrajets() {
		ObservableList<TrajetModel> list=FXCollections.observableArrayList();
		ResultSet rs=TrajetRepository.getAllTrajets();
		try {
			while(rs.next()) {
				list.add(new TrajetModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initTrajetTable();
		trajet_table.setItems(list);
		
	}
}
