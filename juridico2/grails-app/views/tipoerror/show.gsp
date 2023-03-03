
<%@ page import="catalogos.Tipoerror" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoerror.label', default: 'Tipoerror')}" />
		
	</head>
	<body>
		<a href="#show-tipoerror" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoerror" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoerror">
			
				<g:if test="${tipoerrorInstance?.error}">
				<li class="fieldcontain">
					<span id="error-label" class="property-label"><g:message code="tipoerror.error.label" default="Error" /></span>
					
						<g:each in="${tipoerrorInstance.error}" var="e">
						<span class="property-value" aria-labelledby="error-label"><g:link controller="erroresperado" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tipoerrorInstance?.tipoerror}">
				<li class="fieldcontain">
					<span id="tipoerror-label" class="property-label"><g:message code="tipoerror.tipoerror.label" default="Tipoerror" /></span>
					
						<span class="property-value" aria-labelledby="tipoerror-label"><g:fieldValue bean="${tipoerrorInstance}" field="tipoerror"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoerrorInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoerrorInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
