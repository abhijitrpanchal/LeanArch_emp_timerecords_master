package com.accenture.microservices.emp.business.vo;

import java.util.Date;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;

/**
 * 
 */




/**
 * @author j.venugopalan
 *
 */
@Document
public class DayAttendance {
	
	@Field
	public String date;
	@Field
	public Integer hours;
	@Field
	public String chargeCode;
	@Field
	public String location;
	
	public DayAttendance(String date, Integer hours, String chargeCode, String location) {
		this.date = date;
		this.hours = hours;
		this.chargeCode = chargeCode;
		this.location = location;
	}
	public DayAttendance() {
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}
	/**
	 * @param hours the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	/**
	 * @return the chargeCode
	 */
	public String getChargeCode() {
		return chargeCode;
	}
	/**
	 * @param chargeCode the chargeCode to set
	 */
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DayAttendance [date=" + date + ", hours=" + hours + ", chargeCode=" + chargeCode + ", location="
				+ location + "]";
	}
	

}
