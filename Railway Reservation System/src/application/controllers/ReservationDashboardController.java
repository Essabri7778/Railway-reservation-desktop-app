package application.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.models.ReservationModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ReservationDashboardController implements Initializable {
	
	 @FXML
	    private Button DashboardClient;

	    @FXML
	    private Button DashboardDeconnexion;

	    @FXML
	    private Button DashboardReservation;

	    @FXML
	    private Button DashboardTrain;

	    @FXML
	    private Button DashboardTrajet;
	
	
    @FXML
    private TextField search_text;
    @FXML
    private Button btn_delete;
    @FXML
    private TextField id_reservation_text;
    
    
    @FXML
    private TableColumn<ReservationModel, Integer> num_seats_column=new TableColumn<ReservationModel, Integer>();
    @FXML
    private TableColumn<ReservationModel, Integer> reservation_id_column=new TableColumn<ReservationModel, Integer>();
    @FXML
    private TableColumn<ReservationModel, String> cin_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, String> class_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, String> date_journey_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, String> date_reservation_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, String> from_station_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, String> hour_arrive_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, String> hour_depart_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, Integer> nbr_passengers_column=new TableColumn<ReservationModel, Integer>();
    @FXML
    private TableColumn<ReservationModel, String> to_station_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, String> train_column=new TableColumn<ReservationModel, String>();
    @FXML
    private TableColumn<ReservationModel, Double> price_column=new TableColumn<ReservationModel,Double>();
    @FXML
    private TableView<ReservationModel> table_reservation=new TableView<ReservationModel>();
    
    
    private ReservationModel reservationRepository=new ReservationModel();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		displayReservation();
		btn_delete.setOnAction(e->{
			deleteReservation();
		});
		
		DashboardTrajet.setOnAction(e->{
			new SwitchScensController().switchScene("trajet",DashboardTrajet);
			
		});
		DashboardTrain.setOnAction(e->{
			new SwitchScensController().switchScene("train",DashboardTrain);
			
		});
		DashboardClient.setOnAction(e->{
			new SwitchScensController().switchScene("client",DashboardClient);
			
		});
		 DashboardDeconnexion.setOnAction(e->{
			new SwitchScensController().switchScene("deconnexion", DashboardDeconnexion);
			
		});
		
		
	}
    
	
	 @FXML
	 void rowClicked(MouseEvent event) {
		 ReservationModel clickedReservation=table_reservation.getSelectionModel().getSelectedItem();
		 id_reservation_text.setText(String.valueOf(clickedReservation.getId_reservation()));
	    }
   

	 private void initReservationTable() {
		 
		 price_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,Double>("prix"));
		 train_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("train"));
		 to_station_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("to_station"));//
		 nbr_passengers_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,Integer>("nbr_passenger"));
		 hour_depart_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("date_depart"));
		 hour_arrive_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("date_arrive"));
		 from_station_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("from_station"));
		 date_reservation_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("date_reservation"));
		 date_journey_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("date_journey"));
		 class_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("classTrain"));
		 cin_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,String>("cin_costumer"));//
		 reservation_id_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,Integer>("id_reservation"));
		 num_seats_column.setCellValueFactory(new PropertyValueFactory<ReservationModel,Integer>("num_seats"));
	 }
	 
	 
	 private void displayReservation() {
		 ObservableList<ReservationModel> list=FXCollections.observableArrayList();
		 ResultSet rs=reservationRepository.getAllReseravations();
		 try {
			 while(rs.next()) {
				list.add(new ReservationModel(rs.getInt(1),rs.getString(2),rs.getString(13)
						,rs.getString(12),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getDouble(11))); 
				}
			 }catch(Exception e){
	                e.printStackTrace();
	                }
		 initReservationTable();
		 table_reservation.setItems(list);
		 }
	 
	 private void deleteReservation() {
		 ReservationModel selected=new ReservationModel();
		 selected =table_reservation.getSelectionModel().getSelectedItem();
			if(selected==null) {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("No item selected");
				alert.showAndWait();
			}
			int ID=selected.getId_reservation();
			reservationRepository.deleteReservation(ID);
			displayReservation();
			clear();
	 }
		 
	

private void clear() {
	id_reservation_text.setText("");
}
   }  

    

   


