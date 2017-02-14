<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td width="24%"><div align="right">erledigt:</div></td>
		<td width="2%"><div align="center">&nbsp;</div></td>
		<td width="24%"><div align="left"><html:checkbox property="erledigt" onmouseover="this.style.cursor='hand'"></html:checkbox></div></td>
		<td width="24%"><div align="left">am: <html:text 	name="fb"
												property="erledigungsdatum" 
												titleKey="label.Geburtsdatum.title" 
												styleClass="DatumDefault" 
												onfocus="this.style.backgroundColor='#C5D4E0'" 
												onblur="this.style.backgroundColor='#FFFFFF'"/></div></td>
		<td width="2%">&nbsp;</td>
		<td width="24%">
			<html:select name="benutzer" property="userID" size="1" titleKey="label.Anrede.title">
				<html:option value="">durch:</html:option>	
				<html:optionsCollection name="fb" property="erledigungsgrund" />
			</html:select>		
		</td>
	  </tr>
	  <tr>
		<td colspan="3"><div align="center"><html:errors property="aktenzeichen"/></div></td>
		<td colspan="3"><div align="center"><html:errors property="eingangsdatum"/></div></td>
	  </tr>	 
	  <tr>
		<td width="24%"><div align="right">Ergebnis:</div></td>
		<td width="2%"><div align="center">&nbsp;</div></td>
		<td width="14%">
			<html:select name="benutzer" property="userID" size="1" titleKey="label.Anrede.title">
				<html:option value="">bitte auswählen:</html:option>	
				<html:optionsCollection name="fb" property="ergebnis" />
			</html:select>		
		</td>
		<td width="34%"><div align="right"></div></td>
		<td width="2%">&nbsp;</td>
		<td width="24%"></td>
	  </tr>
	  <tr>
		<td colspan="3"><div align="center"><html:errors property="aktenzeichen"/></div></td>
		<td colspan="3"><div align="center"><html:errors property="eingangsdatum"/></div></td>
	  </tr>	 
	</table>
