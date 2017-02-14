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
public class LoeschenEreignis extends Action 
{
//private static final Log log = LogFactory.getLog(LoeschenEreignis.class);	

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	String strSQL = null;
	//int ereignisdID = 0; 
	Ereignis ereignis = (Ereignis)form;
	HttpSession session = req.getSession();
	
	strSQL = "Delete from Ereignis WHERE (id = "+ereignis.getId()+")";		
	Java2SQLMapper.deleteDatensatz(strSQL);

	RowSetDynaClass rsdc = Java2SQLMapper.getRowSetDynaClass("Select * from Ereignis");
	session.setAttribute("Ergebnis",rsdc);			

	this.speichernErfolgreich(ereignis,req,session);

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
	private void speichernErfolgreich(Ereignis e,HttpServletRequest req,HttpSession session)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("UpdateEreignis.Erfolgreich"));
		this.saveErrors(req,errors); 
		String strSQL = 	"Select * from Ereignis order by organisationseinheitID";
				
		session.setAttribute("ergebnis",Java2SQLMapper.getRowSetDynaClass(strSQL));

	}	

	
}
