<%@ page import="catalogos.Nacionalidad" %>



<div class="fieldcontain ${hasErrors(bean: nacionalidadInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="nacionalidad.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="clave" type="number" value="${nacionalidadInstance.clave}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: nacionalidadInstance, field: 'nacionalidades', 'error')} ">
	<label for="nacionalidades">
		<g:message code="nacionalidad.nacionalidades.label" default="Nacionalidades" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${nacionalidadInstance?.nacionalidades?}" var="n">
    <li><g:link controller="scaact" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="scaact" action="create" params="['nacionalidad.id': nacionalidadInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'scaact.label', default: 'Scaact')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: nacionalidadInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="nacionalidad.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${nacionalidadInstance?.nombre}"/>
</div>

