
<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
                <resource:richTextEditor  />
	
	</head>
	<body>
            <div id="todo">
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                </div>
            <div class="col-md-2"> 
        
            <label>Expediente		
            </label>
                <g:textField name="expediente" value="${notaInstance?.expro[0]}" class="form-control"/>       
            </div>
            <div class="col-md-2"> 
        
            <label>Año		
            </label>
                <g:textField name="anio" value="${notaInstance?.expano[0]}" class="form-control"/>
       
            </div>
            </div>
            
		<!--<h1 class="text-center">Nuevo Jefe de Archivo Central</h1>-->
		<!--<div id="create-encargadoArchivo" class="content scaffold-create" role="main">-->
			
			
                        
                        
                            <g:if test="${notaInstanceTotal> 0}">
                            <div class="container">
                          
                        <div class="panel-body">
                            <g:form method="post" >
                                
                                  <!--<richui:richTextEditor name="texto" value="${notaInstance?.nota}" width="100%" />-->
                                  <!--<resource:richTextEditor type="full" name="nota" value="${notaInstance.nota}" width="100%"/>-->
                                <g:if test="${notaInstanceTotal>1}">
                                <div id="opciones">
                                <g:checkBox name="oficialia" value="${oficialia}" onchange="${remoteFunction(action:'verificaResolucionOficialia',update:'todo',params:'\'expediente2=\'+ expediente2.value+\'&anio2=\'+ anio2.value')}"/>Oficialia                                
                                <g:checkBox name="archivo" value="${archivo}" onchange="${remoteFunction(action:'verificaResolucionArchivo',update:'todo',params:'\'expediente2=\'+ expediente2.value+\'&anio2=\'+ anio2.value')}" />Archivo Central  
                                </div>
                                </g:if>
                            <richui:richTextEditor name="nota" value="${notaInstance?.nota[0]}" width="925" height="525" />
                            <fieldset class="buttons">
                                <br>
        <g:actionSubmit class="btn btn-primary" action="update3" value="Guardar" />
        <g:actionSubmit class="btn btn-primary" action="update3Sinespacios" value="Quitar espacios" />        
        <g:if test="${notaInstanceTotal> 0}">
        <g:link action="toPdf" class="btn btn-primary" params="['dato1':notaInstance?.expro[0],'dato2':notaInstance?.expano[0],'dato3':sec.loggedInUserInfo(field: 'id')]" target="_new" ><g:message code="default.button.print.label" default="Generar pdf" /></g:link>
        </g:if>
        <g:else>
            <g:link action="toPdf" class="btn btn-primary" params="['dato1':notaInstance?.expro,'dato2':notaInstance?.expano,'dato3':sec.loggedInUserInfo(field: 'id')]" target="_new" ><g:message code="default.button.print.label" default="Generar pdf" /></g:link>
        
        </g:else>
        
        <g:link controller="scasol" action="vali" params="['id':id]" class="btn btn-primary">Regresar</g:link>
            <g:select id="usuarioactual" name="val.id" from="${com.testapp.User.list()}" class="invisible" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>
      </fieldset>
      <g:textField name="idoriginal" value="${id}" class="form-control invisible"/>  
      <fieldset class="invisible">
        
       <g:textField name="expediente" value="${notaInstance?.expro}" class="form-control"/>
       <g:textField name="anio" value="${notaInstance?.expano}" class="form-control"/>
       <g:textField name="expediente2" value="${notaInstance?.expro[0]}" class="form-control"/>
       <g:textField name="anio2" value="${notaInstance?.expano[0]}" class="form-control"/>       
      </fieldset>
                            </g:form>
                        </div>
                           
                       </div> 
                            </g:if>
                                <g:else>
                 <div class="container">
        <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-offset-2 col-md-8">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                                               
                            </div>
                            <div class="panel-body">                                                    
                                        <div class="alert alert-danger" role="status">¡No se encontraron registros con estos datos!</div>                                                                                     
                           </div>
                        </div>
                    </div>
                </div>
            </div>         
        
    </div>
    
                </g:else> 
                            
		</div>
	</body>
</html>
