package de.hannit.fsch.datenbank;

import java.sql.*;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

import de.hannit.fsch.wsdb.FormularBean;


/**
 * @author Frank.Schaare@HannIT.de
 * @version 1.0 vom 27.11.2002 11:47:15
 * 
 * <p>Die Klasse SQLManager behandelt zentral alle Datenbankanfragen:</p>
 * 
 */
public class SQLManager
{
 Statement stmt = null; 
 ResultSet rs = null;
 Connection con = null;
 private RowSetDynaClass rsdc = null;

 /**
 * 
 * Methode zur Rückgabe einer RowSetDynaClass.
 * 
 * @param strSQL: der vorgefertigte SQL String für das Select
 * @return RowSetDynaClass
 */
 public RowSetDynaClass getRowSetDynaClass(String strSQL) 
 {
 //String strWert = null;	
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
 * Methode zur Rückgabe einer RowSetDynaClass.
 * 
 * @param strSQL: der vorgefertigte SQL String für das Select
 * @return RowSetDynaClass
 */
 public RowSetDynaClass getWiderspruchsListe(HttpServletRequest req) 
 {
	String strSQL = null;
	HttpSession session = req.getSession();
	
	if (req.isUserInRole("50")) // Die Chefin sieht alles 
	{
		strSQL = "SELECT * from vwWiderspruch WHERE verfahrensartID = 1";	
	} 
	else 
	{
		FormularBean fb = (FormularBean) session.getAttribute("fb");
		Vector teams = fb.getTeams();
	
		strSQL = "SELECT * from vwWiderspruch WHERE left(aktenzeichen,5) in (";
	
		for (int i = 0; i < teams.size(); i++) 
		{
		LabelValueBean lvb = (LabelValueBean) teams.elementAt(i);	
		
			String value = lvb.getValue();
			if (req.isUserInRole(value)) 
			{
			strSQL += "'"+value+"',";	
			}
			// Ooops, ein Komma zu viel:

		}
		int letzter = strSQL.lastIndexOf(",");
		strSQL = strSQL.substring(0,letzter);
		strSQL += ") AND verfahrensartID = 1"; // 2 enspricht Klage
	}	
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
 * Methode zur Rückgabe einer RowSetDynaClass.
 * 
 * @param strSQL: der vorgefertigte SQL String für das Select
 * @return RowSetDynaClass
 */
 public RowSetDynaClass getListeForTeam(String view, HttpServletRequest req) 
 {
	String strSQL = null;
	HttpSession session = req.getSession();
	
	if (req.isUserInRole("50")) // Die Chefin sieht alles 
	{
		strSQL = "Select * from "+ view;	
	} 
	else 
	{
		FormularBean fb = (FormularBean) session.getAttribute("fb");
		Vector teams = fb.getTeams();
	
		strSQL = "Select * from "+view+ " WHERE left(aktenzeichen,5) in (";
	
		for (int i = 0; i < teams.size(); i++) 
		{
		LabelValueBean lvb = (LabelValueBean) teams.elementAt(i);	
		
			String value = lvb.getValue();
			if (req.isUserInRole(value)) 
			{
			strSQL += "'"+value+"',";	
			}
			// Ooops, ein Komma zu viel:

		}
		int letzter = strSQL.lastIndexOf(",");
		strSQL = strSQL.substring(0,letzter);
		strSQL += ")"; // 2 enspricht Klage
	}	
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
 * Methode zur Rückgabe einer RowSetDynaClass.
 * 
 * @param strSQL: der vorgefertigte SQL String für das Select
 * @return RowSetDynaClass
 */
 public RowSetDynaClass getThemenForTeam(String view, HttpServletRequest req) 
 {
	String strSQL = null;
	HttpSession session = req.getSession();
	
	if (req.isUserInRole("50")) // Die Chefin sieht alles 
	{
		strSQL = "Select * from "+ view;	
	} 
	else 
	{
		FormularBean fb = (FormularBean) session.getAttribute("fb");
		Vector teams = fb.getTeams();
	
		strSQL = "Select * from "+view+ " WHERE team in (";
	
		for (int i = 0; i < teams.size(); i++) 
		{
		LabelValueBean lvb = (LabelValueBean) teams.elementAt(i);	
		
			String value = lvb.getValue();
			if (req.isUserInRole(value)|| value.length() == 2) // Länge 2 = Fachbereich 
			{
			strSQL += "'"+value+"',";	
			}
			// Ooops, ein Komma zu viel:

		}
		int letzter = strSQL.lastIndexOf(",");
		strSQL = strSQL.substring(0,letzter);
		strSQL += ") ORDER BY thema"; // 2 enspricht Klage
	}	
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
 * Methode zur Rückgabe einer RowSetDynaClass.
 * 
 * @param strSQL: der vorgefertigte SQL String für das Select
 * @return RowSetDynaClass
 */
 public RowSetDynaClass getEreignisseForTeam(String view, HttpServletRequest req) 
 {
	String strSQL = null;
	HttpSession session = req.getSession();
	
	if (req.isUserInRole("50")) // Die Chefin sieht alles 
	{
		strSQL = "Select * from "+ view;	
	} 
	else 
	{
		FormularBean fb = (FormularBean) session.getAttribute("fb");
		Vector teams = fb.getTeams();
	
		strSQL = "Select * from "+view+ " WHERE organisationseinheitID in (";
	
		for (int i = 0; i < teams.size(); i++) 
		{
		LabelValueBean lvb = (LabelValueBean) teams.elementAt(i);	
		
			String value = lvb.getValue();
			if (req.isUserInRole(value) || value.length() == 2) 
			{
			strSQL += "'"+value+"',";	
			}
			// Ooops, ein Komma zu viel:

		}
		int letzter = strSQL.lastIndexOf(",");
		strSQL = strSQL.substring(0,letzter);
		strSQL += ")"; // 2 enspricht Klage
	}	
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
 * Methode zur Rückgabe einer RowSetDynaClass.
 * 
 * @param strSQL: der vorgefertigte SQL String für das Select
 * @return RowSetDynaClass
 */
 public int getMaximum(String spalte, String tabelle) 
 {
	int i = 0;
	String strSQL = "SELECT MAX("+spalte+") AS maxWert FROM "+tabelle;
 
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
  * Methode zur Rückgabe eines bestimmten Feldes.
  * 
  * @param strSQL: der vorgefertigte SQL String für das Select
  * @return String PersID
  */
  public String getSingleField(String strSQL) 
  {
  String strWert = null;	
  	try 
  	{
  	con = VerbindungsManager.getConnection();
  	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	strWert = rs.getString(1);
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
	return strWert;
  }

  /**
  * 
  * Methode zur Rückgabe der ID eines Datensatzes.
  * 
  * @param strSQL: der vorgefertigte SQL String
  * @return int ID
  */
  public int getDatensatzID(String strSQL) 
  {
  int wert = 0;	
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
  * Methode zur Erschaffung eines Sachbearbeiterobjektes
  * 
  * @param strUserID: die ID, welche als Parameter aus der Ergebnisliste übergeben wird
  * @return Sachbearbeiter
  */
  public DynaActionForm getSachbearbeiter(String strUserID, ActionForm form) 
  {
  DynaActionForm dForm = null;
  String strSQL = 	"SELECT userid, Anrede, Vorname, Nachname, Raum, Durchwahl, Fax, user_name, user_pass "+
  					"FROM users "+
  					"WHERE (userid = "+strUserID+")";
  
  	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	// Datenbankabfrage nach allen benötigten Benutzerdaten:
	rs = stmt.executeQuery(strSQL);
	rs.next();

	dForm = (DynaActionForm) form;

	// Nachdem die dynamisch erzeugte Bean vorliegt, kann sie mit Werten gefüllt werden:
	// dynaBean.set("userID",rs.getString("userid").trim());
	dForm.set("anrede",rs.getString("Anrede").trim());
	dForm.set("vorname",rs.getString("Vorname").trim());
	dForm.set("nachname",rs.getString("Nachname").trim());
	dForm.set("raum",rs.getString("Raum").trim());
	dForm.set("durchwahl",rs.getString("Durchwahl").trim());		
		if (rs.getString("Fax") != null) 
		{
		dForm.set("fax",rs.getString("Fax").trim());		
		}
	dForm.set("username",rs.getString("user_name").trim());
		if (rs.getString("user_pass") != null) 
		{
		dForm.set("userpass",rs.getString("user_pass").trim());	
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
	return dForm;
  }
 
  /**
  * 
  * Methode zur Rückgabe einer Hashmap aus einer Wertetabelle
  * 
  * @param strTabelle: die Tabelle, welche die Wertepaare enthält. 
  * Es <strong>MUSS</strong> eine zweispaltige Tabelle sein !
  * @return Sachbearbeiter
  */
  public Vector getValueLabelBeans(String strTabelle) 
  {
  String strSQL = 	"SELECT * FROM " + strTabelle; 
  Vector v = new Vector();
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

  /**
  * 
  * Methode zur Rückgabe einer Liste mit Gemeindewerten
  * 
  * @param strTabelle: die Tabelle, welche die Wertepaare enthält. 
  * Es <strong>MUSS</strong> eine zweispaltige Tabelle sein !
  * @return Sachbearbeiter
  */
  public TreeMap getGemeindeListe() 
  {
  String strSQL = 	"SELECT * FROM vwGemeindeListe"; 
  TreeMap tm = new TreeMap();

	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	// Datenbankabfrage nach allen benötigten Benutzerdaten:
	rs = stmt.executeQuery(strSQL);
	while (rs.next()) 
	{
	tm.put(rs.getString(1).trim(),rs.getString(3).trim());
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
	return tm;
  }

  /**
  * 
  * Methode zur Rückgabe einer Liste mit den bereits erfassten Einrichtungen
  * 
  * referer: SessionListener
  * @return Vector
  */
  public Vector getEinrichtungen() 
  {
  String strSQL = 	"SELECT id,name FROM Einrichtung WHERE geloeschtAm is NULL"; 
  Vector v = new Vector();

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

 
  /**
  * 
  * Methode zur Rückgabe einer Hashmap mit allen aktuellen Sachbearbeitern der Datenbank
  * 
  * @return Sachbearbeiter HashMap
  */
  public Vector getSachbearbeiterVector() 
  {
  String strSQL = 	"SELECT * FROM [users] ORDER BY Nachname"; 
  Vector v = new Vector();

	try 
	{
	con = VerbindungsManager.getJNDIConnection();
	stmt = con.createStatement();
	// Datenbankabfrage nach allen benötigten Benutzerdaten:
	rs = stmt.executeQuery(strSQL);
	while (rs.next()) 
	{
	String value = rs.getString("userid").trim();
	String label = rs.getString("Anrede").trim()+" "+rs.getString("Nachname").trim();
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
  /**
  * 
  * Methode zur Prüfung, wie viele Zahlungsdatensätze vorhanden sind.
  * 
  * @param strSQL: der vorgefertigte SQL String für das Update
  * @return true, wenn der Datensatz besteht, false wenn der Datensatz nicht vorhanden ist.
  */
  public int countDatensatzVorhanden(String strSQL) 
  {
  int iCount = 0;	
  	try 
  	{
  	con = VerbindungsManager.getConnection();
  	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	iCount = rs.getInt(1);
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
	return iCount;
  }

  /**
  * 
  * Methode zur Prüfung, ob bereits ein Zahlungsdatensatz vorhanden ist.
  * 
  * @param strSQL: der vorgefertigte SQL String für das Update
  * @return true, wenn der Datensatz besteht, false wenn der Datensatz nicht vorhanden ist.
  */
  public boolean checkDatensatzVorhanden(String strSQL) 
  {
  int iCount = 0;	
  	try 
  	{
  	con = VerbindungsManager.getConnection();
  	stmt = con.createStatement();
	rs = stmt.executeQuery(strSQL);
	rs.next();
	iCount = rs.getInt(1);
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
  	if (iCount > 0) //Bereits ein Datensatz vorhanden
	{
	return true;	
	}
	else
	{
	return false;
	} 
  }
 
  /**
  * 
  * Methode zum Einfügen der Zahlungsdatensätze in die Datenbank:
  * @param strSQL: der vorgefertigte SQL String für das Update
  */
  public void insertSatz(String strSQL) 
  {
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
  * Methode zum Löschen von Datensätzen:
  * @param strSQL: der vorgefertigte SQL String für das Update
  */
  public boolean deleteSatz(String strSQL) 
  {
  	boolean b = true;
	try 
  	{
  	con = VerbindungsManager.getConnection();
  	stmt = con.createStatement();
	b = stmt.execute(strSQL);
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
	return b;
  }   
  
   
}
