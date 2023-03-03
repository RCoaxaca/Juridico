<%@ page import="catalogos.Resguardo" %>

<g:javascript>
function pulsar(e) { 
  tecla = (document.all) ? e.keyCode :e.which; 
  if(tecla==13)
  {
  document.getElementById('papeleta').focus();
  }
  return (tecla!=13); 
  
}
</g:javascript>


<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
			
	</head>
	<body>
		
		<div id="create-resguardo" class="content scaffold-create" role="main">
			<h1 class="text-center">Agregar Expediente al Archivo</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${resguardoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${resguardoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                         <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
                                  <div class="col-md-offset-0 col-md-10">
                                      
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Guardar" />
                                        <!--<input type="submit" value="Guardar" />-->
				</fieldset>
			</g:form>
                               
                                  </div>
                              </div>
                           </div>
                       </div>
		</div>
	</body>
</html>
