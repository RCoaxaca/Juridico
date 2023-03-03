<%@ page import="tablas.Scapro" %>



<div class="fieldcontain ${hasErrors(bean: scaproInstance, field: 'prog', 'error')} required">
	<label for="prog">
		<g:message code="scapro.prog.label" default="Prog" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="prog" type="number" value="${scaproInstance.prog}" required=""/>
</div>

