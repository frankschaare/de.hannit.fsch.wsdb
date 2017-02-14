/*
 * Created on 05.01.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.benutzerverwaltung;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.LabelValueBean;

import de.hannit.fsch.wsdb.FormularBean;

/**
 * @author fsch
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RechteManager extends Action
{
private final String KEINEBERECHTIGUNG = "KeineBerechtigung";
	/**
	* 
	* Prüfung, ob der in der Session gespeicherte Benutzer das Recht hat,
	* eine Aktion auszuführen.
	* 
	* Dies ist dann der Fall, wenn das Sachbebiet des Benutzers (gespeichert
	* in der Session) Grösser oder gleich dem Sachbebiet des Vorganges ist.
	* 
	* Dabei wird einfach geprüft, ob der String 'Sachgebiet' des Benutzers
	* innerhalb des Strings 'Sachgebiet' des zu speichernden Vorganges enthalten ist.
	* 
	* @param Benutzer: der aus der Session geholte Benutzer
	* @param String: das Sachgebiet des zu speichernden Vorganges
	* @return boolean
	*/
	public boolean pruefeBerechtigung(HttpServletRequest req, String sachgebiet) 
	{
	int anzTreffer = 0;
	HttpSession session = req.getSession();
	
	// Die Formularbean enthält einen Vector mit allen gespeicherten Teams:
	FormularBean fb = (FormularBean) session.getAttribute("fb");
	Vector teams = fb.getTeams();

		for (int i = 0; i < teams.size(); i++) 
		{
		LabelValueBean lvb = (LabelValueBean) teams.elementAt(i);	
		
			String value = lvb.getValue();
			// Ist der Benutzer der Rolle zugeordnet ...
			if (req.isUserInRole(value)) 
			{
				// ...wird geprüft, ob die Rolle im Sachgebiet vorkommt: 
				if (sachgebiet.indexOf(value) == -1) 
				{
				// nicht passiert
				} 
				else 
				{
				anzTreffer += 1;
				}
			}

		}

	if (anzTreffer > 0)	{return true;} 
	else {return false;}	

	}
	
	/**
	* 
	* Wenn die Prüfung, ob der in der Session gespeicherte Benutzer das Recht hat,
	* eine Aktion auszuführen fehlschlägt, wird ein Fehlerobjekt erzeugt und im
	* Request gespeichert.
	* 
	*/
	public String setBerechtigungsFehler(HttpServletRequest req) 
	{
		ActionErrors errors = new ActionErrors();
		errors.add("KeineBerechtigung",new ActionError("ErfassungWiderspruch.KeineBerechtigung"));
		this.saveErrors(req,errors); 
		return KEINEBERECHTIGUNG;
	}

	/**
	 * @param req
	 * @return
	 */
	public boolean pruefeLoeschBerechtigung(HttpServletRequest req) {
		if (req.isUserInRole("Administrator")) {return true;} 
		else {return false;}

	}

	/**
	 * @param req
	 * @return
	 */
	public boolean isAdministrator(HttpServletRequest req) {
		if (req.isUserInRole("Administrator")) 
		{return true;} 
		else 
		{
		
		return false;
		}
	
	}


}
