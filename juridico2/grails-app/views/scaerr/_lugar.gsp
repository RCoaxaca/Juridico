
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="catalogos.Scadto" %>
<%@ page import="catalogos.Scampo" %>
<%@ page import="catalogos.Localidades" %>
<label class="label-control col-md-3">Debe ser: </label>

    
    <div class="form-group col-md-9">
                <label for="dto" class="label-control col-md-3">
                   Distrito          
                </label>   
             <div class="col-md-9">
                            <g:select id="dto" name="distrito"   from="${catalogos.Scadto.list()}"  optionKey="id" class="form-control input-sm"
                        noSelection="['':'Selecciona un Distrito']"
                        onchange="${remoteFunction(
                        controller:'scampo',
                        action:'getMunicipio2',
                        params:'\'id=\' + this.value',
                        update:'municio'          
                        )}"
                         />
        </div>

                    
    </div>
            <div class="col-md-3"></div>
            <div id="municio" class="form-group col-md-9">

                       

            </div>

            <div class="col-md-3"></div>
            <div id="localidadess" class="form-group col-md-9">
              
            </div>
  
