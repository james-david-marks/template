/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jimmarks.template.App;
import com.jimmarks.template.HtmlFormatter;
import com.jimmarks.template.Json;

public class sqlMethods {
	
	public static final String MASTER_SCHEMA_NAME = "mysql";
	private static final String SCHEMA_NAME = "template";
	private static final String TABLE_NAME = "data";
	
	public void initializeEnvironment() throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(MASTER_SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlCheckForSchema(SCHEMA_NAME));
		if(!result.next())
		{
			App.applogger.info("creating schema");
			modelProvider.mySqlExecuteNonQuery(sqlCreateSchema(SCHEMA_NAME));
		}
		else
		{
			App.applogger.info("schema exists");
		}
		modelProvider = new ModelProvider(SCHEMA_NAME);
		if(!modelProvider.checkTableExists(TABLE_NAME, modelProvider.connection))
		{
			App.applogger.info("creating table");
			modelProvider.mySqlExecuteNonQuery(sqlCreateTable_Data());
		}
		else
		{
			App.applogger.info("table exists");
		}
	}
	public String buildHtmlDocument() throws SQLException
	{
		HtmlFormatter html = new HtmlFormatter();
		return html.getLandingPage_build("");		
	}
	public String buildHtmlDocument(String content) throws SQLException
	{
		HtmlFormatter html = new HtmlFormatter();
		return html.getLandingPage_build(content);		
	}
	public String buildHtmlDocument(ResultSet result) throws SQLException
	{
		StringBuffer buf = new StringBuffer();
		HtmlFormatter html = new HtmlFormatter();
		buf.append(Datum.getAsDivs_header());
		while(result.next())
		{
			Datum datum = new Datum();
			datum.id = result.getLong("id");
			datum.type = result.getString("type");
			datum.head = result.getString("head");
			datum.body = result.getString("body");
			buf.append(datum.getAsDivs(true));
		}
		return html.getLandingPage_build(buf.toString());		
	}
	public JsonObject buildJsonDocument() throws SQLException
	{
		JsonObject jsonObject = Json.empty();
		jsonObject.addProperty("jsonservices", "ok");
		return Json.toJsonObject(jsonObject);
	}
	public JsonObject buildJsonDocument(ResultSet result) throws SQLException
	{
		Data data = new Data();
		while(result.next())
		{
			Datum datum = new Datum();
			datum.id = result.getLong("id");
			datum.type = result.getString("type");
			datum.head = result.getString("head");
			datum.body = result.getString("body");
			data.add(datum);
		}
		DataContract.JsonComponent jcm = new DataContract.JsonComponent();
		DataContract.map(data, jcm.JsonData);
		return Json.toJsonObject(jcm);
	}
	public String getAll() throws SQLException
	{
		return getAll(TABLE_NAME);
	}
	public String getAll(String tablename) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectAll(tablename));
		StringBuffer buf = new StringBuffer();
		buf.append(buildHtmlDocument(result));
		return buf.toString();
	}
	public JsonObject getJsonAll() throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectAll(TABLE_NAME));
		return buildJsonDocument(result);
	}
	public void loadData() throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectAll(TABLE_NAME));
		if(!result.next())
		{
			App.applogger.info(String.format("loading"));
			String insertSql = sqlInsert_testData();
			App.debuglogger.info(insertSql);
			modelProvider.mySqlExecuteNonQuery(insertSql);
		}
		else
		{
			App.applogger.info(String.format("data already loaded"));
		}
	}
	public String sqlCheckForSchema(String schemaName)
	{
		String sql = String.format("select schema_name from information_schema.schemata where schema_name = '%s'", schemaName);
		return sql;
	}
	public String sqlCreateTable_Data(){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("create table %s(", TABLE_NAME));
		buf.append("id bigint PRIMARY KEY AUTO_INCREMENT NOT NULL");
		buf.append(",type varchar(100)");
		buf.append(",head varchar(500)");
		buf.append(",body varchar(2000)");
		buf.append(");");
		return buf.toString();
	}
	public String sqlCreateSchema(String database){
		return String.format("create database %s", database);
	}
	public String sqlDropSchema(String database){
		return String.format("drop database %s", database);
	}
	public String sqlInsert_testData(){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("insert into %s (type, head, body) values", TABLE_NAME));
		buf.append("('head','subhead', 'body')");
		buf.append(";");
		return buf.toString();
	}
	public String sqlSelectAll(String tablename){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("select * from %s", tablename));
		buf.append(";");
		return buf.toString();
	}
}
