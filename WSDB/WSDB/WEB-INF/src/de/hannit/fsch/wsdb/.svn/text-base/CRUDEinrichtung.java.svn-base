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
import de.hannit.fsch.datenbank.CRUDAction;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author Frank Schaare am 25.02.2005 
 *
 * CRUD Klasse für das Objekt Einrichtung
 * 
 * C(reate)U(pdate)D(elete) Actions sind von zentraler Bedeutung für die Anwendung.
 * Grundsätzlich gibt es zwei Parameter, die die Gesamtfunktionalität steuern:
 * @param crud: der Schlüssel für die gewünschte Aktion kann die folgenden Werte annehmen:
 * <ul>
 * <li>0 = SELECT</li>
 * <li>1 = CREATE</li>
 * <li>2 = UPDATE</li>
 * <li>3 = DELETE</li>
 * </ul>
 * 
 * Zusätzlich wird der Parameter 'loaded' erwartet:
 * @param id: die id des zu bearbeitenden Objektes
 * 
 * Aus diesen Parametern ergibt sich folgende Matrix:
 * crud = 0, id ist vorhanden -> Objekt laden
 * crud = 0, id nicht vorhanden -> Fehler
 * crud = 1, id ist vorhanden -> Fehler
 * crud = 1, id nicht vorhanden -> ForwardAction zu CREATE{Objekt}.jsp
 * crud = 2, id ist vorhanden -> Objekt laden und Forward zu UPDATE{Objekt}.jsp
 * crud = 2, id nicht vorhanden -> Objekt updaten (id gibt es nochmals im Objekt selber)
 * crud = 3, id ist vorhanden -> Objekt laden Forward zu DELETE{Objekt}.jsp
 * crud = 3, id nicht vorhanden -> Objekt löschen (id gibt es nochmals im Objekt selber)
 * 
 * WICHTIG ! Wenn die Action keinen Requestparameter id erhält, wie kann sie dann wissen,
 * welches Objekt in der Datenbank bearbeitet werden muss ?
 * Antwort: Das Objekt enthält selbst immer auch ein Feld 'id', welches mit der ActionForm
 * übergeben wird.
 * 
 * 
*/
public class CRUDEinrichtung extends Action
{
private String strAktion = null;
private String strID = null;
private int iCRUD;
private int iID;
private boolean loaded;
private String strClass;
private String strForward;
private Einrichtung einrichtung;
//private Einrichtung loadEinrichtung;

	public synchronized ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	this.strAktion = req.getParameter("crud");
	this.strID = req.getParameter("id");
	if (strID != null) {this.iID = Integer.parseInt(strID);}
	this.iCRUD = Integer.parseInt(strAktion);
	this.strClass = form.getClass().getName();
	
