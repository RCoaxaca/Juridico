
<%@ page import="catalogos.Tipoerror" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoerror.label', default: 'Tipoerror')}" />
		
	</head>
	<body>
		<a href="#list-tipoerror" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoerror" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="tipoerror" title="${message(code: 'tipoerror.tipoerror.label', default: 'Tipoerror')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoerrorInstanceList}" status="i" var="tipoerrorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoerrorInstance.id}">${fieldValue(bean: tipoerrorInstance, field: "tipoerror")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoerrorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
