
<%@ page import="tablas.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nota.label', default: 'Nota')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-nota" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-nota" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="namofi" title="${message(code: 'nota.namofi.label', default: 'Namofi')}" />
					
						<g:sortableColumn property="dic" title="${message(code: 'nota.dic.label', default: 'Dic')}" />
					
						<g:sortableColumn property="idn" title="${message(code: 'nota.idn.label', default: 'Idn')}" />
					
						<g:sortableColumn property="expano" title="${message(code: 'nota.expano.label', default: 'Expano')}" />
					
						<g:sortableColumn property="expro" title="${message(code: 'nota.expro.label', default: 'Expro')}" />
					
						<g:sortableColumn property="nota" title="${message(code: 'nota.nota.label', default: 'Nota')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${notaInstanceList}" status="i" var="notaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${notaInstance.id}">${fieldValue(bean: notaInstance, field: "namofi")}</g:link></td>
					
						<td>${fieldValue(bean: notaInstance, field: "dic")}</td>
					
						<td>${fieldValue(bean: notaInstance, field: "idn")}</td>
					
						<td>${fieldValue(bean: notaInstance, field: "expano")}</td>
					
						<td>${fieldValue(bean: notaInstance, field: "expro")}</td>
					
						<td>${fieldValue(bean: notaInstance, field: "nota")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${notaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
