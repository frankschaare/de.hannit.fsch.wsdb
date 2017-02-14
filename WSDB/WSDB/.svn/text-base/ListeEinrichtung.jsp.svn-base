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
/*
* Überträgt die Werte der Einrichtungsliste in die WiderspruchErfassen.jsp
*/
function setValues(i,a) {
opener.document.getElementById("einrichtung.id").value=i;
opener.document.getElementById("einrichtung.name").value=a;
opener.status='Die gewählte Einrichtung '+a+' wurde in das Feld Einrichtung eingetragen.';
this.close();
}
// -->
</script>
</head>

<body onLoad="init()">
<h4>&nbsp;</h4>
<h4 align="center">Übersicht über alle erfassten Einrichtungen </h4>
<div align="center">
<a href="<%=request.getContextPath()%>/Verwaltung/CREATEEinrichtung.jsp?crud=1&loaded=false" target="_self">Neue Einrichtung erfassen</a>

<display:table name="sessionScope.fb.einrichtung.rows" decorator="org.displaytag.sample.Wrapper" >
  <display:column property="name" title="Name" sortable="true" headerClass="sortable"/>
  <display:column property="strasse" title="Strasse" sortable="true" headerClass="sortable"/>
  <display:column property="hausnummer" title="Nr." />
  <display:column property="postleitzahl" title="PLZ" sortable="true" headerClass="sortable"/>
  <display:column property="ort" title="Ort" sortable="true" headerClass="sortable"/>
  <display:column property="script" title="Aktion" />
</display:table>

</div>
</body>