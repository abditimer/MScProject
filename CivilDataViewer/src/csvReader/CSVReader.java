package csvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
	//List containing all the rows from csv file except for header
	private List<String[]> columnData;
	//String array of the headers
	private String[] headerData;
	
	
	/**
	 * Reads CSV file and stores its content
	 * @param fileLocation Location of CSV file to be read
	 */
	public CSVReader(String filename) {
		columnData = new ArrayList<>(); 
		
		try {
			BufferedReader readIn = new BufferedReader(new FileReader("resources/excelFiles/" + filename + ".csv"));
			String lineRead;
			int count = 0;
			while((lineRead = readIn.readLine()) != null) {
				String[] lineReadArray = lineRead.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				if (count == 0) {
					headerData = lineReadArray;
				} else {
					columnData.add(lineReadArray);
				}
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
	
	/**
	 * Reads CSV files but limits number of lines that are stored
	 * @param fileLocation location of file
	 * @param lineLimit number of lines that are needed
	 */
	public CSVReader(String filename, int lineLimit) {
		columnData = new ArrayList<>(); 
		
		try {
			BufferedReader readIn = new BufferedReader(new FileReader("resources/excelFiles/" + filename + ".csv"));
			String lineRead;
			int count = 0;
			while((lineRead = readIn.readLine()) != null && (count < lineLimit)) {
				//seperate the file based on commas
				String[] lineReadArray = lineRead.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				
				if (count == 0) {
					headerData = lineReadArray;
				} else {
					columnData.add(lineReadArray);
				}
				
				count++;
			}
			
			readIn.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found." +"\n" + "Please specify the csv file name without the .csv");
			
		} catch (IOException e) {
			System.out.println("There was a problem reading the lines of the file");
			
		}
		
	}
	
	
	
	
	
	
	/**
	 * Getter for csv data
	 * @return
	 */
	public List<String[]> getData() {
		return columnData;
	}
	public String[] getHeader() {
		return headerData;
	}
	
	/**
	 * This prints out the csv file data
	 */
	public void printCSVFile() {
		for (String[] singleLineFromArray : getData()) {
			System.out.println(Arrays.toString(singleLineFromArray));
		}
	}
	/**
	 * This is a helper method to check the size of the arrays produced from the csv reading
	 */
	public void printLineSizeCSVFile() {
		for (String[] singleLineFromArray : getData()) {
			int sizeOfLine = singleLineFromArray.length;
			System.out.println(sizeOfLine);
			
		}
	}
	/**
	 * This method prints the number of lines the csv has
	 */
	public void countLinesInCSVFile() {
		int count =0;
		for (String[] singleLineFromArray : getData()) {
			count++;
		}
		System.out.println(count); 
	}
	
	public static void main(String[] args) {
		CSVReader test = new CSVReader("Pothole_Enquiries_2015");
		//test.printLineSizeCSVFile();
		//test.printCSVFile();
		//test.countLinesInCSVFile();
	}
}
