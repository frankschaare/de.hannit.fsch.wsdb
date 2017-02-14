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
 * Diese Klasse wird aufgerufen, wenn ein neuer Widerspruch erfasst werden soll
 * Wichtig dabei ist, dass die dynamisch generierten Controls in den JSP´s immer
 * aktuell sind, auch wenn ein Administrator zwischenzeitlich Werte geändert hat.
 * Daher wird vor dem Aufruf der ersten Wizard Seite eine neue FormularBean in die Session gelegt.
 * 
 */
public class WiderspruchWizard0 extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	HttpSession session = req.getSession();
	session.setAttribute("sessionWS", new Widerspruch());
	session.setAttribute("fb",Java2SQLMapper.getFormularbeanForTeams(req));
	
	return mapping.findForward("NextStep");
	}
}
