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
public class DataFormatFX {
	
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
	/**
	 * Selects certain column data to return
	 * @param i the row number needed
	 * @return string[] of column selected
	 */
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
	public DataFormatFX(String fileName) {
		reader = new CSVReader(fileName);
		columnData = reader.getData();
		headerData = reader.getHeader();
	}
	
	/**
	 * Uses CSVReader class to read in a csv file
	 * then splits data into header and data content
	 * stores this info
	 * 
	 * @param fileName fileName of CSV file (Exclude .csv)
	 */
	public DataFormatFX(String filepath, Boolean filePath) {
		reader = new CSVReader(filepath, true);
		columnData = reader.getData();
		headerData = reader.getHeader();
	}
	
	/**
	 * This method prints the different headers this file has
	 */
	public void printHeaders() {
		int count = 0;
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
		//TODO: FIX THIS
		
		for (String string : stringArray) {
			double doubleValue = Double.parseDouble(string);
			listOfDoubles.add(doubleValue);
			
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
				if (singleWord.length() >3) {
					//add the word for the first time to our map
					map.put(singleWord, 1);
				}
			}
		}
		
		return map;
	}
	
	public void printMapData(Map<String, Integer> a) {
		
		System.out.println("Number of keys is: " + a.size());
		for (Map.Entry<String, Integer> entry : a.entrySet()) {
		    System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		DataFormatFX potholeData = new DataFormatFX("Pothole_Enquiries_2015");
		//DataFormatFX rainTempData = new DataFormatFX("RainTemp");
		//DataFormatFX waterTempData = new DataFormatFX("WaterTemperature");
		//potholeData.printHeaders();
		//rainTempData.printHeaders();
		//waterTempData.printHeaders();
		Map<String, Integer> a = potholeData.countAndMapData(potholeData.getColumnData(5));
		
		potholeData.printMapData(a);
		
		
	}
}
