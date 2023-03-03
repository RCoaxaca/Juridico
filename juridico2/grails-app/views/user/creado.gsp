<%@ page import="com.testapp.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
  <div class="container">
     <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h2 class="panel-title text-center">Cambiar contraseña</h2>
                       </div>
			<div class="panel-body">
                            
			<g:if test="${flash.message}">
			<div class="message" role="status"></div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${userInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                            <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <strong>Usuario creado exitosamente!</strong> ${flash.message}
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
