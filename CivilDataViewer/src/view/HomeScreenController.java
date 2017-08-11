package view;

import java.awt.FileDialog;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableView;

import csvReader.CSVReader;
import csvReader.DynamicTable;
import csvReader.FileUploader;
import csvReader.LocalCSVFilesFinder;
import csvReader.ProgressForm;
import data.DataFormatFX;
import data.ObservableListProvider;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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

	// ¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬Visuali -
	// Question page---------------------------------------------
	/**
	 * Panel that is the CSV selector process. Contains: 1. CSVSelectorPanel 2.
	 * IntelligenceAndTableVisualiserPanel
	 * 
	 * 3. the various graph anchorpanes.
	 */
	@FXML
	private AnchorPane visualiQuestionPanel;
	@FXML
	private AnchorPane CSVSelectorPanel;

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
	@FXML
	private Label uploadCSVFileLabel;
	/**
	 * Currently selected csv file name.
	 */
	@FXML
	private Label currentlySelectedCSVFileLabel;
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
	// ========================================******* INTELLIGENCE
	// PAGE************================================
	@FXML
	private AnchorPane IntelligenceAndTableVisualiserPanel;
	
	/**
	 * This is the table that will show the data about the CSV file
	 */
	@FXML
	private TableView<ObservableList<StringProperty>> tableVisualiPage;
	
	/**
	 * This is the button that lets you create a table
	 */
	@FXML
	private JFXButton createTableButton;

	// Visuali - PieChart page
	@FXML
	private AnchorPane pieChartVisualiPanel;
	@FXML
	private PieChart pieChart;

	// *********************************************others references
	// needed*****************************************************************

	// needed for movable screen
	private double xOffset = 0;
	private double yOffset = 0;
	/**
	 * String returns the name of the CSV file selected from the combobox or
	 * file upload.
	 */
	private String csvFileNameSelected;
	/**
	 * This opens a file uploader.
	 */
	private FileUploader fileSelected;
	/**
	 * indicates whether the file selected is a file name or a filepath.
	 */
	private Boolean isFilePath = false;
	/**
	 * This will be where we will store info about the read in csv file.
	 */
	private CSVReader readIn;
	// ---------------------------------------------------------------

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

	// List containing all the rows from csv file except for header
	private List<String[]> columnData;
	// String array of the headers
	private String[] headerData;
	
	//lets us know if a table has been generated from data.
	private Boolean isTableCreated = false;

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

	// ************************************************************Visuali
	// page**********************************************************************
	// --
	// ===============================FIRST QUESTION
	// PAGE=============================================================
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
	 * sets up buttons for toggle button.
	 * 
	 * @param isUpload
	 *            true = if user wants to upload new file. false = if user wants
	 *            to check a saved file
	 */
	public void setUpToggleFORCSVDecider(Boolean isUpload) {
		if (isUpload) {
			UploadNewCSVFileButton.setVisible(true);
			// uploadCSVFileLabel.setVisible(true);
			chooseCSVFileComboBox.setVisible(false);
			chooseCSVFileLabel.setVisible(false);
		} else {
			UploadNewCSVFileButton.setVisible(false);
			// uploadCSVFileLabel.setVisible(false);
			chooseCSVFileComboBox.setVisible(true);
			chooseCSVFileLabel.setVisible(true);
		}
	}

	/**
	 * This method handles when a csv file has been selected from the combobox.
	 * It populates the table and shows it.
	 */
	public void handleCSVFileSelectedFromComboBoxButton() {
		// String of the filename selected
		csvFileNameSelected = chooseCSVFileComboBox.getValue();
		// set that the file name is just a file name.
		isFilePath = false;

		// shows the analyser button
		analyseCSVFileButton.setVisible(true);
		// show the file name
		currentlySelectedCSVFileLabel.setText(csvFileNameSelected);
	}

	/**
	 * This method handles when a csv file has been uploaded. it shows the data
	 * in a table.
	 */
	public void handleNewCSVFileUpload() {
		// lock button so user cant spam
		UploadNewCSVFileButton.setDisable(true);
		// Creates pop-up dialog for getting a csv file only.
		fileSelected = new FileUploader();
		// Returns string with filepath.
		if (fileSelected.getWasSuccessful()) {
			csvFileNameSelected = fileSelected.getCSVFilePath();
		}
		// indicate file is a filepath
		isFilePath = true;

		analyseCSVFileButton.setVisible(true);
		// unlock button
		UploadNewCSVFileButton.setDisable(false);
		// show file name selected
		currentlySelectedCSVFileLabel.setText(csvFileNameSelected);
	}

	public void analyseCSVFileButton() {

		ProgressForm pForm = new ProgressForm();
		// In real life this task would do something useful and return
		// some meaningful result:
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {

				// Here the background thread will run more complicated
				// computations.

				// now create a csvReader object using the data - in background
				// thread
				if (isFilePath) {
					// its a filepath hence
					readIn = new CSVReader(csvFileNameSelected, true);

				} else {
					readIn = new CSVReader(csvFileNameSelected);
				}

				// return and store the header && data
				headerData = readIn.getHeader();
				columnData = readIn.getData();

				 updateProgress(10, 10);
				return null;
			}
		};

		// binds progress of progress bars to progress of task:
		pForm.activateProgressBar(task);

		// in real life this method would get the result of the task
		// and update the UI based on its value:
		task.setOnSucceeded(event -> {
			pForm.getDialogStage().close();

			// visualise the next anchorpane + table.
			// IntelligenceAndTableVisualiserPanel.setVisible(true);
			visualiQuestionPanel.setVisible(false);

			// TODO
			IntelligenceAndTableVisualiserPanel.setVisible(true);
			
		});

		pForm.getDialogStage().show();

		Thread thread = new Thread(task);
		thread.start();

	}
	
	public void handleTableCreationButton() {
		if (!(isTableCreated)) {
			//create table for the first time
			createTableFromData();
			//table has now been created
			isTableCreated = true;
			tableVisualiPage.setVisible(true);
		} else {
			createTableButton.setDisable(true);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("");
			alert.setContentText("You have already created a table from your data");
			Optional<ButtonType> result = alert.showAndWait();

			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
				alert.close();
			}
		}
	}
	
	/**
	 * Sets up the table.
	 */
	public void createTableFromData() {
		
		tableVisualiPage.setPlaceholder(new Label("Loading..."));
		
		//Creating background thread to populate table
				Task<Void> task = new Task<Void>() {
					/**
					 * Are call() creates table
					 */
					@Override
					protected Void call() throws Exception {
						//Create TableColumns
						
						
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								for (int i = 0; i < headerData.length; i++) {
									//create a TableColumn from header i
									TableColumn<ObservableList<StringProperty>, String> tempCol =  createColumn(i, headerData[i]);
									//add column headers to my table
									tableVisualiPage.getColumns().add(tempCol);
								}
								System.out.println("created headers...");
							}
						});
						
						for (String[] row : columnData)
						
						Platform.runLater(new Runnable() {
								@Override
								public void run() {
									// Add additional columns if necessary:
									for (int i = tableVisualiPage.getColumns().size(); i < row.length; i++) {
										tableVisualiPage.getColumns().add(createColumn(i, ""));
									}
									
									
									// Add data from the line to the table column
									//create a list
									ObservableList<StringProperty> data = FXCollections.observableArrayList();
									//each column data, add it to the list.
									for (String value : row) {
										data.add(new SimpleStringProperty(value));
									}
									//add list to our table.
									tableVisualiPage.getItems().add(data);
								}
							});
						
						return null;
					}
				};
				Thread thread = new Thread(task);
				thread.setDaemon(true);
				thread.start();
		
		
	}
	/**
	 * Method creates columns based on the read in csv file header size.
	 * 
	 * @param columnIndex index of the column
	 * @param columnTitle tite of the column
	 * @return TableColumn
	 */
	private TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex, String columnTitle) {
		//create a new column
		TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
		String title;
		//if no title specified
		if (columnTitle == null || columnTitle.trim().length() == 0) {
			//just display column number
			title = "Column " + (columnIndex + 1);
		} else {
			//else display column name
			title = columnTitle;
		}
		//set title of column
		column.setText(title);
		
		column.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
						ObservableList<StringProperty> values = cellDataFeatures.getValue();
						if (columnIndex >= values.size()) {
							return new SimpleStringProperty("");
						} else {
							return cellDataFeatures.getValue().get(columnIndex);
						}
					}
				});
		return column;
	}

	// =====================================================================
	// -------------------------------------------------------------------------------------------------
	// =====================================================================

	// Other methods that control GUI.

	@SuppressWarnings("unchecked")
	public void setUpChartData() {
		
		
		//readIn.printCSVFile();
		
		//dataForGraphs = new ObservableListProvider(fileName)
		//pieChart.setData(dataForGraphs.getPieChartObservableList(1));

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
			//removeChartData();
		}
			//setUpChartData();
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
