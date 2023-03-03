
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
             <g:textField name="expediente" value="${expediente}" class="form-control"/>       
            </div>
            <div class="col-md-2"> 
        
            <label>Año		
            </label>
            <g:textField name="anio" value="${anio}" class="form-control"/>    
       
            </div>
            </div>
            
		<!--<h1 class="text-center">Nuevo Jefe de Archivo Central</h1>-->
		<!--<div id="create-encargadoArchivo" class="content scaffold-create" role="main">-->
			
			
                        
                        
                            
                            <div class="container">
                          
                        <div class="panel-body">
                            <g:form method="post" >
                               <g:textField name="anio2" value="${anio}" class="invisible"/>
                                <g:textField name="expediente2" value="${expediente}" class="invisible"/>       
                                    <richui:richTextEditor name="nota" value="${textonegativo}" width="925" height="525" />
                                
                            
                            <fieldset class="buttons">
                                <br>
        <g:actionSubmit class="btn btn-primary" action="imprimeNegativa" value="Imprimir" target="_new"/>
       
        

            <g:select id="usuarioactual" name="val.id" from="${com.testapp.User.list()}" class="invisible" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>
      </fieldset>
      <fieldset class="invisible">

      </fieldset>
                            </g:form>
                        </div>
                           
                       </div> 
                            

		</div>
	</body>
</html>
