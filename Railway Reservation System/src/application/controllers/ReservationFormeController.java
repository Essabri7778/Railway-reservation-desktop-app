package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.models.StationModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


public class ReservationFormeController implements Initializable {
	
		@FXML
		public Label label_id = new Label();
	
	 	@FXML
	    private Button btn_search;

	    @FXML
	    private ComboBox<String> class_combo;

	    @FXML
	    private DatePicker date_arrive_picker;

	    @FXML
	    private DatePicker date_depart_picker;


	    @FXML
	    private Button close_btn;

	    @FXML
	    private Button hide_btn;

	 
	    @FXML
	    private ComboBox<String> station_arrive_text;

	    @FXML
	    private ComboBox<String> station_depart_text;
	    @FXML
	    private Spinner<Integer> spinnerAdult=new Spinner<Integer>();
	    @FXML
	    private Spinner<Integer> spinnerChild=new Spinner<Integer>();
	    
	    
	    public String classe[]= {"1ère classe","2ème classe"};
	    
	    private StationModel stationRepository=new StationModel();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initClassCombo();	
		initToStationCombo();
		initFromStationCombo();
		initPassengerCombo();
		//btn_search.setOnAction(e->{
			btn_search.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					DBUtils.changeScene(event,"../view/BilletFactory.fxml" ,station_depart_text.getValue(), station_arrive_text.getValue(), class_combo.getValue(), spinnerAdult.getValue(), spinnerChild.getValue(),Integer.valueOf(label_id.getText()));
				}
			});
			
			
			
//			FXMLLoader loader =new FXMLLoader();
//			try {
//			
//    		loader.setLocation(getClass().getResource("../view/BilletFactory.fxml"));
//    		ReservationClientController controller =new ReservationClientController() ;
//    		//controller.displaybillets(station_depart_text.getValue(), station_arrive_text.getValue(), class_combo.getValue(), spinnerAdult.getValue(), spinnerChild.getValue());
//    		//loader.setController(controller);
//    		controller.setLabels(station_depart_text.getValue(), station_arrive_text.getValue());
//    		
//				loader.load();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//    		Scene scene =new Scene(loader.getRoot());
//    		Stage stage =(Stage)btn_search.getScene().getWindow();
//    		stage.setScene(scene);
//    		stage.show();
	//	});
		
	
			
		
		   
		}
	
	private void initFromStationCombo() {
		ResultSet rs= stationRepository.getAllStations();
		ObservableList<String> list=FXCollections.observableArrayList();
		try {
			while(rs.next()) {
				list.add(rs.getString(2));}
			}catch (SQLException e) {
				e.printStackTrace();}
		station_arrive_text.setItems(list);
	}
	
	private void initToStationCombo() {
		ResultSet rs= stationRepository.getAllStations();
		ObservableList<String> list=FXCollections.observableArrayList();
		try {
			while(rs.next()) {
				list.add(rs.getString(2));}
			}catch (SQLException e) {
				e.printStackTrace();}
		station_depart_text.setItems(list);
	}
	
	private void initPassengerCombo() {
		SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
		spinnerAdult.setValueFactory(valueFactory1);
		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
		spinnerChild.setValueFactory(valueFactory2);
	}
	
	private void initClassCombo() {
		List<String> list =new ArrayList<String>();
		for(String content:classe) {
			list.add(content);
		}
		ObservableList<String> oblist=FXCollections.observableArrayList(list);
		class_combo.setItems(oblist);
		class_combo.getSelectionModel().select(0);
		
		
	}
	
	public void setLabelId(int id) {
		label_id.setText(String.valueOf(id));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
}
