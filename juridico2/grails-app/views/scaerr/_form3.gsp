<%@ page import="tablas.Scaerr" %>


  <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'terror', 'error')} required">
	<label for="terror">
		<g:message code="scaerr.terror.label" default="Tipo de error:" class="label-control"/>
		<span class="required-indicator">*</span>
	</label>
	<g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required="" 
         noSelection="['':'']" class="form-control"
                    onchange="${remoteFunction(
                    controller:'scaerr',
                    action:'tipoActa',
                    params:'\'id=\' + this.value',
                    update:'campo'          
                    )}"
            
        value="${scaerrInstance?.terror?.id}" class="form-control"/>
</div>

<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'campo', 'error')} required">
	<label for="campo" classs="label-control">
		<g:message code="scaerr.campo.label" default="Campo:" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="campo" name="campo.id" from="${catalogos.Fields.list()}" optionKey="id" required="" 
          noSelection="['':'']"   onchange="${remoteFunction(
                    controller:'scaerr',
                    action:'verTipo',
                    params:'\'id=\' + this.value',
                    update:'mun'          
                    )}"       
        value="${scaerrInstance?.campo?.id}" class="form-control" />
</div>


<div class="fieldcontain form-group ${hasErrors(bean: scaerrInstance, field: 'base', 'error')} ">
	<label for="base" class="label-control">
		<g:message code="scaerr.base.label" default="Base" />
		
	</label>
	<g:textField name="base" value="${scaerrInstance?.base}" onblur="conMayusculas(this)" class="form-control mayuscula"/>
</div>

<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'contiene', 'error')} ">
	<label for="contiene">
		<g:message code="scaerr.contiene.label" default="Contiene:" class="label-control"/>
		
	</label>
	<g:textField name="contiene" value="${scaerrInstance?.contiene}" onblur="conMayusculas(this)" class="form-control mayuscula"/>
</div>

<div id="muni" class="group-control">

</div>
<div id="mun" class="form-group ${hasErrors(bean: scaerrInstance, field: 'debeser', 'error')} ">
	<label for="debeser" class="label-control">
		<g:message code="scaerr.debeser.label" default="Lo correcto es:" class="form-control mayuscula" />
		
	</label>
	<g:textField name="debeser" value="${scaerrInstance?.debeser}" onblur="conMayusculas(this)" class="form-control mayuscula"/>
</div>

<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'procede', 'error')} ">
	<label for="procede">
		<g:message code="scaerr.procede.label" default="Procede:" class="label-control" />		
	</label>
	<!--<g:textField name="procede" value="${scaerrInstance?.procede}" class="form-control"/>-->
        <select name="procede" noSelection="['':'']" class="form-control">
         <option value=""></option>
         <option value="SI">SI</option>
         <option value="SI">NO</option>
        </select>
</div>
<div class=" ${hasErrors(bean: scaerrInstance, field: 'base', 'error')} ">
    <label for="base" class="label-control">
		<g:message code="scaerr.base.label" default="De acuerdo a:" />
		
	</label>
	<g:textField name="base" value="${scaerrInstance?.base}" onblur="conMayusculas(this)" class="form-control mayuscula"/>
</div>
<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'donde', 'error')} required ">
	<label for="donde">
		<g:message code="scaerr.donde.label" default="Se envia a:" class="label-control"/>
		<span class="required-indicator">*</span>
	</label>
	<!--<g:field name="donde" type="number" value="${scaerrInstance.donde}" required="" class="form-control"/>-->
        <g:select name="donde" from="${catalogos.Terror.list()}" optionKey="id" value="${scaerrInstance.donde}" class="form-control" noSelection="['null': '']" />
</div>

<div class="row invisible">
   <div class="col-md-3">
       <div class="row ">
                <div class="fieldcontain ${hasErrors(bean: scaerrInstance, field: 'expano', 'error')} required">
                <g:field name="expano" type="number" value="${scaerrInstance.expano}" required=""/>
            </div>
        </div>
    </div>    
   <div class="col-md-3">
       <div class="row">
                <div class="fieldcontain ${hasErrors(bean: scaerrInstance, field: 'expro', 'error')} required">
                <g:field name="expro" type="number" value="${scaerrInstance.expro}" required=""/>
            </div>   
       </div>
   </div>
   <div class="col-md-3">
       <div class="row">
            <div class="fieldcontain ${hasErrors(bean: scaerrInstance, field: 'tcorrect', 'error')} required">
            <g:select id="tcorrect" name="tcorrect.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required="" value="${scaerrInstance?.tcorrect?.id}" class="many-to-one"/>
        </div>              
       </div>
   </div>
</div>












