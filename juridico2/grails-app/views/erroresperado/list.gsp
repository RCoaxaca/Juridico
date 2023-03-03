
<%@ page import="catalogos.Erroresperado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'erroresperado.label', default: 'Erroresperado')}" />
		
	</head>
	<body>
		<a href="#list-erroresperado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-erroresperado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="tipodeerror" title="${message(code: 'erroresperado.tipodeerror.label', default: 'Tipodeerror')}" />
					
						<th><g:message code="erroresperado.tipoerr.label" default="Tipoerr" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${erroresperadoInstanceList}" status="i" var="erroresperadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${erroresperadoInstance.id}">${fieldValue(bean: erroresperadoInstance, field: "tipodeerror")}</g:link></td>
					
						<td>${fieldValue(bean: erroresperadoInstance, field: "tipoerr")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${erroresperadoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
