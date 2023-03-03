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
                           <g:hiddenField name="fchsol" value="${fechaSol}"    />
			<table class="table table-striped table-bordered">
                                <thead>
					<tr>
						<g:sortableColumn property="noexp" title="${message(code: 'scasol.noexp.label', default: 'No. Exp.')}" />
                                                <g:sortableColumn property="year" title="${message(code: 'scasol.year.label', default: 'Año')}" />
						<th><g:message code="scasol.estado.label" default="Estado" /></th>
						<g:sortableColumn property="apema" title="${message(code: 'scasol.apema.label', default: 'Promovida Por')}"/>
                                                <g:sortableColumn property="ap1_intere" title="${message(code: 'scasol.ap1_intere.label', default: 'Interesado')}"/>
                                                <th>No. Solicitud </th>
                                                
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
                                                
					
					</tr>
				</g:each>
				</tbody>
			</table>
                        </div>
                        <div class="col-md-2 col-md-offset-10">
                            <g:link  class="btn btn-default" controller="scasol" action="busqueda">Regresar</g:link>
                        </div>
			<div class="pagination">
				<g:paginate total="${scasolInstanceTotal}" params="['nsoli':nsoli]"/>
			</div>
                </div>
            </div>
        </div>
     </div>
 </div>                        
		</div>
	</body>
</html>
