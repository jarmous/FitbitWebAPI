package com.gss.fitbit.utils;



import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:fitbit.properties")
public class ApplicationProperties {

	private Logger logger = Logger.getLogger(ApplicationProperties.class);

	@Autowired
	Environment environment;

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	private static final String OPEN_WINDOW_PROP = "openWindow";

	public static final String MALE = "Male";
	public static final String FEMALE = "Female";

	public static int openWindow = 10;
	public static int eligibleRun = 10;
	
	public static final String db2 ="db2";
	public static final String mysql = "mysql";
	public static final String maria = "maria";
	public static String dbType;
	public static final long eventId = 5L;




	// Application final properties
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String RUNNER = "ROLE_RUNNER";
	public static final String MOBILE_ADMIN = "ROLE_MOBILE_ADMIN";
	public static final String MODERATOR = "ROLE_MODERATOR";
	public static final String CITY = "city";
	public static final String COUNTRY = "country";
	public static final String STATE = "state";
	public static final String GROUP = "group";
	public static final String OVERALL = "overall";
	public static final String REPORT_GROUP = "groups";
	public static final String COMPANY = "company";
	public static final String AGE_GROUP = "age_group";
	public static final String address = "address";
	public static final String tshirtSize = "tshirtSize";
	public static final String nameOnTshirt = "nameOnTshirt";
	public static final String pincode = "pinCode";
	public static final String ticketType = "ticketType";
	public static final String Other = "Other";
	public static final String None = "None";
	public static final String Housewife = "Housewife";
	public static final String Own_Business = "Own Business";
	public static final String Student = "Student";
	public static final String Self_employed = "Self employed";
	public static final String FILE_SEPERATOR = "/";
	public static final long Other_Id = 999;
	public static final long Other_sc_Id = 100000;
	public static final String PASSWORD_ALGO = "SHA-256";
	public static final String http = "http";
	public static final String https = "https";


	// Created and updated authors
	public static final String runner ="runner";
	public static final String strava ="Strava";
	public static final String system = "system";
	public static final String fitbit ="Fitbit";
	public static final String all = "All";

	
	//MAIL PROPERTIES
	public static String regMailAddress = "registrations@100daysofrunning.in";
	public static String infoMailAddress = "info@100daysofrunning.in";
	public static String supportMailAddress = "support@100daysofrunning.in";
	public static String moderatorMailAddress = "moderator@100daysofrunning.in";
	
	@PostConstruct
	private void initialize(){
		System.out.println("INITIALIZING THE APP PROPERTIES");

		openWindow = Integer.parseInt(environment.getProperty(OPEN_WINDOW_PROP, ""+openWindow));
		eligibleRun = Integer.parseInt(environment.getProperty("eligibleRun", ""+eligibleRun));
		dbType = getStrProperty("dbType");
		
		logger.debug("Open window : " + openWindow);
	}

	public String getStrProperty(String propertyName){
		return environment.getProperty(propertyName);
	}
	
	public String getStrProperty(String propertyName,String defaultVal){
		return environment.getProperty(propertyName,defaultVal);
	}

	public int getIntProperty(String propertyName){
		int value = 0;
		try{
			value = Integer.parseInt(environment.getProperty(propertyName)); 
		}catch(NumberFormatException e){
			ApplicationUtilities.printStackTrace(e);
		}
		return value;
	}
	public boolean getbooleanProperty(String propertyName){
		boolean bool = false;
		int value = 0;
		try{
			value = Integer.parseInt(environment.getProperty(propertyName)); 
			if(value ==1)
				bool = true;
		}catch(NumberFormatException e){
			ApplicationUtilities.printStackTrace(e);
		}
		return bool;
	}
}
