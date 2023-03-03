<%@ page import="catalogos.EncargadoAclaraciones" %>
<!DOCTYPE html>
<g:javascript>
        function mostrar(obj){       
        if (obj.value =="SI"){
         document.getElementById('oculto').style.display = 'block';
        }
        else{
          
          document.getElementById('oculto').style.display = 'none';
        }       
        }
        function ocultar(){       
        alert("Se guardo Correctamente")
          
              
        }
    function most(obj){       
        if (obj.value == 1){
         document.getElementById('ocul').style.display = 'none';
         document.getElementById('bot').style.display = 'none';
        }
        if (obj.value == 2 ){
          
          alert("dudas..");
         <!-- document.getElementById('oculto').style.display = 'none';-->
        }
        if (obj.value == 3){
            document.getElementById('bot').style.display = 'block';
            document.getElementById('ocul').style.display = 'none';
        }
        if(obj.value == 4){
            document.getElementById('bot').style.display = 'none';
            document.getElementById('ocul').style.display = 'block';
        }
        else{
        document.getElementById('ocul').style.display = 'none';
         document.getElementById('obot').style.display = 'none';
        }
}
      
</g:javascript>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoAclaraciones.label', default: 'Encargado Aclaraciones')}" />
		
	</head>
	<body>
		<h1 class="text-center">Nuevo Jefe de Aclaraciones</h1>
		<div id="create-encargadoAclaraciones" class="content scaffold-create" role="main">
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${encargadoAclaracionesInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${encargadoAclaracionesInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                        <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" onclick="ocultar()" class="btn btn-primary" value="Guardar" />
				</fieldset>
			</g:form>
                         </div>
                           </div>
                       </div>  
		</div>
	</body>
</html>
