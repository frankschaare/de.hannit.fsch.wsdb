<%@ taglib uri="/WEB-INF/displaytag-11.tld" prefix="display" %>
<%@ page import="org.displaytag.sample.*, java.util.*,
                 org.displaytag.tags.TableTag"%>

<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/screen.css" type="text/css" media="screen, print" />
<SCRIPT LANGUAGE="javascript">
<!--

function init()
{
var navFrame = parent.NavigationFrame;
if (navFrame != null){parent.NavigationFrame.window.location.reload();}
}


-->
</SCRIPT>

</head>
<body onLoad="init()">
<h4>&nbsp;</h4>
<h4 align="center">Übersicht über alle erfassten Widerspr&uuml;che </h4>
<div align="center">
<display:table name="sessionScope.Ergebnis.rows">
  <display:column property="id" title="ID" sortable="true" headerClass="sortable" href="WiderspruchDetail.do" paramId="id"/>
  <display:column property="aktenzeichen" title="Aktenzeichen" sortable="true" headerClass="sortable" href="WiderspruchDetail.do" paramId="id" paramProperty="id"/>
  <display:column property="nachname" title="Nachname HE" sortable="true" headerClass="sortable" href="WiderspruchDetail.do" paramId="id" paramProperty="id"/>
  <display:column property="vorname" title="Vorname HE" href="WiderspruchDetail.do" paramId="id" paramProperty="id"/>
  <display:column property="hegeburtsdatum" title="Geburtsdatum" href="WiderspruchDetail.do" paramId="id" paramProperty="id" width="40"/>
  <display:column property="eingangsdatum" title="Eingang:" href="WiderspruchDetail.do" paramId="id" paramProperty="id" width="40"/>
  <display:column property="erfasstam" title="erfasst:" href="WiderspruchDetail.do" paramId="id" paramProperty="id" width="40"/>
</display:table>

</div>
</body>