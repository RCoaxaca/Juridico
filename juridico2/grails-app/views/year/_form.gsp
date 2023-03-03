<%@ page import="catalogos.Year" %>



<div class="fieldcontain ${hasErrors(bean: yearInstance, field: 'xyear', 'error')} required">
	<label for="xyear">
		<g:message code="year.xyear.label" default="Xyear" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xyear" type="number" value="${yearInstance.xyear}" required=""/>
</div>

