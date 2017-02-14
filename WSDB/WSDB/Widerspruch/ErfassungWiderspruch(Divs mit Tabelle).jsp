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
<style type="text/css">
<!--
body {margin:0; background-color:#999999}

div, input, select
{
font-family:Tahoma, Verdana, Arial, sans-serif;
font-size:11px;
}

input.DatumDefault {width:65px;}
input.Text20 
{
width:49%;
border:none;
border-bottom: 1px solid;
}
input.Text5 
{
width:14%;
border:none;
border-bottom: 1px solid;
}
input.Text50 
{
width:84%;
border:none;
border-bottom: 1px solid;
}
#Dokument
{
position:absolute; 
width:60%; 
height:96%; 
left: 18%; 
top: 2%;
background-color:#FFFFFF;
border: 1px solid #000000; 
z-index:2;
}
#Hilfeempfänger
{
position:absolute; 
width:50%; 
height:auto; 
left: 1px; 
top: 10%;
z-index:1;
}
#Betreffzeile
{
border: 1px solid lightgrey;
position:relative; 
width:98%; 
height:auto; 
left: 1%; 
top: 40%;
z-index:1;
}
#Schatten
{
background-color:#333333;
position:absolute; 
width:60%; 
height:96%; 
left: 19%; 
top: 3%;
z-index:1;
}
td.label
{
font-size:9px;
}
-->
</style>


</head>

<SCRIPT LANGUAGE="javascript">
<!--
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

<body>
<html:form action="/WiderspruchErfassenSubmit">
<div id="Dokument">
	<!--==================== Beginn Hilfeempfänger ====================-->
	<div id="Hilfeempfänger">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%" class="label">Vorname</td>
			<td width="50%" class="label">Nachname *</td>
		  </tr>
		  <tr>
			<td width="50%">
			<html:text 	property="hilfeempfaenger.vorname" 
						titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
						styleClass="Text20"
						onfocus="this.style.backgroundColor='#C5D4E0';this.value=''" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>
			</td>
			<td width="50%">
			<html:text 	property="hilfeempfaenger.nachname"
						titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
						styleClass="Text20"
						onfocus="this.style.backgroundColor='#C5D4E0';this.value=''" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>				
			</td>
		  </tr>
		<tr>
			<td width="50%"></td>
			<td width="50%"></td>
		</tr>
		<tr>
			<td width="50%" class="label">Postleitzahl *</td>
			<td width="50%" class="label">Wohnort *</td>
		</tr>
		<tr>
			<td width="50%">
			<html:text 	name="wsForm"
						property="hilfeempfaenger.adresse.postleitzahl" 
						titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.postleitzahl.title"
						styleClass="Text5" 
						onfocus="this.style.backgroundColor='#C5D4E0';this.value=''" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>			
			</td>
			<td width="50%">
			<html:text 	name="wsForm"
						property="hilfeempfaenger.adresse.ort" 
						titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.ort.title" 
						styleClass="Text50"
						onfocus="this.style.backgroundColor='#C5D4E0';this.value=''" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>	
			</td>
		</tr>
		<tr>
			<td width="50%"></td>
			<td width="50%"></td>
		</tr>
		<tr>
			<td width="50%" class="label">Geburtsdatum *</td>
			<td width="50%" class="label">GA</td>
		</tr>
		<tr>
			<td width="50%">
			<html:text 	property="hilfeempfaenger.geburtsdatum" 
						titleKey="WiderspruchErfassen.hilfeempfaenger.geburtsdatum.title" 
						styleClass="DatumDefault" 
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>
			</td>
			<td width="50%">
			<html:select property="hilfeempfaenger.ga" size="1" titleKey="label.GA.title">
				<html:option value="bitte auswählen:" key=""></html:option>			
				<html:optionsCollection name="fb" property="gemeinden" label="value" value="key"/>
			</html:select>	
			</td>
		</tr>
		</table>

	</div>
	<!--==================== Ende Hilfeempfänger ====================-->	
	<div id="Betreffzeile">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td width="33%"><div align="center">Aktenzeichen</div></td>
		<td width="33%"><div align="center"></div></td>
		<td width="33%"><a href="javascript:PopupKalender()"><div align="center" onClick="getCoordinates()">Eingangsdatum</div></a></td>
	  </tr>
	  <tr>
		<td>
		<html:select property="sachgebiet" size="1" titleKey="label.Anrede.title" styleClass="sachgebiet">
				<html:option value="">........</html:option>	
				<html:optionsCollection name="fb" property="teams" />
			</html:select><html:text 	property="aktenzeichen" 
						titleKey="label.AZ.title" 
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>		</td>
		<td>&nbsp;</td>
		<td>
			<div align="center">
			<html:text 	name="fb"
						property="eingangsdatum" 
						titleKey="label.Geburtsdatum.title" 
						readonly="true"
						styleClass="DatumDefault" 
						styleId="eingangsdatum"
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>		</div></td>
	  </tr>
	  <tr>
		<td></td>
		<td></td>
		<td></td>
	  </tr>
	</table>
	</div>
</div>
<div id="Schatten"></div>
</html:form>
</body>
</html>
