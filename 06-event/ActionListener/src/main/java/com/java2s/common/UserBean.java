package com.java2s.common;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
 
@ManagedBean(name="normal")
@SessionScoped
public class UserBean implements java.io.Serializable{

	public String buttonId="java2s.com"; 
	
	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public void printIt(ActionEvent event){
		//Get submit button id
		buttonId = event.getComponent().getClientId();
	}
	
	public String outcome(){
		return "result";
	}
}