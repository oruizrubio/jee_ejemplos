package com.java2s.common;


import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
 
public class MyValueChangedListener implements ValueChangeListener{

	@Override
	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
		
		UserBean country = (UserBean) FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().get("country");

		country.setLocaleCode(event.getNewValue().toString());
		
	}
	
	
}