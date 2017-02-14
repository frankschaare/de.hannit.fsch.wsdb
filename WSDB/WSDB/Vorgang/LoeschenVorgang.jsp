<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page 	language="java" 
			session="true"
			import="java.util.*"
			buffer="256kb"
            autoFlush="false"
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
<title>Vorgang löschen</title>

</head>
<style type="text/css">
<!--
div, input, select, textarea, td
{
font-family:Tahoma, Verdana, Arial, sans-serif;
font-size:12px;
}
#Aussenlayer
{
position:absolute; 
width:104%; 
height:99%; 
left: 0%; 
top: 0%;
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
	width:48%;
	height:48%;
	left: 0px;
	top: 1%;
	z-index:2;
}

#Step4Vertreter
{
<logic:match name="vorgang" property="vertreterVorhanden" value="false">
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
	top: 22%;
	z-index:2;
}
#Verfahrensverlauf
{
	position:absolute;
	font-size:12px;
	width:100%;
	height:48%;
	left: 0px;
	top: 42%;
	z-index:2;
}
#selectEreignis
{
width:auto;
}
fieldset.buttons
{
position:absolute;
padding:3px;
bottom: 3px;
}
input.textLang {width:45%;}
input.textPLZ {width:auto;}
input.textDatum {width:23%;}
input.button {width:30%;}
textarea {width:98%;}
Select {width:50%;}
Select.Sachgebiet {width:16%;}

li
{
margin-top:5px;
list-style-type:square;
list-style-position:outside;
}

div.errors
{
font-weight:bold;
color:#FF0000;
}
div.Ereignis {display:none;}
-->
</style>

<SCRIPT LANGUAGE="javascript">
<!--

function init()
{
var fehler = null;
<html:errors property="DatensatzVorhanden"/>
<html:errors property="ErfassungErfolgreich"/>

var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

if (fehler != null)
{
alert(fehler);
}

}

function checkEreignis()
{
var wert = document.getElementById("selectEreignis").value;
	if (wert.length == 0)
	{
	alert("Sie können nur speichern, wenn Sie ein gültiges Ereignis auswählen !");
	}
	else
	{
	document.forms[0].submit();
	}

}
/*
* Schaltet alle EreignisDivs aus und blendet das ausgewählte ein.
*/
function showEreignis(EreignisID)
{
	// Schleife durchläuft alle DIVs
	for(var i = 0; i < document.getElementsByTagName("div").length; i++)
	{
	// DIV wird benannt, z.b. 'div1'
	var DivElement = document.getElementsByTagName("div")[i];
	// Die id bildet den eindeutigen Bezeichner des DIVs
	// var DivID = DivElement.id;
		
		if(DivElement.id.indexOf("EreignisDiv") != -1)
		{
			if(DivElement.id==("EreignisDiv"+EreignisID))
			{
			DivElement.style.display="inline";
			}
			else
			{
			DivElement.style.display="none";
			}
		}
	}
}

function setDatum(control)
{
var d = document.getElementById("Ereignis.datum");
	if (d != null)
	{
	d.value=control.value;
	}
control.style.backgroundColor='#FFFFFF';
}

function setKommentar(control)
{
var d = document.getElementById("Ereignis.kommentar");
	if (d != null)
	{
	d.value=control.value;
	}
control.style.backgroundColor='#FFFFFF';
}

function getCoordinates() 
{
x = window.event.clientX;
y = window.event.clientY;
PopupKalender(x,y);
}

function PopupKalender(x,y) {
F = window.open("<%=request.getContextPath()%>/html/Kalender.html","Popup","left="+x+",top="+y+",width=200,height=220,dependant=yes,status=no");
}
-->
</SCRIPT>
<noscript>
<h1>Die Widerspruchsdatenbank funktioniert nur, wenn Ihr Browser JavaScript eingeschaltet hat. 
Leider ist JavaScript bei Ihnen ausgeschaltet. Bitte schalten Sie JavaScript ein, oder beenden Sie die Anwendung.
</h1>
</noscript>

<body onLoad="init()">

<html:form action="LoeschenVorgang" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Vorgang <bean:write name="vorgang" property="widerspruch.aktenzeichen"/>&nbsp;(<bean:write name="vorgang" property="hilfeempfaenger.nachname"/>) löschen: Löschen bestätigen. </div>
	<!--==================== Formular ====================-->
	<div id="Formular">
