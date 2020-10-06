package com.example.discoveryService.model;

import io.swagger.annotations.ApiModelProperty;

public class Person {
	@ApiModelProperty(notes = "FirstName of the person",name="name",required=true,value="test first name")
	private String name;
	@ApiModelProperty(notes = "Surname of the person",name="surName",required=true,value="test surname")
	private String surName;

	public String getName() {
		return name;
	}

	public String getSurName() {
		return surName;
	}
	
	public Person() {
	}
	
	public Person(String name, String surName) {
		super();
		this.name = name;
		this.surName = surName;
	}
	@Override
	public String toString() {
		return "Person [Name=" + name + ", Surname=" + surName + "]";
	}

}
