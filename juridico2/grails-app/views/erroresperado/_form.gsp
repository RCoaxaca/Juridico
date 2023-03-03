<%@ page import="catalogos.Erroresperado" %>



<div class="fieldcontain ${hasErrors(bean: erroresperadoInstance, field: 'tipodeerror', 'error')} ">
	<label for="tipodeerror">
		<g:message code="erroresperado.tipodeerror.label" default="Tipodeerror" />
		
	</label>
	<g:textField name="tipodeerror" value="${erroresperadoInstance?.tipodeerror}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: erroresperadoInstance, field: 'tipoerr', 'error')} required">
	<label for="tipoerr">
		<g:message code="erroresperado.tipoerr.label" default="Tipoerr" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoerr" name="tipoerr.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required="" value="${erroresperadoInstance?.tipoerr?.id}" class="many-to-one"/>
</div>

