           <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                </div>
            <div class="col-md-2"> 
        
            <label>Expediente		
            </label>
                <g:textField name="expediente" value="${notaInstance?.expro}" class="form-control"/>       
            </div>
            <div class="col-md-2"> 
        
            <label>Año		
            </label>
                <g:textField name="anio" value="${notaInstance?.expano}" class="form-control"/>
       
            </div>
            </div>
            
		<!--<h1 class="text-center">Nuevo Jefe de Archivo Central</h1>-->
		<!--<div id="create-encargadoArchivo" class="content scaffold-create" role="main">-->
			
			
                        
                        
                            
                            <div class="container">
                          
                        <div class="panel-body">
                            <g:form method="post" >
                                  <!--<richui:richTextEditor name="texto" value="${notaInstance?.nota}" width="100%" />-->
                                  <!--<resource:richTextEditor type="full" name="nota" value="${notaInstance.nota}" width="100%"/>-->
                                
                                <div id="opciones">
                                <g:checkBox name="oficialia" value="${false}" onchange="${remoteFunction(action:'verificaResolucionOficialia',update:'todo',params:'\'expediente2=\'+ expediente2.value+\'&anio2=\'+ anio2.value')}"/>Oficialia                                
                                <g:checkBox name="archivo" value="${true}" onchange="${remoteFunction(action:'verificaResolucionArchivo',update:'todo',params:'\'id=\' + this.expediente2=\'+ expediente2.value+\'&anio2=\'+ anio2.value')}" />Archivo Central  
                                </div>
                                
                            <richui:richTextEditor name="nota" value="${notaInstance?.nota}" width="925" height="525" />
                            <fieldset class="buttons">
                                <br>
        <g:actionSubmit class="btn btn-primary" action="updatePapeletaArchivo" value="Guardar" />
        <g:actionSubmit class="btn btn-primary" action="update3SinespaciosArc" value="Quitar espacios" />
        <g:link action="toPdfArchivo" class="btn btn-primary" params="['dato1':notaInstance?.expro,'dato2':notaInstance?.expano,'dato3':sec.loggedInUserInfo(field: 'id')]" target="_new" ><g:message code="default.button.print.label" default="Generar pdf" /></g:link>
        <g:select id="usuarioactual" name="val.id" from="${com.testapp.User.list()}" class="invisible" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>
      </fieldset>
      <fieldset class="invisible">
       <g:textField name="expediente" value="${notaInstance?.expro}" class="form-control"/>
       <g:textField name="anio" value="${notaInstance?.expano}" class="form-control"/>
       <g:textField name="expediente2" value="${notaInstance?.expro}" class="form-control"/>
       <g:textField name="anio2" value="${notaInstance?.expano}" class="form-control"/> 
      </fieldset>
                            </g:form>
                        </div>
                           
                       </div> 
                         

                            