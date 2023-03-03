<%@ page import="catalogos.Causr" %>



<div class="fieldcontain ${hasErrors(bean: causrInstance, field: 'passw', 'error')} ">
	<label for="passw">
		<g:message code="causr.passw.label" default="Passw" />
		
	</label>
	<g:textField name="passw" maxlength="10" value="${causrInstance?.passw}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: causrInstance, field: 'clv', 'error')} ">
	<label for="clv">
		<g:message code="causr.clv.label" default="Clv" />
		
	</label>
	<g:textField name="clv" value="${causrInstance?.clv}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: causrInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="causr.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${causrInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: causrInstance, field: 'outdate', 'error')} required">
	<label for="outdate">
		<g:message code="causr.outdate.label" default="Outdate" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="outdate" precision="day"  value="${causrInstance?.outdate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: causrInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="causr.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${causrInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: causrInstance, field: 'updates', 'error')} required">
	<label for="updates">
		<g:message code="causr.updates.label" default="Updates" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="updates" precision="day"  value="${causrInstance?.updates}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: causrInstance, field: 'usrtype', 'error')} ">
	<label for="usrtype">
		<g:message code="causr.usrtype.label" default="Usrtype" />
		
	</label>
	<g:textField name="usrtype" value="${causrInstance?.usrtype}"/>
</div>

