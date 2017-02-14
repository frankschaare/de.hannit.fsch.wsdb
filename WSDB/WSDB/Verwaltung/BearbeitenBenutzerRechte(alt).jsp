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
<script type="text/javascript">
<!--
var enableInsertDIV = true;
/*
* Sorgt dafür, dass die Seite beim Laden den Navigationsbaum aktualisiert.
*/
function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

}
/*
* Wenn eine RollencheckBox angeklickt wird, muss geprüft werden, ob sie an oder aus ist.
* Ist eine an (d.h. eine Rolle soll gelöscht werden) wird das DIV RolleHinzufuegen ausgeblendet und der SubmitButton 
* entsprechend geändert.
*/
function changeDIV(element)
{
if(element.options[element.selectedIndex].value.length > 0) // Ein gültiger Wert wurde ausgewählt
{
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
else
{
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
	<div id="Formular">
	<!--==================== Layouttabelle ====================-->
	<table width="100%"  border="1" cellspacing="0" cellpadding="0">
	  <tr>
		<td width="50%">
	<!--==================== Benutzerdaten ====================-->
		<fieldset>
		<LEGEND ALIGN="left">Benutzer / in</LEGEND>
			<table width="100%"  border="1" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:hidden name="sb" property="userID"/>
				<bean:write	name="sb" property="vorname"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%"><bean:write	name="sb" property="nachname"/></td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Organisationseinheit:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%"><bean:write	name="sb" property="organisationseinheitID"/></td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Raum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%"><bean:write	name="sb" property="raum"/></td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Durchwahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%"><bean:write	name="sb" property="durchwahl"/></td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Benutzerrollen:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">&nbsp;</td>
			  </tr>						
			  <tr>
				<td colspan="3">&nbsp;</td>
			  </tr>						  					    			  					    		    
			</table>
		</fieldset>
	<!--==================== Benutzerdaten ====================-->		
		</td>
		<td width="50%">&nbsp;</td>
	  </tr>
	  <tr>
		<td colspan="2" width="100%">&nbsp;</td>
		</tr>
	  <tr>
		<td width="50%">&nbsp;</td>
		<td width="50%">&nbsp;</td>
	  </tr>
	</table>
	<!--==================== Layouttabelle ====================-->

	<html:select name="sb" property="sachgebiet.insertRolle" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="sachgebiet" onchange="changeDIV(this)">
		<html:option value="">........</html:option>	
		<html:optionsCollection name="fb" property="teams" />
	</html:select>
	<!--==================== Buttons ====================-->	
	</div>
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div align="left"><html:button property="button" value="Abbrechen" styleClass="button" onclick="javascript:history.back();"/></div></td>
			<td width="50%"><div align="right"><html:submit value="&auml;ndern >>" styleClass="button" styleId="submitButton"/></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
			
	</div>
</div>

</html:form>
</body>
</html>
