package view;

import java.awt.FileDialog;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableView;

import csvReader.DynamicTable;
import csvReader.FileUploader;
import csvReader.LocalCSVFilesFinder;
import data.DataFormatFX;
import data.ObservableListProvider;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

	// Visuali - Question page

	/**
	 * Panel that is the CSV selector process
	 */
	@FXML
	private AnchorPane visualiQuestionPanel;

	/**
	 * ToggleButton to choose whether to upload a file or choose a saved file.
	 */
	@FXML
	private JFXToggleButton uploadOrSelectCSVFileToggleButton;
	/**
	 * This combobox allows you to choose a saved csv file
	 */
	@FXML
	private JFXComboBox<String> chooseCSVFileComboBox;
	/**
	 * Label for uploadOrSelectCSVFileToggleButton
	 */
	@FXML
	private Label chooseCSVFileLabel;
	/**
	 * This button will allow user to upload CSV file to analyse
	 */
	@FXML
	private JFXButton UploadNewCSVFileButton;

	/**
	 * This button takes users to the intelligent csv reader page
	 */
	@FXML
	private JFXButton analyseCSVFileButton;

	/**
	 * This is the table that shows up on the UI - showing the CSV file that has
	 * been read.
	 */
	@FXML
	private TableView<ObservableList<StringProperty>> tableVisualiPage;
	
	/**
	 * This is a background thread that will deal with our csv file.
	 */
	private Service backgroundThread;

	// Visuali - PieChart page
	@FXML
	private AnchorPane pieChartVisualiPanel;
	@FXML
	private PieChart pieChart;

	// others ------------------------------------------------------
	// needed for movable screen
	private double xOffset = 0;
	private double yOffset = 0;
	/**
	 * String returns the name of the CSV file selected from the combobox or
	 * file upload.
	 */
	private String comboBoxCSVFileNameSelected;
	// ---------------------------------------------------------------

	// others references needed --------------------------------------------
	// Main App that controls different scenes
	private MainApp mainApp;
	ObservableListProvider dataForGraphs;
	// get the csv file names available to view
	LocalCSVFilesFinder localCSVFilesFound;
	// sets up our table
	/**
	 * This provides the table data that will populate a TableView
	 */
	DynamicTable table;

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
		// TODO: same as below but with selected string name!
		// dataForGraphs = new
		// ObservableListProvider("Pothole_Enquiries_2015.csv");

		
		
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
	 * This method handles the toggle button for if: 1. want to upload new csv
	 * file. 2. want to choose a saved csv file.
	 */
	public void handleUploadOrSavedCSFFileSelectToggleButton() {
		if (uploadOrSelectCSVFileToggleButton.isSelected()) {
			// if the toggle button is on == upload new csv file
			setUpToggleFORCSVDecider(true);
		}
		if (!(uploadOrSelectCSVFileToggleButton.isSelected())) {
			// if the toggle button is off == choose a saved csv file
			setUpToggleFORCSVDecider(false);
		}
	}

	/**
	 * sets up buttons for toggle button. true = if user wants to upload new
	 * file false = if user wants to check a saved file
	 * 
	 * @param isUpload
	 */
	/**
	 * @param isUpload
	 */
	public void setUpToggleFORCSVDecider(Boolean isUpload) {

		if (isUpload) {
			UploadNewCSVFileButton.setVisible(true);
			chooseCSVFileComboBox.setVisible(false);
			chooseCSVFileLabel.setVisible(false);
		} else {
			UploadNewCSVFileButton.setVisible(false);
			chooseCSVFileComboBox.setVisible(true);
			chooseCSVFileLabel.setVisible(true);
		}
	}

	/**
	 * Sets up table data from a file.
	 */
	@SuppressWarnings("unchecked")
	public void createTableFromData(String filename, boolean itsAFileName) {
		/*if ((!tableVisualiPage.getItems().isEmpty()) && (!table.getTable().getItems().isEmpty())) {
			tableVisualiPage.getItems().clear();
			tableVisualiPage.getColumns().clear();
			// creates a dynamic table object which can be used to populate our
			// table view UI
			table.getTable().getItems().clear();
			table.getTable().getColumns().clear();
		}*/
		
		
		table = new DynamicTable(tableVisualiPage, filename, itsAFileName);
		
		tableVisualiPage  = table.getTable();
		
		
		
		
		

		// The code below handles what happens if a section of the table is
		// pressed.
		// lets you select more than one row.
		tableVisualiPage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		// this lets the user select cells instead of rows.
		tableVisualiPage.getSelectionModel().setCellSelectionEnabled(true);
		
	/*	Service<Void> service = new Service<Void>() {
	        @Override
	        protected Task<Void> createTask() {
	            return new Task<Void>() {           
	                @Override
	                protected Void call() throws Exception {
	                    //Background work  
	                	
	                    final CountDownLatch latch = new CountDownLatch(1);
	                    
	                    Platform.runLater(new Runnable() {                          
	                        @Override
	                        public void run() {
	                            try{
	                            	
	                        		//table = new DynamicTable(tableVisualiPage, filename, itsAFileName);

	                        				
	                            }finally{
	                                latch.countDown();
	                            }
	                        }
	                    });
	                    latch.await();                      
	                    //Keep with the background work
	                    return null;
	                }
	            };
	        }
	    };
	    service.start();*/
		
		
		
		
		
		
		
		
		
		

	}
	


	/**
	 * This method handles when a csv file has been selected from the combobox.
	 * It populates the table and shows it.
	 */
	public void handleCSVFileSelectedFromComboBoxButton() {
		// String of the filename selected
		comboBoxCSVFileNameSelected = chooseCSVFileComboBox.getValue();
		// creates a table and sets the table on the ui with data from the
		// selected file
		createTableFromData(comboBoxCSVFileNameSelected, true);
		// shows the table.
		tableVisualiPage.setVisible(true);
		// shows the analyser button
		analyseCSVFileButton.setVisible(true);

		/*
		 * // dataForGraphs = new //
		 * ObservableListProvider(comboBoxCSVFileNameSelected); //TODO: fix this
		 * visualiQuestionPanel.setVisible(false);
		 * 
		 * //pass data to piechart
		 * 
		 * setUpChartData();
		 * 
		 * pieChartVisualiPanel.setVisible(true);
		 */
	}

	/**
	 * This method handles when a csv file has been uploaded. it shows the data
	 * in a table.
	 */
	public void handleNewCSVFileUpload() {
		// Creates pop-up dialog for getting a csv file only.
		FileUploader fileSelected = new FileUploader();
		// Returns string with filepath.
		String csvFilePath = fileSelected.getCSVFilePath();
		// creates and populates table.
		createTableFromData(csvFilePath, false);
		// shows the table.
		tableVisualiPage.setVisible(true);
		// shows analyse button.
		analyseCSVFileButton.setVisible(true);
		/*
		 * dataForGraphs = new ObservableListProvider(csvFilePath, true);
		 * visualiQuestionPanel.setVisible(false); setUpChartData();
		 * pieChartVisualiPanel.setVisible(true);
		 */
	}

	// =====================================================================
	// -------------------------------------------------------------------------------------------------
	// =====================================================================

	// Other methods that control GUI.

	@SuppressWarnings("unchecked")
	public void setUpChartData() {

		// pieChart.setData(dataForGraphs.getPieChartObservableList(1));

		/*
		 * phPieChart1Label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		 * phPieChart1Labe2.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		 * phPieChart1Label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
		 * 
		 * phPieChart1.setData(dataforPieChart.getPieChartObservableList(1));
		 * phPieChart1.getData().stream().forEach(data -> {
		 * data.getNode().addEventHandler(MouseEvent.ANY, e -> {
		 * phPieChart1Label.setText(data.getName() + ": " + (int)
		 * data.getPieValue()); }); });
		 * 
		 * phPieChart2.setData(dataforPieChart.getPieChartObservableList(3));
		 * phPieChart2.getData().stream().forEach(data -> {
		 * data.getNode().addEventHandler(MouseEvent.ANY, e -> {
		 * phPieChart1Labe2.setText(data.getName() + ": " + (int)
		 * data.getPieValue()); }); });
		 * 
		 * phBarChart1.setData(dataforPieChart.getBarChartData(4));
		 */
	}

	/**
	 * Resets all the charts data
	 */
	public void removeChartData() {
		/*
		 * phBarChart1.getData().clear(); phPieChart1.getData().clear();
		 * phPieChart2.getData().clear();
		 */
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

	// ================================
	// METHODS THAT DONT NEED TO BE EDITED
	// ================================
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
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Visuali");
		alert.setContentText("Are you sure you want to quit?");

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			mainApp.getPrimaryStage().close();
		} else {
			alert.close();
		}

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
