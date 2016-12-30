/**
 * 
 */
package com.accenture.microservices.emp.timerecords.web.utility;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.accenture.microservices.emp.data.DayAttendance;

/**
 * @author j.venugopalan
 *
 */
@Service
public class DocumentValidationUtil {
	public static final Logger log = LoggerFactory.getLogger(DocumentValidationUtil.class);

	
	/*public static void main(String ar[]){
		DocumentValidationUtil du = new DocumentValidationUtil();
	}*/

	public Boolean validateDateString(Collection<DayAttendance> dayAttendance){
		String pattern = ".*/.*/.*";
		boolean matches = false;
		for(DayAttendance dAtt : dayAttendance){
			matches = Pattern.matches(pattern, dAtt.getDate());
			if(!matches)
				return matches;
		}
		return matches;
	}
	
	public Boolean validateHours(Collection<DayAttendance> dayAttendance){
		Boolean hoursValidationStatus = false;
		HashMap<String, Integer> dayAttenanceConsolidateHours = new HashMap<String, Integer>();
		for(DayAttendance dAtt: dayAttendance){
			//log.info("dAtt.getDate(): "+dAtt.getDate()+" dAtt.getHours() "+dAtt.getHours());
			if(!dayAttenanceConsolidateHours.containsKey(dAtt.getDate())){
				dayAttenanceConsolidateHours.put((String)dAtt.getDate(), (Integer)dAtt.getHours());
			}else{
				dayAttenanceConsolidateHours.computeIfPresent(dAtt.getDate(), (k, v) -> (Integer)v + (Integer)dAtt.getHours());
			}
		}
		 Set set = dayAttenanceConsolidateHours.entrySet();
	     Iterator i = set.iterator();
	     while(i.hasNext()) {
	         Map.Entry da = (Map.Entry)i.next();
	         log.info("Key *** "+da.getKey() + ": ");
	         log.info("Value *** "+da.getValue());
	         if((Integer)da.getValue() < 9 || (Integer)da.getValue() > 24){
	        	 hoursValidationStatus = false;
	        	 break;
	         }	
	         hoursValidationStatus = true;
	      }
	     log.info("validation status: "+hoursValidationStatus);
		return hoursValidationStatus;
	}
	
}
