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
 * validiert wurden, werden die Eingaben aus dem Request in ein Widerspruch Objekt in der Session gespeichert.
 * 
 * Die Formularelemente der JSP haben Ihre Entsprechungen in der mitgelieferten FormBean, welche sich im Request befindet.
 * Wenn sich der Benutzer 'normal' in der Anwendung bewegt, befinden sich lediglich die Daten des Hilfeempfängers in
 * dieser Bean. Diese werden nun in ein gleichartiges Vorgangsobjekt in der Session kopiert.  
 * 
 */
public class VorgangWizard1 extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{

	Vorgang requestVorgang = (Vorgang) form; //Vorgangsobjekt aus dem Request
	HttpSession session = req.getSession();
	Vorgang sessionVorgang = (Vorgang) session.getAttribute("vorgang"); //Vorgangsobjekt aus der Session
	//Kopieren des Hilfeempfängers in den Vorgang der Session...
	sessionVorgang.setHilfeempfaenger(requestVorgang.getHilfeempfaenger());
	//...und speichern 
	session.setAttribute("vorgang", sessionVorgang);
	
	return mapping.findForward("NextStep");
	}
}
