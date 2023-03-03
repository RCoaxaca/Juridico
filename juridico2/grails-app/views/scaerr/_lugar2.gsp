
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
                            
                        <g:select id="dto" value="${distrito}" name="distrito"  from="${distrito}"  optionKey="id" class="form-control input-sm"/>
                        <br>
                        <g:select id="mpo" name="mpo" from="${mpo}" optionKey="id" required="" value="${mpo}" class="form-control" />
                        <br>
                        <g:select id="loc"  name="localidad" from="${listLoc}" optionKey="id"  required=""
                     noSelection="['':'Selecciona localidad']"
                    onblur="${remoteFunction(
                    controller:'scaerr',
                    action:'muestra',
                    params:'\'id=\' + this.value +\'&municipio=\'+ mpo.value+\'&distrito=\'+ distrito.value',
                    update:'mun'          
                    )}"                    
                     class="form-control input-sm" />  
             </div>

                    
    </div>
            <div class="col-md-3"></div>
            <div id="municio" class="form-group col-md-9">

                       

            </div>

            <div class="col-md-3"></div>
            <div id="localidadess" class="form-group col-md-9">
              
            </div>
  
