
<html>
    <head>
         <meta name="layout" content="main" >
         <g:javascript>
            function mostrar(obj){       
                 if (obj.value == 1){
                        document.getElementById('folio1').style.display = 'block';
                        document.getElementById('nombre1').style.display = 'none';
                        document.getElementById('nsolici').style.display = 'none';
                        document.getElementById('fechasol').style.display = 'none';
                        document.getElementById('solicitante').style.display = 'none';

                 }
                 if(obj.value== 2){
                        
                        document.getElementById('nombre1').style.display = 'block';
                        document.getElementById('nsolici').style.display = 'none';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('fechasol').style.display = 'none';
                        document.getElementById('solicitante').style.display = 'none';
                 }
                 if(obj.value == 3){
                        document.getElementById('nsolici').style.display = 'block';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('nombre1').style.display = 'none';
                        document.getElementById('fechasol').style.display = 'none';
                        document.getElementById('solicitante').style.display = 'none';
                        
                 }
                 if(obj.value == 4){
                        document.getElementById('fechasol').style.display = 'block';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('nombre1').style.display = 'none';
                        document.getElementById('nsolici').style.display = 'none';
                        document.getElementById('solicitante').style.display = 'none';
                        
                 }
                  if(obj.value == 5){
                        document.getElementById('solicitante').style.display = 'block';
                        document.getElementById('folio1').style.display = 'none';
                        document.getElementById('nombre1').style.display = 'none';
                        document.getElementById('nsolici').style.display = 'none';
                        document.getElementById('fechasol').style.display = 'none';
                        
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
                               <div class="row">
                                   <div class="col-md-4">
                                       <label class="lable-control">Tipo de busqueda</label><br/>
                                       <div class="checkbox">
                                            <div class="radio">
                                                 <label>    
                                                     <input type="radio" name="valor" value="1" id="folio" onclick="mostrar(this)" />
                                                       Por Número de expediente:
                                                 </label>
                                            </div>
                                            <div class="radio">
                                                <label> 
                                                    <input type="radio" name="valor" value="2" id="nomb" onclick="mostrar(this)"/>
                                                    Por Nombre del interesado:
                                                </label>
                                            </div>
                                                <div class="radio">
                                                <label>
                                                    <input type="radio" name="valor" value="3" id="solicitud" onclick="mostrar(this)"/>
                                                    Por No. de Solicitud:
                                                </label>
                                             </div>
                                             <div class="radio">
                                                <label>
                                                    <input type="radio" name="valor" value="4" id="solicitud" onclick="mostrar(this)"/>
                                                    Por fecha de solicitud:
                                                </label>
                                             </div>
                                             <div class="radio">
                                                <label>
                                                    <input type="radio" name="valor" value="5" id="solicitud" onclick="mostrar(this)"/>
                                                    Por solicitante:
                                                </label>
                                             </div>
                                       </div>
                                       </div>
                                   
                                <div class="col-md-8">
                                                    <br>
                                                    <br>
                                                    <br>
                                                    
                                    <div id="folio1" style='display:none;'>                                                                                
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscar1dic">  
                                                        <div class="col-md-2">
                                                              <label class="label-control"> Numero de expediente:</label>
                                                        </div>
                                                             <div class="col-md-3">
                                                                 <input type="number" class="form-control" name="folio" id="folio" value="1" required="">
                                                             </div>
                                                             
                                                             <div class="col-md-1">
                                                              <label class="label-control"> Año:</label>
                                                        </div>
                                                             <div class="col-md-3">
                                                                 <input type="number" class="form-control" name="anio" id="anio" value="${new Date().year+1900}" required="">
                                                             </div>
                                                          <div class="col-md-2">
                                                              
                                                              <button class="btn btn-primary " type="submit">Buscar</button>
                                                          </div>
                                                     </g:form> 
                                                 </div>
                                                                                  
                                    </div>
                                    
                                    <div id="nombre1" style='display:none;'>
                                         
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscar2dic">  
                                                        <!--<div class="col-md-2">
                                                              <label class="label-control"> Nombre:</label>
                                                        </div>-->
                                                             <div class="col-md-4">
                                                                 <label class="label-control"> Nombre:</label>
                                                                 <input type="text" class="form-control" name="nombre" id="nombresol" onblur="conMayusculas(this)" required="">
                                                             </div>
                                                             <div class="col-md-4">
                                                                 <label class="label-control"> Apellido Paterno:</label>
                                                                 <input type="text" class="form-control" name="ape1" id="nombresol" onblur="conMayusculas(this)" >
                                                             </div>
                                                             <div class="col-md-4">
                                                                 <label class="label-control"> Apellido Materno:</label>
                                                                 <input type="text" class="form-control" name="ape2" id="nombresol" onblur="conMayusculas(this)">
                                                             </div>
                                                          <div class="col-md-1">
                                                               <button class="btn btn-primary " type="submit">Buscar</button>
                                                            </div>
                                                     </g:form> 
                                                 </div>
                                        
                                                
                                    </div>
                                    
                                    <div id="nsolici" style='display:none;' >
                                         
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscar3dic">  
                                                        <div class="col-md-4">
                                                              <label class="label-control">No. solicitud:</label>
                                                        </div>

                                                             <div class="col-md-4">
                                                                 <input type="text" class="form-control" placeholder="123456" name="nombresol" id="nombresol" required="">
                                                                 
                                                             </div>
                                                          <div class="col-md-4">
                                                               <button class="btn btn-primary " type="submit">Buscar</button>
                                                                                                                      </div>
                                                     </g:form> 
                                                 </div>                                                                                        
                                    </div>
                                    
                                    
                                    
                                      <div id="fechasol" style='display:none;' >
                                         
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscarporfecha">  
                                                        <div class="col-md-3">
                                                              <label class="label-control">Fecha de solicitud:</label>
                                                        </div>

                                                             <div class="col-md-7">
                                                                 <g:datePicker name="fechsol" precision="day"  value="${scasolInstance?.fchsol}"/>
                                                             </div>
                                                          <div class="col-md-2">
                                                               <button class="btn btn-primary " type="submit">Buscar</button>
                                                                                                                      </div>
                                                     </g:form> 
                                                 </div>                                                                                        
                                    </div>
                                    
                                    
                                    
                                    <div id="solicitante" style='display:none;' >
                                         
                                                <div class="form-inline">
                                                    <g:form class="form-group" action="buscarpromovio">  
                                                        <div class="col-md-2">
                                                              <label class="label-control">Nombre del Solicitante:</label>
                                                        </div>

                                                             <div class="col-md-8">
                                                                <input type="text" class="form-control" name="nsolicita" onblur="conMayusculas(this)" id="nsolicita" required="">
                                                             </div>
                                                          <div class="col-md-1">
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
                </div>
            </div>
        </div>

        
    </body>
</html>

