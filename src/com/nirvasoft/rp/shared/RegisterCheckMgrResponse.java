package com.nirvasoft.rp.shared;

public class RegisterCheckMgrResponse {

	private String code;
	private String desc;
	private String error;
	private boolean isExist;

	public RegisterCheckMgrResponse() {
		this.clearProperties();
	}

	private void clearProperties() {
		this.code = "";
		this.desc = "";
		this.error = "";
		this.isExist = false;
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

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

	@Override
	public String toString() {
		return "RegisterCheckResponse [code=" + code + ", desc=" + desc + ", error=" + error + ", isExist=" + isExist
				+ "]";
	}

}
