package data;

import java.util.ArrayList;
import java.util.List;


import csvReader.CSVReader;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;

public class ArrayListCreator {
	private CSVReader reader;
	private List<String[]> columnData;
	private String[] headerData;
	
	public ArrayListCreator(CSVReader reader) {
		this.reader = reader;
		this.columnData = reader.getData();
		this.headerData = reader.getHeader();
	}
	
	public ArrayListCreator(List<String[]> columnData, String[] headerData) {
		this.columnData = columnData;
		this.headerData = headerData;
	}
	
	/**
	 * <b>String<Double>[] -> ArrayList<Doubles</b>
	 * @param stringArray
	 * @return
	 */
	public static ArrayList<Double> stringArrayToDouble(String[] stringArrayThatIsActuallyDouble) {
		ArrayList<Double> listOfDoubles = new ArrayList<>();
		
		for (String string : stringArrayThatIsActuallyDouble) {
			double doubleValue = Double.parseDouble(string);
			listOfDoubles.add(doubleValue);
		}
		return listOfDoubles;
	}
	
	/**
	 * <b>String<Integer>[] -> ArrayList<Integer</b>
	 * @param stringArray
	 * @return
	 */
	public static ArrayList<Integer> stringArrayToInteger(String[] stringArrayThatIsActuallyDouble) {
		ArrayList<Integer> listOfInteger = new ArrayList<>();
		
		for (String string : stringArrayThatIsActuallyDouble) {
			Integer intValie = Integer.parseInt(string);
			listOfInteger.add(intValie);
		}
		return listOfInteger;
	}
	
	/**
	 * <b>String<String>[] -> ArrayList<Strings</b>
	 * @param stringArray
	 * @return
	 */
	public static ArrayList<String> stringArrayToString(String[] stringArrayThatIsActuallyDouble) {
		ArrayList<String> listOfString = new ArrayList<>();
		
		for (String string : stringArrayThatIsActuallyDouble) {
			listOfString.add(string);
		}
		return listOfString;
	}
	public static void main(String[] args) {
		String[] check = {"2"};
		ArrayList<Double> checkList = stringArrayToDouble(check);
		
		for (double d : checkList) {
			System.out.println(Double.toString(d));
		}
		
	}
}
