
<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
		<div id="list-scasol" class="content scaffold-list" role="main">
                    <div class="container">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-md-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered">
                                                        <thead>
                                                                <tr>

                                                                        
                                                                        <th>Exp.</th>
                                                                        <th>Nombre</th>
                                                                        <th>Estado</th>
                                                                        <th>Ventanilla</th>
                                                                        <th>Capturó</th>
                                                                        <th>Valido</th> 
                                                                        <th>Impreso</th>

                                              
                                                                </tr>
                                                        </thead>
                                                        <tbody>
                                                        <g:each in="${scasolInstanceList}" status="i" var="scasolInstance">
                                                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">


                                                                        <td>${fieldValue(bean: scasolInstance, field: "expro")}</td>
                                                                        
                                                                        <td>${fieldValue(bean: scasolInstance, field: "nomb")} ${fieldValue(bean: scasolInstance, field: "apema")} ${fieldValue(bean: scasolInstance, field: "apepa")} </td>

                                                                        <td>${fieldValue(bean: scasolInstance, field: "estado")} </td>
                                                                        
                                                                        <td>${fieldValue(bean: scasolInstance, field: "cap")}</td>

                                                                        <td>${fieldValue(bean: scasolInstance, field: "dicc")}</td>

                                                                        <td>${fieldValue(bean: scasolInstance, field: "val")}</td>

                                                                        

                                                                        <td>
                                                                                    <g:if test="${scasolInstance.isprint == 1}">
                                                                                        SI
                                                                                    </g:if>
                                                                                    <g:else>
                                                                                        NO
                                                                                    </g:else>
                                                                        </td>

                                                                        <!--<td><g:link action="edit" id="${scasolInstance.id}">icono</g:link></td>-->

                                                                </tr>
                                                        </g:each>
                                                        </tbody>
                                                </table>
                                               </div> 
                                                <div class="pagination">
                                                        <g:paginate total="${scasolInstanceTotal}" />
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
