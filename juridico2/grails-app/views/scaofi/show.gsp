
<%@ page import="catalogos.Scaofi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaofi.label', default: 'Scaofi')}" />
		
	</head>
	<body>
		<a href="#show-scaofi" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-scaofi" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scaofi">
			
				<g:if test="${scaofiInstance?.clv}">
				<li class="fieldcontain">
					<span id="clv-label" class="property-label"><g:message code="scaofi.clv.label" default="Clv" /></span>
					
						<span class="property-value" aria-labelledby="clv-label"><g:link controller="scampo" action="show" id="${scaofiInstance?.clv?.id}">${scaofiInstance?.clv?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaofiInstance?.clv2}">
				<li class="fieldcontain">
					<span id="clv2-label" class="property-label"><g:message code="scaofi.clv2.label" default="Clv2" /></span>
					
						<span class="property-value" aria-labelledby="clv2-label"><g:fieldValue bean="${scaofiInstance}" field="clv2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaofiInstance?.descrip}">
				<li class="fieldcontain">
					<span id="descrip-label" class="property-label"><g:message code="scaofi.descrip.label" default="Descrip" /></span>
					
						<span class="property-value" aria-labelledby="descrip-label"><g:fieldValue bean="${scaofiInstance}" field="descrip"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scaofiInstance?.id}" />
					<g:link class="edit" action="edit" id="${scaofiInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
