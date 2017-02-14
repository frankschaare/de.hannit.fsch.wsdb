<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page 	language="java" 
			session="true"
			import="java.util.*"
            autoFlush="true"
			buffer="256kb"
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
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/WindowsFormulare.css">
</head>
<style type="text/css">
<!--
#Erledigungsgrund
{
width:70%;
}
#selectThema
{
width:100%;
}
-->
</style>
<SCRIPT LANGUAGE="javascript">
<!--


function init()
{
var fehler = null;
<html:errors property="KeineBerechtigung"/>

// Tagesdatum für 'Eingangsdatum'
var akt = new Date();
var heute = akt.getDate();
heute = (heute < 9)? "0"+ heute: heute; // ein oder zweistellig?
var monat = akt.getMonth() + 1;         // zaehlt ab 0, also +1
monat = (monat < 9)? "0"+ monat: monat; 
var jahr = akt.getYear();
jahr = (jahr < 1000)?  jahr + 1900: jahr;// muessen 1900 dazu?

var d = document.getElementById("eingangsdatum");
if(d.value.length < 1){d.value=(heute + "." + monat  + "." + jahr);}


var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

document.getElementById("sachgebiet").focus();
if (fehler != null){alert(fehler);}

}

function setVerfahrensart()
{
var va = document.getElementById("verfahrensart");
var id = document.getElementById("verfahrensartID");

	if (id != null)
	{
	va.value=id.options[id.selectedIndex].text;
	}
}

function setGegenstandDesVerfahrens()
{
var g = document.getElementById("gegenstandDesVerfahrens");
var id = document.getElementById("gegenstandDesVerfahrensID");

	if (id != null)
	{
	g.value=id.options[id.selectedIndex].text;
	}
}

function setRechtsgebiet()
{
var r = document.getElementById("rechtsgebiet");
var id = document.getElementById("rechtsgebietID");

	if (id != null)
	{
	r.value=id.options[id.selectedIndex].text;
	}
}

function hinweisDatumsfelder(bezeichner) 
{
alert("Datumsfelder können nur über den Kalender geändert werden ! Klicken Sie dazu links auf den Text.");
}


function getCoordinates(bezeichner) 
{
x = window.event.clientX;
y = window.event.clientY;
PopupKalender(x,y,bezeichner);
}

function PopupKalender(x,y,bezeichner) {
popup = window.open('/WSDB/html/Kalender.html?bezeichner='+bezeichner,"popup","left="+x+",top="+y+",width=200,height=220,dependant=yes,status=no");

// var hidden = popup.document.getElementById("openerElement");
// hidden.value=bezeichner;
}
-->
</SCRIPT>
<noscript>
<h1>Die Widerspruchsdatenbank funktioniert nur, wenn Ihr Browser JavaScript eingeschaltet hat. 
Leider ist JavaScript bei Ihnen ausgeschaltet. Bitte schalten Sie JavaScript ein, oder beenden Sie die Anwendung.
</h1>
</noscript>

