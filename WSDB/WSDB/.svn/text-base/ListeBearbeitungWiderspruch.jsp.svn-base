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
<h4 align="center">Übersicht über alle erfassten Vorg&auml;nge</h4>
<div align="center">
<display:table name="sessionScope.Ergebnis.rows" decorator="org.displaytag.sample.Wrapper">
  <display:column property="id" title="ID" sortable="true" headerClass="sortable" />
  <display:column property="aktenzeichen" title="Aktenzeichen" sortable="true" headerClass="sortable" />
  <display:column property="nachname" title="Nachname HE" sortable="true" headerClass="sortable" />
  <display:column property="vorname" title="Vorname HE" />
  <display:column property="hegeburtsdatum" title="Geburtsdatum" width="40"/>
  <display:column property="eingangsdatum" title="Eingang:" width="40"/>
  <display:column property="erfasstam" title="erfasst:" width="40"/>
  <display:column property="widerspruchsBar" title="Aktion" />
</display:table>

</div>
</body>