package de.hannit.fsch.datenbank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import de.hannit.fsch.benutzerverwaltung.Adresse;
import de.hannit.fsch.benutzerverwaltung.Einrichtung;
import de.hannit.fsch.benutzerverwaltung.Sachbearbeiter;
import de.hannit.fsch.benutzerverwaltung.Sachgebiet;
import de.hannit.fsch.benutzerverwaltung.Vertreter;
import de.hannit.fsch.wsdb.Ereignis;
import de.hannit.fsch.wsdb.FormularBean;
import de.hannit.fsch.wsdb.Hilfeempfaenger;
import de.hannit.fsch.wsdb.Thema;
import de.hannit.fsch.wsdb.Vorgang;
import de.hannit.fsch.wsdb.Widerspruch;

/**
 * @author Frank.Schaare@HannIT.de
 * @version 1.0 vom 27.11.2002 11:47:15
 * 
 * <p>Die Klasse SQLManager behandelt zentral alle Datenbankanfragen:</p>
 * 
 */
public class Java2SQLMapper
{
static Calendar cal = new GregorianCalendar( TimeZone.getTimeZone("ECT") );
static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM, Locale.GERMAN);	
static Logger logger = Logger.getLogger("Java2SQLMapper");

	/**
	* 
	* Methode zur Rückgabe einer RowSetDynaClass.
	* 
	* @param strSQL: der vorgefertigte SQL String für das Select
	* @return RowSetDynaClass
	*/
	public static int getMaximum(String strSQL) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;	   
	int i = 0;
 
	  try 
	  {
	  con = VerbindungsManager.getJNDIConnection();
	  stmt = con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  rs.next();
	  i = rs.getInt(1);
	  rs.close();
	  rs = null;
	  stmt.close();
	  stmt = null;
	  con.close(); // Return to connection pool
	  con = null;  // Make sure we don't close it twice
	  } 
	  catch (SQLException e) 
	  {
	  e.printStackTrace();
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
	  return i;
	}
	
	
	/**
	* 
	* Methode zur Speicherung eines Hilfeempfängers in der Datenbank
	* 
	* @param Hilfeempfänger: Der Hilfeempfänger
	* @return id: die ID des eingefügten Hilfeempfängers
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static int setHilfeempfaenger(Hilfeempfaenger h, String strUserID) 
	{
	int id,adresseID = 0;
	//String anrede,vorname,nachname,geburtsdatum,sterbedatum,ga,erstelltAm;
	
		logger.debug("Versuche nun, den Hilfeempfänger in der Datenbank zu speichern:");
	
	// Prüfung, ob bereits ein Hilfeempfänger mit gleichen Daten gespeichert ist: 	
	String strSQL = "SELECT COUNT(*) "+
					"FROM Hilfeempfaenger "+
					"WHERE (vorname"+h.getVorname(SQL_SELECT)+") AND "+ 
					"(nachname"+h.getNachname(SQL_SELECT)+") AND "+ 
					"(geburtsdatum"+h.getGeburtsdatum(SQL_SELECT)+")";
		logger.debug("SQL: [" + strSQL + "]");
	
	int anzahlHilfeempfaenger = getAnzahlDatensätze(strSQL);
		logger.debug("Anzahl gefundener Hilfeempfänger mit gleichen Daten: " + anzahlHilfeempfaenger );
		
		switch (anzahlHilfeempfaenger) 
		{
		case 0 : //Kein gleicher Datensatz vorhanden
			logger.debug("Kein Hilfeempfänger mit gleichen Daten gefunden - Speichere nun die Adresse");
		// ==================== Speichern der Daten: ====================		
		//	Adresse eingefügen und ID zurückgeben	
		Adresse adresse = h.getAdresse();
			if (adresse.getPostleitzahl() != null && adresse.getOrt() != null) 
			{
			adresseID = setAdresse(adresse);				
			} 
			else 
			{
			adresseID = 0;
			}
			logger.debug("Adresse erhält die ID " + adresseID + " - speichere nun den Hilfeempfänger.");
											
		strSQL =	"INSERT INTO Hilfeempfaenger " + 
					"(anrede,vorname,nachname,geburtsdatum,sterbedatum,adresseID,ga,erstelltAm,erstelltVon) "+ 
					"VALUES ("+
					h.getAnrede(SQL_INSERT)+","+
					h.getVorname(SQL_INSERT)+","+
					h.getNachname(SQL_INSERT)+","+
					h.getGeburtsdatum(SQL_INSERT)+","+
					h.getSterbedatum(SQL_INSERT)+","+								
					adresseID+"," +
					h.getGa(SQL_INSERT)+",'" +					
					getZeitstempel()+"','" +
					strUserID+"')";
			logger.debug("SQL: [" + strSQL + "]");
		insertDatensatz(strSQL);
		// ==================== Speichern der Daten: ====================	
		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
			logger.debug("Der Hilfeempfänger wurde gespeichert - Ermittele nun die gesetzte ID");
		strSQL = 	"SELECT id "+
					"FROM Hilfeempfaenger "+
					"WHERE (vorname"+h.getVorname(SQL_SELECT)+") AND "+
					"(nachname"+h.getNachname(SQL_SELECT)+") AND "+					
					"(geburtsdatum"+h.getGeburtsdatum(SQL_SELECT)+")";		
			logger.debug("SQL: [" + strSQL + "]");
		id = getDatensatzID(strSQL);
			logger.debug("Hilfeempfänger wurde unter der ID " + id + " gespeichert.");
		// ==================== Ermitteln der id: ====================					
				break;
		case 1 : //genau ein gleicher Datensatz vorhanden, es wird nicht gespeichert, nur die ID zurückgegeben
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
			logger.debug("Genau ein Hilfeempfänger mit gleichen Daten gefunden - Liefere die ID des Hilfeempfängers");
			
		strSQL = 	"SELECT id "+
					"FROM Hilfeempfaenger "+
					"WHERE (vorname"+h.getVorname(SQL_SELECT)+") AND "+
					"(nachname"+h.getNachname(SQL_SELECT)+") AND "+					
					"(geburtsdatum"+h.getGeburtsdatum(SQL_SELECT)+")";
			logger.debug("SQL: [" + strSQL + "]");
		id = getDatensatzID(strSQL);
			logger.debug("Hilfeempfänger wurde unter der ID " + id + " gefunden.");
				break;	
		default : // Es sind mehr als ein gleicher Datensatz vorhanden, PANIK !
		
		logger.error("DATENFEHLER: ES WURDEN " + anzahlHilfeempfaenger + " GLEICHE DATENSÄTZE GEFUNDEN ! ES WIRD VERSUCHT, DEN DATENSATZ ZURÜCKZULIEFERN, DER AUCH VOM ANGEMELDETEN BENUTZER ERSTELLT WURDE !");		
		
		strSQL =	"SELECT MAX(id) ";
		strSQL +=	"FROM Hilfeempfaenger WHERE ";
		strSQL +=	"(vorname"+h.getVorname(SQL_SELECT)+") AND ";
		strSQL +=	"(nachname"+h.getNachname(SQL_SELECT)+") AND ";		
		strSQL +=	"(geburtsdatum"+h.getGeburtsdatum(SQL_SELECT)+") AND ";		
		strSQL +=	"(erstelltVon = '"+strUserID+"') OR ";	
		strSQL +=	"(vorname"+h.getVorname(SQL_SELECT)+") AND ";
		strSQL +=	"(nachname"+h.getNachname(SQL_SELECT)+") AND ";		
		strSQL +=	"(geburtsdatum"+h.getGeburtsdatum(SQL_SELECT)+") AND ";		
		strSQL +=	"(aktualisiertVon = '"+strUserID+"')";		

		
		
		logger.debug("SQL: [" + strSQL + "]");
		id = getDatensatzID(strSQL);
		// CS-Hack: Carsten Schmidt August 2011
		// TODO test, deploy
		/* CS-HACK Start */
		/*if (id<=0)
		{
			strSQL =	"SELECT MAX(id) ";
			strSQL +=	"FROM Hilfeempfaenger WHERE ";
			strSQL +=	"(vorname"+h.getVorname(SQL_SELECT)+") AND ";
			strSQL +=	"(nachname"+h.getNachname(SQL_SELECT)+") AND ";		
			strSQL +=	"(geburtsdatum"+h.getGeburtsdatum(SQL_SELECT)+") AND ";		
			strSQL +=	"(erstelltVon = '%') OR ";	
			strSQL +=	"(vorname"+h.getVorname(SQL_SELECT)+") AND ";
			strSQL +=	"(nachname"+h.getNachname(SQL_SELECT)+") AND ";		
			strSQL +=	"(geburtsdatum"+h.getGeburtsdatum(SQL_SELECT)+") AND ";		
			strSQL +=	"(aktualisiertVon = '%')";		
			logger.debug("SQL: [" + strSQL + "]");
			id = getDatensatzID(strSQL);
		}*/
		/* CS-HACK End */

			break;
		}
	return id;
	}

	/**
	* 
	* Methode zur aktualisierung eines Hilfeempfängers in der Datenbank
	* 
	* @param Hilfeempfänger: Der Hilfeempfänger
	* @return true, wenn erfolgreich upgedatet wurde 
	* @return false, NICHT erfolgreich upgedatet wurde
	*  
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static boolean updateHilfeempfaenger(Hilfeempfaenger h, String strUserID) 
	{
	boolean erfolg = false;
	int id = h.getId();
	String strGAID = null;
	
	// Prüfung, ob der Hilfeempfänger überhaupt gespeichert ist: 	
	String strSQL = "SELECT COUNT(*) "+
					"FROM Hilfeempfaenger "+
					"WHERE id = "+id;
	
	int anzahlHilfeempfaenger = getAnzahlDatensätze(strSQL);
		switch (anzahlHilfeempfaenger) 
		{
		case 0 : // die id wurde nicht gefunden, das ist schlecht
			erfolg = false;
		break;			
		case 1 : //Genau ein gleicher Datensatz vorhanden, so soll´s sein
		// ==================== Update der Daten: ====================		
		//	Adresse updaten:
			erfolg = updateAdresse(h.getAdresse(),strUserID);	
			
			// Kleiner, unfeiner Hack, um den GA korrekt einzufügen:
			try 
			{
			strGAID = h.getGa();
				if (strGAID.equalsIgnoreCase("bitte auswählen:")) 
				{
				strGAID = "NULL";	
				} 
				else 
				{
				strGAID = getValueForSQL(strGAID,SQL_UPDATE);	
				}
			} 
			catch (NullPointerException e) 
			{
			strGAID = "NULL";	
			}
			strSQL = 	"UPDATE Hilfeempfaenger SET "+ 
						"anrede = "+getValueForSQL(h.getAnrede(),SQL_UPDATE)+", "+
						"vorname = "+getValueForSQL(h.getVorname(),SQL_UPDATE)+", "+
						"nachname = "+getValueForSQL(h.getNachname(),SQL_UPDATE)+", "+
						"geburtsdatum = "+getValueForSQL(h.getGeburtsdatum(),SQL_UPDATE)+", "+
						"ga = "+strGAID+", "+
						"sterbedatum = "+getValueForSQL(h.getSterbedatum(),SQL_UPDATE)+", "+
						"einrichtungID = "+h.getEinrichtung().getId()+", "+						
						"aktualisiertAm = '"+getZeitstempel()+"', "+
						"aktualisiertVon = '"+strUserID+"' "+														
						"WHERE (id = "+id+")";
		insertDatensatz(strSQL);
		// ==================== Update der Daten: ====================	
		erfolg = true;
		break;
		default : // Es sind mehr als ein gleicher Datensatz vorhanden, da muss ein Fehler ausgegeben werden
		erfolg = false;
		break;
		}
	return erfolg;
	}

	/**
	* 
	* Methode zur Aktualisierung der Stammdaten eines Widerspruches oder Klage in der Datenbank
	* 
	* @param Widerspruch: Der zu aktualisierende Widerspruch
	* @return true, wenn erfolgreich upgedatet wurde 
	* @return false, NICHT erfolgreich upgedatet wurde
	*  
	*/
	public static boolean updateWiderspruch(Vorgang v, String strUserID) 
	{
	boolean erfolg = false;
	Widerspruch w = v.getWiderspruch();
	int id = w.getId();
	
	// Prüfung, ob der Widerspruch überhaupt gespeichert ist: 	
	String strSQL = "SELECT COUNT(*) "+
					"FROM Widerspruch "+
					"WHERE id = "+id;
	
	int anzahlFaelle = getAnzahlDatensätze(strSQL);
		switch (anzahlFaelle) 
		{
		case 0 : // die id wurde nicht gefunden, das ist schlecht
			erfolg = false;
		break;			
		case 1 : //Genau ein gleicher Datensatz vorhanden, so soll´s sein
		// ==================== Update der Daten: ====================		
			strSQL = 	"UPDATE Widerspruch SET "+ 
						"aktenzeichen = "+w.getAktenzeichen(SQL_UPDATE)+", "+
						"eingangsdatum = "+w.getEingangsdatum(SQL_UPDATE)+", "+
						"vertreterID = "+w.getVertreter().getId()+", "+
						"angaben = "+w.getAngaben(SQL_UPDATE)+", "+
						"gegenstandDesVerfahrensID = "+Integer.parseInt(w.getGegenstandDesVerfahrensID())+", "+
						"rechtsgebietID = "+Integer.parseInt(w.getRechtsgebietID())+", "+
						"hilfeempfaengerID = "+v.getHilfeempfaenger().getId()+", "+
						"dae = '"+w.getDae()+"', "+
						"datumrbh = '"+w.getDatumrbh()+"', "+		
						"aktualisiertAm = '"+getZeitstempel()+"', "+
						"aktualisiertVon = '"+strUserID+"' "+														
						"WHERE (id = "+id+")";
		insertDatensatz(strSQL);
		// ==================== Update der Daten: ====================	
		erfolg = true;
		break;
		default : // Es sind mehr als ein gleicher Datensatz vorhanden, da muss ein Fehler ausgegeben werden
		erfolg = false;
		break;
		}
	return erfolg;
	}

	public static final int SQL_SELECT = 0;
