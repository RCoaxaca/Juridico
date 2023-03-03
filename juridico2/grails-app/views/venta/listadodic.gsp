
<%@ page import="catalogos.Venta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
		<div class="nav" role="navigation">
		</div>
		<div id="list-venta" class="content scaffold-list" role="main">
                                <h3 class="text-center">Solicitudes capturadas</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
                                            
                                                <g:sortableColumn property="id" title="${message(code: 'venta.id.label', default: 'Exp.')}" />
                                            
						<g:sortableColumn property="folioexp" title="${message(code: 'venta.folioexp.label', default: 'Folio')}" />
					
						<th><g:message code="venta.estado.label" default="Estado" /></th>
					
						<g:sortableColumn property="obser" title="${message(code: 'venta.obser.label', default: 'Observaciones')}" />		
					
						<g:sortableColumn property="apema" title="${message(code: 'venta.apema.label', default: 'Nombre')}"/>					
                                                
						<th>No. Solicitud </th>                                                
					</tr>
				</thead>
				<tbody>
				<g:each in="${ventaInstanceList}" status="i" var="ventaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${ventaInstance.id}">${fieldValue(bean: ventaInstance, field: "id")}</g:link></td>
                                            
                                                <td>${fieldValue(bean: ventaInstance, field: "folioexp")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "obser")}</td>
					
						<td>${fieldValue(bean: ventaInstance, field: "nomb")} ${fieldValue(bean: ventaInstance, field: "apema")} ${fieldValue(bean: ventaInstance, field: "apepa")} </td>
                                                
                                                <td>${fieldValue(bean: ventaInstance, field:"folio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ventaInstanceTotal}"/>
			</div>
		</div>
	</body>
</html>
