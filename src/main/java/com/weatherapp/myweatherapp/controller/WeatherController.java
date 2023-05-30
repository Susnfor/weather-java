package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;

import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

@Controller

  

public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }
  // API request: 
 
public class ApiForecastRequest {
	
	
	
	public static void timelineRequestHttpClient() throws Exception {
		//set up the end point / variables
		String apiEndPoint="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
		String location="London,UK";
		String startDate=null;
		String endDate=null;
		
		String unitGroup="metric";
		String apiKey="7U758GGSR9KUNN82BH8XJS9XV";
	
    //build the request URI by appending the location, start date and end date
		StringBuilder requestBuilder=new StringBuilder(apiEndPoint);
		requestBuilder.append(URLEncoder.encode(location, StandardCharsets.UTF_8.toString())); //encode and append location string using UTF-8 encoding (no spaces in url)
		

    //append the start date and end date if they are specified
		if (startDate!=null && !startDate.isEmpty()) {
			requestBuilder.append("/").append(startDate);
			if (endDate!=null && !endDate.isEmpty()) {
				requestBuilder.append("/").append(endDate);
      
			}
		}
    //building the request URI
		URIBuilder builder = new URIBuilder(requestBuilder.toString());
		
		builder.setParameter("unitGroup", unitGroup)
			.setParameter("key", apiKey);

		//create the request
		
		HttpGet get = new HttpGet(builder.build());
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		CloseableHttpResponse response = httpclient.execute(get);    
		
		String rawResult=null;
		try { //catch any errors
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
				return;
			}
			//get the response body
			HttpEntity entity = response.getEntity();
		    if (entity != null) {
		    	rawResult=EntityUtils.toString(entity, Charset.forName("utf-8"));
		    }
		    
		    
		} finally {
			response.close();
		}
		

		
	}
	
  

  // TODO: given two city names, compare the length of the daylight hours and return the city with the longest day
    // request two city names (return error if not two, or if unknown city, allow to sumbit two again)
    //  pull data from api for each city
    //  compare the length of the daylight hours, if equal, return both, if city1 > city2, return city1, if city2 > city1, return city2
    //  display the result

  // TODO: given two city names, check which city its currently raining in
    // request two city names (return error if not two, or if unknown city, allow to sumbit two again)
    //  pull data from api for each city
    //  check if its raining in city1, if yes, return city1, if no, check city2, if yes, return city2, if no, return that its not raining in either city
        //  precipitation in api is a number, if its greater than 0, its raining
}
