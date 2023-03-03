
<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
                <resource:richTextEditor  />
	
	</head>
	<body>
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                </div>
            <div class="col-md-2"> 
        
          
            </div>
            
		<!--<h1 class="text-center">Nuevo Jefe de Archivo Central</h1>-->
		<!--<div id="create-encargadoArchivo" class="content scaffold-create" role="main">-->
			
			
                        
                        
                            <g:if test="${notaInstanceTotal> 0}">
                            <div class="container">
                                
                        <div class="panel-body" id="contenidoFormulario">
                            <g:form method="post" target="_new" >

                                <g:if test="${notaInstanceTotal> 1}">
                                <div id="opciones">
                                <g:checkBox name="oficialia" value="${true}" />Oficialia                                
                                <g:checkBox name="archivo" value="${false}" onchange="${remoteFunction(action:'verificaPapeleta',update:'opciones',params:'\'id=\' + this.value')}" />Archivo Central  
                                </div>
                                </g:if>
                                  <!--<richui:richTextEditor name="texto" value="${notaInstance?.nota}" width="100%" />-->
                                  <!--<resource:richTextEditor type="full" name="nota" value="${notaInstance.nota}" width="100%"/>-->
                                <div id="textonota">
                            <richui:richTextEditor name="nota" value="${notaInstance?.nota}" width="925" height="225" />
                            </div>
                            <fieldset class="buttons">
                                <br>
        <g:actionSubmit class="btn btn-primary" action="reimprimePapeleta"  value="Imprimir Papeleta" />
        
        <!--<g:link action="toPdf" class="btn btn-primary" params="['dato1':notaInstance?.expro,'dato2':notaInstance?.expano]" target="_new" ><g:message code="default.button.print.label" default="Generar pdf" /></g:link>-->
        
      </fieldset>
      <fieldset class="invisible">
       <g:textField name="expediente" value="${notaInstance?.expro}" class="form-control"/>
       <g:textField name="anio" value="${notaInstance?.expano}" class="form-control"/>
        
      </fieldset>
                                      <div class="invisible">
                                <label>Expediente		
            </label>
                <g:textField name="expediente2" value="${notaInstance[0]?.expro}" class="form-control"/>
       
            
            <div class="col-md-2">         
            <label>Año		
            </label>
                <g:textField name="anio2" value="${notaInstance[0]?.expano}" class="form-control"/>       
            </div>
            </div>
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
