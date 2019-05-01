package com.java2s.common;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="receipt")
@SessionScoped
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}