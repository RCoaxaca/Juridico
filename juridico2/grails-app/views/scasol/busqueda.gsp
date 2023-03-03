
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
         <meta name="layout" content="main" >
         <g:javascript>
            function mostrar(obj){       
                 if (obj.value == 1){
                        document.getElementById('folio1').style.display = 'block';
                        document.getElementById('nombre1').style.display = 'none';
                        document.getElementById('nsolici').style.display = 'none';
                          document.getElementById('fechaSolicitud1').style.display = 'none';
                        document.getElementById('nombre2').style.display = 'none';
                        document.getElementById('mensaje').style.display = 'none';
                 }
                 if(obj.value== 2){
                        
                        document.getElementById('nombre1').style.display = 'block';
                        document.getElementById('nsolici').style.display = 'none';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('fechaSolicitud1').style.display = 'none';
                        document.getElementById('nombre2').style.display = 'none';
                         document.getElementById('mensaje').style.display = 'none';
                 }
                 if(obj.value == 3){
                        document.getElementById('nsolici').style.display = 'block';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('nombre1').style.display = 'none';
                          document.getElementById('fechaSolicitud1').style.display = 'none';
                        document.getElementById('nombre2').style.display = 'none';
                         document.getElementById('mensaje').style.display = 'none';
                        
                 }
                  if(obj.value == 4){
                        document.getElementById('fechaSolicitud1').style.display = 'block';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('nombre1').style.display = 'none';
                        document.getElementById('nsolici').style.display = 'none';
                        document.getElementById('nombre2').style.display = 'none';
                         document.getElementById('mensaje').style.display = 'none';
                        
                 }
                  if(obj.value == 5){
                        document.getElementById('nombre2').style.display = 'block';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('nombre1').style.display = 'none';
                         document.getElementById('nsolici').style.display = 'none';
                        document.getElementById('fechaSolicitud1').style.display = 'none';
                         document.getElementById('mensaje').style.display = 'none';
                        
                 }
            }     
         </g:javascript>    
       
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
                                <g:if test="${flash.message}">
                                 <div class="alert alert-danger" id="mensaje">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                     <strong>${flash.message}</strong>
                                  </div>
                                </g:if>
                               <div class="row">
                                   <div class="col-md-4">
                                       
                                       <label class="lable-control">Tipo de busqueda</label><br/>
                                       <div class="checkbox">
                                            <div class="radio">
                                                 <label>    
                                                     <input type="radio" name="valor" value="1" id="folio" onclick="mostrar(this)" />
                                                       Por No. Exp.:
                                                 </label>
                                            </div>
                                            <div class="radio">
                                                <label> 
                                                    <input type="radio" name="valor" value="2" id="nomb" onclick="mostrar(this)"/>
                                                    Por Solicitante:
                                                </label>
                                            </div>
                                            
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="valor" value="5" id="interesado" onclick="mostrar(this)"/>
                                                    Por Interesado:
                                                </label>
                                             </div> 
                                              <div class="radio">
                                                <label>
                                                    <input type="radio" name="valor" value="4" id="fecha" onclick="mostrar(this)"/>
                                                    Por Fecha de Solicitud:
                                                </label>
                                             </div>
                                               <div class="radio">
                                                <label>
                                                    <input type="radio" name="valor" value="3" id="solicitud" onclick="mostrar(this)"/>
                                                    Por No. de Solicitud:
                                                </label>
                                             </div>
                                       </div>
                                       </div>
                                   
                                <div class="col-md-6">
                                                    <br>
                                                    <br>
                                                    
                                    <div id="folio1" style='display:none;'>                                                                                
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscar1">  
                                                       <div class="row"> 
                                                        <div class="col-md-3">
                                                              <label class="label-control"> No. Exp.:</label>
                                                        </div>
                                                             <div class="col-md-6">
                                                                 <input type="number" class="form-control" name="folio" id="folio" required="">
                                                             </div>
                                                       </div>  
                                                            <div class=" ">  <br></div>                                                         
                                                           <div class="row">  
                                                             <div class="col-md-3">
                                                                <label class="label-control"> Año:</label>
                                                             </div>
                                                             <div class="col-md-5">
                                                                 <input type="number" class="form-control" name="year" id="year" value="${new Date().year +1900}" required="">
                                                             </div>
                                                           </div> 
                                                           <div class=" ">  <br></div> 
                                                          <div class="col-md-4">
                                                              
                                                              <button class="btn btn-primary " type="submit">Buscar</button>
                                                          </div>
                                                     </g:form> 
                                                 </div>
                                                                                  
                                    </div>
                                    
                                    <div id="nombre1" style='display:none;'>
                                         
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscar2">  
                                                        <div class="row">
                                                        <div class="col-md-4">
                                                              <label class="label-control"> Solicitante:</label>
                                                        </div>
                                                           <div class="col-md-8">
                                                               <input type="text" class="form-control" name="nombresol" id="nombresol" required="">
                                                             </div>
                                                        </div>
                                                        <br>
                                                             <div class="row">
                                                                <div class="col-md-4">
                                                                     <button class="btn btn-primary " type="submit">Buscar</button>
                                                                  </div>
                                                            </div>
                                                     </g:form> 
                                                 </div>
                                    </div>
                                    
                                    <div id="nombre2" style='display:none;'>
                                         
                                                <div class="form-inline col-md-14">
                                                    <g:form class="form-group" action="buscar5">  
                                                        <div class="col-md-12">
                                                         <!--<div class="col-md-4">
                                                              
                                                        </div>-->
                                                           <div class="row col-md-6">
                                                               <label class="label-control"> Nombre(s):</label>
                                                               <input type="text" class="form-control col-md-4" name="nombreint" id="nombreint" required="">                                                                
                                                           </div>
                                                           <div class="row col-md-4">
                                                               <label class="label-control"> Apellido Pa:</label>
                                                               <input type="text" class="form-control col-md-4" name="apepa" id="apepa" >                                                                
                                                           </div>
                                                           <div class="row col-md-4">
                                                               <label class="label-control"> Apellido Ma:</label>
                                                               <input type="text" class="form-control col-md-4" name="apema" id="apema" >                                                                
                                                           </div>
                                                        </div>
                                                        <br>
                                                             <div class="row">
                                                                <div class="col-md-1">
                                                                     <button class="btn btn-primary " type="submit">Buscar</button>
                                                                  </div>
                                                            </div>
                                                     </g:form> 
                                                 </div>
                                        
                                                
                                    </div>
                                    
                                    <div id="fechaSolicitud1" style='display:none;'>
                                         
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscar4">  
                                                        <div class="row">
                                                            
                                                           <div class="col-md-12">
                                                              <label class="label-control"> Fecha de Solicitud:</label>
                                                              <g:datePicker name="fchsol" precision="day"  value="${new Date()}" default="none" noSelection="['': '']" />
                                                             </div>
                                                        </div>
                                                        <br>
                                                             <div class="row">
                                                                <div class="col-md-4">
                                                                     <button class="btn btn-primary " type="submit">Buscar</button>
                                                                  </div>
                                                            </div>
                                                     </g:form> 
                                                 </div>
                                        
                                                
                                    </div>
                                    
                                    <div id="nsolici" style='display:none;' >
                                         
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscar3">  
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                  <label class="label-control">No. solicitud:</label>
                                                            </div>
                                                             <div class="col-md-4">
                                                                 <input type="number" class="form-control" name="nsoli" id="nsoli" required="">
                                                             </div>
                                                        </div>
                                                        <br>
                                                             <div class="row">
                                                                    <div class="col-md-4">
                                                                         <button class="btn btn-primary " type="submit">Buscar</button>
                                                                    </div>
                                                             </div>
                                                     </g:form> 
                                                 </div>                                                                                        
                                    </div>
                                </div>    
                               </div>  
                                
                            </div>
                                   
                        </div>
                    </div>
                </div>
            </div>
        </div>

        
    </body>
</html>

