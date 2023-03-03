<%@ page import="tablas.Scaerr" %>
<!DOCTYPE html>
<html>
	<head>

	</head>
	<body>
		
		<div id="create-scaerr" class="content scaffold-create" role="main">
                    <h1 class="text-center"> Aclaracion de datos contenidos en el Acta</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">Número de Expediente:${flash.message}</div>
                        <input type="number" name="nuemr" id="nuemr" value="${flash.message}" class="invisible">
                                    <input type="number" name="expro" id="expro" value="${flash.message}" class="invisible">
                                    <input  name="evaluar" id="evaluar" value="1" class="invisible">
                                
			</g:if>
					<g:render template="form"/>
				
				
		
		</div>
	</body>
</html>