	checkLoaded(req); //Prüfe, ob das Objekt erst noch geladen werden muss
	if (!loaded) // Objekt wird für die folgende Action geladen: 
	{
	loadObject(req);	
	} 
	else // ID wird nicht mitgeliefert, Action wird direkt mit der Form ausgeführt:
	{
			switch (iCRUD) 
			{
			case CRUDAction.CREATE :
			create(req,form);
			break;
	
			case CRUDAction.UPDATE :
			update(req,form);
			break;
			
			case CRUDAction.DELETE : // Thema 
			delete(req,form);
			break;
	
			default :
			strForward = "LIST";			
			break;
			}		
	
	}
	// Die Liste der Einrichtungen wird in der Formularbean gespeichert. Damit die Einrichtungsliste aktuell
	// dargestellt wird, wird die Formularbean neu gebaut:
	HttpSession session = req.getSession();
	session.setAttribute("fb", Java2SQLMapper.getFormularbeanForTeams(req));
	return mapping.findForward(strForward);
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Wenn der Anfrageparameter 'loaded' = false ist, wird das Objekt zunächst geladen.
	 * Dazu wird diese Methode aufgerufen, die:
	 * <ul>
	 * <li>Das Objekt aus der Datenbank lädt</li>
	 * <li>als Attribut im Request ablegt</li>	 
	 * <li>Dann wird abhängig von der Variable 'iCRUD' an die Folgeseite weitergeleitet.</li>
	 * </ul>
	 * 
	*/
	private void loadObject(HttpServletRequest req) 
	{
	this.einrichtung = Java2SQLMapper.getEinrichtung(iID);
	req.setAttribute("einrichtung",einrichtung);
	
		switch (iCRUD) 
		{
		case CRUDAction.CREATE :
		this.strForward = "CREATE";
		break;
	
		case CRUDAction.UPDATE :
		this.strForward = "UPDATE";
		break;
		
		case CRUDAction.DELETE : // Thema 
		this.strForward = "DELETE";
		break;
	
		default :
		break;
		}	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Diese Methode wird aufgerufen wenn ein neues Objekt in der Datenbank erstellt werden soll.
	 * Vorab müssen leider noch Plausibilitäten geklärt werden, z.b.
	 * <ul>
	 * <li>Existiert bereits ein gleiches Objekt in der Datenbank ?</li>
	 * <li>Darf der Benutzer das Objekt überhaupt speichern ?</li>	 
	 * </ul>
	 * 
	*/
	private void create(HttpServletRequest req,ActionForm form) 
	{
	einrichtung = (Einrichtung) form;
	einrichtung.setErstelltAm(Java2SQLMapper.getZeitstempel());
	
	String strSQL = "SELECT COUNT (*) FROM Einrichtung WHERE name = '"+
					einrichtung.getName()+"'";
					
			switch (Java2SQLMapper.getAnzahlDatensätze(strSQL)) 
			{
			case 0 : // Kein gleicher Datensatz vorhanden, so soll´s sein
				if (req.isUserInRole("Administrator")) 
				{
				Java2SQLMapper.setEinrichtung(einrichtung);	
				setMessage("SpeichernErfolgreich",strClass+".CREATE.Erfolgreich",req);					
				} 
				else 
				{
				setMessage("KeinAdministrator",strClass+".KeinAdministrator",req);
				}
			break;
		
			default :
			setMessage("DatensatzVorhanden",strClass+".DatensatzVorhanden",req);
			break;
			}
		
	strForward = "LIST";	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Diese Methode wird aufgerufen wenn ein neues Objekt in der Datenbank erstellt werden soll.
	 * Vorab müssen leider noch Plausibilitäten geklärt werden, z.b.
	 * <ul>
	 * <li>Existiert bereits ein gleiches Objekt in der Datenbank ?</li>
	 * <li>Darf der Benutzer das Objekt überhaupt speichern ?</li>	 
	 * </ul>
	 * 
	*/
	private void update(HttpServletRequest req,ActionForm form) 
	{				
		if (req.isUserInRole("Administrator")) 
		{
		Java2SQLMapper.updateEinrichtung((Einrichtung) form, req.getRemoteUser());	
		setMessage("UpdateErfolgreich",strClass+".UPDATE.Erfolgreich",req);					
		} 
		else 
		{
		setMessage("KeinAdministrator",strClass+".KeinAdministrator",req);
		}
	strForward = "LIST";	
	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Diese Methode wird aufgerufen wenn ein neues Objekt in der Datenbank erstellt werden soll.
	 * Vorab müssen leider noch Plausibilitäten geklärt werden, z.b.
	 * <ul>
	 * <li>Existiert bereits ein gleiches Objekt in der Datenbank ?</li>
	 * <li>Darf der Benutzer das Objekt überhaupt speichern ?</li>	 
	 * </ul>
	 * 
	*/
	private void delete(HttpServletRequest req,ActionForm form) 
	{				
		if (req.isUserInRole("Administrator")) 
		{
		Einrichtung e = (Einrichtung) form; 
		String strSQL = "UPDATE Einrichtung SET geloeschtAm = '" +
						Java2SQLMapper.getZeitstempel() + "', " +
						"geloeschtVon = '"+ req.getRemoteUser() + "' "+
						"WHERE id = "+e.getId();
		
		Java2SQLMapper.deleteDatensatz(strSQL);	
		setMessage("LoeschenErfolgreich",strClass+".DELETE.Erfolgreich",req);					
		} 
		else 
		{
		setMessage("KeinAdministrator",strClass+".KeinAdministrator",req);
		}
	strForward = "LIST";	
	
	}
	/*
	 * Funktioniert leider noch nicht 
	 */
	/*private Einrichtung load(Integer id) 
	{
		//TODO: Hibernate loading unktioniert noch nicht...
	
	return loadEinrichtung;	
	}*/

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Prüft, ob das Objekt erst noch aus der Datenbank geladen werden muss
	 * 
	*/
	private void checkLoaded(HttpServletRequest req) 
	{
		try 
		{
			if (req.getParameter("loaded").equalsIgnoreCase("true")) 
			{
			this.loaded = true;
			} 
			else 
			{
			this.loaded = false;
			}			
		} 
		catch (NullPointerException e) 
		{
			this.loaded = true;
		}
	
	}

	/**
	 * Wenn die Speicherung erfolgreich war, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt ;-)</ul>
	 * <ul>eine Übersicht aller vorhanden Datensätze, geordnet nach id erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * </li
	 * @param String strKey: Der Schlüssel aus der Resource.properties Datei.
	 * @param String strProperty: Der Bezeichner, unter dem der Error gespeichert wird.
	 * kann ein beliebiger String sein, oder die Konstante 'ActionErrors.GLOBAL_ERROR'
	 */	
	private void setMessage (String strProperty,String strKey,HttpServletRequest req)
	{
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(strKey));
	this.saveErrors(req,errors);
	}

}
