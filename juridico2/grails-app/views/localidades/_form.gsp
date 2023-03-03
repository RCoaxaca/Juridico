<%@ page import="catalogos.Localidades" %>



<div class="fieldcontain ${hasErrors(bean: localidadesInstance, field: 'localidad', 'error')} ">
	<label for="localidad">
		<g:message code="localidades.localidad.label" default="Localidad" />
		
	</label>
	<g:textField name="localidad" value="${localidadesInstance?.localidad}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localidadesInstance, field: 'mpo', 'error')} required">
	<label for="mpo">
		<g:message code="localidades.mpo.label" default="Mpo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mpo" name="mpo.id" from="${catalogos.Scampo.list()}" optionKey="id" required="" value="${localidadesInstance?.mpo?.id}" class="many-to-one"/>
</div>

