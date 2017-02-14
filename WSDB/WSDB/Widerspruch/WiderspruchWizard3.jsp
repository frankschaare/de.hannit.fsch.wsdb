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
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/WindowsFormulare.css">
</head>

<SCRIPT LANGUAGE="javascript">
<!--
function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

document.getElementById("sachgebiet").focus();
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
<html:form action="WiderspruchErfassenSchritt3" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Widerspruch erfassen Schritt 3: weitere Angaben zum Widerspruch</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
		<fieldset>
		<LEGEND ALIGN="left">weitere Angaben zum Widerspruch</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Aktenzeichen:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
					<html:select name="sessionWS" property="sachgebiet" size="1" titleKey="label.Sachgebiet.title" styleClass="Sachgebiet" styleId="sachgebiet">
						<html:option value="">........</html:option>	
						<html:optionsCollection name="fb" property="teams" />
					</html:select>
					<html:text 	name="sessionWS" property="aktenzeichen" 
								titleKey="label.AZ.title" 
								onfocus="this.style.backgroundColor='#C5D4E0'" 
								onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="sachgebiet"/><html:errors property="aktenzeichen"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><a href="javascript:PopupKalender()"><div align="right" onClick="getCoordinates()">Eingangsdatum:</div></a></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="fb"
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
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
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
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left"></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Verfahrensart:</div></td>
				<td width="2%"><div align="center">*</div>
				<html:hidden property="verfahrensart" styleId="verfahrensart"/>
				</td>
				<td width="68%">
				<html:select name="sessionWS" property="verfahrensartID" size="1" titleKey="label.Verfahrensart.title" onchange="setVerfahrensart()">
					<html:option value="">bitte auswählen:</html:option>
					<html:optionsCollection name="fb" property="verfahrensart"/>
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="verfahrensartID"/></div></td>
			  </tr>						
			  <tr>
				<td width="30%"><div align="right">Gegenstand des Verfahrens:</div></td>
				<td width="2%"><div align="center">*</div>
				<html:hidden property="gegenstandDesVerfahrens" styleId="gegenstandDesVerfahrens"/>				
				</td>
				<td width="68%">
				<html:select name="sessionWS" property="gegenstandDesVerfahrensID" size="1" titleKey="label.GegenstandDesVerfahrens.title" onchange="setGegenstandDesVerfahrens()">
					<html:option value="">bitte auswählen:</html:option>	
					<html:optionsCollection name="fb" property="themen" />
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="gegenstandDesVerfahrensID"/></div></td>
			  </tr>		
			  <tr>
				<td width="30%"><div align="right">Rechtsgebiet:</div></td>
				<td width="2%"><div align="center">*</div>
				<html:hidden property="rechtsgebiet" styleId="rechtsgebiet"/>						
				</td>
				<td width="68%">
				<html:select name="sessionWS" property="rechtsgebietID" size="1" titleKey="label.Anrede.title" onchange="setRechtsgebiet()">
					<html:option value="">bitte auswählen:</html:option>	
					<html:optionsCollection name="fb" property="rechtsgebiet" />
				</html:select>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="rechtsgebietID"/></div></td>
			  </tr>		
			</table>
		</fieldset>

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
