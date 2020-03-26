/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.model;

import java.util.ArrayList;

public class DataContract {
	public static class JsonDatum{
		public long id;
		public String type;
		public String head;
		public String body;
	}
	public static class JsonComponent{
		public ArrayList<JsonDatum> JsonData = new ArrayList<JsonDatum>();
	}
	public static void map(Data data, ArrayList<JsonDatum> JsonData)
	{
		for(Datum datum : data)
		{
			JsonDatum jsonDatum = new JsonDatum();
			jsonDatum.id = datum.id;
			jsonDatum.type = datum.type;
			jsonDatum.head = datum.head;
			jsonDatum.body = datum.body;
			JsonData.add(jsonDatum);
		}
	}
}
