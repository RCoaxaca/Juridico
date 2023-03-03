<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

	</head>
	<body>
		<div id="edit-scasol" class="content scaffold-edit" role="main">
		
			<g:if test="${flash.message}">
                                <div class="message" role="status">Numero de solicitud: ${flash.message}</div>
			</g:if>
                        <input id="id" name="id" value="${flash.message}" class="invisible">
			<g:hasErrors bean="${scasolInstance}">
			<ul class="errors" role="alert">
			</ul>
			</g:hasErrors>
                          <div class="container">
                            <div class="panel panel-default">
                               <div class="panel-body">
                                   <div class="col-md-offset-1 col-md-10">
                                       
                                                       <!-- <ul class="nav nav-tabs">
                                                            <li class="active"><a onclick="${remoteFunction(action:'create',params:'\'id=\'+ this.value',update:'err')}">Solicitud</a></li>
                                                            <li><a onclick="${remoteFunction(action:'show',params:'\'id=\'+ this.value',update:'err')}">Errores</a></li>
                                                        </ul> -->
                                                                                                       
                                       <div class="panel panel-default">                                      
                                           <div class="panel-body">  
                                          <g:form action="save">
                                                     <g:render template="nueva"/>
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