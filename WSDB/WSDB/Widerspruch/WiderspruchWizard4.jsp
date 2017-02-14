<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page 	language="java" 
			session="true"
			import="java.util.*"
            autoFlush="true"
			info="JSP zur Erfassung eines Widerspruches"
			isErrorPage="false"
			contentType="text/html; charset=ISO-8859-1"
%>

<%--Die Bean 'now' stellt das Tagesdatum bereit --%>
<jsp:useBean id="now" class="java.util.Date" />  

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<html>
<head>
<title>Widerspruch erfassen</title>

</head>
<style type="text/css">
<!--
div, input, select, textarea, td
{
font-family:Tahoma, Verdana, Arial, sans-serif;
font-size:11px;
}
#Aussenlayer
{
position:absolute; 
width:90%; 
height:90%; 
left: 5%; 
top: 5%;
background-color:#FFFFFF;
border: 1px solid; 
border-bottom-color:#424142;
border-right-color:#424142;
border-top-color:#D6D3CE;
border-left-color:#D6D3CE;
z-index:1;
}
#Innenlayer
{
position:absolute;
background-color:#D6D3CE; 
border: 1px solid;
border-bottom-color:#848284;
border-right-color:#848284;
border-top-color:#FFFFFF;
border-left-color:#FFFFFF; 
width:100%; 
height:100%; 
left:0px; 
top:0px;
padding:1px;
z-index:1;
}
#Kopfzeile
{
	position:absolute;
	background-color:#08246B;
	color:#FFFFFF;
	width:100%;
	height:3%;
	left: 0px;
	top: 0px;
	z-index:1;
	font-weight: bold;
	padding: 3px;
}
#Formular
{
	position:absolute;
	width:100%;
	height:90%;
	left: 0px;
	top: 5%;
	z-index:2;
}
#Step4Hilfeempfänger
{
	position:absolute;
	font-size:11px;
<logic:match name="sessionWS" property="vertreterVorhanden" value="true">
width:48%;
</logic:match>
<logic:match name="sessionWS" property="vertreterVorhanden" value="false">
width:100%;
</logic:match>
	height:48%;
	left: 0px;
	top: 1%;
	z-index:2;
}

#Step4Vertreter
{
<logic:match name="sessionWS" property="vertreterVorhanden" value="false">
	display:none;
</logic:match>
	position:absolute;
	font-size:11px;
	width:49%;
	height:48%;
	left: 50%;
	top: 1%;
	z-index:3;
}
#Step4Angaben
{
	position:absolute;
	font-size:11px;
	width:100%;
	height:48%;
	left: 0px;
	top: 50%;
	z-index:2;
}
fieldset.buttons
{
position:absolute;
padding:3px;
bottom: 3px;
}
input.textLang {width:98%;}
input.textPLZ {width:auto;}
input.textDatum {width:18%;}
input.button {width:30%;}
textarea {width:98%;}
Select {width:50%;}
Select.Sachgebiet {width:16%;}

div.errors
{
font-weight:bold;
color:#FF0000;
}
-->
</style>

<SCRIPT LANGUAGE="javascript">
<!--

function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}
<logic:messagesPresent>
alert("Es ist bereits ein Vorgang mit gleichem Aktenzeichen gespeichert ! Ändern Sie das vorhandene Aktenzeichen, oder bearbeiten Sie den unter gleichem Aktenzeichen vorhandenen Vorgang.");
document.getElementById("aktenzeichen").focus();
</logic:messagesPresent>
}

function getCoordinates() 
{
x = window.event.clientX;
y = window.event.clientY;
PopupKalender(x,y);
}

function PopupKalender(x,y) {

F = window.open("/WSDB/html/Kalender.html","Popup","left="+x+",top="+y+",width=200,height=220,dependant=yes,status=no");
}
-->
</SCRIPT>
<noscript>
<h1>Die Widerspruchsdatenbank funktioniert nur, wenn Ihr Browser JavaScript eingeschaltet hat. 
Leider ist JavaScript bei Ihnen ausgeschaltet. Bitte schalten Sie JavaScript ein, oder beenden Sie die Anwendung.
</h1>
</noscript>

<body onLoad="init()">

<html:form action="WiderspruchErfassenSchritt4" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Widerspruch erfassen Schritt 4: Angaben &uuml;berpr&uuml;fen und speichern </div>
	<!--==================== Formular ====================-->
	<div id="Formular">
