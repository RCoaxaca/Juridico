
<%@ page import="catalogos.Causr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'causr.label', default: 'Causr')}" />
		
	</head>
	<body>
		<a href="#list-causr" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-causr" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="passw" title="${message(code: 'causr.passw.label', default: 'Passw')}" />
					
						<g:sortableColumn property="clv" title="${message(code: 'causr.clv.label', default: 'Clv')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'causr.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="outdate" title="${message(code: 'causr.outdate.label', default: 'Outdate')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'causr.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="updates" title="${message(code: 'causr.updates.label', default: 'Updates')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${causrInstanceList}" status="i" var="causrInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${causrInstance.id}">${fieldValue(bean: causrInstance, field: "passw")}</g:link></td>
					
						<td>${fieldValue(bean: causrInstance, field: "clv")}</td>
					
						<td>${fieldValue(bean: causrInstance, field: "nombre")}</td>
					
						<td><g:formatDate date="${causrInstance.outdate}" /></td>
					
						<td>${fieldValue(bean: causrInstance, field: "status")}</td>
					
						<td><g:formatDate date="${causrInstance.updates}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${causrInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
