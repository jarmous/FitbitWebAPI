package com.gss.fitbit.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gss.fitbit.domain.FitbitCredentials;
//import com.gss.garmin.persistence.CommonUtilityDAO;
import com.gss.fitbit.utils.ApplicationUtilities;
//import com.gss.fitbit.utils.FitbitUtil;
//import com.gss.garmin.util.ApplicationProperties;
//import com.gss.security.util.Encoder;
import com.gss.security.domain.AppUserDetailsDTO;
///import com.gss.security.persistence.AppSecurityDAO;

@Service
public class FitbitService {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(FitbitService.class);
	
	
	private String convertToJason(Object message) {
		try {
			ObjectWriter obw = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return obw.writeValueAsString(message);
		} catch (Exception e) {
			ApplicationUtilities.printStackTrace(e);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("msg", "There was some problem in processing your request. Please try after some time");
			return map.toString();
		}
	}
	
}
