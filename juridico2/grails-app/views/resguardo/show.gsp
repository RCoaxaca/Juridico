
<%@ page import="catalogos.Resguardo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'resguardo.label', default: 'Resguardo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-resguardo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-resguardo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list resguardo">
			
				<g:if test="${resguardoInstance?.papeleta}">
				<li class="fieldcontain">
					<span id="papeleta-label" class="property-label"><g:message code="resguardo.papeleta.label" default="Papeleta" /></span>
					
						<span class="property-value" aria-labelledby="papeleta-label"><g:formatBoolean boolean="${resguardoInstance?.papeleta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${resguardoInstance?.resolucion}">
				<li class="fieldcontain">
					<span id="resolucion-label" class="property-label"><g:message code="resguardo.resolucion.label" default="Resolucion" /></span>
					
						<span class="property-value" aria-labelledby="resolucion-label"><g:formatBoolean boolean="${resguardoInstance?.resolucion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${resguardoInstance?.entrega}">
				<li class="fieldcontain">
					<span id="entrega-label" class="property-label"><g:message code="resguardo.entrega.label" default="Entrega" /></span>
					
						<span class="property-value" aria-labelledby="entrega-label"><g:fieldValue bean="${resguardoInstance}" field="entrega"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resguardoInstance?.fecha_entrada}">
				<li class="fieldcontain">
					<span id="fecha_entrada-label" class="property-label"><g:message code="resguardo.fecha_entrada.label" default="Fechaentrada" /></span>
					
						<span class="property-value" aria-labelledby="fecha_entrada-label"><g:formatDate date="${resguardoInstance?.fecha_entrada}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${resguardoInstance?.numero_expediente}">
				<li class="fieldcontain">
					<span id="numero_expediente-label" class="property-label"><g:message code="resguardo.numero_expediente.label" default="Numeroexpediente" /></span>
					
						<span class="property-value" aria-labelledby="numero_expediente-label"><g:fieldValue bean="${resguardoInstance}" field="numero_expediente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resguardoInstance?.ubicacion}">
				<li class="fieldcontain">
					<span id="ubicacion-label" class="property-label"><g:message code="resguardo.ubicacion.label" default="Ubicacion" /></span>
					
						<span class="property-value" aria-labelledby="ubicacion-label"><g:fieldValue bean="${resguardoInstance}" field="ubicacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resguardoInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="resguardo.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="user" action="show" id="${resguardoInstance?.usuario?.id}">${resguardoInstance?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${resguardoInstance?.id}" />
					<g:link class="edit" action="edit" id="${resguardoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
