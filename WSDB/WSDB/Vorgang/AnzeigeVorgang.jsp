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
<title>Widerspruch erfassen</title>

</head>
<style type="text/css">
<!--
div, input, select, textarea, td
{
font-family:Tahoma, Verdana, Arial, sans-serif;
font-size:12px;
}
a.Kopfzeile:link { color:#FFFFFF; text-decoration:none; font-weight:bold; }
a.Kopfzeile:visited { color:#FFFFFF; text-decoration:none; font-weight:bold; }
a.Kopfzeile:hover { color:#FFFFFF; text-decoration:underline; font-weight:bold; }
a.Kopfzeile:active { color:#FFFFFF; text-decoration:none; font-weight:bold; }

a.Verfahrensverlauf:link { color:black; text-decoration:none;}
a.Verfahrensverlauf:visited { color:black; text-decoration:none;}
a.Verfahrensverlauf:hover { color:black; background-color:#FFFF00; text-decoration:underline; font-weight:bold;} 
a.Verfahrensverlauf:active { color:black; text-decoration:none;}

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
#HilfeLink
{
	position:absolute;
	font-size:12px;
	font-weight:bold;
	text-align:center;
	width:30px;
	height:100%;
	top: 0px;
	right: 0px;
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
width:200px;
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
document.getElementById("Ereignis.datum").value = "";

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

/*
* Handelt den Zurückbutton. 
* Diese Funktion wurde in den Scriptbereich verlegt, weil das dynamische generieren der Aktion 
* wegen mehrerer verschachtelte Anführungszeichen nicht so recht funktionierte.
*/
function doBackButton()
{
var backID = <bean:write name="vorgang" property="widerspruch.id"/>;
var backAktion = 'BearbeitungVorgang.do?id='+backID;
window.location.href = backAktion;
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

<html:form action="SpeichernEreignis" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Vorgang <bean:write name="vorgang" property="widerspruch.aktenzeichen"/>&nbsp;(<bean:write name="vorgang" property="hilfeempfaenger.nachname"/>) anzeigen und Verfahrensverlauf bearbeiten 
		<div id="HilfeLink"><a class="Kopfzeile" href="<%=request.getContextPath()%>/Dokumentation/Entwickler/141 - AnzeigeVorgang.htm" target="_blank">?</a></div>	
	</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
<!--==================== Beginn Hilfeempfänger ====================-->
	<div id="Step4Hilfeempfänger">
	<fieldset>
		<LEGEND ALIGN="left">Hilfeempfänger (<bean:write name="vorgang" property="hilfeempfaenger.id"/>)</LEGEND>
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
				<td width="2%"><div id="labelEinrichtungID" align="center"><html:hidden name="vorgang" property="hilfeempfaenger.einrichtung.id" styleId="einrichtung.id"/></div></td>
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
				<td width="30%"><div id="labelVertreterVorname" align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><strong><bean:write name="vorgang" property="vertreter.vorname"/></strong></td>
			  </tr>
			  <tr>
				<td width="30%"><div id="labelVertreterNachname" align="right">Nachname:</div></td>
				<td width="2%"></td>
				<td width="68%"><strong><bean:write name="vorgang" property="vertreter.nachname"/></strong></td>
			  </tr>
			  <tr>
				<td width="30%"><div id="labelVertreterPLZ" align="right">Postleitzahl:</div></td>
				<td width="2%"></td>
				<td width="68%"><strong><bean:write name="vorgang" property="vertreter.adresse.postleitzahl"/></strong></td>
			  </tr>
			  <tr>
				<td width="30%"><div id="labelVertreterWohnort" align="right">Wohnort:</div></td>
				<td width="2%"></td>
				<td width="68%"><strong><bean:write name="vorgang" property="vertreter.adresse.ort"/></strong></td>
			  </tr>
			  <tr>
				<td width="48%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="48%">&nbsp;</td>
			  </tr>		
			  <tr>
				<td width="48%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="48%">&nbsp;</td>
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
				<td width="25%"><div id="labeldae" align="right">Datum d.a.Entscheidung:</div></td>
				<td width="2%"></td>
				<td width="73%"><strong><bean:write name="vorgang" property="widerspruch.dae"/></strong></td>
			  </tr>		
			  <tr>
				<td width="25%"><div id="labeldatumrbh" align="right">Datum Rechtsbehelf:</div></td>
				<td width="2%"></td>
				<td width="73%"><strong><bean:write name="vorgang" property="widerspruch.datumrbh"/></strong></td>
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
		<%--------------- Verstecktes Feld Ereignis.datum -------------------%>
		<html:hidden property="datum" styleId="Ereignis.datum"/>
		<%--------------- Verstecktes Feld Ereignis.datum -------------------%>
		<%--------------- Verstecktes Feld Ereignis.kommentar ---------------%>
		<html:hidden property="kommentar" styleId="Ereignis.kommentar"/>
		<%--------------- Verstecktes Feld Ereignis.kommentar ---------------%>
		<fieldset>
		<LEGEND ALIGN="left">Verfahrensverlauf</LEGEND>
	<%-- Sofern der in der session liegende Widerspruch einen Verfahrensverlauf aufweist,
		 werden die Ereignisse hier aufgelistet:
	--%>
	<logic:notEmpty name="vorgang" scope="session" property="widerspruch.verfahrensverlauf">
		<ul>
		<logic:iterate id="ereignis" name="vorgang" scope="session" property="widerspruch.verfahrensverlauf">
			<li>
			<a class="Verfahrensverlauf" href="CRUDEreignis.do?crud=2&loaded=false&id=<bean:write name="ereignis" property="id"/>" target="_self" alt="Klicken Sie hier, um das Ereignis zu ändern, oder zu löschen.">
			<bean:write name="ereignis" property="name"/>
			<bean:write name="ereignis" property="datum"/>&nbsp;&nbsp;<em><bean:write name="ereignis" property="kommentar"/></em>
			</a>
			</li>
		</logic:iterate>
		</ul>
	</logic:notEmpty>
	<html:select property="name" styleId="selectEreignis" onchange="showEreignis(this.value)">
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
				Datum:
				<html:text 	property="datum" 
							titleKey="label.AZ.title" 
							styleId="eingangsdatum"
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
							styleClass="textLang" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="setKommentar(this)"/>

		</div>
	</logic:iterate>

		</fieldset>
	  </div>
<!--==================== Ende Verfahrensverlauf ====================-->

	</div>
	<!--==================== Formular ====================-->	
	<!--==================== Buttons ====================-->	
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div id="labelBackButton" align="left"><html:button property="button" value="&lt;&lt; ändern" styleClass="button" styleId="backButton" onclick="doBackButton()"/></div></td>
			<td width="50%"><div id="labelForwardButton" align="right"><html:button property="button" value="speichern &gt;&gt;" styleClass="button" styleId="forwardButton" onclick="checkEreignis()"/></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
  </div>
</div>
</html:form>
</body>
</html>
