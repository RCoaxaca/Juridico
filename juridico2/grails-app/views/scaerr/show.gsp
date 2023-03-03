
<%@ page import="tablas.Scaerr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaerr.label', default: 'Scaerr')}" />
		
	</head>
	<body>
		<a href="#show-scaerr" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-scaerr" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scaerr">
			
				<g:if test="${scaerrInstance?.base}">
				<li class="fieldcontain">
					<span id="base-label" class="property-label"><g:message code="scaerr.base.label" default="Base" /></span>
					
						<span class="property-value" aria-labelledby="base-label"><g:fieldValue bean="${scaerrInstance}" field="base"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.campo}">
				<li class="fieldcontain">
					<span id="campo-label" class="property-label"><g:message code="scaerr.campo.label" default="Campo" /></span>
					
						<span class="property-value" aria-labelledby="campo-label"><g:fieldValue bean="${scaerrInstance}" field="campo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.contiene}">
				<li class="fieldcontain">
					<span id="contiene-label" class="property-label"><g:message code="scaerr.contiene.label" default="Contiene" /></span>
					
						<span class="property-value" aria-labelledby="contiene-label"><g:fieldValue bean="${scaerrInstance}" field="contiene"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.debeser}">
				<li class="fieldcontain">
					<span id="debeser-label" class="property-label"><g:message code="scaerr.debeser.label" default="Debeser" /></span>
					
						<span class="property-value" aria-labelledby="debeser-label"><g:fieldValue bean="${scaerrInstance}" field="debeser"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.donde}">
				<li class="fieldcontain">
					<span id="donde-label" class="property-label"><g:message code="scaerr.donde.label" default="Donde" /></span>
					
						<span class="property-value" aria-labelledby="donde-label"><g:fieldValue bean="${scaerrInstance}" field="donde"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.expano}">
				<li class="fieldcontain">
					<span id="expano-label" class="property-label"><g:message code="scaerr.expano.label" default="Expano" /></span>
					
						<span class="property-value" aria-labelledby="expano-label"><g:fieldValue bean="${scaerrInstance}" field="expano"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.expro}">
				<li class="fieldcontain">
					<span id="expro-label" class="property-label"><g:message code="scaerr.expro.label" default="Expro" /></span>
					
						<span class="property-value" aria-labelledby="expro-label"><g:fieldValue bean="${scaerrInstance}" field="expro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.procede}">
				<li class="fieldcontain">
					<span id="procede-label" class="property-label"><g:message code="scaerr.procede.label" default="Procede" /></span>
					
						<span class="property-value" aria-labelledby="procede-label"><g:fieldValue bean="${scaerrInstance}" field="procede"/></span>
					
				</li>
				</g:if>
			
			
				<g:if test="${scaerrInstance?.tcorrect}">
				<li class="fieldcontain">
					<span id="tcorrect-label" class="property-label"><g:message code="scaerr.tcorrect.label" default="Tcorrect" /></span>
					
						<span class="property-value" aria-labelledby="tcorrect-label"><g:fieldValue bean="${scaerrInstance}" field="tcorrect"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaerrInstance?.terror}">
				<li class="fieldcontain">
					<span id="terror-label" class="property-label"><g:message code="scaerr.terror.label" default="Terror" /></span>
					
						<span class="property-value" aria-labelledby="terror-label"><g:fieldValue bean="${scaerrInstance}" field="terror"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scaerrInstance?.id}" />
					<g:link class="edit" action="edit" id="${scaerrInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
