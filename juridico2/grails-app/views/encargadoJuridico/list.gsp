
<%@ page import="catalogos.EncargadoJuridico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico')}" />
		
	</head>
	<body>
		
		<div id="list-encargadoJuridico" class="content scaffold-list" role="main">
			 <h1 class="text-center">Jefes de Unidad Juridica</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
                        <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
                                                <g:sortableColumn property="nombre" title="${message(code: 'encargadoJuridico.nombre.label', default: 'Nombre')}" />
                                                <g:sortableColumn property="ape_pat" title="${message(code: 'encargadoJuridico.ape_pat.label', default: 'Apepat')}" />
                                                <g:sortableColumn property="ape_mat" title="${message(code: 'encargadoJuridico.ape_mat.label', default: 'Apemat')}" />
                                                
						<g:sortableColumn property="activo" title="${message(code: 'encargadoJuridico.activo.label', default: 'Activo')}" />
					
						
					
						
					
						<!--<g:sortableColumn property="fin" title="${message(code: 'encargadoJuridico.fin.label', default: 'Fin')}" />-->
					
						<!--<g:sortableColumn property="inicio" title="${message(code: 'encargadoJuridico.inicio.label', default: 'Inicio')}" />-->
					
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${encargadoJuridicoInstanceList}" status="i" var="encargadoJuridicoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                            <td><g:link action="edit" id="${encargadoJuridicoInstance.id}">${fieldValue(bean: encargadoJuridicoInstance, field: "nombre")}</g:link></td>
                                                <!--<td>${fieldValue(bean: encargadoJuridicoInstance, field: "nombre")}</td>-->
                                                <td>${fieldValue(bean: encargadoJuridicoInstance, field: "ape_pat")}</td>
                                                <td>${fieldValue(bean: encargadoJuridicoInstance, field: "ape_mat")}</td>
                                                <td>
                                                <g:if test="${encargadoJuridicoInstance.activo}">
                                                SI
                                                </g:if>
                                                <g:else>
                                                NO
                                                </g:else>
                                                </td>
                                                
                                                
						
					
						
					
						
					
						<!--<td><g:formatDate date="${encargadoJuridicoInstance.fin}" /></td>-->
					
						<!--<td><g:formatDate date="${encargadoJuridicoInstance.inicio}" /></td>-->
					
						
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${encargadoJuridicoInstanceTotal}" />
			</div>
                        
                               </div>
                           </div>
                       </div>
		</div>
	</body>
</html>
