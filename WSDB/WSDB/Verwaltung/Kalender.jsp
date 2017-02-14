<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page 	language="java" 
			session="true"
			import="java.util.*"
            autoFlush="true"
			isErrorPage="false"
			contentType="text/html; charset=ISO-8859-1"
%>


<html>
<head>
<title>Kalender</title>
<meta name="Author" content="Dr. Thomas Meinike - thomas@handmadecode.de">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
<!--
html,body
{
overflow-y: auto;
}

table,th,td
{
  border: 1px solid #426f9b;
  border-collapse: collapse;
  background-color:#6496BE;
}

td,th,input
{
  text-align: center;
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: 14px;
}
Select 
{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-style: normal;
	color: #000000;
	line-height: normal;
	letter-spacing: 2px;
	word-spacing: normal;
	vertical-align: baseline;
	white-space: normal;
	border-style:groove;
}

input[type="text"]
{
  margin-top: 10px;
}

td
{
  cursor: hand;
}

table[id="kalender"] td
{
  cursor: pointer;
}
-->
</style>
<script language="JavaScript" type="text/javascript">
<!--
var t,m,j,e,d,ad,at,am,hm,ht,hj,heute,datum,td,kalobj;

function Init()
{
alert(request.getParameter("bezeichner"));
  d=document.forms[0];
  m=new Date().getMonth()+1;
  j=new Date().getFullYear();
  d.monat.selectedIndex=m-1;
  d.jahr.selectedIndex=j-2002;
  Kalender();
}

function Kalender()
{
 if(document.getElementById && document.getElementsByTagName || navigator.userAgent.indexOf("Opera 7")!=-1)
 {
  // Werte fuer Monat (m) und Jahr (j) auslesen
  m=parseInt(d.monat.options[d.monat.selectedIndex].value);
  j=parseInt(d.jahr.options[d.jahr.selectedIndex].value);

  // t=Anzahl der Tage im aktuellen Monat
  t=31;
  t=(new Date(j,m-1,t).getDate()==t)?t:30;
  t=(new Date(j,m-1,t).getDate()==t)?t:29;
  t=(new Date(j,m-1,t).getDate()==t)?t:28;

  // e=Wochentag des Monatsersten (0=Sonntag bis 6)
  e=new Date(j,m-1,1).getDay();

  // at=aktuelles Datum
  ad=new Date();

  ht=ad.getDate().toString();
  ht=(ht.length==2)?ht:"0"+ht;
  hm=(ad.getMonth()+1).toString();
  hm=(hm.length==2)?hm:"0"+hm;
  hj=ad.getFullYear().toString();
  heute=ht+"."+hm+"."+hj;

  kalobj=document.getElementById("kalender").getElementsByTagName("td");

  // Felder leeren
  for(i=0;i<kalobj.length;i++)
  {
    kalobj[i].style.color="#FFF";
    kalobj[i].style.backgroundColor="#FFF";
    kalobj[i].style.textDecoration="none";
    kalobj[i].firstChild.nodeValue="";
    kalobj[i].title="";
  }

  // Datumswerte einzeln abfragen, abgelaufene Tage durchstreichen, Sonntage rot markieren,
  // das jeweilige Datum mittels title als td-Tooltip setzen
  for(i=0;i<t;i++)
  {
    if(e==0)k=i+6;
    else k=i+e-1;

    kalobj[k].style.fontWeight="normal";
    kalobj[k].firstChild.nodeValue=i+1;

    td=new Date(j,m-1,i+2);
    if(td-ad>=0)kalobj[k].style.color="#00C";
    else {kalobj[k].style.color="#000";kalobj[k].style.textDecoration="none";}
    if(k>0 && k % 7 == 0)kalobj[k-1].style.color="#F00";
    if(k==34)kalobj[k].style.color="#F00";

    at=(i+1).toString();
    at=(at.length==2)?at:"0"+at;
    am=m.toString();
    am=(am.length==2)?am:"0"+am;
    datum=at+"."+am+"."+j
    kalobj[k].title=datum;

    // aktuellen Tag hervorheben
    if(datum==heute){kalobj[k].style.color="#090";kalobj[k].style.fontWeight="bold";}
  }

  if(kalobj[35].firstChild.nodeValue=="")document.getElementsByTagName("tr")[6].style.display="none";
  else if(document.all)document.getElementsByTagName("tr")[6].style.display="block";
  else document.getElementsByTagName("tr")[6].style.display="table-row";
 }
}

function TagMarkieren(par)
{
  // fuer IE
  if(par==1 && window.event)
  {
    if(window.event.srcElement.tagName=="TD")
    {
      // bei MouseOver Hintergrundfarbe aendern
      if(window.event.srcElement.firstChild.nodeValue!="")window.event.srcElement.style.backgroundColor="#CFC";
    }
  }  
  else if(par==0 && window.event && window.event.srcElement.tagName=="TD")window.event.srcElement.style.backgroundColor="#FFF";
}

function Datum2Textfeld(datum,lt)
{
	if(datum!="" && lt=="none")
	{
	opener.document.getElementById(bezeichner).value=datum;
	this.close();
	opener.document.getElementById(bezeichner).focus();
	}

}
//-->
</script>
</head>
<body onload="Init()" bgcolor="#CCCCCC">
<form action="">
<select name="monat" onchange="Kalender()">
<option value="1">Januar</option>
<option value="2">Februar</option>
<option value="3">März</option>
<option value="4">April</option>
<option value="5">Mai</option>
<option value="6">Juni</option>
<option value="7">Juli</option>
<option value="8">August</option>
<option value="9">September</option>
<option value="10">Oktober</option>
<option value="11">November</option>
<option value="12">Dezember</option>
</select>
<select name="jahr" onchange="Kalender()">
<option value="2002">2002</option>
<option value="2003">2003</option>
<option value="2004">2004</option>
<option value="2005">2005</option>
<option value="2006">2006</option>
<option value="2007">2007</option>
<option value="2008">2008</option>
<option value="2009">2009</option>
<option value="2010">2010</option>
<option value="2011">2011</option>
<option value="2012">2012</option>
</select>
</form>
<table id="kalender" onmouseover="TagMarkieren(1)" onmouseout="TagMarkieren(0)">
<tr><th>Mo</th><th>Di</th><th>Mi</th><th>Do</th><th>Fr</th><th>Sa</th><th>So</th></tr>
<tr><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td></tr>
<tr><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td></tr>
<tr><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td></tr>
<tr><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td></tr>
<tr><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td></tr>
<tr><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td><td onclick="Datum2Textfeld(this.title,this.style.textDecoration)">&nbsp;</td></tr>
</table>
</body>
</html>
