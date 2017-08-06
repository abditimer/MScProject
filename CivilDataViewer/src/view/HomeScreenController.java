package view;

import java.awt.FileDialog;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;

import csvReader.LocalCSVFilesFinder;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.MainApp;

public class HomeScreenController {
	// Main field variables ----------------------------------------------
	@FXML
	private AnchorPane homePanel;
	@FXML
	private AnchorPane visualPanel;
	@FXML
	private AnchorPane aboutPanel;
	@FXML
	private AnchorPane contactPanel;
	// these refer to the top navigation buttons
	@FXML
	private Button homePanelButton;
	@FXML
	private Button visualPanelButton;
	@FXML
	private Button aboutPanelButton;
	@FXML
	private Button contactPanelButton;
	// minus and close buttons
	@FXML
	private Button minusButton;
	@FXML
	private Button closeButton;
	@FXML
	private Button moveScreenButton;
	// others Buttons ------------------------------------------------------
	// needed for movable screen
	private double xOffset = 0;
	private double yOffset = 0;
	//get the csv file names available to view
	LocalCSVFilesFinder localCSVFilesFound;
	// Visuali Page items --------------------------------------------
	
	//Visuali - Question page
	@FXML
	private AnchorPane visualiQuestionPanel;
	@FXML
	private JFXComboBox<String> chooseCSVFileComboBox;
	@FXML
	private JFXButton UploadNewCSVFileButton;
	@FXML
	private JFXButton visualiseSelectedCSVFileButton;
	@FXML
	private Label chooseCSVFileLabel;
	
	String comboBoxCSVFileNameSelected;
	
	//Visuali - PieChart page
	@FXML
	private AnchorPane pieChartVisualiPanel;
	@FXML
	private PieChart pieChart;
	
	
	// ---------------------------------------------------------------
	
	// others references needed --------------------------------------------
	//Main App that controls different scenes
	private MainApp mainApp;
	ObservableListProvider dataForGraphs;
	// ====================================================================
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * This is called after all fxml variable tags have been loaded
	 */
	@FXML
	public void initialize() {
		localCSVFilesFound = new LocalCSVFilesFinder();
		chooseCSVFileComboBox.getItems().addAll(localCSVFilesFound.getCSVFileNames());
		//TODO: same as below but with selected string name!
		//dataForGraphs = new ObservableListProvider("Pothole_Enquiries_2015.csv");
	}

	// =====================================================================
	// -------------------------------------------------------------------------------------------------
	// =====================================================================

	// The following code will now control the buttons on the top of all
	// screens.

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
	}

	/**
	 * Button for going to the homePanel screen
	 */
	public void aboutButtonClicked() {
		setScreenVisibility(false, false, true, false);
	}

	/**
	 * Button for going to the homePanel screen
	 */
	public void contactButtonClicked() {
		setScreenVisibility(false, false, false, true);
	}

	// Visuali page =======================================================

	
	
	
	
	
	/**
	 * This button becomes visible once a selection has been made from the combobox
	 */
	public void hadleCSVComboBoxButtonSelected() {
		visualiseSelectedCSVFileButton.setVisible(true);
	}
	
	/**
	 * <b> Handler for CSV File selector.
	 * This method handles when a csv file has been selected.
	 * It saves the CSV file string name.
	 */
	public void handleCSVFileSelectedFromComboBoxButton() {
		comboBoxCSVFileNameSelected = chooseCSVFileComboBox.getValue();
		dataForGraphs = new ObservableListProvider(comboBoxCSVFileNameSelected);
		
		//TODO: fix this
		visualiQuestionPanel.setVisible(false);
		
		//pass data to piechart
		
		setUpChartData();
		
		pieChartVisualiPanel.setVisible(true);
	}
	
	public void handleNewCSVFileUpload() {
		String csvFilePath;
		/*FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 0){
			csvFilePath = fd.getFiles()[0].getAbsolutePath();
		}*/
		
		FileChooser filechooser = new FileChooser();
		/*filechooser.setTitle("Open CSV file");
		filechooser.showOpenDialog(new Stage());*/
		File fd = filechooser.showOpenDialog(null);
		csvFilePath = fd.getAbsolutePath();
		dataForGraphs = new ObservableListProvider(csvFilePath, true);
		visualiQuestionPanel.setVisible(false);
		setUpChartData();
		pieChartVisualiPanel.setVisible(true);
	}

	// =====================================================================
	// -------------------------------------------------------------------------------------------------
	// =====================================================================

	// Other methods that control GUI.

	@SuppressWarnings("unchecked")
	public void setUpChartData() {
		
		pieChart.setData(dataForGraphs.getPieChartObservableList(1));
		
		
		
		/*phPieChart1Label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		phPieChart1Labe2.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		phPieChart1Label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));

		phPieChart1.setData(dataforPieChart.getPieChartObservableList(1));
		phPieChart1.getData().stream().forEach(data -> {
			data.getNode().addEventHandler(MouseEvent.ANY, e -> {
				phPieChart1Label.setText(data.getName() + ": " + (int) data.getPieValue());
			});
		});

		phPieChart2.setData(dataforPieChart.getPieChartObservableList(3));
		phPieChart2.getData().stream().forEach(data -> {
			data.getNode().addEventHandler(MouseEvent.ANY, e -> {
				phPieChart1Labe2.setText(data.getName() + ": " + (int) data.getPieValue());
			});
		});

		phBarChart1.setData(dataforPieChart.getBarChartData(4));
*/
	}

	/**
	 * Resets all the charts data
	 */
	public void removeChartData() {
		/*phBarChart1.getData().clear();
		phPieChart1.getData().clear();
		phPieChart2.getData().clear();*/
	}

	/**
	 * Method to decide which screen to show
	 * 
	 * @param homeScreenVisible
	 * @param visualPanelVisible
	 * @param aboutPanelVisible
	 * @param contactPanelVisible
	 */
	public void setScreenVisibility(Boolean homeScreenVisible, Boolean visualPanelVisible, Boolean aboutPanelVisible,
			Boolean contactPanelVisible) {
		homePanel.setVisible(homeScreenVisible);
		visualPanel.setVisible(visualPanelVisible);
		aboutPanel.setVisible(aboutPanelVisible);
		contactPanel.setVisible(contactPanelVisible);
		if (!visualPanelVisible) {
			removeChartData();
		}
		setUpChartData();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	//================================
	//METHODS THAT DONT NEED TO BE EDITED
	//================================
	/**
	 * method for minimising page
	 */
	public void minusButtonClicked() {
		mainApp.getPrimaryStage().setIconified(true);
	}

	/**
	 * This handles when the close button is pressed.
	 * 
	 * @param event
	 */
	public void handleCloseButtonAction() {
		mainApp.getPrimaryStage().close();
	}

	/**
	 * allows for top of the screen to be movable. invisible button above area
	 * on top of window lets screen be draggable when clicked.
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
