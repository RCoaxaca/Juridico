
<%@ page import="catalogos.Venta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'venta.label', default: 'Venta')}" />
		
	</head>
	<body>
		<a href="#show-venta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-venta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list venta">
			
				<g:if test="${ventaInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="venta.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${ventaInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.folioexp}">
				<li class="fieldcontain">
					<span id="folioexp-label" class="property-label"><g:message code="venta.folioexp.label" default="Folioexp" /></span>
					
						<span class="property-value" aria-labelledby="folioexp-label"><g:fieldValue bean="${ventaInstance}" field="folioexp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="venta.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:link controller="docesta" action="show" id="${ventaInstance?.estado?.id}">${ventaInstance?.estado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.obser}">
				<li class="fieldcontain">
					<span id="obser-label" class="property-label"><g:message code="venta.obser.label" default="Obser" /></span>
					
						<span class="property-value" aria-labelledby="obser-label"><g:fieldValue bean="${ventaInstance}" field="obser"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.dic}">
				<li class="fieldcontain">
					<span id="dic-label" class="property-label"><g:message code="venta.dic.label" default="Dic" /></span>
					
						<span class="property-value" aria-labelledby="dic-label"><g:link controller="user" action="show" id="${ventaInstance?.dic?.id}">${ventaInstance?.dic?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.apema}">
				<li class="fieldcontain">
					<span id="apema-label" class="property-label"><g:message code="venta.apema.label" default="Apema" /></span>
					
						<span class="property-value" aria-labelledby="apema-label"><g:fieldValue bean="${ventaInstance}" field="apema"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.apepa}">
				<li class="fieldcontain">
					<span id="apepa-label" class="property-label"><g:message code="venta.apepa.label" default="Apepa" /></span>
					
						<span class="property-value" aria-labelledby="apepa-label"><g:fieldValue bean="${ventaInstance}" field="apepa"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.cap}">
				<li class="fieldcontain">
					<span id="cap-label" class="property-label"><g:message code="venta.cap.label" default="Cap" /></span>
					
						<span class="property-value" aria-labelledby="cap-label"><g:link controller="user" action="show" id="${ventaInstance?.cap?.id}">${ventaInstance?.cap?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.fechasol}">
				<li class="fieldcontain">
					<span id="fechasol-label" class="property-label"><g:message code="venta.fechasol.label" default="Fechasol" /></span>
					
						<span class="property-value" aria-labelledby="fechasol-label"><g:formatDate date="${ventaInstance?.fechasol}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ventaInstance?.nomb}">
				<li class="fieldcontain">
					<span id="nomb-label" class="property-label"><g:message code="venta.nomb.label" default="Nomb" /></span>
					
						<span class="property-value" aria-labelledby="nomb-label"><g:fieldValue bean="${ventaInstance}" field="nomb"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ventaInstance?.id}" />
					<g:link class="edit" action="edit" id="${ventaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
