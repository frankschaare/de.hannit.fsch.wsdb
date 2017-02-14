<%@ page	contentType="text/html; charset=iso-8859-1" 
			language="java" 
			import="java.sql.*" 
			errorPage="" 
%>
<html>
<head>
<title>Unbenanntes Dokument</title>

<style type="text/css">
<!--

body
{
background-color: #6496BE;
font-size: 12px; 
font-family: verdana
}
a {
display : block;
margin-bottom : 1px;
margin-left : 1px;
margin-right : 1px;
margin-top : 1px;
padding-bottom : 2px;
padding-left : 1px;
padding-right : 1px;
padding-top : 2px;
}
a{
	display : block;
	margin-bottom : 3px;
	margin-left : 0px;
	margin-right : 0px;
	margin-top : 3px;
	padding-bottom : 5px;
	padding-left : 2px;
	padding-right : 2px;
	padding-top : 5px;
	width : auto;
}
a:link {
	background-color : #ccccff;
	color : #333355;
	text-decoration : none;
}
a:visited {
	text-decoration : none;
	color : #000000;	
}
a:hover {
background-color : #7ABBFF;
color : #000000;
text-decoration : none;
}
a:active {
	background-color : #FFFFFF;
	color : #000000;
	text-decoration : none;
}
div.WSLayer
{
position:absolute;
width:115%;
height: 100%;
left: 0%; 
top: 5%;
z-index:1;   
background-color: #6496BE;
}
span.header
{
width:100%; 
font-size: 14px;
font-weight:bold;
color:#FFFFFF;
background-color: #426F9B;
padding-top : 2px;
padding-left : 1px;
padding-right : 1px;
padding-bottom : 2px;
}
span.menu
{
width:100%; 
}
span.aktiv
{
background-color : #FFFFFF;
color : #000000;
text-decoration : none;
width:100%; 
}

br { font-size: 10px; font-family: arial}
td { color: #000000; font-size: 12px; font-family: verdana}
td.Rechts {
	font-size: 12px;
	font-family: verdana;
	background-image: url(../img/LogoBackground.jpg);
	background-repeat: no-repeat;
	background-position: right center;
	background-color: #6496BE;
	text-align: right;
	height: 76px;
}

input { font-family: Verdana; font-size: 8pt;}

-->
</style>

<script language="JavaScript" type="text/JavaScript">
<!--
function highlight(elem)
{
var spans = document.getElementsByTagName("span");
  for(var i=0;i<spans.length;i++)
  {
  	if (spans[i].className!="header")
	{
	spans[i].className="menu";
	}
  }
elem.className="aktiv";
}
//-->
</script>
</head>

<body>
<div class="WSLayer">
	<span class="header">Widerspruch</span>
	<span id="0" class="menu" onClick="highlight(this)"><a href="<%=request.getContextPath()%>/jsp/Widerspruch-Erfassen.jsp" target="main" title="Ändern Sie Ihre persönlichen Einstellungen">- <u>erfassen</u></a></span>
	<span id="1" class="menu">- ändern</span>
	<span id="2" class="menu" onClick="highlight(this)"><a href="<%=request.getContextPath()%>/jsp/Widerspruch-Erfassen.jsp" target="main" title="Ändern Sie Ihre persönlichen Einstellungen">- <u>löschen</u></a></span>
	<span class="menu">&nbsp;</span>
	<span class="menu">&nbsp;</span>
	<span class="header">Benutzer</span>
	<span id="4" class="menu" onClick="highlight(this)"><a href="<%=request.getContextPath()%>/BenutzerErfassenVorbereiten.do" target="main" title="Erzeugt dynamische Webseiten aus der Datenbank">- <u>erfassen</u></a></span>
	<span id="4" class="menu" onClick="highlight(this)"><a href="<%=request.getContextPath()%>/jsp/Benutzer-Uebersicht.jsp" target="main" title="Erzeugt dynamische Webseiten aus der Datenbank">- <u>Übersicht</u></a></span>
	<span id="4" class="menu" onClick="highlight(this)"><a href="<%=request.getContextPath()%>/jsp/Benutzer-Uebersicht.jsp" target="main" title="Erzeugt dynamische Webseiten aus der Datenbank">- <u>Zuständigkeit</u></a></span>	
	<span id="4" class="menu" onClick="highlight(this)"><a href="<%=request.getContextPath()%>/Dokumentation/Benutzerauthentifizierung.htm" target="main" title="Erzeugt dynamische Webseiten aus der Datenbank">- <u>Dokumentation</u></a></span>				
</div>

</body>
</html>
