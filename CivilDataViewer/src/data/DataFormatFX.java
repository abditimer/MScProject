package data;

import java.util.ArrayList;
import java.util.Arrays;
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
 * This class creates an object around the csv file.
 * It allows user to set a column it wants;
 * this converts that column into an array of a certain type
 * 
 * @author timer
 *
 */
public class DataFormatFX {

	/**
	 * This allows you to get a string[] of a certain column
	 * 
	 * @param colNumber the column you need to extract
	 * @return a column from the csv file as a string[]
	 */
	public static String[] getColumnData(int colNumber, List<String[]> columnData) {
		//create arraylist to store values of change
		List<String> a = new ArrayList<>();
		
		//parameter: column number of info I want returned as String[]
		
		//for each row of data, get the colNumbers value
		//loop over our existing data set
		for (String[] singleLineFromArray : columnData) {
			//for each String[], get the colNumberth String value.
			String wantedColInfo = singleLineFromArray[colNumber];
			a.add(wantedColInfo);
			
		}
		String[] toReturn = a.toArray(new String[0]);
		
		return toReturn;
	}
	
	/**
	 * This method prints the different headers this file has
	 */
	public static void printHeaders(String[] headerData) {
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
	public static void printHeadersLocations(String[] headerData) {
		int count = 0;
		for (String colName : headerData) {
			System.out.println(colName + count);
			count++;
		}	
		
	}
	/**
	 * This method counts the repeating strings in one column
	 * It collects the instances of the words in a column.
	 * 
	 * Only useful for strings.
	 * 
	 * @return A map containing the unique words with their count
	 */
	public static Map<String, Integer> countAndMapData(String[] col) {
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
	
	public static void printMapData(Map<String, Integer> a) {
		
		System.out.println("Number of keys is: " + a.size());
		for (Map.Entry<String, Integer> entry : a.entrySet()) {
		    System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		
		
	}
}
