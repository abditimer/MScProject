package view;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import com.teamdev.jxmaps.javafx.MapView;

import animationImages.SlideShow;
import backgroundAudio.audioPlayMusic;
import csvReader.CSVReader;
import csvReader.DynamicTable;
import csvReader.FileUploader;
import csvReader.LocalCSVFilesFinder;
import csvReader.ProgressForm;
import data.AdvancedAnalysis;
import data.ArrayListCreator;
import data.DataDetector;
import data.DataFormatFX;
import data.ObservableListProvider;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import main.MainApp;
import map.MapCreator;

public class HomeScreenController {
	/**
	 * Panel holding my entire software components.
	 */
	@FXML
	private AnchorPane allScreensPanels;
	/**
	 * Panel holding splashscreen.
	 */
	@FXML
	private AnchorPane splashScreenPanl;

	// Main field variables ----------------------------------------------
	/**
	 * AnchorPane for one of the main pages.
	 */
	@FXML
	private AnchorPane homePanel;
	/**
	 * This is how our animation is created
	 */
	private SlideShow simpleSlideShow;
	/**
	 * AnchorPane for transitioning homescreen images.
	 */
	@FXML
	private AnchorPane iconHomePageAnimation;
	
	/**
	 * AnchorPane for info panel.
	 */
	@FXML
	private AnchorPane infoPanel;
	/**
	 * AnchorPane for one of the main pages.
	 */
	@FXML
	private AnchorPane visualPanel;
	/**
	 * AnchorPane for one of the main pages.
	 */
	@FXML
	private AnchorPane contactPanel;
	// these refer to the top navigation buttons
	/**
	 * Button that allows you to move between screens.
	 */
	@FXML
	private Button homePanelButton;
	/**
	 * Button that shows the information page.
	 */
	@FXML
	private Button infoPanelButton;
	/**
	 * Button that allows you to move between screens.
	 */
	@FXML
	private Button visualPanelButton;
	/**
	 * Button that allows you to move between screens.
	 */
	@FXML
	private Button contactPanelButton;
	// minus and close buttons
	/**
	 * unique button to minimise the screen.
	 */
	@FXML
	private Button minusButton;
	/**
	 * unique button to play/pause music.
	 */
	@FXML
	private Button musicButton;
	/**
	 * Unique button to close the screen.
	 */
	@FXML
	private Button closeButton;
	/**
	 * unique way to move the screen about.
	 */
	@FXML
	private Button moveScreenButton;
	//Anchor page items ----------------------------------
	/**
	 * AnchorPane for history page.
	 */
	@FXML
	private AnchorPane historyPanel;
	/**
	 * AnchorPane for future page.
	 */
	@FXML
	private AnchorPane futurePanel;
	/**
	 * AnchorPane for people page.
	 */
	@FXML
	private AnchorPane peoplePanel;
	/**
	 * AnchorPane for me page.
	 */
	@FXML
	private AnchorPane mePanel;
	/**
	 * Unique button to hide panel.
	 */
	@FXML
	private Button minimiseBeautyButton;
	//Buttons that control the pages within this page.
	/**
	 * Unique button to close the screen.
	 */
	@FXML
	private Button historyButton;
	/**
	 * Unique button to close the screen.
	 */
	@FXML
	private Button futureButton;
	/**
	 * Unique button to close the screen.
	 */
	@FXML
	private Button peopleButton;
	/**
	 * Unique button to close the screen.
	 */
	@FXML
	private Button meButton;
	
	// Visuali CSV selector page.
	// ------------------------------------------------
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
	// Intelligence automation page***************************************
	/**
	 * lets you return and choose a different csv file
	 */
	@FXML
	private JFXButton backButtonToCSVSelector;
	/**
	 * This page is where the user gets to visualy see there data on a table.
	 * also questions on selecting the data to analyse
	 */
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
	 * This checkbox shows data is lat/long
	 */
	@FXML
	private JFXCheckBox latLongCheckBox;
	/**
	 * This label tells us which data types we can select.
	 */
	@FXML
	private Label dataTypeCol1Label;
	/**
	 * Select data type.
	 */
	@FXML
	private JFXComboBox dataTypeSelectorCBTN;
	@FXML
	private JFXSlider mapSlider;
	/**
	 * combobox with second column choosing
	 */
	@FXML
	private JFXComboBox secondColumnSelectorCBTN;
	// checkboxes on visuali graph generation page.
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
	 * shows if you can create a graph
	 */
	@FXML
	private JFXCheckBox checkBoxPieChart;
	/**
	 * shows if you can create a graph
	 */
	@FXML
	private JFXCheckBox checkBoxBarChart;
	/**
	 * shows if you can create a graph
	 */
	@FXML
	private JFXCheckBox checkBoxScatterChart;
	/**
	 * shows if you can create a map
	 */
	@FXML
	private JFXCheckBox possibleToGenerateMapCheckBox;
	/**
	 * This is the button that lets you create a graph
	 */
	@FXML
	private JFXButton generationButton;
	/**
	 * Label that shows you data type of first col.
	 */
	@FXML
	private Label col1DataTypeLabel;
	/**
	 * Label that shows you data type of second col.
	 */
	@FXML
	private Label col2DataTypeLabel;
	/**
	 * Button that lets you learn more about the data selected.
	 */
	@FXML
	private JFXButton analyseFileButton;
	/**
	 * Button that lets you learn more about the data selected.
	 */
	@FXML
	private JFXButton advancedAnalysesButton;
	// -
	// ***********************************
	// -----Different Graphs pages--------------
	// ***********************************
	// -
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
	private CategoryAxis xAxisBarChart = new CategoryAxis();
	private NumberAxis yAxisBarChart = new NumberAxis();
	@FXML
	private BarChart barChart = new BarChart<>(xAxisBarChart, yAxisBarChart);
	private XYChart.Series barChartSeries;

