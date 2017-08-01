package dataDownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class TempReader {
	
	private static String[] years;
	private static String[] months;
	private static String[][] data;
	
	public static void main(String[] args) {
		try {
	        // Create a URL for the desired page
	        URL url = new URL("http://www.metoffice.gov.uk/pub/data/weather/uk/climate/stationdata/aberporthdata.txt");       

	        // Read all the text returned by the server
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        int count=0;
	        String str;
	        while ((str = in.readLine()) != null) {
	        	str = in.readLine().toString();
	        	if (count > 1) {
	        		String[] matches = str.split("   ");
	        		
	        		//years[count-2] = matches[1];
	        		
	        		//System.out.println(str);
	        		System.out.println(Arrays.toString(matches));
	        		
	        	}
	        	count++;	
	        }
	        
	        //System.out.println(Arrays.toString(years));
	        in.close();
	        
	    } catch (MalformedURLException e) {
	    } catch (IOException e) {
	    }
	}
}
