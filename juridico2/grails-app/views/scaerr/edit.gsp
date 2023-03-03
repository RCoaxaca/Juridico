<%@ page import="tablas.Scaerr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaerr.label', default: 'Scaerr')}" />
		<title>Editar Error</title>
	</head>
	<body>
		<div id="edit-scaerr" class="content scaffold-edit" role="main">
		
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${scaerrInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${scaerrInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
    
                                <div class="container">
                                  <div class="panel panel-default">
                                     <div class="panel-body">
                                         <div class="col-md-offset-1 col-md-10">
                                             <div class="panel panel-default">
                                                 <div class="panel-heading">
                                                     <h3>Edicion de error</h3>
                                                  </div>
                                                         <div class="panel-body">
                                                             <div class="form-horizontal col-md-12">  
                                                                <g:form method="post" >
                                                                        <g:hiddenField name="id" value="${scaerrInstance?.id}" />
                                                                        <g:hiddenField name="version" value="${scaerrInstance?.version}" />
                                                                        <fieldset class="form">
                                                                                <g:render template="form2"/>
                                                                        </fieldset>
                                                                        <fieldset class="buttons">
                                                                                <g:actionSubmit class="save btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Actualizar')}" />

                                                                        </fieldset>
                                                                </g:form>
                                                                 
                                                     </div>
                                                 </div>
                                             </div>
                                          </div>
                                      </div>
                                  </div>
                              </div>


		</div>
	</body>
</html>
