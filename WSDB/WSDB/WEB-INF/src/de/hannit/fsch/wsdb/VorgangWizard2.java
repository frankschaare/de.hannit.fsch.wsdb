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

import de.hannit.fsch.benutzerverwaltung.Vertreter;

/**
 * @author fsch
 *
 * Nachdem der 'weiter' Button auf der JSP 'WiderspruchWizard2.jsp' geklickt wurde und die Eingaben
 * validiert wurden, werden die Eingaben aus dem Request in einm Widerspruch Objekt in der Session gespeichert,
 * sofern ein Vertreter eingegeben wurde.
 * 
 * Das ist dann der Fall, wenn der Nachname nicht leer ist. 
 * 
 */
public class VorgangWizard2 extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	Vorgang requestVorgang = (Vorgang) form;
	HttpSession session = req.getSession();
	Vorgang sessionV = (Vorgang) session.getAttribute("vorgang");

	Vertreter v = requestVorgang.getVertreter();
	
	if (v.getNachname() != null && v.getNachname().length() > 0) 
	{
	sessionV.setVertreter(v); //Dabei wird automatisch vertreterVorhanden = true 
	session.setAttribute("vorgang",sessionV);
	} 
	
	return mapping.findForward("NextStep");
	}
}
