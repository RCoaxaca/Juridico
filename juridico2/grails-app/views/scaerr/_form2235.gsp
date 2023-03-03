<%@ page import="tablas.Scaerr" %>

<div class="form-horizontal">
<div class="form-group">

	<label for="tcorrect" class="col-md-3">
		Datos relativos a:
	</label>
    <div class="col-md-9">
	<g:select id="tcorrect" name="tcorrect.id" from="${catalogos.Tipoerror.findAllByPant(3)}" optionKey="id" required="" 
         noSelection="['':'']" class="form-control"
         onchange="${remoteFunction(
                            controller:'scaerr',  action:'testarOficio',
                            params:'\'campo=\'+ this.value+\'&err=\'+ tcorrect.value',
                            update:'cambiaContenido')}" 
        value="${scaerrInstance?.tcorrect?.id}" class="form-control input-sm"/>
   </div>     
</div>
<div id="campo2"></div>
<div class="form-group">
	<label for="donde" class="col-md-3">
		Se testa en acta en:
	</label>
        <div class="col-md-9">
            <g:select name="donde" from="${catalogos.Terror.list()}" optionKey="id" value="${scaerrInstance.donde}" class="form-control input-sm" noSelection="['null': '']"  required=""  />
        </div>
</div>

<div id="cambiaContenido">
            
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
            <select name="debeser" noSelection="['':'']" onchange="buscaBase()"  class="form-control input-sm" required="">
             <option value=""></option>   
             <option value="POR CARECER DE LOS ELEMENTOS ESCENCIALES PARA TENER VALIDEZ PLENA">
                 POR CARECER DE LOS ELEMENTOS ESCENCIALES PARA TENER VALIDEZ PLENA
             </option>
             <option value="POR SER IMPROCEDENTE">POR SER IMPROCEDENTE</option>
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
    </div>    
      
</div>
