/*
 * Created on 14.05.2004
 */
package de.hannit.fsch.wsdb;

import org.apache.struts.validator.ValidatorForm;

import de.hannit.fsch.benutzerverwaltung.Vertreter;

/**
 * @author fsch
 *
 * Die Klasse Vorgang definiert den gesamten Vorgang der Verfahrens
 * 
 * Sie besteht aus:
 * <ul>
 * <li>dem Hilfeempfänger</li>
 * <li>einem möglichem Betreuer</li>
 * <li>einem möglichem Widerspruchsführer</li>
 * <li>einem möglichem Verfahrensbevollmächtigtem</li>
 * <li>dem Widerspruch</li>
 * <li>einem möglichem Gerichtsverfahren</li>
 * <li>zusätzlichen Sachstandsdaten</li>
 * </ul>
 */
public class Vorgang extends ValidatorForm
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Hilfeempfaenger hilfeempfaenger = new Hilfeempfaenger();
private Vertreter vertreter = new Vertreter();	
private boolean vertreterVorhanden = false;
private Widerspruch widerspruch = new Widerspruch();
private Gerichtsverfahren gerichtsverfahren = null;
/**
 * Token für die Steuerung CREATE oder UPDATE
 */
private int aktion = 0;
public final int CREATE = 1;
public final int UPDATE = 2;

/**
 * @return
 */
public Gerichtsverfahren getGerichtsverfahren() {
	return gerichtsverfahren;
}

/**
 * @return
 */
public Hilfeempfaenger getHilfeempfaenger() {
	return hilfeempfaenger;
}

/**
 * @return
 */
public Vertreter getVertreter() {
	return vertreter;
}

/**
 * @return
 */
public Widerspruch getWiderspruch() {
	return widerspruch;
}

/**
 * @param gerichtsverfahren
 */
public void setGerichtsverfahren(Gerichtsverfahren gerichtsverfahren) {
	this.gerichtsverfahren = gerichtsverfahren;
}

/**
 * @param hilfeempfaenger
 */
public void setHilfeempfaenger(Hilfeempfaenger hilfeempfaenger) {
	this.hilfeempfaenger = hilfeempfaenger;
}

/**
 * @param vertreter
 */
public void setVertreter(Vertreter vertreter) {
	this.vertreter = vertreter;
	this.vertreterVorhanden = true;
}

/**
 * @param widerspruch
 */
public void setWiderspruch(Widerspruch widerspruch) {
	this.widerspruch = widerspruch;
}

/**
 * @return
 */
public boolean getVertreterVorhanden() {
	return vertreterVorhanden;
}

/**
 * @return
 */
public int getAktion() {
	return aktion;
}

/**
 * @param i
 */
public void setAktion(int i) {
	aktion = i;
}

/**
 * @param vertreterVorhanden The vertreterVorhanden to set.
 */
public void setVertreterVorhanden(boolean vertreterVorhanden) {
	this.vertreterVorhanden = vertreterVorhanden;
}
}
