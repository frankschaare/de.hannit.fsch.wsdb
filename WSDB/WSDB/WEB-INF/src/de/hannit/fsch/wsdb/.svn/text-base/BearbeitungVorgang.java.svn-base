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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Diese Klasse wird aufgerufen, wenn ein Vorgang über den Menüpunkt
 * Vorgang | Bearbeiten | bearbeiten angeklickt wird.
 * 
 * Wichtig ist, dass der Token 'aktion' auf UPDATE gesetzt wird.
 * Das signaliert den Folgeklassen, dass 
 * <ul>
 * <li>NICHT geprüft wird, ob ein gleiches Aktenzeichen vorhanden ist</li>
 * <li>der Vorgang über eine update Anweisung gespeichert wird.</li>
 * </ul>
 * 
 */
public class BearbeitungVorgang extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	int id = Integer.parseInt(req.getParameter("id"));
	HttpSession session = req.getSession();
	Vorgang v = Java2SQLMapper.getVorgang(id);
	// Token wird auf UPDATE gesetzt:
	v.setAktion(v.UPDATE);
	
	session.setAttribute("vorgang", v);
	session.setAttribute("fb",Java2SQLMapper.getFormularbeanForTeams(req));
	
	return mapping.findForward("NextStep");
	}
}
