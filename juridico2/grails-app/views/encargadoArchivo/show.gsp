
<%@ page import="catalogos.EncargadoArchivo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo')}" />
		
	</head>
	<body>
		
		<div id="show-encargadoArchivo" class="content scaffold-show" role="main">
			<h1>Encargado Archivo Central</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list encargadoArchivo">
			
				<g:if test="${encargadoArchivoInstance?.activo}">
				<li class="fieldcontain">
					<span id="activo-label" class="property-label"><g:message code="encargadoArchivo.activo.label" default="Activo" /></span>
					
						<span class="property-value" aria-labelledby="activo-label"><g:formatBoolean boolean="${encargadoArchivoInstance?.activo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoArchivoInstance?.ape_mat}">
				<li class="fieldcontain">
					<span id="ape_mat-label" class="property-label"><g:message code="encargadoArchivo.ape_mat.label" default="Apemat" /></span>
					
						<span class="property-value" aria-labelledby="ape_mat-label"><g:fieldValue bean="${encargadoArchivoInstance}" field="ape_mat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoArchivoInstance?.ape_pat}">
				<li class="fieldcontain">
					<span id="ape_pat-label" class="property-label"><g:message code="encargadoArchivo.ape_pat.label" default="Apepat" /></span>
					
						<span class="property-value" aria-labelledby="ape_pat-label"><g:fieldValue bean="${encargadoArchivoInstance}" field="ape_pat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoArchivoInstance?.fin}">
				<li class="fieldcontain">
					<span id="fin-label" class="property-label"><g:message code="encargadoArchivo.fin.label" default="Fin" /></span>
					
						<span class="property-value" aria-labelledby="fin-label"><g:formatDate date="${encargadoArchivoInstance?.fin}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoArchivoInstance?.inicio}">
				<li class="fieldcontain">
					<span id="inicio-label" class="property-label"><g:message code="encargadoArchivo.inicio.label" default="Inicio" /></span>
					
						<span class="property-value" aria-labelledby="inicio-label"><g:formatDate date="${encargadoArchivoInstance?.inicio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoArchivoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="encargadoArchivo.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${encargadoArchivoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${encargadoArchivoInstance?.id}" />
					<g:link class="edit" action="edit" id="${encargadoArchivoInstance?.id}"><g:message code="default.button.editar.label" default="Editar" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.eliminar.label', default: 'Borrar')}" onclick="return confirm('${message(code: 'default.button.eliminar.confirm.message', default: 'Desea continuar?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
