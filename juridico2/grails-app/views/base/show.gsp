
<%@ page import="catalogos.Base" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'base.label', default: 'Base')}" />
		
	</head>
	<body>
		
		<div id="show-base" class="content scaffold-show" role="main">
			<!--<h1><g:message code="default.show.label" args="[entityName]" /></h1>-->
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list base">
			
				<g:if test="${baseInstance?.acta}">
				<li class="fieldcontain">
					<span id="acta-label" class="property-label"><g:message code="base.acta.label" default="Acta" /></span>
					
						<span class="property-value" aria-labelledby="acta-label"><g:link controller="tipoactas" action="show" id="${baseInstance?.acta?.id}">${baseInstance?.acta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${baseInstance?.base}">
				<li class="fieldcontain">
					<span id="base-label" class="property-label"><g:message code="base.base.label" default="Base" /></span>
					
						<span class="property-value" aria-labelledby="base-label"><g:fieldValue bean="${baseInstance}" field="base"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${baseInstance?.campo}">
				<li class="fieldcontain">
					<span id="campo-label" class="property-label"><g:message code="base.campo.label" default="Campo" /></span>
					
						<span class="property-value" aria-labelledby="campo-label"><g:link controller="fields" action="show" id="${baseInstance?.campo?.id}">${baseInstance?.campo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${baseInstance?.id}" />
					<g:link class="btn btn-primary" action="edit" id="${baseInstance?.id}"><g:message code="default.button.Editar.label" default="Editar" /></g:link>
					<g:actionSubmit class="btn btn-primary" action="delete" value="${message(code: 'default.button.Eliminar.label', default: 'Eliminar')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
