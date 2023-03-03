<%@ page import="catalogos.Entidades" %>



<div class="fieldcontain ${hasErrors(bean: entidadesInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="entidades.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="clave" type="number" value="${entidadesInstance.clave}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: entidadesInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="entidades.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${entidadesInstance?.nombre}"/>
</div>