<!--==================== Beginn Hilfeempfänger ====================-->
	<div id="Step4Hilfeempfänger">
	<fieldset>
		<LEGEND ALIGN="left">Hilfeempfänger</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="48%"><div id="labelVorname" align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="48%"><strong><bean:write name="vorgang" property="hilfeempfaenger.vorname"/></strong></td>
			  </tr>
			  <tr>
				<td width="48%"><div id="labelNachname" align="right">Nachname:</div></td>
				<td width="2%"></td>
				<td width="48%"><strong><bean:write name="vorgang" property="hilfeempfaenger.nachname"/></strong></td>
			  </tr>

			  <tr>
				<td width="48%"><div id="labelWohnort" align="right">Wohnort:</div></td>
				<td width="2%"></td>
				<td width="48%"><strong><bean:write name="vorgang" property="hilfeempfaenger.adresse.postleitzahl"/>&nbsp;<bean:write name="vorgang" property="hilfeempfaenger.adresse.ort"/></strong></td>
			  </tr>
			  <tr>
				<td width="48%"><div id="labelGeburtsdatum" align="right">Geburtsdatum:</div></td>
				<td width="2%"></td>
				<td width="48%"><strong><bean:write name="vorgang" property="hilfeempfaenger.geburtsdatum"/></strong></td>
			  </tr>
			<logic:notEmpty name="vorgang" property="hilfeempfaenger.sterbedatum">
			  <tr>
				<td width="48%"><div id="labelSterbedatum" align="right">Sterbedatum:</div></td>
				<td width="2%"></td>
				<td width="48%"><strong><bean:write name="vorgang" property="hilfeempfaenger.sterbedatum"/></strong></td>
			  </tr>
			  <tr>
				<td width="48%"><div align="right" id="labelEinrichtung" title="Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen" onMouseOver="status='Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen';return true;" onMouseOut="status='';return true;"><a href="javascript:PopupEinrichtung()">Einrichtung</a>:</div></td>
				<td width="2%"><div id="labelEinrichtungID" align="center"><html:hidden property="hilfeempfaenger.einrichtung.id" styleId="einrichtung.id"/></div></td>
				<td width="48%"><strong><bean:write name="vorgang" property="hilfeempfaenger.einrichtung.name"/></strong></td>
			  </tr>
			</logic:notEmpty>
			  <tr>
				<td width="48%"><div id="labelGA" align="right">gA:</div></td>
				<td width="2%"></td>
				<td width="48%"><strong><bean:write name="vorgang" property="hilfeempfaenger.ga"/></strong></td>
			  </tr>
			  <tr>
				<td width="48%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="48%">&nbsp;</td>
			  </tr>						  					    			  					    		    
			</table>
		</fieldset>
		</div>
<!--==================== Ende Hilfeempfänger ====================-->
<!--==================== Beginn Vertreter ====================-->
		<div id="Step4Vertreter">
		<fieldset>
		<LEGEND ALIGN="left"><bean:write name="vorgang" property="vertreter.bezeichnung"/></LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div id="labelVertreterArt" align="right">Art:</div></td>
				<td width="2%"></td>
				<td width="68%">
				<html:select  name="vorgang" property="vertreter.art" size="1">
					<html:option value="">bitte auswählen:</html:option>			
					<html:optionsCollection name="fb" property="beteiligte"/>
				</html:select>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div id="labelVertreterVorname" align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>				
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div id="labelVertreterNachname" align="right">Nachname:</div></td>
				<td width="2%"></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.nachname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div id="labelVertreterPLZ" align="right">Postleitzahl:</div></td>
				<td width="2%"></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.adresse.postleitzahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.postleitzahl.title"
							styleClass="textPLZ" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div id="labelVertreterWohnort" align="right">Wohnort:</div></td>
				<td width="2%"></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.adresse.ort"  
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
				<td width="25%"><div id="labelAktenzeichen" align="right">Aktenzeichen:</div></td>
				<td width="2%"></td>
				<td width="73%"><strong><bean:write name="vorgang" property="widerspruch.aktenzeichen"/></strong></td>
			  </tr>
			  <tr>
				<td width="25%"><div id="labelAngaben" align="right">Angaben zum Widerspruch:</div></td>
				<td width="2%"></td>
				<td width="73%"><strong><bean:write name="vorgang" property="widerspruch.angaben"/></strong></td>
			  </tr>
			  <tr>
				<td width="25%"><div id="labelVerfahrensart" align="right">Verfahrensart:</div></td>
				<td width="2%"></td>
				<td width="73%"><strong><div id="textVerfahrensart"><bean:write name="vorgang" property="widerspruch.verfahrensart"/></div></strong></td>
			  </tr>
			  <tr>
				<td width="25%"><div id="labelThema" align="right">Gegenstand des Verfahrens:</div></td>
				<td width="2%"></td>
				<td width="73%"><strong><bean:write name="vorgang" property="widerspruch.gegenstandDesVerfahrens"/></strong></td>
			  </tr>
			  <tr>
				<td width="25%"><div id="labelRechtsgebiet" align="right">Rechtsgebiet:</div></td>
				<td width="2%"></td>
				<td width="73%"><strong><bean:write name="vorgang" property="widerspruch.rechtsgebiet"/></strong></td>
			  </tr>
			  <tr>
				<td width="25%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="73%">&nbsp;</td>
			  </tr>
			</table>
		</fieldset>
		</div>
<!--==================== Ende weitere Angaben ====================-->
<!--==================== Beginn Verfahrensverlauf ====================-->
		<div id="Verfahrensverlauf">

		<fieldset>
		<LEGEND ALIGN="left">Verfahrensverlauf</LEGEND>
	<%-- Sofern der in der session liegende Widerspruch einen Verfahrensverlauf aufweist,
		 werden die Ereignisse hier aufgelistet:
	--%>
	<logic:notEmpty name="vorgang" scope="session" property="widerspruch.verfahrensverlauf">
		<ul>
		<logic:iterate id="ereignis" name="vorgang" scope="session" property="widerspruch.verfahrensverlauf">
			<li>
			<bean:write name="ereignis" property="name"/>
			<bean:write name="ereignis" property="datum"/>&nbsp;&nbsp;<em><bean:write name="ereignis" property="kommentar"/></em>
			</li>
		</logic:iterate>
		</ul>
	</logic:notEmpty>

		</fieldset>
	  </div>
<!--==================== Ende Verfahrensverlauf ====================-->

	</div>
	<!--==================== Formular ====================-->	
	<!--==================== Buttons ====================-->	
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div id="labelBackButton" align="left"><html:button property="button" value="&lt;&lt; zurück" styleClass="button" styleId="backButton" onclick="javascript:history.back();"/></div></td>
			<td width="50%"><div id="labelForwardButton" align="right"><html:submit value="l&ouml;schen &gt;&gt;" styleClass="button" styleId="forwardButton" /></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
  </div>
</div>
</html:form>
</body>
</html>
