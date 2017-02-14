			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="30%"><div align="right">&nbsp;</div></td>
				<td width="2%"><div align="center">&nbsp;</div></td>
				<td width="68%">&nbsp;</td>
			  </tr>					
			  <tr>
				<td width="30%"><div align="right">Vorname:</div></td>
				<td width="2%">&nbsp;</td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.vorname"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Nachname:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.nachname"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Postleitzahl:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.adresse.postleitzahl"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Wohnort:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.adresse.ort"/></td>
			  </tr>
			  <tr>
				<td width="30%"><div align="right">Geburtsdatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.geburtsdatum"/></td>
			  </tr>
			<logic:notEmpty name="sessionWS" scope="session" property="hilfeempfaenger.sterbedatum">
			  <tr>
				<td width="30%"><div align="right">Sterbedatum:</div></td>
				<td width="2%"><div align="center"></div></td>
				<td width="68%" class="Wert"><bean:write name="sessionWS" scope="session" property="hilfeempfaenger.sterbedatum"/></td>
			  </tr>
			</logic:notEmpty>			  
			  <tr>
				<td width="30%"><div align="right">&nbsp;</div></td>
				<td width="2%"><div align="center">&nbsp;</div></td>
				<td width="68%">&nbsp;</td>
			  </tr>			
			</table>

