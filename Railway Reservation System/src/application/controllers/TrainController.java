package application.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.models.TrainModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;


public class TrainController implements Initializable {
	

	
	
	//button navigation
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
    private Button addBtn;
    @FXML
    private Button delete_btn;
    @FXML
    private Button edit_btn;
    @FXML
    private Button update_btn;

    //textField
    @FXML
    private TextField fc_class_nseat_text;
    @FXML
    private TextField id_train_text;
    @FXML
    private TextField sc_class_nseat_text;
    @FXML
    private TextField train_name_text;
    @FXML
    private TextField searchBare;
    @FXML
    private ComboBox<String> train_statu_combo;

   

    //table && column
    @FXML
    private TableView<TrainModel> trainTable;
    @FXML
    private TableColumn<TrainModel, String> trainFseatColumn;
    @FXML
    private TableColumn<TrainModel, String> trainIdColumn;
    @FXML
    private TableColumn<TrainModel, String> trainNameColumn;
    @FXML
    private TableColumn<TrainModel, String> trainSseatColumn;
    @FXML
    private TableColumn<TrainModel, String> trainStatuColumn;
    
    
   // private FXMLLoader loader;
	private TrainModel TrainRepository=new TrainModel();
	public String statu[]= {"in service","out of service"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initStatu();
		displayTrains();
		addBtn.setOnAction(e->{
			try {
				addTrain();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			id_train_text.setDisable(false);
			displayTrains();

		});
		
		update_btn.setOnAction(e->{
			try {
				updateTrain();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			displayTrains();
			id_train_text.setDisable(false);
		});
		delete_btn.setOnAction(e->{
			deleteTrainController();
			displayTrains();
			id_train_text.setDisable(false);
			
		});
		
		DashboardTrajet.setOnAction(e->{
			new SwitchScensController().switchScene("trajet",DashboardTrajet);
			
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
		
		
		//close_btn.setOnAction(e->{
		//System.exit(0);
		//});
		
	}
	
    //fonction pour remplire le formulaire avec les donnes du champ selectionne
	@FXML
    void rowClicked(MouseEvent event) {
		id_train_text.setDisable(true);
		TrainModel clickedTrain=trainTable.getSelectionModel().getSelectedItem();
		id_train_text.setText(String.valueOf(clickedTrain.getTrain_id()));
		train_name_text.setText(String.valueOf(clickedTrain.getTrain_name()));
		train_statu_combo.setValue(clickedTrain.getTrain_statut());
		fc_class_nseat_text.setText(String.valueOf(clickedTrain.getFC_nbr_seats()));
		sc_class_nseat_text.setText(String.valueOf(clickedTrain.getSC_nbr_seats()));
		
    }
	
	
	//initialise les colonne de la table
	private void initTrainTable() {
		trainIdColumn.setCellValueFactory(new PropertyValueFactory<TrainModel,String>("train_id"));
		trainStatuColumn.setCellValueFactory(new PropertyValueFactory<TrainModel,String>("train_statut"));
		trainNameColumn.setCellValueFactory(new PropertyValueFactory<TrainModel,String>("train_name"));
		trainFseatColumn.setCellValueFactory(new PropertyValueFactory<TrainModel,String>("FC_nbr_seats"));
		trainSseatColumn.setCellValueFactory(new PropertyValueFactory<TrainModel,String>("SC_nbr_seats"));
		
		
	}
	
	//affiche des donnes existantes dans la base de donnes
	private void displayTrains() {
		
		ObservableList<TrainModel> list=FXCollections.observableArrayList();
		ResultSet rs=TrainRepository.getAllTrains();
		try {
			while(rs.next()) {
				list.add(new TrainModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initTrainTable();
		trainTable.setItems(list);
	}
	
	
	//delete Train
	private void deleteTrainController() {
		TrainModel selected=new TrainModel();
		selected =trainTable.getSelectionModel().getSelectedItem();
		if(selected==null) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("No item selected");
			alert.showAndWait();
		}
		String ID=selected.Train_idProperty().get();
		TrainRepository.deleteTrain(ID);
		clear();
		
	}
	
	
	//add train
	//fonction qui remplie le combobox
	private void initStatu() {
		List<String> list=new ArrayList<String>();
		for(String content:statu) {
			list.add(content);
		}
		ObservableList<String> oblist=FXCollections.observableArrayList(list);
		train_statu_combo.setItems(oblist);
		train_statu_combo.getSelectionModel().select(0);
		
	}
	
	public void addTrain() throws SQLException {
		if(id_train_text.getText().equals("") ||  train_name_text.getText().equals("") || train_statu_combo.getValue()==null || fc_class_nseat_text.getText().equals("") || sc_class_nseat_text.getText().equals("")) {
			//class alert
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("Some fields are missing");
			alert.showAndWait();
		}
		else {
			if((TrainRepository.getTrain(id_train_text.getText())).next()) {
				//class alert
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("Tain deja existant");
				alert.showAndWait();
			}
			else {
				TrainRepository.creatTrain(id_train_text.getText(), train_name_text.getText(), train_statu_combo.getValue(), fc_class_nseat_text.getText(), sc_class_nseat_text.getText());
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("Tain"+ train_name_text.getText()+"ajouté avec success");
				alert.showAndWait();
				clear();
				}
			}
		}
	
	//update train
	
	public void updateTrain() throws SQLException {
		if(id_train_text.getText().equals("") ||  train_name_text.getText().equals("") || fc_class_nseat_text.getText().equals("") || sc_class_nseat_text.getText().equals("")) {
			//class alrt
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("Some fields are missing");
			alert.showAndWait();
		}
		else {
				TrainRepository.updateTrain(id_train_text.getText(), train_name_text.getText(), train_statu_combo.getValue(), fc_class_nseat_text.getText(), sc_class_nseat_text.getText());
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("Train"+ id_train_text.getText()+"mis à jour avec success");
				alert.showAndWait();
				clear();
				
				}
		}
	
	//efface le formulaire
	private void clear() {
		id_train_text.setText("");
		train_name_text.setText("");
		train_statu_combo.getSelectionModel().select(0);
		fc_class_nseat_text.setText("");
		sc_class_nseat_text.setText("");
	}
}
