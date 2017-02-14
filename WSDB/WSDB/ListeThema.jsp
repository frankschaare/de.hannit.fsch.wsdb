<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display" %>
<%@ page import="org.displaytag.sample.*, java.util.*,
                 org.displaytag.tags.TableTag"%>


<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<head>

<link rel="stylesheet" href="<%= request.getContextPath() %>/style/screen.css" type="text/css" media="screen, print" />
<title>Liste aller erfassten Einrichtungen. Bitte wählen Sie eine Einrichtung aus:</title>
<script type="text/javascript">
<!--

/*
* Sorgt dafür, dass die Seite beim Laden den Navigationsbaum aktualisiert.
*/
function init()
{
var fehler = null;
<html:errors />

var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

if (fehler != null)
{
alert(fehler);
}

}
// -->
</script>
</head>

<body onLoad="init()">
<h4>&nbsp;</h4>
<h4 align="center">Übersicht über alle erfassten Themen, auch 'Gegenstand des Verfahrens' genannt. </h4>
<div align="center">
<a href="<%=request.getContextPath()%>/Verwaltung/CREATEThema.jsp?crud=1&loaded=false" target="_self">Neues Thema erfassen</a>

<display:table name="sessionScope.Ergebnis.rows" decorator="org.displaytag.sample.Wrapper" >
  <display:column property="id" title="ID" sortable="true" headerClass="sortable"/>
  <display:column property="thema" title="Thema" sortable="true" headerClass="sortable"/>
  <display:column property="team" title="Team" sortable="true" headerClass="sortable"/>
  <display:column property="themaBar" title="Aktion" />
</display:table>

</div>
</body>