package view;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import data.DataToJFXGraph;
import data.DataToPieChart;

import java.util.Map.Entry;

import dataDownloader.PotholeEnquiry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainApp;

public class HomeScreenController  {
	//These refer to different panels on software
	@FXML
	private AnchorPane homePanel;
	@FXML
	private AnchorPane visualPanel;
	@FXML
	private AnchorPane aboutPanel;
	@FXML
	private AnchorPane contactPanel;
	//these refer to the top navigation buttons
	@FXML
	private Button homePanelButton;
	@FXML
	private Button visualPanelButton;
	@FXML
	private Button aboutPanelButton;
	@FXML
	private Button contactPanelButton;
	//minus and close buttons
	@FXML
	private Button minusButton;
	@FXML
	private Button closeButton;
	@FXML
	private Button moveScreenButton;
	
	//others
	@FXML
	PieChart piechart;
	//data I need to refer to 
	PotholeEnquiry potholeData = new PotholeEnquiry();
	//GraphData graphData = new GraphData();
	private MainApp mainApp;
	//needed for movable screen
	private double xOffset = 0;
    private double yOffset = 0;
	
    //To transform Data
    DataToPieChart dataToGraph = new DataToPieChart("Pothole_Enquiries_2015");
    
    
    //====================================================================
	public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    }
	
	/**
	 * Button for going to the homePanel screen
	 */
	public void homeButtonClicked() {
		setScreenVisibility(true, false, false, false);
	}
	/**
	 * Button for going to the homePanel screen
	 */
	public void visualButtonClicked() {
		setScreenVisibility(false, true, false, false);
		piechart.setData(dataToGraph.piechartData());
	}
	
	/**
	 * Button for going to the homePanel screen
	 */
	public void aboutButtonClicked() {
		setScreenVisibility(false, false, true, false);
	}/**
	 * Button for going to the homePanel screen
	 */
	public void contactButtonClicked() {
		setScreenVisibility(false, false, false, true);
	}
	


	/**
	 * Method to decide which screen to show
	 * @param homeScreenVisible
	 * @param visualPanelVisible
	 * @param aboutPanelVisible
	 * @param contactPanelVisible
	 */
	public void setScreenVisibility(Boolean homeScreenVisible, Boolean visualPanelVisible, Boolean aboutPanelVisible, Boolean contactPanelVisible) {
		homePanel.setVisible(homeScreenVisible);
		visualPanel.setVisible(visualPanelVisible);
		aboutPanel.setVisible(aboutPanelVisible);
		contactPanel.setVisible(contactPanelVisible);
	}

	/**
	 * method for minimising page
	 */
	public void minusButtonClicked() {
		mainApp.getPrimaryStage().setIconified(true);
	}
	
	public void handleCloseButtonAction(ActionEvent event) {
		mainApp.getPrimaryStage().close();
	}
	
	public void handleMovingScreen() {
		moveScreenButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
		moveScreenButton.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	mainApp.getPrimaryStage().setX(event.getScreenX() - xOffset);
            	mainApp.getPrimaryStage().setY(event.getScreenY() - yOffset);
            }
        });
	}
	
	
}
