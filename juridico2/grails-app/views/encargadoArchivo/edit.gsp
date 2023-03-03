<%@ page import="catalogos.EncargadoArchivo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo')}" />
		
	</head>
	<body>
		
		<div id="edit-encargadoArchivo" class="content scaffold-edit" role="main">
			<div class="container">
     <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel panel-default">
                        
                        
                      
                        <div class="panel-heading">
                            <h2 class="panel-title text-center">Editar Jefe de Archivo Central</h2>
                        </div>
                        
                        
                        <div class="panel-body">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${encargadoArchivoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${encargadoArchivoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${encargadoArchivoInstance?.id}" />
				<g:hiddenField name="version" value="${encargadoArchivoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="Guardar" />
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
