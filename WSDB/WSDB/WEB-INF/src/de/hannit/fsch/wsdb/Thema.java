/*
 * Created on 05.04.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.wsdb;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Thema extends ValidatorForm {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id = 0;
private String thema = null;
private String team = null;
	
	public Thema()
	{
	
	}

	/**
	 * @return
	 */
	public String getThema() 
	{
	if (this.thema != null) {return thema.trim();} 
	else {return this.thema;}
	}
	public void setThema(String string) {thema = string.trim();}

/**
 * @return
 */
public int getId() {return id;}
	public String getTeam() 
	{	
	if (this.team != null) {return team.trim();} 
	else {return this.thema;}
	}

public void setId(int i) {id = i;}
public void setTeam(String string) {team = string.trim();}

}
