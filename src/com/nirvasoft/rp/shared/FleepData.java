
package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class FleepData {
	private String phoneno;
	private String fleepno;
	private String fromLocation;
	private String tolocation;
	private String depaturedatetime;
	private String arrivaldatetime;
	private String remark;
	public FleepData(){
		clearProperties();
	}
	private void clearProperties(){
		this.phoneno="";
		this.fleepno="";
		this.fromLocation="";
		this.tolocation="";
		this.depaturedatetime="";
		this.arrivaldatetime="";
		this.remark="";
		
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getFleepno() {
		return fleepno;
	}
	public void setFleepno(String fleepno) {
		this.fleepno = fleepno;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getTolocation() {
		return tolocation;
	}
	public void setTolocation(String tolocation) {
		this.tolocation = tolocation;
	}
	public String getDepaturedatetime() {
		return depaturedatetime;
	}
	public void setDepaturedatetime(String depaturedatetime) {
		this.depaturedatetime = depaturedatetime;
	}
	public String getArrivaldatetime() {
		return arrivaldatetime;
	}
	public void setArrivaldatetime(String arrivaldatetime) {
		this.arrivaldatetime = arrivaldatetime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
