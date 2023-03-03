<div class="form-group">
	<label for="debeser" class="label-control col-md-3">
		Debeser
	</label>
        <div class="col-md-9">
            <g:textField name="debeser" value="${debeser}" onfocus="${remoteFunction(
                            controller:'scaerr',  action:'buscarTipoErr',
                            params:'\'id=\' + campo.value +\'&contiene=\'+ this.value',
                            update:'mun')}"  class="form-control input-sm" />
        </div>
</div>