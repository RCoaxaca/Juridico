 <div class="${hasErrors(bean: scasolInstance, field: 'loc', 'error')} required">
            <g:if test="${localidadesList}">
                <label for="loc" class="label-control">
                         Localidad
                 </label> 
                <g:select id="loc" name="loc.id" from="${localidadesList}" optionKey="id" required=""
                    noSelection="['':'Selecciona Localidad']"
                    onchange="${remoteFunction(
                    controller:'localidades',
                    action:'getOficialia',
                    params:'\'id=\' + this.value +\'&mpo=\'+ mpo.value',
                    update:'oficialiaformal'          
                    )}"
                    value="${scasolInstance?.loc?.id}" class="many-to-one form-control" />
                    
                                   
            </g:if>
                <g:else>
                    No existen localidades para este municipio
                </g:else>
                
        
        </div>
  <div id="oficialiaformal" class="${hasErrors(bean: scasolInstance, field: 'ofi', 'error')} required">
 

 
  <g:if test="${oficialiasList}">
                <label for="ofi" class="label-control">
                    Oficialia
                    <span class="required-indicator">*</span>
                    </label>
                <g:select id="ofi" name="ofi.id" from="${oficialiasList}" optionKey="id" required="" value="${scasolInstance?.ofi?.id}" class="form-control" />
                 
            
            </g:if>
                    
                <g:else>
                    No existen oficialias para este municipio
                </g:else>
 
 </div>