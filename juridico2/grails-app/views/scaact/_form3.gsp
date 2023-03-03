<%@ page import="tablas.Scaact" %>


<div class="form-horizontal">
    <div class="form-group">
	<label for="datofalta" class="label-control col-md-3">
		Se omitieron:		
	</label>
        <div class="col-md-9">
	 <g:select id="datofalta" name="datofalta.id" from="${catalogos.Campo.list()}" optionKey="id" required=""  noSelection="['':'']" class="form-control input-sm" value="${scaactInstance?.datofalta?.id}"   onchange="${remoteFunction(action:'getMostrar2',params:'\'id=\'+ this.value',update:'modal')}"/>        
        </div> 
    </div>
<div id="modal"> </div>

<div class="form-group">
	<label for="donde"class="label-control col-md-3">
		Se envia a:		
	</label>
        <div class="col-md-9">
         <g:select id="donde" name="donde.id" from="${catalogos.Terror.list()}" optionKey="id"  required="" noSelection="['':'']"  class="form-control input-sm" value="${scaactInstance?.donde?.id}"  />
        </div>
	
</div>

<div class="form-group">
	<label for="procede"class="label-control col-md-3">
		Procede:		
	</label>
        <div class="col-md-9">
	<g:select name="procede" from="${scaactInstance.constraints.procede.inList}" value="${scaactInstance?.procede}" valueMessagePrefix="scaact.procede" noSelection="['': '']"  class="form-control input-sm" required=""/>
        </div>
</div>
<div class="form-group">
	<label for="bases" class="label-control col-md-3">
		En base a ..		
	</label>
        <div class="col-md-9">
            <g:textField name="bases" onblur="conMayusculas(this)" value="${scaactInstance?.bases}" class="form-control input-sm"/>
        </div>
</div>
<div class="row invisible">
   <div class="col-md-3">
<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'expano', 'error')} required">
	<g:field name="expano" type="number" value="${new Date().year+1900}" required=""/>
</div>
 </div>
   <div class="col-md-3 invisible ">
       <div id="progresivo"></div>
    </div>
   <div class="col-md-3">   

         <g:select id="tipoerresp" name="tipoerresp.id" from="${catalogos.Erroresperado.list()}" optionKey="id"  value="4"/>  
  
  </div>
  
    <div class="col-md-3">
       
    </div>

    </div>
</div>
