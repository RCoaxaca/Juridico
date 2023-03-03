<%@ page import="catalogos.Opcion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		 <title>CAPTURA DE ERRORES</title>
	</head>
	<body>

		    <div id="list-opcion" class="content scaffold-list" role="main">	
			<g:if test="${flash.message}">
                            <div class="errors" role="status">Numero de Solicitud: ${flash.args}</div>    
			</g:if>
                         <input type="number" name="idgeneral" id="idgeneral" value="${flash.message}" class="invisible">
                         <!--<input type="number" name="idgeneral2" id="idgeneral2" value="${numeroexpediente}" class="">-->
                         <!--<input type="number" name="idgeneral2" id="idgeneral" value="${numeroexpediente}" class="invisible">-->
                  <div class="container">
     <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>LISTA DE ERRORES CAPTURADOS</h3>
                     </div>
                            <div class="panel-body">
                                <div class="form-horizontal col-md-12">
                         			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
					
						<th>TIPO DE CORRECIÓN</th>
					
						<th>ERROR A CORREGIR</th>
                                                
                                                <sec:ifLoggedIn>
                                                <th>Editar</th>
                                                <th>Eliminar</th>
                                                </sec:ifLoggedIn>
                                                <th>SE ENVIA A</th>
						<!--<g:sortableColumn property="exapro" title="${message(code: 'opcion.exapro.label', default: 'Exapro')}" />-->
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scaactInstanceList}" status="i" var="scaactInstance">				
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                                 <td>${fieldValue(bean: scaactInstance, field: "tipoerresp")}</td>
                                                 
						 <td>${fieldValue(bean: scaactInstance, field: "datofalta")}</td>                                               
                                                
                                                 <sec:ifLoggedIn>
                                               <td><g:link controller="scaact" action="edit" id="${scaactInstance.id}" class="btn btn-success btn-xs">Editar</g:link></td>
                                                 
                                                <td><g:link action="eliminar1" id="${scaactInstance.id}" formnovalidate="" class="btn btn-danger btn-xs" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">Eliminar</g:link></td>
                                                </sec:ifLoggedIn>
                                                 <g:if test="${scaactInstanceList.donde[i].toString().equals("EN EL ARCHIVO CENTRAL")}">
                                                <td>Archivo</td>
                                              </g:if>
                                              <g:if test="${scaactInstanceList.donde[i].toString().equals("EN LA OFICIALIA")}">
                                                <td>Oficialia</td>
                                              </g:if>
                                              <g:if test="${scaactInstanceList.donde[i].toString().equals("ARCHIVO CENTRAL Y OFICIALIA")}">
                                                <td>Ambos</td>
                                              </g:if>
                                                 </tr>
				</g:each>                                     
				<g:each in="${scaerrInstanceList}" status="i" var="scaerrInstance">
					  
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                                <g:if test="${scaerrInstanceList.contiene[i].toString().contains("*")}">
                                                
                                                    <td>TESTAR DE OFICIO</td> 
                                          
                                                 <td>${fieldValue(bean: scaerrInstance, field: "tcorrect")}</td>     
                                                    
                                                </g:if>
                                                <g:else>
                                                    <td>${fieldValue(bean: scaerrInstance, field: "tcorrect")}</td> 
                                                   
                                                    <td>${fieldValue(bean: scaerrInstance, field: "campo")}</td>
                                                    
                                                </g:else>
                                                    
                                                                                                
                                              <sec:ifLoggedIn>
                                                  <td><g:link controller="scaerr" action="edit" id="${scaerrInstance.id}" class="btn btn-success btn-xs">Editar</g:link></td>

                                                 <td><g:link action="eliminar2" id="${scaerrInstance.id}" formnovalidate="" class="btn btn-danger btn-xs" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Desea Continuar?')}');">Eliminar</g:link></td>   
						</sec:ifLoggedIn>
                                              <g:if test="${scaerrInstanceList.donde[i].toString().contains("1")}">
                                                <td>Oficialia</td>
                                              </g:if>
                                              <g:if test="${scaerrInstanceList.donde[i].toString().contains("2")}">
                                                <td>Archivo</td>
                                              </g:if>
                                              <g:if test="${scaerrInstanceList.donde[i].toString().contains("3")}">
                                                <td>Ambos</td>
                                              </g:if>
					</tr>
				</g:each>
                             
				</tbody>
			</table>
                   <!-- <g:select id="usuarioactual" name="val.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>-->
                                                   
		</div>
                
                <div id="nuevo">
                    <sec:ifLoggedIn>
                 <g:remoteLink class ="btn btn-primary" onmousedown="${remoteFunction(action:'mostrar',update:'nuevo',params:'\'id=\'+idgeneral.value')}" >Agregar nuevo</g:remoteLink>
                 </sec:ifLoggedIn>  
                </div>    

               <!--<g:remoteLink  onmouseup="${remoteFunction( action:'autoinc')}"> nueva funcion</g:remoteLink>-->
          </div>
                   <div class="row">
                       <g:select id="usuarioactual" name="val.id" from="${com.testapp.User.list()}" class="invisible" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>
                    
                 <sec:ifAllGranted roles="ROLE_USER">
                      <sec:ifLoggedIn>
                   <!--<g:link controller="opcion" action="imprimir"  target="_blank" id="${flash.message}" class="btn btn-default">Generar Resolucion</g:link>-->
                   <!--<g:link controller="opcion" action="imprimirFinal" class="btn btn-default" params="['id':flash.message,'usuario1':sec.loggedInUserInfo(field: 'id')]" target="_new" ><g:message code="default.button.print.label" default="Generar Todo" /></g:link>-->
                    <g:link controller="opcion" action="imprimirConsiderando" params="['id':flash.message,'usuario1':sec.loggedInUserInfo(field: 'id')]" target="_blank" id="${flash.message}" class="btn btn-default">Imprimir Considerando</g:link>
                   <g:link controller="opcion" action="imprimirSoloPapeleta" params="['id':flash.message,'usuario1':sec.loggedInUserInfo(field: 'id')]"  target="_blank" id="${flash.message}" class="btn btn-default">Imprimir Papeleta</g:link>
                   <g:link controller="nota" action="quitarEspacios" params="['id':flash.message,'usuario1':sec.loggedInUserInfo(field: 'id')]" id="${flash.message}" target="_blank" class="btn btn-default">Quitar Espacios</g:link>
                   <g:link controller="opcion" action="editarResolucion2" params="['id':flash.message,'usuario1':sec.loggedInUserInfo(field: 'id')]" id="${flash.message}" class="btn btn-default">Modificar Resolucion</g:link>                   
                   <g:link controller="scasol" action="editval" id="${flash.message}" class="btn btn-default">Editar Solicitud</g:link>
                   <g:link controller="opcion" action="imprimirNegativa"  target="_blank" id="${flash.message}" class="btn btn-default">Generar Resolucion Negativa</g:link>
                     </sec:ifLoggedIn>
                  </sec:ifAllGranted> 
                       <div class="col-md-3">
                        <sec:ifLoggedIn>
                             <g:link controller="user" action="index" class="btn btn-default">Terminar</g:link>
                        </sec:ifLoggedIn>
                       </div>
                   </div>                    
            </div>
             
        </div>
     </div>
 </div>
    </div>
 </div>
 
 
 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <select onclick="ocultar()"></select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerra</button>
        <button type="button" class="btn btn-primary" onblur="ocultar()">guardar</button>
      </div>
    </div>
  </div>
</div>
 
 
 
 
 
 
 
 
 
 
 
 
 

	</body>
</html>