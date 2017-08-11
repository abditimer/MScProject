package csvReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * This class creates a table object that populates a TableView.
 * It reads in data from a CSV file and populates the table.
 * 
 * What I need this class to do:
 * 1. take in a csv file name
 * 2. read it 
 * 3. turn data into StringProperty
 * 4. put this data into observable list
 * 5. create methods that let someone create a table.
 * 
 * 
 * @author timer
 *
 */
public class DynamicTable {
	/**
	 * This is the table that will be created
	 */
	private TableView<ObservableList<StringProperty>> table;
	//List containing all the rows from csv file except for header
	private List<String[]> columnData;
	//String array of the headers
	private String[] headerData;
	
	public DynamicTable( TableView<ObservableList<StringProperty>> table, String[] headerData, List<String[]> columnData) {
		this.table = table;
		this.columnData = columnData;
		this.headerData = headerData;
		populateTable();
		System.out.println("DynamicTable object has been created.");
	}

	private void populateTable() {
		System.out.println("populating table method has started...");
		table.getItems().clear();
		table.getColumns().clear();
		table.setPlaceholder(new Label("Loading..."));
		
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
							table.getColumns().add(tempCol);
						}
						System.out.println("created headers...");
					}
				});
				
				for (String[] row : columnData)
				
				Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// Add additional columns if necessary:
							for (int i = table.getColumns().size(); i < row.length; i++) {
								table.getColumns().add(createColumn(i, ""));
							}
							
							
							// Add data from the line to the table column
							//create a list
							ObservableList<StringProperty> data = FXCollections.observableArrayList();
							//each column data, add it to the list.
							for (String value : row) {
								data.add(new SimpleStringProperty(value));
							}
							//add list to our table.
							table.getItems().add(data);
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

	
	
	public TableView<ObservableList<StringProperty>> getTable() {
		return table;
	}



}

