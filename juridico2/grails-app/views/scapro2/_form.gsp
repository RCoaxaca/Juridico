<%@ page import="catalogos.Scapro2" %>



<div class="fieldcontain ${hasErrors(bean: scapro2Instance, field: 'prog', 'error')} required">
	<label for="prog">
		<g:message code="scapro2.prog.label" default="Prog" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="prog" type="number" value="${scapro2Instance.prog}" required=""/>
</div>

