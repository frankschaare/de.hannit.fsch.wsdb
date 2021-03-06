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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import de.hannit.fsch.datenbank.Java2SQLMapper;
import de.hannit.fsch.datenbank.SQLManager;

/**
 * @author fsch
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListBearbeitungWiderspruch extends Action 
{
private RowSetDynaClass rsdc = null;
private SQLManager sqlManager = new SQLManager();

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	HttpSession session = req.getSession();
	session.setAttribute("fb",Java2SQLMapper.getFormularbeanForTeams(req));
	
	rsdc = sqlManager.getListeForTeam("vwVorgang",req);
	
	session.setAttribute("Ergebnis",rsdc);
	
	return mapping.findForward("ListeBearbeitungWiderspruch");
	}
}
