package application.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.models.StationModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class StationController implements Initializable  {
	
	@FXML
    private Button close_btn;

    @FXML
    private ImageView hidde_btn;

    @FXML
    private Button page_station_btn;
    
    @FXML
    private TableColumn<StationModel, String> station_column=new TableColumn<StationModel, String>() ;

    @FXML
    private TableView<StationModel> page_station_table=new TableView<StationModel>();

    @FXML
    private TextField page_station_text;
    StationModel stationRepository=new StationModel() ;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initStation();
		refreshTable();
		page_station_btn.setOnAction(e->{
			insertNewStation();
		});
		
		
	}
	
	private void insertNewStation() {
		stationRepository.creatStation(page_station_text.getText());
		page_station_text.setText("");
		refreshTable();
		
	}
	
	private void initStation() {
		station_column.setCellValueFactory(cell -> cell.getValue().station_nameProperty());
		
	}
	
	
	private void refreshTable() {
		ObservableList<StationModel> list=FXCollections.observableArrayList();
		ResultSet rs=stationRepository.getAllStations();
		try {
			while(rs.next()) {
				list.add(new StationModel(rs.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initStation();
		page_station_table.setItems(list);
		
	}

}
