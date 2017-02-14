<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display" %>
<%@ page import="org.displaytag.sample.*, java.util.*,
                 org.displaytag.tags.TableTag"%>

<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/screen.css" type="text/css" media="screen, print" />
</head>

<SCRIPT LANGUAGE="javascript">
<!--

/*
* Sorgt dafür, dass die Seite beim Laden den Navigationsbaum aktualisiert.
*/
function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}
}
-->
</SCRIPT>

<body onLoad="init()">

<h4>&nbsp;</h4>
<h4 align="center">Übersicht über alle erfassten Benutzer/innen</h4>
<div align="center">
<display:table name="sessionScope.Ergebnis.rows" pagesize="20">
  <display:column property="userid" title="ID" sortable="true" headerClass="sortable" href="showUserDetails.do" paramId="id"/>
  <display:column property="anrede" title="Anrede" href="showUserDetails.do" paramId="id" paramProperty="userid"/>
  <display:column property="vorname" title="Vorname" sortable="true" headerClass="sortable" href="showUserDetails.do" paramId="id" paramProperty="userid"/>
  <display:column property="nachname" title="Nachname" sortable="true" headerClass="sortable" href="showUserDetails.do" paramId="id" paramProperty="userid"/>  
</display:table>

</div>
</body>