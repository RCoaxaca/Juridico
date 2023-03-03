
<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scasol.label', default: 'Scasol')}" />
		
	</head>
	<body>
		<a href="#list-scasol" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scasol" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="condonado" title="${message(code: 'scasol.condonado.label', default: 'Condonado')}" />
					
						<g:sortableColumn property="sexintere" title="${message(code: 'scasol.sexintere.label', default: 'Sexintere')}" />
					
						<g:sortableColumn property="folio" title="${message(code: 'scasol.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="folioexp" title="${message(code: 'scasol.folioexp.label', default: 'Folioexp')}" />
					
						<th><g:message code="scasol.estado.label" default="Estado" /></th>
					
						<g:sortableColumn property="obser" title="${message(code: 'scasol.obser.label', default: 'Obser')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scasolInstanceList}" status="i" var="scasolInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scasolInstance.id}">${fieldValue(bean: scasolInstance, field: "condonado")}</g:link></td>
					
						<td>${fieldValue(bean: scasolInstance, field: "sexintere")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "folio")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "folioexp")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "obser")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scasolInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
