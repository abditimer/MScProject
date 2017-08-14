package map;

import java.util.ArrayList;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;

public class MapCreator {
	private MapView mapView;
	private Map map;
	private GeocoderRequest request;
	private boolean isFirstCoordinate = true;
	private int plottedLocationNo = 1;
	int count = 0;

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public MapView getMapView() {
		return mapView;
	}

	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public GeocoderRequest getRequest() {
		return request;
	}

	public void setRequest(GeocoderRequest request) {
		this.request = request;
	}

	/**
	 * This creates a map object for which we can add things to.
	 */
	public MapCreator(ArrayList<Double> latitude, ArrayList<Double> longtitude) {

		mapView = new MapView();

		mapView.setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				if (status == MapStatus.MAP_STATUS_OK) {
					
					map = mapView.getMap();
					map.setZoom(8.0);
					// set up all the markers from the arraylists
					// create a geocode
					GeocoderRequest request = new GeocoderRequest();
					// set the location using long/lat
					request.setAddress("London, UK");

			mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
				@Override
				public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
					if (status == GeocoderStatus.OK) {
						if (isFirstCoordinate) {
							map.setCenter(result[0].getGeometry().getLocation());
							isFirstCoordinate = false;
						}
						int maxSize = 20;
						if (latitude.size() >= longtitude.size()) {
							maxSize = longtitude.size() - 1;
						} else {
							maxSize = latitude.size() - 1;
						}
						for (int i = 0; i < 30; i++) {
							Marker marker = new Marker(map);
							marker.setPosition(new LatLng(latitude.get(i), longtitude.get(i)));
							final InfoWindow window = new InfoWindow(map);
							window.setContent(Integer.toString(plottedLocationNo));
							window.open(map, marker);
							plottedLocationNo++;
						}
					}
				}
			});
				}
			}
		});

	}

	// this method needs to create a marker using just a string
	public synchronized void setLatLongLocation(ArrayList<Double> latitude, ArrayList<Double> longtitude) {
		
	}

	public void setStringLocation(String location) {
		// create a geocode
		GeocoderRequest request = new GeocoderRequest();
		// set the location using long/lat
		request.setAddress(location);

		mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
			@Override
			public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
				if (status == GeocoderStatus.OK) {
					if (isFirstCoordinate) {
						map.setCenter(result[0].getGeometry().getLocation());
						isFirstCoordinate = false;
					}

					Marker marker = new Marker(map);
					marker.setPosition(result[0].getGeometry().getLocation());

					final InfoWindow window = new InfoWindow(map);
					window.setContent(location);
					window.open(map, marker);
				}
			}
		});
	}

	public synchronized void plotArrayOfLatLong(ArrayList<Double> latitude, ArrayList<Double> longtitude) {
		/*
		 * int maxSize; if (latitude.size() >= longtitude.size()) { maxSize =
		 * longtitude.size() -1; } else { maxSize = latitude.size() -1; }
		 * System.out.println(maxSize); System.out.println(latitude.size()); for
		 * (int i = 0; i < latitude.size(); i++) { double lats =
		 * latitude.get(i); double longs = longtitude.get(i);
		 * 
		 * setLatLongLocation(lats, longs); System.out.println(i); }
		 */
	}
}
