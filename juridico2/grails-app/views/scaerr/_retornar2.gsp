<div class="col-md-13">
    
	<label for="debeser" class="label-control">
		<g:message code="scaerr.debeser.label" default="Lo correcto es:" class="form-control mayuscula" />
	</label>
        
            
            <g:textField name="debeser" value="${debeser}"  class="form-control mayuscula" />
    
        
</div>

<!--<div id="mun" class="form-group ${hasErrors(bean: scaerrInstance, field: 'debeser', 'error')} ">
	<label for="debeser" class="label-control">
		<g:message code="scaerr.debeser.label" default="Lo correcto es:" class="form-control mayuscula" />
		
	</label>
                
                	<g:textField name="debeser" onblur="conMayusculas(this)" onfocus="${remoteFunction(
                            controller:'scaerr',  action:'buscarTipoErr',
                            params:'\'id=\' + campo.value',
                            update:'mun')}" value="${scaerrInstance?.debeser}" class="form-control mayuscula"/>
                

</div>-->

