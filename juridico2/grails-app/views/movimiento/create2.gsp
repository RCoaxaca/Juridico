<%@ page import="catalogos.Movimiento" %>
<!DOCTYPE html>

<g:javascript>
function pulsarenter(e) { 
  tecla = (document.all) ? e.keyCode :e.which; 
  if(tecla==13)
  {
  <g:remoteFunction action="saluda" onSuccess="acomoda(data)" params="{id:numero_expediente.value,usuario:usuariorecibe.value}"/> 
  //alert("presiono enter")
  
  }
  return (tecla!=13); 
  
}

function acomoda(data)
{

alert(data)

//var datos=data.split(" ")
//alert(datos[0])
//alert(datos[1])
//document.getElementById("usuarioentrega").value = datos[1];
//document.getElementById("idmovimiento").value = datos[0];
  
}
</g:javascript>

<html>
	<head>
		<meta name="layout" content="main">
		
	</head>
	<body>
		
		<div id="create-movimiento" class="content scaffold-create" role="main">
			<h1>Recepcion de Archivos</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${movimientoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${movimientoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
				<fieldset class="form">
					<g:render template="form2"/>
				</fieldset>
				
			
		</div>
	</body>
</html>
