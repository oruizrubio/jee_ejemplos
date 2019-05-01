package com.java2s.common;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String moveToPage1(){
		return "demo";
	}
	
	public String moveToPage2(){
		return "page2";
	}
	
}