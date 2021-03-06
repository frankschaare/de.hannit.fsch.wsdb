/*
 * Created on 02.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.wsdb;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.datenbank.CRUDAction;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author Frank Schaare am 25.02.2005 
 *
 * CRUD Klasse f�r das Objekt Thema
 * 
 * C(reate)U(pdate)D(elete) Actions sind von zentraler Bedeutung f�r die Anwendung.
 * Grunds�tzlich gibt es zwei Parameter, die die Gesamtfunktionalit�t steuern:
 * @param crud: der Schl�ssel f�r die gew�nschte Aktion kann die folgenden Werte annehmen:
 * <ul>
 * <li>0 = SELECT</li>
 * <li>1 = CREATE</li>
 * <li>2 = UPDATE</li>
 * <li>3 = DELETE</li>
 * </ul>
 * 
 * Zus�tzlich wird der Parameter 'loaded' erwartet:
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
 * crud = 3, id nicht vorhanden -> Objekt l�schen (id gibt es nochmals im Objekt selber)
 * 
 * WICHTIG ! Wenn die Action keinen Requestparameter id erh�lt, wie kann sie dann wissen,
 * welches Objekt in der Datenbank bearbeitet werden muss ?
 * Antwort: Das Objekt enth�lt selbst immer auch ein Feld 'id', welches mit der ActionForm
 * �bergeben wird.
 * 
 * 
*/
public class CRUDEreignis extends Action
{
private String strAktion = null;
private String strID = null;
private int iCRUD;
private int iID;
private boolean loaded;
//private String strClass;
private String strForward;
private Ereignis ereignis;
private Ereignis loadEreignis;
private Vorgang vorgang = null;
private HttpSession session = null;

	public synchronized ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	this.strAktion = req.getParameter("crud");
	this.strID = req.getParameter("id");
	if (strID != null) {this.iID = Integer.parseInt(strID);}
	this.iCRUD = Integer.parseInt(strAktion);
	//this.strClass = form.getClass().getName();
	
	checkLoaded(req); //Pr�fe, ob das Objekt erst noch geladen werden muss
	if (!loaded) // Objekt wird f�r die folgende Action geladen: 
	{
	loadObject(req);	
	} 
	else // ID wird nicht mitgeliefert, Action wird direkt mit der Form ausgef�hrt:
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
			break;
			}		
		
	}
		if (strForward.equalsIgnoreCase("input")) 
		{
		return mapping.getInputForward();
		} 
		else 
		{
		return mapping.findForward(strForward);
		}
	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Wenn der Anfrageparameter 'loaded' = false ist, wird das Objekt zun�chst geladen.
	 * Dazu wird diese Methode aufgerufen, die:
	 * <ul>
	 * <li>Das Objekt aus der Datenbank l�dt</li>
	 * <li>als Attribut im Request ablegt</li>	 
	 * <li>Dann wird abh�ngig von der Variable 'iCRUD' an die Folgeseite weitergeleitet.</li>
	 * </ul>
	 * 
	*/
	private void loadObject(HttpServletRequest req) 
	{
	this.ereignis = load(new Integer(iID),req);
		if (this.ereignis != null) 
		{
			req.setAttribute("ereignis",ereignis);
			
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
		else 
		{
			this.strForward = "SessionTimeout";
		}

	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Diese Methode wird aufgerufen wenn ein neues Objekt in der Datenbank erstellt werden soll.
	 * Vorab m�ssen leider noch Plausibilit�ten gekl�rt werden, z.b.
	 * <ul>
	 * <li>Existiert bereits ein gleiches Objekt in der Datenbank ?</li>
	 * <li>Darf der Benutzer das Objekt �berhaupt speichern ?</li>	 
	 * </ul>
	 * 
	*/
	private void create(HttpServletRequest req,ActionForm form) 
	{
		
	strForward = "?";	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Diese Methode wird aufgerufen wenn ein neues Objekt in der Datenbank erstellt werden soll.
	 * Vorab m�ssen leider noch Plausibilit�ten gekl�rt werden, z.b.
	 * <ul>
	 * <li>Existiert bereits ein gleiches Objekt in der Datenbank ?</li>
	 * <li>Darf der Benutzer das Objekt �berhaupt speichern ?</li>	 
	 * </ul>
	 * 
	*/
	private void update(HttpServletRequest req,ActionForm form) 
	{
	Ereignis reqEreignis = (Ereignis) form;
	Java2SQLMapper.updateVerfahrensverlauf(reqEreignis);
	Widerspruch w = vorgang.getWiderspruch();
	// Der Widerspruch erh�lt einen aktualisierten Verfahrensverlauf:
	w.setVerfahrensverlauf(Java2SQLMapper.getVerfahrensverlauf(w.getId()));
	vorgang.setWiderspruch(w);
	// und landet wieder in der session:
	session.setAttribute("vorgang",vorgang);
	
	strForward = "input";
	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Diese Methode wird aufgerufen wenn ein neues Objekt in der Datenbank erstellt werden soll.
	 * Vorab m�ssen leider noch Plausibilit�ten gekl�rt werden, z.b.
	 * <ul>
	 * <li>Existiert bereits ein gleiches Objekt in der Datenbank ?</li>
	 * <li>Darf der Benutzer das Objekt �berhaupt speichern ?</li>	 
	 * </ul>
	 * 
	*/
	private void delete(HttpServletRequest req,ActionForm form) 
	{				
		strForward = "?";
	
	}
	/*
	 * Funktioniert leider noch nicht 
	 */
	private Ereignis load(Integer id,HttpServletRequest req) 
	{
	    try 
		{
		session = req.getSession();
		vorgang = (Vorgang) session.getAttribute("vorgang");
		Vector v = vorgang.getWiderspruch().getVerfahrensverlauf();
			for (int i = 0; i < v.size(); i++) 
			{
			Ereignis tmp = (Ereignis) v.get(i);
				if (tmp.getId() == this.iID) 
				{
				this.loadEreignis = tmp;	
				}
			}
		} 
	    catch (NullPointerException e) 
		{
		return null;
		}
    
		
	return loadEreignis;	
	}

	/**
	 * @author Frank Schaare am 28.02.2005 
	 *
	 * Pr�ft, ob das Objekt erst noch aus der Datenbank geladen werden muss
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
	 * <ul>eine �bersicht aller vorhanden Datens�tze, geordnet nach id erstellt</ul>
	 * <ul>die �bersicht im Request gespeichert</ul>
	 * </li
	 * @param String strKey: Der Schl�ssel aus der Resource.properties Datei.
	 * @param String strProperty: Der Bezeichner, unter dem der Error gespeichert wird.
	 * kann ein beliebiger String sein, oder die Konstante 'ActionErrors.GLOBAL_ERROR'
	 */	
	/*private void setMessage (String strProperty,String strKey,HttpServletRequest req)
	{
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(strKey));
	this.saveErrors(req,errors);
	}*/

}
