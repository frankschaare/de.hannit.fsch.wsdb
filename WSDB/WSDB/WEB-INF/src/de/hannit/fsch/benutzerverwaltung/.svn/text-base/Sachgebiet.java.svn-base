/*
 * Created on 25.08.2003
 */
package de.hannit.fsch.benutzerverwaltung;

import java.util.*;

import org.apache.struts.validator.ValidatorForm;

/**
 * <h3><strong>Beschreibt die Zuständigkeit eines Sachbearbeiters</strong></h3>
 * 
 * @version 1.0 vom 25.08.2003 10:35:38
 * @author Frank.Schaare@HannIT.de
 *
 * <p>Bei der Erarbeitung des Objektmodells für die Widerspruchsdatenbank
 * wurde versucht, ein allgemein gültiges, wiederverwendbares Modell 
 * für die Zuständigkeit zu finden.</p><br>
 * Folgende Überlegungen waren von Bedeutung:
 * <ul>
 * <li>Ein Sachgebiet definiert sich aus den <strong>eigenen Aufgaben</strong>
 * + den <strong>Vertretungsaufgaben</strong> des Sachbearbeiters</li>
 * <li>Die Summe der <strong>eigenen Aufgaben</strong> eines Sachbearbeiters
 * ist gleich der Stellenbeschreibung</li>
 * </ul>
 * <p>Versucht man aber, schlicht eine Vertretungstabelle mit verschiedenen
 * Sachbearbeiter-IDs zu pflegen, gelangt man sehr schnell an die Grenzen
 * des Modells. Selten vertritt ein Sachbearbeiter einen anderen und umgekehrt. 
 * Vielmehr wird oft nur ein Teil der Aufgaben vertreten.<br>
 * Die Zuständigkeit muss daher mit der <strong>Aufgabe</strong>
 * verknüpft sein, dann ist es relativ unproblematisch, zu der jeweiligen
 * AufgabenID einen oder mehrere Sachbearbeiter und einen oder mehrere
 * Vertreter zuzuordnen.</p>
 * <p>Möchte man in einer Anwendung also wissen, ob ein Sachbearbeiter
 * eine bestimmte Sache tun darf, muss festgelegt sein, um welche Aufgabe
 * es sich dabei handelt. Dies geschieht mit einer eindeutigen AufgabenID.<br>
 * Als nächstes wird geprüft, ob die Aufgabe eine <strong>eigene Aufgabe</strong>
 * des Sachbearbeiters ist. Ist dies nicht der Fall, wird geprüft, ob es s ich um 
 * eine <strong>Vertretungsaufgabe</strong> handelt. Ist dies ebenfalls nicht der Fall,
 * ist der Sachbarbeiter nicht zuständig und damit auch nicht berechtigt,
 * die Aufgabe durchzuführen.
 * </p>
 */
public class Sachgebiet extends ValidatorForm
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected ArrayList aufgaben = new ArrayList();
protected String[] zuloeschen = {};
private String[] aufgabenArray;
private HashMap hmVertretungen = new HashMap();
private String aufgabenListe = "&nbsp;";
private String insertRolle = null;	

	/**
	 * Fügt dem Aufgabenbereich des Sachbearbeiters eine neue Aufgabe hinzu.
	 * 
	 * @param AufgabenID: Die DatenbankID der Aufgabe
	 * @param Beschreibung: Die Beschreibung der Aufgabe
	 *
	 */
	protected void setAufgabe(String aufgabe)
	{
	aufgaben.add(aufgabe);		
	}

	/**
	 * Fügt dem Vertretungsbereich des Sachbearbeiters eine neue Vertretung hinzu.
	 * 
	 * @param VertretungsID: Die DatenbankID der Vertretung
	 * @param Beschreibung: Die Beschreibung der Vertretung
	 *
	 */
	protected void setVertretung(String VertretungsID,String Beschreibung)
	{
	hmVertretungen.put(VertretungsID,Beschreibung);		
	}	
/**
 * @return
 */
public ArrayList getAufgaben() {
	return aufgaben;
}

/**
 * @return HTML Liste mit den Aufgaben
 */
public String getAufgabenListe() 
{
	aufgabenListe = "<ul>";
	
	if (this.aufgaben.size() > -1) 
	{
	for (int i = 0; i < aufgaben.size(); i++) 
	{
		aufgabenListe += "<li>"+(String)aufgaben.get(i)+"</li>";
	}
	aufgabenListe += "</ul>";
	return aufgabenListe;	
	} 
	else 
	{
	return "&nbsp;";
	}
	
}

/**
 * Dummerweise benötigen die Struts Elemente 'html:checkbox' ein Array zum iterieren.
 * Dies wird beim setzen der Aufgabenliste automatisch erstellt.
 * 
 * @param ArrayList
 */
public void setAufgaben(ArrayList list) {
	this.aufgaben = list;
	int anzahl = list.size();
	this.aufgabenArray = new String[anzahl];
		for (int i = 0; i < anzahl; i++) 
		{
		aufgabenArray[i] = (String) list.get(i);	
		}

}

/**
 * @return
 */
public String[] getZuloeschen() {
	return zuloeschen;
}

/**
 * @param strings
 */
public void setZuloeschen(String[] zuloeschen) {
	this.zuloeschen = zuloeschen;
}

/**
 * @return
 */
public String getInsertRolle() {
	return insertRolle;
}

/**
 * @param string
 */
public void setInsertRolle(String string) {
	insertRolle = string;
}

}
