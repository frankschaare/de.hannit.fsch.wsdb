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
 * Nachdem der Submit Button auf der JSP 'ErfassungWiderspruch' geklickt wurde und die Eingaben
 * validiert wurden, kann der Widerspruch in der Datenbank gespeichert werden. 
 * 
 */
public class WiderspruchErfassenSubmit extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	Widerspruch ws = (Widerspruch)form;
	
	// Die Daten des Hilfeempfängers sollten immer vorhanden sein, also werden diese zuerst gespeichert:
	int hilfeempfaengerID = Java2SQLMapper.setHilfeempfaenger(ws.getHilfeempfaenger(), req.getRemoteUser());
	// Rückgabewert ist die ID des Hilfeempfängers, die wird nun gesetzt:
	ws.getHilfeempfaenger().setId(hilfeempfaengerID);

	// Falls ein Vertreter angegeben wurde, wird dieser nun gespeichert:
	if (ws.isVertreterVorhanden()) 
	{
	int vertreterID = Java2SQLMapper.setVertreter(ws.getVertreter());
	ws.getVertreter().setId(String.valueOf(vertreterID));	
	}
	
	return mapping.findForward("welcome");
	}
}
