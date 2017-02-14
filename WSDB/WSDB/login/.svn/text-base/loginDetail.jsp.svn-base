<%-- Author: Frank.Schaare@HannIT.de, 19.11.2002 --%>

<%@ page 	language="java" 
			session="true"
			import="java.util.*"
			info="Login Seite zur Benutzerauthentifizierung"
			errorPage="LoginError.jsp"
			isErrorPage="false"
			contentType="text/html; charset=ISO-8859-15"
%>

<html>
<head>

<META NAME="Title" CONTENT="Hannoversche Informationstechnologien - HannIT">
<META NAME="Author" CONTENT="Frank.Schaare@HannIT.de">
<META NAME="Publisher" CONTENT="HannIT">
<META NAME="Copyright" CONTENT="&copy;HannIT 2002">
<META NAME="Revisit" CONTENT="After 30 days">
<META NAME="Keywords" CONTENT="Sozialhilfe, Wohngeld, KFZ, Programmierung, Content-Managment">
<META NAME="Description" CONTENT="Der Internetauftritt der HannIT">
<META NAME="page-topic" CONTENT="Kommunale Datenverarbeitung">
<META NAME="audience" CONTENT="Alle">
<META NAME="Robots" CONTENT="INDEX,NOFOLLOW">
<META NAME="Language" CONTENT="Deutsch">

<title></title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/WindowsFormulare.css">
<script type="text/javascript">
<!--

/*
* Sorgt dafür, dass die Seite beim Laden den Navigationsbaum aktualisiert.
*/
function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}

document.getElementById("textBenutzerName").focus();
}

// -->
</script>

</head>

<body onLoad="init()">
<form method="post" action="<%=response.encodeURL("j_security_check")%>">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Bitte melden Sie sich an:</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
		<fieldset>
		<LEGEND ALIGN="left">Anmeldung</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%"></td>
				<td width="68%"></td>
			  </tr>
			  <tr>
				<td colspan="3"><div align="center">Bitte geben Sie Ihren Benutzernamen und Ihr Passwort ein:</div></td>
			  </tr>			
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Benutzername:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><input class="textMittel" type="text" name="j_username" id="textBenutzerName" tabindex="1" onBlur="javascript:document.getElementById('textBenutzerPasswort').focus();"></td>
			  </tr>			
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Passwort:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><input class="textMittel" type="password" name="j_password" tabindex="2" id="textBenutzerPasswort" onBlur="javascript:document.getElementById('buttonSubmit').focus();"></td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>
			  <tr>
				<td colspan="3">&nbsp;</td>
			  </tr>						  					    			  					    		    
			</table>
		</fieldset>
	<!--==================== Hinweise ====================-->	
		<fieldset>
			<LEGEND ALIGN="left">Hinweise</LEGEND>
			<div id="Hinweise">
			<p>Da es sich bei den Daten der Widerspruchs- und Klagedatenbank um gesch&uuml;tzte Inhalte handelt, ist ausschliesslich authorisierten Benutzer/innen der Zugriff gestattet. </p>
			<p> Achten Sie bitte auf die korrekte Schreibweise Ihres Benutzernamens und Ihres Passwortes.</p>
			<p>Probleme beim Login ? Klicken Sie <a href="<%=request.getContextPath()%>/login/LoginFAQ.jsp">hier</a> 
          f&uuml;r weitere Informationen.</p>
			</div>
		</fieldset>	
	<!--==================== Hinweise ====================-->	
	<!--==================== Buttons ====================-->	
	</div>
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div align="left"><input class="button" type="button" value="&lt;&lt;&nbsp;Zurück" onclick="javascript:history.back();"></td>
			<td width="50%"><div align="right"><input class="button" type="submit" name="Submit" value="Anmelden&nbsp;&gt;&gt;" tabindex="3" id="buttonSubmit"></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
			
	</div>
</div>
</form>
</body>

</html>