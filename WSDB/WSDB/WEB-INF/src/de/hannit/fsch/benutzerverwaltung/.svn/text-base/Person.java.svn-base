/* Created on 22.05.2003 */

package de.hannit.fsch.benutzerverwaltung;

import org.apache.struts.validator.ValidatorForm;

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * Die Klasse Person beschreibt die generellen Eigenschaften.
 * 
 * Sie vererbt an die Klassen Widerspruchsführer und Sachbearbeiter
 */

public class Person extends ValidatorForm
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String vorname = null;
protected String nachname = null;
private String geburtsdatum = null;
//private SimpleDateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");
private String geschlecht = null;
protected String anrede =null;


public String getVorname(){return vorname;}
public String getVorname(int StatementType){return Java2SQLMapper.getValueForSQL(vorname,StatementType);}
public void setVorname(String vorname){this.vorname = vorname;}

public String getNachname(){return nachname;}
public String getNachname(int StatementType){return Java2SQLMapper.getValueForSQL(nachname,StatementType);}
public void setNachname(String nachname){this.nachname = nachname;}

public String getGeburtsdatum(){return geburtsdatum;}
public String getGeburtsdatum(int StatementType){return Java2SQLMapper.getValueForSQL(geburtsdatum,StatementType);}
public void setGeburtsdatum(String geburtsdatum){this.geburtsdatum = geburtsdatum;}

public String getGeschlecht(){return geschlecht;}
public void setGeschlecht(String strGeschlecht){geschlecht = strGeschlecht;}

public String getAnrede(){return anrede;}
public String getAnrede(int StatementType){return Java2SQLMapper.getValueForSQL(anrede,StatementType);}
public void setAnrede(String strAnrede){anrede = strAnrede;}


}
