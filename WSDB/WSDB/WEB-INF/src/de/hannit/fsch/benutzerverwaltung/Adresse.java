/*
 * Created on 24.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.benutzerverwaltung;

/**
 * @author fsch
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Adresse 
{
private int id;
private String strasse;
private String hausnummer;
private String hausnummerZusatz = null;
private String postleitzahl = null;
private String ort = null;
private String telefonVorwahl;
private String telefonDurchwahl;
private String handyVorwahl;
private String handyDurchwahl;
private String fax;
private String eMail;
private String internetURL;
/**
 * @return
 */
public String getHausnummer() {
	return hausnummer;
}

/**
 * @return
 */
public String getHausnummerZusatz() {
	return hausnummerZusatz;
}

/**
 * @return
 */
public String getOrt() {
	return ort;
}

/**
 * @return
 */
public String getPostleitzahl() {
	return postleitzahl;
}

/**
 * @return
 */
public String getStrasse() {
	return strasse;
}

/**
 * @param string
 */
public void setHausnummer(String string) {
	hausnummer = string;
}

/**
 * @param string
 */
public void setHausnummerZusatz(String string) {
	hausnummerZusatz = string;
}

/**
 * @param string
 */
public void setOrt(String string) {
	ort = string;
}

/**
 * @param string
 */
public void setPostleitzahl(String string) {
	postleitzahl = string;
}

/**
 * @param string
 */
public void setStrasse(String string) {
	strasse = string;
}

/**
 * @return
 */
public int getId() {return id;}
public void setId(int i) {id = i;}

/**
 * @return
 */
public String getEMail() {return eMail;
}

/**
 * @return
 */
public String getFax() {
	return fax;
}

/**
 * @return
 */
public String getHandyDurchwahl() {
	return handyDurchwahl;
}

/**
 * @return
 */
public String getHandyVorwahl() {
	return handyVorwahl;
}

/**
 * @return
 */
public String getInternetURL() {return internetURL;}

/**
 * @return
 */
public String getTelefonDurchwahl() {
	return telefonDurchwahl;
}

/**
 * @return
 */
public String getTelefonVorwahl() {
	return telefonVorwahl;
}

/**
 * @param string
 */
public void setEMail(String string) {
	eMail = string;
}

/**
 * @param string
 */
public void setFax(String string) {
	fax = string;
}

/**
 * @param string
 */
public void setHandyDurchwahl(String string) {
	handyDurchwahl = string;
}

/**
 * @param string
 */
public void setHandyVorwahl(String string) {
	handyVorwahl = string;
}

/**
 * @param string
 */
public void setInternetURL(String string) {
	internetURL = string;
}

/**
 * @param string
 */
public void setTelefonDurchwahl(String string) {
	telefonDurchwahl = string;
}

/**
 * @param string
 */
public void setTelefonVorwahl(String string) {
	telefonVorwahl = string;
}

}
