<%@ page import="com.jimmarks.template.HtmlFormatter"%>
<%
	HtmlFormatter htmlFormatter = new HtmlFormatter();
	out.println(htmlFormatter.getLandingPage());
%>
