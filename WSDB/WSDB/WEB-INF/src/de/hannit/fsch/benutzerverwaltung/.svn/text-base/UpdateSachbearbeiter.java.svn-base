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
public class UpdateSachbearbeiter extends Action 
{
//private static final Log log = LogFactory.getLog(UpdateSachbearbeiter.class);	
private String strForward = null;

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	Sachbearbeiter sb = (Sachbearbeiter)form;
	if (req.isUserInRole("Administrator")) 
	{
		int id = sb.getId(); 
				
		int anzahlUpdates = Java2SQLMapper.updateSachbearbeiter(sb,req.getRemoteUser());
		switch (anzahlUpdates) 
		{
		case 1 : // Update ist OK
		req.setAttribute("sb",Java2SQLMapper.getSachbearbeiter(id));			
		this.setErrors("ErfassungBenutzer.SpeichernErfolgreich",req);	
		strForward = "success";
		break;

		default : // Die Welt ist schlecht
		this.setErrors("ErfassungBenutzer.SQLFehler",req);	
		strForward = "input";
		break;
		}		
	} 
	else 
	{
		this.setErrors("BearbeitungRechte.KeinAdministrator",req);
		strForward = "input";
	}

		if (strForward.equalsIgnoreCase("input")) 
		{
		return mapping.getInputForward();	
		} 
		else 
		{
		return mapping.findForward("success");
		}
	}
	
	/**
	 * Wenn die Speicherung erfolgreich war, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt ;-)</ul>
	 * <ul>eine Übersicht aller vorhanden Datensätze, geordnet nach id erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * </li
	 */	
	private void setErrors(String key,HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(key));
		this.saveErrors(req,errors); 

	}	

	
}
