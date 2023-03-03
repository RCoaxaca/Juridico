<%@ page import="catalogos.Venta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
<g:javascript>
    function mostrar(obj){       
        if (obj.value == 1){
         document.getElementById('oculto').style.display = 'none';
         document.getElementById('boton').style.display = 'none';
        }
        if (obj.value == 2 ){
          
          alert("dudas..");
         <!-- document.getElementById('oculto').style.display = 'none';-->
        }
        if (obj.value == 3){
            document.getElementById('boton').style.display = 'block';
            document.getElementById('oculto').style.display = 'none';
        }
        if(obj.value == 4){
            document.getElementById('boton').style.display = 'none';
            document.getElementById('oculto').style.display = 'block';
        }
        else{
        document.getElementById('oculto').style.display = 'none';
         document.getElementById('oboton').style.display = 'none';
        }
}

</g:javascript>
	</head>
	<body>
		<div id="edit-venta" class="content scaffold-edit" role="main">
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
                                                <h4 class="text-center">Dictaminar</h4>
                                            </div>
                                            <div class="panel-body">
                                                    <g:form method="post" >
                                                              <g:hiddenField name="id" value="${ventaInstance?.id}" />
                                                              <g:hiddenField name="version" value="${ventaInstance?.version}" />
                                                              <fieldset class="form">
                                                                      <g:render template="form2"/>
                                                              </fieldset>
                                                              <fieldset class="buttons">
                                                                    <!--  <g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                                                                      <g:actionSubmit class="btn btn-default" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />-->
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
