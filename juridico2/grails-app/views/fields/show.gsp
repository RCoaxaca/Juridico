
<%@ page import="catalogos.Fields" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fields.label', default: 'Fields')}" />
		
	</head>
	<body>
		<a href="#show-fields" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fields" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fields">
			
				<g:if test="${fieldsInstance?.acta}">
				<li class="fieldcontain">
					<span id="acta-label" class="property-label"><g:message code="fields.acta.label" default="Acta" /></span>
					
						<span class="property-value" aria-labelledby="acta-label"><g:fieldValue bean="${fieldsInstance}" field="acta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fieldsInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="fields.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${fieldsInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fieldsInstance?.nombre2}">
				<li class="fieldcontain">
					<span id="nombre2-label" class="property-label"><g:message code="fields.nombre2.label" default="Nombre2" /></span>
					
						<span class="property-value" aria-labelledby="nombre2-label"><g:fieldValue bean="${fieldsInstance}" field="nombre2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fieldsInstance?.num}">
				<li class="fieldcontain">
					<span id="num-label" class="property-label"><g:message code="fields.num.label" default="Num" /></span>
					
						<span class="property-value" aria-labelledby="num-label"><g:fieldValue bean="${fieldsInstance}" field="num"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fieldsInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="fields.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${fieldsInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fieldsInstance?.id}" />
					<g:link class="edit" action="edit" id="${fieldsInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
