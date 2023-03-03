
<%@ page import="catalogos.Movimiento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movimiento.label', default: 'Movimiento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-movimiento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-movimiento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
                         <pre>
			<ol class="property-list movimiento">
			
				<g:if test="${movimientoInstance?.entrada}">
				<li class="fieldcontain">
					<span id="entrada-label" class="property-label"><g:message code="movimiento.entrada.label" default="Entrada" /></span>
					
						<span class="property-value" aria-labelledby="entrada-label"><g:formatDate date="${movimientoInstance?.entrada}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoInstance?.salida}">
				<li class="fieldcontain">
					<span id="salida-label" class="property-label"><g:message code="movimiento.salida.label" default="Salida" /></span>
					
						<span class="property-value" aria-labelledby="salida-label"><g:formatDate date="${movimientoInstance?.salida}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoInstance?.usuariopresta}">
				<li class="fieldcontain">
					<span id="usuariopresta-label" class="property-label"><g:message code="movimiento.usuariopresta.label" default="Usuariopresta" /></span>
					
						<span class="property-value" aria-labelledby="usuariopresta-label"><g:link controller="user" action="show" id="${movimientoInstance?.usuariopresta?.id}">${movimientoInstance?.usuariopresta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoInstance?.usuariorecibe}">
				<li class="fieldcontain">
					<span id="usuariorecibe-label" class="property-label"><g:message code="movimiento.usuariorecibe.label" default="Usuariorecibe" /></span>
					
						<span class="property-value" aria-labelledby="usuariorecibe-label"><g:link controller="user" action="show" id="${movimientoInstance?.usuariorecibe?.id}">${movimientoInstance?.usuariorecibe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="movimiento.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${movimientoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoInstance?.numero_expediente}">
				<li class="fieldcontain">
					<span id="numero_expediente-label" class="property-label"><g:message code="movimiento.numero_expediente.label" default="Numeroexpediente" /></span>
					
						<span class="property-value" aria-labelledby="numero_expediente-label"><g:fieldValue bean="${movimientoInstance}" field="numero_expediente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoInstance?.usuarioentrega}">
				<li class="fieldcontain">
					<span id="usuarioentrega-label" class="property-label"><g:message code="movimiento.usuarioentrega.label" default="Usuarioentrega" /></span>
					
						<span class="property-value" aria-labelledby="usuarioentrega-label"><g:fieldValue bean="${movimientoInstance}" field="usuarioentrega"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${movimientoInstance?.id}" />
					<g:link class="edit" action="edit" id="${movimientoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