public static final int SQL_INSERT = 1;
public static final int SQL_UPDATE = 2;
public static final int SQL_DELETE = 3;

//private static final Log log = LogFactory.getLog(Java2SQLMapper.class);	

/**
* 
* Methode zur Rückgabe einer Hashmap aus einer Wertetabelle
* 
* @param strTabelle: die Tabelle, welche die Wertepaare enthält. 
* Es <strong>MUSS</strong> eine zweispaltige Tabelle sein !
* @return Sachbearbeiter
*/
public static Vector getValueLabelBeans(String strTabelle) 
{
String strSQL = 	"SELECT * FROM " + strTabelle; 
Vector v = new Vector();
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
  try 
  {
  con = VerbindungsManager.getJNDIConnection();
  stmt = con.createStatement();
  // Datenbankabfrage nach allen benötigten Benutzerdaten:
  rs = stmt.executeQuery(strSQL);
  while (rs.next()) 
  {
  String value = rs.getString(1).trim();
  String label = rs.getString(2).trim();
  v.add(new LabelValueBean(label,value));		
  }
	
  rs.close();
  rs = null;
  stmt.close();
  stmt = null;
  con.close(); // Return to connection pool
  con = null;  // Make sure we don't close it twice
  } 
  catch (SQLException e) 
  {
  e.printStackTrace();
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
  return v;
}


	public static Hilfeempfaenger getHilfeempfaenger (int hilfeempfaengerID)
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Hilfeempfaenger he = new Hilfeempfaenger();
 	
 	// Tabelle Hilfeempfaenger auslesen:
	String strSQL = "Select dbo.Hilfeempfaenger.*, dbo.Kommune.KomName1, dbo.Kommune.KomName2 "+
					"FROM  dbo.Hilfeempfaenger "+
					"LEFT OUTER JOIN dbo.Kommune ON dbo.Hilfeempfaenger.ga = dbo.Kommune.KommuneID "+
					"WHERE id = "+hilfeempfaengerID;	

	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();

	// Hilfeempfaenger mit Werten füllen:  
  	he.setId(rs.getInt("id"));
  	if (rs.getString("anrede") != null) 
  	{
	he.setAnrede(rs.getString("anrede"));	
	}
	if (rs.getString("vorname") != null) 
	{
	he.setVorname(rs.getString("vorname").trim());	
	}
  	he.setNachname(rs.getString("Nachname").trim());
	if (rs.getString("geburtsdatum") != null) 
	{
	String gebDat = df.format(rs.getDate("geburtsdatum"));
	he.setGeburtsdatum(gebDat);	
	}
	if (rs.getDate("sterbedatum") != null) 
	{
	String gebDat = df.format(rs.getDate("sterbedatum"));
	he.setSterbedatum(gebDat);		
	}
	int einrichtungID = rs.getInt("einrichtungID");
	if (einrichtungID != 0) 
	{
	he.setEinrichtung(Java2SQLMapper.getEinrichtung(einrichtungID));		
	}
	
	// der GA wird mit dem sprechenden Wert gefüllt, z.b. 'Hannover'
	if (rs.getString ("ga") != null) 
	{
	he.setGa(rs.getString("ga").trim());
	he.setGa(rs.getString("KomName2").trim());		
	}
	
	he.setAdresse(Java2SQLMapper.getAdresse(rs.getInt("adresseID")));


	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException e) 
	{
	e.printStackTrace();
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
	
	return he;
	}

	public static Sachbearbeiter getSachbearbeiter (int sachbearbeiterID)
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Sachbearbeiter sb = new Sachbearbeiter();
	
	// Tabelle Hilfeempfaenger auslesen:
	String strSQL = "Select * from users where userid = "+sachbearbeiterID;	
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	
	// Hilfeempfaenger mit Werten füllen:  
	sb.setUserID(String.valueOf(rs.getInt("userID")));
	sb.setId(rs.getInt("userID"));
	
	if (rs.getString("anrede") != null) 
	{
	sb.setAnrede(rs.getString("anrede").trim());	
	}
	if (rs.getString("vorname") != null) 
	{
	sb.setVorname(rs.getString("vorname").trim());	
	}
	sb.setNachname(rs.getString("Nachname").trim());
	if (rs.getString("organisationseinheitID") != null) 
	{
	sb.setOrganisationseinheitID(rs.getString("organisationseinheitID"));	
	}
	if (rs.getString("Raum") != null) 
	{
	sb.setRaum(rs.getString("Raum"));	
	}
	if (rs.getString("Durchwahl") != null) 
	{
	sb.setDurchwahl(rs.getString("Durchwahl"));	
	}
	if (rs.getString("Fax") != null) 
	{
	sb.setFax(rs.getString("Fax"));	
	}	
	
	String strUserName = rs.getString("user_name");
	sb.setUsername(strUserName);
	if (rs.getString("user_pass") != null) 
	{
	sb.setPasswort(rs.getString("user_pass").trim());
	sb.setPasswort2(rs.getString("user_pass").trim());
	}
	
	sb.setUserID(rs.getString("userid"));
	
	// Jetzt werden noch die Rollen ausgelesen:
	Sachgebiet sg = new Sachgebiet();	
	ArrayList roles = new ArrayList(); 
	strSQL = "Select * from user_roles where user_name = '"+strUserName+"' ORDER BY role_name";
	rs = stmt.executeQuery(strSQL);
		while (rs.next()) 
		{
		roles.add(rs.getString("role_name"));
		}
	sg.setAufgaben(roles);
	sb.setSachgebiet(sg);

	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException e) 
	{
	e.printStackTrace();
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
	
	return sb;
	}

	public static Vertreter getVertreter (int vertreterID)
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Vertreter ve = new Vertreter();
	
	// Tabelle Hilfeempfaenger auslesen:
	String strSQL = "Select * from vwVertreter where id = "+vertreterID;	
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	
	// Vertreter mit Werten füllen:  
	String strID = String.valueOf(rs.getInt("id"));
	ve.setId(strID);

	if (rs.getString("vertreterArt") != null) 
	{
	ve.setArt(rs.getString("vertreterArt").trim());	
	}
	if (rs.getString("bezeichnung") != null) 
	{
	ve.setBezeichnung(rs.getString("bezeichnung").trim());	
	}	
	if (rs.getString("vorname") != null) 
	{
	ve.setVorname(rs.getString("vorname").trim());	
	}
	ve.setNachname(rs.getString("Nachname").trim());

	ve.setAdresse(Java2SQLMapper.getAdresse(rs.getInt("adresseID")));
	//TODO Was ist mit dem GA ???
	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException e) 
	{
	e.printStackTrace();
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
	
	return ve;
	}

	public static Widerspruch getWiderspruch (int widerspruchID)
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Widerspruch ws = new Widerspruch();
	
	// Tabelle Widerspruch auslesen, dazu wird die View vwWiderspruch benutzt:
	String strSQL = "Select * from vwWiderspruch where id = "+widerspruchID;	
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	
	// Widerspruch mit Werten füllen:  
	ws.setId(rs.getInt("id"));
	if (rs.getString("aktenzeichen") != null) 
	{
	ws.setAktenzeichen(rs.getString("aktenzeichen").trim());		
	}
	if (rs.getString("eingangsdatum") != null) 
	{
	ws.setEingangsdatum(rs.getString("eingangsdatum").trim());		
	}
	if (rs.getString("angaben") != null) 
	{
	ws.setAngaben(rs.getString("angaben").trim());	
	}
	if (rs.getInt("verfahrensartID") != 0) 
	{
	ws.setVerfahrensartID(String.valueOf(rs.getInt("verfahrensartID")));
	}
	if (rs.getString("verfahrensart") != null) 
	{
	ws.setVerfahrensart(rs.getString("verfahrensart").trim());
	}
	if (rs.getInt("gegenstandDesVerfahrensID") != 0) 
	{
	ws.setGegenstandDesVerfahrensID(String.valueOf(rs.getInt("gegenstandDesVerfahrensID")));
	}
	if (rs.getString("gegenstandDesVerfahrens") != null) 
	{
	ws.setGegenstandDesVerfahrens(rs.getString("gegenstandDesVerfahrens").trim());
	}
	if (rs.getInt("rechtsgebietID") != 0) 
	{
	ws.setRechtsgebietID(String.valueOf(rs.getInt("rechtsgebietID")));
	}
	if (rs.getString("rechtsgebiet") != null) 
	{
	ws.setRechtsgebiet(rs.getString("rechtsgebiet").trim());
	}
	if (rs.getInt("hilfeempfaengerID") != 0) 
	{
	ws.setHilfeempfaenger(Java2SQLMapper.getHilfeempfaenger(rs.getInt("hilfeempfaengerID")));
	}

	ws.setVerfahrensverlauf(getVerfahrensverlauf(widerspruchID));
	int einrichtungID = rs.getInt("einrichtungID");
		if (einrichtungID != 0) 
		{
		ws.setEinrichtung(Java2SQLMapper.getEinrichtung(einrichtungID));		
		}
	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException e) 
	{
	e.printStackTrace();
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
	
	return ws;
	}

	public static Vorgang getVorgang (int widerspruchID)
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Vorgang v = new Vorgang();
	Widerspruch ws = v.getWiderspruch();
	
	// Tabelle Widerspruch auslesen, dazu wird die View vwWiderspruch benutzt:
	String strSQL = "Select * from vwVorgang where id = "+widerspruchID;	
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	
	// Widerspruch mit Werten füllen:  
	ws.setId(rs.getInt("id"));
	if (rs.getString("aktenzeichen") != null) 
	{
	ws.setAktenzeichen(rs.getString("aktenzeichen").trim());		
	}
	if (rs.getString("eingangsdatum") != null) 
	{
	ws.setEingangsdatum(rs.getString("eingangsdatum").trim());		
	}
	if (rs.getString("angaben") != null) 
	{
	ws.setAngaben(rs.getString("angaben").trim());	
	}
	if (rs.getInt("verfahrensartID") != 0) 
	{
	ws.setVerfahrensartID(String.valueOf(rs.getInt("verfahrensartID")));
	}
	if (rs.getString("verfahrensart") != null) 
	{
	ws.setVerfahrensart(rs.getString("verfahrensart").trim());
	}
	if (rs.getInt("gegenstandDesVerfahrensID") != 0) 
	{
	ws.setGegenstandDesVerfahrensID(String.valueOf(rs.getInt("gegenstandDesVerfahrensID")));
	}
	if (rs.getString("gegenstandDesVerfahrens") != null) 
	{
	ws.setGegenstandDesVerfahrens(rs.getString("gegenstandDesVerfahrens").trim());
	}
	if (rs.getInt("rechtsgebietID") != 0) 
	{
	ws.setRechtsgebietID(String.valueOf(rs.getInt("rechtsgebietID")));
	}
	if (rs.getString("rechtsgebiet") != null) 
	{
	ws.setRechtsgebiet(rs.getString("rechtsgebiet").trim());
	}
	if (rs.getString("dae") != null) 
	{
	ws.setDae(rs.getString("dae").trim());
	}	
	if (rs.getString("datumrbh") != null) 
	{
	ws.setDatumrbh(rs.getString("datumrbh").trim());
	}		
	if (rs.getInt("hilfeempfaengerID") != 0) 
	{
	v.setHilfeempfaenger(Java2SQLMapper.getHilfeempfaenger(rs.getInt("hilfeempfaengerID")));
	}
	// Vertreter füllen, falls vorhanden:
	if (rs.getInt("vertreterID") != 0) 
	{
	//Vertreter vertreter = new Vertreter();
	v.setVertreter(Java2SQLMapper.getVertreter(rs.getInt("vertreterID")));
	}
		 
	ws.setVerfahrensverlauf(getVerfahrensverlauf(widerspruchID));
	
	// Die Einrichtung war ursprünglich dem Widerspruch zugeordnet, wird jetzt aber dem Hilfeempfänger zugeordnet
	// int einrichtungID = rs.getInt("einrichtungID");
		//if (einrichtungID != 0) 
		//{
		//ws.setEinrichtung(Java2SQLMapper.getEinrichtung(einrichtungID));		
		//}
	v.setWiderspruch(ws);
	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException e) 
	{
	e.printStackTrace();
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
	
	return v;
	}

	public static Vector getVerfahrensverlauf (int widerspruchID)
	{
	Vector v = new Vector();
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	// Alle Ereignisse zum Widerspruch nach reihenfolge sortiert auslesen:
	String strSQL = "Select * from vwEreignisse where widerspruchID = "+widerspruchID+" ORDER BY reihenfolge" ;	
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	
	while (rs.next()) 
	{
	// Ereignisse mit Werten füllen
	Ereignis ereignis = new Ereignis();
	
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMAN);
	String test = df.format(rs.getDate("datum"));

	ereignis.setId(rs.getInt("id"));	

	if (test != null) 
	{
	ereignis.setDatum(test);		
	}
	if (rs.getString("kommentar") != null) 
	{
	ereignis.setKommentar(rs.getString("kommentar").trim());	
	}
	if (rs.getString("name") != null) 
	{
	ereignis.setName(rs.getString("name").trim());	
	}
	if (rs.getInt("ereignisID") != 0) 
	{
	ereignis.setFkEreignisID(rs.getInt("ereignisID"));	
	}	
	//TODO Alle Felder der Ereignis Objektes belegen
	ereignis.setProtected(rs.getBoolean("protected"));	

	v.add(ereignis);		
	}
	
	// Ältere (oder kaputte) Fälle haben ggf keinen gespeicherten Verfahrensverlauf. in diesem Fall wird ein 
	// Ereignis gespeichert, das auf diesen Umstand hinweist. 
	if (v.isEmpty()) 
	{
	Ereignis ereignis = new Ereignis();	
	ereignis.setName("Kein Verfahrensverlauf gespeichert.");
	v.add(ereignis);
	}
	
	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException e) 
	{
	e.printStackTrace();
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
	
	return v;
	}
	/**
	 * @author fsch
	 * @param value: der Wert des Bean Feldes
	 * @param StatementType: der Typ des SQL Statements, für das der Wert aufgearbeitet werden muss 
	 *
	 * Bei SQL Statements müssen eine Reihe von Faktoren beachtet werden
	 * Der SQL Server z.b.  möchte Null Werte bei Select Statements gerne als
	 * 'is null' haben, etc. 
	 * 
	 * Diese Methode versucht, den Wert der Eigenschaft SQL gerecht zurückzugeben.
	 */
	public static String getValueForSQL(String value,int StatementType)
	{
	String returnValue = null;
	
		switch (StatementType) 
		{
			case SQL_INSERT :
				try 
				{
				if (value == null | value.length() < 1) {returnValue="NULL";} else {returnValue="'"+value+"'";}	
				} 
				catch (NullPointerException e) 
				{
				returnValue=null;
				} 
			break;
			
			case SQL_UPDATE :
				try 
				{
				if (value == null | value.length() < 1) {returnValue="NULL";} else {returnValue="'"+value+"'";}	
				} 
				catch (NullPointerException e) 
				{
				returnValue=null;
				} 			
			break;	
			
			case SQL_DELETE :
			break;		
			
			default : // ist Select
				try 
				{
				if (value == null | value.length() < 1) {returnValue=" IS NULL";} else {returnValue=" = '"+value+"'";}
				} 
				catch (NullPointerException e) 
				{
				returnValue=" IS NULL";
				} 			
			break;
		}
	return returnValue;
	}
	/**
	* 
	* Methode zur Speicherung eines Widerspruches in der Datenbank
	* 
	* @param Widerspruch: Der Widerspruch
	* @return id: die ID des eingefügten Widerspruches
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static int insertWiderspruch(Widerspruch w, String userID) 
	{
	int id = 0;
	String strSQL = null;

		// ==================== Speichern der Daten: ====================		
					
		strSQL =	"INSERT INTO Widerspruch " + 
					"(aktenzeichen,eingangsdatum,angaben,verfahrensartID,gegenstandDesVerfahrensID,rechtsgebietID,hilfeempfaengerID,erfasstAm,userID,einrichtungID) "+ 
					"VALUES ("+
					w.getAktenzeichen(SQL_INSERT)+","+
					w.getEingangsdatum(SQL_INSERT)+","+
					w.getAngaben(SQL_INSERT)+","+					
					Integer.parseInt(w.getVerfahrensartID())+","+					
					Integer.parseInt(w.getGegenstandDesVerfahrensID())+","+
					Integer.parseInt(w.getRechtsgebietID())+","+					
					w.getHilfeempfaenger().getId()+",'"+
					getZeitstempel()+"',"+							
					Integer.parseInt(userID)+"," +
					w.getEinrichtung().getId()+")";
		insertDatensatz(strSQL);

		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		
		strSQL = 	"SELECT id "+
					"FROM Widerspruch "+
					"WHERE (aktenzeichen = '"+w.getAktenzeichen()+"')";	
		id = getDatensatzID(strSQL);

	return id;
	}

	/**
	* 
	* Methode zur erstmaligen Speicherung eines Verfahrensverlaufes in der Datenbank.
	* Als erster Satz wird immer das Eingangsdatum (ID = 1)
	* 
	*/
	public static void insertVerfahrensverlauf(Widerspruch w, String userID) 
	{
	int id = w.getId();
		logger.debug("Versuche nun, den Verfahrensverlauf zum Vorgang " + id + " in der Datenbank zu speichern:");
	String strSQL = null;
	
	// === Mit der id kann nun der Verfahrenssverlauf gebildet werden ===
		strSQL =	"INSERT INTO Verfahrensverlauf " + 
					"(widerspruchID,reihenfolge,ereignisID,datum,erfasstAm,erfasstVon) "+ 
					"VALUES ("+
					id+","+
					1+","+
					1+","+					
					w.getEingangsdatum(SQL_INSERT)+",'"+					
					getZeitstempel()+"','"+							
					userID+"')";
		logger.debug("SQL: [" + strSQL + "]");
		
		insertDatensatz(strSQL);					
	}

	/**
	* 
	* Methode zur erstmaligen Speicherung der Verfahrensart in der Datenbank.
	* Für jeden neuen Vorgang wird ein Datensatz in die Tabelle Verfahrensart
	* geschrieben.
	*/
	public static void insertVerfahrensart(Widerspruch w, String userID) 
	{
	int id = w.getId();
	String strSQL = null;
	
	// === Mit der id kann nun der Verfahrensartverlauf gebildet werden ===
		strSQL =	"INSERT INTO Verfahrensart " + 
					"(widerspruchID,verfahrensartID,gueltigVon,erfasstAm,erfasstVon) "+ 
					"VALUES ("+
					id+","+
					Integer.parseInt(w.getVerfahrensartID())+",'"+
					getZeitstempel()+"','"+					
					getZeitstempel()+"','"+							
					userID+"')";
		logger.debug("SQL: [" + strSQL + "]");
		
		insertDatensatz(strSQL);					
	}

	/**
	* 
	* Methode zur Speicherung des Widerspruches eines Vorganges in der Datenbank
	* 
	* @param Vorgang: Der gesamte Vorgang
	* @param Benutzer: Der angemeldete Benutzer
	* @return id: die ID des eingefügten Widerspruches
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static synchronized int insertVorgang(Vorgang v, String user) 
	{
		logger.debug("Versuche nun, den Vorgang in der Datenbank zu speichern:");
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;	
	
	int id = 0;
	String strSQL = null;
	Widerspruch w = v.getWiderspruch();
	
		// ==================== Speichern der Daten: ====================		
		// Timestamp generieren:
		String strStamp = getZeitstempel();
		
		strSQL =	"INSERT INTO Widerspruch " + 
					"(aktenzeichen,eingangsdatum,vertreterID,angaben,gegenstandDesVerfahrensID,rechtsgebietID,hilfeempfaengerID";
					if (w.getDae().length() > 0) 
					{
					strSQL += ",dae";	
					}
					if (w.getDatumrbh().length() > 0) 
					{
					strSQL += ",datumrbh";	
					}
					strSQL += ",erfasstAm,erfasstVon,einrichtungID) VALUES ("+
					w.getAktenzeichen(SQL_INSERT)+","+
					w.getEingangsdatum(SQL_INSERT)+","+
					v.getVertreter().getId(SQL_INSERT)+","+					
					w.getAngaben(SQL_INSERT)+","+					
					Integer.parseInt(w.getGegenstandDesVerfahrensID())+","+
					Integer.parseInt(w.getRechtsgebietID())+","+					
					v.getHilfeempfaenger().getId()+",";
					if (w.getDae().length() > 0) 
					{
					strSQL += "'"+w.getDae()+"',";	
					}					
					if (w.getDatumrbh().length() > 0) 
					{
					strSQL += "'"+w.getDatumrbh()+"',";	
					}					
				
					strSQL += "'" +strStamp+"','"+							
					user+"'," +
					w.getEinrichtung().getId()+")";
		// DAS IST FALSCH !!!	
					logger.debug("SQL: [" + strSQL + "]");
					
					  try 
					  {
					  con = VerbindungsManager.getConnection();
					  stmt = con.createStatement();
					  stmt.execute(strSQL);
					  // ==================== Ermitteln der id: ====================		
					  // Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
					
					  strSQL = 	"SELECT id "+
								"FROM Widerspruch "+
								"WHERE (aktenzeichen = '"+w.getAktenzeichen()+"') AND (erfasstAm = '"+strStamp+"')";	
					  id = getDatensatzID(strSQL);
					  	logger.debug("Der Vorgang wurde erfolgreich unter der ID " + id + " gespeichert.");
					
					  stmt.close();
					  stmt = null;
					  con.close(); // Return to connection pool
					  con = null;  // Make sure we don't close it twice
					  } 
					  catch (SQLException e) 
					  {
					  e.printStackTrace();
					  logger.error("Beim Speichern des Vorganges ist es zu einer SQLException gekommen: "+e.getMessage());
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
			
	
	return id;
	}

	/**
	* 
	* Methode zur Speicherung des Widerspruches eines Vorganges in der Datenbank
	* 
	* @param Vorgang: Der gesamte Vorgang
	* @param Benutzer: Der angemeldete Benutzer
	* @return id: die ID des eingefügten Widerspruches
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static int insertSachbearbeiter(Sachbearbeiter sb, String user) 
	{
	int id = 0;
	String strSQL = null;
	String strUsername = getValueForSQL(sb.getUsername(),SQL_INSERT);
	
		// ==================== Speichern der Daten: ====================		
					
		strSQL =	"INSERT INTO users " + 
					"(Nachname,Vorname,organisationseinheitID,Raum,Durchwahl,Fax,Anrede,user_name,user_pass,erfasstAm,erfasstVon) "+ 
					"VALUES ("+
					getValueForSQL(sb.getNachname(),SQL_INSERT)+","+
					getValueForSQL(sb.getVorname(),SQL_INSERT)+","+
					getValueForSQL(sb.getOrganisationseinheitID(),SQL_INSERT)+","+					
					getValueForSQL(sb.getRaum(),SQL_INSERT)+","+					
					getValueForSQL(sb.getDurchwahl(),SQL_INSERT)+","+					
					getValueForSQL(sb.getFax(),SQL_INSERT)+","+
					getValueForSQL(sb.getAnrede(),SQL_INSERT)+","+					
					strUsername+","+
					getValueForSQL(sb.getPasswort(),SQL_INSERT)+",'"+
					getZeitstempel()+"','"+
					user+"')";
		insertDatensatz(strSQL);
		strSQL = 	"INSERT INTO user_roles (user_name,role_name) "+
					"VALUES ("+strUsername+",'"+sb.getSachgebiet().getInsertRolle()+"')";	
		insertDatensatz(strSQL);			
	
		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		
		strSQL = 	"SELECT userid "+
					"FROM users "+
					"WHERE (user_name = "+strUsername+")";	
		id = getDatensatzID(strSQL);
	
	return id;
	}

	/**
	* 
	* Methode zum Update eines Vorganges in der Datenbank
	* 
	* @param Vorgang: Der gesamte Vorgang
	* @param Benutzer: Der angemeldete Benutzer
	* @return true, wenn erfolgreich upgedatet wurde 
	* @return false, NICHT erfolgreich upgedatet wurde
	*/
	public static boolean updateVorgang(Vorgang v, String strUserID) 
	{
		// Hilfeempfaenger aktualisieren:
		boolean erfolg = updateHilfeempfaenger(v.getHilfeempfaenger(),strUserID);
				
		// ggf Vertreter updaten
		if (v.getVertreterVorhanden()) 
		{
		int vertreterID = insertORupdateVertreter(v.getVertreter(),v.getWiderspruch().getId());
		v.getWiderspruch().getVertreter().setId(String.valueOf(vertreterID));
		}
		
		// Widerspruch updaten
		erfolg = updateWiderspruch(v,strUserID);

		return erfolg;
	}

	 /**
	 * 
	 * Methode zur Rückgabe einer RowSetDynaClass.
	 * 
	 * @param strSQL: der vorgefertigte SQL String für das Select
	 * @return RowSetDynaClass
	 */
	 public static RowSetDynaClass getRowSetDynaClass(String strSQL) 
	 {
	 Connection con = null;
	 Statement stmt = null;
	 ResultSet rs = null;
	 RowSetDynaClass rsdc = null;
	 
	   try 
	   {
	   con = VerbindungsManager.getJNDIConnection();
	   stmt = con.createStatement();
	   rs = stmt.executeQuery(strSQL);
	   rsdc = new RowSetDynaClass(rs);
	   rs.close();
	   rs = null;
	   stmt.close();
	   stmt = null;
	   con.close(); // Return to connection pool
	   con = null;  // Make sure we don't close it twice
	   } 
	   catch (SQLException e) 
	   {
	   e.printStackTrace();
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
	   return rsdc;
	 }

	/**
	* 
	* Liefert das aktuelle Datum und Uhrzeit für Timestamp Operationen
	* 
	* @return String: repräsentiert das aktuelle Datum und Uhrzeit
	*/
	public static String getZeitstempel() 
	{

	//DateFormat df = DateFormat.getDateTimeInstance();
		logger.debug("Erzeuge Zeitstempel für Datenbank [" + df.format(new Date()) + "]");
	return df.format(new Date());
	}


	/**
	* 
	* Viele SQL Statements liefern Ergebnisse, die der Benutzer aufgrund seiner Rolle eigentlich nicht sehen darf.
	* Deswegen werden einige Statements mit einer Filterklausel versehen, d.h. einem AND Statement, dass die 
	* Rückgabe einschränkt
	*  
	* @param Vector teams: Enthält alle definierten Teams
	* @param HttpServletRequest req: Das Request Objekt
	* 
	* @return String: die fertig formulierte AND Klausel
	*/
	public static String getFilteredTeams(HttpServletRequest req, Vector teams) 
	{
	logger.debug("Erzeuge AND-Statement für eingeschränkte SQL-Abfrage");
	String strStatement = "AND left(aktenzeichen,5) in (";
	
		for (int i = 0; i < teams.size(); i++) 
		{
		LabelValueBean lvb = (LabelValueBean) teams.elementAt(i);	
		
			String value = lvb.getValue();
			if (req.isUserInRole(value)) 
			{
			strStatement += "'"+value+"',";	
			}
			// Ooops, ein Komma zu viel:
	
		}
	int letzter = strStatement.lastIndexOf(",");
	strStatement = strStatement.substring(0,letzter);
	strStatement += ")";
	
	logger.debug("AND-Statement wurde wie folgt erzeugt:");
	logger.debug(strStatement);
	
	return strStatement;
	}

	/**
	* 
	* Methode zur Prüfung, wie viele Zahlungsdatensätze vorhanden sind.
	* 
	* @param strSQL: der vorgefertigte SQL String für das Update
	* @return true, wenn der Datensatz besteht, false wenn der Datensatz nicht vorhanden ist.
	*/
	public static int getAnzahlDatensätze(String strSQL) 
	{
	int anzahlDatensätze = 0;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;	
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  rs.next();
	  anzahlDatensätze = rs.getInt(1);
	  rs.close();
	  rs = null;
	  stmt.close();
	  stmt = null;
	  con.close(); // Return to connection pool
	  con = null;  // Make sure we don't close it twice
	  } 
	  catch (SQLException e) 
	  {
	  e.printStackTrace();
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
	  return anzahlDatensätze;
	}
	
	/**
	* 
	* Methode zur Rückgabe der ID eines Datensatzes.
	* 
	* @param strSQL: der vorgefertigte SQL String
	* @return int ID
	*/
	public static int getDatensatzID(String strSQL) 
	{
	int wert = 0;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;		
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  rs.next();
	  wert = rs.getInt(1);
	  rs.close();
	  rs = null;
	  stmt.close();
	  stmt = null;
	  con.close(); // Return to connection pool
	  con = null;  // Make sure we don't close it twice
	  } 
	  catch (SQLException e) 
	  {
	  e.printStackTrace();
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
	  return wert;
	}
	
	/**
	* 
	* Methode zum Einfügen der Zahlungsdatensätze in die Datenbank:
	* @param strSQL: der vorgefertigte SQL String für das Update
	*/
	public static void insertThema(String thema, String team) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String strSQL = "INSERT into [Formulare_Thema] (thema,team) VALUES ('"+thema+"', '"+team+"')"; 
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  stmt.execute(strSQL,Statement.RETURN_GENERATED_KEYS);
	  rs = stmt.getResultSet();
  
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

	/**
	* 
	* Methode zum Einfügen eines Ereignisses in die Tabelle Verfahrensverlauf:
	* Besonders geprüft wird das Ereignis 'Klageerhebung am'. Wird dieses Ereignis eingefügt, 
	* ändert sich automatisch auch der Status des Vorganges von Widerspruch auf Klage.
	* 
	*/
	public static void insertEreignis(Ereignis ereignis, int widerspruchID, String userID) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String strSQL = null;
	
	// Zuerst wird der Maximalwert der bisher erfassten Ereignisse für diesen Vorgang ermittelt:
	strSQL = 	"SELECT MAX(reihenfolge) FROM Verfahrensverlauf Where widerspruchID = "+widerspruchID;
	int maxWert = getMaximum(strSQL)+1;
	
	// ereignis.getName() liefert die ereignisID, das ist leider unglücklich gemacht
	String strEreignisID = ereignis.getName();
	strSQL = 	"INSERT INTO Verfahrensverlauf " + 
				"(widerspruchID,reihenfolge,ereignisID,datum,kommentar,erfasstAm,erfasstVon) "+ 
				"VALUES ("+
				widerspruchID+","+
				maxWert+","+				
				strEreignisID+","+
				ereignis.getDatum(SQL_INSERT)+","+
				ereignis.getKommentar(SQL_INSERT)+",'"+
				getZeitstempel()+"','"+															
				userID+"')"; 
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  stmt.execute(strSQL);
	  stmt.close();
	  stmt = null;
	  con.close(); // Return to connection pool
	  con = null;  // Make sure we don't close it twice
	  } 
	  catch (SQLException e) 
	  {
	  e.printStackTrace();
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

	/**
	* 
	* Methode zum löschen eines Widerspruches aus der Datenbank:
	* Der Widerspruch wird dabei nicht wirklich gelöscht, sondern es wird die Zelle gelöscht am gefüllt
	* @param id: die eindeutige id des Widerspruches
	*/
	public static void deleteWiderspruch(int widerspruchsID, String strUserID) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String strSQL = 	"Update Widerspruch "+ 
						"SET geloeschtAm = '"+getZeitstempel()+"', "+
						"geloeschtVon = '"+strUserID+"' "+						
						"WHERE (id = "+widerspruchsID+")";
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  stmt.executeUpdate(strSQL);
	
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

	/**
	* 
	* Methode zum löschen eines Datensatzes in der Datenbank:
	* @param strSQL: der vorgefertigte SQL String
	*/
	public static void deleteDatensatz(String strSQL) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  stmt.execute(strSQL);
	
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
	/**
	* 
	* Methode zum Einfügen der Zahlungsdatensätze in die Datenbank:
	* @param strSQL: der vorgefertigte SQL String für das Update
	*/
	public static void insertDatensatz(String strSQL) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	  try 
	  {
	  con = VerbindungsManager.getConnection();
	  stmt = con.createStatement();
	  stmt.executeUpdate(strSQL);
	  logger.debug("Datensatz erfolgreich upgedatet.");
	
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


	/**
	* 
	* Methode zur Rückgabe einer Adresse aus der Datenbank
	* 
	* @param id: Der eindeutige Bezeichner der Adresse
	* @return Adresse: die Adresse der vorgegebenen ID
	*/
	public static Einrichtung getEinrichtung(int id) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
 	
	String strSQL = "Select * from Einrichtung where id = "+id;
	Einrichtung e = new Einrichtung();
  	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
  	
	// Einrichtungsobjekt mit Werten füllen
	e.setId(rs.getInt("id"));
	e.setName(rs.getString("name").trim());
	int aID = rs.getInt("adresseID");
	e.setAdresseID(aID);
	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	
	Adresse a = getAdresse(aID);
	e.setAdresse(a);
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
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
		catch (SQLException ex) 
		{ 
		ex.printStackTrace(); 
		}
		rs = null;
	  }
    
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
    	
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}
	return e;
	}

	/**
	* 
	* Methode zur Speicherung einer Einrichtung in der Datenbank
	* 
	* @param Einrichtung: Die Einrichtung
	* @return id: die ID der eingefügten Einrichtung
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static int setEinrichtung(Einrichtung e) 
	{
	int id = 0;
	int adresseID = 0;

	// Prüfung, ob bereits eine Einrichtung mit gleichen Daten gespeichert ist: 	
	String strSQL = "SELECT count(*) "+
					"FROM Einrichtung "+
					"WHERE (name = '"+e.getName()+"')";
		
	int anzahlEinrichtungen = getAnzahlDatensätze(strSQL);
		switch (anzahlEinrichtungen) 
		{
		case 0 : //Kein gleicher Datensatz vorhanden
		// ==================== Speichern der Daten: ====================		
		adresseID = setAdresse(e.getAdresse()); // Die ID der eingefügten oder vorhandenen Adresse
		strSQL =	"INSERT INTO Einrichtung " + 
					"(name,adresseID,erstelltAm) "+ 
					"VALUES ('"+
					e.getName()+"','"+
					adresseID+"','" +					getZeitstempel()+"')";
		insertDatensatz(strSQL);
		// ==================== Speichern der Daten: ====================	
		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		strSQL = 	"SELECT id "+
					"FROM Einrichtung "+
					"WHERE (name = '"+e.getName()+"') AND "+
					"(adresseID = '"+adresseID+"')";		
		id = getDatensatzID(strSQL);
		// ==================== Ermitteln der id: ====================					
				break;
		case 1 : //genau ein gleicher Datensatz vorhanden, es wird nicht gespeichert, nur die ID zurückgegeben
		id = 1;
				break;	
		default : // Es sind mehr als ein gleicher Datensatz vorhanden, da muss ein Fehler ausgegeben werden
		id = 0;
				break;
		}
	return id;
	}

	/**
	* 
	* Methode zur Speicherung einer Einrichtung in der Datenbank
	* 
	* @param Einrichtung: Die Einrichtung
	* @return id: die ID der eingefügten Einrichtung
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static int setEreignis(Ereignis e) 
	{
	int id = 0;
	int datumsFeld = 0;	
	String strSQL = null;
	String zusatzFeld = null;
	
	// Entweder das Ereignis hat ein Datumsfeld, oder eine Hilfstabelle:
	try 
	{
		if (e.getZusatzFeld().equalsIgnoreCase("Hilfstabelle")) 
		{
		zusatzFeld = "'"+e.getNameHilfstabelle()+"'";	
		} 
		else 
		{
		zusatzFeld = null;
		}		
	} 
	catch (NullPointerException ex) 
	{
		zusatzFeld = null;
	}
	
	if (e.isDatumsFeld())
	{
	datumsFeld = 1;	
	} 
	else 
	{
	datumsFeld = 0;
	}
	
	
		// ==================== Speichern der Daten: ====================		
		strSQL =	"INSERT INTO Ereignis " + 
					"(name,kommentar,organisationseinheitID,protected,datumsFeld) "+ 
					"VALUES ('"+
					e.getName()+"',"+
					zusatzFeld+",'"+	
					e.getOrganisationseinheitID()+"',"+
					"0,"+	//Protected wird noch nicht unterstützt									
					datumsFeld+")";
		insertDatensatz(strSQL);

		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		strSQL = 	"SELECT id "+
					"FROM Ereignis "+
					"WHERE (name = '"+e.getName()+"') AND "+
					"(organisationseinheitID = '"+e.getOrganisationseinheitID()+"')";		
		id = getDatensatzID(strSQL);
		// ==================== Ermitteln der id: ====================					
	return id;
	}

	/**
	* 
	* Hier werden alle definierten Ereignisse ausgegeben und in einem Vector abgelegt
	* 
	*/
	public static Vector getEreignisse() 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;		
	Vector v  = new Vector();
	String strSQL = "Select * from Ereignis ORDER BY name";

	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	
	while (rs.next()) 
	{
	Ereignis ereignis = new Ereignis();
	ereignis.setId(rs.getInt("id"));
	ereignis.setName(rs.getString("name").trim());
	
		if (rs.getString("kommentar") != null) 
		{
		ereignis.setHilfstabelle(getValueLabelBeans(rs.getString("kommentar").trim()));	
		}
	ereignis.setOrganisationseinheitID(rs.getString("organisationseinheitID").trim());
	ereignis.setProtected(rs.getBoolean("protected"));
	ereignis.setDatumsFeld(rs.getBoolean("datumsFeld"));
	ereignis.setWiederkehrend(rs.getBoolean("wiederkehrend"));
	
	v.add(ereignis);			
	}

	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
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
		catch (SQLException ex) 
		{ 
		ex.printStackTrace(); 
		}
		rs = null;
	  }
    
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
    	
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}


	return v;
	}


	/**
	* 
	* Hier werden alle definierten Ereignisse ausgegeben und in einem Vector abgelegt
	* 
	*/
	public static Vector getEreignisse(HttpServletRequest req) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;		
	Vector v  = new Vector();
	String strSQL = "Select * from Ereignis ORDER BY name";
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	
	while (rs.next()) 
	{
	Ereignis ereignis = new Ereignis();
	String strTeam = rs.getString("organisationseinheitID").trim();
	
	switch (strTeam.length()) 
	{
	case 3: // Ereigniss ist dem Fachbereich zugeordnet, jeder darf es sehen
		ereignis.setId(rs.getInt("id"));
		ereignis.setName(rs.getString("name").trim());
		
			if (rs.getString("kommentar") != null) 
			{
			ereignis.setHilfstabelle(getValueLabelBeans(rs.getString("kommentar").trim()));	
			}
		ereignis.setOrganisationseinheitID(strTeam);
		ereignis.setProtected(rs.getBoolean("protected"));
		ereignis.setDatumsFeld(rs.getBoolean("datumsFeld"));
		ereignis.setWiederkehrend(rs.getBoolean("wiederkehrend"));
		
		v.add(ereignis);		
	break;

	default: // Rolle muss geprüft werden:
		if (req.isUserInRole(strTeam)) 
		{
			ereignis.setId(rs.getInt("id"));
			ereignis.setName(rs.getString("name").trim());
			
				if (rs.getString("kommentar") != null) 
				{
				ereignis.setHilfstabelle(getValueLabelBeans(rs.getString("kommentar").trim()));	
				}
			ereignis.setOrganisationseinheitID(rs.getString("organisationseinheitID").trim());
			ereignis.setProtected(rs.getBoolean("protected"));
			ereignis.setDatumsFeld(rs.getBoolean("datumsFeld"));
			ereignis.setWiederkehrend(rs.getBoolean("wiederkehrend"));
			
			v.add(ereignis);			
		}
	
	break;
	}
	
	
	
			
	}
	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
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
		catch (SQLException ex) 
		{ 
		ex.printStackTrace(); 
		}
		rs = null;
	  }
	
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
		
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}
	
	
	return v;
	}

	/**
	* 
	* Methode zur Rückgabe eines Ereignisses aus der Datenbank
	* 
	* @param id: die ID der eingefügten Einrichtung
	* 
	*/
	public static Ereignis getEreignis(int id) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;		
	
	String strSQL = "SELECT * "+
					"FROM Ereignis "+
					"WHERE (id = "+id+")";
	
	Ereignis e = new Ereignis();

	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
  	
	e.setId(rs.getInt("id"));
	e.setName(rs.getString("name").trim());
		if (rs.getString("kommentar") != null)
		{
		e.setZusatzFeld("Hilfstabelle");
		e.setNameHilfstabelle(rs.getString("kommentar").trim());
		}
		else
		{
		e.setZusatzFeld("Kommentar");
		}
	e.setOrganisationseinheitID(rs.getString("organisationseinheitID").trim());
	e.setDatumsFeld(rs.getBoolean("datumsFeld"));
	e.setWiederkehrend(rs.getBoolean("wiederkehrend"));
 
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
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
		catch (SQLException ex) 
		{ 
		ex.printStackTrace(); 
		}
		rs = null;
	  }
    
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
    	
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}

							
	return e;
	}

	/**
	* 
	* Methode zur Rückgabe eines Ereignisses aus der Datenbank
	* 
	* @param id: die ID der eingefügten Einrichtung
	* 
	*/
	public static Thema getThema(int id) 
	{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;		
	
	String strSQL = "SELECT * "+
					"FROM Formulare_Thema "+
					"WHERE (id = "+id+")";
	
	Thema t = new Thema();
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	
	t.setId(rs.getInt("id"));
	t.setThema(rs.getString("thema").trim());
	t.setTeam(rs.getString("team").trim());
	
	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
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
		catch (SQLException ex) 
		{ 
		ex.printStackTrace(); 
		}
		rs = null;
	  }
	
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
		
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}
	
							
	return t;
	}

	/**
	* 
	* Methode zur Aktualisierung eines Ereignisses aus der Datenbank
	* 
	* @param Ereignis: Ereignisobjekt mit den aktualisierten Werten
	* @return int rowCount: Die Anzahl der aktualisierten Datensätze
	*/
	public static void updateEreignis(Ereignis e) 
	{
	Connection con = null;
	Statement stmt = null;
	//ResultSet rs = null;
	String zusatzFeld = null; 	
	int datumsFeld = 0;	
	int wiederkehrend = 0;
	//int rowCount = 0;	
	
	// Entweder das Ereignis hat ein Datumsfeld, oder eine Hilfstabelle:
	try 
	{
		if (e.getZusatzFeld().equalsIgnoreCase("Hilfstabelle")) 
		{
		zusatzFeld = "'"+e.getNameHilfstabelle()+"'";	
		} 
		else 
		{
		zusatzFeld = null;
		}		
	} 
	catch (NullPointerException ex) 
	{
		zusatzFeld = null;
	}
	
	if (e.isDatumsFeld())
	{
	datumsFeld = 1;	
	} 
	else 
	{
	datumsFeld = 0;
	}
	
	if (e.isWiederkehrend())
	{
	wiederkehrend = 1;	
	} 
	else 
	{
	wiederkehrend = 0;
	}
	
	String strSQL = 	"Update Ereignis "+ 
						"SET name = '"+e.getName()+"', "+
						"kommentar = "+zusatzFeld+", "+						
						"organisationseinheitID = '"+e.getOrganisationseinheitID()+"', "+
						"protected = 0, "+		
						"datumsFeld = "+datumsFeld+", "+
						"wiederkehrend = "+wiederkehrend+" "+											
						"WHERE (id = "+e.getId()+")";

	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	/*rowCount = */stmt.executeUpdate(strSQL);

	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
	} 
	finally 
	{
    
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
    	
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}

	}


	/**
	* 
	* Methode zur Aktualisierung eines Themas aus der Datenbank
	* 
	* @param Thema: Themaobjekt mit den aktualisierten Werten
	* @return int rowCount: Die Anzahl der aktualisierten Datensätze
	*/
	public static int updateThema(Thema t) 
	{
	Connection con = null;
	Statement stmt = null;
	//ResultSet rs = null;

	int rowCount = 0;	
	

	String strSQL = 	"Update [Formulare_Thema] "+ 
						"SET thema = '"+t.getThema()+"', "+
						"team = '"+t.getTeam()+"' "+						
						"WHERE (id = "+t.getId()+")";
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rowCount = stmt.executeUpdate(strSQL);
	
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
	} 
	finally 
	{
	
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
		
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}
	return rowCount;
	
	}


	/**
	* 
	* Methode zur Aktualisierung einer Einrichtung in der Datenbank
	* 
	* @param Einrichtung: Einrichtungobjekt mit den aktualisierten Werten
	* @return int rowCount: Die Anzahl der aktualisierten Datensätze
	*/
	public static int updateEinrichtung(Einrichtung e, String strUserID) 
	{
	Connection con = null;
	Statement stmt = null;
	//ResultSet rs = null;
	
	// Zunächst wird die Adresse geändert:
	Adresse adresse = e.getAdresse(); 
	updateAdresse(adresse, strUserID);
	
	int rowCount = 0;	
		
	String strSQL = 	"Update Einrichtung "+ 
						"SET name = '"+e.getName()+"' "+
						"WHERE (id = "+e.getId()+")";
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rowCount = stmt.executeUpdate(strSQL);
	
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
	} 
	finally 
	{
	
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
		
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}
	return rowCount;
	
	}


	/**
	* 
	* Methode zur Aktualisierung eines Ereignisses aus der Datenbank
	* 
	* @param Ereignis: Ereignisobjekt mit den aktualisierten Werten
	* @return int rowCount: Die Anzahl der aktualisierten Datensätze
	*/
	public static void updateVerfahrensverlauf(Ereignis e) 
	{
	Connection con = null;
	Statement stmt = null;
	//ResultSet rs = null;
	
	String strSQL = 	"Update Verfahrensverlauf "+ 
						"SET ereignisID = "+e.getFkEreignisID()+", "+
						"datum = '"+e.getDatum()+"', "+						
						"kommentar = '"+e.getKommentar()+"' "+				
						"WHERE (id = "+e.getId()+")";
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	/*int rowCount = */stmt.executeUpdate(strSQL);
	
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
	} 
	finally 
	{
	
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
		
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}
	
	}

	/**
	* 
	* Methode zur Aktualisierung eines Ereignisses aus der Datenbank
	* 
	* @param Ereignis: Ereignisobjekt mit den aktualisierten Werten
	* @return int rowCount: Die Anzahl der aktualisierten Datensätze
	*/
	public static int updateSachbearbeiter(Sachbearbeiter sb, String userID) 
	{
	Connection con = null;
	Statement stmt = null;
	//ResultSet rs = null;
	int rowCount = 0;
	
	String strSQL = 	"Update users "+ 
						"SET user_name = '"+sb.getUsername()+"', "+
						"Anrede = '"+sb.getAnrede()+"', "+						
						"Vorname = '"+sb.getVorname()+"', "+
						"Nachname = '"+sb.getNachname()+"', "+	
						"organisationseinheitID = '"+sb.getOrganisationseinheitID()+"', "+
						"Durchwahl = '"+sb.getDurchwahl().trim()+"', "+	
						"Raum = '"+sb.getRaum().trim()+"', "+	
						"user_pass = '"+sb.getPasswort().trim()+"', "+		
						"aktualisiertAm = '"+getZeitstempel()+"', "+
						"aktualisiertVon = '"+userID+"' "+							
						"WHERE (userid = "+sb.getId()+")";
	
	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rowCount = stmt.executeUpdate(strSQL);
	
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
	} 
	catch (SQLException ex) 
	{
	ex.printStackTrace();
	} 
	finally 
	{
	
	  if (stmt != null) 
	  {
		  try 
		  { 
		  stmt.close(); 
		  } catch (SQLException ex) 
		  {
		  ex.printStackTrace();
		  }
	  stmt = null;
	  }
		
	  if (con != null) 
	  {
		  try 
		  { 
		  con.close(); 
		  } 
		  catch (SQLException ex) 
		  { 
		  ex.printStackTrace(); 
		  }
	  con = null;
	  }
	}
	return rowCount;
	
	}

	/**
	* 
	* Methode zur Aktualisierung der Verfahrensart eines Vorganges
	* 
	* Zunächst wird der Datensatz in der Tabelle Verfahrensart gesucht, 
	* wo das Feld gueltigBis != NULL ist. Dieses Feld wird mit einem Enddatum versehen.
	* 
	* Im zweiten Schritt wird ein neuer Datensatz in die Tabelle Verfahrensart geschrieben, der den neuen Wert für die
	* Verfahrensart mit dem Startdatum enthält. Da die gleiche Funktion bereits in der Methode insertVerfahrensart()
	* implementiert ist, kann diese dafür benutzt werden.
	* 
	* @param VerfahrensartID
	* @param widerspruchID: die eindeutige ID des Widerspruches
	* 
	*/
	public static void updateVerfahrensartID(int verfahrensartID, int widerspruchID, String strUserID) 
	{
	Connection con = null;
	Statement stmt = null;
	//ResultSet rs = null;
	//int rowCount = 0;
	String strSQL = null;
	int anzahlTreffer = 0;	
	
	// Prüfung, ob überhaupt ein passender Datensatz vorhanden ist:
	strSQL =	"SELECT count(*) "+
				"FROM Verfahrensart "+
				"WHERE (widerspruchID = "+widerspruchID+") AND (gueltigBis IS NULL)";
					
	anzahlTreffer = Java2SQLMapper.getAnzahlDatensätze(strSQL);
						
	switch 	(anzahlTreffer) 
			{
			case 1 : // Genau ein Datensatz gefunden, so solls sein ;-)
			strSQL = 	"Update Verfahrensart "+ 
						"SET gueltigBis = '"+getZeitstempel()+"' "+									
						"WHERE (widerspruchID = "+widerspruchID+") AND (gueltigBis IS NULL)";	
				try 
				{
				con = VerbindungsManager.getJNDIConnection();
				stmt = con.createStatement();
				/*rowCount = */stmt.executeUpdate(strSQL);
				
				// zweiter Schritt, neuen Datensatz einfügen:
				strSQL =	"INSERT INTO Verfahrensart " + 
							"(widerspruchID,verfahrensartID,gueltigVon,erfasstAm,erfasstVon) "+ 
							"VALUES ("+
							widerspruchID+","+
							verfahrensartID+",'"+
							getZeitstempel()+"','"+					
							getZeitstempel()+"','"+							
							strUserID+"')";
				insertDatensatz(strSQL);
	
				stmt.close();
				stmt = null;
				con.close(); // Return to connection pool
				con = null;  // Make sure we don't close it twice
				} 
				catch (SQLException ex) 
				{
				ex.printStackTrace();
				} 
				finally 
				{
	
				  if (stmt != null) 
				  {
					  try 
					  { 
					  stmt.close(); 
					  } catch (SQLException ex) 
					  {
					  ex.printStackTrace();
					  }
				  stmt = null;
				  }
		
				  if (con != null) 
				  {
					  try 
					  { 
					  con.close(); 
					  } 
					  catch (SQLException ex) 
					  { 
					  ex.printStackTrace(); 
					  }
				  con = null;
				  }
				}
			break;

			default :
			break;
			}
	
	}
	
	/**
	* 
	* Methode zur Speicherung eines Vertreters in der Datenbank
	* 
	* @param Vertreter: Der Vertreter
	* @return id: die ID des eingefügten Vertreter
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static int setVertreter(Vertreter v) 
	{
	int id,adresseID = 0;
		logger.debug("Prüfe, ob bereits ein Vertreter mit gleichen Daten gespeichert ist:");
	// Prüfung, ob bereits ein Vertreter mit gleichen Daten gespeichert ist: 	
	String strSQL = "SELECT COUNT(*) "+
					"FROM Vertreter "+
					"WHERE (vorname"+v.getVorname(SQL_SELECT)+") AND "+ 
					"(nachname"+v.getNachname(SQL_SELECT)+")";
		logger.debug("SQL: [" + strSQL + "]");
		
	int anzahlVertreter = getAnzahlDatensätze(strSQL);
		logger.debug("Anzahl gefundener Vertreter: " + anzahlVertreter);
		
		switch (anzahlVertreter) 
		{
		case 0 : //Kein gleicher Datensatz vorhanden
			logger.debug("Kein gleicher Datensatz vorhanden - Speichere Vertreter neu ab.");
		// ==================== Speichern der Daten: ====================		
		//	Adresse eingefügen und ID zurückgeben	
		Adresse adresse = v.getAdresse();
			if (adresse.getPostleitzahl() != null && adresse.getOrt() != null) 
			{
			adresseID = setAdresse(adresse);				
			} 
			else 
			{
			adresseID = 0;
			}
			logger.debug("Adresse des Vertreters erhält die ID " + adresseID);
											
		strSQL =	"INSERT INTO Vertreter " + 
					"(anrede,vertreterArtID,vorname,nachname,adresseID,erstelltAm) "+ 
					"VALUES ("+
					v.getAnrede(SQL_INSERT)+","+
					v.getArt()+","+
					v.getVorname(SQL_INSERT)+","+
					v.getNachname(SQL_INSERT)+","+
					adresseID+",'" +
					getZeitstempel()+"')";
			logger.debug("SQL: [" + strSQL + "]");
		insertDatensatz(strSQL);
		// ==================== Speichern der Daten: ====================	
		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		strSQL = 	"SELECT id "+
					"FROM Vertreter "+
					"WHERE (vorname"+v.getVorname(SQL_SELECT)+") AND "+ 
					"(nachname"+v.getNachname(SQL_SELECT)+")";
		id = getDatensatzID(strSQL);
			logger.debug("Vertreter wurde erfolgreich unter der ID " + id + " gespeichert.");
		// ==================== Ermitteln der id: ====================					
				break;
		case 1 : //genau ein gleicher Datensatz vorhanden, es wird nicht gespeichert, nur die ID zurückgegeben
			logger.debug("Genau ein Datensatz vorhanden - Liefere ID des gefundenen Vertreters zurück:");

		strSQL = 	"SELECT id "+
					"FROM Vertreter "+
					"WHERE (vorname"+v.getVorname(SQL_SELECT)+") AND "+ 
					"(nachname"+v.getNachname(SQL_SELECT)+")";
			logger.debug("SQL: [" + strSQL + "]");
		id = getDatensatzID(strSQL);
			logger.debug("Vertreter wurde unter der ID " + id + " gefunden.");
				break;	
		default : // Es sind mehr als ein gleicher Datensatz vorhanden, da muss ein Fehler ausgegeben werden
		id = 0;
			logger.error("DATENFEHLER: ES WURDEN " + anzahlVertreter + " GLEICHE DATENSÄTZE GEFUNDEN ! ES WIRD LEDIGLICH DIE ID 0 ZURÜCKGEGEBEN !");
				break;
		}
	return id;
	}


	/**
	* 
	* Methode zur Speicherung eines Vertreters in der Datenbank
	* 
	* @param Vertreter: Der Vertreter
	* @return id: die ID des eingefügten Vertreter
	* 
	* Der Rückgabewert ist 0, wenn mehr als ein gleicher Datensatz gefunden wurde. 
	* In diesem Fall obliegt es der aufrufenden Methode, eventuelle Fehlerkonstellationen zu erkennen. 
	* 
	*/
	public static int insertORupdateVertreter(Vertreter v, int widerspruchID) 
	{
	int id,adresseID = 0;
	Adresse adresse = null;
	
	// Prüfung, ob bereits ein Vertreter mit gleichen Daten gespeichert ist: 	
	String strSQL = "Select vertreterID from vwVorgang where id = "+widerspruchID;	
		
	int anzahlVertreter = getAnzahlDatensätze(strSQL);
		switch (anzahlVertreter) 
		{
		case 0 : //Kein gleicher Datensatz vorhanden
		// ==================== Speichern der Daten: ====================		
		//	Adresse eingefügen und ID zurückgeben	
		adresse = v.getAdresse();
			if (adresse.getPostleitzahl() != null && adresse.getOrt() != null) 
			{
			adresseID = setAdresse(adresse);				
			} 
			else 
			{
			adresseID = 0;
			}
											
		strSQL =	"INSERT INTO Vertreter " + 
					"(anrede,vertreterArtID,vorname,nachname,adresseID,erstelltAm) "+ 
					"VALUES ("+
					v.getAnrede(SQL_INSERT)+","+
					v.getArt()+","+
					v.getVorname(SQL_INSERT)+","+
					v.getNachname(SQL_INSERT)+","+
					adresseID+",'" +
					getZeitstempel()+"')";
		insertDatensatz(strSQL);
		// ==================== Speichern der Daten: ====================	
		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		strSQL = 	"SELECT id "+
					"FROM Vertreter "+
					"WHERE (vorname"+v.getVorname(SQL_SELECT)+") AND "+ 
					"(nachname"+v.getNachname(SQL_SELECT)+")";
		id = getDatensatzID(strSQL);
		// ==================== Ermitteln der id: ====================					
				break;

		default : // Es ist bereits ein Vertreter vorhanden, es wird upgedatet
		//	Adresse eingefügen und ID zurückgeben	
		adresse = v.getAdresse();
			if (adresse.getPostleitzahl() != null && adresse.getOrt() != null) 
			{
			adresseID = setAdresse(adresse);				
			} 
			else 
			{
			adresseID = 0;
			}	
		strSQL = "UPDATE Vertreter SET " + 
		"anrede = " + v.getAnrede(SQL_UPDATE)+", "+
		"vertreterArtID = " +v.getArt(SQL_UPDATE)+", "+
		"vorname = " + v.getVorname(SQL_UPDATE)+", "+
		"nachname = " + v.getNachname(SQL_UPDATE)+", "+ 
		"adresseID = " + adresseID+", " +
		"erstelltAm = '" +	getZeitstempel()+"' "+
		"WHERE id = " + v.getId();
		
		// HIER FEHLT DIE WHERE KLAUSEL !!!
		
		
		insertDatensatz(strSQL);			
		// ==================== Ermitteln der id: ====================		
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		strSQL = 	"SELECT id "+
					"FROM Vertreter "+
					"WHERE (vorname"+v.getVorname(SQL_SELECT)+") AND "+ 
					"(nachname"+v.getNachname(SQL_SELECT)+")";
		id = getDatensatzID(strSQL);
		// ==================== Ermitteln der id: ====================	
		break;
		}
	return id;
	}


	/**
	* 
	* Methode zur Rückgabe einer Adresse aus der Datenbank
	* 
	* @param id: Der eindeutige Bezeichner der Adresse
	* @return Adresse: die Adresse der vorgegebenen ID
	*/
	public static Adresse getAdresse(int id) 
 	{
		logger.debug("Lade Adresse für Vorgang");
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
 	
 	String strSQL = "Select * from Adresse where id = "+id;
 		logger.debug("SQL: [" + strSQL + "]");
 	Adresse adresse = new Adresse();
 		logger.debug("Erstelle neues Adressobjekt");
  	
  	try 
  	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
  	rs.next();
  	
  	// Adressobjekt mit Werten füllen
  	adresse.setId(rs.getInt("id"));
  	if (rs.getString("strasse") != null) {adresse.setStrasse(rs.getString("strasse").trim());}
  	if (rs.getString("hausnummer") != null) {adresse.setHausnummer(rs.getString("hausnummer").trim());}  	
  	if (rs.getString("postleitzahl") != null) {adresse.setPostleitzahl(rs.getString("postleitzahl").trim());}  
  	if (rs.getString("ort") != null) {adresse.setOrt(rs.getString("ort").trim());}  
 
  	rs.close();
	rs = null;
	stmt.close();
	stmt = null;
	con.close(); // Return to connection pool
	con = null;  // Make sure we don't close it twice
  	} 
  	catch (SQLException e) 
  	{
  	e.printStackTrace();
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
  	return adresse;
	}

	/**
	* 
	* Methode zur Speicherung einer Adresse in der Datenbank
	* Vorab wird geprüft, ob eine gleiche Adresse in der Datenbank vorhanden ist.
	* Wenn Ja (getAnzahlDatensätze(strSQL)> 0) wird die id einer vorhandenen Adresse zurückgegeben.
	* Wenn Nein (getAnzahlDatensätze(strSQL) = 0) wird die neue Adresse eingefügt.
	* 
	* @param adresse: Das Adressobjekt
	* @return id: die ID der eingefügten Adresse
	*/
	public static int setAdresse(Adresse a) 
	{
		logger.debug("Versuche nun, die Adresse in der Datenbank zu speichern:");
		
	int id = 0; // Die ID der eingefügten oder vorhandenen Adresse

	// Prüfung, ob bereits eine Adresse mit gleichen Daten gespeichert ist:
	// Nullwerte müssen leider sytaktisch umgebaut werden:
	String nullWert = null;
	if (a.getHausnummerZusatz()!= null){nullWert = "(zusatz = '"+a.getHausnummerZusatz()+"') AND ";} 
	else {nullWert = "(zusatz IS NULL) AND ";}
	 	
	String strSQL = "SELECT count(*) "+
					"FROM Adresse "+
					"WHERE (strasse = '"+a.getStrasse()+"') AND "+
					"(hausnummer = '"+a.getHausnummer()+"') AND "+
					nullWert+
					"(postleitzahl = '"+a.getPostleitzahl()+"') AND "+
					"(ort = '"+a.getOrt()+"')";
			logger.debug("SQL: [" + strSQL + "]");
		
		// Der SQL Manager gibt Auskunft über die Anzahl der vorhandenen Datensätze
		int anzahlDatensätze = getAnzahlDatensätze(strSQL);
			logger.debug("Anzahl gefundener Adressen: " + anzahlDatensätze );
			
		switch (anzahlDatensätze) 
		{
		case 0 : //Kein gleicher Datensatz vorhanden
			logger.debug("Kein gleicher Datensatz vorhanden - Speichere Adresse neu ab.");
			
		// ==================== Speichern der Daten: ====================		
		strSQL =	"INSERT INTO Adresse " + 
					"(strasse,hausnummer,postleitzahl,ort,erstelltAm) "+ 
					"VALUES ('"+
					a.getStrasse()+"','"+
					a.getHausnummer()+"','"+
					a.getPostleitzahl()+"','"+
					a.getOrt()+"','"+
					getZeitstempel()+"')";
			logger.debug("SQL: [" + strSQL + "]");
			
		insertDatensatz(strSQL);
		// Der SQL Server setzt die id automatisch, die muss jetzt noch geholt werden:
		strSQL = 	"SELECT id "+
					"FROM Adresse "+
					"WHERE (strasse = '"+a.getStrasse()+"') AND "+
					"(hausnummer = '"+a.getHausnummer()+"') AND "+
					"(postleitzahl = '"+a.getPostleitzahl()+"') AND "+
					"(ort = '"+a.getOrt()+"')";		
		id = getDatensatzID(strSQL);
			logger.debug("Adresse wurde erfolgreich unter der ID " + id + " gespeichert.");
			
		// ==================== Speichern der Daten: ====================			
				break;
		default : //genau ein gleicher Datensatz vorhanden, es wird nicht gespeichert, nur die ID zurückgegeben
			logger.debug("Genau ein Datensatz vorhanden - Liefere ID der gefundenen Adresse zurück:");
		
		strSQL = "SELECT id "+
				"FROM Adresse "+
				"WHERE (strasse = '"+a.getStrasse()+"') AND "+
				"(hausnummer = '"+a.getHausnummer()+"') AND "+
				"(postleitzahl = '"+a.getPostleitzahl()+"') AND "+
				"(ort = '"+a.getOrt()+"')";			
			logger.debug("SQL: [" + strSQL + "]");
		id = getDatensatzID(strSQL);
			logger.debug("Adresse wurde unter der ID " + id + " gefunden.");
				break;	
		}
	return id;
	}

	/**
	* 
	* Methode zur Aktualisierung einer Adresse in der Datenbank
	* 
	* @param adresse: Das Adressobjekt
	* @return true, wenn erfolgreich upgedatet wurde 
	* @return false, NICHT erfolgreich upgedatet wurde
	*/
	public static boolean updateAdresse(Adresse a, String strUserID) 
	{
	boolean erfolg = false;
	
	int id = a.getId(); // Die ID der vorhandenen Adresse
	
	// Prüfung, ob bereits eine Adresse mit gleichen Daten gespeichert ist:
	String strSQL = "SELECT count(*) "+
					"FROM Adresse "+
					"WHERE id = "+id;
		
		// Der SQL Manager gibt Auskunft über die Anzahl der vorhandenen Datensätze
		int anzahlDatensätze = getAnzahlDatensätze(strSQL);
		switch (anzahlDatensätze) 
		{
		case 0 : //Kein gleicher Datensatz vorhanden
		erfolg = false;		
		break;
		case 1 : // Genau ein Treffer, so soll´s sein:
		// ==================== Update der Daten: ====================		
		strSQL = 	"Update Adresse SET ";
		// Einrichtungen werden mit optionaler Strasse und Hausnummer gespeichert:
					if (a.getStrasse() != null) 
					{
						strSQL += "strasse = "+getValueForSQL(a.getStrasse(),SQL_UPDATE)+", ";
					}
					if (a.getHausnummer() != null) 
					{
						strSQL += "hausnummer = "+getValueForSQL(a.getHausnummer(),SQL_UPDATE)+", ";
					}	
					
					strSQL +="postleitzahl = "+getValueForSQL(a.getPostleitzahl(),SQL_UPDATE)+", "+
					"ort = "+getValueForSQL(a.getOrt(),SQL_UPDATE)+", "+
					"erstelltAm = '"+getZeitstempel()+"', "+
					"erfasstVon = '"+strUserID+"' "+														
					"WHERE (id = "+id+")";	
					
		insertDatensatz(strSQL);
		erfolg = true;
		// ==================== Update der Daten: ====================			
		break;
		default : //kann eigentlich nicht sein
		erfolg = false;				
		break;	
		}
	return erfolg;
	}


	/**
	 * 
	 * Methode zur Erstellung einer Formularbean mit Werten, für die der Benutzer berechtigt ist.
	 * 
	 * Standardmässig wird bereits im SessionListener eine Formularbean erzeugt und mit defaults belegt.
	 * Dummerweise hat der SessionListener keinen Zugriff auf den RemoteUser und kann somit auch die 
	 * Rollen nicht vernünftig prüfen
	 * 
	 * Diese Methode hat nun ein Request Objekt im Zugriff und kann daher genau die Werte generieren, die der 
	 * Benutzer auch sehen darf.
	 * 
	 * @param strSQL: der vorgefertigte SQL String für das Select
	 * @return Formularbean
	 */
	 public static synchronized FormularBean getFormularbeanForTeams(HttpServletRequest req) 
	 {
	 String strSQL = null;
	 
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
	 	
	 	FormularBean fb = new FormularBean();
	 	
	 	strSQL = "SELECT * FROM vwOEs"; 
	 	Vector teams = new Vector();
	  	
	  	try 
	  	{
		con = VerbindungsManager.getJNDIConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(strSQL);
			// Der Vector wird mit allen eingetragenen Teams gefüllt. 
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			teams.add(new LabelValueBean(label,value));		
			}
	  	rs.close();
	  	fb.setTeams(teams);

	  	// Nun wird ein Vector mit allen Organisationseinheiten erstellt:
	 	//Vector oes = new Vector();
	  	strSQL = "SELECT * from Organisationseinheit";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			//Organisationseinheit oe = new Organisationseinheit();
			
			if (rs.getString("id") != null) 
			{
			//oe.setId(rs.getString("id").trim());	
			}
			if (rs.getString("name") != null) 
			{
			//oe.setName(rs.getString("name").trim());	
			}
			if (rs.getString("azPattern") != null) 
			{
			//oe.setAzPattern(rs.getString("azPattern").trim());	
			}	

			//oe.setAzEindeutig(rs.getBoolean("azEindeutig"));	

			//oes.add(oe);
			}
		rs.close();	  	
		//fb.setOrganisationseinheiten(oes);
		
	  	// Nun werden die Themen ausgelesen:
	 	Vector themen = new Vector();
	  	strSQL = "SELECT * from Formulare_Thema ORDER BY thema";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String strTeam = rs.getString("team").trim();  
			// Wenn der Benutzer in der Rolle ist, wird der Datensatz gespeichert
			if (req.isUserInRole(strTeam) || strTeam.length() < 3) 
				{
				String value = rs.getString(1).trim();
				String label = rs.getString(2).trim();
				themen.add(new LabelValueBean(label,value));
				}				
			}
		rs.close();	  	
		fb.setThemen(themen);
		
	  	// Nun werden die Anreden ausgelesen:
	 	Vector anreden = new Vector();
	  	strSQL = "SELECT * from Formulare_Anreden";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			anreden.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setAnreden(anreden);	

	  	// Nun werden die Verfahrensarten ausgelesen
	 	Vector va = new Vector();
	  	strSQL = "SELECT * from Formulare_Verfahrensart";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			va.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setVerfahrensart(va);
		
	  	// Nun werden die Ergebnisse ausgelesen
	 	Vector ergebnisse = new Vector();
	  	strSQL = "SELECT * from Formulare_Ergebnis";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			ergebnisse.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setErgebnis(ergebnisse);		

	  	// Nun werden die Erledigungsgründe ausgelesen
	 	Vector eg = new Vector();
	  	strSQL = "SELECT * from Formulare_Erledigungsgrund";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			eg.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setErledigungsgrund(eg);

	  	// Nun wird die Schnellsuche ausgelesen
	 	Vector ss = new Vector();
	  	strSQL = "SELECT * from Formulare_SchnellsucheWiderspruch";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			ss.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setSchnellsucheWiderspruch(ss);		
		
	  	// Nun werden die Hilfstabellen ausgelesen
	 	Vector ht = new Vector();
	  	strSQL = "SELECT * from vwHilfstabellen";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			ht.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setHilfstabellen(ht);
		
	  	// Nun werden die Gemeinden ausgelesen
		TreeMap tm = new TreeMap();
	  	strSQL = "SELECT * FROM vwGemeindeListe";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			tm.put(rs.getString(1).trim(),rs.getString(3).trim());
			}				
		rs.close();	  	
		fb.setGemeinden(tm);	

	  	// Nun werden die Einrichtungen ausgelesen
	 	Vector einrichtungen = new Vector();
	  	strSQL = "SELECT id,name FROM Einrichtung WHERE geloeschtAm is NULL";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString(1).trim();
			String label = rs.getString(2).trim();
			einrichtungen.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setEinrichtungen(einrichtungen);
		
	  	strSQL = "Select * from vwEinrichtung";
	  	rs = stmt.executeQuery(strSQL);
	    rs = stmt.executeQuery(strSQL);
	    RowSetDynaClass rsdc = new RowSetDynaClass(rs);			
		rs.close();	  	
		fb.setEinrichtung(rsdc);
		
	  	// Ereignisse sieht der Benutzer, wenn sie dem gesamten Fachbereich (50) oder seiner Rolle zugeordnet sind
		Vector ereignisse  = new Vector();
		strSQL = "Select * from Ereignis ORDER BY name";

		rs = stmt.executeQuery(strSQL);
		
		while (rs.next()) 
		{
			String strOE = rs.getString("organisationseinheitID").trim();
			if (strOE.length() == 2 || req.isUserInRole(strOE)) // Länge 2 entspricht dem gesamten Fachbereich
			{
				Ereignis ereignis = new Ereignis();
				ereignis.setId(rs.getInt("id"));
				ereignis.setName(rs.getString("name").trim());
				
					if (rs.getString("kommentar") != null) 
					{
					ereignis.setHilfstabelle(getValueLabelBeans(rs.getString("kommentar").trim()));	
					}
				ereignis.setOrganisationseinheitID(rs.getString("organisationseinheitID").trim());
				ereignis.setProtected(rs.getBoolean("protected"));
				ereignis.setDatumsFeld(rs.getBoolean("datumsFeld"));
				ereignis.setWiederkehrend(rs.getBoolean("wiederkehrend"));
				
				ereignisse.add(ereignis);				
			}
	
		}		
		fb.setEreignis(ereignisse);
		
	  	// Nun werden die Verfahrensbeteiligten ausgelesen
	 	Vector vb = new Vector();
	  	strSQL = "SELECT * FROM Verfahrensbeteiligte";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString("id").trim();
			String label = rs.getString("bezeichnung").trim();
			vb.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setBeteiligte(vb);		
		
	  	// Nun werden die Rechtsgebiete ausgelesen
	 	Vector rg = new Vector();
	  	strSQL = "SELECT * FROM Rechtsgebiet";
	  	rs = stmt.executeQuery(strSQL);
			while (rs.next()) 
			{
			String value = rs.getString("id").trim();
			String label = rs.getString("bezeichnung").trim();
			rg.add(new LabelValueBean(label,value));
			}				
		rs.close();	  	
		fb.setRechtsgebiet(rg);			  		
		
	  	rs = null;
		stmt.close();
		stmt = null;
		con.close(); // Return to connection pool
		con = null;  // Make sure we don't close it twice
	  	} 
	  	catch (SQLException e) 
	  	{
	  	e.printStackTrace();
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
		
	return fb;
	 }

 
}

