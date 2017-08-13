package tests;

import data.DataDetector;
import static org.junit.Assert.*;

import org.junit.Test;
import data.DataDetector;

public class DataDetectorTest {
	String[] threeString = {"a", "b", "c"};
	String[] threeInts = {"1", "2", "3"};
	String[] threeDouble = {"1.0", "2.0", "3.0"};
	
	String[] threeStringsAnd1Double = {"this", "shouldnt", "work", "1.33"};
	String[] threeStringsAnd1Int = {"this", "shouldnt", "work", "1.33"};
	
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
		String[] expected = { "Integer", "String", "Double"};
		
		String[] test = {"this", "2.223322323", "1"};
		DataDetector detect = new DataDetector(test);
		String[] actual5 = detect.typesContained();
		
		assertArrayEquals(expected, actual5);
	}

}
