
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
                                <h3 class="text-center">Busqueda de Expediente</h3>                 
                            </div>
                            <div class="panel-body">
                               
                               <div class="col-md-12">
                            <g:form class="form-group" action="modificaNegativa">  
                                                        <div class="col-md-2">
                                                          
                                                                 <label class="label-control"> Año:</label>
                                                                 <input type="number" class="form-control" name="anio" id="folio" value="${new Date().year+1900 }" required="">
                                                            
                                                           
                                                        </div>
                                                        <div class="col-md-3">
                                                                 <label class="label-control"> Numero de Expediente:</label>
                                                                 <input type="number" class="form-control" name="expediente" id="folio" required="">
                                                             
                                                          
                                                               
                                                              
                                                        </div>
                                                        <label class="label-control invisible">Aqui va algo </label>
                                                        <div class="col-md-7">
                                                            <button class="btn btn-primary " type="submit">Buscar</button>
                                                        </div>
                                                     </g:form>
                            </div>
                                
                            </div>
                                   
                        </div>
                    </div>
                </div>
            </div>
        </div>

        
    </body>
</html>

