package com.gss.fitbit.utils;

import java.net.URLEncoder;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gss.fitbit.domain.FitbitCredentials;
//import com.gss.fitbit.persistence.CommonUtilityDAO;

@Component
public class FitbitUtils {

	/*
	 * @Autowired CommonUtilityDAO commonUtilityDAO;
	 */
	@Autowired
	ApplicationProperties applicationProperties;

	// Keys used
//	public static final String HMAC_SHA1 = "HMAC-SHA1";
//	public static final String version = "1.0";
//	public static final String HmacSHA1 ="HmacSHA1";
	public static String client_secret = "134898b76543465d653661cfb7bc2208";
	public static String client_id = "22BF3D";
    public static String call_back_url = "https://connect.100daysofrunning.in/hdor-fitbit/connect/authorize/";

	// Fitbit URL's
	public static final String fitbit_request_token_URL= "https://api.fitbit.com/oauth2/token";
	public static final String user_auth_redirect_URL = "https://www.fitbit.com/oauth2/authorize";
	//public static final String fitbit_access_token_URL = "https://connectapi.fitbit.com/oauth-service/oauth/access_token";
	public static final String fitbit_pull_activities_url = "https://api.fitbit.com/1/user/-/activities";
	// Headers
	/*
	 * public static final String oauth_version = "oauth_version"; public static
	 * final String oauth_verifier = "oauth_verifier"; public static final String
	 * oauth_consumer_key = "oauth_consumer_key"; public static final String
	 * oauth_token = "oauth_token"; public static final String oauth_timestamp =
	 * "oauth_timestamp"; public static final String oauth_nonce = "oauth_nonce";
	 * public static final String oauth_signature_method = "oauth_signature_method";
	 * public static final String oauth_signature = "oauth_signature"; public static
	 * final String oauth_token_secret = "oauth_token_secret"; public static final
	 * String oauth_consumer_secret ="oauth_consumer_secret"; public static final
	 * String uploadStartTimeInSeconds = "uploadStartTimeInSeconds"; public static
	 * final String uploadEndTimeInSeconds = "uploadEndTimeInSeconds";
	 */


	// METHOD TYPES 
	public static final String POST = "POST";
	public static final String GET = "GET";

	// Response params
	public static final String status = "status";
	public static final String data = "data";
	public static final String msg = "msg";
	public static final String response_code = "response_code";
	public static final String redirect_url = "redirect_url";

	// Response codes
	public static final int CODE_500 = 500;
	public static final int CODE_400 = 400;
	public static final int CODE_200 = 200;
	public static final int CODE_401 = 401;



	//private static ConcurrentHashMap<Long, String> user_ouath_secret_map = new ConcurrentHashMap<>();

	private static ConcurrentHashMap<String, String> refresh_token_map = new ConcurrentHashMap<>();

	private static ConcurrentHashMap<Long, String> user_access_token_map = new ConcurrentHashMap<>();


	@PostConstruct
	public void loadData() {
		loadValues();
		loadAllFitbitUsers();
	}

	private void loadValues() {
		call_back_url = applicationProperties.getStrProperty("fitbit.redirectUrl", call_back_url);
		client_id = applicationProperties.getStrProperty("fitbit.clientId", client_id);
		client_secret = applicationProperties.getStrProperty("fitbit.clientSecret",client_secret);
	}
	private void loadAllFitbitUsers() {
		String query = "from Fitbit where deleted = :deleted";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("deleted", false);
		List<FitbitCredentials> list = commonUtilityDAO.executeSelectQuery(query, paramMap);
		if (null!=list && list.size()>0) {
			clearAllMaps();
			for (FitbitCredentials fitbitCredentials : list) {
				if(!fitbitCredentials.isDeleted()) {
					addAccessTokenForUser(fitbitCredentials.getRunnerId(),fitbitCredentials.getAccessToken());
					addSecretForAccessToken(fitbitCredentials.getAccessToken(), fitbitCredentials.getToken());
				}
			}
		}
	}

	private void clearAllMaps() {
		//user_ouath_secret_map.clear();
		refresh_token_map.clear();
		user_access_token_map.clear();
	}


	public static void addTokenSecretForUser(long userId,String tokenSecret) {
		user_ouath_secret_map.put(userId, tokenSecret);
	}
	public static String getTokenSecretForUser(long userId) {
		return user_ouath_secret_map.get(userId);
	}

	public static boolean checkIfTokenSecretOfUserExists(long userId) {
		return user_ouath_secret_map.containsKey(userId);
	}

