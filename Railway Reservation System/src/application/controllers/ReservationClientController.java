package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.models.ReservationModel;
import application.models.TrainModel;
import application.models.TrajetModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReservationClientController implements Initializable {
	@FXML
    private Hyperlink logout=new Hyperlink()  ;
	@FXML
    private TableColumn<TrajetModel, String> arrive_time1=new TableColumn<TrajetModel, String>();

    @FXML
    private TableView<TrajetModel> billet_table=new TableView<TrajetModel>();

    @FXML
    private TableColumn<TrajetModel, String> dapart_time1=new TableColumn<TrajetModel, String>();

    @FXML
    private TableColumn<TrajetModel, String> prix1=new TableColumn<TrajetModel, String>();

    @FXML
    private Button reserver_btn;
    @FXML
    private Label id_label=new Label() ;

    @FXML
    private TableColumn<TrajetModel, String> train_name1=new TableColumn<TrajetModel, String>();

    @FXML
    private Label ville_arrive = new Label();

    @FXML
    private Label ville_depart = new Label();
    
     TrajetModel trajetRepository=new TrajetModel();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		reserver_btn.setOnAction(e->{
			/*FXMLLoader loader =new FXMLLoader();
			loader.setLocation(getClass().getResource("../view/ReservationForm.fxml"));
			ReservationClientController controller =new ReservationClientController();
			loader.setController(controller);
			try {
				loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene scene =new Scene(loader.getRoot());
			Stage stage =(Stage)reserver_btn.getScene().getWindow();
			stage.setScene(scene);
			stage.show();*/
			addReservationController();
			logout.setOnAction(e1->{
				FXMLLoader loader =new FXMLLoader();
	    		loader.setLocation(getClass().getResource("../view/Home.fxml"));
	    		HomeController controller =new HomeController () ;
	    		loader.setController(controller);
	    		try {
					loader.load();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
	    		Scene scene =new Scene(loader.getRoot());
	    		Stage stage =(Stage)logout.getScene().getWindow();
	    		stage.setScene(scene);
	    		stage.show();
			});
		});
		
	}

	private void addReservationController() {
		TrajetModel selected =new TrajetModel();
		selected=billet_table.getSelectionModel().getSelectedItem();
		if(new ReservationModel().creatReservation(ville_depart.getText(), ville_depart.getText(),selected.getTime_depart(),selected.getTime_depart(),id_label.getText(),selected.getTrain_name(),selected.getPrix())!=0) {
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("reservation effectuer avec succée");
			alert.showAndWait();
		}
		}
	private void initTrainTable() {
		train_name1.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("train_name"));
		prix1.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("prix"));
		dapart_time1.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("time_depart"));
		arrive_time1.setCellValueFactory(new PropertyValueFactory<TrajetModel,String>("time_arrive"));
	}
	
	public void setLabels(String stationDepart,String stationArrive) {
		
		ville_arrive.setText(stationArrive);
		ville_depart.setText(stationDepart);
	}
	public void displaybillets(String stationDepart,String stationArrive,String classe, int adult,int child,int id) {
		id_label.setText(String.valueOf(id));
		ville_arrive.setText(stationArrive);
		ville_depart.setText(stationDepart);
		ObservableList<TrajetModel> list=FXCollections.observableArrayList();
		ResultSet rs=trajetRepository.findeTrajetModel(stationDepart, stationArrive);
		try {
			while(rs.next()) {
				double prix;
				if(classe.equals("1ère classe")) {
					prix=rs.getDouble(7)*(adult+rs.getDouble(9)*child);
				}
				else {
					prix=rs.getDouble(8)*(adult+(rs.getDouble(9)/100)*child);
				}
				list.add(new TrajetModel(rs.getString(4),rs.getString(5),rs.getString(6),prix));
				}
			
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		initTrainTable();
		billet_table.setItems(list);
		}
		
	
	


}
