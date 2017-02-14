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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Nachdem der 'weiter' Button auf der JSP 'WiderspruchWizard4.jsp' geklickt wurde, wird der Widerspruch
 * in der Datenbank gespeichert. 
 * 
 */
public class WiderspruchWizard4 extends Action 
{

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	HttpSession session = req.getSession();
	Widerspruch sessionWS = (Widerspruch) session.getAttribute("sessionWS");
	String strForward = null;
	boolean datensatzVorhanden = false;

	// Pr�fung, ob bereits ein Widerspruch mit gleichen Daten gespeichert ist: 	
	String strSQL = "SELECT COUNT(*) "+
					"FROM Widerspruch "+
					"WHERE (aktenzeichen = '"+sessionWS.getAktenzeichen()+"')";
	int anzahlVorg�nge = Java2SQLMapper.getAnzahlDatens�tze(strSQL);
	
	switch (anzahlVorg�nge) 
	{
	// ========== Kein gleicher Datensatz vorhanden ==========
	case 0 :
		// Hilfeempfaenger speichern und ID im Widerspruchsobjekt speichern
		int hilfeempfaengerID = Java2SQLMapper.setHilfeempfaenger(sessionWS.getHilfeempfaenger(),req.getRemoteUser());
		sessionWS.getHilfeempfaenger().setId(hilfeempfaengerID);	
		
		// ggf Vertreter speichern und ID im Widerspruchsobjekt speichern
		if (sessionWS.isVertreterVorhanden()) 
		{
			int vertreterID = Java2SQLMapper.setVertreter(sessionWS.getVertreter());
			sessionWS.getVertreter().setId(String.valueOf(vertreterID));
		}
	
		// Widerspruch speichern und ID im Widerspruchsobjekt speichern
		int widerspruchID = Java2SQLMapper.insertWiderspruch(sessionWS,req.getRemoteUser());
		sessionWS.setId(widerspruchID);
		session.setAttribute("sessionWS",sessionWS);
		
		strForward = "success";	
	break;
	
	//	========== Genau ein gleicher Datensatz vorhanden ==========
	case 1 :
		this.fehlerDatensatzVorhanden(sessionWS,req);
		datensatzVorhanden = true;
	break;

	//	========== Mehrere gleiche Datens�tze vorhanden -> Chaos ! ==========
	default :
		strForward = "mehrereGleicheDatenGefunden";
	break;
	}
	
	if (datensatzVorhanden) 
	{
	return mapping.getInputForward();	
	} 
	else 
	{
	return mapping.findForward(strForward);	
	}

	}

	/**
	 * Wenn ein Datensatz vorhanden ist, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt</ul>
	 * <ul>das gefundene Ereignis im Request abgespeichert</ul>
	 * </li
	 */	
	private void fehlerDatensatzVorhanden(Widerspruch w,HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("ErfassungWiderspruch.DatensatzVorhanden"));
		this.saveErrors(req,errors); 
		
		String strSQL =	"SELECT id "+
						"FROM Widerspruch "+
						"WHERE (aktenzeichen = '"+w.getAktenzeichen()+"')";	
		
		/*int id = */Java2SQLMapper.getDatensatzID(strSQL);
		
		//req.setAttribute("ereignis",Java2SQLMapper.getEreignis(id));

	}

}