package data;

import java.util.ArrayList;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;

public class EastingNorthingConvertor {
	//to be returned
	private ArrayList<Double> latitude = new ArrayList<>();
	private ArrayList<Double> longtitude = new ArrayList<>();
	//to convert
	private ArrayList<Double> easting;
	private ArrayList<Double> northings;
	/**
	 * <b>Make sure they are the correct way around!</b>
	 * @param eastings
	 * @param northings
	 */
	public EastingNorthingConvertor(String[] eastings, String[] northings) {
		this.easting = ArrayListCreator.stringArrayToDouble(eastings);
		this.northings = ArrayListCreator.stringArrayToDouble(northings);
	}
	
	public int findMax() {
		int max = 0;
		if (easting.size() > northings.size()) {
			max = northings.size();
		} else {
			max = easting.size();
		}
		
		return max;
	}
	
	public void convert() {
		//create LatLong Objects
		for (int i = 0; i < findMax(); i ++) {
			//for each item, we create a latlong object
			
			LatLng LatLongObj = new OSRef(easting.get(i), northings.get(i)).toLatLng();
			LatLongObj.toWGS84();
			double lat = LatLongObj.getLat();
			double lng = LatLongObj.getLng();
			
			
			latitude.add(lat);
			longtitude.add(lng);
		}
	}
	


	public ArrayList<Double> getLatitude() {
		return latitude;
	}

	public ArrayList<Double> getLongtitude() {
		return longtitude;
	}

	public ArrayList<Double> getEasting() {
		return easting;
	}

	public ArrayList<Double> getNorthings() {
		return northings;
	}
	
	public void printConverted() {
		for (int i = 0; i < findMax(); i++) {
			System.out.println(Double.toString(latitude.get(i)) + " : " + Double.toString(longtitude.get(i)));
			System.out.println(Double.toString(latitude.get(i)));
			System.out.println(Double.toString(longtitude.get(i)));
		}
	}

	public static void main(String[] args) {
		// easting - northing
		LatLng latLng = new OSRef(460830,452510).toLatLng();
		latLng.toWGS84();
		double lat = latLng.getLat();
		double lng = latLng.getLng();
		System.out.println("Should return:");
		System.out.println("Longtitude should be -1.074261:  " + lng);
		System.out.println("Latitude should be 53.965099:  " + lat);
		
		System.out.println("======------=========");
		String[] eastings = {"460830", "469375", "460679"};
		String[] northings = {"452510", "449837", "451868"};
		EastingNorthingConvertor convert = new EastingNorthingConvertor(eastings, northings);
		convert.convert();
		convert.printConverted();
		
	}
}
