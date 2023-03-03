
<%@ page import="catalogos.Venta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'venta.label', default: 'Venta')}" />
		
	</head>
	<body>
		<a href="#list-venta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-venta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="folio" title="${message(code: 'venta.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="folioexp" title="${message(code: 'venta.folioexp.label', default: 'Folioexp')}" />
					
						<th><g:message code="venta.estado.label" default="Estado" /></th>
					
						<g:sortableColumn property="obser" title="${message(code: 'venta.obser.label', default: 'Obser')}" />
					
						<th><g:message code="venta.dic.label" default="Dic" /></th>
					
						<g:sortableColumn property="apema" title="${message(code: 'venta.apema.label', default: 'Apema')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ventaInstanceList}" status="i" var="ventaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ventaInstance.id}">${fieldValue(bean: ventaInstance, field: "folio")}</g:link></td>
					
						<td>${fieldValue(bean: ventaInstance, field: "folioexp")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "obser")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "dic")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "apema")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ventaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
