package com.java2s.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="user")
@SessionScoped
public class UserBean{

	public String text = "This is Text!";
	public String htmlInput = "<a href='http://java2s.com'>java2s.com</a>";
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHtmlInput() {
		return htmlInput;
	}
	public void setHtmlInput(String htmlInput) {
		this.htmlInput = htmlInput;
	}
	
}