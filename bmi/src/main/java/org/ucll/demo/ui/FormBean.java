package org.ucll.demo.ui;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ucll.demo.ParameterizedIllegalArgumentException;

public class FormBean extends ServiceBean {

	protected void setMessage(Exception e) {
		ResourceBundle bundle = getResourceBundle();	
	
		String message = e.getMessage();
		if(e instanceof ParameterizedIllegalArgumentException){
			Object[] parameters = ((ParameterizedIllegalArgumentException) e).getParameters();
			message = MessageFormat.format(bundle.getString(message), parameters);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}

	protected ResourceBundle getResourceBundle() {
			FacesContext context = FacesContext.getCurrentInstance();
			Locale locale = context.getViewRoot().getLocale();
			ResourceBundle bundle = ResourceBundle.getBundle("org.ucll.demo.ui.bmi.messages",locale);
			return bundle;
		}

}