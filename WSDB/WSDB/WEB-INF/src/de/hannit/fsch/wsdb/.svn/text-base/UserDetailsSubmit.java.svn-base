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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author fsch
 *
 * Nachdem ein Benutzer aus der Ergebnisliste ausgewählt wurde, kann er auf der Detailseite
 * weiter bearbeitet werden. Diese Klasse trifft alle notwendigen Vorbereitungen. Es wird noch einmal 
 * eine Datenbankabfrage gemacht, die alle Eigenschaften eines Benutzers abfragt und diese im Objekt 
 * Sachbearbeiter einfügt und im Requests ablegt.
 * 
 */
public class UserDetailsSubmit extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	//Object o = req.getAttribute("sbForm");
	
	return mapping.findForward("welcome");
	}
}
