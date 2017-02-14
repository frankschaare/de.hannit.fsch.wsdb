/*
 * Created on 05.04.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.wsdb;

import java.util.Vector;

import org.apache.struts.validator.ValidatorForm;

import de.hannit.fsch.datenbank.Java2SQLMapper;


/**
 * @author Frank Schaare
 *
 * Ereignisse bilden den Verfahrensverlauf des Widerspruches ab.
 * 
 * Ereignisse sind beispielsweise:
 * <ul>
 * <li>Der Eingang des Vorgangs</li>
 * <li>Abgabe des Vorgangs an eine andere Behörde</li>
 * <li>Erledigung des Vorgangs</li> 
 * </ul>
 * 
 * Eingang und Erledigung und Erledigungsgrund des Verfahrens sind besondere, feste Ereignisse, 
 * während alle zwischen Anfang und Ende liegenden Ereignisse frei über die 
 * Administration gepflegt werden können.
 * 
 * Feste Ereignisse sind schreibgeschützt(protected=true) und können NICHT gelöscht werden. 
 * Es kann aber die Bezeichnung geändert werden. 
 * 
 * Wichtige Eigenschaften eines Ereignisses sind ausserdem name(=Bezeichnung), Datum und Kommentar
 * Der Kommentar kann frei sein (Spalte kommentar=<NULL>), oder an eine Hilfstabelle gebunden. In
 * diesem Fall enthält die Spalte kommentar den Namen der Hilfstabelle.
 * 
 * Die zugeordnete Organisationseinheit ist entscheidend für die Sichtbarbeit des Ereignisses. Ist ein 
 * Ereignis der OE '50' zugeordnet, kann es von allen OE´s gesehen werden, die ebenfalls mit 50 beginnen,
 * also praktisch vom gesamten Fachbereich. Ist ein Ereignis dagegen einer Arbeitsgruppe, z.b. '50.04.03'
 * zugeordnet, wird es nur von dieser gesehen.
 * 
 * Die Ereignisobjekte mit deren Eigenschaften werden in der Tabelle 'Ereignis' definiert, die konkreten 
 * Ereignisse in der Tabelle Ereignisse.
 * 
 * Die Eigenschaft 'wiederkehrend' gibt an, ob ein Ereignis ein- oder mehrfach im Verfahrensverlauf
 * gespeichert werden kann.
 *  
 */
public class Ereignis extends ValidatorForm {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id = 0;
private String name = null;
private String datum = null;
private boolean datumsFeld = false;
private String zusatzFeld = null;
private String nameHilfstabelle = null;
private boolean isProtected = false;
private boolean wiederkehrend = false;
private String kommentar = null;
private String organisationseinheitID = null;
private Vector hilfstabelle = null;
private int fkEreignisID;

/**
 * @return Returns the fkEreignisID.
 */
public int getFkEreignisID() {
	return fkEreignisID;
}
/**
 * @param fkEreignisID The fkEreignisID to set.
 */
public void setFkEreignisID(int fkEreignisID) {
	this.fkEreignisID = fkEreignisID;
}
	public Ereignis()
	{
	
	}


/**
 * @return
 */
public String getDatum() 
{
return datum;
}

/**
 * @return
 */
public int getId() {
	return id;
}

/**
 * @return
 */
public String getKommentar() {return kommentar;}
public String getKommentar(int StatementType){return Java2SQLMapper.getValueForSQL(kommentar,StatementType);}

public String getName() {return name;}
public String getDatum(int StatementType){return Java2SQLMapper.getValueForSQL(datum,StatementType);}

/**
 * @return
 */
public String getOrganisationseinheitID() {
	return organisationseinheitID;
}

/**
 * @param string
 */
public void setDatum(String string) {
	datum = string;
}

/**
 * @param i
 */
public void setId(int i) {
	id = i;
}

/**
 * @param string
 */
public void setKommentar(String string) {
	kommentar = string;
}

/**
 * @param string
 */
public void setName(String string) {
	name = string;
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
public boolean isDatumsFeld() {
	return datumsFeld;
}

/**
 * @return
 */
public boolean isProtected() {
	return isProtected;
}

/**
 * @return
 */
public Vector getHilfstabelle() {
	return hilfstabelle;
}

/**
 * @param b
 */
public void setDatumsFeld(boolean b) {
	datumsFeld = b;
}

/**
 * @param b
 */
public void setProtected(boolean b) {
	isProtected = b;
}

/**
 * @param class1
 */
public void setHilfstabelle(Vector v) {
	hilfstabelle = v;
}


/**
 * @return
 */
public String getNameHilfstabelle() {
	return nameHilfstabelle;
}

/**
 * @return
 */
public String getZusatzFeld() {
	return zusatzFeld;
}

/**
 * @param string
 */
public void setNameHilfstabelle(String string) {
	nameHilfstabelle = string;
}

/**
 * @param string
 */
public void setZusatzFeld(String string) {
	zusatzFeld = string;
}

/**
 * @return
 */
public boolean isWiederkehrend() {
	return wiederkehrend;
}

/**
 * @param b
 */
public void setWiederkehrend(boolean b) {
	wiederkehrend = b;
}

}
