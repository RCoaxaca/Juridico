
<%@ page import="catalogos.Opcion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'opcion.label', default: 'Opcion')}" />
		
	</head>
	<body>
		<a href="#list-opcion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-opcion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="opcion.tipo.label" default="Tipo" /></th>
					
						<th><g:message code="opcion.error.label" default="Error" /></th>
					
						<g:sortableColumn property="exapro" title="${message(code: 'opcion.exapro.label', default: 'Exapro')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${opcionInstanceList}" status="i" var="opcionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${opcionInstance.id}">${fieldValue(bean: opcionInstance, field: "tipo")}</g:link></td>
					
						<td>${fieldValue(bean: opcionInstance, field: "error")}</td>
					
						<td>${fieldValue(bean: opcionInstance, field: "exapro")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${opcionInstanceTotal}" />
			</div>
		</div>
                               
	</body>
</html>
