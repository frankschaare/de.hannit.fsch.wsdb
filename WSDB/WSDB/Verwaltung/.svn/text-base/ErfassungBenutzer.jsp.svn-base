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
<html:errors property="KeineBerechtigung"/>
<html:errors property="speichernErfolgreich"/>
<html:errors property="DatensatzVorhanden"/>

var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

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

// -->
</script>

</head>

<body onLoad="init()">

<html:form action="ErfassungSachbearbeiter" method="post" target="_self">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Neuen Benutzer in der Datenbank anlegen:</div>
	<!--==================== Formular ====================-->
	<div id="Formular"style="height:100%;padding-left:10px">
	<!--==================== versteckte Formularfelder ====================-->
	<html:hidden name="sb" property="id"/>
	<html:hidden name="sb" property="aktion" styleId="hAktion"/>
	<!--==================== Benutzerdaten ====================-->
		<span id="Benutzerdaten">
			<fieldset>
					<LEGEND ALIGN="left">Benutzer / in</LEGEND>
						<div id="Benutzername"><span class="label">Benutzername:*</span><span class="wert">
						<html:text name="sb"
							property="username" 
							titleKey="label.Username.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div align="center" class="errors"><html:errors property="username"/></div>							
						<div id="Anrede"><span class="label">Anrede:&nbsp;</span><span class="wert">
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
						<div id="Vorname"><span class="label">Vorname:&nbsp;</span><span class="wert">
						<html:text name="sb"
							property="vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Nachname"><span class="label">Nachname:*</span><span class="wert">
						<html:text name="sb"
							property="nachname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div align="center" class="errors"><html:errors property="nachname"/></div>							
						<div id="Organisationseinheit"><span class="label">Organisationseinheit:*</span><span class="wert">
						<html:select name="sb" property="organisationseinheitID" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="sachgebiet" onchange="setSelectRolle(this)">
							<html:option value="">........</html:option>	
							<html:optionsCollection name="fb" property="teams" />
						</html:select></span></div>
						<div align="center" class="errors"><html:errors property="organisationseinheitID"/></div>													
						<div id="Durchwahl"><span class="label">Durchwahl:&nbsp;</span><span class="wert">
						<html:text name="sb"
							property="durchwahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Raum"><span class="label">Raum:&nbsp;</span><span class="wert">
						<html:text name="sb"
							property="raum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textUsername"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Passwort"><span class="label">Passwort:&nbsp;</span><span class="wert">
						<html:password name="sb"
							property="passwort" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textPasswort"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span></div>
						<div id="Passwort2"><span class="label">Passwortwiederholung:&nbsp;</span><span class="wert">
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
				<LEGEND ALIGN="left">Berechtigungen</LEGEND>
					<div id="Rollen">
						<logic:iterate id="rolle" name="sb" property="sachgebiet.aufgaben">
							<span class="wert">
							<html:multibox name="sb" property="sachgebiet.zuloeschen" onclick="setAktion(this)">
								<bean:write name="rolle"/>
							</html:multibox>
								<bean:write name="rolle"/>
							</span>
						</logic:iterate>
					</div>
			</fieldset>
		</span>
	<!--==================== Berechtigung hinzufügen ====================-->
		<span id="BerechtigungenNeu" style="width:98%;height:35%;">
			<fieldset>
				<legend align="left">Berechtigung hinzufügen</legend>
			<span style="padding-left:7px;">Neue Berechtigung für Team:&nbsp;
					<html:select name="sb" property="sachgebiet.insertRolle" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="selectRolle" onchange="changeDIV(this)">
						<html:option value="">........</html:option>	
						<html:optionsCollection name="fb" property="teams" />
					</html:select>
			hinzufügen. 
				</span>
				<p style="padding-left:7px;">Hinweis:&nbsp;Auf dieser Seite k&ouml;nnen Sie einen neuen Benutzer anlegen. Es ist ausserdem sehr sinnvoll, dem Benutzer gleich eine Berechtigung auszustellen. Aus diesem Grund wird das Auswahlfeld 'Berechtigung hinzuf&uuml;gen' automatisch mit der ausgew&auml;hlten Organisationseinheit synchronisiert. </p>
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
