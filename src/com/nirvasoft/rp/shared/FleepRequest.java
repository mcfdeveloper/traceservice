
package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class FleepRequest {
	private long autokey;
	private String phoneNo;
	private String fleepNo;
	private String fromLocation;
	private String toLocation;
	private String remark;
	private String depatureDateTime;
	private String arrivalDateTime;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	public FleepRequest() {
		this.clearProperties();
	}

	private void clearProperties() {
		this.autokey = 0;
		this.phoneNo = "";
		this.fleepNo = "";
		this.depatureDateTime="";
		this.arrivalDateTime="";
		this.fromLocation="";
		this.toLocation="";
		this.t1="";
		this.t2="";
		this.t3="";
		this.t4="";
		this.t5="";
	}

	public long getAutokey() {
		return autokey;
	}

	public void setAutokey(long autokey) {
		this.autokey = autokey;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFleepNo() {
		return fleepNo;
	}

	public void setFleepNo(String fleepNo) {
		this.fleepNo = fleepNo;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepatureDateTime() {
		return depatureDateTime;
	}

	public void setDepatureDateTime(String depatureDateTime) {
		this.depatureDateTime = depatureDateTime;
	}

	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public String getT5() {
		return t5;
	}

	public void setT5(String t5) {
		this.t5 = t5;
	}

	@Override
	public String toString() {
		return "FleepRequest [autokey=" + autokey + ", phoneNo=" + phoneNo + ", fleepNo=" + fleepNo + ", fromLocation="
				+ fromLocation + ", toLocation=" + toLocation + ", remark=" + remark + ", depatureDateTime="
				+ depatureDateTime + ", arrivalDateTime=" + arrivalDateTime + ", t1=" + t1 + ", t2=" + t2 + ", t3=" + t3
				+ ", t4=" + t4 + ", t5=" + t5 + "]";
	}
	
}
	