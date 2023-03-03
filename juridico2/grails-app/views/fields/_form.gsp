<%@ page import="catalogos.Fields" %>



<div class="fieldcontain ${hasErrors(bean: fieldsInstance, field: 'acta', 'error')} required">
	<label for="acta">
		<g:message code="fields.acta.label" default="Acta" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="acta" type="number" value="${fieldsInstance.acta}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: fieldsInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="fields.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${fieldsInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fieldsInstance, field: 'nombre2', 'error')} ">
	<label for="nombre2">
		<g:message code="fields.nombre2.label" default="Nombre2" />
		
	</label>
	<g:textField name="nombre2" value="${fieldsInstance?.nombre2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fieldsInstance, field: 'num', 'error')} required">
	<label for="num">
		<g:message code="fields.num.label" default="Num" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="num" type="number" value="${fieldsInstance.num}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: fieldsInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="fields.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" value="${fieldsInstance?.tipo}"/>
</div>

