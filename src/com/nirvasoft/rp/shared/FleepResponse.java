
package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class FleepResponse {

	private String code;
	private String desc;
	private String error;
	private long skey;
	private String fleetNo;

	public FleepResponse() {
		this.clearProperties();
	}

	private void clearProperties() {
		this.code = "";
		this.desc = "";
		this.error = "";
		this.skey = 0;
		this.fleetNo = "";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public long getSkey() {
		return skey;
	}

	public void setSkey(long skey) {
		this.skey = skey;
	}

	public String getFleetNo() {
		return fleetNo;
	}

	public void setFleetNo(String fleetNo) {
		this.fleetNo = fleetNo;
	}

	@Override
	public String toString() {
		return "FleepResponse [code=" + code + ", desc=" + desc + ", error=" + error + ", skey=" + skey + ", fleetNo="
				+ fleetNo + "]";
	}

}

