<%@ page import="catalogos.Venta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
                <title>Nueva Solicitud</title>
		
	</head>
	<body>	
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${ventaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${ventaInstance}" var="error">
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
                                      <h3>Captura</h3>
                                   </div>
                                          <div class="panel-body">
                                                <g:form action="save" >
                                                        <fieldset class="form">
                                                                <g:render template="form"/>
                                                        </fieldset>
                                                        <fieldset class="buttons">
                                                                <g:submitButton name="create" class="btn btn-primary" value="Aceptar" />
                                                        </fieldset>
                                                </g:form>

                                              <div class="form-horizontal col-md-12">                  
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
