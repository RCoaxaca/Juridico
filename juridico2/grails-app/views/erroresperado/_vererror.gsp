 <div class="row form-group">
        
 <!--<div class="fieldcontain ${hasErrors(bean: opcionInstance, field: 'error', 'error')} required">
	<label for="error">
		<g:message code="opcion.error.label" default="Error" />
		<span class="required-indicator">*</span>
	</label>
                <div >
	<select id="error" name="error.id"  optionKey="id" required="" noSelection="['':'']" onchange="${remoteFunction( action:'getErrores', params:'\'id=\'+this.value',update:'errors' )}" value="${opcionInstance?.error?.id}">
         <option id="7" value="6">Seleccione campo</option>
         <option id="1" value="1"data-toggle="modal" data-target="#myModal1">ACLARACION</option>
         <option id="2" value="2"data-toggle="modal" data-target="#myModal1">APLICASION ART 71</option>
         <option id="3" value="3"data-toggle="modal" data-target="#myModal1">OMISION</option>
         <option id="4" value="4"data-toggle="modal" data-target="#myModal1">OMISION</option>
         <option id="5" value="5"data-toggle="modal" data-target="#myModal1">OMISION</option>
        </select>
        </div>
	
  
        
</div>-->
            <g:if test="${errorList}">
 
 <div class="fieldcontain ${hasErrors(bean: opcionInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="opcion.tipo.label" default="Tipo" />
		
	</label>

                <g:select id="tipo" name="tipo.id" optionKey="id" from="${errorList}"  required="" optionKey="id"
                    value="${opcionInstance?.tipo?.id}"  

                     class="many-to-one" />                 
</div>
                    <button type="button" data-toggle="modal" data-target="#myModal" onclick="${remoteFunction (
                    controller:"opcion",
                    action:'mostrar',
                    params:'\'id=\' + 1')}">Capturar registro</button>
                    
            </g:if>
                <g:else>
                    No existen aclaraciones para este error
                </g:else>
                
        




        
                   


