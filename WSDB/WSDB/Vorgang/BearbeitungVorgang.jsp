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
font-size:11px;
}
a.Kopfzeile:link { color:#FFFFFF; text-decoration:none; font-weight:bold; }
a.Kopfzeile:visited { color:#FFFFFF; text-decoration:none; font-weight:bold; }
a.Kopfzeile:hover { color:#FFFFFF; text-decoration:underline; font-weight:bold; }
a.Kopfzeile:active { color:#FFFFFF; text-decoration:none; font-weight:bold; }

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
	top: 35%;
	z-index:2;
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
	cursor:help;
}
fieldset.buttons
{
position:absolute;
padding:3px;
bottom: 3px;
}
input.textLang {width:98%;}
input.textPLZ {width:auto;}
input.textDatum {width:23%;}
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
var fehler = null;
<html:errors property="DatensatzVorhanden"/>

var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

	if (fehler != null)
	{
	alert(fehler);
	document.getElementById("aktenzeichen").focus();
	}
}

/*
* Setze die Bezeichnung des Vertreters im Hidden Field 'vertreter.bezeichnung'
* wenn sich der Wert ses Selects 'vertreter.art' ändert:
*/
function setVertreterBezeichnung()
{
var s = document.getElementById("vertreter.art");
var b = document.getElementById("vertreter.bezeichnung");
var vv = document.getElementById("vertreterVorhanden");

	if (b != null)
	{
	b.value=s.options[s.selectedIndex].text;
	}
	if (s.options[s.selectedIndex].value > 0) 
	{
	vv.value='true';
	document.getElementById("vertreter.vorname").disabled = false;
	document.getElementById("vertreter.nachname").disabled = false;
	document.getElementById("vertreter.adresse.postleitzahl").disabled = false;
	document.getElementById("vertreter.adresse.ort").disabled = false;			
	}
	else
	{
	vv.value='false';
	document.getElementById("vertreter.vorname").disabled = true;
	document.getElementById("vertreter.nachname").disabled = true;
	document.getElementById("vertreter.adresse.postleitzahl").disabled = true;
	document.getElementById("vertreter.adresse.ort").disabled = true;		
	}
}

function getCoordinates(bezeichner) 
{
x = window.event.clientX;
y = window.event.clientY;
PopupKalender(x,y,bezeichner);
}

function PopupKalender(x,y,bezeichner) {
popup = window.open('/WSDB/html/Kalender.html?bezeichner='+bezeichner,"popup","left="+x+",top="+y+",width=200,height=220,dependant=yes,status=no");
var hidden = popup.document.getElementById("openerElement");
hidden.value=bezeichner;
}

function PopupEinrichtung() {
F = window.open("<%= request.getContextPath() %>/ListeEinrichtung.jsp","Popup","left=0,top=0,dependant=yes,status=no");
F.resizeTo(screen.availWidth,screen.availHeight);
}

/*
* Da die Felder Einrichtung disabled sind, können sie nicht gelöscht werden.
* Diese Funktion ermöglicht es dennoch, alle Werte zu reseten
*/
function resetField()
{
check = confirm("Wollen Sie die Werte für Einrichtung und Sterbedatum zurücksetzen ?");
	if (check)
	{
	document.getElementById("SterbeDatum").value="";
	document.getElementById("einrichtung.id").value="";
	document.getElementById("einrichtung.name").value="";
	}
}
-->
</SCRIPT>
<noscript>
<h1>Die Widerspruchsdatenbank funktioniert nur, wenn Ihr Browser JavaScript eingeschaltet hat. 
Leider ist JavaScript bei Ihnen ausgeschaltet. Bitte schalten Sie JavaScript ein, oder beenden Sie die Anwendung.
</h1>
</noscript>

<body onLoad="init()">

<html:form action="VorgangErfassenSchritt4" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Vorgang <bean:write name="vorgang" property="widerspruch.aktenzeichen"/>&nbsp;(<bean:write name="vorgang" property="hilfeempfaenger.nachname"/>): Stammdaten bearbeiten
	<div id="HilfeLink"><a class="Kopfzeile" href="<%=request.getContextPath()%>/Dokumentation/Entwickler/142 - VorgangBearbeiten.htm" target="_blank">?</a></div>
	</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
<!--==================== Beginn Hilfeempfänger ====================-->
	<div id="Step4Hilfeempfänger">
	<fieldset>
		<LEGEND ALIGN="left">Hilfeempfänger</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
<%--==================== Aktionssteuerung ====================
	Die JSP wird für neue Vorgänge und Änderungen benutzt. CREATE prüft, ob bereits ein gleiches Aktenzeichen
	vorhanden ist, UPDATE nicht. Ob CREATE oder UPDATE ansteht, entscheidet der im folgendem Hidden übergebene
	Parameter: 
==================== Aktionssteuerung ====================--%>
				<td width="2%">
				<html:hidden name="vorgang" property="aktion" styleId="hiddenAktion"/>
				<html:hidden name="vorgang" property="widerspruch.id" styleId="hiddenAktion"/>				
				<html:hidden name="vorgang" property="hilfeempfaenger.id" styleId="hiddenAktion"/>			
				<html:hidden name="vorgang" property="hilfeempfaenger.adresse.id" styleId="hiddenAktion"/>				
				</td>
				<td width="68%">
				<html:text 	name="vorgang"
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
				<html:text 	name="vorgang"
							property="hilfeempfaenger.nachname"
							titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				<div align="left" class="errors"><html:errors property="hilfeempfaenger.nachname"/></div>
				</td>
			  </tr>

			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="vorgang"
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
				<html:text 	name="vorgang"
							property="hilfeempfaenger.adresse.ort" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.ort.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				<div align="left" class="errors"><html:errors property="hilfeempfaenger.adresse.ort"/></div>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Geburtsdatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="hilfeempfaenger.geburtsdatum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.geburtsdatum.title" 
							styleClass="textDatum" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('SterbeDatum')">Sterbedatum:</div></a></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="hilfeempfaenger.sterbedatum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.sterbedatum.title" 
							styleId="SterbeDatum"
							styleClass="textDatum"
							onfocus="this.style.backgroundColor='#C5D4E0'"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right" id="labelEinrichtung" title="Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen" onMouseOver="status='Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen';return true;" onMouseOut="status='';return true;"><a href="javascript:PopupEinrichtung()">Einrichtung</a>:</div></td>
				<td width="2%"><div align="center"><html:hidden name="vorgang" property="hilfeempfaenger.einrichtung.id" styleId="einrichtung.id"/></div></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="hilfeempfaenger.einrichtung.name" 
							titleKey="WiderspruchErfassen.einrichtung.name.title" 
							styleClass="textLang"
							readonly="false"
							styleId="einrichtung.name"
							ondblclick="resetField()"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">gA:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:select name="vorgang" property="hilfeempfaenger.gaID" size="1" titleKey="label.GA.title">
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
		<LEGEND ALIGN="left"><bean:write name="vorgang" property="vertreter.bezeichnung"/></LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Art:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:hidden name="vorgang" property="vertreter.id" styleId="vertreterID"/>
				<html:hidden name="vorgang" property="vertreterVorhanden" styleId="vertreterVorhanden"/>				
				<html:hidden name="vorgang" property="vertreter.bezeichnung" styleId="vertreter.bezeichnung"/>								
				<html:select name="vorgang" property="vertreter.art" size="1" styleId="vertreter.art" onchange="setVertreterBezeichnung()">
					<html:option value="">bitte auswählen:</html:option>			
					<html:optionsCollection name="fb" property="beteiligte"/>
				</html:select>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="vertreter.vorname"							
							disabled="false"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>				
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.nachname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
							styleClass="textLang"
							styleId="vertreter.nachname"							
							disabled="false"							
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.adresse.postleitzahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.postleitzahl.title"
							styleClass="textPLZ" 
							styleId="vertreter.adresse.postleitzahl"
							disabled="false"							
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Wohnort:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="vertreter.adresse.ort"  
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.ort.title" 
							styleClass="textLang"
							styleId="vertreter.adresse.ort"
							disabled="false"							
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
					<html:text 	name="vorgang"
								property="widerspruch.aktenzeichen" 
								titleKey="label.AZ.title" 
								styleId="aktenzeichen"
								onfocus="this.style.backgroundColor='#C5D4E0'" 
								onblur="this.style.backgroundColor='#FFFFFF'"/>
					<div align="left" class="errors"><html:errors property="widerspruch.aktenzeichen"/></div>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('eingangsdatum')">Eingangsdatum:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="widerspruch.eingangsdatum" 
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
				<html:textarea 	name="vorgang"
								property="widerspruch.angaben" 
								titleKey="label.Angaben.title" 
								rows="5"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Verfahrensart:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="vorgang" property="widerspruch.verfahrensartID" size="1" titleKey="label.Verfahrensart.title">
					<html:option value="">bitte auswählen:</html:option>
					<html:optionsCollection name="fb" property="verfahrensart"/>
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Gegenstand des Verfahrens:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="vorgang" property="widerspruch.gegenstandDesVerfahrensID" size="1" titleKey="label.GegenstandDesVerfahrens.title">
					<html:optionsCollection name="fb" property="themen" />
				</html:select>	
				<div align="left" class="errors"><html:errors property="widerspruch.gegenstandDesVerfahrensID"/></div>
				</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Rechtsgebiet:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="vorgang" property="widerspruch.rechtsgebietID" size="1" titleKey="label.Anrede.title">
					<html:optionsCollection name="fb" property="rechtsgebiet" />
				</html:select>	
				<div align="left" class="errors"><html:errors property="widerspruch.rechtsgebietID"/></div>
				</td>
			  </tr>
<%-- ******************** Dafür diese beiden Datumsfelder: ******************** --%>			  			  
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('dae')">Datum der angef. Entscheidung:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="widerspruch.dae" 
							titleKey="label.Geburtsdatum.title" 
							readonly="true"
							styleClass="textDatum" 
							styleId="dae"
							ondblclick="hinweisDatumsfelder(dae)"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('drbh')">Datum Rechtsbehelf:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vorgang"
							property="widerspruch.datumrbh" 
							titleKey="label.Geburtsdatum.title" 
							readonly="true"
							styleClass="textDatum" 
							styleId="drbh"
							ondblclick="hinweisDatumsfelder(drbh)"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>		
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
			<td width="50%"><div align="right"><html:submit value="speichern &gt;&gt;" styleClass="button"/></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
	</div>
</div>
</html:form>
</body>
</html>
