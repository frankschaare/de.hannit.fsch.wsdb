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

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.benutzerverwaltung.Vertreter;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Nachdem der 'weiter' Button auf der JSP 'WiderspruchWizard4.jsp' geklickt wurde, wird der Vorgang
 * in der Datenbank gespeichert. 
 * 
 * Diese Action Klasse wird sowohl für das erstmalige anlegen eines Falles, als auch zur Änderung eines 
 * bereits bestehenden Falles benutzt. Da die Anforderungen jeweils etwas anders sind, muss unterschieden werden, 
 * ob gespeichert oder geändert wird.
 * Hierzu wird in der vorangegangenen JSP der Parameter <strong>'aktion'</strong> übergeben, der folgende Werte annimmt:
 * <ul>
 * <li>1 = Es wird ein neuer Vorgang gespeichert</li>
 * <li>2 = Es wird ein bestehender Vorgang geändert</li>
 * </ul>
 * 
 * Da die aufrufende JSP die Feldwerte aus der bean 'vorgang' bezieht, wird die bean vStep4 (die ja auch ein
 * Vorgang Objekt ist) validiert und dann komplett nach 'vorgang' kopiert.
 * 
 */
public class VorgangWizard4 extends Action 
{
static Logger logger = Logger.getLogger("VorgangWizard4");

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	int anzahlVorgänge = 0;	
	//Vector oes = null;
	
		logger.debug("Entnehme Vorgang aus der Anfrage");
	Vorgang requestVorgang = (Vorgang) form;
	
		logger.debug("Hole Widerspruch aus dem Vorgang");
	//Widerspruch requestWS = requestVorgang.getWiderspruch();

		
	HttpSession session = req.getSession();
	// Die Bean 'vorgang' wird durch die Bean requestVorgang ersetzt:
		logger.debug("Ersetze Session Attribut Vorgang durch Request Attribut Vorgang");
	session.setAttribute("vorgang",requestVorgang);
		logger.debug("Lösche FormularBean");
	//FormularBean fb = null;

	Widerspruch sessionWS = requestVorgang.getWiderspruch();
	
	String strForward = null;
	boolean datensatzVorhanden = false;
	String strUserID = req.getRemoteUser();
		logger.debug("Aktion wird durchgeführt für den Benutzer: " + strUserID);

	// Abhängig vom Token aktion wird geprüft, ob bereits ein Widerspruch mit gleichen Daten gespeichert ist:
		logger.debug("Prüfe anhand des Tokens 'Aktion' wie weiter verfahren werden soll");
		logger.debug("Token Aktion hat den Wert: " + requestVorgang.getAktion());
		
