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

import de.hannit.fsch.benutzerverwaltung.Einrichtung;
import de.hannit.fsch.datenbank.Java2SQLMapper;
import de.hannit.fsch.datenbank.SQLManager;

/**
 * @author fsch
 *
 * Speichert eine Einrichtung und erledigt einige Vorarbeiten.
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
public class ErfassungEinrichtung extends Action 
{
//private static final Log log = LogFactory.getLog(ErfassungEinrichtung.class);	
private SQLManager sqlManager = new SQLManager();

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	//String strSQL = null; 
	//RowSetDynaClass rsdc;
	Einrichtung e = (Einrichtung)form;
	//Adresse a = e.getAdresse();
	HttpSession session = req.getSession();

	int einrichtungID = Java2SQLMapper.setEinrichtung(e);

	switch (einrichtungID) 
		{
		case 0 : // Datensatzfehler, mehrere Datensätze mit gleichem Namen vorhanden
			break;
		
		case 1 : // Es ist genau ein gleicher Datensatz vorhanden
				this.fehlerDatensatzVorhanden(e,req);
				break;
		default : // Fehler, es sind mehrere gleiche Datensätze vorhanden
				this.speichernErfolgreich(e,req,session);
				break;
			}
			
	// Zum Schluss wird noch die Liste der Einrichtungen in der Formularbean aktualisiert:
	FormularBean fb = (FormularBean) session.getAttribute("fb");
	fb.refreshEinrichtungen();
	session.setAttribute("fb",fb);
		
	return mapping.findForward("success");
	}
	
	/**
	 * Wenn die Speicherung erfolgreich war, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt ;-)</ul>
	 * <ul>eine Übersicht aller vorhanden Datensätze, geordnet nach id erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * </li
	 */	
	private void speichernErfolgreich(Einrichtung e,HttpServletRequest req,HttpSession session)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("ErfassungEinrichtung.Erfolgreich"));
		this.saveErrors(req,errors); 
		//String strSQL = 	"Select * from vwEinrichtung order by id";
				
		FormularBean fb = (FormularBean) session.getAttribute("fb");
		fb.refreshEinrichtungen();
		session.setAttribute("fb",fb);

	}	

	/**
	 * Wenn ein Datensatz vorhanden ist, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt</ul>
	 * <ul>eine Übersicht aller bereits vorhanden Datensätze erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * <ul>die eingehende FormBean in der Session gespeichert (für späteres 'speichernwaseswolle')</ul> 
	 * </li
	 */	
	private void fehlerDatensatzVorhanden(Einrichtung e,HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("ErfassungEinrichtung.DatensatzVorhanden"));
		this.saveErrors(req,errors); 
		String strSQL = 	"Select * " +
							"from vwEinrichtung where name = '"+
							e.getName().trim()+"'";		
		req.setAttribute("Ergebnis",sqlManager.getRowSetDynaClass(strSQL));

	}
	
}
