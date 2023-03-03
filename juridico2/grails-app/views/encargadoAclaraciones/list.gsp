
<%@ page import="catalogos.EncargadoAclaraciones" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones')}" />
		
	</head>
	<body>
		<h1 class="text-center">Jefes del Departamento de Aclaraciones</h1>
                
                
        
                 <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
                                  <div class="col-md-offset-1 col-md-10">
                                      <div class="panel panel-default">                                          
                                        <div class="table-responsive">
                                                <table class="table table-striped table-bordered">

                                                                <thead>
					<tr>
					
															
						<g:sortableColumn property="nombre" title="${message(code: 'encargadoAclaraciones.nombre.label', default: 'Nombre')}" />
                                                <g:sortableColumn property="ape_pat" title="${message(code: 'encargadoAclaraciones.ape_pat.label', default: 'Apepat')}" />
                                                <g:sortableColumn property="ape_mat" title="${message(code: 'encargadoAclaraciones.ape_mat.label', default: 'Apemat')}" />					
						<g:sortableColumn property="activo" title="${message(code: 'encargadoAclaraciones.activo.label', default: 'Activo')}" />					
						
					
					</tr>
				</thead>
                                                                      <tbody>
				<g:each in="${encargadoAclaracionesInstanceList}" status="i" var="encargadoAclaracionesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${encargadoAclaracionesInstance.id}">${fieldValue(bean: encargadoAclaracionesInstance, field: "nombre")}</g:link></td>
                                              
                                                <td>${fieldValue(bean: encargadoAclaracionesInstance, field: "ape_pat")}</td>						
                                                <td>${fieldValue(bean: encargadoAclaracionesInstance, field: "ape_mat")}</td>					
						<td>
                                                <g:if test="${encargadoAclaracionesInstance.activo}">
                                                SI
                                                </g:if>
                                                <g:else>
                                                NO
                                                </g:else>
                                                </td>			
						
					</tr>
				</g:each>
				</tbody>
                                                                </table>
                                                                <div class="pagination">
                                                                        <g:paginate total="${encargadoAclaracionesInstanceTotal}" />
                                                                </div>
                                              
                                              
                                              
                                          </div>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>  
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
		<!--<div id="list-encargadoAclaraciones" class="content scaffold-list" role="main">
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
                        <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
					
															
						<g:sortableColumn property="nombre" title="${message(code: 'encargadoAclaraciones.nombre.label', default: 'Nombre')}" />
                                                <g:sortableColumn property="ape_pat" title="${message(code: 'encargadoAclaraciones.ape_pat.label', default: 'Apepat')}" />
                                                <g:sortableColumn property="ape_mat" title="${message(code: 'encargadoAclaraciones.ape_mat.label', default: 'Apemat')}" />					
						<g:sortableColumn property="activo" title="${message(code: 'encargadoAclaraciones.activo.label', default: 'Activo')}" />					
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${encargadoAclaracionesInstanceList}" status="i" var="encargadoAclaracionesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${encargadoAclaracionesInstance.id}">${fieldValue(bean: encargadoAclaracionesInstance, field: "nombre")}</g:link></td>
                                              
                                                <td>${fieldValue(bean: encargadoAclaracionesInstance, field: "ape_pat")}</td>						
                                                <td>${fieldValue(bean: encargadoAclaracionesInstance, field: "ape_mat")}</td>					
						<td>
                                                <g:if test="${encargadoAclaracionesInstance.activo}">
                                                SI
                                                </g:if>
                                                <g:else>
                                                NO
                                                </g:else>
                                                </td>			
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${encargadoAclaracionesInstanceTotal}" />
			</div>
                          </div>
                           </div>
                       </div>
		</div>-->
	</body>
</html>
