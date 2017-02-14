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

import org.apache.commons.beanutils.RowSetDynaClass;
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
 * Diese Action markiert den Vorgang endgültig als gelöscht. Dazu wird ein Enddatum und 
 * die UserID des Benutzers in die Tabelle geschrieben. 
 */
public class LoeschenVorgang extends Action 
{
	private RowSetDynaClass rsdc = null;
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	String strForward = null;
	String strSQL = null;
	HttpSession session = req.getSession();
	Vorgang v = (Vorgang) session.getAttribute("vorgang");
	int widerspruchsID = v.getWiderspruch().getId();
	RechteManager rm = new RechteManager();
	
	// Berechtigung wird nochmals geprüft, wass soll's:
	if (rm.pruefeBerechtigung(req,v.getWiderspruch().getAktenzeichen())) 
	{// User ist berechtigt

	Java2SQLMapper.deleteWiderspruch(widerspruchsID,req.getRemoteUser());

		// Liste der Vorgänge aktualisieren; Wenn die Liste aus der Schnellsuche generiert wurde, liegt das ursprüngliche SQL in der Session:
		Object anfrageSchnellsuche = session.getAttribute("AnfrageSchnellsuche");
		if (anfrageSchnellsuche != null) // Anfrage aus Schnellsuche 
		{
		strSQL = (String) anfrageSchnellsuche;	
		} 
		else // Anfrage aus Vorgang bearbeiten
		{
		strSQL = "Select * from vwWiderspruch";
		}
	rsdc = Java2SQLMapper.getRowSetDynaClass(strSQL);
	session.setAttribute("Ergebnis",rsdc);	

	this.setMessage(req,"WiderspruchLoeschen.Erfolgreich");
	strForward = "success";	
	} 
	else 
	{
	strForward = rm.setBerechtigungsFehler(req);
	}
	
	return mapping.findForward(strForward);


	}
	
	/**
	 * 
	 */	
	private void setMessage(HttpServletRequest req,String messageKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(messageKey));
		this.saveErrors(req,errors); 
	}	

	
}
