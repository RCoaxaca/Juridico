
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
                                                                        <th>Folio</th>
                                                                        <th>Estado</th>
                                                                        <th>Observaciones</th>
                                                                        <th>Nombre</th>
                                                                        <th>Elaboró </th> 

                                              
                                                                </tr>
                                                        </thead>
                                                        <tbody>
                                                        <g:each in="${scasolInstanceList}" status="i" var="scasolInstance">
                                                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">


                                                                        <td>${fieldValue(bean: scasolInstance, field: "expro")}</td>

                                                                        <td>${fieldValue(bean: scasolInstance, field: "folioexp")}</td>

                                                                        <td>${fieldValue(bean: scasolInstance, field: "estado")}</td>

                                                                        <td>${fieldValue(bean: scasolInstance, field: "obser")}</td>

                                                                        <td>${fieldValue(bean: scasolInstance, field: "nom_intere")} ${fieldValue(bean: scasolInstance, field: "ap1_intere")} ${fieldValue(bean: scasolInstance, field: "ap2_intere")}</td>

                                                                        <td>${fieldValue(bean: scasolInstance, field:"dicc")}</td>

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
