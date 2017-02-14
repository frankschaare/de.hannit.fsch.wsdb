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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.benutzerverwaltung.RechteManager;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Von der Übersichtsseite 'bearbeiten\Vorgänge' kommend, kann der Benutzer Vorgänge löschen.
 * Dazu wird der Vorgang aus der Datenbank geladen und auf der LoeschenVorgang.jsp dargestellt.
 * Nach dem Bestätigen eines Confirms kann der Benutzer den Vorgang löschen, wenn er das Recht dazu 
 * hat.
 * 
 * Zum löschen wird ein Löschdatum und die UserID in die Datenbank eingetragen. Tatsächlich werden keine 
 * Daten gelöscht. 
 */
public class LoeschenVorgangConfirm extends Action 
{
	private String strForward = "KeineBerechtigung";

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	String widerspruchsID = req.getParameter("id");
	Vorgang v = Java2SQLMapper.getVorgang(Integer.parseInt(widerspruchsID));
	RechteManager rm = new RechteManager();
	HttpSession session = req.getSession();
	
	if (rm.pruefeBerechtigung(req,v.getWiderspruch().getAktenzeichen())) 
	{
		session.setAttribute("vorgang",v);
		this.ladenErfolgreich(req);
		this.strForward = "success";
	} 
	else // Fehlerobjekt speichern und zum Input zurück:
	{
		this.strForward = rm.setBerechtigungsFehler(req);
	}
	
	return mapping.findForward(strForward);	

	


	}

	/**
	 * 
	 */	
	private void ladenErfolgreich(HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();
		errors.add("DatensatzVorhanden",new ActionError("WiderspruchLoeschen.Geladen"));
		this.saveErrors(req,errors); 
	}	
		
}
