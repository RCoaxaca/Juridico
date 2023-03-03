<%@ page import="catalogos.Tipoactas" %>



<div class="fieldcontain ${hasErrors(bean: tipoactasInstance, field: 'actas', 'error')} ">
	<label for="actas">
		<g:message code="tipoactas.actas.label" default="Actas" />
		
	</label>
	<g:select name="actas" from="${catalogos.Scadto.list()}" multiple="multiple" optionKey="id" size="5" value="${tipoactasInstance?.actas*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoactasInstance, field: 'tipoacta', 'error')} ">
	<label for="tipoacta">
		<g:message code="tipoactas.tipoacta.label" default="Tipoacta" />
		
	</label>
	<g:textField name="tipoacta" value="${tipoactasInstance?.tipoacta}"/>
</div>

