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

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Lädt ein Ereignis aus der Datenbank und speichert es im Request.
 * Danach wird die BearbeitenEreignis.jsp aufgerufen, in der das Ereignis
 * bearbeitet werden kann.  
 *  
 */
public class BearbeitenEreignis extends Action 
{
//private static final Log log = LogFactory.getLog(BearbeitenEreignis.class);	

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	//String strSQL = null;
	String ereignisdID = req.getParameter("id"); 
	Ereignis ereignis = Java2SQLMapper.getEreignis(Integer.parseInt(ereignisdID));

	req.setAttribute("ereignis",ereignis);

	return mapping.findForward("success");
	}
		
}
