<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
<META NAME="Title" CONTENT="Hannoversche Informationstechnologien - HannIT">
<META NAME="Author" CONTENT="Frank.Schaare@HannIT.de">
<META NAME="Publisher" CONTENT="HannIT">
<META NAME="Copyright" CONTENT="HannIT 2002">
<META NAME="Revisit" CONTENT="After 30 days">
<META NAME="Keywords" CONTENT="Sozialhilfe, Wohngeld, KFZ, Programmierung, Content-Managment">
<META NAME="Description" CONTENT="Der Internetauftritt der HannIT">
<META NAME="Abstract" CONTENT="Der Internetauftritt der HannIT">
<META NAME="page-topic" CONTENT="Kommunale Datnverarbeitung">
<META NAME="page-topic" CONTENT="HTML-Formular">
<META NAME="audience" CONTENT="Alle">
<META NAME="Robots" CONTENT="INDEX,NOFOLLOW">
<META NAME="Language" CONTENT="Deutsch">

<title>Hannoversche Informationstechnologien</title>
<style type="text/css">
<!--
a{font-family: arial;font-size: 10px;color: #000000;text-decoration: none;}
a:active{font-family: arial;font-size: 10px;color: #000000; text-decoration: none;}
a:hover{font-family: arial;font-size: 10px;color: #000000;text-decoration: none;}

a.head{font-family: arial;font-size: 11px;font-weight:bold;color: #ffffff;text-decoration: none;}
a.head:active{font-family: arial;font-size: 11px;color: #ffffff; text-decoration: none;}
a.head:hover{font-family: arial;font-size: 11px;color: #ffffff;text-decoration: none;}

br { font-size: 10px; font-family: arial}
td { color: #000000; font-size: 12px; font-family: verdana}
td.Suche{
	font-family: arial;
	font-size: 11px;
	font-weight:bold;
	color: #ffffff;
}
td.Rechts {
	font-size: 12px;
	font-family: verdana;
	background-image: url(../img/logo_region.gif);
	background-repeat: no-repeat;
	background-position: right center;
	background-color: #6496BE;
	text-align: left;
	color: #FFFFFF;
	height: 76px;
}
div.WSDB
{
font-size: 18px;
font-family: verdana;
color: #FFFFFF;
padding-top : 5px;
padding-left : 178px;
Shadow:(color=#808080,direction=135);
}

select {
	font-family: Verdana;
	font-size: 11px;
	border-width:0;
	height:17px;
	backgroundColor='#C5D4E0';
}
select.LevelZwei {
display:none;
}
#selectThemen {
	font-family: Verdana;
	font-size: 11px;
	height:18px;
	visibility:hidden;
	width:250px;
}
input {
	font-family: Verdana;
	font-size: 11px;
	border-width:1px;
	height:18px;
	visibility:hidden;
	display:none;
}
#datum { color: #000000; font-size: 12px; font-family: verdana}
-->
</style>
<script language="javascript"><!--
      var imgLight1=new Image();imgLight1.src='../img/trans.gif';
      var imgLight2=new Image();imgLight2.src='../img/nav_light.gif';

function init()
{
document.getElementById('selectTabelle').focus();
}

function showWert()
{
var wert = document.getElementById("selectSpalte").value;
var themen = document.getElementById("selectThemen");

if(wert.length != 0)
	{
	if (wert != 'gegenstandDesVerfahrensID') // Wert ist ungleich 'Themen' -> Textfeld SuchText einblenden
	{
	document.getElementById("selectThemen").style.display="none";
	document.getElementById("selectThemen").style.visibility="hidden";
	document.getElementById("SuchText").style.visibility="visible";
	document.getElementById("SuchText").style.display="inline";
	document.getElementById("SuchText").focus();
	document.getElementById("SuchButton").style.display="inline";
	document.getElementById("SuchButton").style.visibility="visible";
	//Das Hidden erhält den Text der Textbox 'SuchText'
	document.getElementById("hiddenSuchbegriff").value = document.getElementById("SuchText").value;
	}
	else // Wert ist gleich 'Themen' -> Select Themen einblenden
	{
	document.getElementById("SuchText").style.visibility="hidden";
	document.getElementById("SuchText").style.display="none";
	document.getElementById("selectThemen").style.visibility="visible";
	document.getElementById("selectThemen").style.display="inline";
	document.getElementById("selectThemen").focus();
	document.getElementById("SuchButton").style.display="inline";
	document.getElementById("SuchButton").style.visibility="visible";
	//Das Hidden erhält den Text des Selectes Themen
	document.getElementById("hiddenSuchbegriff").value = themen.options[themen.selectedIndex].value;
	}

	}
else
	{
	document.getElementById("SuchText").style.visibility="hidden";
	document.getElementById("SuchButton").style.visibility="hidden";
	}
}

function setGegenstandDesVerfahrens()
{
var t = document.getElementById("selectThemen");
var h = document.getElementById("hiddenSuchbegriff");

	if (t != null)
	{
	h.value=t.options[t.selectedIndex].value;
	}
}

function showDependant()
{
var sValue = parseInt(document.getElementById("selectTabelle").value);

	switch (sValue) {
		case 0 :
			document.getElementById("selectSpalte").style.display="none";
			break;
		default :
			document.getElementById("selectSpalte").style.display="inline";
			document.getElementById("selectSpalte").focus();
			break;
	}
}
//-->
</script>
</head>
<body onLoad="init()" leftmargin="0" rightmargin="0" topmargin="0" bottommargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
<html:form action="/Schnellsuche" target="main">
<table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td width="100%" bgcolor="#426f9b"><img src="../img/trans.gif" width="10" height="1" border="0"></td></tr></table>
<table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td width="100%" bgcolor="#666666"><img src="../img/trans.gif" width="10" height="1" border="0"></td></tr></table>
<table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td width="100%" bgcolor="#fffffff"><img src="../img/trans.gif" width="10" height="1" border="0"></td></tr></table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr> 
    <td width="48%" height="76" bgcolor="#6496BE">&nbsp;<img src="../img/LogoLinks.gif"></td>
    <td width="8%" height="76" bgcolor="#6496BE"></td>
    <td width="44%" height="76" class="Rechts">
	<img src="../img/WsdbNova.jpg" style="Filter: Alpha(opacity=80);padding-top : 1px; -moz-opacity: 80%;" title="&copy;2003 &nbsp;Hannoversche Informationstechnologien">
	<!--div class="WSDB" title="&copy;2003 &nbsp;Hannoversche Informationstechnologien">Widerspruchsdatenbank</div--></td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr><td width="100%"><img src="../img/trans.gif" width="10" height="1" border="0"></td></tr></table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr><td width="100%" bgcolor="#666666"><img src="../img/trans.gif" width="10" height="1" border="0"></td></tr></table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<html:hidden property="suchbegriff" styleId="hiddenSuchbegriff"/>
<tr>
<td width="90%" bgcolor="#7399b9" class="Suche">&nbsp;Schnellsuche:
	<html:select property="tabelle" styleId="selectTabelle" onchange="showDependant()">
		<html:option value="0">bitte auswählen...</html:option>
		<html:option value="vwWiderspruch">Widerspruch</html:option>
		<html:option value="vwKlagen">Klage</html:option>
		<html:option value="vwMediation">Mediation</html:option>				
		<html:option value="vwEAO">einstw. Anordnung</html:option>		
		<html:option value="vwBerufung">Berufung</html:option>				
		<html:option value="vwBeschwerde">Beschwerde</html:option>				
		<html:option value="vwRevision">Revision</html:option>				
		<html:option value="vwAll">Alle Vorgänge</html:option>		
		<html:option value="Benutzer">Benutzer</html:option>
	</html:select>
	<html:select 	property="spalte" 
					size="1" 
					titleKey="label.GA.title"
					styleClass="LevelZwei"
					styleId="selectSpalte"
					onchange="showWert()">
		<html:option value="">bitte auswählen...</html:option>
		<html:optionsCollection name="fb" property="schnellsucheWiderspruch"/>
	</html:select>	
	<html:text property="wert" styleId="SuchText"/>
	<!--Select mit den Gegenständen des Verfahrens (Themen)-->
	<html:select property="wert" size="1" titleKey="label.GegenstandDesVerfahrens.title" styleId="selectThemen" onchange="setGegenstandDesVerfahrens()">
		<html:option value="">bitte auswählen:</html:option>	
		<html:optionsCollection name="fb" property="themen" />
		</html:select>	
	<html:submit value="suchen" styleId="SuchButton" onmouseover="this.style.cursor='hand'"></html:submit>
</td>
<td bgcolor="#7497B4" align="center"><img src="../img/nav_line1x20.gif" width="1" height="15" border="0"></td>
<td bgcolor="#7497B4">&nbsp;&nbsp;<a href="../pages/home.jsp" target=main class=head onmouseover="document.light1.src=imgLight2.src;" onmouseout="document.light1.src=imgLight1.src;">Home</a>&nbsp;&nbsp;</td>
<td bgcolor="#7497B4" align="center"><img src="../img/nav_line1x20.gif" width="1" height="15" border="0"></td>
<td bgcolor="#7497B4">&nbsp;&nbsp;<a href="../pages/Impressum.jsp" target=main class=head onmouseover="document.light2.src=imgLight2.src;" onmouseout="document.light2.src=imgLight1.src;">Impressum</a>&nbsp;&nbsp;</td>
<td bgcolor="#7497B4" align="center"><img src="../img/nav_line1x20.gif" width="1" height="15" border="0"></td>
<td bgcolor="#7497B4">&nbsp;&nbsp;<a href="mailto:info@HannIT.de" target=main class=head onmouseover="document.light3.src=imgLight2.src;" onmouseout="document.light3.src=imgLight1.src;">Kontakt</a>&nbsp;&nbsp;</td>
<td bgcolor="#7497B4" align="center"><img src="../img/nav_line1x20.gif" width="1" height="15" border="0"></td>
<td bgcolor="#7497B4">
  <p align="center"><a target="main" title="Klicken Sie hier, um Ihre Arbeit zu beenden" href="#" class=head  onClick="top.close()" onmouseover="document.light4.src=imgLight2.src;" onmouseout="document.light4.src=imgLight1.src;">&nbsp;&nbsp;Abmelden</a>&nbsp;&nbsp;</p>
</td>
<td bgcolor="#7497B4" align="center"><img src="../img/nav_line1x20.gif" width="1" height="15" border="0"></td>
<!--td width="5%" bgcolor=#7497B4>  &nbsp;</td-->
<td width="2px" bgcolor="#7497B4"></td>
<tr>
<td width="95%" valign="top" bgcolor="#426f9b">&nbsp;</td>
<td valign="top" align="center" bgcolor="#426f9b"><img src="../img/trans.gif" width="1" height="1" border="0"></td>
<td valign="top" bgcolor="#426f9b"><img src="../img/trans.gif" width="100%" height="15" border="0" name="light1"></td>
<td valign="top" align="center" bgcolor="#426f9b"><img src="../img/trans.gif" width="1" height="1" border="0"></td>
<td valign="top" bgcolor="#426f9b"><img src="../img/trans.gif" width="100%" height="15" border="0" name="light2"></td>
<td valign="top" bgcolor="#426f9b" align="center"><img src="../img/trans.gif" width="1" height="1" border="0"></td>
<td valign="top" bgcolor="#426f9b"><img src="../img/trans.gif" width="100%" height="15" border="0" name="light3"></td>
<td valign="top" bgcolor="#426f9b" align="center"><img src="../img/trans.gif" width="1" height="1" border="0"></td>
<td valign="top" bgcolor="#426f9b"><img src="../img/trans.gif" width="100%" height="15" border="0" name="light4"></td>
<td valign="top" bgcolor="#426f9b" align="center"><img src="../img/trans.gif" width="1" height="1" border="0"></td>
<!--<td valign="top" bgcolor="#426f9b"><img src="../img/trans.gif" width="100%" height="15" border="0" name="light5"></td>
<td valign="top" bgcolor="#426f9b" align="center"><img src="../img/trans.gif" width="1" height="1" border="0"></td>-->
<td width="5%" valign="top" bgcolor="#426f9b">&nbsp;</td>
</tr>
</table>
</html:form>
</body>
</html>