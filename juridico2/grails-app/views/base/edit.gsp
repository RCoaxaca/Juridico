<%@ page import="catalogos.Base" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'base.label', default: 'Base')}" />
		
	</head>
	<body>
		
		<div id="edit-base" class="content scaffold-edit" role="main">
			<h1 class="text-center">Editar Bases</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${baseInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${baseInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                         <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
                                  <div class="col-md-offset-1 col-md-10">
                                      <div class="panel panel-default">
			<g:form method="post" >
				<g:hiddenField name="id" value="${baseInstance?.id}" />
				<g:hiddenField name="version" value="${baseInstance?.version}" />
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
	</body>
</html>
