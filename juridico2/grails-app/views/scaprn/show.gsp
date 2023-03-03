
<%@ page import="catalogos.Scaprn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaprn.label', default: 'Scaprn')}" />
		
	</head>
	<body>
		<a href="#show-scaprn" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-scaprn" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scaprn">
			
				<g:if test="${scaprnInstance?.mimp}">
				<li class="fieldcontain">
					<span id="mimp-label" class="property-label"><g:message code="scaprn.mimp.label" default="Mimp" /></span>
					
						<span class="property-value" aria-labelledby="mimp-label"><g:fieldValue bean="${scaprnInstance}" field="mimp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaprnInstance?.tlet}">
				<li class="fieldcontain">
					<span id="tlet-label" class="property-label"><g:message code="scaprn.tlet.label" default="Tlet" /></span>
					
						<span class="property-value" aria-labelledby="tlet-label"><g:fieldValue bean="${scaprnInstance}" field="tlet"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaprnInstance?.toja}">
				<li class="fieldcontain">
					<span id="toja-label" class="property-label"><g:message code="scaprn.toja.label" default="Toja" /></span>
					
						<span class="property-value" aria-labelledby="toja-label"><g:fieldValue bean="${scaprnInstance}" field="toja"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaprnInstance?.usrid}">
				<li class="fieldcontain">
					<span id="usrid-label" class="property-label"><g:message code="scaprn.usrid.label" default="Usrid" /></span>
					
						<span class="property-value" aria-labelledby="usrid-label"><g:fieldValue bean="${scaprnInstance}" field="usrid"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scaprnInstance?.id}" />
					<g:link class="edit" action="edit" id="${scaprnInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
