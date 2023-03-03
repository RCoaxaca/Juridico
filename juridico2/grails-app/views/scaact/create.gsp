<%@ page import="tablas.Scaact" %>
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
			<h1 class="text-center">Aplicación del Art. 71</h1>
			<g:if test="${flash.message}">
                               <input type="exppro" name="exppro" id="mensaj" value="${flash.message}" class="invisible">
                               <input type="evaluar" name="evaluar" id="mensaj" value="3" class="invisible">
			</g:if>
			<g:hasErrors bean="${scaactInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${scaactInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
			
		
	
	</body>
</html>
