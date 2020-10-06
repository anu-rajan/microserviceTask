package com.example.discoveryService.contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.discoveryService.model.Person;

public class ContactUtil {
	
	public static ResponseEntity<String> getCompleteSalutation(RestTemplate restTemplate, Person person)
	{
	    final String uri1 = "http://localhost:8082/microservice2/greeting";
	    final String uri2 = "http://localhost:8083/microservice3/fullName";

	    ResponseEntity<String> result1 = restTemplate.getForEntity(uri1, String.class);
	    String greeting = result1.getBody();
	    
	    ResponseEntity<String> result2 = restTemplate.postForEntity(uri2, person, String.class);
	    String fullName = result2.getBody();

	    return new ResponseEntity<String>(greeting + " " + fullName,HttpStatus.OK);
	}
	
}
