package data;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Lets us find out the data types in a String[]
 * 
 * @author Timer
 *
 */
public class DataDetector {
	//this is the column data to check
	String[] columnData;
	
	
	public DataDetector(String[] columnData) {
		this.columnData = columnData;
		checkTypeContained(columnData);
	}
	
	
	
	public HashMap<String, Integer> checkTypeContained(String[] toCheck) {
		HashMap<String, Integer> typesContained = new HashMap<>();
		List values;
		
		
		
		int itsAnInt;
        double itsADouble;
        String itsAString;
        
        for ( String a : toCheck) {
        	if (a.equals("")) {
        		continue;
        	}
        	String input = a;
        	
        	try {
        		//Try - if it is an int
        		itsAnInt = Integer.parseInt(input);
        		if (typesContained.containsKey("Integer")) {
        			//already has an integer
        			typesContained.put("Integer", (typesContained.get("Integer") + 1));
        		} else {
        			//doesnt have an integer
        			typesContained.put("Integer", 1);
        		}
        		continue;
        	} catch (NumberFormatException e) {
        	}
        	
        	try {
        		//Try - if it is a double
        		itsADouble = Double.parseDouble(input);
        		
        		if (typesContained.containsKey("Double")) {
        			//already has an integer
        			typesContained.put("Double", (typesContained.get("Double") + 1));
        		} else {
        			//doesnt have an integer
        			typesContained.put("Double", 1);
        		}
        		continue;
        	} catch (NumberFormatException e) {
        	}
        	
        	try {
        		//Try - if it is a string
        		itsAString = input;
        		
        		if (typesContained.containsKey("String")) {
        			//already has an integer
        			typesContained.put("String", (typesContained.get("String") + 1));
        		} else {
        			//doesnt have an integer
        			typesContained.put("String", 1);
        		}
        		continue;
        	} catch (NumberFormatException e) {
        	}
        }
        
        
        
        
        Iterator it = typesContained.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        return typesContained;
        
	}
	
	
	public static void main(String[] args) {
		//String[] a = {"asd", "s", "2.2", "asasdasdasd"};
		String[] a = {"asd", "1.1", "", ";dkfjhaspf[afj"};
		DataDetector test = new DataDetector(a);
		
		/*
		String a = "2.2";
		Scanner sc = new Scanner(a);
		
		int number;
		do {
		    while (!sc.hasNextInt()) {
		    	
		        System.out.println("That's not a number!");
		        sc.next(); // this is important!
		    }
		    
		    number = sc.nextInt();
		} while (number <= 0);
		System.out.println("Thank you! Got " + number);
		*/
	}
}
