
<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	
	</head>
	<body>
                <g:if test="${scasolInstanceList.size()> 0}">
		<div id="list-scasol" class="content scaffold-list" role="main">
                   
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
        <div class="container">
           <div class="panel panel-default">
              <div class="panel-body">
                  <div class="col-md-offset-1 col-md-10">
                      <div class="panel panel-default">

                        <div class="table-responsive">
			<table class="table table-striped table-bordered">
                                <thead>
					<tr>                                                                               
                                                <th>Exp.</th>
                                                <th>Folio</th>
                                                <th>Estado</th>
                                                <th>Promueve</th>  
                                                <th>Interesado</th> 
						<th>No. Solicitud </th>
                                                <th>Observaciones</th>                                                
                                                <th>Capturar </th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${scasolInstanceList}" status="i" var="scasolInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                          
					
                                                <td>${fieldValue(bean: scasolInstance, field: "id")}</td>
                                            
                                                <td>${fieldValue(bean: scasolInstance, field: "folioexp")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "estado")}</td>
                                                
                                                <td>${fieldValue(bean: scasolInstance, field: "promov")}</td>

						<td>${fieldValue(bean: scasolInstance, field: "nomb")} ${fieldValue(bean: scasolInstance, field: "apepa")} ${fieldValue(bean: scasolInstance, field: "apema")} </td>

                                                <td>${fieldValue(bean: scasolInstance, field:"folio")}</td>
                                                
                                                <td>${fieldValue(bean: scasolInstance, field: "obser")}</td>
                                                
                                                <td>
                                                       <g:link controller="scasol" action="editval" id="${scasolInstance.id}" class="btn btn-default">Editar solicitud..</g:link>                                                      
                                                </td>
                                                

                                                
                                                         
					
					</tr>
				</g:each>
				</tbody>
			</table>
                        </div>
                        <div class="col-md-2 col-md-offset-10">
                            <g:link  class="btn btn-default" controller="opcion" action="consulta">Regresar</g:link>
                        </div>
			<div class="pagination">
				<g:paginate total="${scasolInstanceTotal}" />
			</div>
                </div>
            </div>
        </div>
     </div>
 </div>                        
		</div>
                </g:if>
                <g:else>
                 <div class="container">
        <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-offset-2 col-md-8">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                                               
                            </div>
                            <div class="panel-body">                                                    
                                        <div class="alert alert-danger" role="status">¡No se encontraron registros con estos datos!</div>                                                                                     
                           </div>
                        </div>
                    </div>
                </div>
            </div>         
        
    </div>
                </g:else>
	</body>
</html>

