<%@ page import="tablas.Scaerr" %>
<html>
    
	<body>	
            <head>
            </head>
<div class="form-horizontal" role="form">
    
        <div class="form-group">           
                <label class=" col-md-3 label-control">
                      Tipo de Error:
                </label>
                <div class="col-md-9">
                <g:select id="tcorrect" name="tcorrect.id" from="${catalogos.Tipoerror.findAllByPant(1)}" optionKey="id" required="" 
                 noSelection="['':'']" class="form-control input-sm"
                            onchange="${remoteFunction(
                            controller:'scaerr',  action:'tipoActa',
                            params:'\'id=\'+ nuemr.value+\'&err=\'+ tcorrect.value',
                            update:'campo2')}"  
                            value="${scaerrInstance?.tcorrect?.id}"                           
                            />
                </div>                
        </div>
        <div id="campo2">    
        </div>
        
        <div class="form-group">               
                    <label class="label-control col-md-3">
                            Contiene:
                    </label>
                    <div class="col-md-9">
                        <g:textField name="contiene" value="${scaerrInstance?.contiene}" class="form-control input-sm mayuscula"   onChange="conMayusculas(this)" />                
                    </div> 
        </div>

<div id="muni">

</div>
<div id="mun">

</div>        
<div class="form-group">
	<label for="procede" class="label-control col-md-3">
		Procede la aclaración:	
	</label>        
    <div class="col-md-2">
            <select name="procede" noSelection="['':'']"  onchange="${remoteFunction(controller:'base',action:'buscaBase',params:'\'id=\'+ procede.value+\'&err=\'+ campo.value',update:'baseaclara')}"  class="form-control input-sm" required="">
             <option value=""></option>   
             <option value="NO">NO</option>
             <option value="SI">SI</option>
            </select>                  
    </div>
   <div class="col-md-7"></div>
</div>

<div class="form-group">
    <div id="baseaclara">
        <label for="base" class="label-control col-md-3">
         De acuerdo a:
	</label>
    <div class="col-md-9">
        
        <g:textField name="base" id="base" value="${scaerrInstance?.base}"class="form-control input-sm" required=""  onChange="conMayusculas(this)" />
    </div>
    </div>
</div>

<div class="form-group">
    	<label for="donde" class="label-control col-md-3">
        Se envia a:
	</label>
    <div class="col-md-9">
         <g:select name="donde" from="${catalogos.Terror.list()}" optionKey="id" value="${scaerrInstance.donde}" class="form-control input-sm"  required="" />
    </div>
</div>
<div class="row invisible">
    <div class="col-md-3"> 
     <g:select id="terror" name="terror.id" from="${catalogos.Erroresperado.list()}" optionKey="id"  value="1"/>
    </div>
    <div class="col-md-3"> 
	<g:field name="expano" type="number" value="${new Date().year+1900}" required=""/>

    </div>
    <div class="col-md-3"> 
       
    </div>
    <div class="col-md-3">
         <div id="progresivo" class=""></div>
    </div>

    </div>
</div>    
  
        
	</body>
</html>