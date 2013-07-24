package am.utils;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {
	public static void reportError(FacesContext facesContext,String message, String detail,Exception exception){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message, detail));
		if (exception != null)
		{
			facesContext.getExternalContext().log(message, exception);
		}
	}
	public static void reportError(FacesContext facesContext,String forComponent, String message, String detail,Exception exception){
		facesContext.addMessage(forComponent, new FacesMessage(FacesMessage.SEVERITY_ERROR,message, detail));
		if (exception != null)
		{
			facesContext.getExternalContext().log(message, exception);
		}
	}
	
	public static Object getSessionObject(String aName, Class aClass) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application app = facesContext.getApplication();
		ELContext elcontext = FacesContext.getCurrentInstance().getELContext();
		ValueExpression userValueExpression = app.getExpressionFactory().createValueExpression(elcontext, "#{sessionScope."+aName+"}", aClass);
		return userValueExpression.getValue(elcontext);
	}
	
}
