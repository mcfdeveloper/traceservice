package com.nirvasoft.rp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DivisionData {
	private String division;
	private String check;
	private String positive;
	private String dead;
	
	public DivisionData() {
		clearProperties();
	}
	
	private void clearProperties() {
		this.division = "";
		this.check = "";
		this.positive = "";
		this.dead = "";		
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getPositive() {
		return positive;
	}

	public void setPositive(String positive) {
		this.positive = positive;
	}

	public String getDead() {
		return dead;
	}

	public void setDead(String dead) {
		this.dead = dead;
	}

	@Override
	public String toString() {
		return "DivisionData [division=" + division + ", check=" + check + ", positive=" + positive + ", dead=" + dead
				+ "]";
	}
	
}
