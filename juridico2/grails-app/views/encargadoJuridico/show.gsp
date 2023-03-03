
<%@ page import="catalogos.EncargadoJuridico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico')}" />
		
	</head>
	<body>
		
		<div id="show-encargadoJuridico" class="content scaffold-show" role="main">
			<h1>Encargado de Unidad Juridica</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list encargadoJuridico">
			
				<g:if test="${encargadoJuridicoInstance?.activo}">
				<li class="fieldcontain">
					<span id="activo-label" class="property-label"><g:message code="encargadoJuridico.activo.label" default="Activo" /></span>
					
						<span class="property-value" aria-labelledby="activo-label"><g:formatBoolean boolean="${encargadoJuridicoInstance?.activo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoJuridicoInstance?.ape_mat}">
				<li class="fieldcontain">
					<span id="ape_mat-label" class="property-label"><g:message code="encargadoJuridico.ape_mat.label" default="Apemat" /></span>
					
						<span class="property-value" aria-labelledby="ape_mat-label"><g:fieldValue bean="${encargadoJuridicoInstance}" field="ape_mat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoJuridicoInstance?.ape_pat}">
				<li class="fieldcontain">
					<span id="ape_pat-label" class="property-label"><g:message code="encargadoJuridico.ape_pat.label" default="Apepat" /></span>
					
						<span class="property-value" aria-labelledby="ape_pat-label"><g:fieldValue bean="${encargadoJuridicoInstance}" field="ape_pat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoJuridicoInstance?.fin}">
				<li class="fieldcontain">
					<span id="fin-label" class="property-label"><g:message code="encargadoJuridico.fin.label" default="Fin" /></span>
					
						<span class="property-value" aria-labelledby="fin-label"><g:formatDate date="${encargadoJuridicoInstance?.fin}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoJuridicoInstance?.inicio}">
				<li class="fieldcontain">
					<span id="inicio-label" class="property-label"><g:message code="encargadoJuridico.inicio.label" default="Inicio" /></span>
					
						<span class="property-value" aria-labelledby="inicio-label"><g:formatDate date="${encargadoJuridicoInstance?.inicio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${encargadoJuridicoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="encargadoJuridico.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${encargadoJuridicoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${encargadoJuridicoInstance?.id}" />
					<g:link class="edit" action="edit" id="${encargadoJuridicoInstance?.id}"><g:message code="default.button.editar.label" default="Editar" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.eliminar.label', default: 'Borrar')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Desea Continuar?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
