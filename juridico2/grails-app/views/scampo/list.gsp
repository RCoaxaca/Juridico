
<%@ page import="catalogos.Scampo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scampo.label', default: 'Scampo')}" />
		
	</head>
	<body>
		<a href="#list-scampo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scampo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descrip" title="${message(code: 'scampo.descrip.label', default: 'Descrip')}" />
					
						<th><g:message code="scampo.distrito.label" default="Distrito" /></th>
					
						<g:sortableColumn property="mpo" title="${message(code: 'scampo.mpo.label', default: 'Mpo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scampoInstanceList}" status="i" var="scampoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scampoInstance.id}">${fieldValue(bean: scampoInstance, field: "descrip")}</g:link></td>
					
						<td>${fieldValue(bean: scampoInstance, field: "distrito")}</td>
					
						<td>${fieldValue(bean: scampoInstance, field: "mpo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scampoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
