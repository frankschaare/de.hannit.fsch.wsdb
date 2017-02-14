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
    <td colspan="3"><div align="center"><strong>Leider ist ein Fehler aufgetreten ! </strong></div></td>
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
      <p>In Ihrer Benutzersitzung wurde kein g&uuml;ltiger Vorgang (Widerspruch, Klage etc.) gefunden auf den Bezug genommen werden kann. </p>
      <p>Daf&uuml;r kann es folgende Ursachen geben: </p>
      <ul>
        <li><strong>Ihre Sitzung wurde beendet</strong>: Wenn Sie l&auml;ngere Zeit nicht mehr in der Widerspruchsdatenbank gearbeitet haben, wird Ihre Benutzersitzung beendet. Damit erlischt auch der aktuell von Ihnen ausgew&auml;hlte Vorgang.<br>
          Bitte versuchen Sie die Vorgangsbearbeitung abzuschliessen, wenn Sie einen Vorgang zur bearbeitung ausgew&auml;hlt haben. </li>
        </ul>
    </div></td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="center"></div></td>
  </tr>
</table>

</body>

</html>
