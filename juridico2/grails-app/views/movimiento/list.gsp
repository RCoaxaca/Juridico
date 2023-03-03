
<%@ page import="catalogos.Movimiento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		
	</head>
	<body>
		
		<div id="list-movimiento" class="content scaffold-list" role="main">
			
			
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
					
						<g:sortableColumn property="entrada" title="${message(code: 'movimiento.entrada.label', default: 'Entrada')}" />
					
						<g:sortableColumn property="salida" title="${message(code: 'movimiento.salida.label', default: 'Salida')}" />
					
						<th><g:message code="movimiento.usuariopresta.label" default="Usuariopresta" /></th>
					
						<th><g:message code="movimiento.usuariorecibe.label" default="Usuariorecibe" /></th>
					
						
					
						<g:sortableColumn property="numero_expediente" title="${message(code: 'movimiento.numero_expediente.label', default: 'Numeroexpediente')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${movimientoInstanceList}" status="i" var="movimientoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${movimientoInstance.id}">${fieldValue(bean: movimientoInstance, field: "entrada")}</g:link></td>
					
						<td><g:formatDate date="${movimientoInstance.salida}" /></td>
					
						<td>${fieldValue(bean: movimientoInstance, field: "usuariopresta")}</td>
					
						<td>${fieldValue(bean: movimientoInstance, field: "usuariorecibe")}</td>
					
						
					
						<td>${fieldValue(bean: movimientoInstance, field: "numero_expediente")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${movimientoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
