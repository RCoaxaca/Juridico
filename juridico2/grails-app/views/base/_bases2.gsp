<table class="table table-striped table-bordered">
				<thead>
					<tr>
                                                <th><g:message code="base.acta.label" default="Acta" /></th>
                                                <th><g:message code="base.campo.label" default="Campo" /></th>					
						<g:sortableColumn property="base" title="${message(code: 'base.base.label', default: 'Base')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${baseInstanceList}" status="i" var="baseInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: baseInstance, field: "acta")}</td>
                                                <td>${fieldValue(bean: baseInstance, field: "campo")}</td>
						<td><g:link action="edit" id="${baseInstance.id}">${fieldValue(bean: baseInstance, field: "base")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>