<%-- Author: Frank.Schaare@HannIT.de, 21.10.2003 --%>

<%@ page 	language="java" 
			session="true"
			import="java.util.*"
			info="Seite zur Benutzererfassung"
			isErrorPage="false"
			contentType="text/html; charset=ISO-8859-15"
%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<jsp:useBean id="sb" class="de.hannit.fsch.benutzerverwaltung.Sachbearbeiter" scope="request"/>
<html>
<head>

<title></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/Formulare.css">
</head>

<SCRIPT LANGUAGE="javascript">
<!--
/**
* 	Script zum ein-/ausblenden der Selects
*
*	Abhängig vom Wert des Selects 'Hauptgruppe' wird das entsprechende Select 'Untergruppe' eingeblendet.
*	Dies geschieht durch setzen der CSS Eigenschaft 'Display' von 'none' auf 'block'.
*
*/ 
function FocusSelect()
{
	if(document.getElementById)
  	{
	document.getElementById("TextAnreden").style.display="none";
	document.getElementById("Anreden").style.display="block";
  	}
}
function SetValue()
{
	if(document.getElementById)
  	{
	document.getElementById("TextAnreden").value=document.getElementById("Anreden").value;
	document.getElementById("TextAnreden").style.display="block";
	document.getElementById("Anreden").style.display="none";
  	}
}
-->
</SCRIPT>


<body>
<div align="center"><h4>Benutzerdaten:</h4></div>

<table width="100%" border="0" cellspacing="0">
<html:form action="/BenutzerBearbeitungSubmit">
  <tr>
    <td width="49%" class="label"><bean:message key="label.Anrede"/></td>
    <td width="2%"><div align="center">*</div></td>
    <td width="49%">
	<html:text property="Anrede" value="<%=sb.getAnrede()%>" styleId="TextAnreden" onclick="FocusSelect()" titleKey="label.Anrede.title" /> 
	<html:select property="Anrede" styleId="Anreden" size="1" titleKey="label.Anrede.title" onchange="SetValue()">
		<html:optionsCollection name="fb" property="hmAnreden" label="value" value="key"/>
	</html:select>
	</td>
  </tr>
  <tr>
    <td colspan="3"><!--html:errors property="Vorname"/--></td>
  </tr> 
  <tr>
    <td width="49%" class="label"><bean:message key="label.Vorname"/></td>
    <td width="2%"><div align="center">*</div></td>
    <td width="49%"><html:text property="Vorname" value="<%=sb.getVorname()%>" titleKey="label.Vorname.title" /></td>
  </tr>
  <tr>
    <td colspan="3"><!--html:errors property="Vorname"/--></td>
  </tr>  
  <tr>
    <td width="49%" class="label"><bean:message key="label.Nachname"/></td>
    <td width="2%"><div align="center">*</div></td>
    <td width="49%"><html:text property="Nachname" value="<%=sb.getNachname()%>" titleKey="label.Nachname.title" /></td>
  </tr>
  <tr>
    <td colspan="3"><html:errors property="Nachname"/></td>
  </tr>
  <tr>
    <td width="49%" class="label"><bean:message key="label.Raum"/></td>
    <td width="2%"><div align="center">*</div></td>
    <td width="49%"><html:text property="Raum" value="<%=sb.getRaum()%>" titleKey="label.Raum.title" /></td>
  </tr>
  <tr>
    <td colspan="3"><!--html:errors property="Nachname"/--></td>
  </tr>
  <tr>
    <td width="49%" class="label"><bean:message key="label.Durchwahl"/></td>
    <td width="2%"><div align="center">*</div></td>
    <td width="49%"><html:text property="Durchwahl" value="<%=sb.getDurchwahl()%>" titleKey="label.Durchwahl.title" /></td>
  </tr>
  <tr>
    <td colspan="3"><!--html:errors property="Nachname"/--></td>
  </tr>  
  <tr>  
    <td width="49%"><div align="right">Testcheckbox</div></td>
    <td width="2%"><div align="center">*</div></td>
    <td width="49%"><!--html:checkbox property="testCheck" disabled="false" titleKey="benutzerErfassung.ueberschrift"/--></td>
  </tr>
  <tr>
    <td width="49%"><div align="right"></div></td>
    <td width="2%">&nbsp;</td>
    <td width="49%">&nbsp;</td>
  </tr>  
<tr>
    <td width="49%"><div align="right">TestSelect</div></td>
    <td width="2%"><div align="center">*</div></td>
    <td width="49%">&nbsp;</td>
  </tr>
  <tr>
    <td width="49%"><div align="right"></div></td>
    <td width="2%">&nbsp;</td>
    <td width="49%">&nbsp;</td>
  </tr>  
  <tr>
    <td><div align="left"><html:reset value="abbrechen"/></div></td>
    <td>&nbsp;</td>
    <td><div align="right"><html:submit value="speichern"/></div></td>
  </tr>
</html:form>
</table>

</body>

</html>
