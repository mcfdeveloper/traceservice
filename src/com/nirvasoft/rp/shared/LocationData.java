package com.nirvasoft.rp.shared;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LocationData {
	private String latitude;
	private String longitude;
	
	public LocationData() {
		clearProperties();
	}
	
	private void clearProperties() {
		this.latitude = "";
		this.longitude = "";
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "LocationData [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
   
}
