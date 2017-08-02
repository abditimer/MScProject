package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import csvReader.CSVReader;
import dataDownloader.PotholeEnquiry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * This class will:
 * 1. Take a csv file and pass it through my csv reader
 * 2. Now we have access to the header and table content
 * 3. save the content in this file
 * 4. print headers with number infront of them
 * 4.5 String[] to Int[]
 * 5.1 method will let you save 1 set of data mapped to its name
 * 5.2 method will let you save 2 sets of data mapped to its name
 * 6.create graphs with this data
 * 
 * @author timer
 *
 */
public class DataToJFXGraph {
	CSVReader reader;
	//List containing all the rows from csv file except for header
	private List<String[]> columnData;
	//String array of the headers
	private String[] headerData;
	
	public List<String[]> getAllColumnData() {
		return columnData;
	}

	public String[] getHeaderData() {
		return headerData;
	}
	
	public String[] getColumnData(int i) {
		return reader.getColumnData(i);
	}
	
	/**
	 * Uses CSVReader class to read in a csv file
	 * then splits data into header and data content
	 * stores this info
	 * 
	 * @param fileName fileName of CSV file (Exclude .csv)
	 */
	public DataToJFXGraph(String fileName) {
		reader = new CSVReader(fileName);
		columnData = reader.getData();
		headerData = reader.getHeader();
	}
	
	/**
	 * This method prints the different headers this file has
	 */
	public void printHeaders() {
		int count = 1;
		System.out.println("These are the different headers you can choose from: " + "\n");
		for (String colName : headerData) {
			System.out.println(count + " " + colName);
			count++;
		}	
	}
	
	/**
	 * This method has been created to turn String []
	 * from Strings -> Integers
	 * This method considers if the string has a decimal point
	 * @param stringArray
	 * @return
	 */
	public ArrayList<Double> stringToIntNumbers(String[] stringArray) {
		ArrayList<Double> listOfDoubles = new ArrayList<>();
		
		
		for (String string : stringArray) {
			
			String[] splitVal = string.split("\\.");
			//if it contains a .
			if (splitVal.length > 1) {
				int integerPart = Integer.parseInt(splitVal[0]);
				int fractionPart = Integer.parseInt(splitVal[1]);
				int sizeOfFrac = splitVal[1].length();
				
				double decimal = integerPart + (fractionPart / Math.pow(10, sizeOfFrac));
				listOfDoubles.add(decimal);
			} else {
				double value = Double.parseDouble(splitVal[0]);
				listOfDoubles.add(value);
			}
		}
		
		return listOfDoubles;
	}
	
	/**
	 * This method counts the repeating strings in one column
	 * It collects the instances of the words in a column
	 * 
	 * @return A map containing the unique words with their count
	 */
	public Map<String, Integer> countAndMapData(String[] words) {
		TreeMap<String, Integer> map = new TreeMap<>();
		
		for (String singleWord : words) {
			if (map.containsKey(singleWord)) {
				//already in our list, so add 1 to its val
				map.put(singleWord, map.get(singleWord) + 1);
			} else {
				//add the word for the first time to our map
				map.put(singleWord, 1);
			}
		}
		
		return map;
	}
	
	
	
	public static void main(String[] args) {
		DataToJFXGraph potholeData = new DataToJFXGraph("Pothole_Enquiries_2015");
		DataToJFXGraph rainTempData = new DataToJFXGraph("RainTemp");
		DataToJFXGraph waterTempData = new DataToJFXGraph("WaterTemperature");
		potholeData.printHeaders();
		rainTempData.printHeaders();
		waterTempData.printHeaders();
	}
}
