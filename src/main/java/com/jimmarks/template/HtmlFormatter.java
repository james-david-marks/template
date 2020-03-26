/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.template;

public class HtmlFormatter {
	public final String SERVLET_CONTEXT = "/template/";
	
 	public String closeContainer()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</div>");
		return buf.toString();
	}
	public String closeDiv()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</div>");
		return buf.toString();
	}
	public String closeDocument()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</body>");
		buf.append("</html>");
		return buf.toString();
	}
	public String closeTable()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</table>");
		return buf.toString();
	}
	public String openContainer(String classes)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("<div class=\"container %s\">", classes));
		return buf.toString();
	}
	public String openDiv(String classes)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("<div class=\"%s\">", classes));
		return buf.toString();
	}
	public String openTable()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<table>");
		buf.append("<th>");
		buf.append("</th>");
		return buf.toString();
	}
	public String openDocument()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<html>");
		buf.append("<head>");
		buf.append(scripts());
		buf.append(styleSheet());
		buf.append("</head>");
		buf.append("<body>");
		return buf.toString();
	}
	public String getErrorDivs(String status, String message, String context)
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row border_bottom\">");
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "Status"));
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "Message"));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row\">");
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", status));
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", message));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row\">");
		buf.append(String.format("<DIV class=\"col-lg-12  bold_alert\"><label >%s</label></DIV>", "Requested resource unknown."));
		buf.append(String.format("<DIV class=\"column_image\"><img src=\"%s/panda404.png\" height=\"300px\" ></DIV>", context));
		buf.append("</DIV>");
		return buf.toString();
	}
	public String getLandingPage()
	{
		return getLandingPage_build("");
	}
	public String getLandingPage_build(String resultCopy)
	{
		final String TITLE = "API Portal Template";
		final String SUBTITLE = "Landing Page";
		StringBuffer buf = new StringBuffer();
		HtmlFormatter html = new HtmlFormatter();
		buf.append(html.openDocument());
		buf.append(html.openContainer("full"));
		buf.append(html.openDiv("row"));
		buf.append(html.openDiv("column_blank"));
		buf.append(String.format("<span class=\"font_head\">%s&emsp;</span><span class=\"font_subhead\">%s&emsp;</span><span class=\"base_link\"><a href=\"%s\">home</a>", TITLE, SUBTITLE, SERVLET_CONTEXT));
		buf.append(html.closeDiv());
		buf.append(html.closeDiv());
		buf.append(html.openDiv("row flexdisplay"));
		buf.append(html.openDiv("column basic"));
		buf.append(getLandingPage_copy());
		buf.append(html.closeDiv());
		buf.append(html.openDiv("column pretty"));
		buf.append(resultCopy);
		buf.append(html.closeDiv());
		buf.append(html.closeDiv());
		buf.append(html.closeContainer());
		buf.append(html.closeDocument());
		return buf.toString();		
	}
	public String getLandingPage_copy()
	{
		String copy_1 = "Welcome";
		
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_normal\"><span>%s</span></DIV>",copy_1));
		buf.append("</DIV>");
		return buf.toString();
	}
	public String getLandingPage_tableHeader()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row border_bottom_darkgray\">");
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "JSON"));
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "HTML"));
		buf.append("</DIV>");
		return buf.toString();
	}
	public String scripts()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<script type=\"text/javascript\">");
		buf.append("</script>");
		return buf.toString();
	}
	public String styleSheet()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\"/>");
		buf.append("<style type=\"text/css\">");
		buf.append("html {"); 
		buf.append("width: 100%;");
		buf.append("height: 100%");
		buf.append("}");
		buf.append("body {"); 
		buf.append("background-color:white;");
		buf.append("width: 100%;");
		buf.append("height: 100%");
		buf.append("}");
		buf.append("select {"); 
		buf.append("padding: 3px 0px 3px 0px;");
		buf.append("width: 100%;");
		buf.append("vertical-align: middle");
		buf.append("}");
		buf.append("input {"); 
		buf.append("width: 100%;");
		buf.append("}");
		buf.append(".flexdisplay {"); 
		buf.append("display: flex;");
		buf.append("min-height: 100%;");
		buf.append("}");
		buf.append(".gutters {"); 
		buf.append("padding: 0px 30px 0px 30px;");
		buf.append("}");
		buf.append(".font_head {"); 
		buf.append("font-family: \"Verdana\";");
		buf.append("font-size: 36;");
		buf.append("}");
		buf.append(".font_subhead {"); 
		buf.append("font-family: \"Verdana\";");
		buf.append("font-size: 28;");
		buf.append("color: darkgray;");
		buf.append("}");
		buf.append(".basic {"); 
		buf.append("font-family: \"Georgia\";");
		buf.append("}");
		buf.append(".basic_head {"); 
		buf.append("font-size: 16px;");
		buf.append("font-weight: bold;");
		buf.append("margin-top: 10px;");
		buf.append("margin-bottom: 5px;");
		buf.append("}");
		buf.append(".basic_normal {"); 
		buf.append("font-size: 14px;");
		buf.append("font-weight: normal;");
		buf.append("margin-top: 10px;");
		buf.append("margin-bottom: 5px;");
		buf.append("}");
		buf.append(".basic_input {"); 
		buf.append("font-size: 12px;");
		buf.append("font-weight: bold;");
		buf.append("}");
		buf.append(".basic_link {"); 
		buf.append("font-size: 12px;");
		buf.append("font-weight: bold;");
		buf.append("}");
		buf.append(".pretty {"); 
		buf.append("font-family: \"Georgia\";");
		buf.append("background-color:#e7f4f9;");
		buf.append("}");
		buf.append(".half {"); 
		buf.append("width: 50%;");
		buf.append("}");
		buf.append(".full {"); 
		buf.append("width: 100%;");
		buf.append("height: 100%;");
		buf.append("}");
		buf.append(".column {"); 
		buf.append("float: left;");
		buf.append("width: 50%;");
		buf.append("padding: 20px;");
		buf.append("}");
		buf.append(".column_blank {"); 
		buf.append("padding: 10px;");
		buf.append("height: 100px;");
		buf.append("background-color:#fff2e6;");
		buf.append("}");
		buf.append(".column_image {"); 
		buf.append("text-align: center;");
		buf.append("}");
		buf.append(".border_bottom {");
		buf.append("border-bottom: 2px solid black;");
		buf.append("margin-bottom: 4px;");
		buf.append("}");
		buf.append(".border_bottom_darkgray {");
		buf.append("color: darkgray;");
		buf.append("border-bottom: 2px solid darkgray;");
		buf.append("margin-bottom: 4px;");
		buf.append("text-align: center;");
		buf.append("}");
		buf.append(".bold_alert {");
		buf.append("text-align: center;");
		buf.append("padding-top: 100px;");
		buf.append("padding-left: 40px;");
		buf.append("font-size: 36;");
		buf.append("color: #d84c7e;");
		buf.append("}");
		buf.append(".try-it {");
		buf.append("padding: 0px 0px 0px 0px;");
		buf.append("font-size: 10;");		
		buf.append("color: darkgray;");
		buf.append("text-align: center;");
		buf.append("vertical-align: text-bottom;");
		buf.append("}");
		buf.append(".font10 {");
		buf.append("font-size: 10;");		
		buf.append("color: darkgray;");
		buf.append("}");
		buf.append("</style>");
		return buf.toString();
	}
}
