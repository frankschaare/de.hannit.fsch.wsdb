<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display" %>
<%@ page import="org.displaytag.sample.*, java.util.*,
                 org.displaytag.tags.TableTag"%>

<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/screen.css" type="text/css" media="screen, print" />
</head>
<body>
<h4>&nbsp;</h4>
<h4 align="center">Übersicht über alle erfassten Benutzer/innen</h4>
<div align="center">
<display:table name="requestScope.Ergebnis.rows" pagesize="20">
</display:table>

<display:table name="requestScope.Ergebnis.rows" decorator="org.displaytag.sample.Wrapper" >
  <display:column property="id" title="ID" />
  <display:column property="id" title="Actions" />
</display:table>

</div>
</body>