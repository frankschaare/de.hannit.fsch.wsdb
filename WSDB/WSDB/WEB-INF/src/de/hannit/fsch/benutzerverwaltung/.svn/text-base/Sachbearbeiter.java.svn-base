/*
 * Created on 22.05.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package de.hannit.fsch.benutzerverwaltung;

import java.util.*;

/**
 * <h3><strong>Beschreibt einen Sachbearbeiter mit allen Eigenschaften</strong></h3>
 * 
 * @version 1.0 vom 22.08.2003
 * @author Frank.Schaare@HannIT.de
 */
public class Sachbearbeiter extends Person
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String aktion = null; //Token für die Rechteoperation 
private String userID;
private int id;
private String strRaum;
private String strDurchwahl;
private String strFAX;
private String username;
private String passwort;
private String passwort2;
private String organisationseinheitID = null;


private Sachgebiet sachgebiet = new Sachgebiet();

private boolean LogedIn = false;

public Sachbearbeiter(){}

public String getUserID(){return userID;}
public void setUserID(String userID){this.userID = userID;}

public String getRaum(){return strRaum;}
public void setRaum(String raum){strRaum = raum;}

public String getDurchwahl(){return strDurchwahl;}
public void setDurchwahl(String durchwahl){strDurchwahl = durchwahl;}

public String getFax(){return strFAX;}
public void setFax(String fax){strFAX = fax;}

public String getUsername(){return username;}
public void setUsername(String name){username = name;}

public String getPasswort(){return passwort;}
public void setPasswort(String userpass){passwort = userpass;}
public String getPasswort2(){return passwort2;}
public void setPasswort2(String userpass){passwort2 = userpass;}

/**
 * Liefert den tatsächlichen Active Directory Namen des Benutzers
 * 
 */

public String getActiveDirectoryName()
{
StringTokenizer st = new StringTokenizer(System.getProperty("user.home"),"\\");	
int iAnzahl = st.countTokens();
String[] inhalt = new String[iAnzahl];

	for (int i = 0; i < iAnzahl; i++)
	{
	inhalt[i] = st.nextToken();	
	}
return inhalt[iAnzahl-1];
}
/**
 * Prüft, ob ein Sachbearbeiter aktuell angemeldet ist.
 *
 */
public boolean isLogedIn(){return this.LogedIn;}
public void setLogedInStatus(boolean status){this.LogedIn = status;}
/**
 * @return
 */
public Sachgebiet getSachgebiet() {
	return sachgebiet;
}

/**
 * @param sachgebiet
 */
public void setSachgebiet(Sachgebiet sachgebiet) {
	this.sachgebiet = sachgebiet;
}

/**
 * @return
 */
public String getOrganisationseinheitID() {
	return organisationseinheitID;
}

/**
 * @param string
 */
public void setOrganisationseinheitID(String string) {
	organisationseinheitID = string;
}

/**
 * @return
 */
public String getAktion() {
	return aktion;
}

/**
 * @param string
 */
public void setAktion(String string) {
	aktion = string;
}

/**
 * @return
 */
public int getId() {
	return id;
}

/**
 * @param i
 */
public void setId(int i) {
	id = i;
}

}
