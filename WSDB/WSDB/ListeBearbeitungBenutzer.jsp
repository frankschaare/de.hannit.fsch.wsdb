<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display" %>
<%@ page import="org.displaytag.sample.*, java.util.*,
                 org.displaytag.tags.TableTag"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/screen.css" type="text/css" media="screen, print" />
<SCRIPT LANGUAGE="javascript">
<!--

function init()
{
var fehler = null;
<logic:messagesPresent>
	<html:errors />
</logic:messagesPresent>

var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

if (fehler != null){alert(fehler);}
}


-->
</SCRIPT>

</head>
<body onLoad="init()">
<h4>&nbsp;</h4>
<h4 align="center">&Uuml;bersicht &uuml;ber alle erfassten Benutzer/innen</h4>
<div align="center">
<display:table name="sessionScope.Ergebnis.rows" decorator="org.displaytag.sample.Wrapper">
  <display:column property="userid" title="ID" sortable="true" headerClass="sortable" />
  <display:column property="user_name" title="Benutzer" sortable="true" headerClass="sortable" />
  <display:column property="anrede" title="Anrede" />
  <display:column property="vorname" title="Vorname" />
  <display:column property="nachname" title="Nachname" sortable="true" headerClass="sortable" />
  <display:column property="organisationseinheitid" title="OE" width="40"/>
  <display:column property="raum" title="Raum" sortable="true" headerClass="sortable" />
  <display:column property="durchwahl" title="Durchwahl" sortable="true" headerClass="sortable" />
  <display:column property="benutzerBar" title="Aktion" width="170"/>
</display:table>

</div>
</body>