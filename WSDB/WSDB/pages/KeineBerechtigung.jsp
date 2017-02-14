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
      <p>Sie haben nicht die Berechtigung, die angeforderte Aktion auszuf&uuml;hren  . </p>
      <p>Daf&uuml;r kann es folgende Ursachen geben: </p>
      <ul>
        <li><strong>Sie sind kein Mitglied der vorgesehenen Benutzergruppe</strong>: Sie d&uuml;rfen nur Aktionen ausf&uuml;hren, die f&uuml;r Ihre Benutzergruppe vorgesehen ist. Benutzer eines Teams d&uuml;rfen z.b. meist nur F&auml;lle Ihres Teams bearbeiten. </li>
        <li><strong>Sie ben&ouml;tigen Administratorenrechte </strong>: Viele Verwaltungsaktionen, z.b. Benutzer l&ouml;schen, erfordern Administratorenrechte. Wenn Sie kein Administrator sind, d&uuml;rfen Sie diese Aktionen nicht ausf&uuml;hren. </li>
      </ul>
    </div></td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="center"></div></td>
  </tr>
</table>

</body>

</html>
