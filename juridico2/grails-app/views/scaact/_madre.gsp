
<legend class="text-center">Datos de la Madre </legend>   
 <div class="row">
    <div class="col-md-3">
       
            <g:textField name="mnom" value="${scaactInstance?.mnom}" class ="form-control input-sm mayuscula" onblur="conMayusculas(this)" />
               <label for="mnom">
                       Nombre
               </label>

      
    </div>    
    <div class="col-md-3">
       
                <g:textField name="map1" value="${scaactInstance?.map1}" class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
               <label for="map1">
                       Apellido Paterno

               </label>
               
  
    </div>
    <div class="col-md-3">
 
           
                <g:textField name="map2" value="${scaactInstance?.map2}" class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
                   <label for="map2">
                          Apellido Materno
                   </label>
         
    </div>

    <!--<div class="col-md-1">                       
         <g:field name="medad" type="number" value="${scaactInstance.medad}" class ="form-control input-sm"/>
                
	<label for="medad">
		Edad
	</label>
    </div> -->
                
<div class="col-md-1">
        <div class="row">                                                
            <input type="number" name="medad" value="${scaactInstance.medad}" class="form-control input-sm" onkeypress="return isNumberKey(event)" />
            <label class="col-lg-2">Edad</label>
        </div>                               
</div>
        
    <div class="col-md-2">

	
	<label for="mnac">
                <g:select id="mnac" name="mnac.id" from="${catalogos.Nacionalidad.list()}" noSelection="['':'']" optionKey="id" value="${scaactInstance?.mnac?.id}" class="form-control input-sm"/>    
		Nacionalidad	
	</label>	   



    </div>  
</div>
 
 <div class="row">
    <div class="col-md-3">
            
                    <g:textField name="mab1" value="${scaactInstance?.mab1}"  class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
                   <label for="mab1">
                           Nombre del Abuelo
                   </label>
                   
      
    </div>    
    <div class="col-md-3">
       
        <g:textField name="mab1ap1" value="${scaactInstance?.mab1ap1}" class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
	<label for="mab1ap1">
		Apellido Paterno		
	</label>
	
        
    </div>
    <div class="col-md-3">
       
            <g:textField name="mab1ap2" value="${scaactInstance?.mab1ap2}"  class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
	<label for="mab1ap2">
		Apellido Materno
	</label>
	
        
    </div>  
    <div class="col-md-1">
        
    </div>  
    <div class="col-md-2">

        <div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'mab1nac', 'error')} ">
                <label for="mab1nac">
                        <g:select id="mab1nac" name="mab1nac.id" from="${catalogos.Nacionalidad.list()}" noSelection="['':'']" optionKey="id" value="${scaactInstance?.mab1nac?.id}" class="form-control input-sm"/>
                        Nacionalidad
                </label>
               
                
        </div> 
 
    </div>  
</div>

<div class="row">
    <div class="col-md-3">
        
            <g:textField name="mba2" value="${scaactInstance?.mba2}" class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
	<label for="mba2">
		Nombre Abuela
		
	</label>
	

    </div>    
    <div class="col-md-3">
      
            <g:textField name="mab2ap1" value="${scaactInstance?.mab2ap1}"  class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
	<label for="mab2ap1">
		Apellido Paterno	
	</label>
	
 
    </div>
    <div class="col-md-3">
           
                     <g:textField name="mab2ap2" value="${scaactInstance?.mab2ap2}"  class ="form-control input-sm mayuscula"  onblur="conMayusculas(this)" />
                   <label for="mab2ap2">
                           Apellido Materno

                   </label>
                  
          
    </div>  
    <div class="col-md-1">
        
    </div>  
    <div class="col-md-2">
       
             <g:select id="mab2nac" name="mab2nac.id" from="${catalogos.Nacionalidad.list()}" noSelection="['':'']" optionKey="id" value="${scaactInstance?.mab2nac?.id}"  class ="form-control input-sm" />
               <label for="mab2nac">
                       Nacionalidad

               </label>
              

        
    </div>  
</div>










