
package com.nirvasoft.rp.shared;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class FleepListDataset {
	
	private String msgCode = "";
	private String msgDesc = "";
	private String phneno;
	private String fleepno;
	private String fromlocation;
	private FleepData[] data;
	public FleepListDataset() {
		clearProperty();
	}

	private void clearProperty() {
		
		msgCode = "";
		msgDesc = "";
		phneno="";
		fleepno="";
		fromlocation="";
		data = null;
	}
	
	@Override
	public String toString() {
		return "FleepListDataset [msgCode=" + msgCode + ", msgDesc=" + msgDesc + ", phneno=" + phneno + ", fleepno="
				+ fleepno + ", fromlocation=" + fromlocation + ", data=" + Arrays.toString(data) + "]";
	}

	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getMsgDesc() {
		return msgDesc;
	}
	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}
	public String getPhneno() {
		return phneno;
	}
	public void setPhneno(String phneno) {
		this.phneno = phneno;
	}
	public String getFleepno() {
		return fleepno;
	}
	public void setFleepno(String fleepno) {
		this.fleepno = fleepno;
	}
	public String getFromlocation() {
		return fromlocation;
	}
	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}
	public FleepData[] getData() {
		return data;
	}
	public void setData(FleepData[] data) {
		this.data = data;
	}

	

	

	
}
