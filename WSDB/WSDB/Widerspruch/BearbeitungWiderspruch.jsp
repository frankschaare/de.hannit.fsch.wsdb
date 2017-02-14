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

div, input, select, td
{
font-family:Tahoma, Verdana, Arial, sans-serif;
font-size:12px;
}
div.Ereignis {display:none;}

li{list-style-type:decimal;}
td.Wert{font-weight:bold;}
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
position:absolute; 
width:98%; 
height:auto; 
left: 1%; 
top: 30%;
z-index:1;
}
#Ereignisse
{
border: 1px solid lightgrey;
position:absolute; 
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
-->
</style>


</head>

<SCRIPT LANGUAGE="javascript">
<!--
function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}
}

/*
* Schaltet alle EreignisDivs aus und blendet das ausgewählte ein.
*/
function showEreignis(EreignisID)
{
	for(var i = 0; i <= document.getElementsByTagName("div").length; i++)
	{
	var DivElement = document.getElementsByTagName("div")[i];
	var DivID = DivElement.id;
		if(DivID.indexOf("EreignisDiv") != -1)
		{
			if(DivID.indexOf("EreignisDiv"+EreignisID) != -1)
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

-->
</SCRIPT>
<noscript>
<h1>Die Widerspruchsdatenbank funktioniert nur, wenn Ihr Browser JavaScript eingeschaltet hat. 
Leider ist JavaScript bei Ihnen ausgeschaltet. Bitte schalten Sie JavaScript ein, oder beenden Sie die Anwendung.
</h1>
</noscript>

<body onLoad="init()">

<div id="Dokument">
	<!--==================== Beginn Hilfeempfänger ====================-->
	<div id="Hilfeempfänger">
		<fieldset>
		<LEGEND ALIGN="left">Hilfeempfänger</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">&nbsp;</div></td>
				<td width="2%"><div align="center">&nbsp;</div></td>
				<td width="68%">&nbsp;</td>
			  </tr>					
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.vorname"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.nachname"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.adresse.postleitzahl"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Wohnort:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.adresse.ort"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Geburtsdatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.geburtsdatum"/></td>
			  </tr>
			<logic:notEmpty name="sessionWS" scope="session" property="hilfeempfaenger.sterbedatum">
			  <tr>
				<td width="30%"><div align="right">Sterbedatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.sterbedatum"/></td>
			  </tr>
			</logic:notEmpty>			  
			  <tr>
				<td width="30%"><div align="right">&nbsp;</div></td>
				<td width="2%"><div align="center">&nbsp;</div></td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			</table>
		</fieldset>
	</div>
	<!--==================== Ende Hilfeempfänger ====================-->	
	<div id="Betreffzeile">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td width="33%"><div align="center">Aktenzeichen</div></td>
		<td width="33%"><div align="center"></div></td>
		<td width="33%"><div align="center">Eingangsdatum</div></td>
	  </tr>
	  <tr>
		<td><div align="center"><strong><bean:write name="sessionWS" scope="session" property="aktenzeichen"/></strong></div>
		</td>
		<td>&nbsp;</td>
		<td><div align="center"><strong><bean:write name="sessionWS" scope="session" property="eingangsdatum"/></strong></div></td>
	  </tr>
	  <tr>
		<td></td>
		<td></td>
		<td></td>
	  </tr>
	</table>
	</div>

	<!--==================== Beginn DIV Verfahrensverlauf ====================-->
	<div id="Ereignisse">
	<html:form action="SpeichernEreignis" target="main">
	<%--------------- Verstecktes Feld Ereignis.datum -------------------%>
	<html:hidden property="datum" styleId="Ereignis.datum"/>
	<%--------------- Verstecktes Feld Ereignis.datum -------------------%>
	<%--------------- Verstecktes Feld Ereignis.kommentar ---------------%>
	<html:hidden property="kommentar" styleId="Ereignis.kommentar"/>
	<%--------------- Verstecktes Feld Ereignis.kommentar ---------------%>

	<fieldset>
	<legend align="left">Verfahrensverlauf</legend>

	<%-- Sofern der in der session liegende Widerspruch einen Verfahrensverlauf aufweist,
		 werden die Ereignisse hier aufgelistet:
	--%>
	<logic:notEmpty name="sessionWS" scope="session" property="verfahrensverlaufAsCollection">
		<ul>
		<logic:iterate id="ereignis" name="sessionWS" scope="session" property="verfahrensverlaufAsCollection">
			<li>
			<bean:write name="ereignis" property="name"/>
			<bean:write name="ereignis" property="datum"/>&nbsp;&nbsp;<em><bean:write name="ereignis" property="kommentar"/></em>
			</li>
		</logic:iterate>
		</ul>
	</logic:notEmpty>

	<html:select property="name" onchange="showEreignis(this.value)">
		<html:option value="">Ereignis hinzufügen...</html:option>
		<html:optionsCollection name="fb" property="ereignis" value="id" label="name" />
	</html:select>
	<%-- Die in der Formularbean eingelesenen Ereignisse werden durchlaufen. Für jedes Ereignis 
		 wird ein unsichtbares DIV generiert, welches bei Bedarf eingeblendet wird.
		 Struts hat aber Schwierigkeiten mit mehreren gleichen Properties, also werden die Feldwerte in
		 versteckten Feldern übergeben.	
	--%>
	<logic:iterate id="ereignis" name="fb" property="ereignis">

		<div id="EreignisDiv<bean:write name="ereignis" property="id"/>" class="Ereignis">
			<logic:match name="ereignis" property="datumsFeld" value="true">
				<html:text 	property="datum" 
							titleKey="label.AZ.title" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="setDatum(this)"/>			
			</logic:match>
	
			<logic:notEmpty name="ereignis" property="hilfstabelle">
				<html:select property="datum" size="1" titleKey="label.Anrede.title" styleClass="sachgebiet">
					<html:option value="">........</html:option>	
					<html:optionsCollection name="ereignis" property="hilfstabelle" />
				</html:select>			
			</logic:notEmpty>
			Kommentar:
				<html:text 	property="kommentar" 
							titleKey="label.AZ.title" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="setKommentar(this)"/>
				<html:submit>speichern</html:submit>
			</div>
	</logic:iterate>
	  </fieldset>
  </html:form>
</div>
	<!--==================== Ende DIV Verfahrensverlauf ====================-->
</div>
<div id="Schatten"></div>

</body>
</html>
