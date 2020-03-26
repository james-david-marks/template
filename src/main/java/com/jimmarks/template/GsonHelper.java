/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.template;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;

public class GsonHelper
{
	private final Gson _ref = getGson();
	private Gson getGson() {
		  // Trick to get the DefaultDateTypeAdatpter instance
		  // Create a first instance a Gson
		  Gson gson = new GsonBuilder()
		    .setDateFormat("yyyy-MM-dd")
			.serializeNulls()
		    .create();

		  // Get the date adapter
		  TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);

		  // Ensure the DateTypeAdapter is null safe
		  TypeAdapter<Date> safeDateTypeAdapter = dateTypeAdapter.nullSafe();

		  // Build the definitive safe Gson instance
		  return new GsonBuilder()
		    .registerTypeAdapter(Date.class, safeDateTypeAdapter)
		    .create();
	}
	public String toJson(Object obj)
	{
		return _ref.toJson(obj);
	}
	public JsonElement toJsonTree(Object obj)
	{
		return _ref.toJsonTree(obj);
	}
	public <T> T fromJson(JsonElement jsonElement, Class<T> classOfT)
	{
		return _ref.fromJson(jsonElement, classOfT);
	}
}