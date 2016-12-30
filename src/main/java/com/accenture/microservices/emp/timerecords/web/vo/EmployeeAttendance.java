package com.accenture.microservices.emp.timerecords.web.vo;
/**
 * 
 */

import java.util.Collection;
import java.util.Iterator;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

/**
 * @author j.venugopalan
 *
 */
@Document
public class EmployeeAttendance {

	@Field
	public Integer employeeId;
	@Field
	public Collection<DayAttendance> dayAttendance;
	
	public EmployeeAttendance(Integer id, Integer employeeId, Collection<DayAttendance> dayAttendance) {
		this.employeeId = employeeId;
		this.dayAttendance = dayAttendance;
	}
	public EmployeeAttendance() {
	}
	/**
	 * @return the employeeId
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the dayAttendance
	 */
	public Collection<DayAttendance> getDayAttendance() {
		return dayAttendance;
	}
	/**
	 * @param dayAttendance the dayAttendance to set
	 */
	public void setDayAttendance(Collection<DayAttendance> dayAttendance) {
		this.dayAttendance = dayAttendance;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "EmployeeAttendance [ employeeId=" + employeeId + ", dayAttendance="
				+ (dayAttendance != null ? toString(dayAttendance, maxLen) : null) + "]";
	}
	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
