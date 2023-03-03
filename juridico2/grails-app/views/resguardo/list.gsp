
<%@ page import="catalogos.Resguardo" %>
<!DOCTYPE html>
<html>
	<head>
		<!--<meta name="layout" content="main">-->
		
	</head>
	<body>
		
		<div id="list-resguardo" class="content scaffold-list" role="main">
			<h1 class="text-center">Listado de Expedientes</h1>
                         <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
                                  <div class="col-md-offset-1 col-md-10">
                                      <div class="panel panel-default">                                          
                                        <div class="table-responsive">
			
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
                                                <g:sortableColumn property="numero_expediente" title="${message(code: 'resguardo.numero_expediente.label', default: 'Numero de expediente')}" />					
						<g:sortableColumn property="fecha_entrada" title="${message(code: 'resguardo.fecha_entrada.label', default: 'Fecha de entrada')}" />
                                                <g:sortableColumn property="entrega" title="${message(code: 'resguardo.entrega.label', default: 'Entregó')}" />
                                                <g:sortableColumn property="ubicacion" title="${message(code: 'resguardo.ubicacion.label', default: 'Ubicacion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${resguardoInstanceList}" status="i" var="resguardoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
						<td><g:link action="show" id="${resguardoInstance.id}">${fieldValue(bean: resguardoInstance, field: "numero_expediente")}</g:link></td>
                                                <td><g:formatDate date="${resguardoInstance.fecha_entrada}" /></td>
						<td>${fieldValue(bean: resguardoInstance, field: "entrega")}</td>			
						<td>${fieldValue(bean: resguardoInstance, field: "ubicacion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${resguardoInstanceTotal}" />
			</div>
                                                                      
                                              
                                              
                                          </div>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>                        
                     
		</div>
	</body>
</html>
