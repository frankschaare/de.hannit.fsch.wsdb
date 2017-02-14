/*
 * $Header: /home/cvs/jakarta-tomcat-4.0/webapps/examples/WEB-INF/classes/listeners/SessionListener.java,v 1.2 2001/03/17 00:28:11 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2001/03/17 00:28:11 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */


package listeners;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.jenkov.tags.tree.impl.Tree;
import com.jenkov.tags.tree.impl.TreeNode;
import com.jenkov.tags.tree.itf.ICollapseListener;
import com.jenkov.tags.tree.itf.IExpandListener;
import com.jenkov.tags.tree.itf.ISelectListener;
import com.jenkov.tags.tree.itf.ITree;
import com.jenkov.tags.tree.itf.ITreeNode;
import com.jenkov.tags.tree.itf.IUnselectListener;

import de.hannit.fsch.wsdb.FormularBean;

/**
 * Example listener for context-related application events, which were
 * introduced in the 2.3 version of the Servlet API.  This listener
 * merely documents the occurrence of such events in the application log
 * associated with our servlet context.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2001/03/17 00:28:11 $
 */

public final class SessionListener
    implements ServletContextListener,
	       HttpSessionAttributeListener, HttpSessionListener 
	       
{
	/**
	 * Der Logger für diese Klasse
	 */
	//private Logger log = Logger.getLogger(this.getClass());
	
//private SQLManager sqlManager = new SQLManager();

	public SessionListener()
	{
		//this.initLogger();
	}
	
    /**
     * The servlet context with which we are associated.
     */
    private ServletContext context = null;


    // --------------------------------------------------------- Public Methods


    /**
     * Record the fact that a servlet context attribute was added.
     *
     * @param event The session attribute event
     */
    public void attributeAdded(HttpSessionBindingEvent event) {

	log("attributeAdded('" + event.getSession().getId() + "', '" +
	    event.getName() + "', '" + event.getValue() + "')");

    }


    /**
     * Record the fact that a servlet context attribute was removed.
     *
     * @param event The session attribute event
     */
    public void attributeRemoved(HttpSessionBindingEvent event) {

	log("attributeRemoved('" + event.getSession().getId() + "', '" +
	    event.getName() + "', '" + event.getValue() + "')");

    }


    /**
     * Record the fact that a servlet context attribute was replaced.
     *
     * @param event The session attribute event
     */
    public void attributeReplaced(HttpSessionBindingEvent event) {

	log("attributeReplaced('" + event.getSession().getId() + "', '" +
	    event.getName() + "', '" + event.getValue() + "')");

    }


    /**
     * Record the fact that this web application has been destroyed.
     *
     * @param event The servlet context event
     */
    public void contextDestroyed(ServletContextEvent event) {

	log("contextDestroyed()");
	this.context = null;

    }


    /**
     * Record the fact that this web application has been initialized.
     *
     * @param event The servlet context event
     */
    public void contextInitialized(ServletContextEvent event) {

	this.context = event.getServletContext();
	

    }


    /**
     * Bereitet die Session für die Applikation vor:
     * <ul>
     * <li> Es werden Hilfstabellen mit Formulardaten eingelesen</li>  
     * </ul>
     * @param event The session event
     */
    public void sessionCreated(HttpSessionEvent event) {
	
	HttpSession session = event.getSession();
	this.buildTree(session);
	
	// Java Bean mit Hilfswerten für alle Formulare
	FormularBean fb = new FormularBean();
	// Formularbean wird mit default Werten bestückt
	fb.populate();
	session.setAttribute("fb",fb);
    }

	public void buildTree(HttpSession session)
	{
		
		if(session.getAttribute("example") == null)
		{
		ITree tree = new Tree();
		
				tree.addExpandListener(new IExpandListener(){
				   public void nodeExpanded(ITreeNode node, ITree tree){
					   System.out.println("node " + node.getName() + " was expanded");
				   }
				});

				tree.addCollapseListener(new ICollapseListener(){
				   public void nodeCollapsed(ITreeNode node, ITree tree){
					   System.out.println("node " + node.getName() + " was collapsed");
				   }
				});

				tree.addSelectListener(new ISelectListener(){
					public void nodeSelected(ITreeNode node, ITree tree){
						System.out.println("node " + node.getName() + " was selected");
					}
				});

				tree.addUnselectListener(new IUnselectListener(){
					public void nodeUnselected(ITreeNode node, ITree tree){
						System.out.println("node " + node.getName() + " was unselected");
					}
				});


				ITreeNode   home        	= new TreeNode("home.do", "WSDB", "home");
				ITreeNode   widerspruch   	= new TreeNode("HilfeWiderspruch.do", "Vorgang"  , "widerspruch");
				ITreeNode   wsListe   		= new TreeNode("listWidersprueche.do", "Widersprüche"  , "listeWiderspruch");				
				ITreeNode   wsKlagen   		= new TreeNode("listKlagen.do", "Klagen"  , "listeKlagen");				
				ITreeNode   wsErfassen   	= new TreeNode("ErfassungVorgang.do", "erfassen"  , "erfassungWiderspruch");				
				ITreeNode   wsBearbeiten	= new TreeNode("listBearbeitungWiderspruch.do", "bearbeiten"  , "bearbeitenWiderspruch");				

				ITreeNode   benutzer     	= new TreeNode("HilfeBenutzer.do", "Benutzer", "benutzer");
				ITreeNode   userListe   	= new TreeNode("listeBenutzer.do", "Rechte"  , "listeBenutzer");				
				ITreeNode   userErfassen   	= new TreeNode("erfassungBenutzer.do", "erfassen"  , "widerspruchErfassen");				
				ITreeNode   userBearbeiten	= new TreeNode("listBenutzer.do", "bearbeiten"  , "listeBenutzer");				

				ITreeNode   verwaltung   	= new TreeNode("HilfeVerwaltung.do", "Verwaltung"  , "verwaltung");
				ITreeNode   einrichtung		= new TreeNode("listEinrichtung.do", "Einrichtungen"  , "listeEinrichtung");				
				ITreeNode   themen		   	= new TreeNode("listThema.do", "Themen"  , "listThema");				
				ITreeNode   ereignisse	   	= new TreeNode("ListeEreignis.do", "Ereignisse"  , "ereignisse");				

				home.addChild(widerspruch);
				home.addChild(benutzer);
				home.addChild(verwaltung);

				widerspruch.addChild(wsListe);
				widerspruch.addChild(wsKlagen);				
				widerspruch.addChild(wsErfassen);
				widerspruch.addChild(wsBearbeiten);	
				
				benutzer.addChild(userListe);
				benutzer.addChild(userErfassen);
				benutzer.addChild(userBearbeiten);
				
				verwaltung.addChild(einrichtung);
				verwaltung.addChild(themen);	
				verwaltung.addChild(ereignisse);
								
				tree.setRoot(home);
				tree.setSingleSelectionMode(true);
			
				tree.expand(home.getId());				
				tree.expand(widerspruch.getId());
				tree.select(widerspruch.getId());
				

				session.setAttribute("example", tree);
			}
	} 

    /**
     * Record the fact that a session has been destroyed.
     *
     * @param event The session event
     */
    public void sessionDestroyed(HttpSessionEvent event) {


    }

    /**
     * Log a message to the servlet context application log.
     *
     * @param message Message to be logged
     */
    private void log(String message) {

	if (context != null)
	    context.log("SessionListener: " + message);
	else
	    System.out.println("SessionListener: " + message);

    }


    /**
     * Log a message and associated exception to the servlet context
     * application log.
     *
     * @param message Message to be logged
     * @param throwable Exception to be logged
     */
    /*private void log(String message, Throwable throwable) {

	if (context != null)
	    context.log("SessionListener: " + message, throwable);
	else {
	    System.out.println("SessionListener: " + message);
	    throwable.printStackTrace(System.out);
	}

    }*/
	
	/*private void initLogger()
	{	
		String logConfig = context.getInitParameter("Log4JConfig");
		PropertyConfigurator.configure(logConfig);
		log.debug("Configfile loaded");
	}*/

}
