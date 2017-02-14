<%@ page contentType="text/html; charset=utf-8" language="java" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html>
  <head>
    <title>gazingus.org - Expandable Menu</title>
    <style type="text/css" media="screen">
        @import url("<%=request.getContextPath()%>/style/Standard.css"); 
    </style>
    <script type="text/javascript">
    <!--
    //-->
    </script>
  </head>
  <body>
<html:form action="/ThemaErfassen">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="2%">&nbsp;</td>
    <td width="31%">Unterthema erfassen </td>
    <td width="2%">&nbsp;</td>
    <td width="31%">&nbsp;</td>
    <td width="2%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="2%">&nbsp;</td>
    <td width="31%">&nbsp;</td>
    <td width="2%">&nbsp;</td>
    <td width="31%">&nbsp;</td>
    <td width="2%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="2%">&nbsp;</td>
    <td width="31%">
		<logic:present name="t">
			<p>t ist vorhanden !</p>
		</logic:present>
			<html:select property="schluessel" size="13" titleKey="label.Anrede.title" ondblclick="document.forms[0].submit()">
				<html:optionsCollection name="fb" property="themen" />
			</html:select>		
	</td>
    <td width="2%">&nbsp;</td>
    <td width="31%"><div align="right"></div></td>
    <td width="2%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</html:form>

  </body>
</html>