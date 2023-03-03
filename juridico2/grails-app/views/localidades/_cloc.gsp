<g:select id="loc" name="loc.id" from="${localidadesList}" optionKey="id" required=""
onchange="${remoteFunction(
                    controller:'localidades',
                    action:'getOficialia',
                    params:'\'id=\' + this.value',
                    update:'oficialiaformal7'          
                    )}"
                    value="${scaactInstance?.loc?.id}" class="form-control input-sm" />
                    
                    <label for="loc" >
                   Localidad                    
            </label>
            
         