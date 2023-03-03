
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="main" >
    </head>
    <body>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-offset-1 col-md-10">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="text-center">Busqueda de solicitud</h3>                 
                            </div>
                            <div class="panel-body">
                               
                                <div class="form-inline">
                                   <form class="form-group">  
                                       <div class="col-md-4">
                                             <label class="label-control"> Numero de folio:</label>
                                       </div>                                  
                                            <div class="col-md-4">
                                                <input type="number" class="form-control" name="folio" id="folio">
                                            </div>
                                         <div class="col-md-4">
                                            <input  type="button" class="btn btn-primary" value="Aceptar" onclick="${remoteFunction(action:'buscardic', params: '\'id=\'+folio.value', update:'datos' )}">
                                         </div>
                                    </form> 
                                </div>
                                
                            </div>
                                        <div id="datos"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        
    </body>
</html>

