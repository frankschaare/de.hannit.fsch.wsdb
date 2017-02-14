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
 * Nachdem der 'weiter' Button auf der JSP 'WiderspruchWizard2.jsp' geklickt wurde und die Eingaben
 * validiert wurden, werden die Eingaben aus dem Request in einm Widerspruch Objekt in der Session gespeichert,
 * sofern ein Vertreter eingegeben wurde.
 * 
 * Das ist dann der Fall, wenn der Nachname nicht leer ist. 
 * 
 */
public class WiderspruchWizard3 extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	Widerspruch ws = (Widerspruch)form;
	HttpSession session = req.getSession();
	Widerspruch sessionWS = (Widerspruch) session.getAttribute("sessionWS");

	// Werte vom Request in die Session übertragen
	sessionWS.setAktenzeichen(ws.getSachgebiet()+"-"+ws.getAktenzeichen());
	sessionWS.setEingangsdatum(ws.getEingangsdatum());
	sessionWS.setAngaben(ws.getAngaben());
	sessionWS.setVerfahrensartID(ws.getVerfahrensartID());
	sessionWS.setVerfahrensart(ws.getVerfahrensart());	
	sessionWS.setGegenstandDesVerfahrensID(ws.getGegenstandDesVerfahrensID());
	sessionWS.setGegenstandDesVerfahrens(ws.getGegenstandDesVerfahrens());
	sessionWS.setRechtsgebietID(ws.getRechtsgebietID());
	sessionWS.setRechtsgebiet(ws.getRechtsgebiet());
	
	return mapping.findForward("NextStep");
	}
}
