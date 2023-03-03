
                <g:select id="mpo" name="mpo.id" from="${municipiosList}" optionKey="id" required=""
                    noSelection="['':'Selecciona un Municipio']"
                    onchange="${remoteFunction(
                    controller:'localidades',
                    action:'getLocal',
                    params:'\'id=\' + this.value',
                    update:'local'          
                    )}"

                    value="${scaactInstance?.mpo?.id}" class="form-control input-sm" />
                    
            <label for="mpo" >
                   Municipio                    
            </label>
                        
        
        

