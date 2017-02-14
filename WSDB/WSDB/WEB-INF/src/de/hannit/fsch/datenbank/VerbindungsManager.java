package de.hannit.fsch.datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;

/**
 * @author fsch
 *
 * Handelt die notwendigen Datenbankzugriffe
 * 
 */
public class VerbindungsManager extends HttpServlet
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//static Connection con;

public static Connection getConnection() 
{
	  /*try
	  {
	  Context initContext = new InitialContext();
	  Context envContext  = (Context)initContext.lookup("java:/comp/env");
	  DataSource ds = (DataSource)envContext.lookup("jdbc/hWSDB");
	  con = ds.getConnection(); 	
	  Statement st = con.createStatement();	
	  if(st.execute("Select * from users"))
	  {System.out.println("Verbindung ist da");}			 						  
	  }
	  catch (NamingException e)
	  {
	  e.printStackTrace();
	  }
	  catch (SQLException e)
	  {
	  e.printStackTrace();
	  }

  return con;*/
  return getConnectionEmergencyHackByCarstenSchmidt();

}
  public static Connection getJNDIConnection() 
  {
		/*try
		{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/hWSDB");
		con = ds.getConnection(); 	
		Statement st = con.createStatement();	
		if(st.execute("Select * from users"))
		{System.out.println("Verbindung zu hWSDB wurde aufgebaut");}			 						  
		}
		catch (NamingException e)
		{
		e.printStackTrace();
		}
		catch (SQLException e)
		{
		e.printStackTrace();
		}

	return con;*/
	  return getConnectionEmergencyHackByCarstenSchmidt();
  }
  
  private static Connection getConnectionEmergencyHackByCarstenSchmidt()
  {
	final boolean DEBUG=true;
	final boolean LEGACY_DATABASE=false;

	final String DRIVER="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	final String CONNECTIONSTRING_CURRENT="jdbc:sqlserver://10.33.4.72:3759;databasename=WSDB";
	final String CONNECTIONSTRING_LEGACY="jdbc:sqlserver://10.33.4.233:1317;databasename=WSDB";
	final int TIMEOUT=10;
	final String USER="wsdb";
	final String PASSWORD="bdsw";

	Connection con = null;
	Logger log = Logger.getAnonymousLogger();
	String constr;
	if (LEGACY_DATABASE)
	{
		constr = CONNECTIONSTRING_LEGACY;
	}
	else
	{
		constr = CONNECTIONSTRING_CURRENT;
	}

	try
	{
		Class.forName(DRIVER).newInstance();
		DriverManager.setLoginTimeout(TIMEOUT);
		con = DriverManager.getConnection(constr, USER, PASSWORD);
		
		if (DEBUG)
		{
			Statement st = con.createStatement();	
			if(st.execute("Select * from users"))
			{
				log.info("wsdb-connected: "+constr);
			}
			st.close();
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
		log.severe(e.getMessage());
	}
	  
	return con;
  }
  
  /*public static Connection getConnection(String treiber, String url) 
  {
	try
	{
	Class.forName(treiber); 
	con = DriverManager.getConnection(url); 
	}
	catch (ClassNotFoundException e)
	{
	e.printStackTrace();
	}
	catch (SQLException e)
	{
	e.printStackTrace();
	}

	return con;
  }  */
  
  public static void main (String[] args)
  {
	  VerbindungsManager.getConnection();
	  VerbindungsManager.getJNDIConnection();
  }
}
