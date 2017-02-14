package de.hannit.fsch.wsdb;

import de.hannit.fsch.benutzerverwaltung.Einrichtung;
import de.hannit.fsch.benutzerverwaltung.Sachbearbeiter;
import de.hannit.fsch.benutzerverwaltung.Vertreter;

/**
 * @author fsch
 *
 * <strong>Verfahrensverlauf eines Widerspruches</strong>
 * 
 * Im Verfahrensverlauf werden die verschiedenen Ereignisse bei der Sachbearbeitung dargestellt,
 * beispielsweise Eingang, Abgabe an eine andere Behörde, Entscheidung, etc.
 *   
 */
public class Verfahrensverlauf
{
private String aktenzeichen;
private String eingangsdatum;
private String angaben;
private boolean erledigt;
private Sachbearbeiter sachbearbeiter; 
private Hilfeempfaenger hilfeempfaenger;
private Vertreter vertreter;
private boolean vertreterVorhanden;
private Einrichtung einrichtung;

	public Verfahrensverlauf()
	{
	this.hilfeempfaenger = new Hilfeempfaenger();
	this.vertreter = new Vertreter();
	this.einrichtung = new Einrichtung();	
	}	

/**
 * @return
 */
public String getAktenzeichen() {return aktenzeichen;}

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

/**
 * @param string
 */
public void setAngaben(String string) {	angaben = string;}

/**
 * @return
 */
public boolean isErledigt() {return erledigt;}
public void setErledigt(boolean b) {erledigt = b;}

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

}
