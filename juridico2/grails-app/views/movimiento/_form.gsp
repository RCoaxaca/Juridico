<%@ page import="catalogos.Movimiento" %>


<div class="fieldcontain ${hasErrors(bean: movimientoInstance, field: 'numero_expediente', 'error')} ">
	<label for="numero_expediente">
		<g:message code="movimiento.numero_expediente.label" default="Numero de expediente" />
		
	</label>
	<g:textField name="numero_expediente" value="${movimientoInstance?.numero_expediente}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: movimientoInstance, field: 'usuarioentrega', 'error')} ">
	<label for="usuarioentrega">
		<g:message code="movimiento.usuarioentrega.label" default="Usuario para Prestamo" />
		
	</label>
	<g:textField name="usuarioentrega" value="${movimientoInstance?.usuarioentrega}"/>
</div>



<div class="fieldcontain ${hasErrors(bean: movimientoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="movimiento.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${movimientoInstance?.fecha}"  />
</div>









<div class="fieldcontain ${hasErrors(bean: movimientoInstance, field: 'usuariopresta', 'error')} invisible">
	<label for="usuariopresta">
		<g:message code="movimiento.usuariopresta.label" default="Usuariopresta" />
		
	</label>
	<g:select id="usuariopresta" name="usuariopresta.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>
</div>





<div class="fieldcontain ${hasErrors(bean: movimientoInstance, field: 'salida', 'error')} invisible">
	<label for="salida">
		<g:message code="movimiento.salida.label" default="Salida" />
		
	</label>
	<g:datePicker name="salida" precision="day"  value="${movimientoInstance?.salida}" />
</div>





