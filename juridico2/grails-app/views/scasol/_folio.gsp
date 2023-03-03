
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'folio', 'error')} required">
            <g:field name="folio" type="number" value="${p}" required="" class="form-control" onkeypress="return isNumberKey(event)" onblur="${remoteFunction( action:'validar',params:'\'id=\'+ this.value',update:'cambio')}"/>
	<label for="folio" class="label-control">
		<g:message code="scasol.folio.label" default="Folio"  />		
	</label>
        </div>
        <div>
                    <div class="alert alert-danger alert-dismissable">
                      <button type="button" class="close" data-dismiss="alert">&times;</button>
                      Numero de folio existente
                    </div>                    

        </div>
 