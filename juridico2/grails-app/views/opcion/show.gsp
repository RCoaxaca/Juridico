
<%@ page import="catalogos.Opcion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'opcion.label', default: 'Opcion')}" />
		
	</head>
	<body>
		<a href="#show-opcion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-opcion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list opcion">
			
				<g:if test="${opcionInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="opcion.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:link controller="erroresperado" action="show" id="${opcionInstance?.tipo}">${opcionInstance?.tipo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${opcionInstance?.error}">
				<li class="fieldcontain">
					<span id="error-label" class="property-label"><g:message code="opcion.error.label" default="Error" /></span>
					
						<span class="property-value" aria-labelledby="error-label"><g:link controller="tipoerror" action="show" id="${opcionInstance?.error?.id}">${opcionInstance?.error?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${opcionInstance?.exapro}">
				<li class="fieldcontain">
					<span id="exapro-label" class="property-label"><g:message code="opcion.exapro.label" default="Exapro" /></span>
					
						<span class="property-value" aria-labelledby="exapro-label"><g:fieldValue bean="${opcionInstance}" field="exapro"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${opcionInstance?.id}" />
					<g:link class="edit" action="edit" id="${opcionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
