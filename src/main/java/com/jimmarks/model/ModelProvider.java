/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.jimmarks.template.App;
import com.jimmarks.template.Constants;

public class ModelProvider {

    public ResultSet reader;
	public Connection connection = null;
	
    private String source = "localhost";
    private String userId;
    private String password;
    private String catalog;

	public String getSource(){ return source; } 
	public void setSource(String source){ this.source = source; }
	public String getUserId(){ return userId; } 
	public void setUserId(String userId){ this.userId = userId; }
	public String getPassword(){ return password; } 
	public void setPassword(String password){ this.password = password; }
	public String getCatalog(){ return catalog; } 
	public void setCatalog(String catalog){ this.catalog = catalog; }

	public ModelProvider(String catalog){
		this.userId = Constants.getString("modelprovider.user");
		this.password = Constants.getString("modelprovider.password");
		this.catalog = catalog;
	}

	public boolean checkTableExists(String tableName, Connection conn)
	{
		boolean found = false;
		try
		{
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT table_name FROM information_schema.tables WHERE table_schema = ");
			buf.append(" '" + catalog + "' ");
			buf.append(" AND table_name = ");
			buf.append(" '" + tableName + "' ");
			buf.append(" LIMIT 1 ");
			reader = mySqlDispatch(buf.toString(), conn);
			if(reader != null)
			{
	            if(reader.next())
	            {
		            String echoTableName = reader.getString("TABLE_NAME");
		            if(tableName.toLowerCase().equals(echoTableName.toLowerCase()))
		            {
		            	found = true;
		            }
	            }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return found;
	}
	public Connection getOpenMySqlConnection()
	{
		String connectString = makeMySqlConnectionString(source, catalog);
		String forName = "com.mysql.jdbc.Driver";
		Connection con = null;
		try{
			Class.forName(forName);
			con = DriverManager.getConnection(
					connectString, userId, password); 
			if(connection != null)
			{
				connection.close();
			}
			connection = con;
		}
		catch(Exception e){
			System.out.println("GetConnection failed: " + e.getMessage());
			e.printStackTrace();
		};
		return con;
	}
	public String makeMySqlConnectionString(String source, String catalog)
	{
		String connectString = String.format("jdbc:mysql://%s:3306/%s", source, catalog);
		return connectString;
	}
	public ResultSet mySqlDispatch(String cmdText)
	{
		return mySqlDispatch(cmdText, null);
	}
	public ResultSet mySqlDispatch(String cmdText, Connection connection)
	{
		try
		{
			if(connection == null)
			{
				connection = getOpenMySqlConnection();            	
			}
			Statement cmd = connection.createStatement();
			
			reader = cmd.executeQuery(cmdText);
		}
		catch(Exception e){
			App.applogger.error("GetConnection failed: " + e.getMessage());
			e.printStackTrace();
		}
		return reader;
	}
	public int mySqlExecuteNonQuery(String cmdText)
	{
		return mySqlExecuteNonQuery(cmdText, null);
	}
	public int mySqlExecuteNonQuery(String cmdText, Connection connection)
	{
		int rows = -1;
		try
		{
			if(connection == null)
			{
				connection = getOpenMySqlConnection();   

			}
			Statement cmd = connection.createStatement();
			rows = cmd.executeUpdate(cmdText);			
		}
		catch(Exception e){
			App.applogger.error("GetConnection failed: " + e.getMessage());
			e.printStackTrace();
		};       
		return rows;
	}
	public ResultSet mySqlQuery(String cmdText)
	{
		return mySqlDispatch(cmdText);
	}
	public void setConnectionParameters(String source, String userId, String password, String catalog)
	{
		this.source = source;
		this.userId = userId;
		this.password = password;
		this.catalog = catalog;
	}
	public String testMySqlConnection()
	{
		String testResult = "Connection Successful!";
		String connectString = makeMySqlConnectionString(source, catalog);
		String forName = "com.mysql.jdbc.Driver";
		Connection con = null;
		try{
			Class.forName(forName);
			con = DriverManager.getConnection(
					connectString, userId, password); 
			if(connection != null)
			{
				connection.close();
			}
			connection = con;
		}
		catch(Exception e){
			System.out.println("GetConnection failed: " + e.getMessage());
			testResult = "GetConnection failed: " + e.getMessage();
			e.printStackTrace();
		};
		return testResult;
	}

}