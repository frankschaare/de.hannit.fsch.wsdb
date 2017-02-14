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

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/Standard.css">

</head>

<SCRIPT LANGUAGE="javascript">
<!--

/*
* Sorgt dafür, dass die Seite beim erneuten Laden (nach erfolgloser Validierung) so aussieht,
* wie der Benutzer sie abgeschickt hat. Geprüft werden die Felder Vertreter und Sterbedatum.
*/
function init()
{
var st = document.getElementById("SterbeDatum");
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

	if (st != null)
	{
	showEinrichtung();
	}

// Vertreter checked ? Wenn ja, DIV "vertreterVorhanden" einblenden:
showVertreter();
}

function PopupEinrichtung() {
F = window.open("<%= request.getContextPath() %>/ListeEinrichtung.jsp","Popup","left=0,top=0,dependant=yes,status=no");
F.resizeTo(screen.availWidth,screen.availHeight);
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

function showVertreter()
{
var v = document.getElementById("Vertreter");
var c = document.getElementById("vertreterVorhanden");
	if(c.checked){v.style.display="block";}
	else {v.style.display="none";}
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
-->
</SCRIPT>
<noscript>
<h1>Die Widerspruchsdatenbank funktioniert nur, wenn Ihr Browser JavaScript eingeschaltet hat. 
Leider ist JavaScript bei Ihnen ausgeschaltet. Bitte schalten Sie JavaScript ein, oder beenden Sie die Anwendung.
</h1>
</noscript>

<body onLoad="init()">
<html:form action="/WiderspruchErfassenSubmit">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div align="center"><strong>Widerspruch erfassen</strong></div></td>
  </tr>
  <tr>
    <td>
	<!-- ======================== Beginn Tabelle Hilfeempfänger======================== -->
    <%@ include file="Hilfeempfaenger.jsp"%>	
    <!-- ======================== Ende Tabelle Hilfeempfänger======================== -->	
	</td>

  </tr>
  <tr>
    <td class="bottom">Widerspruch</td>
  </tr>  
  <tr>
    <td>
	<!-- ======================== Beginn Tabelle Widerspruch ======================== -->			
	<table width="100%"  border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<td width="24%"><div align="right">Aktenzeichen:</div></td>
		<td width="2%"><div align="center">*</div></td>
		<td width="24%">
			<html:select property="sachgebiet" size="1" titleKey="label.Anrede.title" styleClass="sachgebiet">
				<html:option value="">........</html:option>	
				<html:optionsCollection name="fb" property="teams" />
			</html:select><html:text 	property="aktenzeichen" 
						titleKey="label.AZ.title" 
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>	</td>
		<td width="24%"><a href="javascript:PopupKalender()"><div align="right" onClick="getCoordinates()">Eingangsdatum:</div></a></td>
		<td width="2%"></td>
		<td width="24%">	
			<html:text 	name="fb"
						property="eingangsdatum" 
						titleKey="label.Geburtsdatum.title" 
						readonly="true"
						styleClass="DatumDefault" 
						styleId="eingangsdatum"
						onfocus="this.style.backgroundColor='#C5D4E0'" 
						onblur="this.style.backgroundColor='#FFFFFF'"/>
		</td>
	  </tr>
	  <tr>
		<td colspan="3"><div align="center"><html:errors property="aktenzeichen"/></div></td>
		<td colspan="3"><div align="center"><html:errors property="eingangsdatum"/></div></td>
	  </tr>	 
	  <tr>
		<td width="24%"><div align="right">Angaben zum Widerspruch:</div></td>
		<td width="2%"><div align="center">*</div></td>
		<td colspan="4"><div align="left">
				<html:text 	property="angaben" 
							styleClass="Angaben"  
							titleKey="label.Angaben.title" 
							onfocus="this.style.backgroundColor='#C5D4E0'" 
							onblur="this.style.backgroundColor='#FFFFFF'"/>	</div></td>
		</tr>	 
	  <tr>
		<td colspan="3"><div align="center"><html:errors property="angaben"/></div></td>
		<td colspan="3"><div align="center"></div></td>
	  </tr>	
	  <tr>
		<td width="24%"><div align="right">Verfahrensart:</div></td>
		<td width="2%"><div align="center">*</div></td>
		<td width="24%">	
			<html:select name="benutzer" property="formelleAnrede" size="1" titleKey="label.Anrede.title">
				<html:option value="bitte auswählen:" key="null"></html:option>
				<html:optionsCollection name="fb" property="verfahrensart"/>
			</html:select>			
		</td>
		<td width="24%"><div align="right">Thema:</div></td>
		<td width="2%"><div align="center">*</div></td>
		<td width="24%">
			<html:select name="benutzer" property="userID" size="1" titleKey="label.Anrede.title">
				<html:option value="">bitte auswählen:</html:option>	
				<html:optionsCollection name="fb" property="themen" />
			</html:select>		
		</td>
	  </tr>	 
	  <tr>
		<td colspan="3"><div align="center"></div></td>
		<td colspan="3"><div align="center"></div></td>
	  </tr>		   	   

	  <tr>
		<td width="24%"><div align="right"></div></td>
		<td width="2%"><div align="center"></div></td>
		<td width="24%"></td>
		<td width="24%"></td>
		<td width="2%"></td>
		<td width="24%"></td>
	  </tr>	 
	  <tr>
		<td colspan="3"><div align="center"></div></td>
		<td colspan="3"><div align="center"></div></td>
	  </tr>		   	   
	</table>

	<!-- ======================== Ende Tabelle Widerspruch ======================== -->	</td>
  </tr>
  <tr>
    <td class="bottom">Status</td>
  </tr>  
  <tr>
    <td>
	<!-- ======================== Beginn Tabelle Status ======================== -->
	<%@ include file="WiderspruchStatus.jsp"%> 	
	<!-- ======================== Ende Tabelle Status ======================== -->
	</td>
  </tr>
</table>


<table width="100%" border="0" cellspacing="0">

  <tr>
    <td>&nbsp;</td>
    <td><input class="Submit" type="reset" name="Abbrechen" value="abbrechen"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div align="right"><html:submit value="speichern"/></div></td>
    <td>&nbsp;</td>
  </tr>
</table>
</html:form>
</body>
</html>
