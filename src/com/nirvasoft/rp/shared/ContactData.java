package com.nirvasoft.rp.shared;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContactData {
	private String division;
	private String township;
	private String phoneNo;
	
	public ContactData() {
		clearProperties();
	}
	
	private void clearProperties() {
		this.division = "";
		this.township = "";
		this.phoneNo = "";	
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "ContactData [division=" + division + ", township=" + township + ", phoneNo=" + phoneNo + "]";
	}
	
}
