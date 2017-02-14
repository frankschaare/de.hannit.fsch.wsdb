/*
 * Created on 23.02.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.datenbank;

import javax.servlet.http.HttpServletRequest;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author fsch
 *
 * Template für eine Struts CRUD Klasse
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
 * Zusätzlich kann es den Parameter id geben:
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
 * 
*/
public class CRUDAction extends Action {

public final static int SELECT = 0;
public final static int CREATE = 1;
public final static int UPDATE = 2;
public final static int DELETE = 3;
protected final String DATENSATZVORHANDEN = "DatensatzVorhanden";
protected final String INPUT = "input";

//private ActionErrors errors = new ActionErrors();

protected final String strAktion = "crud";
//private final String strID = "id";
protected int iCRUD;
//private int iID;
protected String strForward = null;

	/*
	 * Jede Anfrage an eine CRUD Action MUSS die Parameter: 
	 * CRUD = der Integer für die auszuführende Aktion 
	 * ID = die ID des Objektes, wenn Aktion = SELECT,UPDATE oder DELETE ist.
	 * liefern.
	 */
	public void delegateAction(HttpServletRequest req, ActionForm form)	
	{
		try 
		{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		String strClass = form.getClass().getName();
				
		iCRUD = Integer.parseInt(req.getParameter(strAktion));
			switch (iCRUD) 
			{
			case CREATE :
			session.save(form);	
			this.actionSucceeded("SpeichernErfolgreich",strClass+".CREATE.Erfolgreich",req);
			break;
	
			case UPDATE :
			session.saveOrUpdate(form);	
			this.actionSucceeded("SpeichernErfolgreich",strClass+".UPDATE.Erfolgreich",req);					
			break;
			case DELETE :
					
			break;
			
	
			default :
			break;
			}
		tx.commit();
		HibernateUtil.closeSession();			
		
		} 
		catch (HibernateException e) 
		{
		strForward = "HibernateFehler";
		}		
		catch (NullPointerException e) 
		{
			// TODO: handle exception
		}
		finally
		{
		}
	
	}	

	/**
	 * Der String strForward enthält die Weiterleitung zur nächsten Seite.
	 * 
	 * Entweder geht´s zurück zum Input, oder zu einem anderen Forward.
	 *   
	 * @param String strForward: Die Bezeichnung des Forwards zur nächsten action
	 * @param mapping: Das Struts Action Mapping
	 */	
	protected ActionForward getForward(String strForward,ActionMapping mapping)
	{
		if (strForward.equalsIgnoreCase(INPUT)) 
		{
		return mapping.getInputForward();
		} 
		else 
		{
		return mapping.findForward(strForward);
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
	protected void actionSucceeded (String strProperty,String strKey,HttpServletRequest req)
	{
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError(strKey));
	this.saveErrors(req,errors);
	}	

	/**
	 * Wenn ein Datensatz vorhanden ist, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt ;-)</ul>
	 * <ul>eine Übersicht aller vorhanden Datensätze, geordnet nach id erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * </li
	 * @param String strKey: Der Schlüssel aus der Resource.properties Datei.
	 *
	 * Im Gegensatz zu actionSucceeded wird kein Bezeichner (Property) übergeben, sondern 
	 * immer die Konstante DATENSATZVORHANDEN im Fehlerobjekt gespeichert. 
	 */		
	/*private void fehlerDatensatzVorhanden(String strKey,HttpServletRequest req)
	{
	errors.add(DATENSATZVORHANDEN,new ActionError(strKey));
	this.saveErrors(req,errors); 
	}*/	

}
