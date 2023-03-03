<%@ page import="catalogos.EncargadoAclaraciones" %>


<div class="row">
     <div class="col-md-2"> 
    <div class="fieldcontain ${hasErrors(bean: encargadoAclaracionesInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="encargadoAclaraciones.nombre.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" onblur="conMayusculas(this)" value="${encargadoAclaracionesInstance?.titulo}" class="form-control mayuscula"/>
</div>
</div>
    <div class="col-md-3"> 
    <div class="fieldcontain ${hasErrors(bean: encargadoAclaracionesInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="encargadoAclaraciones.nombre.label" default="Nombre(s)" />
		
	</label>
	<g:textField name="nombre" onblur="conMayusculas(this)" value="${encargadoAclaracionesInstance?.nombre}" class="form-control mayuscula"/>
</div>
</div>


<div class="col-md-3"> 
<div class="fieldcontain ${hasErrors(bean: encargadoAclaracionesInstance, field: 'ape_pat', 'error')} ">
	<label for="ape_pat">
		<g:message code="encargadoAclaraciones.ape_pat.label" default="Apellido Paterno" />
		
	</label>
	<g:textField name="ape_pat" onblur="conMayusculas(this)" value="${encargadoAclaracionesInstance?.ape_pat}" class="form-control mayuscula"/>
</div>
</div>
    

<div class="col-md-3"> 
<div class="fieldcontain ${hasErrors(bean: encargadoAclaracionesInstance, field: 'ape_mat', 'error')} ">
	<label for="ape_mat">
		<g:message code="encargadoAclaraciones.ape_mat.label" default="Apellido Materno" />
		
	</label>
	<g:textField name="ape_mat" onblur="conMayusculas(this)" value="${encargadoAclaracionesInstance?.ape_mat}" class="form-control mayuscula"/>
</div>
</div>
</div>



<div class="fieldcontain ${hasErrors(bean: encargadoAclaracionesInstance, field: 'activo', 'error')} ">
	<label for="activo">
		<g:message code="encargadoAclaraciones.activo.label" default="Activo" />
		
	</label>
	<g:checkBox name="activo" value="${encargadoAclaracionesInstance?.activo}" />
</div>

<div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'inicio', 'error')} required">
	<label for="inicio">
		<g:message code="encargadoArchivo.inicio.label" default="Inicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="inicio" value="${encargadoArchivoInstance?.inicio}"  />
</div>
