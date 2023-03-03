<%@ page import="catalogos.Movimiento" %>



<div class="fieldcontain ${hasErrors(bean: movimientoInstance, field: 'numero_expediente', 'error')} ">
	<label for="numero_expediente">
		<g:message code="movimiento.numero_expediente.label" default="Numero de expediente" />
		
	</label>
	<g:textField name="numero_expediente" onkeypress="return pulsarenter(event)" value="${movimientoInstance?.numero_expediente}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: movimientoInstance, field: 'usuariorecibe', 'error')} invisible required">
	<label for="usuariorecibe">
		<g:message code="movimiento.usuariorecibe.label" default="Usuariorecibe" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuariorecibe" name="usuariorecibe.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${sec.loggedInUserInfo(field: 'id')}" class="many-to-one"/>
</div>



<div class="invisible">
	<label for="usuarioentrega">
		<g:message code="movimiento.usuarioentrega.label" default="Usuario para Prestamo" />
		
	</label>
	<g:textField id="idmovimiento" name="idmovimiento" />
</div>



