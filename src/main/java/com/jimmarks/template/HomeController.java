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

import com.jimmarks.model.sqlMethods;
import com.jimmarks.model.ModelProvider;

/**
 * @author dderose
 *
 */
//@Controller
@RestController
@RequestMapping("api/home")
public class HomeController {	
	
	@RequestMapping("/")
	public String home() {
		return "Home controller online.";
	}

	@RequestMapping("/initialize")
	public String initialize() throws SQLException {
		sqlMethods ddl = new sqlMethods();
		ddl.initializeEnvironment();
		ddl.loadData();
		return "Initialization complete.";
	}
	
	@RequestMapping("/testhomeservices")
	public String testHomeServices() throws SQLException {
		sqlMethods sqlMethods = new sqlMethods();
		String result = sqlMethods.getAll();
		return result;
	}	
	
	@RequestMapping("/testmodelprovider")
	public String testModelProvider() {
		ModelProvider modelProvider = new ModelProvider(sqlMethods.MASTER_SCHEMA_NAME);
		return modelProvider.testMySqlConnection();
	}

	
}