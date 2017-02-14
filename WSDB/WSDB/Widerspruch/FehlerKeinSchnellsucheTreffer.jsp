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
      <p>Es wurde kein Treffer zu Ihrem Suchbegriff gefunden. </p>
      <p>Daf&uuml;r kann es folgende Ursachen geben: </p>
      <ul>
        <li><strong>Es exisistiert kein Treffer </strong>: Es ist durchaus m&ouml;glich, das der gesuchte Datensatz nicht in der Datenbank gespeichert ist. Legen Sie den gew&uuml;nschten Datensatz einfach neu an.</li>
        <li><strong>Die Spalte passt nicht zum Suchbegriff </strong>: &Uuml;berpr&uuml;fen Sie bitte, ob der eingegebene Suchbegriff zu der ausgew&auml;hlten Spalte passt. Sucht man beispielsweise in der Spalte 'Aktenzeichen' nach einem Nachnamen, wird ebenfalls kein Treffer gefunden.</li>
      </ul>
    </div></td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="center"></div></td>
  </tr>
</table>

</body>

</html>
