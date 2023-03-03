
<%@ page import="com.testapp.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">		
	</head>
	<body>

            <h1 class="text-center">Usuarios</h1>
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
                                                                                        <g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Nombre de usuario')}" />

                                                                                        <th>Activo</th>
                                                                                        
                                                                                        <g:sortableColumn property="nomb" title="${message(code: 'user.nomb.label', default: 'Nombre')}" />                                                                                        

                                                                                        <g:sortableColumn property="apellpa" title="${message(code: 'user.apellpa.label', default: 'Apellido Paterno')}" />
                                                                                        
                                                                                        <g:sortableColumn property="apellma" title="${message(code: 'user.apellma.label', default: 'Apellido Materno')}" />

                                                                                </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <g:each in="${userInstanceList}" status="i" var="userInstance">
                                                                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                                                                        <td><g:link action="edit2" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>

                                                                                        <td>
                                                                                                <g:if test="${userInstance.enabled}">
                                                                                                    SI
                                                                                                </g:if>
                                                                                                <g:else>
                                                                                                    NO
                                                                                                </g:else>
                                                                                        </td>
                                                                                        
                                                                                        <td>${fieldValue(bean: userInstance, field: "nombre")}</td>                                                                                        

                                                                                        <td>${fieldValue(bean: userInstance, field: "apellpa")}</td>
                                                                                        
                                                                                        <td>${fieldValue(bean: userInstance, field: "apellma")}</td>

                                                                                </tr>
                                                                        </g:each>
                                                                        </tbody>
                                                                </table>
                                                                <div class="pagination">
                                                                        <g:paginate total="${userInstanceTotal}" />
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