	public static void addAccessTokenForUser(long userId,String accessToken) {
		if(!user_access_token_map.containsKey(userId))
			user_access_token_map.put(userId, accessToken);
	}

	public static boolean checkIfAccessTokenOfUserExists(long userId) {
		return user_access_token_map.containsKey(userId);
	}

	public static String getAccessTokenOfUser(long userId) {
		return user_access_token_map.get(userId);
	}


	public static void addSecretForAccessToken(String accessToken,String secret) {
		if(!access_token_secret_map.containsKey(accessToken))
			access_token_secret_map.put(accessToken, secret);
	}

	public static String getSecretForAccessToken(String accessToken) {
		return access_token_secret_map.get(accessToken);
	}


	// NORMALIZED PARAMETER
	private static String normalizeParameters(HashMap<String, Object> paramMap){
		StringBuffer normalized_parameters = new StringBuffer();
		for(String key :paramMap.keySet()){
			if(normalized_parameters.length()>0)
				normalized_parameters.append("&" +key +"="+ paramMap.get(key));
			else 
				normalized_parameters.append(key +"="+ paramMap.get(key));
		}

		return normalized_parameters.toString();
	}

	// BASE SIGNATURE STRING
	private static String  baseSignatureString(String normalized_parameters,String method,String URL){
		try{
			String encodedParamter = URLEncoder.encode( normalized_parameters, "UTF-8" ); 
			String encodedURL = URLEncoder.encode( URL, "UTF-8" );
			String base_signature_string= method+"&"+encodedURL+"&"+encodedParamter;
			return base_signature_string;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	// CREATE HMAC_SIGNATURE
	private static String getSignature(String base_signature_string,String secret){
		StringBuffer str = new StringBuffer(); 
		str.append(client_secrert_key);
		if(null!=secret && !secret.equals("")){
			str.append("&" + secret);
		}
		SecretKeySpec key = new SecretKeySpec(str.toString().getBytes(), HmacSHA1);
		Mac mac;
		try {
			mac = Mac.getInstance(HmacSHA1);
			mac.init(key);
			byte[] bytes = mac.doFinal(base_signature_string.getBytes());
			String base64Encoded = Base64.getEncoder().encodeToString(bytes); 
			String signature = URLEncoder.encode( base64Encoded, "UTF-8" );
			return signature;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static HashMap<String,Object> basicHeaderMap(){
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put(oauth_version, version);
		paramMap.put(oauth_consumer_key, client_consumer_key);
		paramMap.put(oauth_nonce, createNonce());
		paramMap.put(oauth_signature_method, HMAC_SHA1);
		paramMap.put(oauth_timestamp, System.currentTimeMillis()/1000L);
		return paramMap;
	}

	public static String getAuthorizationHeader(HashMap<String, Object> paramMap,String method,String url,String secret){
		String authorization_header = "";
		try {

			paramMap = sortByKey(paramMap);

			String normalized_parameters = normalizeParameters(paramMap);

			String base_signature_string = baseSignatureString(normalized_parameters,method, url);

			String signature = getSignature(base_signature_string, secret);

			authorization_header = getAuthorizationHeader(paramMap, signature);

		}catch (Exception e) {
			ApplicationUtilities.printStackTrace(e);
		}
		return authorization_header;
	}	

	//Get Authorization Header
	private static String getAuthorizationHeader(HashMap<String, Object> paramMap,String signature){
		paramMap = sortByKey(paramMap);
		StringBuffer authorization_header = new StringBuffer();
		authorization_header.append("OAuth ");
		for(String key :paramMap.keySet()){
			authorization_header.append(key +"=\""+ paramMap.get(key)+"\",");
		}
		authorization_header.append("oauth_signature=\""+signature+"\"");
		return authorization_header.toString();
	}	

	// SORT MAP BY KEY
	private static HashMap<String, Object> sortByKey(HashMap<String, Object> unsortMap) {
		List<Map.Entry<String,Object>> list =new LinkedList<Map.Entry<String,Object>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String,Object>>() {
			public int compare(Map.Entry<String,Object> o1,
					Map.Entry<String,Object> o2) {
				return (o1.getKey()).compareTo(o2.getKey());
			}
		});
		HashMap<String,Object> sortedMap = new LinkedHashMap<String,Object>();
		for (Map.Entry<String,Object> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}	

	// METHOD TO CREATE A NONCE
	private static String createNonce() {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { 
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		return salt.toString();
	}

}
