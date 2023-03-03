<%@ page import="catalogos.Resguardo" %>
<div class="row"> 
<div class="col-md-4">
<div class="fieldcontain ${hasErrors(bean: resguardoInstance, field: 'numero_expediente', 'error')} ">
	<label for="numero_expediente">
		<g:message code="resguardo.numero_expediente.label" default="Numero de expediente" />
		
	</label>
	<g:textField class="form-control" id="noexpediente" name="numero_expediente" onkeypress="return pulsar(event)" value="${resguardoInstance?.numero_expediente}"/>
</div>
</div>

<div class="col-md-3">
<div class="fieldcontain ${hasErrors(bean: resguardoInstance, field: 'papeleta', 'error')} ">
	<label for="papeleta">
		<g:message code="resguardo.papeleta.label" default="Papeleta" />
		
	</label>
	<g:checkBox id="papeleta" name="papeleta" value="${resguardoInstance?.papeleta}" />
</div>
</div>


<div class="col-md-3">
<div class="fieldcontain ${hasErrors(bean: resguardoInstance, field: 'resolucion', 'error')} ">
	<label for="resolucion">
		<g:message code="resguardo.resolucion.label" default="Resolucion" />
		
	</label>
	<g:checkBox name="resolucion" value="${resguardoInstance?.resolucion}" />
</div>
</div>

</div>





<div class="fieldcontain ${hasErrors(bean: resguardoInstance, field: 'entrega', 'error')} ">
	<label for="entrega">
		<g:message code="resguardo.entrega.label" default="Entrega" />
		
	</label>
	<g:textField class="form-control" name="entrega" value="${resguardoInstance?.entrega}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: resguardoInstance, field: 'ubicacion', 'error')} ">
	<label for="ubicacion">
		<g:message code="resguardo.ubicacion.label" default="Ubicacion" />
		
	</label>
	<g:textField class="form-control" name="ubicacion" value="${resguardoInstance?.ubicacion}"/>
</div>

<div class="invisible">

<div class="fieldcontain ${hasErrors(bean: resguardoInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="resguardo.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${sec.loggedInUserInfo(field: 'id')}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resguardoInstance, field: 'fecha_entrada', 'error')} required">
	<label for="fecha_entrada">
		<g:message code="resguardo.fecha_entrada.label" default="Fechaentrada" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha_entrada" precision="day"  value="${resguardoInstance?.fecha_entrada}"  />
</div>

</div>

