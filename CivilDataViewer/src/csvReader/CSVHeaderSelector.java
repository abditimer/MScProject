package csvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This file determines the headers that want to be used
 * @author timer
 *
 */
public class CSVHeaderSelector extends CSVReader {
	String[] header;
	CSVReader reader;
	
	public CSVHeaderSelector(String fileLocation, int lineLimit) {
		super(fileLocation, 1);
		printCSVFile();
	}
	
	
	
	public static void main(String[] args) {
		CSVHeaderSelector test = new CSVHeaderSelector("Pothole_Enquiries_2015", 1);
		CSVHeaderSelector test2 = new CSVHeaderSelector("rainTemp", 1);
		CSVHeaderSelector test3 = new CSVHeaderSelector("WaterTemperature", 1);
	}
}
