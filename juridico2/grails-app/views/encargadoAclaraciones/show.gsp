
<%@ page import="catalogos.EncargadoAclaraciones" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-encargadoAclaraciones" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-encargadoAclaraciones" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list encargadoAclaraciones">
			
				<g:if test="${encargadoAclaracionesInstance?.fin}">
				<li class="fieldcontain">
					<span id="fin-label" class="property-label"><g:message code="encargadoAclaraciones.fin.label" default="Fin" /></span>
					
						<span class="property-value" aria-labelledby="fin-label"><g:formatDate date="${encargadoAclaracionesInstance?.fin}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoAclaracionesInstance?.ape_mat}">
				<li class="fieldcontain">
					<span id="ape_mat-label" class="property-label"><g:message code="encargadoAclaraciones.ape_mat.label" default="Apemat" /></span>
					
						<span class="property-value" aria-labelledby="ape_mat-label"><g:fieldValue bean="${encargadoAclaracionesInstance}" field="ape_mat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoAclaracionesInstance?.activo}">
				<li class="fieldcontain">
					<span id="activo-label" class="property-label"><g:message code="encargadoAclaraciones.activo.label" default="Activo" /></span>
					
						<span class="property-value" aria-labelledby="activo-label"><g:formatBoolean boolean="${encargadoAclaracionesInstance?.activo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoAclaracionesInstance?.ape_pat}">
				<li class="fieldcontain">
					<span id="ape_pat-label" class="property-label"><g:message code="encargadoAclaraciones.ape_pat.label" default="Apepat" /></span>
					
						<span class="property-value" aria-labelledby="ape_pat-label"><g:fieldValue bean="${encargadoAclaracionesInstance}" field="ape_pat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoAclaracionesInstance?.inicio}">
				<li class="fieldcontain">
					<span id="inicio-label" class="property-label"><g:message code="encargadoAclaraciones.inicio.label" default="Inicio" /></span>
					
						<span class="property-value" aria-labelledby="inicio-label"><g:formatDate date="${encargadoAclaracionesInstance?.inicio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoAclaracionesInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="encargadoAclaraciones.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${encargadoAclaracionesInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${encargadoAclaracionesInstance?.id}" />
					<g:link class="edit" action="edit" id="${encargadoAclaracionesInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
