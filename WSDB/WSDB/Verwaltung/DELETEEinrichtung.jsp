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
<html:errors property="DatensatzVorhanden"/>

var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

//document.getElementById("textUsername").focus();

if (fehler != null)
{
alert(fehler);
}

}

// -->
</script>

</head>

<body onLoad="init()">

<html:form action="CRUDEinrichtung?crud=3&loaded=true" method="post" target="_self">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Vorhandene Einrichtung löschen:</div>
	<!--==================== Formular ====================-->
	<div id="Formular"style="height:94%;padding-left:10px;">
	<!--==================== versteckte Formularfelder ====================-->

	<!--==================== Benutzerdaten ====================-->
		<span id="Benutzerdaten">
			<fieldset>
					<LEGEND ALIGN="left">Einrichtung</LEGEND>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td colspan="3"><div align="center">&nbsp;</div></td>
		<td colspan="3"><div align="center">&nbsp;</div></td>
	  </tr>	  
	  <tr>
		<td width="24%"><div align="right">Name der Einrichtung:</div></td>
		<td width="2%"><div align="center">*</div></td>
		<td colspan="4"><div align="left">
			<html:hidden property="id" />
			<html:text 	property="name" 
						styleClass="textLang"
						titleKey="ErfassungEinrichtung.name.title" 
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>		
		</div></td>
	  </tr>
	  <tr>
		<td colspan="3"><div align="center"><html:errors property="name"/></div></td>
		<td colspan="3"><div align="center"></div></td>
	  </tr>	 
	  <tr>
		<td width="24%"><div align="right">Strasse:</div></td>
		<td width="2%"><div align="center">&nbsp;</div></td>
		<td width="24%">
			<html:hidden property="adresse.id" />
			<html:text 	property="adresse.strasse" 
						titleKey="label.Nachname.title" 
						styleClass="textLang"						
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>		
		</td>
		<td width="24%"><div align="right">Hausnummer:</div></td>
		<td width="2%">&nbsp;</td>
		<td width="24%">
			<html:text 	property="adresse.hausnummer" 
						titleKey="label.Nachname.title" 
						styleClass="textMittel"						
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>			
		</td>
	  </tr>
	  <tr>
		<td colspan="3"><div align="center"></div></td>
		<td colspan="3"><div align="center"></div></td>
	  </tr>	
	  <tr>
		<td width="24%"><div align="right">Postleitzahl:</div></td>
		<td width="2%"><div align="center">*</div></td>
		<td width="24%">
			<html:text 	property="adresse.postleitzahl" 
						styleClass="textMittel"
						titleKey="ErfassungEinrichtung.adresse.postleitzahl.title" 
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>		
		</td>
		<td width="24%"><div align="right">Ort:</div></td>
		<td width="2%">*</td>
		<td width="24%">
			<html:text 	property="adresse.ort" 
						titleKey="label.Nachname.title" 
						styleClass="textLang"						
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>
		</td>
	  </tr>
	  <tr>
		<td colspan="3"><div align="center"><html:errors property="adresse.postleitzahl"/></div></td>
		<td colspan="3"><div align="center"><html:errors property="adresse.ort"/></div></td>
	  </tr>		   
	</table>
</fieldset>
		</span>

	<!--==================== Berechtigung hinzufügen ====================-->
		<span id="BerechtigungenNeu" style="width:98%;height:35%;">
			<fieldset>
				<legend>Hinweise</legend>
				<p style="padding-left:7px;"><div class="errors"><bean:message key="Einrichtung.DELETE.text.Hinweise"/></div></p>
		</fieldset>
		</span>
	<!--==================== Buttons ====================-->	
		<span id="Buttons">
			<fieldset>
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td width="50%"><div align="left"><html:button property="button" value="&lt;&lt;&nbsp;Abbrechen" styleClass="button" onclick="javascript:history.back();"/></div></td>
					<td width="50%"><div align="right"><html:submit value="löschen >>" styleClass="button" styleId="submitButton"/></div></td>
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
