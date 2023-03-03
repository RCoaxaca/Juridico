<%@ page import="catalogos.Scaofi" %>



<div class="fieldcontain ${hasErrors(bean: scaofiInstance, field: 'clv', 'error')} required">
	<label for="clv">
		<g:message code="scaofi.clv.label" default="Clv" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="clv" name="clv.id" from="${catalogos.Scampo.list()}" optionKey="id" required="" value="${scaofiInstance?.clv?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scaofiInstance, field: 'clv2', 'error')} required">
	<label for="clv2">
		<g:message code="scaofi.clv2.label" default="Clv2" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="clv2" type="number" value="${scaofiInstance.clv2}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scaofiInstance, field: 'descrip', 'error')} ">
	<label for="descrip">
		<g:message code="scaofi.descrip.label" default="Descrip" />
		
	</label>
	<g:textField name="descrip" value="${scaofiInstance?.descrip}"/>
</div>

