package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotiResponse {

	private String code;
	private String desc;
	private String responseCode;
	private String responseBody;

	public NotiResponse() {
		clearProperty();
	}

	private void clearProperty() {
		code = "";
		desc = "";
		responseBody = "";
		responseCode = "";
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "ResponseNoti [code=" + code + ", desc=" + desc + ", responseCode=" + responseCode + ", responseBody="
				+ responseBody + "]";
	}

}
