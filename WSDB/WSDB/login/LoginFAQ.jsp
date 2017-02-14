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

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/Standard.css">


</head>

<body>
  <table width="100%" border="0">
    <tr> 
      <td width="15%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="15%">&nbsp;</td>
    </tr>
    <tr> 
      <td width="15%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="15%">&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="4"><div align="center"><strong>Widerspruchs- und Klagedatenbank  - Login 
          FAQ (frequently asked Questions)</strong></div></td>
    </tr>
    <tr> 
      <td width="15%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="15%">&nbsp;</td>
    </tr>
    <tr> 
      <td width="15%">&nbsp;</td>
      <td colspan="2"><div align="center">Auf dieser Seite finden Sie Sie Fragen 
          und Antworten zu den bisher aufgetretenen Problemen bei der Anmeldung 
          zur Datenbank.</div></td>
      <td width="15%">&nbsp;</td>
    </tr>
    <tr> 
      <td width="15%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="15%">&nbsp;</td>
    </tr>
    <tr> 
      <td width="15%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="35%">&nbsp;</td>
      <td width="15%">&nbsp;</td>
    </tr>
    <tr> 
      <td width="15%">&nbsp;</td>
      <td colspan="2"><strong>Frage</strong>: Ich erhalte, nachdem ich meine Anmeldeinformationen 
        eingetragen habe, lediglich eine seltsame <a href="<%=request.getContextPath()%>/html/HTTPStatus400.html" title="Klicken Sie hier für einen detaillierte Ausgabe der Fehlerseite">Fehlerseite</a> 
        mit der &Uuml;berschrift 'Invalid direct reference to form login page 
        '</td>
      <td width="15%">&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
      <td colspan="2"><p><strong>Antwort</strong>: Sie haben Sie Anmeldeseite 
          der WSDB zu Ihren Favoriten bzw. Lesezeichen hinzugef&uuml;gt. 
          Das ist so nicht m&ouml;glich, da bei jedem Aufruf der Loginseite zur 
          WSDB eine eindeutige ID erzeugt wird, die sogenannte 
          'jsessionid'. Das ist die lange Nummer, die Sie in der Adressleiste 
          Ihres Browsers sehen k&ouml;nnen, wenn Sie sich anmelden. </p>
        
        <p>Speichern Sie diese einmalige Nummer nun in Ihren Favoriten ab, wird 
        bei der n&auml;chsten Anmeldung eine andere ID erzeugt und Ihr Lesezeichen 
        referenziert auf eine ung&uuml;ltige ID.</p>
      <p><strong>L&ouml;sung</strong>: &Auml;ndern Sie Ihr Lesezeichen dahingehend 
        ab, das nur auf die WSDB Startadresse '<a href="http://10.33.5.17/WSDB">http://10.33.5.17/WSDB</a>' 
        verwiesen wird, alles andere erledigt WSDB f&uuml;r Sie. </p>
      <!--
		<p>Eine kleine Anleitung, wie Lesezeichen im Internet Explorer ge&auml;ndert 
        werden k&ouml;nnen, finden Sie hier:
		</p>
	-->
	</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
	
    <tr> 
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
      <td colspan="2"><div align="left"><strong>Frage</strong>: Mein Passwort 
        wird nicht angenommen | Ich kann mich nicht anmelden</div></td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
      
    <td colspan="2"><div align="left"> 
        <p><strong>Antwort</strong>: Falls Sie sich wirklich nicht vertippt haben, kann es sein, dass die Anwendung tempor&auml;r nicht zur Verf&uuml;gung steht.</p>
      </div>
      <p>Wenden Sie sich bei Problemen bei der Anmeldung an Ihren TUI  oder an die HannIT Hotline unter der Rufnummer 
        (0511) 616-11111.</p></td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</body>

</html>
