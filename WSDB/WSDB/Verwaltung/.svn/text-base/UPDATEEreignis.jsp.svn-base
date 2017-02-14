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

document.getElementById("Ereignis.name").focus();
}

function getCoordinates(bezeichner) 
{
x = window.event.clientX;
y = window.event.clientY;
PopupKalender(x,y,bezeichner);
}

function PopupKalender(x,y,bezeichner) {
popup = window.open('/WSDB/html/Kalender.html?bezeichner='+bezeichner,"popup","left="+x+",top="+y+",width=200,height=220,dependant=yes,status=no");
var hidden = popup.document.getElementById("openerElement");
hidden.value=bezeichner;
}

// -->
</script>

</head>

<body onLoad="init()">

<html:form action="CRUDEreignis?crud=2&loaded=true" method="post" target="_self">
<div id="Aussenlayer">
  <div id="Innenlayer">
	<div id="Kopfzeile">Ein bereits erfasstes Ereignis bearbeiten:</div>
	<!--==================== Formular ====================-->
	<div id="Formular">
		<fieldset>
		<LEGEND ALIGN="left">Ereignis</LEGEND>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right"><bean:message key="Ereignis.label.Name"/></div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:hidden name="ereignis" property="id"/>

				<!-- Wenn das Ereignis geschützt ist, dürfen die Anwender nichts ändern: -->
				<logic:match name="ereignis" property="protected" value="true">
					<html:hidden name="ereignis" property="fkEreignisID"/>
					<strong><bean:write name="ereignis" property="name"/></strong>
				</logic:match>
				<logic:match name="ereignis" property="protected" value="false">				
				<html:select 	name="ereignis" 
								property="fkEreignisID"
								styleId="selectEreignis">
					<html:optionsCollection name="fb" property="ereignis" value="id" label="name" />
				</html:select>
				</logic:match>				
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%"><div align="left" class="errors"><html:errors property="name"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><a href="#"><div align="right" onClick="getCoordinates('datum')">Datum:</div></a></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="ereignis"
							property="datum" 
							titleKey="Ereignis.update.textTitle.Name"
							styleClass="textLang" 
							styleId="datum" 
							readonly="true"
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
				<td width="30%"><div align="right">Kommentar:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:text 	name="ereignis"
							property="kommentar" 
							titleKey="Ereignis.update.textTitle.Name"
							styleClass="textLang" 
							styleId="Ereignis.name"
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
				<td colspan="3">&nbsp;</td>
			  </tr>						  					    			  					    		    
			</table>
		</fieldset>
	<!--==================== Hinweise ====================-->	
		<fieldset>
			<LEGEND ALIGN="left">Hinweise</LEGEND>
			<div id="Hinweise">
			<bean:message key="Verfahrensverlauf.update.text.Hinweise"/>
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
