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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Nachdem ein Benutzer aus der Ergebnisliste ausgewählt wurde, kann er auf der Detailseite
 * weiter bearbeitet werden. Diese Klasse trifft alle notwendigen Vorbereitungen. Es wird noch einmal 
 * eine Datenbankabfrage gemacht, die alle Eigenschaften eines Benutzers abfragt und diese im Objekt 
 * Sachbearbeiter einfügt und im Request ablegt.
 * 
 */
public class SelectSachbearbeiter extends Action 
{
private Sachbearbeiter sb = null;
//private SQLManager sqlManager = new SQLManager();

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	String strForward = "BenutzerRechte";
	
	// Der Java2SQLMapper liefert ein fertig konfiguriertes Sachbearbeiter Objekt:	
	this.sb = Java2SQLMapper.getSachbearbeiter(Integer.parseInt(req.getParameter("id")));

	// Diese Action kann auch aus der ListeBenutzer.jsp aufgerufen werden, dann ist aktion=UPDATE:
	if (req.getParameter("aktion") != null) 
	{
	sb.setAktion(req.getParameter("aktion"));
	strForward = "BenutzerBearbeitung";	
	}

	req.setAttribute("sb",sb);
	
	return mapping.findForward(strForward);
	}
}
