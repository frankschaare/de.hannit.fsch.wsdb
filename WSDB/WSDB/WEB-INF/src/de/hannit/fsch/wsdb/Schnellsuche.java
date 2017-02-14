package de.hannit.fsch.wsdb;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import de.hannit.fsch.datenbank.Java2SQLMapper;
import de.hannit.fsch.datenbank.SQLManager;

/**
 * @author fsch
 *
 * Klasse zur Verwaltung der Hilfstabellen in der Datenbank
 * @param vwForm.hilfstabelle - Name der Tabelle, die editiert werden soll
 */
public class Schnellsuche extends Action 
{
private RowSetDynaClass rsdc = null;
private SQLManager sqlManager = new SQLManager();
private String strSQL;

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
		throws IOException, ServletException 
	{
	DynaValidatorForm dForm = (DynaValidatorForm)form;
	HttpSession session = req.getSession();
	//String strSQLCount = null;
	String strForward = null;

	String tabelle = (String)dForm.get("tabelle");
	String spalte = (String)dForm.get("spalte");
	String wert = (String)dForm.get("wert");	
	String suchbegriff = (String)dForm.get("suchbegriff");
	
	// Die FormularBean enthält eine Liste aller möglichen Teams:
	FormularBean fb = (FormularBean) session.getAttribute("fb");
	Vector teams = fb.getTeams();

	// Suchbegriff 'Thema'
	if (spalte.equalsIgnoreCase("gegenstandDesVerfahrensID")) 
	{
		strSQL = "Select count (*) from "+
						tabelle+
						" Where "+
						spalte+" = "+suchbegriff;		
	} 
	//	Suchbegriff 'Aktenzeichen' etc...
	else 
	{
		strSQL = "Select count (*) from "+
						tabelle+
						" Where "+
						spalte+" like '%"+wert+"%'";
	}

	
	switch (Java2SQLMapper.getAnzahlDatensätze(strSQL)) 
	{
	case 0 :
	strForward = "KeinTreffer";
	break;

	case 1 :
	if (spalte.equalsIgnoreCase("gegenstandDesVerfahrensID")) 
	{
	
		if (req.isUserInRole("50")) // Cheffe sieht alles 
		{
			strSQL = "Select * from "+
			tabelle+
			" Where "+
			spalte+" = "+suchbegriff;	
		} 
		else 
		{
			strSQL = "Select * from "+
			tabelle+
			" Where "+
			spalte+" = "+suchbegriff+" "+
			Java2SQLMapper.getFilteredTeams(req,teams);
		}
	} 
	else 
	{
		if (req.isUserInRole("50")) // Cheffe sieht alles 
		{
			strSQL = "Select * from "+
			tabelle+
			" Where "+
			spalte+" like '%"+wert+"%'";
		} 
		else 
		{
		strSQL = "Select * from "+
						tabelle+
						" Where "+
						spalte+" like '%"+wert+"%' "+
						Java2SQLMapper.getFilteredTeams(req,teams);
		}
	}
				
	rsdc = sqlManager.getRowSetDynaClass(strSQL);
	session.setAttribute("AnfrageSchnellsuche",strSQL);
	session.setAttribute("Ergebnis",rsdc);
	strForward = "ListeWiderspruch";
	break;

	default :
	if (spalte.equalsIgnoreCase("gegenstandDesVerfahrensID")) 
	{
		if (req.isUserInRole("50")) // Cheffe sieht alles 
		{
			strSQL = "Select * from "+
			tabelle+
			" Where "+
			spalte+" = "+suchbegriff;	
		} 
		else 
		{
			strSQL = "Select * from "+
			tabelle+
			" Where "+
			spalte+" = "+suchbegriff+" "+
			Java2SQLMapper.getFilteredTeams(req,teams);
		}	
	} 
	else 
	{
		if (req.isUserInRole("50")) // Cheffe sieht alles 
		{
			strSQL = "Select * from "+
			tabelle+
			" Where "+
			spalte+" like '%"+wert+"%'";
		} 
		else 
		{
		strSQL = "Select * from "+
						tabelle+
						" Where "+
						spalte+" like '%"+wert+"%' "+
						Java2SQLMapper.getFilteredTeams(req,teams);
		}
	}
				
	rsdc = sqlManager.getRowSetDynaClass(strSQL);
	session.setAttribute("AnfrageSchnellsuche",strSQL);	
	session.setAttribute("Ergebnis",rsdc);
	strForward = "ListeWiderspruch";
	break;
	}
	
	return mapping.findForward(strForward);
	}
}
