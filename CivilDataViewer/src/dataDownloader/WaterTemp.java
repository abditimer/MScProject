package dataDownloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class WaterTemp {
	private  ArrayList<String> headings = new ArrayList<String>();
	private  ArrayList<String> col1 = new ArrayList<String>();
	private  ArrayList<String> col2 = new ArrayList<String>();
	private  ArrayList<String> col3 = new ArrayList<String>();
	private  ArrayList<String> col4 = new ArrayList<String>();
	private  ArrayList<String> col5 = new ArrayList<String>();
	private  ArrayList<String> col6 = new ArrayList<String>();
	private  ArrayList<String> col7 = new ArrayList<String>();
	private  ArrayList<String> col8 = new ArrayList<String>();
	
	
	public WaterTemp() {
		try {
		       
	        BufferedReader in = new BufferedReader(new FileReader("resources/excelFiles/WaterTemperature.csv"));
	        int numberOfLines=0;
	        String str;
	        while ((str = in.readLine()) != null) {
	        	
		        
	        	if ( numberOfLines == 0 ) {
	        		str = str.toString();
			        String[] splits = str.split(",");
	        		
	        		for (String a : splits) {
	        			headings.add(a);
	        		//System.out.println(a);
	        		}
	        	
		        	
	        	} else {
	        		
	        		
	        		str = str.toString();
			        String[] splits = str.split(",");
			        col1.add(splits[0]);
		        	col2.add(splits[1]);
		        	col3.add(splits[2]);
		        	col4.add(splits[3]);
		        	//need to merge next two elements
		        	col5.add(splits[4] + " " + splits[5]);
		        	col6.add(splits[6]);
		        	col7.add(splits[7]);
		        	col8.add(splits[8]);
	        		
	        		
	        		if (splits.length == 9) {
	        			
	        		/*	col1.add(splits[0]);
			        	col2.add(splits[1]);
			        	col3.add(splits[2]);
			        	col4.add(splits[3]);
			        	//need to merge next two elements
			        	col5.add(splits[4] + " " + splits[5]);
			        	col6.add(splits[6]);
			        	col7.add(splits[7]);
			        	col8.add(splits[8]);*/
	        		} else {
	        			/*enquiryName.add(splits[0]);
			        	enquiryCategory.add(splits[1]);
			        	dateRecord.add(splits[2]);
			        	division.add(splits[3]);
			        	clientOfficeName.add(splits[4]);
			        	easting.add(splits[5]);
			        	northing.add(splits[6]);
			        	approvalStatusName.add(splits[7]);*/
	        		}
		        	
	        	}
	        	
	        	numberOfLines++;	
	        }
	       
	        in.close();
	        
	    } catch (MalformedURLException e) {
	    } catch (IOException e) {
	    	System.out.println("Cant reach the file. Please re-type file name.");
	    }
	}
	
	public static void printArrayList(ArrayList<String> a) {
		for (String b : a) {
        	System.out.println(b);
        }
	}
	
	public static void main(String[] args) {
		WaterTemp a = new WaterTemp();
		a.printArrayList(a.col5);
		
	}
}
