package com.nirvasoft.rp.shared;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationDataSet {
	
	private String msgCode = "";
	private String msgDesc = "";
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

	private String description;
	private String title;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private NotiData[] data;

	public NotificationDataSet() {
		clearProperty();
	}

	private void clearProperty() {
		
		msgCode = "";
		msgDesc = "";
		division="";
		data = null;
	}
	
	public String getMsgCode() {
		return msgCode;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}
	public NotiData[] getData() {
		return data;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public void setData(NotiData[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NotificationDataSet [msgCode=" + msgCode + ", msgDesc=" + msgDesc + ", division=" + division + ", data="
				+ Arrays.toString(data) + ", getMsgCode()=" + getMsgCode() + ", getMsgDesc()=" + getMsgDesc()
				+ ", getData()=" + Arrays.toString(getData()) + ", getDivision()=" + getDivision() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}