/*
 * Created on 16.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.wsdb;

import java.util.Iterator;
import java.util.Vector;

import org.apache.struts.validator.ValidatorForm;

import de.hannit.fsch.benutzerverwaltung.Einrichtung;
import de.hannit.fsch.benutzerverwaltung.Sachbearbeiter;
import de.hannit.fsch.benutzerverwaltung.Vertreter;
import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * <strong>Modell eines Widerspruches</strong>
 * Ein Widerspruch hat verschiedene verschachtelte Eigenschaften:
 * <ul>
 * <li>den Widerspruchsführer</li>
 * <li>ggf. einen Rechtsvertreter</li>
 * <li>den Sachbearbeiter</li>
 * </ul>
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Widerspruch extends ValidatorForm
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id = 0;
private String aktenzeichen;
private String anweisungAbhilfe;
private String sachgebiet;
private String eingangsdatum;
private String angaben;
private String verfahrensartID;
private String verfahrensart;
private String gegenstandDesVerfahrensID;
private String gegenstandDesVerfahrens;
private String rechtsgebiet;
private String rechtsgebietID;
private String erledigtAm;
private String erledigungsgrund;
private String erledigungsgrundID;
private Sachbearbeiter sachbearbeiter; 
private Hilfeempfaenger hilfeempfaenger;
private Vertreter vertreter;
private boolean vertreterVorhanden = false;
private Einrichtung einrichtung;
private Vector verfahrensverlauf = null;
private String verfahrensverlaufID;
private String dae; //Datum der angefochtenen Entscheidung
private String datumrbh; //Datum des Rechtsbehelfes

/**
 * @return Returns the dae.
 */
public String getDae() {
	return dae;
}
/**
 * @param dae The dae to set.
 */
public void setDae(String dae) {
	this.dae = dae;
}
	public Widerspruch()
	{
	this.hilfeempfaenger = new Hilfeempfaenger();
	this.vertreter = new Vertreter();
	this.einrichtung = new Einrichtung();	
	}	

/**
 * @return
 */
public String getAktenzeichen() {return aktenzeichen;}
public String getAktenzeichen(int StatementType){return Java2SQLMapper.getValueForSQL(aktenzeichen,StatementType);}

/**
 * @param string
 */
public void setAktenzeichen(String aktenzeichen) {this.aktenzeichen = aktenzeichen;}

/**
 * @return
 */
public Hilfeempfaenger getHilfeempfaenger() {return hilfeempfaenger;}

/**
 * @param widerspruchsfuehrer
 */
public void setHilfeempfaenger(Hilfeempfaenger hilfeempfaenger) {this.hilfeempfaenger = hilfeempfaenger;}

/**
 * @return
 */
public String getEingangsdatum() {return eingangsdatum;}
public String getEingangsdatum(int StatementType){return Java2SQLMapper.getValueForSQL(eingangsdatum,StatementType);}
/**
 * @return
 */
public Sachbearbeiter getSachbearbeiter() {return sachbearbeiter;}

/**
 * @param string
 */
public void setEingangsdatum(String string) {eingangsdatum = string;}

/**
 * @param sachbearbeiter
 */
public void setSachbearbeiter(Sachbearbeiter sachbearbeiter) {
	this.sachbearbeiter = sachbearbeiter;
}

/**
 * @return
 */
public String getAngaben() {return angaben;}
public String getAngaben(int StatementType){return Java2SQLMapper.getValueForSQL(angaben,StatementType);}
/**
 * @param string
 */
public void setAngaben(String string) {	angaben = string;}


/**
 * @return
 */
public Vertreter getVertreter() {return vertreter;}
public void setVertreter(Vertreter vertreter) {this.vertreter = vertreter;}

/**
 * @return
 */
public boolean isVertreterVorhanden() {return vertreterVorhanden;}
public void setVertreterVorhanden(boolean b) {vertreterVorhanden = b;}

/**
 * @return
 */
public Einrichtung getEinrichtung() {return einrichtung;}
public void setEinrichtung(Einrichtung einrichtung) {this.einrichtung = einrichtung;}

/**
 * @return
 */