	/**
	 * ScatterGraph Page and pieChart
	 */
	@FXML
	private AnchorPane scatterChartVisualiPanel;
	NumberAxis xAxisScatterChart = new NumberAxis();
	NumberAxis yAxisScatterChart = new NumberAxis();
	@FXML
	private ScatterChart scatterChart = new ScatterChart(xAxisScatterChart, yAxisScatterChart);
	private XYChart.Series scatterChartSeries;
	/**
	 * Button to return from different charts page.
	 */
	@FXML
	private JFXButton backFromChartsButton;

	// *********************************************
	// others references needed
	// *****************************************************************
	/**
	 * This will show the user the number of files on record.
	 */
	@FXML
	private Label numberOfFiles = new Label();
	/**
	 * This is the homepage fading item 1.
	 */
	@FXML
	private ImageView icon1;
	@FXML
	private ImageView icon2;
	@FXML
	private ImageView icon3;
	@FXML
	private ImageView icon4;
	@FXML
	private ImageView icon5;
	@FXML
	private ImageView icon6;
	@FXML
	private ImageView carIcon;
	
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
	 * Index of second column selected
	 */
	private int secondColIndex;
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
	private DataDetector firstColDataTypeDetected;
	/**
	 * Needed to check type of columns
	 */
	private DataDetector secondColDataTypeDetected;
	/**
	 * true if first column selected is latitude.
	 */
	private Boolean firstColumnIsLatOrEasts;
	/**
	 * Boolean to track if coordinates have been recognised
	 */
	private Boolean canCreateMap = false;
	/**
	 * Converting and storing longtitudes.
	 */
	private ArrayList<Double> longtitudeDoubles;
	/**
	 * Converting and storing latitudes.
	 */
	private ArrayList<Double> latitudeDoubles;
	/**
	 * This is true if col#1 has mutliple data types
	 */
	private boolean dataTypeChangedCol1 = false;
	// if data type of col#1 is conflicted
	private boolean dataTypeCol1IsInteger = false;
	private boolean dataTypeCol1IsDouble = false;
	private boolean dataTypeCol1IsString = false;
	// the first columns data if conflicting data types found
	private Integer[] firstColSelectedINT;
	private Double[] firstColSelectedDOUBLE;
	private String[] firstColSelectedSTRING;
	// string to help us keep track of the data type that it is
	private String col1DataType;
	private String col2DataType;
	/**
	 * This boolean indicates if second column is not used.
	 */
	private boolean secondColUsed = false;
	// Some booleans to indicate which graph the user is on
	private boolean createdPie = false;
	private boolean createdBar = false;
	private boolean createdScatter = false;
	/**
	 * Displays mode, median etc.
	 */
	private String toDisplayAdvanceInfo;
	/**
	 * lets us know if the music has been paused or not.
	 */
	private boolean isMusicPaused = false;
	// ---------------------------------------------------------------

	// Main App that controls different scenes
	private MainApp mainApp;
	// observable list provider that will be used to create graphs.
	private ObservableListProvider dataForGraphs;
	// get the csv file names available to view
	private LocalCSVFilesFinder localCSVFilesFound;
	// List containing all the rows from csv file except for header
	private List<String[]> columnData;
	// String array of the headers
	private String[] headerData;
	// String array of the headers
	private String[] headerDataSecondColumn;
	// lets us know if a table has been generated from data.
	private Boolean isTableCreated = false;
	private Boolean firstColAccepted = false;
	private MapCreator mapViewObj;
	private MapView mapView;
	private Stage primaryStage;
	private Stage mapStage;
	/**
	 * Our music class that lets us play music
	 */
	audioPlayMusic playMusic;
	

