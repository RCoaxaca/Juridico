 <div class="form-group ">
     <label for="loc" class="label-control col-md-3">
                    Localidad
     </label>
            <g:if test="${localidadesList}">
     <div class="col-md-9">
                <g:select id="loc"  name="localidad" from="${localidadesList}" optionKey="id"  required=""
                     noSelection="['':'Selecciona localidad']"
                    onblur="${remoteFunction(
                    controller:'scaerr',
                    action:'muestra',
                    params:'\'id=\' + this.value +\'&municipio=\'+ municipio.value+\'&distrito=\'+ distrito.value',
                    update:'mun'          
                    )}"                    
                    value="${scasolInstance?.loc?.id}" class="form-control input-sm" />     
     </div>                 
            </g:if>
                <g:else>
                    No existen localidades para este municipio
                </g:else>
                
        
        </div>