<body onLoad="init()">
<html:form action="VorgangErfassenSchritt3" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Vorgang erfassen Schritt 3: Angaben zum Widerspruch
		<div id="HilfeLink"><a class="Kopfzeile" href="<%=request.getContextPath()%>/Dokumentation/Entwickler/122 - VorgangWizard3.htm" target="_blank">?</a></div>	
	</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
		<fieldset>
		<LEGEND ALIGN="left">Angaben zum Widerspruch</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Aktenzeichen:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
					<html:select name="vStep3" property="widerspruch.sachgebiet" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="sachgebiet">
						<html:option value="">........</html:option>	
						<html:optionsCollection name="fb" property="teams" />
					</html:select>
					<html:text 	name="vStep3" property="widerspruch.aktenzeichen" 
								titleKey="label.AZ.title" 
								onfocus="this.style.backgroundColor='#C5D4E0'" 
								onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="widerspruch.sachgebiet"/><html:errors property="widerspruch.aktenzeichen"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('eingangsdatum')">Eingangsdatum:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vStep3"
							property="widerspruch.eingangsdatum" 
							titleKey="label.Geburtsdatum.title" 
							readonly="true"
							styleClass="textDatum" 
							styleId="eingangsdatum"
							ondblclick="hinweisDatumsfelder(dae)"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>			
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Angaben zum Widerspruch:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:textarea 	name="vStep3"
								property="widerspruch.angaben" 
								titleKey="label.Angaben.title" 
								rows="5"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left"><div align="left" class="errors"><html:errors property="widerspruch.angaben"/></div></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Verfahrensart:</div></td>
				<td width="2%">
				<html:hidden property="widerspruch.verfahrensart" styleId="verfahrensart"/>
				</td>
				<td width="68%">
				<html:select name="vorgang" property="widerspruch.verfahrensartID" size="1" titleKey="label.Verfahrensart.title" onchange="setVerfahrensart()">
					<html:option value="">bitte auswählen:</html:option>
					<html:optionsCollection name="fb" property="verfahrensart"/>
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="widerspruch.verfahrensartID"/></div></td>
			  </tr>						
			  <tr>
				<td width="30%"><div align="right">Gegenstand des Verfahrens:</div></td>
				<td width="2%"><div align="center">*</div>
				<html:hidden property="widerspruch.gegenstandDesVerfahrens" styleId="gegenstandDesVerfahrens"/>				
				</td>
				<td width="68%">
				<html:select name="vStep3" property="widerspruch.gegenstandDesVerfahrensID" styleId="selectThema" size="1" titleKey="label.GegenstandDesVerfahrens.title" onchange="setGegenstandDesVerfahrens()">
					<html:option value="">bitte auswählen:</html:option>	
					<html:optionsCollection name="fb" property="themen" />
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="widerspruch.gegenstandDesVerfahrensID"/></div></td>
			  </tr>		
			  <tr>
				<td width="30%"><div align="right">Rechtsgebiet:</div></td>
				<td width="2%"><div align="center">*</div>
				<html:hidden property="widerspruch.rechtsgebiet" styleId="rechtsgebiet"/>						
				</td>
				<td width="68%">
				<html:select name="vStep3" property="widerspruch.rechtsgebietID" size="1" titleKey="label.Anrede.title" onchange="setRechtsgebiet()">
					<html:option value="">bitte auswählen:</html:option>	
					<html:optionsCollection name="fb" property="rechtsgebiet" />
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="widerspruch.rechtsgebietID"/></div></td>
			  </tr>	
<%-- ******************** Die beiden Felder werden zunächst nicht benutzt. ******************** --%>			  
			  <%--tr>
				<td width="30%"><div align="right">erledigt am:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vStep3"
							property="widerspruch.erledigtAm" 
							titleKey="label.Geburtsdatum.title" 
							readonly="false"
							styleClass="textDatum" 
							styleId="erledigtAm"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="showErledigungsgrund()"/>		
				<div id="Erledigungsgrund">&nbsp;durch:&nbsp;<html:select name="vStep3" property="widerspruch.erledigungsgrundID" size="1" titleKey="label.Anrede.title" styleId="selectErledigungsgrund">
					<html:option value="">bitte auswählen:</html:option>	
					<html:optionsCollection name="fb" property="erledigungsgrund" />
				</html:select>					
				</div>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="widerspruch.rechtsgebietID"/></div></td>
			  </tr>	
			  <tr>
				<td width="30%"><div align="right">Anweisung zur Abhilfe am:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vStep3"
							property="widerspruch.anweisungAbhilfe" 
							titleKey="label.Geburtsdatum.title" 
							readonly="false"
							styleClass="textDatum" 
							styleId="erledigtAm"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>		
				</td>
			  </tr--%>
<%-- ******************** Die beiden Felder werden zunächst nicht benutzt. ******************** --%>	
<%-- ******************** Dafür diese beiden Datumsfelder: ******************** --%>			  			  
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('dae')">Datum der angef. Entscheidung:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vStep3"
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
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>				
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('drbh')">Datum Rechtsbehelf:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vStep3"
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
<%-- ******************** Dafür diese beiden Datumsfelder: ******************** --%>			  			  
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>				  
			</table>
		</fieldset>

	</div>
	<!--==================== Formular ====================-->	
	<!--==================== Buttons ====================-->	
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div align="left"><html:button property="button" value="&lt;&lt; zurück" styleClass="button" onclick="location.href='Wizard3Back.do'"/></div></td>
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
