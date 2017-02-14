<%-- Author: Frank.Schaare@HannIT.de, 19.11.2002 --%>

<%@ page 	language="java" 
			session="true"
			import="java.util.*"
			info="Login Seite zur Benutzerauthentifizierung"
			errorPage="LoginError.jsp"
			isErrorPage="false"
			contentType="text/html; charset=ISO-8859-15"
%>

<html>
<head>

<title>Hilfeseite Einrichtung</title>
<script type="text/javascript">
<!--
function init() 
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}
}
// -->
</script>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/Standard.css">


</head>

<body onLoad="init()">

  
<table width="90%" border="0">
  <tr> 
    <td>&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="3"><div align="center"><strong>Benutzerverwaltung der Widerspruchsdatenbank </strong></div></td>
  </tr>
  <tr> 
    <td width="50%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td width="50%"><div align="right"></div></td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="left">
      <p>Hier k&ouml;nnen Sie die Benutzer der Applikation pflegen. </p>
      <p>Sofern das Verwaltungsmen&uuml; noch geschlossen ist, erscheint ein kleines Plus-Symbol davor. Sie k&ouml;nnen auf das Plus-Symbol klicken, um das Men&uuml; Benutzer zu erweitern und zu den einzelnen Unterpunkten zu gelangen. </p>
      <p>Der Zugriff auf diesen Bereich ist gesch&uuml;tzt und kann nicht von allen Benutzer/innen aufgerufen werden.</p>
      <p>Die Benutzerverwaltung ist besonders wichtig, da Sie hier u.a. auch die Zugriffsrechte f&uuml;r die Benutzer/innen festlegen. </p>
      <p>Im Einzelnen k&ouml;nnen Sie folgende Dinge tun:</p>
      <ul>
        <li><strong>&Uuml;bersicht</strong>: Hier k&ouml;nnen Sie sich leicht einen &Uuml;berblick &uuml;ber alle erfassten Benutzer/innen verschaffen. Die wichtigsten Tabellenspalten k&ouml;nnen Sie auf-/absteigend sortieren.</li>
        <li><strong>erfassen</strong>: Hier legen Sie Benutzer/innen an, die noch nicht f&uuml;r die Widerspruchsdatenbank eingerichtet sind.</li>
        <li><strong>bearbeiten</strong>: Hier k&ouml;nnen Sie bereits erfasste Benutzer/innen &auml;ndern oder l&ouml;schen.  </li>
      </ul>
    </div></td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="center"></div></td>
  </tr>
</table>

</body>

</html>
