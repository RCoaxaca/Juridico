
<%@ page import="catalogos.EncargadoArchivo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo')}" />
		
	</head>
	<body>
		<h1 class="text-center">Jefes de Archivo Central</h1>              
                
                <div class="container">
                           <div class="panel panel-default">
                              <div class="panel-body">
                                  <div class="col-md-offset-1 col-md-10">
                                      <div class="panel panel-default">                                          
                                        <div class="table-responsive">
                                                <table class="table table-striped table-bordered">

                                                                
                                                                        <thead>
                                                                                <tr>					
                                                                                        <g:sortableColumn property="nombre" title="${message(code: 'encargadoArchivo.nombre.label', default: 'Nombre')}" />

                                                                                        
                                                                                        
                                                                                        <!--<g:sortableColumn property="nombre" title="${message(code: 'encargadoArchivo.nomb.label', default: 'Nombre')}" /> -->
                                                                                        
                                                                                        <g:sortableColumn property="ape_pat" title="${message(code: 'encargadoArchivo.ape_pat.label', default: 'Apellido Paterno')}" />
                                                                                        
                                                                                        <g:sortableColumn property="ape_mat" title="${message(code: 'encargadoArchivo.ape_mat.label', default: 'Apellido Materno')}" />
                                                                                        <th>Activo</th>

                                                                                </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <g:each in="${encargadoArchivoInstanceList}" status="i" var="encargadoArchivoInstance">
                                                                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                                                                        <td><g:link action="edit" id="${encargadoArchivoInstance.id}">${fieldValue(bean: encargadoArchivoInstance, field: "nombre")}</g:link></td>

                                                                                        
                                                                                        
                                                                                       <!-- <td>${fieldValue(bean: encargadoArchivoInstance, field: "nombre")}</td> -->
                                                                                        <td>${fieldValue(bean: encargadoArchivoInstance, field: "ape_pat")}</td>
                                                                                        <td>${fieldValue(bean: encargadoArchivoInstance, field: "ape_mat")}</td>

                                                                                        
                                                                                        <td>
                                                                                                <g:if test="${encargadoArchivoInstance.activo}">
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
                                                                        <g:paginate total="${encargadoArchivoInstanceTotal}" />
                                                                </div>
                                              
                                              
                                              
                                          </div>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>   
                
                
                
	</body>
</html>
