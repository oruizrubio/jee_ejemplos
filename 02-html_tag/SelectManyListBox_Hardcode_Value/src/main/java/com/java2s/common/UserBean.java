package com.java2s.common;


import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{
	public String[] item = {"A", "B"};
	public String[] getItem() {
		return item;
	}

	public void setItem(String[] i) {
		this.item = i;
	}

	public String getItemString() {
		return Arrays.toString(item);
	}
}