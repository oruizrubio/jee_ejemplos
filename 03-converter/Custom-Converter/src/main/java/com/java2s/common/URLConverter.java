package com.java2s.common;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.java2s.common.URLConverter")
public class URLConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		String HTTP = "http://";
		StringBuilder url = new StringBuilder();

		if(!value.startsWith(HTTP, 0)){
			url.append(HTTP);
		}
		url.append(value);
		
		if(url.toString().length() > 10){
			FacesMessage msg = 
				new FacesMessage("URL Conversion error.", 
						"Invalid URL format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
		return url.toString();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		return value.toString();
		
	}
	
	
}