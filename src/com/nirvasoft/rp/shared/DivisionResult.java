package com.nirvasoft.rp.shared;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;
import com.nirvasoft.rp.shared.DivisionData;

@XmlRootElement
public class DivisionResult {
	private String code;
	private String desc;
	private DivisionData[] data = null;
	
	public DivisionResult() {
		clearProperties();
	}

	private void clearProperties() {
		this.code = "";
		this.desc = "";
		this.data = null;
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

	public DivisionData[] getData() {
		return data;
	}

	public void setData(DivisionData[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DivisionResult [code=" + code + ", desc=" + desc + ", data=" + Arrays.toString(data) + "]";
	}
	
}
