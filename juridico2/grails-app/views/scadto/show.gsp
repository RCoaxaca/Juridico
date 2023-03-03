
<%@ page import="catalogos.Scadto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scadto.label', default: 'Scadto')}" />
		
	</head>
	<body>
		<a href="#show-scadto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-scadto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scadto">
			
				<g:if test="${scadtoInstance?.clv}">
				<li class="fieldcontain">
					<span id="clv-label" class="property-label"><g:message code="scadto.clv.label" default="Clv" /></span>
					
						<span class="property-value" aria-labelledby="clv-label"><g:fieldValue bean="${scadtoInstance}" field="clv"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scadtoInstance?.clvreg}">
				<li class="fieldcontain">
					<span id="clvreg-label" class="property-label"><g:message code="scadto.clvreg.label" default="Clvreg" /></span>
					
						<span class="property-value" aria-labelledby="clvreg-label"><g:fieldValue bean="${scadtoInstance}" field="clvreg"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scadtoInstance?.descc}">
				<li class="fieldcontain">
					<span id="descc-label" class="property-label"><g:message code="scadto.descc.label" default="Descc" /></span>
					
						<span class="property-value" aria-labelledby="descc-label"><g:fieldValue bean="${scadtoInstance}" field="descc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scadtoInstance?.municipios}">
				<li class="fieldcontain">
					<span id="municipios-label" class="property-label"><g:message code="scadto.municipios.label" default="Municipios" /></span>
					
						<g:each in="${scadtoInstance.municipios}" var="m">
						<span class="property-value" aria-labelledby="municipios-label"><g:link controller="scampo" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scadtoInstance?.id}" />
					<g:link class="edit" action="edit" id="${scadtoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
