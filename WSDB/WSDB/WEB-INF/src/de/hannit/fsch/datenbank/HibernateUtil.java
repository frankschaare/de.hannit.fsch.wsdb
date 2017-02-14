/*
 * Created on 24.02.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.hannit.fsch.datenbank;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.apache.log4j.Logger;

/**
 * @author fsch
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HibernateUtil 
{
private static Logger logger = Logger.getLogger(HibernateUtil.class);
private static final SessionFactory sessionFactory;

	static 
	{
		try 
		{
		// Create the SessionFactory
		sessionFactory = new Configuration().configure().buildSessionFactory();
		} 
		catch (Throwable ex) 
		{
		logger.error("Initial SessionFactory creation failed.", ex);
		throw new ExceptionInInitializerError(ex);
		}
	}
	public static final ThreadLocal session = new ThreadLocal();

	public static Session currentSession() throws HibernateException 
	{
	Session s = (Session) session.get();
	// Open a new Session, if this Thread has none yet
	if (s == null) {
	s = sessionFactory.openSession();
	session.set(s);
	}
	return s;
	}

	public static void closeSession() throws HibernateException {
	Session s = (Session) session.get();
	session.set(null);
	if (s != null)
	s.close();
	}
}