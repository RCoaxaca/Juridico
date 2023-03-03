

            <g:if test="${municipiosList}">
                 <label for="mpo" class="label-control col-md-3">
                                Municipio
                    </label>   
                    <div class="col-md-9">
                <g:select id="mpo" name="municipio" from="${municipiosList}" optionKey="id" required=""
                    noSelection="['':'Selecciona un Municipio']"
                    onchange="${remoteFunction(
                    controller:'localidades',
                    action:'getLocalidades2',
                    params:'\'id=\' + this.value',
                    update:'localidadess'          
                    )}"
                    value="${scasolInstance?.mpo?.id}" class="form-control input-sm" />
                  </div>                  
            </g:if>
                <g:else>
                    No existen municipios para este distrito
                </g:else>
                
        
      