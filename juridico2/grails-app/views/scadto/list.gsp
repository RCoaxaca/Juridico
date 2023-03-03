
<%@ page import="catalogos.Scadto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scadto.label', default: 'Scadto')}" />
		
	</head>
	<body>
		<a href="#list-scadto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scadto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clv" title="${message(code: 'scadto.clv.label', default: 'Clv')}" />
					
						<g:sortableColumn property="clvreg" title="${message(code: 'scadto.clvreg.label', default: 'Clvreg')}" />
					
						<g:sortableColumn property="descc" title="${message(code: 'scadto.descc.label', default: 'Descc')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scadtoInstanceList}" status="i" var="scadtoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scadtoInstance.id}">${fieldValue(bean: scadtoInstance, field: "clv")}</g:link></td>
					
						<td>${fieldValue(bean: scadtoInstance, field: "clvreg")}</td>
					
						<td>${fieldValue(bean: scadtoInstance, field: "descc")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scadtoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
