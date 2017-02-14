package org.displaytag.sample;


import java.text.DecimalFormat;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.TableDecorator;

/**
 * This class is a decorator of the TestObjects that we keep in our List. This class provides a number of methods for
 * formatting data, creating dynamic links, and exercising some aspects of the display:table API functionality
 * @author epesh
 * @version $Revision$ ($Author$)
 */
public class Wrapper extends TableDecorator
{
	/**
	 * Action Bar für die ListeThema.jsp
	 * 
	 * @return String
	 */
	public String getThemaBar()
	{
		BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
		Integer id = (Integer)(bdb.get("id"));
		//HttpServletRequest req = (HttpServletRequest)this.getPageContext().getRequest();
						
		return "<a href=\"CRUDThema.do?crud=2&loaded=false&id="
			+ id
			+ "\">bearbeiten</a> | "
			+ "<a href=\"CRUDThema.do?crud=3&loaded=false&id="
			+ id
			+ "\">löschen</a>";
	}

	/**
	 * Action Bar für die ListeEinrichtung.jsp
	 * 
	 * @return String
	 */
	public String getEinrichtungBar()
	{
		BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
		Integer id = (Integer)(bdb.get("id"));
		//HttpServletRequest req = (HttpServletRequest)this.getPageContext().getRequest();
						
		return "<a href=\"CRUDEinrichtung.do?crud=2&loaded=false&id="
			+ id
			+ "\">bearbeiten</a> | "
			+ "<a href=\"CRUDEinrichtung.do?crud=3&loaded=false&id="
			+ id
			+ "\">löschen</a>";			
	}
    /**
     * FastDateFormat used to format dates in getDate()
     */
    private FastDateFormat dateFormat = null;

    /**
     * DecimalFormat used to format money in getMoney()
     */
    private DecimalFormat moneyFormat = null;

    /**
     * Creates a new Wrapper decorator who's job is to reformat some of the data located in our TestObject's.
     */
    public Wrapper()
    {
        super();

        // Formats for displaying dates and money.

        this.dateFormat = FastDateFormat.getInstance("MM/dd/yy");
        this.moneyFormat = new DecimalFormat("$ #,###,###.00");
    }

    /**
     * Test method which always returns a null value
     * @return <code>null</code>
     */
    public String getNullValue()
    {
        return null;
    }

    /**
     * Returns the date as a String in MM/dd/yy format
     * @return formatted date
     */
    public String getDate()
    {
        return this.dateFormat.format(((ListObject) this.getCurrentRowObject()).getDate());
    }

    /**
     * Returns the money as a String in $ #,###,###.00 format
     * @return String
     */
    public String getMoney()
    {
        return this.moneyFormat.format(((ListObject) this.getCurrentRowObject()).getMoney());
    }

    /**
     * Returns the TestObject's ID as a hyperlink that the person can click on and "drill down" for more details.
     * @return String
     */
    public String getLink1()
    {
        BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
        String id = (String) bdb.get("id");

        return "<a href=\"details.jsp?id=" + id + "\">" + id + "</a>";
    }
	
	/**
	 * Action Bar für die ListeEreignis.jsp
	 * 
	 * @return String
	 */
	public String getEreignisBar()
	{
		BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
		Integer id = (Integer)(bdb.get("id"));
		//HttpServletRequest req = (HttpServletRequest)this.getPageContext().getRequest();
						
		return "<a href=\"BearbeitenEreignis.do?id="
			+ id
			+ "\">bearbeiten</a> | "
			+ "<a href=\"LoeschenEreignisConfirm.do?id="
			+ id
			+ "\">löschen</a>";
	}

	/**
	 * Action Bar für die ListeBearbeitungWiderspruch.jsp
	 * 
	 * @return String
	 */
	public String getWiderspruchsBar()
	{
		BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
		Integer id = (Integer)(bdb.get("id"));
		//HttpServletRequest req = (HttpServletRequest)this.getPageContext().getRequest();
						
		return "<a class=\"gruen\" href=\"WiderspruchDetail.do?id="
			+ id
			+ "\">anzeigen</a> | "
			+ "<a class=\"gelb\" href=\"BearbeitungVorgang.do?id="
			+ id
			+ "\">bearbeiten</a> | "
			+ "<a class=\"rot\" href=\"LoeschenWiderspruchConfirm.do?id="
			+ id
			+ "\">löschen</a>";
	}

	/**
	 * Action Bar für die ListeBearbeitungWiderspruch.jsp
	 * 
	 * @return String
	 */
	public String getBenutzerBar()
	{
		BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
		Integer id = (Integer)(bdb.get("userid"));
		//HttpServletRequest req = (HttpServletRequest)this.getPageContext().getRequest();
						
		return "<a class=\"gruen\" href=\"showUserDetails.do?id="
			+ id
			+ "\">Rechte</a> | "
			+ "<a class=\"gelb\" href=\"showUserDetails.do?id="
			+ id
			+ "&aktion=UPDATE"
			+ "\">bearbeiten</a> | "
			+ "<a class=\"rot\" href=\"LoeschenBenutzerConfirm.do?id="
			+ id
			+ "\">löschen</a>";
	}
    /**
     * Returns an "action bar" of sorts that allow the user to perform various actions on the TestObject based on it's
     * id.
     * @return String
     */
    public String getAktionen()
    {
		BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
		Integer id = (Integer)(bdb.get("id"));

        return "<a href=\"details.jsp?id="
            + id
            + "&action=view\">Details</a> | "
            + "<a href=\"details.jsp?id="
            + id
            + "&action=edit\">bearbeiten</a> | "
            + "<a href=\"details.jsp?id="
            + id
            + "&action=delete\">löschen</a>";
    }
	/**
	 * Returns an "action bar" of sorts that allow the user to perform various actions on the TestObject based on it's
	 * id.
	 * @return String
	 */
	public String getScript()
	{
		BasicDynaBean bdb = (BasicDynaBean) getCurrentRowObject();
		Integer id = (Integer)(bdb.get("id"));
		String name = (String)(bdb.get("name"));
		name=name.trim();
		//String ankerStart ="<a href='javascript:setValues('";
		//String ankerEnde = "')'>einfügen</a>";

		return "<a href=\"javascript:setValues('"+id+"','"+name+"')\">einfügen</a> | "
			+"<a href=\"CRUDEinrichtung.do?crud=2&loaded=false&id="
			+ id
			+ "\">bearbeiten</a> | "
			+ "<a href=\"CRUDEinrichtung.do?crud=3&loaded=false&id="
			+ id
			+ "\">löschen</a>";	
	}    
}
