<%@ page import="catalogos.Base" %>
    <script>
    function vistaPrevia() {
        <!--field.value = field.value.toUpperCase()-->
    var texto=document.getElementById("base").value
    var textoc="Y DE ACUERDO A: "+texto+", LO CORRECTO DEBE DE SER:"
    <!--document.getElementById("base").value=textoc-->
    document.getElementById('vista').value = textoc; 
    <!--alert(texto)-->
        
      }
      
</script>


<div class="fieldcontain ${hasErrors(bean: baseInstance, field: 'acta', 'error')} required">
	<label for="acta">
		<g:message code="base.acta.label" default="Acta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="acta" name="acta.id" from="${catalogos.Tipoactas.list()}" optionKey="id" required="" 
        noSelection="['':'Selecciona tipo de acta']"
        onchange="${remoteFunction(
                    controller:'base',
                    action:'getCampo',
                    params:'\'id=\' + this.value',
                    update:'campoacta'          
                    )}"
        value="${baseInstance?.acta?.id}" class="form-control input-sm"/>
</div>



<div id="campoacta">

<div class="fieldcontain ${hasErrors(bean: baseInstance, field: 'campo', 'error')} required">
	<label for="campo">
		<g:message code="base.campo.label" default="Campo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="campo" name="campo.id" from="${catalogos.Fields.list()}" optionKey="id" required="" value="${baseInstance?.campo?.id}" class="form-control input-sm"/>
</div>
</div>

<div class="fieldcontain ${hasErrors(bean: baseInstance, field: 'base', 'error')} ">
	<label for="base">
		<g:message code="base.base.label" default="Base" />
		
	</label>
	<g:textField id="base" class="form-control" onkeyup="conMayusculas(this),vistaPrevia()"   name="base" value="${baseInstance?.base}"/>
</div>

<div id="ocul" >
    <br>
    <label>Vista Previa</label>
    <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'obser', 'error')} ">
        <textarea name="obser" id="vista" onchange="conMayusculas(this)"  class="form-control" rows="3"  ></textarea>
        <br>
        <!--<button id="btnguardar" class="btn btn-primary" onclick="ocultar()" >Guardar</button>-->
       <!-- <g:submitButton  name="create" class="btn btn-primary" onclick="ocultar()" value="Guardarrr" />-->
       
    </div>           
</div>

