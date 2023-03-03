<%@ page import="catalogos.Scampo" %>



<div class="fieldcontain ${hasErrors(bean: scampoInstance, field: 'descrip', 'error')} ">
	<label for="descrip">
		<g:message code="scampo.descrip.label" default="Descrip" />
		
	</label>
	<g:textField name="descrip" value="${scampoInstance?.descrip}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scampoInstance, field: 'distrito', 'error')} required">
	<label for="distrito">
		<g:message code="scampo.distrito.label" default="Distrito" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="distrito" name="distrito.id" from="${catalogos.Scadto.list()}" optionKey="id" required="" value="${scampoInstance?.distrito?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scampoInstance, field: 'mpo', 'error')} required">
	<label for="mpo">
		<g:message code="scampo.mpo.label" default="Mpo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="mpo" type="number" value="${scampoInstance.mpo}" required=""/>
</div>

