package csvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			BufferedReader readIn = new BufferedReader(new FileReader("resources/excelFiles/" + filename));
			//BufferedReader readIn = new BufferedReader(new FileReader("resources/excelFiles/" + filename + ".csv"));
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
	 * Reads CSV file and stores its content
	 * @param fileLocation Only use this if its a complete filepath
	 */
	public CSVReader(String filePath, Boolean filepath) {
		columnData = new ArrayList<>(); 
		
		try {
			BufferedReader readIn = new BufferedReader(new FileReader(filePath));
			//BufferedReader readIn = new BufferedReader(new FileReader("resources/excelFiles/" + filename + ".csv"));
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
			BufferedReader readIn = new BufferedReader(new FileReader("resources/excelFiles/" + filename));
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
	
/*	*//**
	 * This allows you to get a string[] of a certain column
	 * 
	 * @param colNumber the column you need to extract
	 * @return a column from the csv file as a string[]
	 *//*
	public String[] getColumnData(int colNumber) {
		//create arraylist to store values of change
		List<String> a = new ArrayList<>();
		
		
		//parameter: column number of info I want returned as String[]
		
		//for each row of data, get the colNumbers value
		//loop over our existing data set
		for (String[] singleLineFromArray : getData()) {
			//for each String[], get the colNumberth String value.
			String wantedColInfo = singleLineFromArray[colNumber];
			System.out.println(wantedColInfo);
			a.add(wantedColInfo);
			
		}
		
		
		
		
		
		String[] toReturn = a.toArray(new String[0]);
		
		return toReturn;
	}*/
	
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
	 * @return 
	 */
	public int countLinesInCSVFile() {
		int count =0;
		for (String[] singleLineFromArray : getData()) {
			count++;
		}
		return count;
	}
	
	/**
	 * This method prints the different size lengths 
	 * of the different rows within the csv file
	 */
	public void printLineLengths() {
		Set<Integer> setOfNumbers = new HashSet<>();
		
		
		for (String[] singleLineFromArray : getData()) {
			//for each row
			setOfNumbers.add(singleLineFromArray.length);
		}
		
		for ( int a : setOfNumbers) {
			System.out.println(a);
		}
	}
	
	/**
	 * This method finds the location of the header array.
	 * 
	 * @param columnName
	 * @return location of column in our data[]
	 */
	public int findColumnLocation(String columnName) {
		int locationNo = 0;
		
		for (String header : headerData) {
			if (header.equals(columnName)) {
				break;
			} else {
				locationNo++;
			}
		}
		
		return locationNo;
	}
	
	
	public static void main(String[] args) {
		CSVReader test = new CSVReader("Pothole_Enquiries_2015.csv");
		//CSVReader rainData = new CSVReader("RainTemp.csv");
		//CSVReader waterData = new CSVReader("WaterTemperature.csv");
		//test.printLineLengths();
		//System.out.println(Arrays.toString(test.getHeader()));
		
		//System.out.println(Arrays.toString(test.getColumnData(0)));
		//System.out.println(Arrays.toString(waterData.getHeader()));
		//test.countLinesInCSVFile();
		
		
	}
}
