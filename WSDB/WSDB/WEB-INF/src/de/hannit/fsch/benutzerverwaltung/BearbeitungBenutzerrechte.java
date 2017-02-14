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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Nachdem ein Benutzer aus der Ergebnisliste ausgewählt wurde, kann er auf der Detailseite
 * weiter bearbeitet werden. 
 * 
 * Dabei wird entweder:
 * <ul>
 * <li>Eine oder mehrere Rechte gelöscht -> Token sb.aktion = DELETE </li>
 * <li>Eine neues Recht erfasst -> Token sb.aktion = INSERT </li>
 * </ul>
 * 
 */
public class BearbeitungBenutzerrechte extends Action 
{
private Sachbearbeiter sb = null;
private final int INSERT = 1;
private final int DELETE = 0;
private String strSQL;			
private ActionErrors errors = new ActionErrors();
 
// private SQLManager sqlManager = new SQLManager();

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	RechteManager rm = new RechteManager();
	this.sb = (Sachbearbeiter) form;
	int iAktion = Integer.parseInt(sb.getAktion());
	String strUsername = sb.getUsername(); 
	int userID = sb.getId();
		
	if (rm.pruefeLoeschBerechtigung(req)) // Nur Administratoren dürfen löschen
	{
		switch (iAktion) 
		{
		case DELETE : // Die zu löschende Rollen enthält das StringArray 'zuloeschen':
			String[] zuLoeschen = sb.getSachgebiet().getZuloeschen();
			int laenge = zuLoeschen.length;
				for (int i = 0; i < laenge; i++)
				{
				strSQL = 	"DELETE from user_roles "+
							"WHERE user_name = '"+strUsername+"' "+
							"AND role_name = '"+zuLoeschen[i]+"' ";	
			
				Java2SQLMapper.insertDatensatz(strSQL);	
				}
				switch (laenge) 
				{
				case 0 :
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("BearbeitungRechte.LoeschenFehler"));				
				break;
				case 1 :
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("BearbeitungRechte.LoeschenErfolgreich"));				
				break;
	
				default :
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("BearbeitungRechte.LoeschenMehrereErfolgreich"));
				break;
				}
		this.saveErrors(req,errors); 			
		break;

		case INSERT : // Neue Rolle einfügen, prüfen, ob Rolle bereits vorhanden ist:
			String strRolle = sb.getSachgebiet().getInsertRolle();
			strSQL = 	"SELECT count (*) from user_roles "+
						"WHERE user_name = '"+strUsername+"' "+
						"AND role_name = '"+strRolle+"' ";
				switch (Java2SQLMapper.getAnzahlDatensätze(strSQL)) 
				{
				case 0 : // Kein Datensatz vorhanden, so soll´s sein.
				strSQL = 	"INSERT INTO user_roles (user_name,role_name) "+
							"VALUES ('"+strUsername+"','"+strRolle+"')";	
				Java2SQLMapper.insertDatensatz(strSQL);	
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("BearbeitungRechte.SpeichernErfolgreich"));				
				break;
		
				default :
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("BearbeitungRechte.DatensatzVorhanden"));
				break;
				}
		this.saveErrors(req,errors); 					
		break;	

		default : 
		//TODO Was passiert, wenn der Token illegal ist ?
		break;
		}		
	} 
	else // Benutzer ist nicht in der Rolle Administrator
	{
	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("BearbeitungRechte.KeinAdministrator"));
	this.saveErrors(req,errors); 
	}

	req.setAttribute("sb",Java2SQLMapper.getSachbearbeiter(userID));
	
	return mapping.getInputForward();
	}
}
