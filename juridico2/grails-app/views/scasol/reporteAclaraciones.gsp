
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
         <meta name="layout" content="main" >
         
  
       
    </head>
    <body>
        
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-body"> 
                    <div class="panel-heading">
                                <h3 class="text-center">Consulta de Totales</h3>                 
                    </div>

                               <g:form action="totales">
                                    <div class="row">
                                         <div class="col-md-3">
                                       <label>Inicio</label>
                                       <!--<input type="date" name="fechainicio" class="form-control">-->
                                    <div class="form-control"> 
                                   <g:datePicker name="fechainicio" precision="day"  class="form-control" value=""   />
                                         </div>
                                         </div>
                                       <div class="col-md-3">
                                       <label>Fin</label>
                                       <!--<input type="date" name="fechafinal" class="form-control">-->
                                    <div class="form-control"> 
                                       <g:datePicker name="fechafinal" precision="day" value="" />
                                    </div>   
                                       </div>
                                       <div class="col-md-2">
                                           <label> </label>
                                           <button class="form-control btn btn-primary " type="submit">Consultar</button>
                                       </div>    
                                       
                                    
                                    </div>
                               </g:form>    
                     
                </div>
            </div>
        </div>

        
    </body>
</html>

