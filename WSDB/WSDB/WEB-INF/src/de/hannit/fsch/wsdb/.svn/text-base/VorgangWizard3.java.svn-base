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

import de.hannit.fsch.benutzerverwaltung.RechteManager;

/**
 * @author fsch
 *
 * Nachdem der 'weiter' Button auf der JSP 'WiderspruchWizard3.jsp' geklickt wurde und die Eingaben
 * validiert wurden, werden die Eingaben aus dem Request in ein Vorgang Objekt in der Session gespeichert.
 * 
 * Vorher muss allerdings geprüft werden, ob der Benutzer das Recht hat, diesen Vorgang zu speichern.
 * 
 * Ausserdem wird hier das Token 'aktion' auf CREATE gesetzt, was den nachfolgenden
 * actions zeigt, das hier ein neuer Vorgang gespeichert werden soll. 
 * 
 */
public class VorgangWizard3 extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	String strForward = "NextStep";
	
	Vorgang requestVorgang = (Vorgang) form;
	Widerspruch requestWS = requestVorgang.getWiderspruch();
	
	HttpSession session = req.getSession();
	Vorgang sessionVorgang = (Vorgang) session.getAttribute("vorgang");
	Widerspruch sessionWS = sessionVorgang.getWiderspruch();
	
	String strSachgebiet = requestVorgang.getWiderspruch().getSachgebiet();
	
	if (req.isUserInRole(strSachgebiet)) //Hat der Benutzer das Recht den Vorgang zu speichern ? 
	{
	// Werte vom Request in die Session übertragen
	sessionWS.setAktenzeichen(requestWS.getSachgebiet()+"-"+requestWS.getAktenzeichen());
	sessionWS.setEingangsdatum(requestWS.getEingangsdatum());
	sessionWS.setAngaben(requestWS.getAngaben());
	sessionWS.setVerfahrensartID(requestWS.getVerfahrensartID());
	sessionWS.setVerfahrensart(requestWS.getVerfahrensart());	
	sessionWS.setGegenstandDesVerfahrensID(requestWS.getGegenstandDesVerfahrensID());
	sessionWS.setGegenstandDesVerfahrens(requestWS.getGegenstandDesVerfahrens());
	sessionWS.setRechtsgebietID(requestWS.getRechtsgebietID());
	sessionWS.setRechtsgebiet(requestWS.getRechtsgebiet());	
	sessionWS.setDae(requestWS.getDae());
	sessionWS.setDatumrbh(requestWS.getDatumrbh());
	
	sessionVorgang.setWiderspruch(sessionWS);
	//Token wird auf CREATE gesetzt, damit wissen die nachfolgenden Klassen, das ein neuer Vorgang eingefügt werden soll
	sessionVorgang.setAktion(sessionVorgang.CREATE);
	session.setAttribute("vorgang",sessionVorgang);	
	} 
	else // Nein, hat er nicht; mit Fehler zurück zum Input
	{
	RechteManager drm = new RechteManager();
	strForward = drm.setBerechtigungsFehler(req);
	}
	
	return mapping.findForward(strForward);
	}
}
