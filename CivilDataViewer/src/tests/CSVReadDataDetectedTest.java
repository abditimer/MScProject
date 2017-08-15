package tests;

import static org.junit.Assert.*;

import java.util.List;

import csvReader.CSVReader;
import data.DataDetector;
import data.DataFormatFX;

import org.junit.Test;

public class CSVReadDataDetectedTest {
	//all tests are based on this file
	CSVReader potholeData = new CSVReader("Pothole_Enquiries_2015.csv");
	CSVReader accidentsData = new CSVReader("accidents.csv");
	private List<String[]> columnData = potholeData.getData();
	
	
	DataDetector dDetector;
	
	@Test
	public void test1() {
		String[] colTest = DataFormatFX.getColumnData(0, columnData);
		dDetector = new DataDetector(colTest);
		
		boolean expected = true;
		boolean actual = dDetector.contains1DataType();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		String[] colTest =  DataFormatFX.getColumnData(0, columnData);
		dDetector = new DataDetector(colTest);
		
		String expected = "String = 6403"
;
		String actual = dDetector.printDataTypes();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		String[] colTest =  DataFormatFX.getColumnData(2, columnData);
		dDetector = new DataDetector(colTest);
		
		boolean expected = true;
		boolean actual = dDetector.containsString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		String[] colTest =  DataFormatFX.getColumnData(3, columnData);
		dDetector = new DataDetector(colTest);
		
		boolean expected = true;
		boolean actual = dDetector.latLongDetector("Longitude");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test5() {
		String[] colTest =  DataFormatFX.getColumnData(3, accidentsData.getData());
		dDetector = new DataDetector(colTest);
		
		String[] expected = {"Double"};
		String[] actual = dDetector.typesContained();
		
		assertArrayEquals(expected, actual);
	}

}
