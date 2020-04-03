package com.nirvasoft.rp.shared;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;
import com.nirvasoft.rp.shared.ContactData;

@XmlRootElement
public class ContactResult {
	private String code;
	private String desc;
	private ContactData[] data = null;
	
	public ContactResult() {
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

	public ContactData[] getData() {
		return data;
	}

	public void setData(ContactData[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ContactResult [code=" + code + ", desc=" + desc + ", data=" + Arrays.toString(data) + "]";
	}
	
}
