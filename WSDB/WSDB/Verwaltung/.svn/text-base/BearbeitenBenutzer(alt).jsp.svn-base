<%@ page import="org.displaytag.sample.*, java.util.*,
                 org.displaytag.tags.TableTag"%>

<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html>
<head>
<title>Ereignis erfassen</title>
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

}

// -->
</script>

</head>

<body onLoad="init()">

<html:form action="BenutzerBearbeitungSubmit" method="post" target="_self">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Benutzer / in bearbeiten:</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
		<fieldset>
		<LEGEND ALIGN="left">Benutzer / in</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:hidden name="sb" property="userID"/>
				<html:text 	name="sb"
							property="vorname" 
							titleKey="ErfassungEinrichtung.name.title"
							styleClass="textLang" 
							styleId="sb.vorname"
							tabindex="1"
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
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:text 	name="sb"
							property="nachname" 
							titleKey="ErfassungEinrichtung.name.title"
							styleClass="textLang" 
							styleId="sb.nachname"
							tabindex="2"
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
				<td width="30%"><div align="right">Organisationseinheit:</div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="sb" property="organisationseinheitID" size="1" titleKey="label.Anrede.title" styleClass="sachgebiet" styleId="organisationseinheitID" tabindex="3">
					<html:option value="">........</html:option>	
					<html:optionsCollection name="fb" property="teams" />
				</html:select>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="organisationseinheitID"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right">Raum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="sb"
							property="raum" 
							titleKey="ErfassungEinrichtung.name.title"
							styleClass="textLang" 
							styleId="sb.raum"
							tabindex="4"
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
				<td width="30%"><div align="right">Durchwahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="sb"
							property="durchwahl" 
							titleKey="ErfassungEinrichtung.name.title"
							styleClass="textLang" 
							styleId="sb.durchwahl"
							tabindex="5"
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
				<td width="30%"><div align="right">Benutzerrollen:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<bean:write name="sb" property="sachgebiet.aufgabenListe" filter="false"/>
				</td>
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
			<p>Auf dieser Seite k&ouml;nnen Sie ein bereits erfasstes Ereignis bearbeiten. Dies ist z.b. dann sehr sinnvoll, wenn Sie die Sichtbarkeit des Ereignisses umstellen wollen. </p>
			<p> Mit der Zuordnung des Ereignisses zu einer Organisationseinheit steuern Sie automatisch die Sichtbarkeit des Ereignisses. Ordnen Sie das Ereignis dem gesamten Fachbereich zu, ist es automatisch f&uuml;r alle Teams des Fachbereiches sichtbar. Ordnen Sie es dagegegen einem Team oder einem Fachbereich zu, ist das Ereignis nur f&uuml;r dieses Team sichtbar.</p>
			<p>Sie k&ouml;nnen ausserdem festlegen, ob das Ereignis ein optionales Zusatzfeld und ein Kommentarfeld enth&auml;lt. Wenn Sie ein Zusatzfeld angeben, kann dies entweder ein Datumsfeld oder eine Hilfstabelle aus der Datenbank sein. Wenn Sie f&uuml;r das Zusatzfeld 'Hilfstabelle' angeben, erscheint eine Liste aller verf&uuml;gbaren Hilfstabellen. Aus dieser Liste m&uuml;ssen Sie einen Wert ausw&auml;hlen. </p>
			<p>Ein wiederkehrendes Ereignis kann mehrfach ein den Verfahrensverlauf gespeichert werden. Ist das Ereignis dagegegen nicht wiederkehrend, also einmalig, kann es nur einmal in den Verfahrensverlauf geschrieben werden. </p>
			</div>
		</fieldset>	
	<!--==================== Hinweise ====================-->	
	<!--==================== Buttons ====================-->	
	</div>
	<fieldset class="buttons">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="50%"><div align="left"><html:button property="button" value="Abbrechen" styleClass="button" onclick="javascript:history.back();"/></div></td>
			<td width="50%"><div align="right"><html:submit value="&auml;ndern >>" styleClass="button"/></div></td>
		  </tr>
		</table>
	</fieldset>	
	<!--==================== Buttons ====================-->			
			
	</div>
</div>

</html:form>
</body>
</html>
