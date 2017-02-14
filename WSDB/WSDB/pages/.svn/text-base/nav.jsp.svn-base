<%@ page import="com.jenkov.tags.tree.impl.TreeNode,
                 com.jenkov.tags.tree.impl.Tree,
                 com.jenkov.tags.tree.itf.*"%>
<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<html>
<head>
<style type="text/css">
<!--
a{font-family: arial;font-size: 10px;color: #000000;text-decoration: none;}
a:active{font-family: arial;font-size: 10px;color: #000000; text-decoration: none;}
a:hover{font-family: arial;font-size: 10px;color: #000000;text-decoration: none;}

a.head{font-family: arial;font-size: 11px;font-weight:bold;color: #ffffff;text-decoration: none;}
a.head:active{font-family: arial;font-size: 11px;color: #ffffff; text-decoration: none;}
a.head:hover{font-family: arial;font-size: 11px;color: #ffffff;text-decoration: none;}


-->
</style>
<base target="main">
</head>
<body>

<logic:present name="example" scope="session">
	<logic:present parameter="nodeID">
		<% 
		ITree tree = (ITree) request.getSession().getAttribute("example");
		String ziel = "/"+request.getParameter("nodeID");
		tree.select(request.getParameter("nodeID"));
		%>
		<logic:redirect page="<%=ziel%>"/>
	</logic:present>

	<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tree:tree tree="example" node="example.node" >
		<tr><td
		><table cellspacing="0" cellpadding="0" border="0">
		<tr><td
		><tree:nodeIndent    node="example.node" indentationType="type"
			><tree:nodeIndentVerticalLine indentationType="type" ><img src="<%=request.getContextPath()%>/img/verticalLine.gif"></tree:nodeIndentVerticalLine
			><tree:nodeIndentBlankSpace   indentationType="type" ><img src="<%=request.getContextPath()%>/img/blankSpace.gif"></tree:nodeIndentBlankSpace
		></tree:nodeIndent
		></td>
		<tree:nodeMatch    node="example.node"
							expanded="false"
							hasChildren="true"
							isLastChild="false"
							><td><a href="nav.jsp?expand=<tree:nodeId node="example.node"/>" target='_self'
							><img src="<%=request.getContextPath()%>/img/collapsedMidNode.gif" border="0"></a
							><img src="<%=request.getContextPath()%>/img/closedFolder.gif"></td>
		</tree:nodeMatch
		><tree:nodeMatch     node="example.node"
							expanded="true"
							hasChildren="true"
							isLastChild="false"
							><td><a href="nav.jsp?collapse=<tree:nodeId node="example.node"/>" target='_self'
							><img src="<%=request.getContextPath()%>/img/expandedMidNode.gif"  border="0"></a
							><img src="<%=request.getContextPath()%>/img/openFolder.gif"></td>
		</tree:nodeMatch
		><tree:nodeMatch     node="example.node"
							expanded="false"
							hasChildren="true"
							isLastChild="true"
							><td><a href="nav.jsp?expand=<tree:nodeId node="example.node"/>" target='_self'
							><img src="<%=request.getContextPath()%>/img/collapsedLastNode.gif"  border="0"></a
							><img src="<%=request.getContextPath()%>/img/closedFolder.gif"></td>
		</tree:nodeMatch
		><tree:nodeMatch     node="example.node"
							expanded="true"
							hasChildren="true"
							isLastChild="true"
							><td><a href="nav.jsp?collapse=<tree:nodeId node="example.node"/>" target='_self'
							><img src="<%=request.getContextPath()%>/img/expandedLastNode.gif" border="0"></a
							><img src="<%=request.getContextPath()%>/img/openFolder.gif"></td>
		</tree:nodeMatch
		><tree:nodeMatch     node="example.node"
							expanded="false"
							hasChildren="false"
							isLastChild="false"
							><td><img src="<%=request.getContextPath()%>/img/noChildrenMidNode.gif"
								><img src="<%=request.getContextPath()%>/img/nonFolder.gif"></td>
		</tree:nodeMatch
		><tree:nodeMatch     node="example.node"
							expanded="false"
							hasChildren="false"
							isLastChild="true"
							><td><img src="<%=request.getContextPath()%>/img/noChildrenLastNode.gif"
								><img src="<%=request.getContextPath()%>/img/nonFolder.gif"></td>
		</tree:nodeMatch
		><td valign="top">
	
		<tree:nodeMatch node="example.node" selected="true">
		<a href="nav.jsp?nodeID=<tree:nodeId node="example.node"/>">
		<span style="Font-Size: 12px;"><b><tree:nodeName      node="example.node"/></b></span></a>
		</tree:nodeMatch>
	
		<tree:nodeMatch node="example.node" selected="false">
		<a href="nav.jsp?nodeID=<tree:nodeId node="example.node"/>">
		<span style="Font-Size: 12px;"><tree:nodeName      node="example.node"/></span></a>
		</tree:nodeMatch>
	
		<!--
		<tree:nodeMatch node="example.node" type="comedy"> (Comedy)</tree:nodeMatch>
		<tree:nodeMatch node="example.node" type="thriller"> (Thriller)</tree:nodeMatch>
		<tree:nodeMatch node="example.node" type="fantasy"> (Fantasy)</tree:nodeMatch>
	
		<tree:nodeMatch node="example.node" type="cartoon">
				<tree:nodeType node="example.node"/>
		</tree:nodeMatch>
	
		<tree:nodeMatch node="example.node" hasChildren="true">hasChildren works</tree:nodeMatch>
		<tree:nodeMatch node="example.node" type="com*">wildcard works</tree:nodeMatch>
	
		<tree:nodeMatch node="example.node" isFirstChild="true" isLastChild="true">   (First + Last) </tree:nodeMatch>
		<tree:nodeMatch node="example.node" isFirstChild="false" isLastChild="false"> (Middle) </tree:nodeMatch>
		-->
		</td>
		</tr>
		</table></td></tr>
	</tree:tree>
	</table>
</logic:present>

</body>

</html>