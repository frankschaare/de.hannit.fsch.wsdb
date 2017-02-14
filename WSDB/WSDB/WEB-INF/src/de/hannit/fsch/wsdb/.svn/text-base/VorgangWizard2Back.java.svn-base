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
 * Nachdem der 'zurück' Button auf der JSP 'WiderspruchWizard2.jsp' geklickt wurde,
 * wird die Vorgang-Bean in der Session in den Request gelegt und zur Wizard Seite davor
 * verwiesen: 
 * 
 */
public class VorgangWizard2Back extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	HttpSession session = req.getSession();
	Vorgang sessionV = (Vorgang) session.getAttribute("vorgang");
	req.setAttribute("vStep1",sessionV);
	
	return mapping.findForward("NextStep");
	}
}
