<%@ page import="com.testapp.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>

		<div id="create-user" class="content scaffold-create" role="main">
                    <h1 class="text-center">Nuevo usuario</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${userInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                        
                        <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
                                  <div class="col-md-offset-1 col-md-10">
                                     

                                            <g:form action="save" >
                                                    <fieldset class="form">
                                                            <g:render template="form3"/>
                                                    </fieldset>
                                                    <fieldset class="buttons">
                                                            <g:submitButton name="create" class="btn btn-primary" value="${message(code: 'default.button.Guardar.label', default: 'Nuevo usuario')}" />
                                                    </fieldset>
                                            </g:form>
                                      
                                  </div>
                              </div>
                           </div>
                       </div>                        

                        
                        
		</div>
	</body>
</html>
