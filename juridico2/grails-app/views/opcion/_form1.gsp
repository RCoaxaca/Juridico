<%@ page import="catalogos.Opcion" %>
<div form-horizontal>
<!--<div class="form-group invisible ${hasErrors(bean: opcionInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="opcion.error.label" default="Error" class="label-control" />	
	</label>	
         <select id="error" name="error.id"  optionKey="id" required="" noSelection="['':'']"  value="${opcionInstance?.error?.id}" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre',params:' \'id=\'+mensaj.value' )}" onchange="${remoteFunction( action:'getDat', params: '\'id=\'+this.value +\'&exap=\'+ mensaj.value', update:'errors' )}" class="form-control">
         <select id="error" name="error.id"  optionKey="id" required="" noSelection="['':'']" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre' )}" value="${opcionInstance?.error?.id}" onchange="${remoteFunction( action:'getDat', params:'\'id=\'+this.value',update:'errors' )}" class="form-control">             
         <option id="6" value="0">Seleccione campo</option>
         <option id="1" value="1" data-toggle="modal" data-target="#myModal1">ACLARACION</option>
         <option id="2" value="2"data-toggle="modal" data-target="#myModal1">APLICACION ART 71</option>
         <option id="3" value="3"data-toggle="modal" data-target="#myModal1">OFICIO DE LEGITIMACION</option>
         <option id="4" value="4"data-toggle="modal" data-target="#myModal1">OFICIO DE RECONOCIMIENTO</option>
         <option id="5" value="5" data-toggle="modal" data-target="#myModal1">TESTEAR OFICIO</option>
        </select>
</div>-->
<button type="button" name="browser" class="btn btn-default btn-lg btn-block btn-sm" value="1" onclick="check(this.value)">ACLARACION</button>
<button type="button" class="btn btn-default btn-lg btn-block btn-sm" value="2" >APLICACION ART 71</button>
<button type="button" class="btn btn-default btn-lg btn-block btn-sm" value="3" >OFICIO DE LEGITIMACION</button>     
<button type="button" class="btn btn-default btn-lg btn-block btn-sm" value="4" >OFICIO DE RECONOCIMIENTO</button>     
<button type="button" class="btn btn-default btn-lg btn-block btn-sm" value="5" >TESTEAR OFICIO</button>     

<g:select id="error" name="error.id" from="${catalogos.Tcorrect.list()}" optionKey="id" value="${opcionInstance?.error?.id}" class="many-to-one" noSelection="['null': '']"/>           
<input name="hola">





         <div id="progre" class="invisible" ></div>
         <div id="progreerro" class="invisible" ></div>
         <div id="envioerros" class="invisible"></div>
         <div id="continuo" class="invisible"></div>      
     
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
       
      </div>
      <div class="modal-body">
          <div id="errors"></div>
      </div>   
    <div class="modal-footer">
             <button type="submit" class="btn btn-primary">Guardar</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>         
    </div>        
    </div>
    
  </div>
</div>
</div>

