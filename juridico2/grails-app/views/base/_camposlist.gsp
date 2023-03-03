<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<div class="fieldcontain ${hasErrors(bean: baseInstance, field: 'campo', 'error')} required">
	<label for="campo">
		<g:message code="base.campo.label" default="Campo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="campo" name="campo.id" from="${camposList}" optionKey="id" required="" value="${baseInstance?.campo?.id}" class="form-control input-sm"/>
</div>
