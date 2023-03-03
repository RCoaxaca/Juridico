
<%@ page import="tablas.Scapro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scapro.label', default: 'Scapro')}" />
		
	</head>
	<body>
		<a href="#list-scapro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scapro" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="prog" title="${message(code: 'scapro.prog.label', default: 'Prog')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scaproInstanceList}" status="i" var="scaproInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scaproInstance.id}">${fieldValue(bean: scaproInstance, field: "prog")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scaproInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
