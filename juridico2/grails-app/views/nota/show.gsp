
<%@ page import="tablas.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nota.label', default: 'Nota')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-nota" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-nota" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list nota">
			
				<g:if test="${notaInstance?.namofi}">
				<li class="fieldcontain">
					<span id="namofi-label" class="property-label"><g:message code="nota.namofi.label" default="Namofi" /></span>
					
						<span class="property-value" aria-labelledby="namofi-label"><g:fieldValue bean="${notaInstance}" field="namofi"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.dic}">
				<li class="fieldcontain">
					<span id="dic-label" class="property-label"><g:message code="nota.dic.label" default="Dic" /></span>
					
						<span class="property-value" aria-labelledby="dic-label"><g:fieldValue bean="${notaInstance}" field="dic"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.idn}">
				<li class="fieldcontain">
					<span id="idn-label" class="property-label"><g:message code="nota.idn.label" default="Idn" /></span>
					
						<span class="property-value" aria-labelledby="idn-label"><g:fieldValue bean="${notaInstance}" field="idn"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.expano}">
				<li class="fieldcontain">
					<span id="expano-label" class="property-label"><g:message code="nota.expano.label" default="Expano" /></span>
					
						<span class="property-value" aria-labelledby="expano-label"><g:fieldValue bean="${notaInstance}" field="expano"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.expro}">
				<li class="fieldcontain">
					<span id="expro-label" class="property-label"><g:message code="nota.expro.label" default="Expro" /></span>
					
						<span class="property-value" aria-labelledby="expro-label"><g:fieldValue bean="${notaInstance}" field="expro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.nota}">
				<li class="fieldcontain">
					<span id="nota-label" class="property-label"><g:message code="nota.nota.label" default="Nota" /></span>
					
						<span class="property-value" aria-labelledby="nota-label"><g:fieldValue bean="${notaInstance}" field="nota"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${notaInstance?.id}" />
					<g:link class="edit" action="edit" id="${notaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
