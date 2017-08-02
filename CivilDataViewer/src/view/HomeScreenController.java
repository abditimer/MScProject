package view;

import data.DataFormatFX;
import data.ObservableListProvider;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
	PieChart potholeChart1;
    @FXML
	PieChart potholeChart2;
    @FXML
	PieChart potholeChart3;
    ObservableListProvider dataforPieChart = new ObservableListProvider("Pothole_Enquiries_2015");
    
    
    
    
    //====================================================================
	public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    }
	
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
	
	//=====================================================================
	//-------------------------------------------------------------------------------------------------
	//=====================================================================
	
	//Other methods that control GUI.
	
	public void setUpChartData() {
		potholeChart1.setData(dataforPieChart.getPieChartObservableList(1));
		potholeChart2.setData(dataforPieChart.getPieChartObservableList(3));
		potholeChart3.setData(dataforPieChart.getPieChartObservableList(4));
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
