package csvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVHeaderSelector extends CSVReader {
	CSVReader reader;
	
	public CSVHeaderSelector(String fileLocation, int lineLimit) {
		super(fileLocation, lineLimit);
	}
	
	public static void main(String[] args) {
		CSVHeaderSelector test = new CSVHeaderSelector("resources/excelFiles/Pothole_Enquiries_2015.csv", 1);
		test.printCSVFile();
	}
}
