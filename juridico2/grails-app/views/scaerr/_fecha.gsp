<html>

    <body>
        <div class="form-group">
            <label class="label-control col-md-3">Debe ser: </label>
            <div class="col-md-9">
                <input type="date"   data-toggle="tooltip" data-placement="top" title="Formato de fecha dia/mes/año ejemplo:12/02/2014" class="form-control input-sm" name="fecha" onBlur="${remoteFunction(
                            controller:'scaerr',
                            action:'verFecha',
                            params:'\'fecha=\' + this.value',
                            update:'mun'          
                            )}">
            </div>
        </div>
    </body>
</html>