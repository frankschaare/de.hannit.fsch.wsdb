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
var st = document.getElementById("SterbeDatum");
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

	if (st != null)
	{
	showEinrichtung();
	}
document.getElementById("hilfeempfaenger.vorname").focus();
}
function PopupEinrichtung() {
F = window.open("<%= request.getContextPath() %>/ListeEinrichtung.jsp","Popup","left=0,top=0,dependant=yes,status=no");
F.resizeTo(screen.availWidth,screen.availHeight);
}
/*
* Abhängig vom Feld Sterbedatum werden Controls für die Einrichtung ein-/ausgeblendet
*/
function showEinrichtung()
{
var t = document.getElementById("SterbeDatum");
var i = document.getElementById("einrichtung.id");
var e = document.getElementById("einrichtung.name");
var l = document.getElementById("labelEinrichtung");

	// Wenn das Textfeld Sterbedatum nicht leer ist, zeige Label und Textfeld Einrichtung:
	if(t.value.length > 0)
	{
	l.style.display="block";
	e.style.display="block";
		// Wenn das Textfeld Einrichtung noch leer ist, zeige ListeEinrichtung.jsp:
		if(e.value.length == 0)
		{
		PopupEinrichtung();
		}
	}
	// Wenn das Textfeld Sterbedatum leer ist, blende Label und Textfeld Einrichtung aus:
	else 
	{
	l.style.display="none";
	i.value="";
	e.value="";
	e.style.display="none";
	}
t.style.backgroundColor='#FFFFFF';
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
	document.getElementById("einrichtung.name").style.display="none";
	document.getElementById("labelEinrichtung").style.display="none";
	}
}

function setFocusToNachname(control)
{
control.style.backgroundColor='#FFFFFF';
document.getElementById("hilfeempfaenger.nachname").focus();
}

/*
* Prüft, ob das eingegebene Datum gültig ist.
* Wenn der Wert keine Punkte enthält, wird versucht, das Datum zu parsen.
*/
function checkDatum(control)
{
var datum = control.value;
var datumLaenge = datum.length;

if (datumLaenge > 10)
{
alert("Fehler beim Datumsformat. Das Datum höchstens 10 Stellen haben.");
}
else
{
	switch(datumLaenge) 
	{
	 case 4:
	 control.value = datum.slice(0,1)+"."+datum.slice(1,2)+"."+datum.slice(2,datumLaenge);
	 break;
	 case 5:
	 alert("Fehler beim Datumsformat. Bitte geben Sie das Datum wie folgt ein:'tt.mm.yyyy'");
	 break;
	 default:
		if (datum.indexOf(".") != -1)		//Wert enthält Punkte
		{
		}
		else								//Wert enthält keine Punkte
		{
			if(datumLaenge > 1)
			{
			var tag = datum.slice(0,2);
			var monat = datum.slice(2,4);
			var jahr = datum.slice(4,datum.length); 
			control.value = tag+"."+monat+"."+jahr;
			}
		}
	 break;
	}
control.style.backgroundColor='#FFFFFF';	
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
<html:form action="VorgangErfassenSchritt1" target="main">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Widerspruch erfassen Schritt 1: Hilfeempfänger erfassen</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
		<fieldset>
		<LEGEND ALIGN="left">Hilfeempfänger</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%">
				<html:text 	name="wsForm"
							property="hilfeempfaenger.vorname" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.vorname.title" 
							styleClass="textLang"
							styleId="hilfeempfaenger.vorname"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="setFocusToNachname(this)"/>				
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="wsForm"
							property="hilfeempfaenger.nachname"
							titleKey="WiderspruchErfassen.hilfeempfaenger.nachname.title" 
							styleClass="textLang"
							styleId="hilfeempfaenger.nachname"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="hilfeempfaenger.nachname"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="wsForm"
							property="hilfeempfaenger.adresse.postleitzahl" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.postleitzahl.title"
							styleClass="textPLZ" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><html:errors property="hilfeempfaenger.adresse.postleitzahl"/></td>
			  </tr>						
			  <tr>
				<td width="30%"><div align="right">Wohnort:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="wsForm"
							property="hilfeempfaenger.adresse.ort" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.adresse.ort.title" 
							styleClass="textLang"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="hilfeempfaenger.adresse.ort"/></div></td>
			  </tr>		
			  <tr>
				<td width="30%"><div align="right">Geburtsdatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="wsForm"
							property="hilfeempfaenger.geburtsdatum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.geburtsdatum.title" 
							styleClass="textDatum" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="checkDatum(this)"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><html:errors property="hilfeempfaenger.geburtsdatum"/></td>
			  </tr>		
			  <tr>
				<td width="30%"><div align="right">Sterbedatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="wsForm"
							property="hilfeempfaenger.sterbedatum" 
							titleKey="WiderspruchErfassen.hilfeempfaenger.sterbedatum.title" 
							styleId="SterbeDatum"
							styleClass="textDatum"
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="showEinrichtung()"/>		
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><html:errors property="hilfeempfaenger.sterbedatum"/></td>
			  </tr>					
			  <tr>
				<td width="30%"><div align="right" id="labelEinrichtung" title="Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen" onMouseOver="status='Klicken Sie hier, um die Liste der Einrichtungen erneut aufzurufen';return true;" onMouseOut="status='';return true;"><a href="javascript:PopupEinrichtung()">Einrichtung</a>:</div></td>
				<td width="2%"><div align="center"><html:hidden property="einrichtung.id" styleId="einrichtung.id"/></div></td>
				<td width="68%">
				<html:text 	name="wsForm"
							property="einrichtung.name" 
							titleKey="WiderspruchErfassen.einrichtung.name.title" 
							styleClass="textLang"
							readonly="true"
							styleId="einrichtung.name"
							ondblclick="resetField()"/>	
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">gew&ouml;hnlicher Aufenthalt:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:select name="wsForm" property="hilfeempfaenger.ga" size="1" titleKey="label.GA.title">
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
	<!--==================== Formular ====================-->	
	<!--==================== Buttons ====================-->	
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div align="left"><html:button property="button" value="Abbrechen" styleClass="button" onclick="javascript:history.back();"/></div></td>
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
