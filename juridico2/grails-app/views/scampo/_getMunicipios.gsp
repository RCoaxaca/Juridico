	
     <div class="${hasErrors(bean: scasolInstance, field: 'mpo', 'error')} required">
            <g:if test="${municipiosList}">
                 <label for="mpo" class="label-control">
                    Municipio
                    </label>
                <g:select id="mpo" name="mpo.id" from="${municipiosList}" optionKey="id" required=""
                    noSelection="['':'Selecciona un Municipio']"
                    onchange="${remoteFunction(
                    controller:'localidades',
                    action:'getLocalidades',
                    params:'\'id=\' + this.value',
                    update:'localidadess'          
                    )}"
                    value="${scasolInstance?.mpo?.id}" class="form-control" />
                    
                 
            </g:if>
                <g:else>
                    No existen municipios para este distrito
                </g:else>
                
        
        </div>
    
    
    
    
   <!-- <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'mpo', 'error')} ">
                            <g:select id="mpo" name="mpo.id" from="${catalogos.Scampo.list()}" optionKey="id" value="${scasolInstance?.mpo?.id}" class="form-control" />
                            <label for="mpo">
                                   Municipio
                            </label>                                                     
                    </div>
        -->
        
        
                   


