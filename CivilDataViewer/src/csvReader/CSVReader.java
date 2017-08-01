package csvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
	private List<String[]> readInCSVFile;
	
	
	/**
	 * Reads CSV file and stores its content
	 * @param fileLocation Location of CSV file to be read
	 */
	public CSVReader(String fileLocation) {
		readInCSVFile = new ArrayList<>(); 
		
		try {
			BufferedReader readIn = new BufferedReader(new FileReader(fileLocation));
			String lineRead;
			while((lineRead = readIn.readLine()) != null) {
				//seperate the file based on commas
				String[] lineReadArray = lineRead.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				readInCSVFile.add(lineReadArray);
			}
			
			readIn.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found, please try to specify another file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was a problem reading the lines of the file");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Reads CSV files but limits number of lines that are stored
	 * @param fileLocation location of file
	 * @param lineLimit number of lines that are needed
	 */
	public CSVReader(String fileLocation, int lineLimit) {
		readInCSVFile = new ArrayList<>(); 
		
		try {
			BufferedReader readIn = new BufferedReader(new FileReader(fileLocation));
			String lineRead;
			int count = 0;
			while((lineRead = readIn.readLine()) != null && (count < lineLimit)) {
				int count2 =0;
				//seperate the file based on commas
				//String[] lineReadArray = lineRead.replaceAll("^[,\\s]+", "").split("[,\\s]+");
				String[] lineReadArray = lineRead.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				
				readInCSVFile.add(lineReadArray);
				
				count++;
			}
			
			readIn.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found, please try to specify another file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was a problem reading the lines of the file");
			e.printStackTrace();
		}
		
	}
	
	public List<String[]> getReadInCSVFile() {
		return readInCSVFile;
	}
	
	public void printCSVFile() {
		for (String[] singleLineFromArray : getReadInCSVFile()) {
			System.out.println(Arrays.toString(singleLineFromArray));
		}
	}
	
	public void printLineSizeCSVFile() {
		for (String[] singleLineFromArray : getReadInCSVFile()) {
			int sizeOfLine = singleLineFromArray.length;
			System.out.println(sizeOfLine);
			
		}
	}
	
	public void countLinesInCSVFile() {
		int count =0;
		for (String[] singleLineFromArray : getReadInCSVFile()) {
			count++;
		}
		System.out.println(count); 
	}
	
	public static void main(String[] args) {
		CSVReader test = new CSVReader("resources/excelFiles/Pothole_Enquiries_2015.csv");
		//test.printLineSizeCSVFile();
		test.printCSVFile();
		//test.countLinesInCSVFile();
	}
}
