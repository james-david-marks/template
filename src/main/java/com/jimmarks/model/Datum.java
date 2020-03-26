/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.model;

public class Datum {
	
	public long id;
	public String type;
	public String head;
	public String body;
	
	public String getAsHtmlTableLine()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<TR>");
		buf.append(String.format("<TD>%d</TD>", id));
		buf.append(String.format("<TD>%s</TD>", type));
		buf.append(String.format("<TD>%s</TD>", head));
		buf.append(String.format("<TD>%s</TD>", body));
		buf.append("</TR>");
		return buf.toString();
	}
	public static String getAsDivs_header()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row border_bottom\">");
		buf.append(String.format("<DIV class=\"col-lg-1\"><label >%s</label></DIV>", "Id"));
		buf.append(String.format("<DIV class=\"col-lg-2\"><label >%s</label></DIV>", "Type"));
		buf.append(String.format("<DIV class=\"col-lg-9\"><label >%s</label></DIV>", "Summary"));
		buf.append("</DIV>");
		return buf.toString();
	}
	public String getAsDivs(boolean includeDetail)
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-1\"><label >%d</label></DIV>", id));
		buf.append(String.format("<DIV class=\"col-lg-2\"><label >%s</label></DIV>", LineType.set(type).get()));
		buf.append(String.format("<DIV class=\"col-lg-9\"><label >%s</label></DIV>", head));
		buf.append("</DIV>");
		if(includeDetail)
		{
			getAsDivs_detail(buf);
		}
		return buf.toString();
	}
	private void getAsDivs_detail(StringBuffer buf) {
		String cleanBody = body.replace("\r\n", "");
		String[] bodyBullets = cleanBody.split("·");
		buf.append("<DIV class=\"row gutters\">");
		buf.append("<DIV class=\"col-lg-12\">");
		buf.append("<UL>");
		for(int i = 0; i < bodyBullets.length; i++)
		{
			if(bodyBullets[i].trim().length() > 0)
			{
				buf.append(String.format("<LI>%s</LI>", bodyBullets[i]));
			}
		}
		buf.append("</UL>");
		buf.append("</DIV>");
		buf.append("</DIV>");
	}
	public static enum LineType { head, subhead, body, experience, education, community;
		public String get()
		{
			switch(this)
			{
				default: return "Body";
				case head: return "Head";
				case subhead: return "Subhead";
				case body: return "Body";
				case experience: return "Experience";
				case education: return "Education";
				case community: return "Community";
			}
		}
		public static LineType set(String value)
		{
			switch(value)
			{
				default: return body;
				case "head": return head;
				case "subhead": return subhead;
				case "body": return body;
				case "experience": return experience;
				case "education": return education;
				case "community": return community;
			}
		}
	}
}
