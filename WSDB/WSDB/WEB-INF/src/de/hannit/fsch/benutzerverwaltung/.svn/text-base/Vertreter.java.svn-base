/*
 * Created on 24.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.benutzerverwaltung;

import de.hannit.fsch.datenbank.Java2SQLMapper;

/**
 * @author fsch
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Vertreter extends Person 
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String id;
private Adresse adresse;
private String art;
private String bezeichnung;

	public Vertreter()
	{
	this.adresse = new Adresse();
	}
/**
 * @return
 */
public Adresse getAdresse() {
	return adresse;
}

/**
 * @return
 */
public String getId() {
	return id;
}

/**
 * @param adresse
 */
public void setAdresse(Adresse adresse) {
	this.adresse = adresse;
}

/**
 * @param string
 */
public void setId(String string) {
	id = string;
}

public String getArt() {return art;}
public String getArt(int StatementType) {return Java2SQLMapper.getValueForSQL(this.art,StatementType);}
public void setArt(String string) {art = string;}

public String getBezeichnung() {return bezeichnung;}
public void setBezeichnung(String string) {bezeichnung = string;}
/**
 * @param SQL_INSERT
 * @return
 */
public String getId(int StatementType) {
return Java2SQLMapper.getValueForSQL(this.id,StatementType);
}

}