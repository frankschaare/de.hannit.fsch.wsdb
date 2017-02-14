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

-->
</SCRIPT>
<noscript>
<h1>Die Widerspruchsdatenbank funktioniert nur, wenn Ihr Browser JavaScript eingeschaltet hat. 
Leider ist JavaScript bei Ihnen ausgeschaltet. Bitte schalten Sie JavaScript ein, oder beenden Sie die Anwendung.
</h1>
</noscript>

<body onLoad="init()">

<div id="Dokument">
<table width="100%"  border="0" cellspacing="0" cellpadding="3">
  <tr>
    <td width="50%" align="center">
	<!--==================== Beginn Hilfeempfänger ====================-->
	<fieldset>
		<LEGEND ALIGN="left">Hilfeempfänger</LEGEND>
		<%@ include file="Hilfeempfaenger.jsp"%>
	</fieldset>
	<!--==================== Ende Hilfeempfänger ====================-->	
	</td>
    <td width="50%">
	<!--==================== Beginn Hilfeempfänger ====================-->

	<!--==================== Ende Hilfeempfänger ====================-->	

	</td>
  </tr>
  <tr>
    <td width="50%">Aktenzeichen:</td>
    <td width="50%">Eingangsdatum:</td>
  </tr>
  <tr>
    <td><strong><bean:write name="sessionWS" property="aktenzeichen"></bean:write></strong></td>
    <td><strong><bean:write name="sessionWS" property="eingangsdatum"></bean:write></strong></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>


	<!--==================== Beginn DIV Verfahrensverlauf ====================-->
</div>
<div id="Schatten"></div>

</body>
</html>
