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
import de.hannit.fsch.benutzerverwaltung.Sachbearbeiter;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Diese Action markiert den Vorgang endgültig als gelöscht. Dazu wird ein Enddatum und 
 * die UserID des Benutzers in die Tabelle geschrieben. 
 */
public class LoeschenBenutzer extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	RowSetDynaClass rsdc = null;
	HttpSession session = req.getSession();
	String strForward = null;
	String strSQL = null;
	Sachbearbeiter sb = (Sachbearbeiter) form;
	int id = sb.getId();
	RechteManager rm = new RechteManager();
	
	// Berechtigung wird nochmals geprüft, wass soll's:
	if (rm.pruefeLoeschBerechtigung(req)) 
	{// User ist berechtigt
	strSQL = 	"Update users "+
				"Set geloeschtAm = '" + Java2SQLMapper.getZeitstempel()+"', "+
				"geloeschtVon = '"+req.getRemoteUser()+"' "+
				"WHERE userid = "+id;
	Java2SQLMapper.deleteDatensatz(strSQL);

	rsdc = Java2SQLMapper.getRowSetDynaClass("Select * from users WHERE geloeschtAm IS NULL");
	//List rows = rsdc.getRows();
	
	session.setAttribute("Ergebnis",rsdc);

	this.setMessage(req,"BenutzerLoeschen.Erfolgreich");
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
