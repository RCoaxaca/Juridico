
<%@ page import="catalogos.Scapro2" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scapro2.label', default: 'Scapro2')}" />
		
	</head>
	<body>
		<a href="#list-scapro2" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scapro2" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="prog" title="${message(code: 'scapro2.prog.label', default: 'Prog')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scapro2InstanceList}" status="i" var="scapro2Instance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scapro2Instance.id}">${fieldValue(bean: scapro2Instance, field: "prog")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scapro2InstanceTotal}" />
			</div>
		</div>
	</body>
</html>
