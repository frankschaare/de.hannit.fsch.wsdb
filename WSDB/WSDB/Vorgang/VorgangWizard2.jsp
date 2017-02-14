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
<meta http-equiv="cache-control" content="private">
<title>Widerspruch erfassen</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/WindowsFormulare.css">
</head>

<SCRIPT LANGUAGE="javascript">
<!--
function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

document.getElementById("vertreter.art").focus();
}

/*
* Setze die Bezeichnung des Vertreters im Hidden Field 'vertreter.bezeichnung'
* wenn sich der Wert ses Selects 'vertreter.art' ändert:
*/
function setVertreterBezeichnung()
{
var s = document.getElementById("vertreter.art");
var b = document.getElementById("vertreter.bezeichnung");

	if (b != null)
	{
	b.value=s.options[s.selectedIndex].text;
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

<html:form action="VorgangErfassenSchritt2" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Vorgang erfassen Schritt 2: Verfahrensführer erfassen
		<div id="HilfeLink"><a class="Kopfzeile" href="<%=request.getContextPath()%>/Dokumentation/Entwickler/121 - VorgangWizard2.htm" target="_blank">?</a></div>	
	</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
		<fieldset>
		<LEGEND ALIGN="left"><div id="legendVertreter">Verfahrensführer</div></LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Art:</div></td>
				<td width="2%"><div align="center"><html:hidden name="vStep2" property="vertreter.bezeichnung" styleId="vertreter.bezeichnung"/></div></td>
				<td width="68%">
				<html:select name="vStep2" property="vertreter.art" size="1" styleId="vertreter.art" onchange="setVertreterBezeichnung()">
					<html:option value="">bitte auswählen:</html:option>			
					<html:optionsCollection name="fb" property="beteiligte"/>
				</html:select>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="vStep2"
							property="vertreter.vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>				
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="vertreter.vorname"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="vStep2"
							property="vertreter.nachname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="vertreter.nachname"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="vStep2"
							property="vertreter.adresse.postleitzahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.postleitzahl.title"
							styleClass="textPLZ" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="vertreter.adresse.postleitzahl"/></div></td>
			  </tr>						
			  <tr>
				<td width="30%"><div align="right">Wohnort:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="vStep2"
							property="vertreter.adresse.ort"  
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.ort.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="vertreter.adresse.ort"/></div></td>
			  </tr>	
			</table>
		</fieldset>
		<%--
		Enumeration e = request.getHeaderNames();
			while (e.hasMoreElements())
			{
			String name = (String) e.nextElement();
			String value = request.getHeader(name);
			out.print(name +" = "+value+"<br>");
			}
		--%>	
	</div>
	<!--==================== Formular ====================-->	
	<!--==================== Buttons ====================-->	
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div align="left"><html:button property="button" value="&lt;&lt; zurück" styleClass="button" onclick="location.href='Wizard2Back.do'"/></div></td>
			<td width="50%"><div align="right"><html:submit value="weiter &gt;&gt;" styleClass="button"/></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
	</div>
</div>
</html:form>
</body>
</html>
