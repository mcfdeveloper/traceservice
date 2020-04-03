package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotiData {
	private String phoneno;
	private String name;
	private String deviceId;
	private String token;
	private String division;
	private String district;
	private String township;
	
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public NotiData() {
		clearProperties();
	}

	private void clearProperties() {
		this.phoneno="";
		this.name = "";
		this.deviceId = "";
		this.token = "";
		this.division = "";
		
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Override
	public String toString() {
		return "NotiData [phoneno=" + phoneno + ", name=" + name + ", deviceId=" + deviceId + ", token=" + token
				+ ", division=" + division + "]";
	}
	
}