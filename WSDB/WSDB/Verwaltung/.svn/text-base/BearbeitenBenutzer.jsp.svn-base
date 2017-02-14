<%@ page import="org.displaytag.sample.*, java.util.*,
                 org.displaytag.tags.TableTag"%>

<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html>
<head>
<title>Ereignis erfassen</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/WindowsFormulare.css">
<style type="text/css">
<!--
#Benutzerdaten,#Berechtigungen
{
width:49%;
height:50%;
}
span.label
{
width:40%;
text-align:right;
padding:2px;
}
span.wert
{
width:60%;
text-align:left;
padding:2px;
font-weight:bold;
}
span.berechtigung
{
width:60%;
text-align:left;
padding-left:10px;
padding-top:5px;
font-weight:bold;
color:#666666;
}
fieldset
{
height:100%;
padding:5px;
}
#selectAnreden
{
width:60px;
}

select.Sachgebiet
{
width:80px;
}
-->
</style>

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

document.getElementById("selectRolle").disabled=true;
document.getElementById("textUsername").focus();

if (fehler != null)
{
alert(fehler);
}

}

/*
* Wird eine Rollencheckbox angeklickt muss das hidden hAktion = 0 sein !
*/
function setAktion(control)
{
	if(control.checked){document.getElementById("hAktion").value = "0";	}
}

/*
* Setzt den Wert des SelectRolle gleich des Wertes OE
*/
function setSelectRolle(element)
{
var wert = element.options[element.selectedIndex].value;
	if(wert.length > 0) // Ein gültiger Wert wurde ausgewählt
	{
	document.getElementById("selectRolle").value = wert;
	}
}

/*
* Die Übereinstimmung der Passwörter ist besonders wichtig und wird daher sofort geprüft.
*/
function vergleichePasswort(element)
{
var pw1 = document.getElementById("textPasswort").value;
var pw2 = element.value;
	if(pw1.length > 0 && pw1 != pw2) // Passwort eingetragen aber ungleich Feld 2
	{
	alert("Die eingegebenen Passwörter stimmen nicht überein. Bitte überprüfen Sie Ihre Eingaben.");
	}
element.style.backgroundColor='#FFFFFF'
}
// -->
</script>

</head>

<body onLoad="init()">

<html:form action="UpdateSachbearbeiter" method="post" target="_self">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Benutzerdaten bearbeiten:</div>
	<!--==================== Formular ====================-->
	<div id="Formular"style="height:100%;padding-left:10px">
	<!--==================== versteckte Formularfelder ====================-->
	<html:hidden name="sb" property="id"/>
	<html:hidden name="sb" property="aktion" styleId="hAktion"/>
	<!--==================== Benutzerdaten ====================-->
		<span id="Benutzerdaten">
			<fieldset>
					<LEGEND ALIGN="left">Benutzer / in</LEGEND>
						<div id="Benutzername"><span class="label">Benutzername:</span><span class="wert">
						<html:text name="sb"
							property="username" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Anrede"><span class="label">Anrede:</span><span class="wert">
						<html:select name="sb" 
							property="anrede" 
							size="1" 
							titleKey="label.Sachgebiet.title" 
							styleClass="Anreden" 
							styleId="selectAnreden">
						<html:option value="">........</html:option>
						<html:optionsCollection 
							name="fb" 
							property="anreden" />
						</html:select></span></div>
						<div id="Vorname"><span class="label">Vorname:</span><span class="wert">
						<html:text name="sb"
							property="vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Nachname"><span class="label">Nachname:</span><span class="wert">
						<html:text name="sb"
							property="nachname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Organisationseinheit"><span class="label">Organisationseinheit:</span><span class="wert">
						<html:select name="sb" property="organisationseinheitID" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="sachgebiet" onchange="setSelectRolle(this)">
							<html:option value="">........</html:option>	
							<html:optionsCollection name="fb" property="teams" />
						</html:select></span></div>
						<div id="Durchwahl"><span class="label">Durchwahl:</span><span class="wert">
						<html:text name="sb"
							property="durchwahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Raum"><span class="label">Raum:</span><span class="wert">
						<html:text name="sb"
							property="raum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Passwort"><span class="label">Passwort:</span><span class="wert">
						<html:password name="sb"
							property="passwort" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textPasswort"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Passwort2"><span class="label">Passwortwiederholung:</span><span class="wert">
						<html:password name="sb"
							property="passwort2" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textPasswort2"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="vergleichePasswort(this)"/></span></div>
			</fieldset>
		</span>
	<!--==================== Berechtigungen ====================-->
		<span id="Berechtigungen">
			<fieldset>
				<LEGEND style="text-align:left;color:#666666">Berechtigungen</LEGEND>
					<div id="Rollen">
						<logic:iterate id="rolle" name="sb" property="sachgebiet.aufgaben">
							<span class="berechtigung"><bean:write name="rolle"/></span>
						</logic:iterate>
					</div>
			</fieldset>
		</span>
	<!--==================== Berechtigung hinzufügen ====================-->
		<span id="BerechtigungenNeu" style="width:98%;height:35%;">
			<fieldset>
				<legend style="text-align:left;color:#666666">Berechtigung hinzufügen</legend>
			<span style="padding-left:7px;color:#666666">Neue Berechtigung für Team:&nbsp;
					<html:select name="sb" property="sachgebiet.insertRolle" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="selectRolle" onchange="changeDIV(this)">
						<html:option value="">........</html:option>	
						<html:optionsCollection name="fb" property="teams" />
					</html:select>
			hinzufügen. 
				</span>
				<p style="padding-left:7px;">Hinweis:&nbsp;Auf dieser Seite k&ouml;nnen Sie die Stammdaten eines vorhandenen Benutzers &auml;ndern. Sie k&ouml;nnen nicht die Berechtigungen des Benutzers &auml;ndern, daf&uuml;r ist die Seite 'Benutzer / Rechte' vorgesehen. Aus diesem Grund wird das Auswahlfeld 'Berechtigung hinzuf&uuml;gen' ausgegraut. </p>
		</fieldset>
		</span>
	<!--==================== Buttons ====================-->	
		<span id="Buttons" style="width:98%;">
			<fieldset>
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td width="50%"><div align="left"><html:button property="button" value="&lt;&lt;&nbsp;Abbrechen" styleClass="button" onclick="javascript:history.back();"/></div></td>
					<td width="50%"><div align="right"><html:submit value="speichern >>" styleClass="button" styleId="submitButton"/></div></td>
				  </tr>
				</table>
			</fieldset>	
		</span>		
	<!--==================== Buttons ====================-->	
	</div>
			
	</div>
</div>

</html:form>
</body>
</html>
