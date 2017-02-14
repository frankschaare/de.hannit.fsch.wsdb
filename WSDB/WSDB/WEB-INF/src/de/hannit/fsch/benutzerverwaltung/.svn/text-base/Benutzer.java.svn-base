/*
 * Created on 22.05.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package de.hannit.fsch.benutzerverwaltung;

/**
 * <h3><strong>Beschreibt den aktuell angemeldeten Benutzer mit allen Eigenschaften</strong></h3>
 * 
 * @version 1.0 vom 16.03.2004
 * @author Frank.Schaare@HannIT.de
 * 
 * Die Standardrolle 'defaultRole' wird beim Abrufen der Hilfedateien abgefragt.
 * Ein Benutzer der Rolle Entwickler erhält daher Hilfedateien aus dem Verzeichnis 'Entwickler'
 *  
 */
public class Benutzer extends Person
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//private String userID;
private String raum;
private String durchwahl;
private String fax;
private String username;
private String userpass;
private String formelleAnrede;
private String sachgebiet;
private String defaultRole;

private boolean LogedIn = false;

public Benutzer(){}

//public String getUserID(){return userID;}
//public void setUserID(String userID){this.userID = userID;}

public String getRaum(){return raum;}
public void setRaum(String raum){this.raum = raum;}

public String getDurchwahl(){return durchwahl;}
public void setDurchwahl(String durchwahl){this.durchwahl = durchwahl;}

public String getFAX(){return fax;}
public void setFAX(String fax){this.fax = fax;}

public String getUsername(){return username;}
public void setUsername(String username){this.username = username;}

public String getUserpass(){return userpass;}
public void setUserpass(String userpass){this.userpass = userpass;}

/**
 * Prüft, ob ein Sachbearbeiter aktuell angemeldet ist.
 *
 */
public boolean isLogedIn(){return this.LogedIn;}
public void setLogedInStatus(boolean status){this.LogedIn = status;}

/**
 * @return
 */
public String getFormelleAnrede() {return formelleAnrede;}

/**
 * Liefert die formelle Anrede des Benutzers, z.b. 'Herr Schaare'
 * 
 * Wird gelegentlich bei Formularfeldern benötigt.
 *  
 */
public void setFormelleAnrede() 
{
	if (this.anrede != null && this.nachname != null) 
	{
	this.formelleAnrede = this.anrede+ " "+this.nachname;	
	} 
	else 
	{
	this.formelleAnrede = "unbekannt";	
	}

}

/**
 * @return
 */
public String getSachgebiet() {
	return sachgebiet;
}

/**
 * @param string
 */
public void setSachgebiet(String string) {
	sachgebiet = string;
}

/**
 * @return
 */
public String getDefaultRole() {
	return defaultRole;
}

/**
 * @param string
 */
public void setDefaultRole(String string) {
	defaultRole = string;
}

}
