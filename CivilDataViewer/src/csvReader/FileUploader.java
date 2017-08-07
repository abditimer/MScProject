package csvReader;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.stage.FileChooser;

/**
 * This class creates a popup dailog for the option
 * of uploading a csv file. 
 * It makes sure that the file that is uploaded is a csv file.
 * 
 * @author timer
 *
 */
public class FileUploader {
	
	private String csvFilePath;
	
	/**
	 * This checks that the file that has been uploaded is an excel sheet.
	 */
	public FileUploader() {
		
		FileChooser filechooser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV file", "*.csv");
		filechooser.getExtensionFilters().add(filter);
		
		File fd = filechooser.showOpenDialog(null);
		csvFilePath = fd.getAbsolutePath();
	}
	
	public String getCSVFilePath() {
		return csvFilePath;
	}
	
	public static void main(String[] args) {
		FileUploader a = new FileUploader();
		
	
	}
}
