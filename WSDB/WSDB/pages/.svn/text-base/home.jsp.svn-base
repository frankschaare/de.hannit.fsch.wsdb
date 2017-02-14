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

<META NAME="Title" CONTENT="Hannoversche Informationstechnologien - HannIT">
<META NAME="Author" CONTENT="Frank.Schaare@HannIT.de">
<META NAME="Publisher" CONTENT="HannIT">
<META NAME="Copyright" CONTENT="&copy;HannIT 2002">
<META NAME="Revisit" CONTENT="After 30 days">
<META NAME="Keywords" CONTENT="Sozialhilfe, Wohngeld, KFZ, Programmierung, Content-Managment">
<META NAME="Description" CONTENT="Der Internetauftritt der HannIT">
<META NAME="page-topic" CONTENT="Kommunale Datnverarbeitung">
<META NAME="audience" CONTENT="Alle">
<META NAME="Robots" CONTENT="INDEX,NOFOLLOW">
<META NAME="Language" CONTENT="Deutsch">

<title></title>
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


<style type="text/css">
<!--
.style1 {color: #999999}
.style2 {color: #FF0000}
-->
</style>
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
    <td colspan="3"><div align="center"><strong>Willkommen bei der Widerspruchsdatenbank</strong></div></td>
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
      <p>05.06.2006: <strong><span class="style2">Neue Version der Widerspruchsdatenbank </span>:</strong></p>
      <p>Die Widerspruchsdatenbank wurde zwischenzeitlich so angepasst, dass in den meisten Feldern Eingaben mit bestimmten Sonderzeichen nicht mehr m&ouml;glich sind. Zu diesen Sonderzeichen geh&ouml;ren einfache (') und doppelte (&quot;) Anf&uuml;hrungsstriche, runde (()) und geschweifte ({}) Klammern, sowie das Semikolon (;).</p>
      <p>Diese &Auml;nderung war notwendig, da durch Eingabe dieser Zeichen Datenbankfehler entstehen konnten. </p>
    </div></td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="center"></div></td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="left">
      <p>12.04.2005: <strong>Ein Bild sagt mehr als tausend Worte.</strong></p>
      <p>Nachdem erneut einen Menge Fehler bereinigt wurden, steht die Anwendung kurz vor der Produktionsreife. Wenn Ihr Browser mit dem Flash-PlugIn der Firma Macromedia ausgestattet ist, haben Sie die M&ouml;glichkeit, sich ein kleines <a href="<%= request.getContextPath() %>/pages/Fall_erfassen_viewlet_swf.html" target="_blank">Video</a> anzusehen, dass die Fallerfassung genau beschreibt. Falls Ihr Browser nicht mit einem Flash-Player ausgestattet ist, k&ouml;nnen Sie <a href="Fall_erfassen.exe" target="_self">diese ausführbare Datei</a> herunterladen und es damit probieren. Leider ist die Qualit&auml;t etwas schlechter. Klicken Sie im folgendem Dialog einfach auf '&ouml;ffnen' und das Video sollte laufen. F&uuml;hren Sie das Fenster mit dem Video in jedem Fall im Vollbild aus, damit Sie alles sehen k&ouml;nnen. </p>
      <p>Falls keine technischen H&uuml;rden auftauchen, werden wir in den n&auml;chsten Tagen noch weitere Videos hinzuf&uuml;gen. Den Anfang macht die Er&auml;uterung, wie man eine Vollbildverkn&uuml;pfung f&uuml;r das Programm anlegt:</p>
      <ul>
        <li>Fallerfassung allgemein (<a href="<%= request.getContextPath() %>/pages/Fall_erfassen_viewlet_swf.html" target="_blank">Flash-Film</a>, <a href="Fall_erfassen.exe" target="_self">ausf&uuml;hrbare Datei</a>) </li>
        <li>Verkn&uuml;pfung auf dem Desktop anlegen  (<a href="<%= request.getContextPath() %>/viewlets/VerknuepfungErstellen_viewlet_swf.html" target="_blank">Flash-Film</a>, <a href="<%= request.getContextPath() %>/viewlets/VerknuepfungErstellen.exe" target="_self">ausf&uuml;hrbare Datei</a>) </li>
      </ul>
      <p>Nachdem der Test in der 13 KW  einige Fehler hervorgebracht hatte, konnten diese fast alle beseitigt werden.</p>
      <p>Sie konnten keine F&auml;lle abspeichern, da auf der entscheidenden Seite der Button 'speichern' nicht richtig dargestellt wurde. Grund daf&uuml;r war schlicht zu wenig Platz. Aus diesem Grund wird Herr Uloth Ihnen eine Verkn&uuml;pfung erstellen, welche die Anwendung im Vollbildmodus startet. Der Vollbildmodus verdeckt i.d.R Ihre anderen Anwendungen. Sie k&ouml;nnen aber leicht zu Ihren anderen Anwendungen wechseln, indem Sie die 'Alt' - Taste gedr&uuml;ckt halten und dann die 'TAB'-Taste dr&uuml;cken:</p>
      <p align="center"><img height="173" src="<%= request.getContextPath() %>/img/alttab.jpg" width="244"></p>
      <p> Beenden Sie die Anwendung, indem Sie oben rechts auf 'Abmelden' klicken. Das Fenster wird dann (nach R&uuml;ckfrage) geschlossen.</p>
      <p>Folgende Probleme bestehen noch, werden aber am 01.04. und 04.04.2005 korrigiert:<br>
      </p>
      <ul>
        <li class="style1"><span class="style1">die 'Zur&uuml;ck' Schaltfl&auml;chen funktionieren nicht wie gew&uuml;nscht. Der Internet Explorer weigert sich, den Fall beim Klicken auf 'Zur&uuml;ck' anzuzeigen. Die Anwendung wird jedoch in K&uuml;rze auf einem anderen Webserver laufen, bei dem diese Probleme nicht mehr auftreten werden.</span> <span class="style1">(am 12.04.2005 korrigiert)</span></li>
        <li class="style1">Gelegentlich treten noch Scriptfehler auf (am 04.04.2005 korrigiert) </li>
        <li class="style1">Einige F&auml;lle werden in den Trefferlisten doppelt angezeigt. Das bedeutet aber nicht, dass sie auch doppelt erfasst sind, es handelt sich nur um einen Darstellungsfehler. (am 05.04.2005 korrigiert)</li>
        <li class="style1">Sie k&ouml;nnen Ihr Passwort noch nicht &auml;ndern (am 04.04.2005 korrigiert) </li>
      </ul>
      <p>F&uuml;r Fragen steht Ihnen Herr Schaare unter der Durchwahl -22412 gerne
        zur Verf&uuml;gung.</p>
      </div></td>
  </tr>
  <tr> 
    <td colspan="3"> <div align="center"></div></td>
  </tr>
</table>

</body>

</html>
