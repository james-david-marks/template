/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.model;
import java.util.ArrayList;

public class StringParsingUtilities 
{
    public void appendLine(StringBuffer buf)
    {
        buf.append("\n");
    }
    public void appendLine(StringBuffer buf, String s)
    {
        buf.append(s + "\n");
    }
    public void appendLineIndented(StringBuffer buf, String s, int level)
    {
        buf.append(indent(level) + s + "\n");
    }
	public String convertAccessorMutator(String source)
	{
		final String SPACE = " ";
		final String LEFTCURLYBRACKET = "{";
		final String RIGHTCURLYBRACKET = "}";
		String target = source.replaceAll("\\s+"," ").trim();
		String[] terms = target.split(SPACE);
		ArrayList termsArray = new ArrayList();
		StringBuffer buf = new StringBuffer();
		for(String s : terms)
		{
			if(!s.equals(LEFTCURLYBRACKET)&&!s.equalsIgnoreCase(RIGHTCURLYBRACKET))
			{
				termsArray.add(s);
			}
		}
		buf.append(String.format("public %s get%s(){ return this.%s; }\n", 
				termsArray.get(1), termsArray.get(2), termsArray.get(7)));
		buf.append(String.format("public void set%s(%s %s){ this.%s = %s; }\n", 
				termsArray.get(2), termsArray.get(1), termsArray.get(7), termsArray.get(7), termsArray.get(7)));
		return buf.toString();
	}
	public String convertAccessorMutatorList(String source)
	{
		StringBuffer buf = new StringBuffer();
		String[] instances = source.trim().split("public");
		for(String s : instances)
		{
			if(s.trim().length() > 0)
			{
				buf.append(convertAccessorMutator(String.format("public %s\n",  s)));
			}
		}		
		return buf.toString();
	}
    public String indent(int level)
    {
        int INDENTSIZE = 4;
        String SPACE = " ";
        String s = "";
        for (int i = 0; i < level * INDENTSIZE; i++)
        {
            s += SPACE;
        }
        return s;
    }
}
