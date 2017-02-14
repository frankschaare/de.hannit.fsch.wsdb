/*
 * Created on 02.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.wsdb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author fsch
 *
 * Nachdem der 'weiter' Button auf der JSP 'WiderspruchWizard1.jsp' geklickt wurde und die Eingaben
 * validiert wurden, werden die Eingaben aus dem Request in einm Widerspruch Objekt in der Session gespeichert. 
 * 
 */
public class WiderspruchWizard1 extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	Widerspruch ws = (Widerspruch)form;
	HttpSession session = req.getSession();
	session.setAttribute("sessionWS", ws);
	
	return mapping.findForward("NextStep");
	}
}
