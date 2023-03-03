<%@ page import="catalogos.EncargadoJuridico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico')}" />
		
	</head>
	<body>
		
		<div id="edit-encargadoJuridico" class="content scaffold-edit" role="main">
			<div class="container">
     <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel panel-default">
                        
                        
                      
                        <div class="panel-heading">
                            <h2 class="panel-title text-center">Editar Jefe de Unidad</h2>
                        </div>
                        
                        
                        <div class="panel-body">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${encargadoJuridicoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${encargadoJuridicoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${encargadoJuridicoInstance?.id}" />
				<g:hiddenField name="version" value="${encargadoJuridicoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.Actualizar.label', default: 'Actualizar')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.Eliminar.label', default: 'Eliminar')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Desea Continuar?')}');" />
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
