package csvReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
 * @author timer
 *
 */
public class DynamicTable {

	TableView<ObservableList<StringProperty>> table;
	
	public DynamicTable(String filepath, Boolean itsAFileName) {
		table = new TableView<ObservableList<StringProperty>>();
		System.out.println(2);
		populateTable(table, filepath, itsAFileName);
		System.out.println(3);
	}

	private void populateTable(TableView<ObservableList<StringProperty>> table, String filepath,
			Boolean itsAFileName) {
		System.out.println(4);
		//table.getItems().clear();
		//table.getColumns().clear();

		//table.setPlaceholder(new Label("Loading..."));
		System.out.println(5);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				String fileName = filepath;
				System.out.println(fileName);
				if (itsAFileName) {
					fileName = "resources/excelFiles/" + filepath;
				}

				BufferedReader in = new BufferedReader(new FileReader(fileName));

				final String headerLine = in.readLine();
				System.out.println(headerLine);
				final String[] headerValues = headerLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						for (int column = 0; column < headerValues.length; column++) {
							table.getColumns().add(createColumn(column, headerValues[column]));
							
						}
						System.out.println("running");
					}
				});

				// Data:

				String dataLine;
				while ((dataLine = in.readLine()) != null) {
					System.out.println(dataLine);
					final String[] dataValues = dataLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// Add additional columns if necessary:
							for (int columnIndex = table.getColumns()
									.size(); columnIndex < dataValues.length; columnIndex++) {
								table.getColumns().add(createColumn(columnIndex, ""));
							}
							// Add data to table:
							ObservableList<StringProperty> data = FXCollections.observableArrayList();
							for (String value : dataValues) {
								data.add(new SimpleStringProperty(value));
							}
							table.getItems().add(data);
						}
					});
				}
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	private TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex,
			String columnTitle) {

		TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();

		String title;
		if (columnTitle == null || columnTitle.trim().length() == 0) {
			title = "Column " + (columnIndex + 1);
		} else {
			title = columnTitle;
		}
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

	
	
	public TableView<ObservableList<StringProperty>> getTable() {
		return table;
	}



}

