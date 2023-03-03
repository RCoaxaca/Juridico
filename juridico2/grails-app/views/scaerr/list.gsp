
<%@ page import="tablas.Scaerr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaerr.label', default: 'Scaerr')}" />
		
	</head>
	<body>
		<a href="#list-scaerr" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scaerr" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="base" title="${message(code: 'scaerr.base.label', default: 'Base')}" />
					
						<g:sortableColumn property="campo" title="${message(code: 'scaerr.campo.label', default: 'Campo')}" />
					
						<g:sortableColumn property="contiene" title="${message(code: 'scaerr.contiene.label', default: 'Contiene')}" />
					
						<g:sortableColumn property="debeser" title="${message(code: 'scaerr.debeser.label', default: 'Debeser')}" />
					
						<g:sortableColumn property="donde" title="${message(code: 'scaerr.donde.label', default: 'Donde')}" />
					
						<g:sortableColumn property="expano" title="${message(code: 'scaerr.expano.label', default: 'Expano')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scaerrInstanceList}" status="i" var="scaerrInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scaerrInstance.id}">${fieldValue(bean: scaerrInstance, field: "base")}</g:link></td>
					
						<td>${fieldValue(bean: scaerrInstance, field: "campo")}</td>
					
						<td>${fieldValue(bean: scaerrInstance, field: "contiene")}</td>
					
						<td>${fieldValue(bean: scaerrInstance, field: "debeser")}</td>
					
						<td>${fieldValue(bean: scaerrInstance, field: "donde")}</td>
					
						<td>${fieldValue(bean: scaerrInstance, field: "expano")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scaerrInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
