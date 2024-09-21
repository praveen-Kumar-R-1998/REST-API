package com.in28minuter.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	/**
	 * URL versioning
	 */
	
	
	@GetMapping("v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2("Bob","Charlie");
	}
	
	/**
	 * Request Param versioning
	 */

	@GetMapping(path="/person", params="version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2("Bob","Charlie");
	}
	
	/**
	 * Custom headers versioning
	 */
	
	@GetMapping(path="/person/header", headers ="X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonOnRequestHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/header", headers ="X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonOnRequestHeader() {
		return new PersonV2("Bob","Charlie");
	}
	
	/**
	 * Media type versioning (a.k.a content negotiation)
	 */
	
	@GetMapping(path="/person/accept", produces ="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonOnAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/accept", produces ="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonOnAcceptHeader() {
		return new PersonV2("Bob","Charlie");
	}
	

}
