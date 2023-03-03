
<%@ page import="catalogos.Opcion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		 <title>CAPTURA DE ERRORES</title>
	</head>
	<body>

		    <div id="list-opcion" class="content scaffold-list" role="main">
			
			<g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
			</g:if>
                        
                  <div class="container">
     <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>LISTA DE ERRORES CAPTURADOS</h3>
                     </div>
                            <div class="panel-body">
                                <div class="form-horizontal col-md-12">
                         			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
					
						<th><g:message code="opcion.tipo.label" default="TIPO DE CORRECCION" /></th>
					
						<th><g:message code="opcion.error.label" default="ERROR A CORREGIR" /></th>
                                                <th>Editar</th>
                                                <th>Eliminar</th>
					
						<!--<g:sortableColumn property="exapro" title="${message(code: 'opcion.exapro.label', default: 'Exapro')}" />-->
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${opcionInstanceList}" status="i" var="opcionInstance">
					
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="edit" id="${opcionInstance.id}">${fieldValue(bean: opcionInstance, field: "tipo")}</g:link></td>
		                                     
                                                 <td>${fieldValue(bean: opcionInstance, field: "error")}</td>
                                          
                                               
                                                      <g:if test="${opcionInstance.error.id == 1 || opcionInstance.error.id == 5 }" >  
                                                            <td><g:link controller="scaerr" action="edit" id="${opcionInstance.tablaid}" class="btn btn-success btn-xs">aa</g:link></td>
                                                        </g:if>
                                                        <g:elseif test="${opcionInstance.error.id == 2 || opcionInstance.error.id == 3 || opcionInstance.error.id == 4 }">
                                                            <td><g:link controller="scaact" action="edit" id="${opcionInstance.tablaid}" class="btn btn-success btn-xs">ooo</g:link></td>
                                                        </g:elseif>                               
                                               
                                                <td><g:link action="eliminar" id="${opcionInstance.id}" formnovalidate="" class="btn btn-danger btn-xs" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">Eliminar</g:link></td>

                                                        
						<!--<td>${fieldValue(bean: opcionInstance, field: "exapro")}</td>-->					                                       
					</tr>
				</g:each>
				</tbody>
			</table>

                       
                                <input type="number" name="mensaj" id="mensaj" value="${flash.message}" class="invisible">
                        
		</div>
                
                <div id="nuevo">
                  <g:remoteLink class ="btn btn-primary" onmousedown="${remoteFunction(action:'mostrar',update:'nuevo',params:'\'id=\'+mensaj.value')}" >Agregar nuevo</g:remoteLink>
                  </div>    

               <!--   <g:remoteLink  onmouseup="${remoteFunction( action:'autoinc')}"> nueva funcion</g:remoteLink>-->
          </div>
                 <sec:ifAllGranted roles="ROLE_USER">
                   <g:link controller="opcion" action="imprimir" class="btn btn-default">Imprimir Solicitud</g:link>
                </sec:ifAllGranted>   
                   
            </div>
             
        </div>
     </div>
 </div>
    </div>
 </div>

	</body>
</html>