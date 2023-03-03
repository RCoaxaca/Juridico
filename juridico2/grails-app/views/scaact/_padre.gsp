<div>

 <legend class="text-center">Datos del Padre</legend>   
        <div class="row" >
            <div class="col-md-3">                            
                            <g:textField name="pnombre"  class="form-control input-sm mayuscula" value="${scaactInstance?.pnombre}"  onblur="conMayusculas(this)" />
                            <label for="pnombre">
                                    Nombre
                            </label>                  
            </div>
            <div class="col-md-3">                       
                            <g:textField name="pap1" class="form-control input-sm mayuscula" value="${scaactInstance?.pap1}"  onblur="conMayusculas(this)" />                           
                            <label for="pap1">
                                    Apellido Paterno
                            </label>                   
            </div> 
            <div class="col-md-3">                   
                        <g:textField name="pap2" class="form-control input-sm mayuscula" value="${scaactInstance?.pap2}"  onblur="conMayusculas(this)" />
                            <label for="pap2">
                                   Apellido Materno
                            </label>                                
            </div> 
            <div class="col-md-1">
                <div class="row">                                                
                    <input type="number" name="pedad" value="${scaactInstance.pedad}" class="form-control input-sm" onkeypress="return isNumberKey(event)" />
                    <label class="col-lg-2">Edad</label>
                </div>                               
            </div>
                                       
            <div class="col-md-2">                
                   
                         <g:select id="pnac" name="pnac.id" from="${catalogos.Nacionalidad.list()}" optionKey="id" value="${scaactInstance?.pnac?.id}" class="form-control input-sm"/>
                   <label for="pnac">
                         Nacionalidad
                   </label>
                  
                   
             
               
            </div>   
                  
         </div>  

            
<div class="row">
    <div class="col-md-3">
               
                     <g:textField name="pab1" class="form-control input-sm mayuscula" value="${scaactInstance?.pab1}" onblur="conMayusculas(this)" />
                <label for="pab1">
                        Nombre del Abuelo
                </label>
               
              
    </div>    
    <div class="col-md-3">
                
                    <g:textField name="pab1ap1" class="form-control input-sm mayuscula" value="${scaactInstance?.pab1ap1}" onblur="conMayusculas(this)" />
                <label for="pab1ap1">
                       Apellido Paterno

                </label>
                
      
                    <!--<g:textField name="pab1ap1" class="form-control input-sm mayuscula" value="${scaactInstance?.pab1ap1}" onblur="conMayusculas(this)"/>-->
    </div>
    <div class="col-md-3">
                
                    <g:textField name="pab1ap2"class="form-control input-sm mayuscula"  value="${scaactInstance?.pab1ap2}" onblur="conMayusculas(this)" />
                <label for="pab1ap2">
                      Apellido Materno

                </label>
                
    
    </div>  
    <div class="col-md-1">
                
    </div>  
    <div class="col-md-2">
       
            <label for="pab1nac">
                  <g:select id="pab1nac" name="pab1nac.id" from="${catalogos.Nacionalidad.list()}" optionKey="id" value="${scaactInstance?.pab1nac?.id}" class="form-control input-sm" />         
                    Nacionalidad

            </label>
          
     
        
    </div>  
</div>

<div class="row">
    <div class="col-md-3">                
                     <g:textField name="pab2" value="${scaactInstance?.pab2}"class="form-control input-sm mayuscula" onblur="conMayusculas(this)"  />
                <label for="pab2">
                        Nombre Abuela

                </label>
               
       
    </div>    
    <div class="col-md-3">
              
                    <g:textField name="pab2ap1" value="${scaactInstance?.pab2ap1}"class="form-control input-sm mayuscula" onblur="conMayusculas(this)" />
                <label for="pab2ap1">
                        Apellido Paterno

                </label>
      
    </div>
    <div class="col-md-3">
               
                      <g:textField name="pab2ap2" value="${scaactInstance?.pab2ap2}"class="form-control input-sm mayuscula" onblur="conMayusculas(this)" />
                <label for="pab2ap2">
                        Apellido Materno

                </label>

    </div>  
    <div class="col-md-1">
        
    </div>  
    <div class="col-md-2">

        
	<label for="pab2nac">
             <g:select id="pab2nac" name="pab2nac.id" from="${catalogos.Nacionalidad.list()}" optionKey="id" value="${scaactInstance?.pab2nac?.id}" class="form-control input-sm"/>
		Nacionalidad
		
	</label>

       

    </div>  
</div>




</div>
