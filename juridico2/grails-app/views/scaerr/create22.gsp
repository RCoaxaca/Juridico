<%@ page import="tablas.Scaerr" %>
<!DOCTYPE html>
<html>
	<head>

	</head>
	<body>	
                   <div class="panel-body">
                               <g:if test="${flash.message}">
                                     <input type="number" name="nuemr" id="nuemr" value="${flash.message}" class="invisible">
                                        <input type="number" name="expro" id="expro" value="${flash.message}" class="invisible">
                                         <input  name="evaluar" id="evaluar" value="5" class="invisible">			
			</g:if>
                               <h1 class="text-center">Testar Oficio </h1>
			<g:hasErrors bean="${scaerrInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${scaerrInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                        
                    
					<g:render template="form222"/>
			
                                
                                
                
           
        </div>        
                        
                        
                        
                        
               
	</body>
</html>
