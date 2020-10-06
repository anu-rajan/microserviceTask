package com.example.discoveryService.controller;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.discoveryService.contact.ContactUtil;
import com.example.discoveryService.model.Person;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "DiscoveryServiceController", description="Api's of microservice one")
@RestController
public class DiscoveryServiceController {
	
	Logger logger = Logger.getLogger(DiscoveryServiceController.class.getName());
	
	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	@ApiOperation(value = "GetStatus of the system ", response = Iterable.class, tags = "getStatus")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 500, message = "Service Down"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ResponseEntity<String> getStatus() {
		  logger.log(Level.INFO, "DiscoveryService -- getStatus");
		  ResponseEntity<String> response= new ResponseEntity<String>("Up", HttpStatus.OK);
		  return response;
	}
	

	@ApiOperation(value = "Get Complete Salutation for person ", response = String.class, tags = "getCompleteSalutation")
	@RequestMapping(value= "/salutation", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> createSalutation(@RequestBody Person person) {
			logger.log(Level.INFO, "DiscoveryService -- getSalutation");
			return ContactUtil.getCompleteSalutation(restTemplate,person);
	}
	
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<ObjectNode> handleException(JsonParseException ex)
	{
		ObjectNode node = JsonNodeFactory.instance.objectNode(); // initializing
		node.put("message", "Invalid input"); 
		node.put("error", ex.getMessage()); 
		node.put("code", HttpStatus.UNPROCESSABLE_ENTITY.value()); 
		
	    ResponseEntity<ObjectNode> response= new ResponseEntity<ObjectNode>(node, HttpStatus.UNPROCESSABLE_ENTITY);
	    return response;
	}

}
