package tests;

import static org.junit.Assert.*;
import csvReader.CSVReader;
import org.junit.Test;

public class CSVReaderTest {
	CSVReader potholeData = new CSVReader("Pothole_Enquiries_2015.csv");

	// Test to make sure each file has data
	@Test
	public void test1_1() {
		long start = System.currentTimeMillis();

		CSVReader potholeData = new CSVReader("Pothole_Enquiries_2015.csv");

		String[] expected = { "\"ENQUIRY_TYPE\"", "\"ENQUIRY_CATEGORY\"", "\"DATE_RECORDED\"", "\"DIVISION\"",
				"\"CLIENT_OFFICE_NAME\"", "\"EASTING\"", "\"NORTHING\"", "\"APPROVAL_STATUS_NAME\"" };

		String[] actual = potholeData.getHeader();
		assertArrayEquals(expected, actual);

		long end = System.currentTimeMillis();
		System.out.println("DEBUG: length of time to upload File 1: " + (end - start) + " MilliSeconds");
	}

	@Test
	public void test1_2() {
		long start = System.currentTimeMillis();
		CSVReader rainData = new CSVReader("RainTemp.csv");
		String[] expected = { "﻿", "Temp (°C) Dec-Feb", "Rain (mm) Dec-Feb", "Temp (°C) Jun-Aug",
				"Rain (mm) Jun-Aug", "Temp (oC) Jan-Dec", "Rain (mm) Jan-Dec" };

		String[] actual = rainData.getHeader();
		assertArrayEquals(expected, actual);

		long end = System.currentTimeMillis();
		System.out.println("DEBUG: length of time to upload File 2: " + (end - start) + " MilliSeconds");
	}

	@Test
	public void test1_3() {
		long start = System.currentTimeMillis();
		CSVReader waterData = new CSVReader("WaterTemperature.csv");
		String[] expected = { "determinan", "id", "determin_1", "determin_2", "determin_3", "result", "codedResul",
				"resultQual", "samplesamp", "samplesa_1", "samplesa_2", "samplesa_3", "samplesa_4", "samplesa_5",
				"sampleisCo", "samplepurp" };

		String[] actual = waterData.getHeader();
		assertArrayEquals(expected, actual);

		long end = System.currentTimeMillis();
		System.out.println("DEBUG: length of time to upload File 3: " + (end - start) + " MilliSeconds");
	}

	// Test if right column name is fetched
	@Test
	public void test2_1() {
		int expected = 0;
		int actual = potholeData.findColumnLocation("\"ENQUIRY_TYPE\"");
		equals(expected == actual);
	}

	// Test if the right number of lines of the csv file are returned.
	@Test
	public void test2_2() {
		int expected = 6403;
		int actual = potholeData.countLinesInCSVFile();
		equals(expected == actual);
	}

	// Test if the right number of lines of the csv file are returned.
	@Test
	public void test2_3() {
		for (String actual : potholeData.getColumnData(0)) {
			assertEquals("\"Pothole\"", actual);
		}
	}
}
