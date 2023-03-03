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
                                            
                                                <g:sortableColumn property="id" title="${message(code: 'scasol.id.label', default: 'Exp.')}" />
                                            
						<!--<g:sortableColumn property="folioexp" title="${message(code: 'scasol.folioexp.label', default: 'Folio')}" />-->
					
						<th><g:message code="scasol.estado.label" default="Estado" /></th>
					
						<g:sortableColumn property="obser" title="${message(code: 'scasol.obser.label', default: 'Observaciones')}" />		
					
						<g:sortableColumn property="apema" title="${message(code: 'scasol.apema.label', default: 'Nombre')}"/>					
                                                
						<th>Elaboró </th>                                                
                                                <th>Capturar </th>
                                                <th>Considerando</th>
                                                <th>Papeleta</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${scasolInstanceList}" status="i" var="scasolInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                          
					
                                                <td>${fieldValue(bean: scasolInstance, field: "expro")}</td>
                                                
                                                <td>${fieldValue(bean: scasolInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "obser")}</td>
					
						<td>${fieldValue(bean: scasolInstance, field: "nom_intere")} ${fieldValue(bean: scasolInstance, field: "ap1_intere")} ${fieldValue(bean: scasolInstance, field: "ap2_intere")} </td>
                                                
                                                <td>${fieldValue(bean: scasolInstance, field:"dicc")}</td>
                                                
                                                <td>
                                                    <sec:ifLoggedIn>
                                                        <g:link controller="scasol" action="editval" id="${scasolInstance.id}" class="btn btn-default">Editar solicitud..</g:link>
                                                  </sec:ifLoggedIn>
                                                </td>
                                                <td>
                                                    <sec:ifLoggedIn>
                                                        <g:link controller="scasol" action="reimprimeConsiderando" id="${scasolInstance.id}" params="[param2: "${sec.loggedInUserInfo(field: 'id')}"]" class="btn btn-default" target="_new">Imprimir</g:link>
                                                    </sec:ifLoggedIn>
                                                </td>
                                                <td>
                                                    <sec:ifLoggedIn>
                                                        <g:link controller="scasol" action="reimprimePapeleta" id="${scasolInstance.id}" params="[param2: "${sec.loggedInUserInfo(field: 'id')}"]" class="btn btn-default" target="_new">Imprimir</g:link>
                                                    </sec:ifLoggedIn>
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
                        <g:select id="usuarioactual" name="val.id" from="${com.testapp.User.list()}" class="invisible" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>
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
