
<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	
	</head>
	<body>

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
                                                <g:sortableColumn property="noexp" title="${message(code: 'scasol.noexp.label', default: 'No. Exp.')}" />
                                                <g:sortableColumn property="year" title="${message(code: 'scasol.year.label', default: 'Año')}" />
						<th><g:message code="scasol.estado.label" default="Estado" /></th>
						<g:sortableColumn property="apema" title="${message(code: 'scasol.apema.label', default: 'Promovida Por')}"/>
                                                <g:sortableColumn property="ap1_intere" title="${message(code: 'scasol.ap1_intere.label', default: 'Interesado')}"/>
                                                <th>No. Solicitud </th>                                             
                                                <th>Capturar </th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${scasolInstanceList}" status="i" var="scasolInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                           <td>${scasolInstance.expro}</td>
					        <td>${scasolInstance.expano}</td>
                                                <td>${scasolInstance.estado.docuestado}</td>
						<td>${fieldValue(bean: scasolInstance, field: "nomb")} ${fieldValue(bean: scasolInstance, field: "apepa")} ${fieldValue(bean: scasolInstance, field: "apema")} </td>
                                                <td>${fieldValue(bean: scasolInstance, field: "nom_intere")} ${fieldValue(bean: scasolInstance, field: "ap1_intere")} ${fieldValue(bean: scasolInstance, field: "ap2_intere")} </td>
                                                <td>${scasolInstance.folio}</td>
                                                <td>
                                                        <g:if test="${scasolInstance.estado.id == 1 || scasolInstance.estado.id == 4 }">
                                                                 <g:link action="edit" class="btn btn-default" id="${scasolInstance.id}">editar</g:link>
                                                        </g:if>
                                                        <g:elseif test="${scasolInstance.estado.id == 2 || scasolInstance.estado.id == 3 }" >
                                                             <div class="alert alert-success">Solicitud Capturada</div>
                                                        </g:elseif>                                                      
                                                </td>
                                                

                                                
                                                         
					
					</tr>
				</g:each>
				</tbody>
			</table>
                        </div>
                        <div class="col-md-2 col-md-offset-10">
                            <g:link  class="btn btn-default" controller="scasol" action="busquedic">Regresar</g:link>
                        </div>
			<div class="pagination">
				<g:paginate total="${scasolInstanceTotal}" params="['fchsol':fechaSol]"/>
			</div>
                </div>
            </div>
        </div>
     </div>
 </div>                        
		</div>
	</body>
</html>
