/*
 * Created on 16.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.wsdb;

import de.hannit.fsch.benutzerverwaltung.Adresse;
import de.hannit.fsch.benutzerverwaltung.Einrichtung;
import de.hannit.fsch.benutzerverwaltung.Person;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Hilfeempfaenger extends Person {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id = 0;
private Adresse adresse = null;
private String ga;
private String sterbedatum;
private int widerspruchID;
private Einrichtung einrichtung = new Einrichtung();

public Hilfeempfaenger()
{
this.adresse = new Adresse();
}

/**
 * @return
 */
public String getGa(){return ga;}
public String getGa(int StatementType){return Java2SQLMapper.getValueForSQL(ga,StatementType);}

/**
 * @param string
 */
public void setGa(String string) 
{
	if (string.length() < 1 |string.equalsIgnoreCase("bitte auswählen:"))
	{
	this.ga = null;
	}
	else
	{
	this.ga = string;
	}
}

/**
 * @return
 */
public String getSterbedatum() {return sterbedatum;}
public String getSterbedatum(int StatementType){return Java2SQLMapper.getValueForSQL(sterbedatum,StatementType);}

public void setSterbedatum(String sterbedatum) {
	if (sterbedatum == null | sterbedatum.length()<1) {this.sterbedatum = null;} 
	else {this.sterbedatum = sterbedatum;}
}

/**
 * @return
 */
public int getWiderspruchID() {return widerspruchID;}
public void setWiderspruchID(int i) {widerspruchID = i;}

public Adresse getAdresse() {return adresse;}
public void setAdresse(Adresse adresse) {this.adresse = adresse;}

public int getId() {return id;}
public void setId(int i) {id = i;}

/**
 * @return
 */
public Einrichtung getEinrichtung() {
	return einrichtung;
}

/**
 * @param einrichtung
 */
public void setEinrichtung(Einrichtung einrichtung) {
	this.einrichtung = einrichtung;
}

}
