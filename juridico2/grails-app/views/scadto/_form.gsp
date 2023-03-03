<%@ page import="catalogos.Scadto" %>



<div class="fieldcontain ${hasErrors(bean: scadtoInstance, field: 'clv', 'error')} required">
	<label for="clv">
		<g:message code="scadto.clv.label" default="Clv" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="clv" type="number" value="${scadtoInstance.clv}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scadtoInstance, field: 'clvreg', 'error')} required">
	<label for="clvreg">
		<g:message code="scadto.clvreg.label" default="Clvreg" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="clvreg" type="number" value="${scadtoInstance.clvreg}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scadtoInstance, field: 'descc', 'error')} ">
	<label for="descc">
		<g:message code="scadto.descc.label" default="Descc" />
		
	</label>
	<g:textField name="descc" value="${scadtoInstance?.descc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scadtoInstance, field: 'municipios', 'error')} ">
	<label for="municipios">
		<g:message code="scadto.municipios.label" default="Municipios" />
		
	</label>
	<g:select name="municipios" from="${catalogos.Scampo.list()}" multiple="multiple" optionKey="id" size="5" value="${scadtoInstance?.municipios*.id}" class="many-to-many"/>
</div>

