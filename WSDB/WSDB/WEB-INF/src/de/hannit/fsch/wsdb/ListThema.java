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

import de.hannit.fsch.datenbank.SQLManager;

/**
 * @author fsch
 *
 * Klasse zur Verwaltung der Hilfstabellen in der Datenbank
 * @param vwForm.hilfstabelle - Name der Tabelle, die editiert werden soll
 */
public class ListThema extends Action 
{
private RowSetDynaClass rsdc = null;

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	SQLManager sm = new SQLManager();
	HttpSession session = req.getSession();
	
	// Der Java2SQLMapper liefert die benötigte Collection.
	// dazu ruft er die passende View in der Datenbank auf:
	rsdc = sm.getThemenForTeam("vwThema",req);
	
	session.setAttribute("Ergebnis",rsdc);
	
	// Abhängig von der gewählten Hilfstabelle wird zur passenden View weitergeleitet:
	return mapping.findForward("ListeThema");
	}
}
