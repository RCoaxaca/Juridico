<%@ page import="tablas.Nota" %>



<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'namofi', 'error')} ">
	<label for="namofi">
		<g:message code="nota.namofi.label" default="Namofi" />
		
	</label>
	<g:textField name="namofi" value="${notaInstance?.namofi}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'dic', 'error')} ">
	<label for="dic">
		<g:message code="nota.dic.label" default="Dic" />
		
	</label>
	<g:textField name="dic" value="${notaInstance?.dic}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'idn', 'error')} required">
	<label for="idn">
		<g:message code="nota.idn.label" default="Idn" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="idn" type="number" value="${notaInstance.idn}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'expano', 'error')} required">
	<label for="expano">
		<g:message code="nota.expano.label" default="Expano" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="expano" type="number" value="${notaInstance.expano}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'expro', 'error')} required">
	<label for="expro">
		<g:message code="nota.expro.label" default="Expro" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="expro" type="number" value="${notaInstance.expro}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'nota', 'error')} ">
	<label for="nota">
		<g:message code="nota.nota.label" default="Nota" />
		
	</label>
	<g:textField name="nota" value="${notaInstance?.nota}"/>
</div>

