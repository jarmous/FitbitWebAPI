package com.gss.fitbit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class RegexUtils {
	
	public static final String nameRegex = "^[a-zA-Z. -]{0,150}$";
	public static final String mobileNumberRegex = "^(\\d{4,13})$";
	public static final String mobileCodeRegex = "^([0-9]{1,3})$";
	public static final String emailRegex = "^[_a-z0-9][.a-z0-9_+-]+@[a-z0-9][.a-z0-9_-]+.[a-z]{2,10}$";
	public static final String stringRegex = "^[a-zA-Z0-9. -]{0,250}$";
	public static final String pincodeRegex = "^[0-9-]{4,12}$";
	public static final String urlRegex = "^[a-zA-Z_]{0,150}$";
	public static final String companyGroupNameRegex = "^[a-zA-Z0-9(). -]{0,150}$";
	
	
	public static boolean validateString(String val,String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(val);
		return matcher.matches();
	}

}