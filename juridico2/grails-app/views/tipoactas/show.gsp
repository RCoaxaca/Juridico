
<%@ page import="catalogos.Tipoactas" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoactas.label', default: 'Tipoactas')}" />
		
	</head>
	<body>
		<a href="#show-tipoactas" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoactas" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoactas">
			
				<g:if test="${tipoactasInstance?.actas}">
				<li class="fieldcontain">
					<span id="actas-label" class="property-label"><g:message code="tipoactas.actas.label" default="Actas" /></span>
					
						<g:each in="${tipoactasInstance.actas}" var="a">
						<span class="property-value" aria-labelledby="actas-label"><g:link controller="scadto" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tipoactasInstance?.tipoacta}">
				<li class="fieldcontain">
					<span id="tipoacta-label" class="property-label"><g:message code="tipoactas.tipoacta.label" default="Tipoacta" /></span>
					
						<span class="property-value" aria-labelledby="tipoacta-label"><g:fieldValue bean="${tipoactasInstance}" field="tipoacta"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoactasInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoactasInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
