package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterCheckResponse {

	private String code;
	private String desc;
	private String error;

	public RegisterCheckResponse() {
		this.clearProperties();
	}

	private void clearProperties() {
		this.code = "";
		this.desc = "";
		this.error = "";
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

	@Override
	public String toString() {
		return "RegisterCheckResponse [code=" + code + ", desc=" + desc + ", error=" + error + "]";
	}

}
