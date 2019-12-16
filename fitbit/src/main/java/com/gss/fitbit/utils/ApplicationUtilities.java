package com.gss.fitbit.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.gss.security.domain.AppUserDetailsDTO;

@Component
public class ApplicationUtilities {


	public static String getCurrentUsername(){
		return ((AppUserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	public static String getCurrentUserPassword(){
		return ((AppUserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPassword();
	}

	public static long getCurrentRunnerId(){
		return ((AppUserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRunnerId();
	}


	public static AppUserDetailsDTO getCurrentUser(){
		return ((AppUserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	public static String getCurrentUserDisplayName(){
		return ((AppUserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getDisplayName();
	}

	public static String getFormattedTime(long timeInSec){
		String time = "";

		long hours = timeInSec / 3600;
		int min = (int) (timeInSec - (hours*3600))/60;
		int sec = (int) (timeInSec - (hours*3600))%60;
		time = (hours > 9 ? (""+hours) : ("0"+hours)) + ":" + (min > 9 ? (""+ min) : ("0"+min)) + ":" + (sec > 9 ? (""+ sec) : ("0"+sec));
		return time;
	}

	public static String getFormatedTimeInMinutes(long timeInSec){
		String time = "";

		long hours = timeInSec / 3600;
		int min = (int) (timeInSec - (hours*3600))/60;
		int sec = (int) (timeInSec - (hours*3600))%60;
		time = (min > 9 ? (""+ min) : ("0"+min)) + ":" + (sec > 9 ? (""+ sec) : ("0"+sec));
		return time;
	}

	public static String getCertificateFormatedTime(long timeInSec){
		String time = "";

		long hours = timeInSec / 3600;
		int min = (int) (timeInSec - (hours*3600))/60;
		int sec = (int) (timeInSec - (hours*3600))%60;
		time = (min > 9 ? (""+ min) : ("0"+min)) + "m " + (sec > 9 ? (""+ sec) : ("0"+sec) + "s");
		return time;
	}

	public static String getFormattedTimeWithDays(long timeInSec){
		String time = "";

		long days = timeInSec / 86400;
		timeInSec = timeInSec - (days*86400);
		long hours =  timeInSec / 3600;
		int min = (int) (timeInSec - (hours*3600))/60;
		int sec = (int) (timeInSec - (hours*3600))%60;
		time = (days > 0 ? ""+days+":" : "" ) + (hours > 9 ? (""+hours) : ("0"+hours)) + ":" + (min > 9 ? (""+ min) : ("0"+min)) + ":" + (sec > 9 ? (""+ sec) : ("0"+sec));
		return time;
	}

	public static int getDiffYears(Date first, Date last) {
		int diff = 0;
		if(null != first && null != last) {
			Calendar a = getCalendar(first);
			Calendar b = getCalendar(last);
			diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
			if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
					(a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
				diff--;
			}
		}
		return diff;
	}

	private static Calendar getCalendar(Date date) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);
		return cal;
	}

	public static String getPace(double distance, long timeInSec) {
		if(distance > 0 && timeInSec > 0) {
			String time = "";
			long pace = (Math.round((timeInSec/distance)));
			int min = (int) (pace/60);
			int sec = (int) (pace%60);
			time = (min > 9 ? (""+ min) : ("0"+min)) + ":" + (sec > 9 ? (""+ sec) : ("0"+sec));
			return time;
		}
		return "00:00";
	}



	public static String getStartDate(){
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
		String startDate = ddMMyyyy.format(new Date());
		try {
			long openWindow = ApplicationProperties.openWindow;
			Date initialStartDate = yyyyMMdd.parse(yyyyMMdd.format(new Date(yyyyMMdd.parse(yyyyMMdd.format(new Date())).getTime() - ((openWindow-1)*1000*60*60*24))));
			startDate = ddMMyyyy.format(initialStartDate);
		} catch (ParseException e) {
			ApplicationUtilities.printStackTrace(e);
		}
		return startDate;
	} 

	public static String getEndDate(){
		DateFormat ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
		String sendingEndDate = ddMMyyyy.format(new Date());
		return sendingEndDate;
	}

	public static String getDateInDisplayFormat(String dateInYYYYMMDDFormat){
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat displayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = "";
		try{
			formattedDate = displayDateFormat.format(yyyyMMdd.parse(dateInYYYYMMDDFormat));
		}catch(Exception e){
			ApplicationUtilities.printStackTrace(e);
		}
		return formattedDate;
	}

	public static String getDateInYYYYMMDDformat(Date date) {
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		return yyyyMMdd.format(date);
	}

	public static boolean checkIfDateInRange(Date submittedDate){
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		boolean inRange = false;
		try{
			Date startDate = yyyyMMdd.parse(getStartDate());
			Date endDate = yyyyMMdd.parse(getEndDate());

			startDate = new Date(startDate.getTime() - 60000);
			endDate = new Date(endDate.getTime() + (60*60*24*1000));

			if(submittedDate.after(startDate) && submittedDate.before(endDate)){
				inRange = true;
			}

		}catch(Exception e){
			ApplicationUtilities.printStackTrace(e);
		}
		return inRange;
	}

	public static Long getBeggningOfADayInSeconds(String dateInddMMyyyy){
		DateFormat ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calender = new GregorianCalendar();
		long time = 0L;
		try{
			calender.setTime(ddMMyyyy.parse(dateInddMMyyyy));
			calender.set(Calendar.HOUR,00);
			calender.set(Calendar.MINUTE,00);
			calender.set(Calendar.SECOND,00);
			calender.set(Calendar.MILLISECOND,00);
			time = calender.getTimeInMillis()/1000L;
		}catch (Exception e) {
			ApplicationUtilities.printStackTrace(e);
		}
		return time+19800L;
	}

	public static Long getEndOfADayInSeconds(String date){
		DateFormat ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calender = new GregorianCalendar();
		long time = 0L;
		try{
			TimeZone timeZone = TimeZone.getTimeZone("UTC");
			calender = Calendar.getInstance(timeZone);
			calender.setTime(ddMMyyyy.parse(date));
			calender.set(Calendar.HOUR,23);
			calender.set(Calendar.MINUTE,59);
			calender.set(Calendar.SECOND,59);
			calender.set(Calendar.MILLISECOND,999);
			time = calender.getTimeInMillis()/1000L;
		}catch (Exception e) {
			ApplicationUtilities.printStackTrace(e);
		}
		return time+19800L;
	}


	public static VelocityEngine getVelocityEngine() {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();
		return velocityEngine;
	}

	public static HashMap<Long, String> sortMapByValue(HashMap<Long, String> unsortMap) {
		List<Map.Entry<Long,String>> list =new LinkedList<Map.Entry<Long,String>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Long,String>>() {
			public int compare(Map.Entry<Long,String> o1,
					Map.Entry<Long,String> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		HashMap<Long,String> sortedMap = new LinkedHashMap<Long,String>();
		for (Map.Entry<Long,String> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static HashMap<Long, String> sortMapByKey(HashMap<Long, String> unsortMap) {
		List<Map.Entry<Long,String>> list =new LinkedList<Map.Entry<Long,String>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Long,String>>() {
			public int compare(Map.Entry<Long,String> o1,
					Map.Entry<Long,String> o2) {
				return (o1.getKey()).compareTo(o2.getKey());
			}
		});
		HashMap<Long,String> sortedMap = new LinkedHashMap<Long,String>();
		for (Map.Entry<Long,String> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}


	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException e) {
			ApplicationUtilities.printStackTrace(e);
			result = false;
		}
		return result;
	}


	public static Logger getErrorLogger() {
		return Logger.getLogger("com.gss.error");
	}

	public static void printStackTrace(Exception e) {
		try {
			getErrorLogger().debug("Error trace follows : ",e);
		}catch(Exception ex) {
			System.err.println("Not able to open logger.............");
		}
	}
	
	public static void printErrorMessage(String msg) {
		try {
			getErrorLogger().error(msg);
		}catch(Exception ex) {
			System.err.println("Not able to open logger.............");
		}
	}

}
