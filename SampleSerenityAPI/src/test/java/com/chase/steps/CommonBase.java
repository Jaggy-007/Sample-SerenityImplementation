package com.chase.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;

import support.libraries.GlobalProperties;

public class CommonBase {
	
	protected String END_POINT=null;
	protected static String servicename=null;
	protected String requestPath=null;
	protected Headers headers= null;
	protected static Headers staticheaders= null;
	protected static Map<String,String> pathParams = new HashMap();
	protected static Map<String,String> queryParams = new HashMap();
	protected static ExtractableResponse response= null;
	
	public void setRequestUri(String service){
		this.servicename =service;
		END_POINT=GlobalProperties.envProps.getProperty("host")+GlobalProperties.envProps.getProperty(servicename);
		
	}
	
	public void setRequestPath(String servicename){
		requestPath=GlobalProperties.envProps.getProperty(servicename+"_path");
	}
	
	public void setPathParams(Map<String,String> params){
		
		for (Map.Entry<String, String> param: params.entrySet() ){
			pathParams.put(param.getKey(), param.getValue());
			
		}
	}
	
	public void setQueryParams(Map<String,String> params){
		
		for (Map.Entry<String, String> param: params.entrySet() ){
			queryParams.put(param.getKey(), param.getValue());
			
		}
	}
	
	public void setRequestHeaders(Map<String,String> requestHeaders){
		List<Header> headerList = new ArrayList();
		Header header;
		
		
		for (Map.Entry<String, String> pair: requestHeaders.entrySet()){
			
			header= new Header(pair.getKey(),pair.getValue());
			headerList.add(header);
			
		}
		
		headers= new Headers(headerList);
		
	}
	
	
	public String getRequestMethod(String servicename){
		return GlobalProperties.envProps.getProperty(servicename+"_method");
	}
	
	
	public void service_GET(){
		
		RestAssuredConfig rac = RestAssured.config.sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation("SSLv3"));
		
		if (headers!=null)
		{
		given().contentType(ContentType.JSON)
				.pathParameters(pathParams)
				.headers(headers)
				.queryParams(queryParams)
				.baseUri(END_POINT)
				.basePath(requestPath)
				.relaxedHTTPSValidation().get();
		}
		
		else
		{
			given().contentType(ContentType.JSON)
			.pathParameters(pathParams)
			.queryParams(queryParams)
			.baseUri(END_POINT)
			.basePath(requestPath)
			.relaxedHTTPSValidation().get();
			
		}
		headers=null;
		pathParams.clear();
		queryParams.clear();
		
		
		}
	
	
	
public void service_POST(){
		
		RestAssuredConfig rac = RestAssured.config.sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation("SSLv3"));
		
		if (headers!=null)
		{
		given().contentType(ContentType.JSON)
				.pathParameters(pathParams)
				.headers(headers)
				.queryParams(queryParams)
				.baseUri(END_POINT)
				.basePath(requestPath)
				.relaxedHTTPSValidation()
				.post();
		}
		
		else
		{
			given().contentType(ContentType.JSON)
			.pathParameters(pathParams)
			.queryParams(queryParams)
			.baseUri(END_POINT)
			.basePath(requestPath)
			.relaxedHTTPSValidation()
			.post();
			
		}
		headers=null;
		pathParams.clear();
		queryParams.clear();
		
		
		}
	
	
	public void getresponse()
	{
		
		response=then().extract();
	}
				
		
	}
	
	
	

