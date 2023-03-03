
<%@ page import="catalogos.Erroresperado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'erroresperado.label', default: 'Erroresperado')}" />
		
	</head>
	<body>
		<a href="#show-erroresperado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-erroresperado" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list erroresperado">
			
				<g:if test="${erroresperadoInstance?.tipodeerror}">
				<li class="fieldcontain">
					<span id="tipodeerror-label" class="property-label"><g:message code="erroresperado.tipodeerror.label" default="Tipodeerror" /></span>
					
						<span class="property-value" aria-labelledby="tipodeerror-label"><g:fieldValue bean="${erroresperadoInstance}" field="tipodeerror"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${erroresperadoInstance?.tipoerr}">
				<li class="fieldcontain">
					<span id="tipoerr-label" class="property-label"><g:message code="erroresperado.tipoerr.label" default="Tipoerr" /></span>
					
						<span class="property-value" aria-labelledby="tipoerr-label"><g:link controller="tipoerror" action="show" id="${erroresperadoInstance?.tipoerr?.id}">${erroresperadoInstance?.tipoerr?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${erroresperadoInstance?.id}" />
					<g:link class="edit" action="edit" id="${erroresperadoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
