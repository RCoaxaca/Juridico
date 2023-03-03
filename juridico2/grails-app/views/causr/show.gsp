
<%@ page import="catalogos.Causr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'causr.label', default: 'Causr')}" />
	
	</head>
	<body>
		<a href="#show-causr" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-causr" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list causr">
			
				<g:if test="${causrInstance?.passw}">
				<li class="fieldcontain">
					<span id="passw-label" class="property-label"><g:message code="causr.passw.label" default="Passw" /></span>
					
						<span class="property-value" aria-labelledby="passw-label"><g:fieldValue bean="${causrInstance}" field="passw"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${causrInstance?.clv}">
				<li class="fieldcontain">
					<span id="clv-label" class="property-label"><g:message code="causr.clv.label" default="Clv" /></span>
					
						<span class="property-value" aria-labelledby="clv-label"><g:fieldValue bean="${causrInstance}" field="clv"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${causrInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="causr.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${causrInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${causrInstance?.outdate}">
				<li class="fieldcontain">
					<span id="outdate-label" class="property-label"><g:message code="causr.outdate.label" default="Outdate" /></span>
					
						<span class="property-value" aria-labelledby="outdate-label"><g:formatDate date="${causrInstance?.outdate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${causrInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="causr.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${causrInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${causrInstance?.updates}">
				<li class="fieldcontain">
					<span id="updates-label" class="property-label"><g:message code="causr.updates.label" default="Updates" /></span>
					
						<span class="property-value" aria-labelledby="updates-label"><g:formatDate date="${causrInstance?.updates}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${causrInstance?.usrtype}">
				<li class="fieldcontain">
					<span id="usrtype-label" class="property-label"><g:message code="causr.usrtype.label" default="Usrtype" /></span>
					
						<span class="property-value" aria-labelledby="usrtype-label"><g:fieldValue bean="${causrInstance}" field="usrtype"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${causrInstance?.id}" />
					<g:link class="edit" action="edit" id="${causrInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
