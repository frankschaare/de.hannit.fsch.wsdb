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
 * Von der �bersichtsseite 'bearbeiten\Vorg�nge' kommend, kann der Benutzer Vorg�nge l�schen.
 * Dazu wird der Vorgang aus der Datenbank geladen und auf der LoeschenVorgang.jsp dargestellt.
 * Nach dem Best�tigen eines Confirms kann der Benutzer den Vorgang l�schen, wenn er das Recht dazu 
 * hat.
 * 
 * Zum l�schen wird ein L�schdatum und die UserID in die Datenbank eingetragen. Tats�chlich werden keine 
 * Daten gel�scht. 
 */
public class LoeschenBenutzerConfirm extends Action 
{
	private String strForward = "KeineBerechtigung";

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	int id = Integer.parseInt(req.getParameter("id"));
	Sachbearbeiter sb = Java2SQLMapper.getSachbearbeiter(id);
	RechteManager rm = new RechteManager();
	
	if (rm.pruefeLoeschBerechtigung(req)) 
	{
		sb.setAktion(String.valueOf(Java2SQLMapper.SQL_DELETE));
		req.setAttribute("sb",sb);
		this.ladenErfolgreich(req);
		this.strForward = "success";
	} 
	else // Fehlerobjekt speichern und zum Input zur�ck:
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
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("BenutzerLoeschen.Geladen"));
		this.saveErrors(req,errors); 
	}	
		
}
