
<%@ page import="catalogos.Localidades" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'localidades.label', default: 'Localidades')}" />
		
	</head>
	<body>
		<a href="#list-localidades" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-localidades" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="localidad" title="${message(code: 'localidades.localidad.label', default: 'Localidad')}" />
					
						<th><g:message code="localidades.mpo.label" default="Mpo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${localidadesInstanceList}" status="i" var="localidadesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${localidadesInstance.id}">${fieldValue(bean: localidadesInstance, field: "localidad")}</g:link></td>
					
						<td>${fieldValue(bean: localidadesInstance, field: "mpo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${localidadesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
