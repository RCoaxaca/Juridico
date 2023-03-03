<%@ page import="catalogos.Opcion" %>

<div class="fieldcontain ${hasErrors(bean: opcionInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="opcion.error.label" default="Error" />
		
	</label>	
         <select id="error" name="error.id"  optionKey="id" required="" noSelection="['':'']" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre' )}" value="${opcionInstance?.error?.id}" onchange="${remoteFunction( action:'getDat', params:'\'id=\'+this.value',update:'errors' )}">
         <option id="6" value="4">Seleccione campo</option>
         <option id="1" value="1"data-toggle="modal" data-target="#myModal1">ACLARACION</option>
         <option id="2" value="2"data-toggle="modal" data-target="#myModal1">APLICASION ART 71</option>
         <option id="3" value="4"data-toggle="modal" data-target="#myModal1">OMISION</option>
         <option id="4" value="5"data-toggle="modal" data-target="#myModal1">OMISION</option>
         <option id="5" value="6"data-toggle="modal" data-target="#myModal1">OMISION</option>
        </select>
</div>
         <div id="progre"></div>
         <div id="progreerro"></div>
         <div id="envioerros"></div>
         <div id="continuo"></div>
     
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
       
      </div>
      <div class="modal-body">
          <div id="errors"></div>
      </div>
     
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerra</button>
        <button type="button" class="btn btn-primary">guardar</button>
      
    </div>
  </div>
</div>

