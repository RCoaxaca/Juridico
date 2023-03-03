
<%@ page import="catalogos.Scaprn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaprn.label', default: 'Scaprn')}" />
		
	</head>
	<body>
		<a href="#list-scaprn" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scaprn" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="mimp" title="${message(code: 'scaprn.mimp.label', default: 'Mimp')}" />
					
						<g:sortableColumn property="tlet" title="${message(code: 'scaprn.tlet.label', default: 'Tlet')}" />
					
						<g:sortableColumn property="toja" title="${message(code: 'scaprn.toja.label', default: 'Toja')}" />
					
						<g:sortableColumn property="usrid" title="${message(code: 'scaprn.usrid.label', default: 'Usrid')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scaprnInstanceList}" status="i" var="scaprnInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scaprnInstance.id}">${fieldValue(bean: scaprnInstance, field: "mimp")}</g:link></td>
					
						<td>${fieldValue(bean: scaprnInstance, field: "tlet")}</td>
					
						<td>${fieldValue(bean: scaprnInstance, field: "toja")}</td>
					
						<td>${fieldValue(bean: scaprnInstance, field: "usrid")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scaprnInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
