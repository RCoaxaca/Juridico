
            
<g:if test="${campoInstance}">
<div class="fieldcontain ${hasErrors(bean: opcionInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="opcion.tipo.label" default="Tipo" />
		
	</label>
	
        <g:field name="tipo"  value="${campoInstance.campo}" />
</div>
               
            </g:if>
                <g:else>
                    No se capturado
                </g:else>