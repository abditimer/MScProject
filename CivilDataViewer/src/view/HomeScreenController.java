package view;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;

import data.DataFormatFX;
import data.ObservableListProvider;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	
	private MainApp mainApp;
	//needed for movable screen
	private double xOffset = 0;
    private double yOffset = 0;
    
    //Graph data
    @FXML
	PieChart phPieChart1;
    @FXML
    private Label phPieChart1Label;
    @FXML
	PieChart phPieChart2;
    @FXML
    private Label phPieChart1Labe2;
    @FXML
	BarChart phBarChart1;
    @FXML
    private Label phBarChartLabel1;
    
    ObservableListProvider dataforPieChart = new ObservableListProvider("Pothole_Enquiries_2015");
    
    
    //Data visuali page buttons
    @FXML
    private JFXToggleButton onlineOfflineToggleButton;
    @FXML
    private JFXComboBox<String> csvFileSelector;
    
    @FXML
    private AnchorPane onlineVisualiPanel;
    @FXML
    private AnchorPane offlineVisualiPane;
    
    
    //====================================================================
	public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    	
    }
	
	/**
	 * This is called after all fxml variable tags have been loaded
	 */
	/*@FXML
	public void initialize() {
		
	}*/
	
	//=====================================================================
	//-------------------------------------------------------------------------------------------------
	//=====================================================================
	
	//The following code will now control the buttons on the top of all screens.
	
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
		setUpChartData();
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
	
	//Visuali page =======================================================
	
	public void onoffToggleButtonClicked() {
		
		
		if (onlineOfflineToggleButton.isSelected()) {
			offlineVisualiPane.setVisible(false);
			onlineVisualiPanel.setVisible(true);
		} else {
			offlineVisualiPane.setVisible(true);
			onlineVisualiPanel.setVisible(false);
		}
	}
	
	//=====================================================================
	//-------------------------------------------------------------------------------------------------
	//=====================================================================
	
	//Other methods that control GUI.
	
	@SuppressWarnings("unchecked")
	public void setUpChartData() {
		phPieChart1Label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		phPieChart1Labe2.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		phPieChart1Label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		
		phPieChart1.setData(dataforPieChart.getPieChartObservableList(1));
		phPieChart1.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e -> {
                phPieChart1Label.setText(data.getName() + ": " +
                        (int) data.getPieValue());
            });
        });
		
		phPieChart2.setData(dataforPieChart.getPieChartObservableList(3));
		phPieChart2.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e -> {
            	phPieChart1Labe2.setText(data.getName() + ": " +
                        (int) data.getPieValue());
            });
        });
		
		
		phBarChart1.setData(dataforPieChart.getBarChartData(4));
		
		
		
	}
	
	/**
	 * Resets all the charts data
	 */
	public void removeChartData() {
		phBarChart1.getData().clear();
		phPieChart1.getData().clear();
		phPieChart2.getData().clear();
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
		
		removeChartData();
	}

	/**
	 * method for minimising page
	 */
	public void minusButtonClicked() {
		mainApp.getPrimaryStage().setIconified(true);
	}
	
	/**
	 * This handles when the close button is pressed.
	 * @param event
	 */
	public void handleCloseButtonAction() {
		mainApp.getPrimaryStage().close();
	}
	
	/**
	 * allows for top of the screen to be movable.
	 * invisible button above area on top of window lets screen be draggable when clicked.
	 */
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
