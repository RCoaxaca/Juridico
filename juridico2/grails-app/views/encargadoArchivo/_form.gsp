<%@ page import="catalogos.EncargadoArchivo" %>


<div class="row">
    <div class="col-md-2"> 
        <div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'titulo', 'error')} ">
            <label for="titulo">
		<g:message code="encargadoArchivo.nombre.label" default="Titulo" />
		
            </label>
                <g:textField name="titulo" onblur="conMayusculas(this)" value="${encargadoArchivoInstance?.titulo}" class="form-control mayuscula"/>
        </div>
    </div>
    <div class="col-md-3"> 
        <div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'nombre', 'error')} ">
            <label for="nombre">
		<g:message code="encargadoArchivo.nombre.label" default="Nombre(s)" />
		
            </label>
                <g:textField name="nombre" onblur="conMayusculas(this)" value="${encargadoArchivoInstance?.nombre}" class="form-control mayuscula"/>
        </div>
    </div>
    
    <div class="col-md-3"> 

        <div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'ape_pat', 'error')} ">
                <label for="ape_pat">
                        <g:message code="encargadoArchivo.ape_pat.label" default="Apellido Paterno" />

                </label>
                <g:textField name="ape_pat" onblur="conMayusculas(this)" value="${encargadoArchivoInstance?.ape_pat}" class="form-control mayuscula"/>
        </div>
    </div>    

    
    <div class="col-md-3"> 
            <div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'ape_mat', 'error')} ">
                <label for="ape_mat">
		<g:message code="encargadoArchivo.ape_mat.label" default="Apellido Materno" />
		
                </label>
                <g:textField name="ape_mat" onblur="conMayusculas(this)" value="${encargadoArchivoInstance?.ape_mat}" class="form-control mayuscula"/>
            </div>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'activo', 'error')} ">
	<label for="activo">
		<g:message code="encargadoArchivo.activo.label" default="Activo" />
		
	</label>
	<g:checkBox name="activo" value="${encargadoArchivoInstance?.activo}" />
</div>





<!--<div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'fin', 'error')} required">
	<label for="fin">
		<g:message code="encargadoArchivo.fin.label" default="Fin" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fin" precision="day"  value="${encargadoArchivoInstance?.fin}"  />
</div>-->

<div class="fieldcontain ${hasErrors(bean: encargadoArchivoInstance, field: 'inicio', 'error')} required">
	<label for="inicio">
		<g:message code="encargadoArchivo.inicio.label" default="Inicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="inicio" precision="day"  value="${encargadoArchivoInstance?.inicio}"  />
</div>



