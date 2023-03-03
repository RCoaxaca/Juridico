<%@ page import="tablas.Scaact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	
		<title>editar Errores</title>
	</head>
	<body>

		<div id="edit-scaact" class="content scaffold-edit" role="main">

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${scaactInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${scaactInstance}" var="error">
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
                                                 <h3> Editar Error</h3>
                                              </div>
                                                     <div class="panel-body">


                                                         <div class="form-horizontal col-md-12">  

                                                        <g:form method="post" >
                                                                <g:hiddenField name="id" value="${scaactInstance?.id}" />
                                                                <g:hiddenField name="version" value="${scaactInstance?.version}" />
                                                                <fieldset class="form">
                                                                        <g:render template="from2"/>
                                                                </fieldset>
                                                                <fieldset class="buttons">
                                                                        <g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Guardar')}" />					
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
