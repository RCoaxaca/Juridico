
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
                                            
                                                <g:sortableColumn property="id" title="${message(code: 'scasol.id.label', default: 'Fecha Sol.')}" />
                                            
						<g:sortableColumn property="folioexp" title="${message(code: 'scasol.folioexp.label', default: 'Expediente')}" />
					
						<th><g:message code="scasol.estado.label" default="Estado" /></th>
					
						<g:sortableColumn property="obser" title="${message(code: 'scasol.obser.label', default: 'Observaciones')}" />		
					
						<g:sortableColumn property="apema" title="${message(code: 'scasol.apema.label', default: 'Nombre')}"/>					
                                                
						<th>No. Solicitud </th>
                                                
                                                <th>Capturar </th>
					</tr>
				</thead>
				<tbody>
                              
				<g:each in="${scasolInstanceList}" status="i" var="scasolInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                          
					
                                                <td>${fieldValue(bean: scasolInstance, field: "fechasol".split(" ")[0])}</td>
                                            
                                                <td>${fieldValue(bean: scasolInstance, field: "expro")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "obser")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "nom_intere")} ${fieldValue(bean: scasolInstance, field: "ap1_intere")} ${fieldValue(bean: scasolInstance, field: "ap2_intere")} </td>
                                                
                                                <td>${fieldValue(bean: scasolInstance, field:"folio")}</td>
                                                
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
			<div class="pagination invisible">
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
