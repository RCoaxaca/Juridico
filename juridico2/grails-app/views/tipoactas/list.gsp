
<%@ page import="catalogos.Tipoactas" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoactas.label', default: 'Tipoactas')}" />
		
	</head>
	<body>
		<a href="#list-tipoactas" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoactas" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="tipoacta" title="${message(code: 'tipoactas.tipoacta.label', default: 'Tipoacta')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoactasInstanceList}" status="i" var="tipoactasInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoactasInstance.id}">${fieldValue(bean: tipoactasInstance, field: "tipoacta")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoactasInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
