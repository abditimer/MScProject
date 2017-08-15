package view;

import java.awt.FileDialog;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableView;

import csvReader.CSVReader;
import csvReader.DynamicTable;
import csvReader.FileUploader;
import csvReader.LocalCSVFilesFinder;
import csvReader.ProgressForm;
import data.DataDetector;
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
import javafx.scene.input.DataFormat;
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
	/**
	 * This page is where the user gets to visualy see there data on a table.
	 * also questions on selecting the data to analyse
	 */
	@FXML
	private AnchorPane IntelligenceAndTableVisualiserPanel;

	/**
	 * This is the button that lets you create a table
	 */
	@FXML
	private JFXButton createTableButton;
	/**
	 * Combobox showing the names of different columns you can pick
	 */
	@FXML
	private JFXComboBox columnSelectorCBTN;
	/**
	 * its label
	 */
	@FXML
	private Label columnSelectorLabel;
	/**
	 * Select data type.
	 */
	@FXML
	private JFXComboBox dataTypeSelectorCBTN;
	/**
	 * This toggle button lets you inform system the data is lat/long
	 */
	@FXML
	private JFXToggleButton latLongSelectorBTN;
	/**
	 * This toggle button lets you inform system user wants to compare data.
	 */
	@FXML
	private JFXToggleButton isTwoColumnsWantedTBTN;
	/**
	 * Combobox to select second column to compare.
	 */
	@FXML
	private JFXComboBox secondColumnSelectorCBTN;
	/**
	 * checkbox showing: data type has been recognised
	 */
	@FXML
	private JFXCheckBox dataTypeOKCheckBox;
	/**
	 * checkbox showing: data type has been recognised
	 */
	@FXML
	private JFXCheckBox singleColumnSelectedCheckBox;
	/**
	 * checkbox showing: data type has been recognised
	 */
	@FXML
	private JFXCheckBox twoColumnsCheckBox;
	/**
	 * This is the button that lets you create a graph
	 */
	@FXML
	private JFXButton createGraphButton;

	/**
	 * This is the table that will show the data about the CSV file
	 */
	@FXML
	private TableView<ObservableList<StringProperty>> tableVisualiPage;
	/**
	 * This label tells us the type the data is which we have selected
	 */
	@FXML
	private Label dataTypeCol1Label;

	// -----Different Graphs--------------
	/**
	 * PieChart Page and pieChart
	 */
	@FXML
	private AnchorPane pieChartVisualiPanel;
	@FXML
	private PieChart pieChart;

	/**
	 * BarChart page, barchart and back button.
	 */
	@FXML
	private AnchorPane barChartVisualiPanel;
	@FXML
	private BarChart barChart;
	/**
	 * Button to return from different charts page.
	 */
	@FXML
	private JFXButton backFromChartsButton;

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

	/**
	 * first Selected column
	 */
	private String firstColumnName;
	/**
	 * Index of column selected
	 */
	private int firstColIndex;
	/**
	 * Second selected column
	 */
	private String secondColumnName;
	/**
	 * This is the data from the column selected.
	 */
	private String[] firstColumn;
	/**
	 * This is the data from the second column selected.
	 */
	private String[] secondColumn;
	/**
	 * Needed to check type of columns
	 */
	private DataDetector dataDetector;
	/**
	 * true if first column selected is latitude.
	 */
	private Boolean firstColumnIsLatitutde;
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

	// lets us know if a table has been generated from data.
	private Boolean isTableCreated = false;
	private Boolean twoDataSetsSelected = false;

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

		tableVisualiPage.setPlaceholder(new Label("waiting..."));

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

	// **********Visuali Main
	// page************************************************
	// -----------------------
	// --FIRST QUESTION PAGE--
	// ------------------------
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
			// indicate file is a filepath
			isFilePath = true;
			analyseCSVFileButton.setVisible(true);
			// unlock button
			UploadNewCSVFileButton.setDisable(false);
			// show file name selected
			currentlySelectedCSVFileLabel.setText(csvFileNameSelected);
		} else {
			// if they pressed exit
			UploadNewCSVFileButton.setDisable(false);
		}
	}

	/**
	 * This method is called to analyse the file that has been selected. It sets
	 * both the header String[] and Data List<String[]>.
	 */
	public void analyseCSVFileButton() {
		// This is our loader indicator
		ProgressForm pForm = new ProgressForm();
		// Hard work is being done here
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
			IntelligenceAndTableVisualiserPanel.setVisible(true);

		});

		pForm.getDialogStage().show();

		Thread thread = new Thread(task);
		thread.start();

	}

	// -----------------------
	// --Table + Intelligence--
	// ------------------------
	/**
	 * This is the handler for the creation of a table. It allows user to
	 * visualise the data they want to analyse.
	 */
	public void handleTableCreationButton() {
		if (!(isTableCreated)) {
			// create table for the first time
			createTableFromData();
			// table has now been created
			isTableCreated = true;
			// show the generated table.
			tableVisualiPage.setVisible(true);
			// method to bring headers into combobox to be selectable.
			setHeadersInCol1ComboBox(true);
			// we want to undisable the first column button selector.
			columnSelectorCBTN.setDisable(false);
			columnSelectorLabel.setDisable(false);
		} else {
			createTableButton.setDisable(true);
			// create a pop up telling someone they have already created a
			// table.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("");
			alert.setContentText("You have already created a table from your data");
			Optional<ButtonType> result = alert.showAndWait();
			// once user presses ok, the alert will close.
			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
				alert.close();
			}

		}
	}

	/**
	 * Steps this method takes:
	 * 
	 * 1. Column is selected, so we save the column header name + location
	 * 
	 * 2. we check the data type, make sure there is one data type
	 * 
	 * 2.1 if there is only 1 data type = success
	 * 
	 * 2.2 if more than one data type, mention it to the user, and let them
	 * decide which data type they want
	 * 
	 * if the data type is not a string, then they have to choose two data sets.
	 */
	public void handleFirstColumnSelection() {

		setNameAndLocationOfFirstColumn();
		analyseDataTypeOfColumn();

		// Check if column is lat/long/eastings/northings
		checkColLatLong();

		if (!(rulesForColumn1Selector())) {
			// it doesnt abide by the rules
			//need to select another column
			setHeadersInCol1ComboBox(true);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("");
			alert.setContentText("Please select another column. This is not supported.");
			Optional<ButtonType> result = alert.showAndWait();
			// once user presses ok, the alert will close.
			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
				alert.close();
			}
			
		}
		//set up second combobox
		setHeadersInCol2ComboBox(true);
		
		// Tick the following:
		dataTypeOKCheckBox.setSelected(true);
		singleColumnSelectedCheckBox.setSelected(true);

		// undisable the following:

		// 1. lat/long toggle button
		latLongSelectorBTN.setDisable(false);
		// 2. second col toggle button
		isTwoColumnsWantedTBTN.setDisable(false);
		// 3. second col combobx button
		secondColumnSelectorCBTN.setDisable(false);
		// 4. disable first column selector button
		columnSelectorCBTN.setDisable(true);
		// 5. undisable graph generator button
		createGraphButton.setDisable(false);
		
		
		
	}

	/**
	 * This method sets the rules for creating the graphs. Only datatypes
	 * allowed are:
	 * 
	 * 1. String only
	 * 
	 * 2. string -> Int/Dbl
	 * 
	 * 3. Int/Dbl -> Int/Dbl
	 * 
	 * @return true if column selected fits the above.
	 */
	public Boolean rulesForColumn1Selector() {
		// is the headers col data either string or double?
		// String[] chosenArray = DataFormatFX.getColumnData(firstColIndex,
		// columnData);
		String[] chosenArray = firstColumn;
		DataDetector detectDType = new DataDetector(chosenArray);
		if (detectDType.containsString()) {
			return true;
		} else if (detectDType.containsDouble() || detectDType.containsInt()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Toggle button for selecting two variables.
	 */
	public void handlesecondColumnSelectionToggle() {

		// when turned on, need to show data in combobox
		if (isTwoColumnsWantedTBTN.isSelected()) {
			// set up combobox
			setHeadersInCol1ComboBox(false);
			secondColumnSelectorCBTN.setDisable(false);
		} else {
			secondColumnSelectorCBTN.setDisable(true);
		}
	}

	public void handleSecondColumnSelection() {
		// data must be double

		// dataDetector = new
		// DataDetector(secondColumnSelectorCBTN.getValue().toString());

		// let system know two data sets have been selected.
		twoDataSetsSelected = true;

		// disable button
		secondColumnSelectorCBTN.setDisable(true);
	}

	/**
	 * <b>Important Method: Creates graphs!</b>
	 */
	public void handleGraphGeneration() {

		// Some check to see which graph we can create
		checksToGenerateGraph();
		// if (!makePieChart || !makeBarChart || so on and so on)
		// return

		// hence we get here if we can make a graph
		// create observable list creator
		ObservableListProvider listProvider = new ObservableListProvider(readIn, firstColIndex);
		// do we need another column?
		// if toggle is on

		// create chart depending on selected chart.
		pieChart.setData(listProvider.getPieChartObservableList());
		pieChartVisualiPanel.setVisible(true);

		// hide the current page.
		IntelligenceAndTableVisualiserPanel.setVisible(false);

	}

	/**
	 * This method is called to determine which graph is needed to be generated.
	 */
	public void checksToGenerateGraph() {
		// check if one or two data sets have been set.
	}

	public void backFromGraphButton() {
		pieChartVisualiPanel.setVisible(false);
		pieChart.getData().clear();
		IntelligenceAndTableVisualiserPanel.setVisible(true);
	}

	// =====================================================================
	// -------------------------------------------------------------------------------------------------
	// =====================================================================

	/**
	 * just check if the data type within the column is of one type.
	 * 
	 * conversion of the data types happens during the graph generation.
	 */
	private void analyseDataTypeOfColumn() {
		// Detect the datatype of the selected column and return corresponding
		// get the required column
		firstColumn = DataFormatFX.getColumnData(firstColIndex, columnData);
		dataDetector = new DataDetector(firstColumn);

		if (dataDetector.contains1DataType()) {
			// hence contains one data type only. we are all set.
			// hide stuff for showing more than 1 datatype
			setUpDataTypeOfCol1Label(false);

		} else {
			// contains more than 1 data type, show the extra datatypes
			// -
			// fill other combo box with data types available
			dataTypeSelectorCBTN.getItems().addAll(dataDetector.typesContained());
			// alert the user
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("More than one data type detected - Please select one data type!");
			Optional<ButtonType> result = alert.showAndWait();
			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
				alert.close();
			} else {
				alert.close();
			}
			// has more than one data type. Ask user to choose a data type.
			setUpDataTypeOfCol1Label(true);
		}

		// if data selected is not a string, has to choose another

	}

	/**
	 * This method sets the text for the label above the combobox for the
	 * datatype.
	 * 
	 * if 1 datatype is contained == hidden.
	 * 
	 * if >1 datatype == show label.
	 */
	private void setUpDataTypeOfCol1Label(Boolean setUp) {
		if (setUp) {
			// This means more than 1 datatype, so set this up.
			dataTypeCol1Label.setVisible(true);
			dataTypeSelectorCBTN.setVisible(true);
		} else {
			dataTypeCol1Label.setVisible(false);
			dataTypeSelectorCBTN.setVisible(false);
		}
	}

	/**
	 * This is called the moment a column has been selected from our
	 * pre-uploaded table. This method takes the value of the selected column,
	 * and finds out the type of that column, hence determining the data type of
	 * the actual data.
	 */
	public void setNameAndLocationOfFirstColumn() {
		// store chosen name of column selected from within combobox.
		firstColumnName = columnSelectorCBTN.getValue().toString();
		// store location of column chosen, to be used to id the column.
		firstColIndex = readIn.findColumnLocation(firstColumnName);
	}

	/**
	 * Checks if the column selected has lat/long/northings/eastings in the
	 * name.
	 */
	private void checkColLatLong() {
		// check if it is lat/long
		if (dataDetector.latLongDetector(firstColumnName) || dataDetector.northingEastingDetector(firstColumnName)) {
			// hence lat long value is included.
			latLongSelectorBTN.setSelected(true);

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Column is Lat/Long/Northings/Eastings?");

			ButtonType okButton = new ButtonType("Yes");
			ButtonType noButton = new ButtonType("No");
			alert.getButtonTypes().setAll(okButton, noButton);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == okButton) {
				firstColumnIsLatitutde = true;
			} else {
				latLongSelectorBTN.setSelected(false);
				firstColumnIsLatitutde = false;
				alert.close();
			}
		}
	}

	/**
	 * TODO: FIX THIS
	 * 
	 * @param noDataSelected
	 */
	public void setHeadersInCol1ComboBox(Boolean noDataSelected) {
		columnSelectorCBTN.valueProperty().set(null);
		if (noDataSelected) {
			columnSelectorCBTN.getItems().addAll(headerData);
		}
	}

	/**
	 * TODO: FIX THIS
	 * 
	 * @param noDataSelected
	 */
	public void setHeadersInCol2ComboBox(Boolean noDataSelected) {
		secondColumnSelectorCBTN.valueProperty().set(null);
		if (noDataSelected) {
			final List<String> list = new ArrayList<String>();
			Collections.addAll(list, headerData);
			list.remove(firstColumnName);
			headerData = list.toArray(new String[list.size()]);
			secondColumnSelectorCBTN.getItems().addAll(headerData);
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
	 * Sets up the table from the csv file selected.
	 */
	public void createTableFromData() {
		ProgressForm pForm = new ProgressForm();
		// Creating background thread to populate table
		Task<Void> task = new Task<Void>() {
			/**
			 * Are call() creates table
			 */
			@Override
			protected Void call() throws Exception {
				// Create TableColumns
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						for (int i = 0; i < headerData.length; i++) {
							// create a TableColumn from header i
							TableColumn<ObservableList<StringProperty>, String> tempCol = createColumn(i,
									headerData[i]);
							// add column headers to my table
							tableVisualiPage.getColumns().add(tempCol);
						}

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
							// create a list
							ObservableList<StringProperty> data = FXCollections.observableArrayList();
							// each column data, add it to the list.
							for (String value : row) {
								data.add(new SimpleStringProperty(value));
							}
							// add list to our table.
							tableVisualiPage.getItems().add(data);
							updateProgress(10, 10);
						}
					});

				return null;
			}
		};

		// binds progress of progress bars to progress of task:
		pForm.activateProgressBar(task);

		task.setOnSucceeded(event -> {
			pForm.getDialogStage().close();

		});

		pForm.getDialogStage().show();
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();

	}

	/**
	 * Method creates columns based on the read in csv file header size.
	 * 
	 * @param columnIndex
	 *            index of the column
	 * @param columnTitle
	 *            tite of the column
	 * @return TableColumn
	 */
	private TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex,
			String columnTitle) {
		// create a new column
		TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
		String title;
		// if no title specified
		if (columnTitle == null || columnTitle.trim().length() == 0) {
			// just display column number
			title = "Column " + (columnIndex + 1);
		} else {
			// else display column name
			title = columnTitle;
		}
		// set title of column
		column.setText(title);

		column.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
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

	@SuppressWarnings("unchecked")
	public void setUpChartData() {

		// readIn.printCSVFile();

		// dataForGraphs = new ObservableListProvider(fileName)
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
			// removeChartData();
		}
		// setUpChartData();
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
