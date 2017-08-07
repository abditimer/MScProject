package csvReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class will combine the csv files, and provide an easy string[] that one
 * can loop over and easily pass to the other methods.
 * 
 * 
 * @author timer
 *
 */
public class LocalCSVFilesFinder {
	public ObservableList<String> fileNames = FXCollections.observableArrayList();

	public LocalCSVFilesFinder() {
		loadFiles();
		
	}

	/**
	 * This method finds the different files saved the excelFiles folder
	 */
	public void loadFiles() {

		File folder = new File("resources/excelFiles/");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		      if (file.isFile()) {
		        String fileName = file.getName().toString();
		        fileNames.add(fileName);
		      }
		    }
		
	}

	/**
	 * Returns Observable list of strings
	 * 
	 * @return ObservableList<String>
	 */
	public ObservableList<String> getCSVFileNames() {
		return fileNames;
	}

	public static void main(String[] args) {
		LocalCSVFilesFinder test = new LocalCSVFilesFinder();

		for (String a : test.getCSVFileNames()) {
			System.out.println(a);
		}
	}
}
