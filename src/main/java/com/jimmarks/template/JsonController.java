/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.template;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.jimmarks.model.sqlMethods;
import com.jimmarks.model.ModelProvider;

/**
 * @author dderose
 *
 */
//@Controller
@RestController
@RequestMapping("api/json")
public class JsonController {
	
	@RequestMapping("/")
	public String json() {
		return "Json controller online.";
	}
	
	@RequestMapping("/testjsonservices")
	public ResponseEntity<String> testJsonServices() throws SQLException {
		sqlMethods sqlMethods = new sqlMethods();
		JsonObject json = sqlMethods.getJsonAll();
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		return responseEntity;
	}
	
}