<!--<label for="debeser" class="label-control">
		<g:message code="scaerr.debeser.label" default="Lo correcto es:" class="form-control mayuscula" />
		
	</label>
                <input type="date" name="debeser"  data-toggle="tooltip" data-placement="top" title="Formato de fecha dia/mes/año ejemplo:12/02/2014" class="form-control input-sm" name="fecha" onBlur="${remoteFunction(
                            controller:'scaerr',
                            action:'verFecha',
                            params:'\'fecha=\' + this.value',
                            update:'mun'          
                            )}">-->


<div id="nuevafecha">
	<label for="debeser" class="label-control">
		<g:message code="scaerr.debeser.label" default="Lo correcto es:" class="form-control mayuscula" />
		
	</label>
                
                	<g:field type="date" id="dbser" name="debeser"   
                        data-toggle="tooltip" data-placement="top"  
                        class="form-control input-sm" name="fecha" 
                        onBlur="${remoteFunction(
                            controller:'scaerr',
                            action:'verFecha2',
                            params:'\'fecha=\' + this.value',
                            update:'nuevafecha'          
                            )}"/>


</div>

