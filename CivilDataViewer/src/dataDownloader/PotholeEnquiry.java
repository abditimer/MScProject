package dataDownloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.TreeMap;

public class PotholeEnquiry {
	
	private  ArrayList<String> enquiryName = new ArrayList<String>();
	private  ArrayList<String> enquiryCategory = new ArrayList<String>();
	private  ArrayList<String> dateRecord = new ArrayList<String>();
	private  ArrayList<String> division = new ArrayList<String>();
	private  ArrayList<String> clientOfficeName = new ArrayList<String>();
	private  ArrayList<String> easting = new ArrayList<String>();
	private  ArrayList<String> northing = new ArrayList<String>();
	private  ArrayList<String> approvalStatusName = new ArrayList<String>();
	private ArrayList<String> headings = new ArrayList<>();
	
	private ArrayList<Double> northingsDouble = new ArrayList<>();
	
	public PotholeEnquiry() {
		try {
	       
	        BufferedReader in = new BufferedReader(new FileReader("resources/excelFiles/Pothole_Enquiries_2015.csv"));
	        int numberOfLines=0;
	        String str;
	        while ((str = in.readLine()) != null) {
	        	
		        
	        	if ( numberOfLines == 0 ) {
	        		str = str.toString();
			        String[] splits = str.split(",");
	        		//headings
	        		for (String a : splits) 
	        			headings.add(a);
		        	
	        	} else {
	        		
	        		
	        		str = str.toString();
			        String[] splits = str.split(",");
			        
	        		
	        		
	        		if (splits.length == 9) {
	        			
	        			enquiryName.add(splits[0]);
			        	enquiryCategory.add(splits[1]);
			        	dateRecord.add(splits[2]);
			        	division.add(splits[3]);
			        	//need to merge next two elements
			        	clientOfficeName.add(splits[4] + " " + splits[5]);
			        	easting.add(splits[6]);
			        	northing.add(splits[7]);
			        	approvalStatusName.add(splits[8]);
	        		} else {
	        			enquiryName.add(splits[0]);
			        	enquiryCategory.add(splits[1]);
			        	dateRecord.add(splits[2]);
			        	division.add(splits[3]);
			        	clientOfficeName.add(splits[4]);
			        	easting.add(splits[5]);
			        	northing.add(splits[6]);
			        	approvalStatusName.add(splits[7]);
	        		}
		        	
	        	}
	        	
	        	numberOfLines++;	
	        }
	        northingValuesAsInt();
	        in.close();
	        
	    } catch (MalformedURLException e) {
	    } catch (IOException e) {
	    	System.out.println("Cant reach the file. Please re-type file name.");
	    }
	}
	
	
	public ArrayList<String> getEnquiryName() {
		return enquiryName;
	}


	public ArrayList<String> getEnquiryCategory() {
		return enquiryCategory;
	}


	public ArrayList<String> getDateRecord() {
		return dateRecord;
	}


	public ArrayList<String> getDivision() {
		return division;
	}


	public ArrayList<String> getClientOfficeName() {
		return clientOfficeName;
	}


	public ArrayList<String> getEasting() {
		return easting;
	}


	public ArrayList<String> getNorthing() {
		return northing;
	}


	public ArrayList<String> getApprovalStatusName() {
		return approvalStatusName;
	}

	public ArrayList<String> getHeadings() {
		return headings;
	}
	
	

	public ArrayList<Double> getNorthingsDouble() {
		return northingsDouble;
	}


	public static void printArrayList(ArrayList<String> a) {
		for (String b : a) {
        	System.out.println(b);
        }
	}
	
	private void northingValuesAsInt() {
		
		for (String singleString : getNorthing()) {
			String[] splittedVals = singleString.split("\\.");
			
			if (splittedVals.length > 1) {
				//two values
				int val1 = Integer.parseInt(splittedVals[0]);
				int val2 = Integer.parseInt(splittedVals[1]);
				int sizeVal2 = splittedVals[1].length();
				//value is the northing value
				double value = val1 + (val2 / (Math.pow(10, sizeVal2 )));
				
				northingsDouble.add(value);
			} else {
				double value = Double.parseDouble(splittedVals[0]);
				
				northingsDouble.add(value);
			}
			
		}
		
	}
	
	//a method that will return a map: key = name, value = amount
	//key = enquiry approval status
	//value = how many 
	
	public TreeMap<String, Double> getEnquiryInfo() {
		TreeMap<String, Double> map = new TreeMap<>();
		
		for (String s : getApprovalStatusName()) {
			//if map has some value assigned to the key
			if (map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 0.0);
			}
		}
		
		return map;
	}
	
	
	
	 private void findSizeOfArrayLists() {
		System.out.println(getEnquiryName().size());
		System.out.println(getEnquiryCategory().size());
		System.out.println(getDateRecord().size());
		System.out.println(getDivision().size());
		System.out.println(getClientOfficeName().size());
		System.out.println(getEasting().size());
		System.out.println(getNorthing().size());
		System.out.println(getApprovalStatusName().size());
	 }
	
	private void printData() {
		printArrayList(getEnquiryName());
		System.out.println("============================================================================");
        printArrayList(getEnquiryCategory());
        System.out.println("============================================================================");
        printArrayList(getDateRecord());
        System.out.println("============================================================================");
        printArrayList(getDivision());
        System.out.println("============================================================================");
        printArrayList(getClientOfficeName());
        System.out.println("============================================================================");
        printArrayList(getEasting());
        System.out.println("============================================================================");
        printArrayList(getNorthing());
        System.out.println("============================================================================");
        printArrayList(getApprovalStatusName());
        System.out.println("============================================================================");
	}
	
	public static void main(String[] args) {
		PotholeEnquiry a = new PotholeEnquiry();
		a.printArrayList(a.headings);
		
	}


}
