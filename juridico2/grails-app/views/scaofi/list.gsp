
<%@ page import="catalogos.Scaofi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaofi.label', default: 'Scaofi')}" />
		
	</head>
	<body>
		<a href="#list-scaofi" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scaofi" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="scaofi.clv.label" default="Clv" /></th>
					
						<g:sortableColumn property="clv2" title="${message(code: 'scaofi.clv2.label', default: 'Clv2')}" />
					
						<g:sortableColumn property="descrip" title="${message(code: 'scaofi.descrip.label', default: 'Descrip')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scaofiInstanceList}" status="i" var="scaofiInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scaofiInstance.id}">${fieldValue(bean: scaofiInstance, field: "clv")}</g:link></td>
					
						<td>${fieldValue(bean: scaofiInstance, field: "clv2")}</td>
					
						<td>${fieldValue(bean: scaofiInstance, field: "descrip")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scaofiInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
