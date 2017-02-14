/*
 * Created on 02.04.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.datenbank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.wsdb.Hilfeempfaenger;

/**
 * @author fsch
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImportModule extends Action {

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	this.reorganizeHilfeempfaenger();	
	return mapping.findForward("success");
	}
	
	/**
	* 
	* Methode zur Reorganisation der Hilfeempfänger in der Datenbank:
	* Liest die Hilfeempfängerdaten aus der alten Tabelle und schreibt
	* die Werte in die Tabelle Hilfeempfaenger. 
	* 
	* Dann wird der generierte Autowert ermittelt und in die Tabelle Widerspruch geschrieben.
	*/
	public void reorganizeHilfeempfaenger() 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	Vector hilfeempfänger = new Vector();
	String strSQL = "SELECT id, nachname, Vorname, geb FROM Widerspruch";
	//String strDatum = null;
	
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  
		while (rs.next()) 
		{
		Hilfeempfaenger he = new Hilfeempfaenger();
		he.setWiderspruchID(rs.getInt("id"));
		he.setNachname(rs.getString("nachname"));
		he.setVorname(rs.getString("Vorname"));
		
		// Datum transformieren:
		//DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		//Date datum = rs.getDate("geb");
			/*if (datum != null)
			{
			//strDatum = df.format(datum);
			//he.setGeburtsdatum(datum);			
			}*/

		hilfeempfänger.add(he);		
		}
	int anzahlDatensätze = hilfeempfänger.size();

	rs.close();
	
		// Jetzt wird über den Vector literiert und die Datensätze in die Tabelle Hilfeempfänger geschrieben:
		for (int i = 0; i < anzahlDatensätze; i++) 
		{
		Hilfeempfaenger he = (Hilfeempfaenger) hilfeempfänger.get(i);
		// Datum transformieren:
		int id = Java2SQLMapper.setHilfeempfaenger(he, "ImportModule");
		
		// Zum Schluss wird die hilfeempfaengerID in die Tabelle Widerspruch geschrieben:
		strSQL =	"UPDATE Widerspruch" + 
					" SET Widerspruch.hilfeempfaengerID = "+id+					" WHERE Widerspruch.id = "+he.getWiderspruchID();
		stmt.execute(strSQL);			
			
		}

	  stmt.close();
	  stmt = null;
	  con.close(); // Return to connection pool
	  con = null;  // Make sure we don't close it twice
	  } 
	  catch (SQLException e) 
	  {
	  e.printStackTrace();
	  //Die UPDATE-Anweisung verstieß gegen die COLUMN FOREIGN KEY-Einschränkung 'FK_Person_User'. Der Konflikt trat in der NSH-Datenbank, Tabelle 'User', column 'UserID' auf
	  } 
	  finally 
	  {
	  // Always make sure result sets and statements are closed,
	  // and the connection is returned to the pool
		  if (rs != null) 
		  {
			try 
			{ 
			rs.close(); 
			} 
			catch (SQLException e) 
			{ 
			e.printStackTrace(); 
			}
			rs = null;
		  }
    
		  if (stmt != null) 
		  {
			  try 
			  { 
			  stmt.close(); 
			  } catch (SQLException e) 
			  {
			  e.printStackTrace();
			  }
		  stmt = null;
		  }
    	
		  if (con != null) 
		  {
			  try 
			  { 
			  con.close(); 
			  } 
			  catch (SQLException e) 
			  { 
			  e.printStackTrace(); 
			  }
		  con = null;
		  }
	}

	} 


}
