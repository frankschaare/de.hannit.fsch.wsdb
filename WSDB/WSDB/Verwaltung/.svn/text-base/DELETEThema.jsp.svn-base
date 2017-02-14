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
#thema
{
width:98%;
height:30%;
}
span.label
{
width:35%;
text-align:right;
padding:2px;
}
span.wert
{
width:65%;
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
span.buttons
{
width:98%;
}
fieldset
{
padding:5px;
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
<html:errors property="KeinAdministrator"/>

var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

//document.getElementById("textUsername").focus();

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

<html:form action="CRUDThema?crud=3&loaded=true" method="post" target="_self">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Gegenstand des Verfahrens bearbeiten:</div>
	<!--==================== Formular ====================-->
	<div id="Formular"style="height:94%;padding-left:10px;">
	<!--==================== versteckte Formularfelder ====================-->
	<html:hidden name="thema" property="id"/>
	<!--==================== Benutzerdaten ====================-->
		<span id="Benutzerdaten">
			<fieldset>
					<LEGEND ALIGN="left">Gegenstand des Verfahrens</LEGEND>
						<div class="errors"><span class="label"></span><span class="wert"></span></div>						
						<div id="thema"><span class="label">Gegenstand des Verfahrens:</span><span class="wert">
						<html:text name="thema"
							property="thema" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="textThema"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/></span>
						<div class="errors"><span class="label"></span><span class="wert"><html:errors property="thema"/></span></div>
						<span class="label">Organisationseinheit:</span><span class="wert">
						<html:select name="thema" property="team" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="sachgebiet" onchange="setSelectRolle(this)">
							<html:option value="">........</html:option>	
							<html:optionsCollection name="fb" property="teams" />
						</html:select></span>	
						<div class="errors"><span class="label"></span><span class="wert"><html:errors property="team"/></span></div>
						</div>
		</fieldset>
		</span>

	<!--==================== Berechtigung hinzufügen ====================-->
		<span id="BerechtigungenNeu" style="width:98%;height:35%;">
			<fieldset>
				<legend>Hinweise</legend>
				<p style="padding-left:7px;"><bean:message key="Thema.DELETE.text.Hinweise"/></p>
		</fieldset>
		</span>
	<!--==================== Buttons ====================-->	
		<span id="Buttons">
			<fieldset>
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td width="50%"><div align="left"><html:button property="button" value="&lt;&lt;&nbsp;Abbrechen" styleClass="button" onclick="javascript:history.back();"/></div></td>
					<td width="50%"><div align="right"><html:submit value="l&ouml;schen >>" styleClass="button" styleId="submitButton"/></div></td>
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
