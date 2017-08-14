package data;

import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import csvReader.CSVReader;

/**
 * Lets us find out the data types in a String[]
 * 
 * @author Timer
 *
 */
public class DataDetector {
	// this is the column data to check
	private String[] columnData;
	// map containing types and number of them
	private HashMap<String, Integer> typesContained;

	// different data types seperated:
	private ArrayList<String> onlyStrings;
	private ArrayList<Integer> onlyInts;
	ArrayList<Double> onlyDoubles;

	public DataDetector(String[] columnData) {
		this.columnData = columnData;
		typesContained = new HashMap<>();

		onlyStrings = new ArrayList<>();
		onlyInts = new ArrayList<>();
		onlyDoubles = new ArrayList<>();

		analyseTypesContained();
	}

	/**
	 * This method checks the column and identifies the number of each type
	 * there is.
	 * 
	 * @param toCheck
	 *            column you want to check
	 */
	public void analyseTypesContained() {
		int itsAnInt;
		double itsADouble;
		String itsAString;

		for (String a : columnData) {
			if (a.equals("")) {
				continue;
			}
			String input = a;

			try {
				// Try - if it is an int
				itsAnInt = Integer.parseInt(input);
				if (typesContained.containsKey("Integer")) {
					// already has an integer
					typesContained.put("Integer", (typesContained.get("Integer") + 1));
				} else {
					// doesnt have an integer
					typesContained.put("Integer", 1);
				}
				onlyInts.add(itsAnInt);
				continue;
			} catch (NumberFormatException e) {
			}

			try {
				// Try - if it is a double
				itsADouble = Double.parseDouble(input);

				if (typesContained.containsKey("Double")) {
					// already has an integer
					typesContained.put("Double", (typesContained.get("Double") + 1));
				} else {
					// doesnt have an integer
					typesContained.put("Double", 1);
				}
				onlyDoubles.add(itsADouble);
				continue;
			} catch (NumberFormatException e) {
			}

			try {
				// Try - if it is a string
				itsAString = input;

				if (typesContained.containsKey("String")) {
					// already has an integer
					typesContained.put("String", (typesContained.get("String") + 1));
				} else {
					// doesnt have an integer
					typesContained.put("String", 1);
				}
				onlyStrings.add(itsAString);
				continue;
			} catch (NumberFormatException e) {
			}

		}
	}

	/**
	 * This method checks the column and identifies the number of each type
	 * there is.
	 * 
	 * @param toCheck
	 *            column you want to check
	 */
	public static HashMap<String, Integer> analyseTypesContained(String[] colData) {
		HashMap<String, Integer> typesContained = new HashMap<>();
		int itsAnInt;
		double itsADouble;
		String itsAString;

		for (String a : colData) {
			if (a.equals("")) {
				continue;
			}
			String input = a;

			try {
				// Try - if it is an int
				itsAnInt = Integer.parseInt(input);
				if (typesContained.containsKey("Integer")) {
					// already has an integer
					typesContained.put("Integer", (typesContained.get("Integer") + 1));
				} else {
					// doesnt have an integer
					typesContained.put("Integer", 1);
				}
				continue;
			} catch (NumberFormatException e) {
			}

			try {
				// Try - if it is a double
				itsADouble = Double.parseDouble(input);

				if (typesContained.containsKey("Double")) {
					// already has an integer
					typesContained.put("Double", (typesContained.get("Double") + 1));
				} else {
					// doesnt have an integer
					typesContained.put("Double", 1);
				}
				continue;
			} catch (NumberFormatException e) {
			}

			try {
				// Try - if it is a string
				itsAString = input;

				if (typesContained.containsKey("String")) {
					// already has an integer
					typesContained.put("String", (typesContained.get("String") + 1));
				} else {
					// doesnt have an integer
					typesContained.put("String", 1);
				}
				continue;
			} catch (NumberFormatException e) {
			}

		}
		return typesContained;
	}
	
	/**
	 * @return true if String[] contains integer
	 */
	public Boolean containsInt() {
		return typesContained.containsKey("Integer");
	}

	/**
	 * @return true if String[] contains Strings
	 */
	public Boolean containsString() {
		return typesContained.containsKey("String");
	}

	/**
	 * @return true if String[] contains doubles
	 */
	public Boolean containsDouble() {
		return typesContained.containsKey("Double");
	}

	/**
	 * @return true if it contains only 1 data type.
	 */
	public Boolean contains1DataType() {
		if (typesContained.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * prints the different data types within the array
	 */
	public String printDataTypes() {
		String toReturn = "";
		Iterator it = typesContained.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			toReturn = (pair.getKey() + " = " + pair.getValue());

		}
		
		return toReturn;
	}

	public String[] getOnlyStrings() {
		String[] strings = onlyStrings.stream().toArray(String[]::new);
		return strings;
	}

	public Integer[] getOnlyInts() {
		Integer[] ints = onlyInts.stream().toArray(Integer[]::new);
		return ints;
	}

	public Double[] getOnlyDoubles() {
		Double[] doubles = onlyDoubles.stream().toArray(Double[]::new);
		return doubles;
	}
	/**
	 * Picks up if there are any references to Latitude or Longtitude.
	 * @param headerName column header name
	 * @return true if the header name suggests its a latitude or longtitude.
	 */
	public static Boolean latLongDetector(String headerName) {
		String sentence = headerName;
		String search1  = "lat";
		String search2  = "long";
		
		if ( sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1  || sentence.toLowerCase().indexOf(search2.toLowerCase()) != -1) {

		   return true;

		} else {

		   return false;

		}
	}
	/**
	 * Picks up if there are any references to Latitude or Longtitude.
	 * @param headerName column header name
	 * @return true if the header name suggests its a latitude or longtitude.
	 */
	public static Boolean northingEastingDetector(String headerName) {
		String sentence = headerName;
		String search1  = "north";
		String search2  = "east";
		
		if ( sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1  || sentence.toLowerCase().indexOf(search2.toLowerCase()) != -1) {

		   return true;

		} else {

		   return false;

		}
	}
	
	
	
	/**
	 * This returns string[] of all the different datatypes
	 * @return
	 */
	public String[] typesContained() {
		String[] types = new String[typesContained.size()];
		int count = 0;
		
		Iterator it = typesContained.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			types[count] = pair.getKey().toString();
			count++;
		}
		
		return types;
	}
	
	public static void main(String[] args) {
		CSVReader accidentsData = new CSVReader("accidents.csv");
		String[] colTest = accidentsData.getColumnData(3);
		DataDetector dDetector = new DataDetector(colTest);

		String[] contained = dDetector.typesContained();
		
		System.out.println(Arrays.toString(contained));
	}
	
}
