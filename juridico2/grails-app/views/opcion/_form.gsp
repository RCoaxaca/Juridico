<%@ page import="catalogos.Opcion" %>



<div class="fieldcontain ${hasErrors(bean: opcionInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="opcion.tipo.label" default="Tipo" />
		
	</label>
	
        <g:textField name="nombre" value="${opcionInstance.tipo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opcionInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="opcion.error.label" default="Error" />
		
	</label>
	<g:select id="error" name="error.id" from="${catalogos.Tipoerror.list()}" optionKey="id" value="${opcionInstance?.error?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opcionInstance, field: 'exapro', 'error')} required">
	<label for="exapro">
		<g:message code="opcion.exapro.label" default="Exapro" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="exapro" type="number" value="${opcionInstance.exapro}" required=""/>
</div>

