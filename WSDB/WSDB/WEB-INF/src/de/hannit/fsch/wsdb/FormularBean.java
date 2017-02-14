package de.hannit.fsch.wsdb;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import de.hannit.fsch.datenbank.Java2SQLMapper;
import de.hannit.fsch.datenbank.SQLManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Speichert alle von den Formularen der Applikation benötigten Informationen
 */

public final class FormularBean extends ActionForm 
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private SQLManager sqlManager = new SQLManager();	
private	Vector anreden = new Vector();
private	Vector sachbearbeiter = new Vector();
private	Vector teams = new Vector();
private	Vector verfahrensart = new Vector();
private	Vector rechtsgebiet = new Vector();
private	Vector themen = new Vector();
private	Vector unterthemen = new Vector();
private	Vector ergebnis = new Vector();
private	Vector erledigungsgrund = new Vector();
private	Vector suchbegriffe = new Vector();
private	Vector einrichtungen = new Vector();
private RowSetDynaClass einrichtung = null;
private	Vector schnellsucheWiderspruch = new Vector();
private	Vector hilfstabellen = new Vector();
private	TreeMap gemeinden = new TreeMap();
private String eingangsdatum;
private String erledigungsdatum;
private String button;
private	Vector ereignis = new Vector();
private	Vector beteiligte = new Vector();


	/**
	 * Standard Konstruktor 
	 */
	public FormularBean() 
	{
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMAN);
	this.eingangsdatum = df.format(new Date());
	this.erledigungsdatum = df.format(new Date());
			
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
	}
	
	/**
	 * liest dynamische Werte aus der Datenbank und füllt die entsprechenden Elemente
	 */
	public void populate()
	{
	// Als erstes werden alle erfassten Teams ausgelesen, da viele andere Elemente Teambasiert sind:
	this.setTeams(sqlManager.getValueLabelBeans("vwOEs"));	
	this.setThemen(sqlManager.getValueLabelBeans("Formulare_Thema"));
	this.setAnreden(sqlManager.getValueLabelBeans("Formulare_Anreden"));
	this.setVerfahrensart(sqlManager.getValueLabelBeans("Formulare_Verfahrensart"));
	
	//this.setUnterthemen(sqlManager.getValueLabelBeans("Formulare_Unterthema1"));
	this.setErgebnis(sqlManager.getValueLabelBeans("Formulare_Ergebnis"));
	this.setErledigungsgrund(sqlManager.getValueLabelBeans("Formulare_Erledigungsgrund"));
	// Werte für die Selects 'Schnellsuche' in der Head.jsp
	this.setSchnellsucheWiderspruch(sqlManager.getValueLabelBeans("Formulare_SchnellsucheWiderspruch"));	
	this.setHilfstabellen(sqlManager.getValueLabelBeans("vwHilfstabellen"));
	this.setGemeinden(sqlManager.getGemeindeListe());
	this.setEinrichtungen(sqlManager.getEinrichtungen());
	this.setEinrichtung(sqlManager.getRowSetDynaClass("Select * from vwEinrichtung"));
	//Sachbearbeiter Select wird z.Zt. nicht benötigt
	//this.setSachbearbeiter(sqlManager.getSachbearbeiterVector());
	this.setEreignis(Java2SQLMapper.getEreignisse());
	this.setBeteiligte(sqlManager.getValueLabelBeans("Verfahrensbeteiligte"));
	this.setRechtsgebiet(sqlManager.getValueLabelBeans("Rechtsgebiet"));
	}
	
	/**
	 * @return HashMap mit den Werten für das Select Aufgaben
	 */
	public Vector getHmAnreden(){return anreden;}

	/**
	 * @param map
	 */
	public void setAnreden(Vector v) {anreden = v;}

	/**
	 * @return HashMap mit den Werten für das Select Aufgaben
	 */
	public Vector getSachbearbeiter(){return sachbearbeiter;}

	/**
	 * @param map
	 */
	public void setSachbearbeiter(Vector vector) {sachbearbeiter = vector;}
	
	/**
	 * @return
	 */
	public Vector getVerfahrensart() {return verfahrensart;}
	
	/**
	 * @param map
	 */
	public void setVerfahrensart(Vector v) {verfahrensart = v;}

/**
 * @return
 */
public TreeMap getGemeinden() {
	return gemeinden;
}

/**
 * @param map
 */
public void setGemeinden(TreeMap map) {
	gemeinden = map;
}

/**
 * @return
 */
public String getEingangsdatum() {return eingangsdatum;}

/**
 * @param date
 */
public void setEingangsdatum(String datum) {
	eingangsdatum = datum;
}

/**
 * @return
 */
public Vector getThemen() {return themen;}
public void setThemen(Vector vector) {themen = vector;}

/**
 * @return
 */
public Vector getAnreden() {return anreden;}

/**
 * @return
 */
public Vector getUnterthemen() {return unterthemen;}
public void setUnterthemen(Vector vector) {unterthemen = vector;}

/**
 * @return
 */
public Vector getErgebnis() {return ergebnis;}
public void setErgebnis(Vector vector) {ergebnis = vector;}

/**
 * @return
 */
public String getErledigungsdatum() {return erledigungsdatum;}

/**
 * @return
 */
public Vector getErledigungsgrund() {return erledigungsgrund;}
public void setErledigungsgrund(Vector vector) {erledigungsgrund = vector;}

/**
 * @return
 */
public Vector getSuchbegriffe() {return suchbegriffe;}
public void setSuchbegriffe(Vector vector) {suchbegriffe = vector;}

/**
 * @return
 */
public Vector getEinrichtungen() {return einrichtungen;}
public void setEinrichtungen(Vector vector) {einrichtungen = vector;}
public void refreshEinrichtungen() {einrichtungen = sqlManager.getEinrichtungen();}
/**
 * @return
 */
public Vector getHilfstabellen() {return hilfstabellen;}
public void setHilfstabellen(Vector vector) {hilfstabellen = vector;}

/**
 * @return
 */
public Vector getSchnellsucheWiderspruch() {return schnellsucheWiderspruch;}
public void setSchnellsucheWiderspruch(Vector vector) {schnellsucheWiderspruch = vector;}

/**
 * @return
 */
public RowSetDynaClass getEinrichtung() {return einrichtung;}

/**
 * @param class1
 */
public void setEinrichtung(RowSetDynaClass class1) {einrichtung = class1;}

/**
 * @return
 */
public Vector getTeams() {
	return teams;
}

/**
 * @param vector
 */
public void setTeams(Vector vector) {
	teams = vector;
}

/**
 * @return
 */
public Iterator getEreignis() {
	return ereignis.iterator();
}

public void setEreignis(Vector vector) {ereignis = vector;}
public Vector getBeteiligte() {return beteiligte;}
public void setBeteiligte(Vector vector) {beteiligte = vector;}

public Vector getRechtsgebiet() {return rechtsgebiet;}
public void setRechtsgebiet(Vector vector) {rechtsgebiet = vector;}

/**
 * @return
 */
public String getButton() {return button;}
public void setButton(String string) {button = string;}

}
