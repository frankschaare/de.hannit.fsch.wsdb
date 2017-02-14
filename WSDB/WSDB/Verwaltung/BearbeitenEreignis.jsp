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

document.getElementById("nameHilfstabelle").style.display="none";
document.getElementById("Ereignis.name").focus();
<logic:notEmpty name="ereignis" property="nameHilfstabelle">
document.getElementById("nameHilfstabelle").style.display="inline";
</logic:notEmpty>
}

function showNameHilfstabelle(control)
{
if(control.value != 'Kommentar'){document.getElementById("nameHilfstabelle").style.display="inline";}
else{document.getElementById("nameHilfstabelle").style.display="none";}
}

// -->
</script>

</head>

<body onLoad="init()">

<html:form action="UpdateEreignis" method="post" target="_self">
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
				<html:text 	name="ereignis"
							property="name" 
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
				<td width="68%"><div align="left" class="errors"><html:errors property="name"/></div></td>
			  </tr>			
			  <tr>
				<td width="30%"><div align="right"><bean:message key="Ereignis.label.OE"/></div></td>
				<td width="2%"><div align="center">*</div></td>
				<td width="68%">
				<html:select name="ereignis" property="organisationseinheitID" size="1" titleKey="Ereignis.insert.textTitle.OE" styleClass="sachgebiet" styleId="organisationseinheitID" tabindex="2">
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
				<td width="30%"><div align="right">Datumsfeld:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:checkbox name="ereignis" property="datumsFeld" tabindex="3"/>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Zusatzfeld:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:radio name="ereignis" property="zusatzFeld" value="Kommentar" onclick="showNameHilfstabelle(this)" tabindex="4">Kommentarfeld</html:radio>
				<html:radio name="ereignis" property="zusatzFeld" value="Hilfstabelle" onclick="showNameHilfstabelle(this)" tabindex="5">Hilfstabelle</html:radio>
				<html:select name="ereignis" property="nameHilfstabelle" size="1" titleKey="label.Anrede.title" styleId="nameHilfstabelle" tabindex="6">
					<html:option value="">bitte auswählen:</html:option>	
					<html:optionsCollection name="fb" property="hilfstabellen" />
				</html:select>
				</td>
			  </tr>
			  <tr>
				<td width="30%">&nbsp;</td>
				<td width="2%">&nbsp;</td>
				<td width="68%">&nbsp;</td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">wiederkehrend ?</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%">
				<html:checkbox name="ereignis" property="wiederkehrend" tabindex="5"/>
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
			<bean:message key="Ereignis.update.text.Hinweise"/>
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
