<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="hello.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<h1>Hallo <bean:write name="user" property="strUsername"/>, alte Säge...</h1>

</body>
</html:html>
