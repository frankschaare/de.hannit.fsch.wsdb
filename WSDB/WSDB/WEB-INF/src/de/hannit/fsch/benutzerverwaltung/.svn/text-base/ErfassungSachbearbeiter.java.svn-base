/*
 * Created on 02.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.benutzerverwaltung;

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

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Speichert ein Ereignis und erledigt einige Vorarbeiten.
 * 
 * Die Methode Java2SQLMapper.setEinrichtung(e) versucht, die Einrichtung zu speichern.
 * Sie gibt einen Integer Wert 'id' zurück, der drei Zustände widergibt:
 * <ul>
 * <li>id = 0: 	Datensatzfehler, mehrere Datensätze mit gleichem Namen vorhanden</li>
 * <li>id = 1: 	Es wurde genau eine mit gleichem Namen gefunden<br>
 * 				Die Benutzer werden zur Seite ListEinrichtung.jsp zurückgeführt.<br>
 * 				Die Einrichtung wird nicht gespeichert.</li>
 * <li>id = x: 	Die Speicherung war erfolgreich.</li>
 * <ul>
 *  
 */
public class ErfassungSachbearbeiter extends Action 
{


	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	String strSQL = null;
	String strForward = null;	
	int id = 0; 
	Sachbearbeiter sb = (Sachbearbeiter)form;
	//HttpSession session = req.getSession();	

		// Prüfung, ob bereits ein Sachbearbeiter mit gleichem Namen gespeichert ist: 	
		strSQL =	"SELECT count(*) "+
					"FROM users "+
					"WHERE (user_name = '"+sb.getUsername()+"')";	
				
		switch 	(Java2SQLMapper.getAnzahlDatensätze(strSQL)) 
				{
				case 0 : // Kein Datensatz gefunden, so soll´s sein
				id = Java2SQLMapper.insertSachbearbeiter(sb, req.getRemoteUser());
				this.speichernErfolgreich(sb,req,id);
				strForward = "success";
				break;

				case 1 : // Ein Datensatz gefunden
				this.fehlerDatensatzVorhanden(req);
				strForward = "InputForward";
				break;

				default :
				break;
				}
		if (strForward.equalsIgnoreCase("success")) {return mapping.findForward(strForward);} 
		else {return mapping.getInputForward();}
		
		
 
	}
	
	/**
	 * Wenn die Speicherung erfolgreich war, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt ;-)</ul>
	 * <ul>eine Übersicht aller vorhanden Datensätze, geordnet nach id erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * </li
	 */	
	private void speichernErfolgreich(Sachbearbeiter sb,HttpServletRequest req, int id)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("ErfassungBenutzer.SpeichernErfolgreich"));
		this.saveErrors(req,errors); 

		req.setAttribute("sb",Java2SQLMapper.getSachbearbeiter(id));		

	}	

	/**
	 * Wenn ein Datensatz vorhanden ist, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt</ul>
	 * <ul>das gefundene Ereignis im Request abgespeichert</ul>
	 * </li
	 */	
	private void fehlerDatensatzVorhanden(HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("ErfassungBenutzer.DatensatzVorhanden"));
		this.saveErrors(req,errors); 


	}
	
}