	switch (requestVorgang.getAktion()) 
		{
		case 0 : // Ganz schlecht; Token wurde nicht gesetzt, User hat die action ggf. per Bookmark oder URL aufgerufen ;-(
				logger.error("Token Aktion hat den Wert 0 ! - Leite weiter zum Forward 'IllegalerAufruf'");
			strForward = "IllegalerAufruf";
		break;

		case 1 : // Ein neuer Vorgang wird gespeichert. Es wird geprüft, ob schon ein Vorgang existiert	
			String strAZ = sessionWS.getAktenzeichen();
				logger.debug("Token Aktion hat den erwarteten Wert 1. Prüfe nun, ob bereits ein Vorgang zum Aktenzeichen " + strAZ + " existiert");
			String strSQL = "SELECT COUNT(*) "+
							"FROM Widerspruch "+
							"WHERE (aktenzeichen = '"+strAZ+"')";
				logger.debug("SQL: [" + strSQL + "]");
			
			anzahlVorgänge = Java2SQLMapper.getAnzahlDatensätze(strSQL);
				logger.debug("Anzahl gefundener Vorgänge zum Aktenzeichen: " + anzahlVorgänge );

			// Allerdings nur, wenn das Team eindeutige Aktenzeichen benutzt. Wie ich schmerzlich lernen musste,
			// erfassen einige Teams HÄUFIG ! Vorgänge mit gleichen Aktenzeichen. Wenn die das so wollen, muss
			// in der Tabelle 'Organisationseinheit.AZEindeutig' das Bit auf 0 gesetzt werden, dann können für das Team
			// so viele Fälle mit gleichem Aktenzeichen erfasst werden wie geht.
			// ******************** Start Aktenzeichen eindeutig Hack ********************
				logger.debug("Prüfe, ob OE eindeutige Aktenzeichen verwendet, oder nicht.");
			String strOE = strAZ.substring(0,5);
				logger.debug("Extrahiere OE: " + strOE );
			try 
			{
				logger.debug("Hole FormularBean aus der Session");
			//fb = (FormularBean) session.getAttribute("fb");
			//oes = fb.getOrganisationseinheiten();
			} 
			catch (NullPointerException e) 
			{
				logger.warn("Keine FormularBean in der Session ! Fordere neue FormularBean an");
			/*fb = */Java2SQLMapper.getFormularbeanForTeams(req);	
			//oes = fb.getOrganisationseinheiten();
			}
		
			/*for (int i = 0; i < oes.size(); i++) 
			{
			//Organisationseinheit oe = (Organisationseinheit) oes.get(i);	
				//if (strOE.equalsIgnoreCase(oe.getId())) 
				//{
					//if (!oe.isAzEindeutig()) 
					//{
					//	logger.debug("OE verwendet keine eindeutigen Aktenzeichen. Der Vorgang wird gespeichert.");
					//anzahlVorgänge = 0;	
					//}
				//}
			}*/
			// ******************** Ende Aktenzeichen eindeutig Hack ********************
			
			switch (anzahlVorgänge) 
			{
			// ========== Kein gleicher Datensatz vorhanden ==========
			case 0 :
					logger.debug("Der Vorgang wird nun gespeichert. Begonnen wird mit dem Hilfeempfänger");
				// Hilfeempfaenger speichern und ID im Widerspruchsobjekt speichern
				int hilfeempfaengerID = Java2SQLMapper.setHilfeempfaenger(requestVorgang.getHilfeempfaenger(),strUserID);
					logger.debug("Übertrage die empfangene HilfeempfängerID " + hilfeempfaengerID);
				requestVorgang.getHilfeempfaenger().setId(hilfeempfaengerID);	
			
				// ggf Vertreter speichern und ID im Widerspruchsobjekt speichern
				Vertreter vertreter = requestVorgang.getVertreter();
					logger.debug("Prüfe, ob ein Vertreter erfasst wurde. Dies wird dann angenommen, wenn das Pflichtfeld Nachname nicht leer ist");
					logger.debug("Länge Feld Vertreter.Nachname ist: [" + vertreter.getNachname().length() + "]");
					
				if (vertreter.getNachname().length() > 0) 
				{
					logger.debug("Vertreter gefunden - Beginne die Speicherung");
					int vertreterID = Java2SQLMapper.setVertreter(vertreter);
						logger.debug("Übertrage die empfangene VertreterID " + vertreterID);
					vertreter.setId(String.valueOf(vertreterID));
				}
		
				// Widerspruch speichern und ID im Widerspruchsobjekt speichern
					logger.debug("Speichere nun den restlichen Teil des Vorganges");
				int widerspruchID = Java2SQLMapper.insertVorgang(requestVorgang,strUserID);
					logger.debug("Der Vorgang wurde erfolgreich unter der ID " + widerspruchID + " gespeichert.");
			
					logger.debug("Übertrage die ID " + widerspruchID + " in die Session.");
				sessionWS.setId(widerspruchID);
	
					logger.debug("Schreibe nun den Verfahrensverlauf:");
				Java2SQLMapper.insertVerfahrensverlauf(sessionWS,strUserID);
				
				// Mit der WiderspruchsID wird nun noch die Tabelle Verfahrensart bedient:
					logger.debug("Schreibe in die Tabelle Verfahrensart");
				Java2SQLMapper.insertVerfahrensart(sessionWS,strUserID);
	
					logger.debug("Der Vorgang wurde unfallfrei in die Datenbank geschrieben. Lade den Vorgang nun neu und speichere ihn in der Session");
				requestVorgang.setWiderspruch(sessionWS);
				session.setAttribute("vorgang",Java2SQLMapper.getVorgang(sessionWS.getId()));
					logger.debug("Vorgang mit der ID: " + sessionWS.getId() + " wurde erfolgreich in die Session gespeichert. Übergebe nun an den Forward 'success'");
			
				strForward = "success";	
			break;
		
			//	========== Genau ein gleicher Datensatz vorhanden ==========
			case 1 :
				this.fehlerDatensatzVorhanden(sessionWS,req);
				datensatzVorhanden = true;
			break;
	
			//	========== Mehrere gleiche Datensätze vorhanden -> Chaos ! ==========
			default :
				strForward = "mehrereGleicheDatenGefunden";
			break;
			}
		break;

		case 2 : // Ein vorhandener Vorgang wird aktualisiert:
			int vID = requestVorgang.getWiderspruch().getId();
			Java2SQLMapper.updateVorgang(requestVorgang,strUserID);
			session.setAttribute("vorgang",Java2SQLMapper.getVorgang(vID));			
			strForward = "success";
		break;		
		
		default : // die Welt ist schlecht:
		strForward = "IllegalerAufruf";
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
		errors.add("DatensatzVorhanden",new ActionError("ErfassungWiderspruch.DatensatzVorhanden"));
		this.saveErrors(req,errors); 
		
		String strSQL =	"SELECT id "+
						"FROM Widerspruch "+
						"WHERE (aktenzeichen = '"+w.getAktenzeichen()+"')";	
		
		/*int id = */Java2SQLMapper.getDatensatzID(strSQL);
		
		//req.setAttribute("ereignis",Java2SQLMapper.getEreignis(id));

	}

}