public String getSachgebiet() {return sachgebiet;}
public void setSachgebiet(String string) {sachgebiet = string;}

public Vector getVerfahrensverlauf() {return verfahrensverlauf;}
public Iterator getVerfahrensverlaufAsCollection() 
{
	if (verfahrensverlauf != null ) 
	{
	return verfahrensverlauf.iterator();	
	} 
	else 
	{
	return null;
	}

}

/**
 * Im Verfahrensverlauf wird der Verlauf des Verfahrens vom Eingang
 * bis zur Erledigung gespeichert. 
 * 
 * Der Verfahrensverlauf setzt sich aus einer Reihe von Ereignissen zusammen. 
 * Diese werden in der Datenbank in der Tabelle Verfahrensverlauf mit der ID 
 * des zugehörigen Widerspruches gespeichert.
 * 
 * Der Vector wird also so konstruiert, das alle Ereignisse zur WiderspruchsID
 * nach Datum geordnet aus der Datenbank gelesen werden. Dies erledigt wie immer
 * der Java2SQLMapper. 
 * 
 * Der Verfahrensverlauf kann erst dann erstellt werden, wenn der Widerspruch in der Datenbank
 * gespeichert wurde und eine ID für den Widerspruch generiert wurde.
 *
 */
public void setVerfahrensverlauf() 
{
this.verfahrensverlauf = Java2SQLMapper.getVerfahrensverlauf(this.id);
}

/**
 * @return
 */
public int getId() {return id;}
public void setId(int i) {id = i;}

/**
 * @return
 */
public String getRechtsgebiet() {
	return rechtsgebiet;
}

/**
 * @param string
 */
public void setRechtsgebiet(String string) {
	rechtsgebiet = string;
}

/**
 * @param vector
 */
public void setVerfahrensverlauf(Vector vector) {
	verfahrensverlauf = vector;
}
public String getVerfahrensart() {return verfahrensart;}
public void setVerfahrensart(String string) {verfahrensart = string;}

public String getGegenstandDesVerfahrens() {return gegenstandDesVerfahrens;}
public String getGegenstandDesVerfahrens(int StatementType){return Java2SQLMapper.getValueForSQL(gegenstandDesVerfahrens,StatementType);}
public void setGegenstandDesVerfahrens(String string) {gegenstandDesVerfahrens = string;}

public String getVerfahrensartID() {return verfahrensartID;}
public void setVerfahrensartID(String string) {verfahrensartID = string;}

public String getGegenstandDesVerfahrensID() {return gegenstandDesVerfahrensID;}
public String getRechtsgebietID() {return rechtsgebietID;}

public void setGegenstandDesVerfahrensID(String string) {gegenstandDesVerfahrensID = string;}

public void setRechtsgebietID(String string) {rechtsgebietID = string;}

/**
 * @return
 */
public String getErledigtAm() {
	return erledigtAm;
}

/**
 * @param string
 */
public void setErledigtAm(String string) {
	erledigtAm = string;
}

/**
 * @return
 */
public String getErledigungsgrund() {
	return erledigungsgrund;
}

/**
 * @return
 */
public String getErledigungsgrundID() {
	return erledigungsgrundID;
}

/**
 * @param string
 */
public void setErledigungsgrund(String string) {
	erledigungsgrund = string;
}

/**
 * @param string
 */
public void setErledigungsgrundID(String string) {
	erledigungsgrundID = string;
}

/**
 * @return
 */
public String getAnweisungAbhilfe() {
	return anweisungAbhilfe;
}

/**
 * @param string
 */
public void setAnweisungAbhilfe(String string) {
	anweisungAbhilfe = string;
}

/**
 * @return
 */
public String getVerfahrensverlaufID() {
	return verfahrensverlaufID;
}

/**
 * @param string
 */
public void setVerfahrensverlaufID(String string) {
	verfahrensverlaufID = string;
}

/**
 * @return Returns the datumrbh.
 */
public String getDatumrbh() {
	return datumrbh;
}
/**
 * @param datumrbh The datumrbh to set.
 */
public void setDatumrbh(String datumrbh) {
	this.datumrbh = datumrbh;
}
}
