<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
		<div id="create-scasol" class="content scaffold-create" role="main">
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${scasolInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${scasolInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                        <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
                                  <div class="col-md-offset-1 col-md-10">
                                      <div class="panel panel-default">

                                                  <div class="panel-body">

                                                        <g:form action="save" >
                                                                <fieldset class="form-horizontal">
                                                                        <g:render template="fromsol"/>
                                                                </fieldset>

                                                               
                                                                    <div class="row">
                                                                        <div class="col-sm-4 col-md-4">
                                                                            <fieldset class="buttons">

                                                                                    <g:submitButton name="create" class="btn btn-primary" value="Guardar" />
                                                                            </fieldset>                                                                        
                                                                        </div>
                                                                        <div class="col-sm-4 col-md-4"></div>
                                                                        <div class="col-sm-4 col-md-4">
                                                                                 <g:link  class="btn btn-danger " controller="user" action="index">Cancelar</g:link>
                                                                          </div>
                                                                    </div>                                                                 
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
