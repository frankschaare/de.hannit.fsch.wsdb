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
* Wenn eine RollencheckBox angeklickt wird, muss geprüft werden, ob sie an oder aus ist.
* Ist eine an (d.h. eine Rolle soll gelöscht werden) wird das DIV RolleHinzufuegen ausgeblendet und der SubmitButton 
* entsprechend geändert.
*/
function changeDIV(element)
{
var aktion = document.getElementById("hAktion");

if(element.options[element.selectedIndex].value.length > 0) // Ein gültiger Wert wurde ausgewählt, Token auf INSERT !
{
aktion.value = "1";
	for(var i=0;i<document.forms[0].length;++i)
	{
		var fe = document.forms[0].elements[i]; // fe = Formelement
		if(fe.type == 'checkbox' && fe.name == "sachgebiet.zuloeschen")
		{
		fe.checked = false;
		fe.disabled=true;
		}
	}

}
else // Kein gültiger Wert ausgewählt, löschen wird erwartet, Token auf DELETE !
{
aktion.value = "0";
	for(var i=0;i<document.forms[0].length;++i)
	{
		var fe = document.forms[0].elements[i]; // fe = Formelement
		if(fe.type == 'checkbox' && fe.name == "sachgebiet.zuloeschen")
		{
		fe.disabled=false;
		}
	}
}

//alert(enableInsertDIV);
}
// -->
</script>

</head>

<body onLoad="init()">

<html:form action="BenutzerBearbeitungRechte" method="post" target="_self">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Berechtigungen f&uuml;r Benutzer <bean:write name="sb" property="username"></bean:write> bearbeiten:</div>
	<!--==================== Formular ====================-->
	<div id="Formular"style="height:100%;padding-left:10px">
	<!--==================== versteckte Formularfelder ====================-->
	<html:hidden name="sb" property="id"/>
	<html:hidden name="sb" property="aktion" styleId="hAktion"/>
	<!--==================== Benutzerdaten ====================-->
		<span id="Benutzerdaten">
			<fieldset>
					<LEGEND ALIGN="left">Benutzer / in</LEGEND>
						<div id="Benutzername"><span class="label">Benutzername:<html:hidden name="sb" property="username"/></span><span class="wert">&nbsp;<bean:write	name="sb" property="username"/></span></div>
						<div id="Vorname"><span class="label">Vorname:</span><span class="wert">&nbsp;<bean:write name="sb" property="vorname"/></span></div>
						<div id="Nachname"><span class="label">Nachname:</span><span class="wert">&nbsp;<bean:write	name="sb" property="nachname"/></span></div>
						<div id="Organisationseinheit"><span class="label">Organisationseinheit:</span><span class="wert">&nbsp;<bean:write name="sb" property="organisationseinheitID"/></span></div>
						<div id="Durchwahl"><span class="label">Durchwahl:</span><span class="wert">&nbsp;<bean:write name="sb" property="durchwahl"/></span></div>
						<div id="Raum"><span class="label">Raum:</span><span class="wert">&nbsp;<bean:write name="sb" property="raum"/></span></div>
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
					<html:select name="sb" property="sachgebiet.insertRolle" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="sachgebiet" onchange="changeDIV(this)">
						<html:option value="">........</html:option>	
						<html:optionsCollection name="fb" property="teams" />
					</html:select>
			hinzufügen. 
				</span>
				<p style="padding-left:7px;"><strong>Bitte beachten:</strong>&nbsp;Sie können <u>entweder</u> bestehende Rechte löschen,&nbsp; 
				  <u>oder</u> eine neue Berechtigung in diesem Bereich
hinzuf&uuml;gen.	Sie	k&ouml;nnen	nicht beides gleichzeitig tun. Sofern Sie bereits Rechte zum l&ouml;schen markiert haben, und dennoch eine neue Berechtigung aus der Auswahlliste w&auml;hlen, werden die bereits markierten 


 Berechtigungen

 wieder gel&ouml;scht und die Felder gesperrt. </p>
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
