<!--<div class="form-group">
	<label for="procede" class="col-md-3">
		Procede:
	</label>
        <div class="col-md-9">
            <select name="procede" noSelection="['':'']" class="form-control input-sm" >
             <option value=""></option>
             <option value="SI">SI</option>
             <option value="SI">NO</option>
            </select>
        </div>
</div>


<div id="mun">
    <div class="form-group">
	<label for="debeser" class="col-md-3">
		En virtud de:		
	</label>
        <div class="col-md-9" id="virtud">
           
            <select name="debeser" noSelection="['':'']" onchange="${remoteFunction(controller:'base',action:'buscaBaseAnotacion',params:'\'id=\'+ this.value+\'&err=\'+ campo.value',update:'mun')}"  class="form-control input-sm" required="">
             <option value=""></option>   
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y EL REGISTRADO">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y EL REGISTRADO
             </option>
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITOR Y EL REGISTRADO">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITOR Y EL REGISTRADO
             </option>
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y LA REGISTRADA">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y EL REGISTRADO
             </option>
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITORA Y LA REGISTRADA">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITOR Y EL REGISTRADO
             </option>
             <option value="OTRO">
                 OTRO
             </option>
            </select>       
        </div>
   </div>     
</div>



<div class="form-group">
    <label for="base" class="col-md-3">
		Con fundamento en:		
	</label>
        <div class="col-md-9">
            <g:textField name="base" id="base" value="${scaerrInstance?.base}"class="form-control input-sm mayuscula" onChange="conMayusculas(this)" />
        </div>
</div>


<div class="row invisible">
    <div class="col-md-2 ">           
                    <g:select id="terror" name="terror.id" from="${catalogos.Erroresperado.list()}" optionKey="id"  value="5"/>           
    </div>
    <div class="col-md-2">        
            <g:field name="expano" type="number" value="${new Date().year+1900}"/>
       
    </div>
    <div class="col-md-2"> 
                <div id="progresivo" class="invisible"></div>
    </div>
    <div class="col-md-2">       
                 <g:textField name="contiene" value="*"/>             
    </div>
    <div class="col-md-2">
            <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'campo', 'error')}">
                <g:select id="campo" name="campo.id" from="${catalogos.Fields.list()}" optionKey="id" 
                  noSelection="['':'']"   value=""  />
           </div>
    </div>    
    </div>  -->




<div class="form-group">
	<label for="procede" class="col-md-3">
		Procede:
	</label>
        <div class="col-md-9">
            <select name="procede" noSelection="['':'']" class="form-control input-sm" >
             <option value=""></option>
             <option value="SI">SI</option>
             <option value="SI">NO</option>
            </select>
        </div>
</div>


<div id="mun">
  <div class="form-group">
	<label for="debeser" class="col-md-3">
		En virtud de:		
	</label>
        <div class="col-md-9" id="virtud">
            <!--<g:textField name="debeser" value="${scaerrInstance?.debeser}" class="form-control input-sm" onChange="conMayusculas(this)" />-->
            <select name="debeser" noSelection="['':'']" onchange="${remoteFunction(controller:'base',action:'buscaBaseAnotacion',params:'\'id=\'+ this.value+\'&err=\'+ campo.value',update:'mun')}"  class="form-control input-sm" required="">
             <option value=""></option>   
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO
             </option>
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO
             </option>
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA
             </option>
             <option value="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA">
                 EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA
             </option>
             <option value="OTRO">
                 OTRO
             </option>
            </select>       
        </div>
   </div>     




<div class="form-group">
    <label for="base" class="col-md-3">
		Con fundamento en:		
	</label>
        <div class="col-md-9">
            <g:textField name="base" id="base" value="${scaerrInstance?.base}"class="form-control input-sm mayuscula" onChange="conMayusculas(this)" />
        </div>
</div>
</div>

<div class="row invisible">
    <div class="col-md-2 ">           
                    <g:select id="terror" name="terror.id" from="${catalogos.Erroresperado.list()}" optionKey="id"  value="5"/>           
    </div>
    <div class="col-md-2">        
            <g:field name="expano" type="number" value="${new Date().year+1900}"/>
       
    </div>
    <div class="col-md-2"> 
                <div id="progresivo" class="invisible"></div>
    </div>
    <div class="col-md-2">       
                 <g:textField name="contiene" value="*"/>             
    </div>
    <div class="col-md-2">
            <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'campo', 'error')}">
                <g:select id="campo" name="campo.id" from="${catalogos.Fields.list()}" optionKey="id" 
                  noSelection="['':'']"   value=""  />
           </div>
    </div>    
    </div>    
</div>   