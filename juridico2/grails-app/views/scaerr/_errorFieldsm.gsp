<div class="form-group">
    <div class="label-control col-md-3">        
            <label for="campo">
                Campo
            </label>
    </div>
    <div class="col-md-9">
            <g:select id="campo" name="campo.id" from="${errorh}" optionKey="id" required="" 
             noSelection="['':'']"
                        onchange="${remoteFunction(
                        controller:'scaerr',
                        action:'verTipo',
                        params:'\'id=\' + this.value',
                        update:'mun'          
                        )}"     
            value="${scaerrInstance?.campo?.id}" class="form-control input-sm" required="" />
    </div>        
</div>