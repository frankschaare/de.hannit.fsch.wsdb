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

import de.hannit.fsch.datenbank.CRUDAction;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author Frank Schaare am 25.02.2005
 * @version 1.1 vom 08.04.2005 
 *
 * <h3>CRUD Klasse für das Objekt Thema</h3>
 * 
 * C(reate)U(pdate)D(elete) Actions sind von zentraler Bedeutung für die Anwendung.
 * Sie werden immer wieder kursiv aufgerufen und reduzieren die Anzahl der benötigten Klassen
 * (und damit die Übersicht) erheblich.
 * 
 * Grundsätzlich gibt es zwei Parameter, die die Gesamtfunktionalität steuern:
 * @param crud: der Schlüssel für die gewünschte Aktion kann die folgenden Werte annehmen:
 * <ul>
 * <li>0 = SELECT</li>
 * <li>1 = CREATE</li>
 * <li>2 = UPDATE</li>
 * <li>3 = DELETE</li>
 * </ul>
 * 
 * Die Aktion wird als Integer Wert an die CRUD-Aktion übergeben, damit die Aktion von aussen nicht 
 * so gut lesbar ist (macheDies.do?action=delete ist wesentlich deutlicher als macheDies.do?crud=3). 
 * Ausserdem lassen sich die Integer Werte wesentlich besser durch Switch-Anweisungen verwzweigen.
 * 
 * Die CRUD Parameter sind Konstanten der Klasse 'CRUDAction' und damit für alle CRUD Aktionen gleich.
 * 
 * Zusätzlich wird der Parameter 'loaded' erwartet, der mitteilt, ob das Objekt bereits geladen ist:
 * @param loaded [true|false]: Objekt bereits geladen ?
 * 
 * Konstant sind ebenfalls die Forwards. In der Regel gibt es vier Seiten, welche den Aktionen entsprechen:
 * <ul>
 * <li>LIST (SELECT) - eine Übersichtsliste aller Treffer</li>
 * <li>CREATE - Objekt neu erstellen (id darf nicht vorhanden sein, SQL = INSERT)</li>
 * <li>UPDATE - Objekt bearbeiten (id ist vorhanden, SQL = UPDATE)</li>
 * <li>DELETE - Objekt löschen (id ist vorhanden, SQL = DELETE, Sicherheitshinweis an den Benutzer)</li>
 * </ul>
 *  
 * WICHTIG ! Wenn die Action keinen Requestparameter id erhält, wie kann sie dann wissen,
 * welches Objekt in der Datenbank bearbeitet werden muss ?
 * Antwort: Das Objekt enthält selbst immer auch ein Feld 'id', welches mit der ActionForm
 * übergeben wird.
 * 
 * 
*/
public class CRUDThema extends Action
{
/**
 * der Übergabeparameter crud 
 */
private String strAktion = null;
private String strID = null;
private int iCRUD;
private int iID;
private boolean loaded;

private String strClass;
private String strForward;
private String strSQL;

private Thema thema;
//private Thema loadThema;

	public synchronized ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	// Der Parameter crud sollte beim Aufruf einer CRUD-Aktion immer vorhanden sein
	if (req.getParameter("crud") != null)
	{
	this.strAktion = req.getParameter("crud");
	this.iCRUD = Integer.parseInt(strAktion);
	}
	// Fehlt der Parameter, wird zu einer allgemeinen Fehlerseite verzweigt, die den Fehlerstack als Kommentar enthält
	else 
	{
	return mapping.findForward("CRUDFehler");
	}

	// Erfolgt der Aufruf aus einer Liste, wird die Objekt-ID in der Anfrage mitgeliefert.
	if (req.getParameter("id") != null)
	{
	this.strID = req.getParameter("id");
	this.iID = Integer.parseInt(strID);
	} 

	// Der Klassenname dient als Prefix, um Text aus der Properties-Datei zu laden.
	this.strClass = form.getClass().getName();
	
	/**
	 * Die Übergabeparameter sollten nun sauber vorliegen.
	 * Als nächstes wird der Parameter 'loaded' überprüft.
	 * Dieser legt fest, ob das Objekt noch geladen werden muss 
	 */	
	checkLoaded(req); 

	/**
	 * Ist das Objekt NICHT geladen, wird es nun aus der Datenbank geladen.
	 * Im nächsten Schritt wird dann zur nächsten Seite verzweigt
	 */		
	if (!loaded)  
	{
	loadObject(req);	
	} 
	/**
	 * oder das Objekt IST geladen.
	 * Dann kann die gewünschte Aktion mit dem Objekt durchgeführt werden:
	 */			
	else 
	{
		this.thema = (Thema) form;
		switch (iCRUD) 
		{
		case CRUDAction.CREATE :
		create(req,form);
		break;

		case CRUDAction.UPDATE :
		update(req);
		strForward = "ListeThema";	
		break;
			
		case CRUDAction.DELETE : 
		delete(req);
		strForward = "ListeThema";	
		break;
	
		default :
		strForward = "ListeThema";			
		break;
		}		

		
	}

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
	this.thema = Java2SQLMapper.getThema(iID);
	req.setAttribute("thema",thema);
	
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
	Thema testThema = (Thema) form;
	
	String strSQL = "SELECT COUNT (*) FROM [Formulare_Thema] WHERE thema = '"+
					testThema.getThema()+
					"' AND team = '"+
					testThema.getTeam()+"'";
			switch (Java2SQLMapper.getAnzahlDatensätze(strSQL)) 
			{
			case 0 : // Kein gleicher Datensatz vorhanden, so soll´s sein
				if (req.isUserInRole("Administrator")) 
				{
				Java2SQLMapper.insertThema(testThema.getThema(),testThema.getTeam());	
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
		
	strForward = "ListeThema";	
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
	private void update(HttpServletRequest req) 
	{				
		if (req.isUserInRole("Administrator")) 
		{
		/*int anzDaten = */Java2SQLMapper.updateThema(this.thema);
		setMessage("UpdateErfolgreich",strClass+".UPDATE.Erfolgreich",req);					
		} 
		else 
		{
		setMessage("KeinAdministrator",strClass+".KeinAdministrator",req);
		}

	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Diese Methode wird aufgerufen wenn ein Thema in der Datenbank gelöscht werden soll.
	 * 
	*/
	private void delete(HttpServletRequest req) 
	{				
		if (req.isUserInRole("Administrator")) 
		{
		strSQL = "DELETE FROM [Formulare_Thema] WHERE id="+iID;
		Java2SQLMapper.deleteDatensatz(strSQL);
		setMessage("LoeschenErfolgreich",strClass+".DELETE.Erfolgreich",req);					
		} 
		else 
		{
		setMessage("KeinAdministrator",strClass+".KeinAdministrator",req);
		}
	}
	
	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Lädt ein Thema aus der Datenbank
	 * 
	*/
	/*private Thema load(Integer id) 
	{
	this.loadThema = Java2SQLMapper.getThema(iID);	
	return loadThema;	
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