	// ====================================================================
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		primaryStage = mainApp.getPrimaryStage();
	}

	/**
	 * This is called after all fxml variable tags have been loaded
	 */
	@FXML
	public void initialize() {
		// below gets the names of any files stored within this program.
		localCSVFilesFound = new LocalCSVFilesFinder();
		chooseCSVFileComboBox.getItems().addAll(localCSVFilesFound.getCSVFileNames());
		// below sets the waiting text within the table, ready for when its
		// called.
		tableVisualiPage.setPlaceholder(new Label("waiting..."));
		// The below starts music
		playMusic = new audioPlayMusic();
		playMusic.playMusic();
		isMusicPaused = false;
		//set the homepage translating images up.
		translationIcons();
		//set up the moving car.
		translateCar();
		//show number of files currently on record to the user.
		numberOfFiles.setText(localCSVFilesFound.numberOfFiles());
	}
	
	


	// =====================================================================
	// code for controlling the header buttons
	// =====================================================================

	// The following code will now control the buttons on the top of all
	// screens.

	/**
	 * Button for going to the homePanel screen
	 */
	public void homeButtonClicked() {
		setScreenVisibility(true,false, false,  false);
	}

	/**
	 * Button for going to the homePanel screen
	 */
	public void visualButtonClicked() {
		setScreenVisibility(false,false, true,  false);
	}

	/**
	 * Button for going to the homePanel screen
	 */
	public void aboutButtonClicked() {
		setScreenVisibility(false, false, false,  false);
	}

	/**
	 * Button for going to the homePanel screen
	 */
	public void contactButtonClicked() {
		setScreenVisibility(false,false, false,  true);
	}
	
	/**
	 * Button for going to the info screen
	 */
	public void infoButtonClicked() {
		setScreenVisibility(false,true, false,  false);
	}

	// ***********************************************
	// Visuali Main page.
	// The following methods are called on the page that handles selecting a csv
	// file.
	// ************************************************
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
	 * It populates the table and shows it. Hence, choosing a locally selected
	 * file.
	 */
	public void handleCSVFileSelectedFromComboBoxButton() {
		// String of the filename selected
		csvFileNameSelected = chooseCSVFileComboBox.getValue();
		// System.out.println(csvFileNameSelected);
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
				// now create a csvReader object using the data
				if (isFilePath) {
					// its a filepath hence;
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
			// visualise the correct pages.

			dataTypeOKCheckBox.setSelected(false);
			singleColumnSelectedCheckBox.setSelected(false);
			twoColumnsCheckBox.setSelected(false);
			checkBoxBarChart.setSelected(false);
			checkBoxPieChart.setSelected(false);
			checkBoxScatterChart.setSelected(false);
			latLongCheckBox.setSelected(false);
			possibleToGenerateMapCheckBox.setSelected(false);

			visualiQuestionPanel.setVisible(false);
			IntelligenceAndTableVisualiserPanel.setVisible(true);
		});

		pForm.getDialogStage().show();
		// Start the thread
		Thread thread = new Thread(task);
		thread.start();
	}

	// -----------------------
	// --Table + Intelligence--
	// ------------------------
	/**
	 * This method is used when a user wants to return to the previous page in
	 * order to upload another file.
	 */
	public void handleBackToCSVFileSelectorPage() {
		// hides the panel that shows the table panel.
		IntelligenceAndTableVisualiserPanel.setVisible(false);
		dataTypeCol1Label.setVisible(false);
		dataTypeSelectorCBTN.setVisible(false);
		advancedAnalysesButton.setVisible(false);
		// lets you reset the table
		isTableCreated = false;
		// shows the panel that lets you select a csv file
		visualiQuestionPanel.setVisible(true);
		// Clear tables.
		tableVisualiPage.getItems().clear();
		tableVisualiPage.getColumns().clear();
		// undisable the graph button
		createTableButton.setDisable(false);
		// disable buttons on the previous page
		columnSelectorCBTN.setDisable(true);
		secondColumnSelectorCBTN.setDisable(true);
		//hide buttons that show data type
		col1DataTypeLabel.setVisible(false);
		col2DataTypeLabel.setVisible(false);
	}

	/**
	 * This is the handler for the creation of a table. It allows user to
	 * visualise the data they want to analyse.
	 */
	public void handleTableCreationButton() {
		if (!(isTableCreated)) {
			System.out.println("Creating table...");
			tableVisualiPage.getItems().clear();
			// create table for the first time
			createTableFromData();
			// table has now been created
			isTableCreated = true;
			// show the generated table.
			tableVisualiPage.setVisible(true);
			// method to bring headers into combobox to be selectable.
			setHeadersInCol1ComboBox();
			// we want to undisable the first column button selector.
			columnSelectorCBTN.setDisable(false);
			columnSelectorLabel.setDisable(false);
		} else {
			// disable creation of new table button.
			createTableButton.setDisable(true);
			// create a pop up telling someone they have already created a
			// table.
			createWarningDialog("You have already created a table from your data");
		}
	}

	/**
	 * When a user selects the firstColSelector CMBXBTN, then three things can
	 * happen: (1) The column could be empty/null. (2) The column can be
	 * detected to be a coordinate (3) The column could be an accepted data
	 * type. (String/Double/Int).
	 * 
	 * Steps this method takes:
	 * <ul>
	 * <li>1. Column is selected, so we save the column header name +
	 * location.</li>
	 * <li>2. we check the data type.</li>
	 * <ul>
	 * <li>2.1 if there is only 1 data type = success</li>
	 * <li>2.2 if more than one data type, mention it to the user, and let them
	 * decide which data type they want.</li>
	 * </ul>
	 * </ul>
	 */
	public void handleFirstColumnSelection() {
		// Sets the name and location of the first column selected
		setNameAndLocationOfFirstColumn();
		// analyses the data type of the column selected.
		dataCheckForFirstCol();
	}

	/**
	 * This is called the moment a column has been selected from our
	 * pre-uploaded table. This method takes the value of the selected column,
	 * and finds out the type of that column, hence determining the data type of
	 * the actual data.
	 */
	private void setNameAndLocationOfFirstColumn() {
		// store chosen name of column selected from within combobox.
		firstColumnName = columnSelectorCBTN.getValue().toString();
		// store location of column chosen, to be used to id the column.
		firstColIndex = readIn.findColumnLocation(firstColumnName);
		// This gets the column data that we want and returns firstColumn
		// String[].
		firstColumn = DataFormatFX.getColumnData(firstColIndex, columnData);
		// create a dataDetector object based on the selected column.
		firstColDataTypeDetected = new DataDetector(firstColumn);
	}

	/**
	 * just check if the data type within the column is:
	 * <ul>
	 * <li>1. if firstCol is empty</li>
	 * <li>2. if firstCol is of one type</li>
	 * <li>3. if more than 1 type - opens up the datatype selector button!</li>
	 * </ul>
	 */
	private void dataCheckForFirstCol() {
		// if Column == empty
		if (firstColDataTypeDetected.isColumnEmpty()) {
			createWarningDialog("Column is empty - Please select another column!");
			firstColAccepted = false;
			return;
		} else {
			// Column is not empty, hence check if we can accept it.
			// if column == weird format
			if (!(rulesForColumn1Selector())) {
				// it doesnt abide by the rules
				createWarningDialog("Please select another column. This format is not supported.");
				firstColAccepted = false;
				return;
			}
			// if column == GOOD
			if (firstColDataTypeDetected.contains1DataType()) {
				System.out.println("Column has been accepted.");
				// hence contains one data type only. we are all set.
				setUpDataTypeOfCol1Label(false);
				// boolean indicating that we have accepted first column
				firstColAccepted = true;
				// select single column selected and datatype recognised
				// checkbox.
				singleColumnSelectedCheckBox.setSelected(true);
				dataTypeOKCheckBox.setSelected(true);
				// Check if column is lat/long/eastings/northings
				checkColLatLong();
				// ----
				// set up second combobox
				setHeadersInCol2ComboBox();
				// 3. second col combobx button
				setupSecondColumnComboBX();
				secondColumnSelectorCBTN.setDisable(false);
				// 4. disable first column selector button
				// columnSelectorCBTN.setDisable(true);
				// 5. undisable graph generator button
				generationButton.setDisable(false);
				col1DataType = firstColDataTypeDetected.printDataTypes();
				showColumnsDataTypes();
				col1DataTypeLabel.setVisible(true);
				// set up the graphs checkboxes
				if (firstColDataTypeDetected.containsString()) {
					setUpBooleanForColumn1(true, false, false);
					setUpCheckBoxesForGraphs(true, true, false);
				} else if (firstColDataTypeDetected.containsDouble()) {
					setUpBooleanForColumn1(false, true, false);
					setUpCheckBoxesForGraphs(false, false, true);
					advancedAnalysesButton.setVisible(true);
				} else if (firstColDataTypeDetected.containsInt()) {
					setUpBooleanForColumn1(false, false, true);
					setUpCheckBoxesForGraphs(false, false, true);
					advancedAnalysesButton.setVisible(true);
				}
			} else {
				System.out.println("column has more than 1 datatype.");
				// if column == more than 1 datatype
				firstColAccepted = false;
				// fill other combo box with data types available
				dataTypeSelectorCBTN.getItems().setAll(firstColDataTypeDetected.typesContained());
				// alert the user
				createWarningDialog("More than one data type detected - Please select one data type!");
				// has more than one data type. Ask user to choose a data type.
				setUpDataTypeOfCol1Label(true);
			}
		}
	}

	private void setUpCheckBoxesForGraphs(boolean piechart, boolean barchart, boolean scattergraph) {
		checkBoxPieChart.setSelected(piechart);
		checkBoxBarChart.setSelected(barchart);
		checkBoxScatterChart.setSelected(scattergraph);
	}

	private void setUpBooleanForColumn1(boolean isString, boolean isDouble, boolean isInt) {
		dataTypeCol1IsString = isString;
		dataTypeCol1IsDouble = isDouble;
		dataTypeCol1IsInteger = isInt;
	}

	/**
	 * Creates a dialog WARNING pop up with a chosen warning msg. Comes with one
	 * OK button.
	 * 
	 * @param warningMsg
	 *            warning message to be displayed.
	 */
	private void createWarningDialog(String warningMsg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(warningMsg);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			alert.close();
		} else {
			alert.close();
		}
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
		if (firstColDataTypeDetected.containsString() || firstColDataTypeDetected.containsDouble()
				|| firstColDataTypeDetected.containsInt()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method is called when a column has more than 1 data type. Hence,
	 * depending on the data type selected, we set the data we will be using to
	 * that.
	 */
	public void handleDataTypeSelected() {
		// Lets find out which data type it actually belongs to
		if (dataTypeSelectorCBTN.getValue().toString().equals("Numbers/Integer")) {
			// the first column is integer[]
			firstColSelectedINT = firstColDataTypeDetected.getOnlyInts();
			setDataTypeColumn1(true, true, false, false);
		} else if (dataTypeSelectorCBTN.getValue().toString().equals("Decimals/Double")) {
			// note that the first column is not a string[] and actually doubel.
			firstColSelectedDOUBLE = firstColDataTypeDetected.getOnlyDoubles();
			setDataTypeColumn1(true, false, true, false);
			// Check if column is lat/long/eastings/northings
			checkColLatLong();
		} else if (dataTypeSelectorCBTN.getValue().toString().equals("Words/String")) {
			// note that the first column is String[].
			firstColSelectedSTRING = firstColDataTypeDetected.getOnlyStrings();
			setDataTypeColumn1(true, false, false, true);
		}
		firstColAccepted = true;
		// select single column selected and datatype recognised
		// checkbox.
		singleColumnSelectedCheckBox.setSelected(true);
		dataTypeOKCheckBox.setSelected(true);
		// ----
		// set up second combobox
		setHeadersInCol2ComboBox();
		// 3. second col combobx button
		setupSecondColumnComboBX();
		secondColumnSelectorCBTN.setDisable(false);
		// 4. disable first column selector button
		// columnSelectorCBTN.setDisable(true);
		// 5. undisable graph generator button
		generationButton.setDisable(false);
		// sets up checkboxes depending on selection.
		if (firstColDataTypeDetected.containsString()) {
			setUpBooleanForColumn1(true, false, false);
			setUpCheckBoxesForGraphs(true, true, false);
		} else if (firstColDataTypeDetected.containsDouble()) {
			setUpBooleanForColumn1(false, true, false);
			setUpCheckBoxesForGraphs(false, false, true);
			advancedAnalysesButton.setVisible(true);
		} else if (firstColDataTypeDetected.containsInt()) {
			setUpBooleanForColumn1(false, false, true);
			setUpCheckBoxesForGraphs(false, false, true);
			advancedAnalysesButton.setVisible(true);
		}
	}

	/**
	 * helper method that notifies the machine if the columns data type is
	 * different.
	 * 
	 * @param datatypeChanged
	 *            has more than one data type been found?
	 * @param isInt
	 *            int?
	 * @param isDouble
	 *            double?
	 * @param isString
	 *            string?
	 */
	private void setDataTypeColumn1(Boolean datatypeChanged, Boolean isInt, Boolean isDouble, Boolean isString) {
		// Datatype has changed?
		dataTypeChangedCol1 = datatypeChanged;
		// Datatype String?
		dataTypeCol1IsString = isString;
		checkBoxPieChart.setSelected(isString);
		// DataType Double?
		dataTypeCol1IsDouble = isDouble;
		// DataType Int?
		dataTypeCol1IsInteger = isInt;
		// sets the string of the datatype here.
		col1DataType = dataTypeSelectorCBTN.getValue().toString();
		// reveals the data type on a label on the gui
		showColumnsDataTypes();
	}

	/**
	 * Method shows the datatype of the first column on a label on the gui.
	 */
	private void showColumnsDataTypes() {
		if (!col1DataType.isEmpty()) {
			col1DataTypeLabel.setText("First columns data type: " + col1DataType);
			col1DataTypeLabel.setVisible(true);
		} else {
			col1DataTypeLabel.setVisible(false);
		}

	}

	/**
	 * This method will allow for a dialog to tell the user more information
	 * about the column selected.
	 */
	public void handleAnalyseFileSelectedBtn() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information about the file.");
		alert.setHeaderText("Information gathered:");
		String toDisplay = "Name of File: " + csvFileNameSelected + ".\n" + "Name of column selected: "
				+ firstColumnName + ".\n" + "Number of lines: " + firstColumn.length + ".\n" + "Columns data type: "
				+ firstColDataTypeDetected.printDataTypes() + ".\n";
		alert.setContentText(toDisplay);
		alert.showAndWait();
	}

	public void handleAdvancedAnalysis() {
		System.out.println("Analysin file...");
		ProgressForm pForm = new ProgressForm();
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {
				// Work out max and min of String

				AdvancedAnalysis advanceAnalysis = new AdvancedAnalysis(firstColDataTypeDetected);
				toDisplayAdvanceInfo = advanceAnalysis.toString();
				updateProgress(10, 10);
				return null;
			}
		};

		pForm.activateProgressBar(task);
		System.out.println("came out of the thread for analysing file");
		task.setOnSucceeded(event -> {
			pForm.getDialogStage().close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Advanced column analysis.");
			alert.setTitle("More column info.");

			String toDisplay = toDisplayAdvanceInfo;
			alert.setContentText(toDisplay);
			alert.showAndWait();

		});

		pForm.getDialogStage().show();

		Thread thread = new Thread(task);
		thread.start();
	}

	/**
	 * Method shows the datatype of the second column on a label on the gui.
	 */
	private void showSecondColumnDataTypes() {
		if (!col2DataType.isEmpty()) {
			col2DataTypeLabel.setText("Second columns data type: " + col2DataType);
			col2DataTypeLabel.setVisible(true);
		} else {
			col2DataTypeLabel.setVisible(false);
		}

	}

	/**
	 * This method sets up the labels within the second combobox. It makes sure
	 * to remove the header selected from the first column.
	 */
	private void setupSecondColumnComboBX() {
		int tempCount = 0;
		ArrayList<String> tempHeaders = new ArrayList<>();
		// go over all the remaining
		for (String[] columnData : columnData) {
			secondColDataTypeDetected = new DataDetector(columnData);
			if (secondColDataTypeDetected.contains1DataType() && secondColDataTypeDetected.containsDouble()) {
				// hence its only made of doubles
				tempHeaders.add(headerData[tempCount]);
			}
			tempCount++;
		}
		secondColumnSelectorCBTN.getItems().addAll(tempHeaders);
	}

	/**
	 * This method handles when the second column is selected. <b>It can only
	 * select columns that are doubles!</b>
	 */
	public void handleSecondColumnSelection() {
		// cant be a piechart now.
		checkBoxPieChart.setSelected(false);
		secondColUsed = true;
		// set name + location of second column header.
		setNameAndLocationOfSecondColumn();
		// show to the user they have selected two columns.
		twoColumnsCheckBox.setSelected(true);
		col2DataType = secondColDataTypeDetected.printDataTypes();
		showSecondColumnDataTypes();
		col2DataTypeLabel.setVisible(true);
		// if first columns not coordinate or string, must be scatter graph
		if ((!dataTypeCol1IsString) && (!firstColumnIsLatOrEasts)) {
			checkBoxScatterChart.setSelected(true);
		}
	}

	/**
	 * Method decides, depending on the columns selected, on which graph to
	 * create. The options include:
	 * <ul>
	 * <li>A map.</li>
	 * <li>A Pie Chart</li>
	 * <li>A Bar Chart</li>
	 * <li>A Scatter Chart</li>
	 */
	public void handleGenerateMapOrGraph() {

		// If Longitude/Latitude values selected
		if (latLongCheckBox.isSelected() || canCreateMap) {
			System.out.println("creating a map...");
			createWorldMap();
		} else if ((!secondColUsed) || checkBoxPieChart.isSelected()) {
			// If pie chart wanted
			if (checkBoxBarChart.isSelected()) {
				// and if bar chart box has been selected
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Chart selection");
				alert.setContentText("Which chart would you like?");
				ButtonType okButton = new ButtonType("Bar Chart");
				ButtonType noButton = new ButtonType("Pie Chart");
				alert.getButtonTypes().setAll(okButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == okButton) {
					// want a barchart...
					alert.close();
					System.out.println("Creating a bar chart...");
					IntelligenceAndTableVisualiserPanel.setVisible(false);
					barChartVisualiPanel.setVisible(true);
					// means only string selected
					barChart.setData(ObservableListProvider.generateBarChartListFromString(firstColumn));
					createdBar = true;
				} else {
					alert.close();
					// means we must generate pie chart
					System.out.println("create pie");
					createPieChart();
					createdPie = true;
				}

			}

		} else if (secondColUsed) {
			// For barchart: col1=String, col2=int
			if (dataTypeCol1IsString) {
				// must be barchart
				System.out.println("create bar");
				createBarChart(); 
				createdBar = true;
			} else {
				// must be a scatter graph
				createScatterChart();
				createdScatter = true;
			}
		}
	}

	private void createPieChart() {
		// hide the current page.
		IntelligenceAndTableVisualiserPanel.setVisible(false);
		// create observable list creator
		ObservableListProvider listProvider = new ObservableListProvider(readIn, firstColIndex);
		// create chart depending on selected chart.
		pieChart.setData(listProvider.getPieChartObservableList());
		
		pieChartVisualiPanel.setVisible(true);
	}

	/**
	 * This method is called and creates a world map in an outside scene.
	 */
	private void createWorldMap() {
		ProgressForm pForm = new ProgressForm();
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {

				longtitudeDoubles = ArrayListCreator.stringArrayToDouble(firstColumn);
				latitudeDoubles = ArrayListCreator.stringArrayToDouble(secondColumn);
				
				updateProgress(10, 10);
				return null;
			}
		};

		pForm.activateProgressBar(task);
		System.out.println("came out of the thread for creating map");
		task.setOnSucceeded(event -> {
			pForm.getDialogStage().close();

			// hide panel
			int maxPoints = (int) mapSlider.getValue();
			System.out.println("slider is at: " + maxPoints);
			mapViewObj = new MapCreator(latitudeDoubles, longtitudeDoubles, maxPoints);
			mapView = mapViewObj.getMapView();

			// IntelligenceAndTableVisualiserPanel.setVisible(false);

			mapStage = new Stage();
			Scene mapScene = new Scene(new BorderPane(mapView), 700, 500);
			mapStage.setScene(mapScene);
			mapStage.show();

		});

		pForm.getDialogStage().show();

		Thread thread = new Thread(task);
		thread.start();

	}

	/**
	 * Button to take you back from any CHART.
	 */
	public void backFromGraphButton() {
		// hide all panels
		pieChartVisualiPanel.setVisible(false);
		barChartVisualiPanel.setVisible(false);
		scatterChartVisualiPanel.setVisible(false);
		// clear all graphs
		if (createdPie) {
			pieChart.getData().clear();
		}
		if (createdBar) {
			barChart.getData().clear();
		}
		if (createdScatter) {
			scatterChart.getData().clear();
		}
		// show our back page.
		IntelligenceAndTableVisualiserPanel.setVisible(true);
	}

	private void createBarChart() {
		// add it all to our barchart
		IntelligenceAndTableVisualiserPanel.setVisible(false);
		barChartVisualiPanel.setVisible(true);
		// X AXIS is always string
		// IF Y AXIS == INT

		if (secondColDataTypeDetected.containsInt()) {
			System.out.println("contains ints....");
			// assume Y AXIS == INT
			xAxisBarChart.setLabel("x axis");
			yAxisBarChart.setLabel("y axis");
			barChart.setTitle("title");

			ProgressForm pForm = new ProgressForm();
			Task<Void> task = new Task<Void>() {
				@Override
				public Void call() throws InterruptedException {
					// create series list for the barchart.
					barChartSeries = new XYChart.Series();
					Integer[] secondColData = secondColDataTypeDetected.getOnlyInts();
					// find max size for looping
					int maxSize = 0;
					if (firstColumn.length > secondColData.length) {
						maxSize = secondColData.length;
					} else {
						maxSize = firstColumn.length;
					}
					for (int i = 0; i < maxSize; i++) {
						String x1 = firstColumn[i];
						Integer y1 = secondColData[i];
						barChartSeries.getData().add(new XYChart.Data(x1, y1));
					}
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
				barChart.getData().add(barChartSeries);
			});
			pForm.getDialogStage().show();
			Thread thread = new Thread(task);
			thread.start();
		} else {
			System.out.println("contains doubles");
			// assume Y AXIS == Double
			xAxisBarChart.setLabel("x axis");
			yAxisBarChart.setLabel("y axis");
			barChart.setTitle("title");

			ProgressForm pForm = new ProgressForm();
			Task<Void> task = new Task<Void>() {
				@Override
				public Void call() throws InterruptedException {
					// create series list for the barchart.
					barChartSeries = new XYChart.Series();
					Double[] secondColData = secondColDataTypeDetected.getOnlyDoubles();
					// find max size for looping
					int maxSize = 0;
					if (firstColumn.length > secondColData.length) {
						maxSize = secondColData.length;
					} else {
						maxSize = firstColumn.length;
					}
					for (int i = 0; i < maxSize; i++) {
						String x1 = firstColumn[i];
						Double y1 = secondColData[i];
						barChartSeries.getData().add(new XYChart.Data(x1, y1));
					}
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
				barChart.getData().add(barChartSeries);
			});
			pForm.getDialogStage().show();
			Thread thread = new Thread(task);
			thread.start();
		}
		
	}

	/**
	 * helper method to convert int[] -> double[]
	 */
	private Double[] copyFromIntArray(Integer[] source) {
		Double[] dest = new Double[source.length];
		for (int i = 0; i < source.length; i++) {
			dest[i] = source[i].doubleValue();
		}
		return dest;
	}

	private void createScatterChart() {
		IntelligenceAndTableVisualiserPanel.setVisible(false);
		scatterChartVisualiPanel.setVisible(true);

		System.out.println("Creating a scatter chart....");
		xAxisScatterChart.setLabel("x axis");
		yAxisScatterChart.setLabel("y axis");
		
		ProgressForm pForm = new ProgressForm();
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {
				// create series list for the barchart.
				scatterChartSeries = new XYChart.Series();
				Double[] secondColData = secondColDataTypeDetected.getOnlyDoubles();
				// find max size for looping
				int maxSize = 0;
				if (firstColumn.length > secondColData.length) {
					maxSize = secondColData.length;
				} else {
					maxSize = firstColumn.length;
				}
				for (int i = 0; i < maxSize; i++) {
					String x1 = firstColumn[i];
					Double y1 = secondColData[i];
					scatterChartSeries.getData().add(new XYChart.Data(x1, y1));
				}
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
			scatterChart.getData().add(scatterChartSeries);
			
		});
		pForm.getDialogStage().show();
		Thread thread = new Thread(task);
		thread.start();
	}

	// =====================================================================
	// -------------------------------------------------------------------------------------------------
	// =====================================================================

	/**
	 * This method shows/hides the data type of col#1 selector.
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

	private void setNameAndLocationOfSecondColumn() {
		secondColumnName = secondColumnSelectorCBTN.getValue().toString();
		secondColIndex = readIn.findColumnLocation(secondColumnName);
		secondColumn = DataFormatFX.getColumnData(secondColIndex, columnData);
		secondColDataTypeDetected = new DataDetector(secondColumn);
	}

	/**
	 * Checks if the column selected has lat/long/northings/eastings in the
	 * name. Asks user if it is in the right order: Longtitude or Easting!
	 */
	private void checkColLatLong() {
		// check if it is lat/long
		if (DataDetector.latLongDetector(firstColumnName) || DataDetector.northingEastingDetector(firstColumnName)) {
			// Alert user that we have found that they are trying to create a
			// map.
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Coordinates detected...");
			alert.setContentText("Selected Longtitude or Eastings (In that order)?");
			ButtonType okButton = new ButtonType("Yes");
			ButtonType noButton = new ButtonType("No");
			alert.getButtonTypes().setAll(okButton, noButton);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == okButton) {
				// Means it is a coordinate.
				firstColumnIsLatOrEasts = true;
				latLongCheckBox.setSelected(true);
				mapSlider.setVisible(true);
				canCreateMap = true;
			} else {
				// wrongly identified as coordinate
				latLongCheckBox.setSelected(false);
				firstColumnIsLatOrEasts = false;
				alert.close();
			}
		} else {
			// its not lat/long - safety net.
			mapSlider.setVisible(false);
			firstColumnIsLatOrEasts = false;
			latLongCheckBox.setSelected(false);
			canCreateMap = false;
		}
	}

	/**
	 * This method sets the column names within the combobox.
	 * 
	 * @param noDataSelected
	 *            if true set all the names. if false, set certain names.
	 */
	public void setHeadersInCol1ComboBox() {
		columnSelectorCBTN.getItems().setAll(headerData);
	}

	/**
	 * if true, this sets headers within the second column selector.
	 * 
	 * @param noDataSelected
	 */
	public void setHeadersInCol2ComboBox() {
		// set data within second column
		// removing selected first column header label as an option
		final List<String> list = new ArrayList<String>();
		Collections.addAll(list, headerData);
		list.remove(firstColumnName);
		headerDataSecondColumn = list.toArray(new String[list.size()]);
		secondColumnSelectorCBTN.getItems().setAll(headerDataSecondColumn);
	}

	/**
	 * method for deciding what to show up depending on the selection.
	 * <ul>
	 * <li>If the user wants to upload a new file.</li>
	 * <li>If the user wants to choose an existing file.</li>
	 * </ul>
	 * 
	 * @param isUpload
	 *            true = if user wants to upload new file. false = if user wants
	 *            to check a saved file
	 */
	public void setUpToggleFORCSVDecider(Boolean isUpload) {
		if (isUpload) {
			UploadNewCSVFileButton.setVisible(true);
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
	 * Method to decide which screen to show
	 * 
	 * @param homeScreenVisible
	 * @param visualPanelVisible
	 * @param contactPanelVisible
	 */
	public void setScreenVisibility(Boolean homeScreenVisible, Boolean infoScreenVisible, Boolean visualPanelVisible,
			Boolean contactPanelVisible) {
		
		homePanel.setVisible(homeScreenVisible);
		infoPanel.setVisible(infoScreenVisible);
		visualPanel.setVisible(visualPanelVisible);
		contactPanel.setVisible(contactPanelVisible);

		homePanelButton.setUnderline(homeScreenVisible);
		infoPanelButton.setUnderline(infoScreenVisible);
		visualPanelButton.setUnderline(visualPanelVisible);
		contactPanelButton.setUnderline(contactPanelVisible);

		if (!visualPanelVisible) {
			// removeChartData();
		}
		// setUpChartData();
	}

	// ================================
	// Code that handles the information page.
	// ================================
	
	public void hideBeautyPanel() {
		setSmartCityPages(false, false, false, false);
		infoPanel.setVisible(true);
	}
	
	private void setSmartCityPages(boolean history, boolean future, boolean keypeople, boolean me) {
		historyPanel.setVisible(history);
		futurePanel.setVisible(future);
		peoplePanel.setVisible(keypeople);
		mePanel.setVisible(me);
	}
	
	public void openHistoryPanel() {
		setSmartCityPages(true, false, false, false);
	}
	public void openFutureyPanel() {
		setSmartCityPages(false, true, false, false);
	}
	public void openKeyPeoplePanel() {
		setSmartCityPages(false, false, true, false);
	}
	public void openMePanel() {
		setSmartCityPages(false, false, false, true);
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
	
	private void translateCar() {
		TranslateTransition t1 = new TranslateTransition(Duration.millis(8000));
		t1.setNode(carIcon);
		t1.setFromX(300);
		t1.setToX(-1700);
		t1.setCycleCount(Timeline.INDEFINITE);
		t1.play();
	}
	
	/**
	 * Method has been created to create an animation sequence for the use of an animated sequence.
	 * It calls the various images in order and establishes an order that they should be displayed.
	 * As they come in, they are faded into the screen, and subsequently faded out.
	 */
	private void translationIcons() {
		//Icon 1s transition
		
		FadeTransition t1 = new FadeTransition(Duration.millis(2500));
		t1.setNode(icon1);
		//t1.setFromValue(1.0);
		//t1.setToValue(0.0);
		//t1.setAutoReverse(true);
		
		FadeTransition t2 = new FadeTransition(Duration.millis(5000));
		t2.setNode(icon2);
		t2.setFromValue(0.0);
		t2.setToValue(1.0);
		t2.setAutoReverse(true);
		
		FadeTransition t3 = new FadeTransition(Duration.millis(5000));
		t3.setNode(icon3);
		t3.setFromValue(0.0);
		t3.setToValue(1.0);
		t3.setAutoReverse(true);
		
		FadeTransition t4 = new FadeTransition(Duration.millis(5000));
		t4.setNode(icon4);
		t4.setFromValue(0.0);
		t4.setToValue(1.0);
		t4.setAutoReverse(true);
		
		FadeTransition t5 = new FadeTransition(Duration.millis(5000));
		t5.setNode(icon5);
		t5.setFromValue(0.0);
		t5.setToValue(1.0);
		t5.setAutoReverse(true);
		
		FadeTransition t6 = new FadeTransition(Duration.millis(5000));
		t6.setNode(icon6);
		t6.setFromValue(0.0);
		t6.setToValue(1.0);
		t6.setAutoReverse(true);
		
		
		FadeTransition t7 = new FadeTransition(Duration.millis(2500));
		t7.setNode(icon1);
		t7.setFromValue(0.0);
		t7.setToValue(1.0);
		
		SequentialTransition st = new SequentialTransition(t1,t2, t3, t4, t5, t6);
		st.setInterpolator(Interpolator.LINEAR);
		st.setCycleCount(Timeline.INDEFINITE);
		st.play();
		
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

	public void handleSplashScreen() {
		FadeTransition ft = new FadeTransition(Duration.millis(3000), splashScreenPanl);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		splashScreenPanl.setDisable(true);

		allScreensPanels.setDisable(false);
	}
	
	/**
	 * This method handles the music, playing or pausing it.
	 */
	public void handleMusic() {
		if (isMusicPaused) {
			//music is paused
			playMusic.playMusic();
			isMusicPaused = false;
		} else {
			//means is currently playing
			playMusic.pauseMusic();
			isMusicPaused = true;
		}
	}

}
