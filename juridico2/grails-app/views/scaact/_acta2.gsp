<legend class="text-center">Datos del Acta de Reconocimiento</legend>
<div class="row">
    <div class="col-md-6">
            <div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'numacta', 'error')} ">

                <g:field name="numacta" class="form-control input-sm" type="number" value="${scaactInstance.numacta}"  onkeypress="return isNumberKey(event)"/>
                <label for="numacta">
                        Numero de acta:
                </label>
           </div>
    </div>    
    <div class="col-md-5">       
        <div >
                
                        <g:field name="fechaacta" type="date" value="${scaactInstance?.fechaacta.toString()}" class="form-control" />
        </div>
                <label for="fechaacta">
                        Levantada con Fecha:
                </label>
</div>
</div>


<div class="row">
    <div class="col-md-3">
                <g:select id="dto" name="dto.id" from="${catalogos.Scadto.list()}" optionKey="id" 
                    noSelection="['':'Selecciona un Distrito']"
                    onchange="${remoteFunction(
                    controller:'scampo',
                    action:'getMunici',
                    params:'\'id=\' + this.value',
                    update:'munici'          
                    )}"

                    value="${scaactInstance?.dto?.id}" class="form-control input-sm" />
             <label for="dto">
                Distrito           
            </label>
    </div>    
    <div class="col-md-4">
     
        <div id="munici">
                 <label for="mpo" class="control-label">
                        Municipio

                </label>
             </div>


        </div>
    <div class="col-md-4">
   <div id="local">
          <label for="loc" class="control-label">
                   Localidad
                   
            </label>                 
        </div>  
        
    </div>  


</div>

