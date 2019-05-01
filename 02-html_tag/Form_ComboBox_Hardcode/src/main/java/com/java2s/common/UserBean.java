package com.java2s.common;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{
	public String favItem = "Black";
	public String getFavItem() {
		return favItem;
	}

	public void setFavItem(String f) {
		this.favItem = f;
	}
}