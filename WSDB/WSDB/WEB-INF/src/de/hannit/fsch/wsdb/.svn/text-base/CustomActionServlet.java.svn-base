/*
 * Created on 25.02.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.wsdb;

import javax.servlet.ServletException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;

/**
 * @author fsch
 *
 * Das Struts ActionServlet wird überschrieben, um Log4J zu initialisieren
 * 
 */
public class CustomActionServlet extends ActionServlet {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static Logger logger = Logger.getLogger(CustomActionServlet.class);

	public void init() throws ServletException 
	{
	super.init();
	
	BasicConfigurator.configure();
	logger.info("Log4J wurde initialisiert...");
	}		


}
