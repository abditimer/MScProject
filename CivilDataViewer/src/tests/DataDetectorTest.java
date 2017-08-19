package tests;

import data.DataDetector;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import data.DataDetector;

public class DataDetectorTest {
	String[] threeString = {"a", "b", "c"};
	String[] threeInts = {"1", "2", "3"};
	String[] threeDouble = {"1.0", "2.0", "3.0"};
	String[] emptyArray = {};
	String[] threeStringsAnd1Double = {"this", "shouldnt", "work", "1.33"};
	String[] threeStringsAnd1Int = {"this", "shouldnt", "work", "1.33"};
	
	String[] lotsOfInts = { "1", "2","3","4", "5","6","7", "8","9", "10"};
	
	DataDetector detect; 
			
			
	/**
	 * Test to make sure only one data type is within these arrays
	 */
	@Test
	public void test1_1() {
		Boolean expected = true;
		
		DataDetector detect = new DataDetector(threeString);
		Boolean actual = detect.contains1DataType();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test1_2() {
		Boolean expected = true;
		
		DataDetector detect2 = new DataDetector(threeInts);
		Boolean actual2 = detect2.contains1DataType();
		
		assertEquals(expected, actual2);
	}
	@Test
	public void test1_3() {
		Boolean expected = true;
		
		DataDetector detect3 = new DataDetector(threeDouble);
		Boolean actual3 = detect3.contains1DataType();
		
		assertEquals(expected, actual3);
	}
	@Test
	public void test1_4() {
		Boolean expected = false;
		
		DataDetector detect4 = new DataDetector(threeStringsAnd1Double);
		Boolean actual4 = detect4.contains1DataType();
		
		assertEquals(expected, actual4);
	}
	@Test
	public void test1_5() {
		Boolean expected = false;
		
		DataDetector detect5 = new DataDetector(threeStringsAnd1Int);
		Boolean actual5 = detect5.contains1DataType();
		
		assertEquals(expected, actual5);
	}
	/**
	 * Tests to make sure correct arrays are returned.
	 */
	@Test
	public void test2_1() {
		String[] expected = {"this", "shouldnt", "work"};
		
		DataDetector detect = new DataDetector(threeStringsAnd1Double);
		String[] actual5 = detect.getOnlyStrings();
		
		assertArrayEquals(expected, actual5);
	}
	@Test
	public void test2_2() {
		String[] expected = {"this"};
		
		String[] test = {"this", "2.223322323", "1"};
		DataDetector detect = new DataDetector(test);
		String[] actual5 = detect.getOnlyStrings();
		
		assertArrayEquals(expected, actual5);
	}
	
	/**
	 * Tests to find reference to Lat/Long in a String.
	 */
	@Test
	public void test3_1() {
		Boolean expected = true;
		
		String columnName = "Longtitude";
		Boolean actual = DataDetector.latLongDetector(columnName);
		
		assertEquals(expected, actual);
	}
	@Test
	public void test3_2() {
		Boolean expected = true;
		
		String columnName = "Latitude";
		Boolean actual = DataDetector.latLongDetector(columnName);
		
		assertEquals(expected, actual);
	}
	@Test
	public void test3_3() {
		Boolean expected = true;
		
		String columnName = "Lat";
		Boolean actual = DataDetector.latLongDetector(columnName);
		
		assertEquals(expected, actual);
	}
	@Test
	public void test3_4() {
		Boolean expected = true;
		
		String columnName = "sa_Long";
		Boolean actual = DataDetector.latLongDetector(columnName);
		
		assertEquals(expected, actual);
	}
	@Test
	public void test3_5() {
		Boolean expected = false;
		
		String columnName = "Foods";
		Boolean actual = DataDetector.latLongDetector(columnName);
		
		assertEquals(expected, actual);
	}
	/**
	 * Tests to make sure correct type list is returned
	 */
	@Test
	public void test4_1() {
		String[] expected = { "Decimals/Double", "Words/String", "Numbers/Integer"};
		
		String[] test = {"this", "2.223322323", "1"};
		DataDetector detect = new DataDetector(test);
		String[] actual5 = detect.typesContained();
		System.out.println(Arrays.toString(actual5));
		assertArrayEquals(expected, actual5);
	}
	/**
	 * Tests empty arrays
	 */
	@Test
	public void test5() {
		Boolean expected = true;
		
		DataDetector detect = new DataDetector(emptyArray);
		Boolean actual = detect.isColumnEmpty();
		
		assertEquals(expected, actual);
	}
	/**
	 * Tests more complex arrays
	 */
	@Test
	public void test6() {
		Integer[] expected = {1,2,3,4,5,6,7,8,9, 10};
		
		DataDetector detect = new DataDetector(lotsOfInts);
		
		assertArrayEquals(detect.getOnlyInts(), expected);
	}

}
