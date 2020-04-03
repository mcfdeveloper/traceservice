package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterCheckRequest {

	private String phoneNo;
	private String division;
	private String district;
	private String township;

	public RegisterCheckRequest() {
		this.clearProperties();
	}

	private void clearProperties() {
		this.phoneNo = "";
		this.division = "";
		this.district = "";
		this.township = "";
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

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

	@Override
	public String toString() {
		return "RegisterCheckRequest [phoneNo=" + phoneNo + ", division=" + division + ", district=" + district
				+ ", township=" + township + "]";
	}

}
