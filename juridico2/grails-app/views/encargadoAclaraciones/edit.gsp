<%@ page import="catalogos.EncargadoAclaraciones" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<div id="edit-encargadoAclaraciones" class="content scaffold-edit" role="main">
						<div class="container">
     <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel panel-default">
                        
                        
                      
                        <div class="panel-heading">
                            <h2 class="panel-title text-center">Editar Jefe de Aclaraciones</h2>
                        </div>
                        
                        
                        <div class="panel-body">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${encargadoAclaracionesInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${encargadoAclaracionesInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${encargadoAclaracionesInstance?.id}" />
				<g:hiddenField name="version" value="${encargadoAclaracionesInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
                                </div>
                        
                        </div>
                </div>
            </div>
        </div>
     </div>
		</div>
	</body>
</html>
