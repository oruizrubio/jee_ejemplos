package com.java2s.common;

import javax.inject.Named;


@Named
public class PrinterImpl implements Printer{
 
	public String print() {
		
		return "JSF 2 + Spring Integration";
	
	}
 
}