<%@ page import="catalogos.Opcion" %>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>
        <g:hasErrors bean="${opcionInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${opcionInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form action="save">
            <fieldset class="form">
            
                    <div form-horizontal>
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="1" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value', update:'errors' )}">ACLARACION</button>
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="2" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value+\'&exap=\'+ idgeneral.value', update:'errors' )}">APLICACION ART 71</button>
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="3" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value+\'&exap=\'+ idgeneral.value', update:'errors' )}">OFICIO DE LEGITIMACION</button>     
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="4" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value+\'&exap=\'+ idgeneral.value', update:'errors' )}">OFICIO DE RECONOCIMIENTO</button>     
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="5" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value+\'&exap=\'+ idgeneral.value', update:'errors' )}">TESTAR DE OFICIO</button>
                        
                            <!--<button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="1" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value' )}" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value', update:'errors' )}">ACLARACION</button>
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="2" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value' )}" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value', update:'errors' )}">APLICACION ART 71</button>
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="3" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value' )}" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value', update:'errors' )}">OFICIO DE LEGITIMACION</button>     
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="4" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value' )}" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value', update:'errors' )}">OFICIO DE RECONOCIMIENTO</button>     
                            <button type="button" id="error" name="error.id" class="btn btn-default btn-lg btn-block btn-sm" data-toggle="modal" data-target="#myModal1" value="5" onmouseover="${remoteFunction(action:'verProgresivo',update:'progre', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value' )}" onclick="${remoteFunction( action:'getDat', params: '\'id=\'+this.value +\'&exap=\'+ idgeneral.value', update:'errors' )}">TESTAR OFICIO</button>  -->   
                        </div>
                        <div class="col-md-4"></div>
                                    <div id="progre" class="invisible" ></div>
                                             <div id="progreerro" class="invisible" ></div>
                                             <div id="envioerros" class="invisible"></div>
                                             <div id="continuo" class="invisible"></div>      

                                             
                                    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                      <div class="modal-dialog">
                                        <div class="modal-content">
                                         <!-- <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                          </div>-->
                                          <div class="modal-body">
                                              <div id="errors"></div>
                                              <legend></legend>   
                                                 <button type="submit" class="btn btn-primary">Guardar</button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>                                              
                                               
                                          </div>   
                                      <!--  <div class="modal-footer">-->
         
                                        </div>        
                                        </div>

                                      </div>
                                    </div>
                                    </div>


            </fieldset>
            <fieldset class="buttons">
             
            </fieldset>
        </g:form>
  
    </div>
</body>
</html>
