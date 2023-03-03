<%@ page import="catalogos.Scaprn" %>



<div class="fieldcontain ${hasErrors(bean: scaprnInstance, field: 'mimp', 'error')} required">
	<label for="mimp">
		<g:message code="scaprn.mimp.label" default="Mimp" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="mimp" type="number" value="${scaprnInstance.mimp}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scaprnInstance, field: 'tlet', 'error')} required">
	<label for="tlet">
		<g:message code="scaprn.tlet.label" default="Tlet" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tlet" type="number" value="${scaprnInstance.tlet}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scaprnInstance, field: 'toja', 'error')} required">
	<label for="toja">
		<g:message code="scaprn.toja.label" default="Toja" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="toja" type="number" value="${scaprnInstance.toja}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scaprnInstance, field: 'usrid', 'error')} ">
	<label for="usrid">
		<g:message code="scaprn.usrid.label" default="Usrid" />
		
	</label>
	<g:textField name="usrid" value="${scaprnInstance?.usrid}"/>
</div>

