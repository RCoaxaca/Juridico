
<%@ page import="tablas.Scaact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaact.label', default: 'Scaact')}" />
		
	</head>
	<body>
		<a href="#list-scaact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scaact" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numacta" title="${message(code: 'scaact.numacta.label', default: 'Numacta')}" />
					
						<g:sortableColumn property="fechaacta" title="${message(code: 'scaact.fechaacta.label', default: 'Fechaacta')}" />
					
						<th><g:message code="scaact.dto.label" default="Dto" /></th>
					
						<th><g:message code="scaact.mpo.label" default="Mpo" /></th>
					
						<th><g:message code="scaact.loc.label" default="Loc" /></th>
					
						<g:sortableColumn property="pnombre" title="${message(code: 'scaact.pnombre.label', default: 'Pnombre')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scaactInstanceList}" status="i" var="scaactInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scaactInstance.id}">${fieldValue(bean: scaactInstance, field: "numacta")}</g:link></td>
					
						<td><g:formatDate date="${scaactInstance.fechaacta}" /></td>
					
						<td>${fieldValue(bean: scaactInstance, field: "dto")}</td>
					
						<td>${fieldValue(bean: scaactInstance, field: "mpo")}</td>
					
						<td>${fieldValue(bean: scaactInstance, field: "loc")}</td>
					
						<td>${fieldValue(bean: scaactInstance, field: "pnombre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scaactInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
