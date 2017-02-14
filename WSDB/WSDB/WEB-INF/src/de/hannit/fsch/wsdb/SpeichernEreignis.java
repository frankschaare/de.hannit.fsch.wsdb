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
 * Speichert ein Ereignis im VERFAHRENSVERLAUF und erledigt einige Vorarbeiten.
 * Für sie Speicherung eines Ereignisses in der Datenbanktabelle Ereignis ist die 
 * Action 'ErfassungEreignis' zuständig.
 * 
 * Es gibt grundsätzlich zwei Arten von Ereignissen, einmalige und wiederkehrende.
 * Einmalige können nur einmal in den Verfahrensverlauf geschrieben werden,
 * wiederkehrende dagegen mehrmals.
 * 
 * Da neben der Tabelle Verfahrensverlauf auch noch Felder aus der Tabelle Ereignis 
 * benötigt werden, wird die View 'vwVerfahrensverlauf' benutzt.
 * 
 * Im ersten Schritt wird die abgefragt, ob das Ereignis wiederkehrend ist.
 * Dazu wird der Vector 
 * Wenn ja, wird das Ereignis ohne weitere Prüfung in den Verfahrensverlauf geschrieben.
 * Wenn nein, muss geprüft werden, ob bereits ein gleiches Ereignis vorhanden ist. Dazu 
 * wird der View vwVerfahrensverlauf abgefragt und abhängig von den gefundenen Datensätzen
 * folgendes veranlasst:
 * <ul>
 * <li>Treffer = 0: Ereignis kann ohne weiteres gespeichert werden.</li>
 * <li>Treffer > 0: Ereignis kann nicht gespeichert werden.</li>
 * <ul>
 *  
 */
public class SpeichernEreignis extends Action 
{
public final int KLAGE = 2;
private String benutzerID = null;

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	String strSQL = null;
	String strForward = "success";
	Ereignis ereignis = (Ereignis)form;
	int ereignisID = Integer.parseInt(ereignis.getName());
	int anzahlTreffer = 0;
		
	HttpSession session = req.getSession();
	Vorgang v = (Vorgang) session.getAttribute("vorgang");
	Widerspruch sessionWS = v.getWiderspruch();
	benutzerID = req.getRemoteUser();

	
	//	Wenn kein Widerspruch in der Session liegt, geht´s leider nicht ;-(
	if (sessionWS != null) 
	{
		// Prüfung, ob bereits das Ereignis wiederkehrend ist:
		Ereignis testEreignis = Java2SQLMapper.getEreignis(ereignisID); 
		
		if (testEreignis.isWiederkehrend()) 
		{
			this.speichernEreignis(ereignis,sessionWS.getId(),benutzerID,req,session);	
		} 
		else 
		{
			// Prüfung, ob bereits ein Ereignis mit gleichen Daten gespeichert ist: 	
		
			// SQL CHECKEN: ereignisID
			strSQL =	"SELECT count(*) "+
						"FROM Verfahrensverlauf "+
						"WHERE (ereignisID = '"+ereignis.getName()+"') AND "+
						"(widerspruchID = "+sessionWS.getId()+")";	
			anzahlTreffer = Java2SQLMapper.getAnzahlDatensätze(strSQL);
		
						
			switch 	(anzahlTreffer) 
					{
					case 0 : // Kein Datensatz gefunden
					this.speichernEreignis(ereignis,sessionWS.getId(),req.getRemoteUser(),req,session);
					strForward = "success";
					break;

					default :
					this.fehlerDatensatzVorhanden(ereignis,req);
					strForward = "success"; // Doof, aber Benutzer erhält Info über JavaScript alert()
					break;
					}
			
		}

		
	} 
	else 
	{
		strForward = "FehlerKeinSessionWS";
	}
	return mapping.findForward(strForward); 
	}


	/**
	 * Wenn das Ereignis wiederkehrend ist, oder bei einem einmaligem Ereignis kein
	 * gleicher Datensatz vorhanden ist, wird das Ereignis in den Verfahrensvelauf
	 * geschrieben 
	 */	
	private void speichernEreignis(Ereignis ereignis, int widerspruchID,String benutzerID,HttpServletRequest req,HttpSession session)
	{
		Java2SQLMapper.insertEreignis(ereignis,widerspruchID,benutzerID);
		
		// Bestimmte Ereignisse haben Folgen für den Vorgang. Die folgende Switch 
		// Anweisung bietet Raum für Sonderbehandlungen
		switch (Integer.parseInt(ereignis.getName())) 
		{
			case 28 :
			Java2SQLMapper.updateVerfahrensartID(KLAGE,widerspruchID,benutzerID);
			this.speichernErfolgreichKlage(ereignis,req,session);						
				break;

			default :
			this.speichernErfolgreich(ereignis,req,session);
				break;
		}
		
		Vorgang vorgang = Java2SQLMapper.getVorgang(widerspruchID);
		session.setAttribute("vorgang",vorgang);
		req.setAttribute("ereignis",new Ereignis());
	}
		
	/**
	 * Wenn die Speicherung erfolgreich war, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt ;-)</ul>
	 * <ul>eine Übersicht aller vorhanden Datensätze, geordnet nach id erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * </li
	 */	
	private void speichernErfolgreichKlage(Ereignis e,HttpServletRequest req,HttpSession session)
	{
		ActionErrors errors = new ActionErrors();
		errors.add("ErfassungErfolgreich",new ActionError("ErfassungEreignisKlage.Erfolgreich"));
		this.saveErrors(req,errors); 

	}	
		
	/**
	 * Wenn die Speicherung erfolgreich war, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt ;-)</ul>
	 * <ul>eine Übersicht aller vorhanden Datensätze, geordnet nach id erstellt</ul>
	 * <ul>die Übersicht im Request gespeichert</ul>
	 * </li
	 */	
	private void speichernErfolgreich(Ereignis e,HttpServletRequest req,HttpSession session)
	{
		ActionErrors errors = new ActionErrors();
		errors.add("ErfassungErfolgreich",new ActionError("ErfassungEreignis.Erfolgreich"));
		this.saveErrors(req,errors); 
		//String strSQL = 	"Select * from Ereignis order by organisationseinheitID";
		//session.setAttribute("ergebnis",Java2SQLMapper.getRowSetDynaClass(strSQL));

	}	

	/**
	 * Wenn ein Datensatz vorhanden ist, 
	 * <li>
	 * <ul>wird ein Fehlerobjekt erstellt</ul>
	 * <ul>das gefundene Ereignis im Request abgespeichert</ul>
	 * </li
	 */	
	private void fehlerDatensatzVorhanden(Ereignis e,HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();
		errors.add("DatensatzVorhanden",new ActionError("ErfassungEreignis.DatensatzVorhanden"));
		this.saveErrors(req,errors); 

	}
	
}
