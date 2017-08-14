package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.naming.StringRefAddr;

import org.hamcrest.core.IsInstanceOf;

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
	//Reader object
	private CSVReader reader;
	//List containing all the rows from csv file except for header
	private List<String[]> columnData;
	//String array of the headers
	private String[] headerData;
	
	//Selected column
	private int selectedColumn;
	
	public CSVReader getReader() {
		return reader;
	}

	public List<String[]> getColumnData() {
		return columnData;
	}
	
	public String[] getHeaderData() {
		return headerData;
	}
	/**
	 * <b>Returns 1 Column</b>
	 * 
	 * Selects certain column data to return
	 * 
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
	public DataFormatFX(CSVReader reader) {
		this.reader = reader;
		columnData = reader.getData();
		headerData = reader.getHeader();
	}
	
	public DataFormatFX(List<String[]> columnData, String[] headerData) {
		this.columnData = columnData;
		this.headerData = headerData;
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
	 * This method prints the different headers this file has
	 */
	public void printHeadersLocations() {
		int count = 0;
		for (String colName : headerData) {
			System.out.println(colName + count);
			count++;
		}	
		
	}
	//-
	//==================================================================================
	//==================================================================================
	//Methods for turning data into required data sets
	//e.g. {"4.4", "3.5"} -> {4.4, 3.5}
	
	
	
	
	//-
	//==================================================================================
	//==================================================================================
	
	/**
	 * This method counts the repeating strings in one column
	 * It collects the instances of the words in a column.
	 * 
	 * Only useful for strings.
	 * 
	 * @return A map containing the unique words with their count
	 */
	public Map<String, Integer> countAndMapData(String[] col) {
		TreeMap<String, Integer> map = new TreeMap<>();
		
		for (String singleWord : col) {
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
		/*CSVReader reader = new CSVReader("accidents.csv");
		DataFormatFX accidentData = new DataFormatFX(reader);
		String[] longtitude = accidentData.getColumnData(3);
		ArrayList<Double> longtitudeDoubles = stringArrayToDouble(longtitude);
		DataDetector detectDoubles = new DataDetector(longtitude);
		//System.out.println(detectDoubles.contains1DataType());
		
		String[] latitude = accidentData.getColumnData(4);
		accidentData.printMapData(accidentData.countAndMapData(longtitude));
		accidentData.printMapData(accidentData.countAndMapData(latitude));
		
		
		//potholeData.printHeaders();
		//accidentData.printMapData(accidentData.countAndMapData(longtitude));
		
		//DataFormatFX rainTempData = new DataFormatFX("RainTemp");
		//potholeData.printHeaders();
		
		
		//DataDetector detect = new DataDetector(columnWanted);
		//System.out.println(detect.contains1DataType());
		//System.out.println(detect.containsDouble());
		
		//ArrayList<Double> dAList = stringArrayToDouble(columnWanted);
		
		
		
		
		//Map<String, Integer> a = potholeData.countAndMapData();
		//potholeData.printMapData(a);
			
		//potholeData.printHeadersLocations();
*/		
	}
}