<!--==================== Beginn Hilfeempfänger ====================-->
	<div id="Step4Hilfeempfänger">
	<fieldset>
		<LEGEND ALIGN="left">Hilfeempfänger</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="hilfeempfaenger.vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>				
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="hilfeempfaenger.nachname"
							titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="hilfeempfaenger.adresse.postleitzahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.postleitzahl.title"
							styleClass="textPLZ" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Wohnort:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="hilfeempfaenger.adresse.ort" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.ort.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Geburtsdatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="hilfeempfaenger.geburtsdatum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.geburtsdatum.title" 
							styleClass="textDatum" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Sterbedatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="hilfeempfaenger.sterbedatum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.sterbedatum.title" 
							styleId="SterbeDatum"
							styleClass="textDatum"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="showEinrichtung()"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right" id="labelEinrichtung" title="Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen" onMouseOver="status='Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen';return true;" onMouseOut="status='';return true;"><a href="javascript:PopupEinrichtung()">Einrichtung</a>:</div></td>
				<td width="2%"><div align="center"><html:hidden property="einrichtung.id" styleId="einrichtung.id"/></div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="einrichtung.name" 
							titleKey="WiderspruchErfassen.einrichtung.name.title" 
							styleClass="textLang"
							readonly="true"
							styleId="einrichtung.name"
							ondblclick="resetField()"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">gA:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:select name="sessionWS" property="hilfeempfaenger.ga" size="1" titleKey="label.GA.title">
					<html:option value="bitte auswählen:" key=""></html:option>			
					<html:optionsCollection name="fb" property="gemeinden" label="value" value="key"/>
				</html:select>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>						  					    			  					    		    
			</table>
		</fieldset>
		</div>
<!--==================== Ende Hilfeempfänger ====================-->
<!--==================== Beginn Vertreter ====================-->
		<div id="Step4Vertreter">
		<fieldset>
		<LEGEND ALIGN="left"><bean:write name="sessionWS" property="vertreter.bezeichnung"/></LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Art:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:select  name="sessionWS" property="vertreter.art" size="1">
					<html:option value="">bitte auswählen:</html:option>			
					<html:optionsCollection name="fb" property="beteiligte"/>
				</html:select>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="vertreter.vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>				
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="vertreter.nachname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="vertreter.adresse.postleitzahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.postleitzahl.title"
							styleClass="textPLZ" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Wohnort:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="vertreter.adresse.postleitzahl"  
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.ort.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			</table>
		</fieldset>		
		</div>
<!--==================== Ende   Vertreter ====================-->
<!--==================== Beginn weitere Angaben ====================-->
		<div id="Step4Angaben">
		<fieldset>
		<LEGEND ALIGN="left">weitere Angaben zum Widerspruch</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Aktenzeichen:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
					<html:text 	name="sessionWS"
								property="aktenzeichen" 
								titleKey="label.AZ.title" 
								styleId="aktenzeichen"
								onfocus="this.style.backgroundColor='#C5D4E0'" 
								onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><a href="javascript:PopupKalender()"><div align="right" onClick="getCoordinates()">Eingangsdatum:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="sessionWS"
							property="eingangsdatum" 
							titleKey="label.Geburtsdatum.title" 
							readonly="true"
							styleClass="textDatum" 
							styleId="eingangsdatum"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>			
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Angaben zum Widerspruch:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:textarea 	name="sessionWS"
								property="angaben" 
								titleKey="label.Angaben.title" 
								rows="5"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Verfahrensart:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="sessionWS" property="verfahrensartID" size="1" titleKey="label.Verfahrensart.title">
					<html:option value="">bitte auswählen:</html:option>
					<html:optionsCollection name="fb" property="verfahrensart"/>
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Gegenstand des Verfahrens:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="sessionWS" property="gegenstandDesVerfahrensID" size="1" titleKey="label.GegenstandDesVerfahrens.title">
					<html:optionsCollection name="fb" property="themen" />
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Rechtsgebiet:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="sessionWS" property="rechtsgebietID" size="1" titleKey="label.Anrede.title">
					<html:optionsCollection name="fb" property="rechtsgebiet" />
				</html:select>	
				</td>
			  </tr>
			</table>
		</fieldset>
		</div>
<!--==================== Ende weitere Angaben ====================-->
	</div>
	<!--==================== Formular ====================-->	
	<!--==================== Buttons ====================-->	
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div align="left"><html:button property="button" value="&lt;&lt; zurück" styleClass="button" onclick="javascript:history.back();"/></div></td>
			<td width="50%"><div align="right"><html:submit value="weiter >>" styleClass="button"/></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
	</div>
</div>
</html:form>
</body>
</html>
