
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
                    <br>

    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
            <label for="ordinarias" class="label-control">
                Aclaraciones Ordinarias
            </label>
            <g:if test="${totales.equals()}">
                <g:field name="ordinarias" type="number" value="${0}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
         
            </g:if>
                <g:else>
                <g:field name="ordinarias" type="number" value="${totales.size()-totalesuso.size()}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
         
                </g:else>
            
           
        </div>
    </div>
        <div class="col-md-1"></div>
        <div class="col-md-2">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
            <label for="uso" class="label-control">
                Aclaraciones Por Uso
            </label>
            <g:if test="${totalesuso == null}">
                <g:field name="uso" type="number" value="${0}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
         
            </g:if>
                <g:else>
                <g:field name="uso" type="number" value="${totalesuso.size()}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
         
                </g:else>
                
            
           
        </div>
    </div>
     <div class="col-md-1"></div>
             <div class="col-md-2">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
            <label for="total" class="label-control">
                Total de Aclaraciones
            </label>
         <g:field name="total" type="number" value="${totales.size()}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
            
           
        </div>
    </div>
                     
                </div>
            </div>
        </div>

        
    </body>
</html>
