<%@ page import="tablas.Scasol" %>
<g:javascript>
        function mostrar(obj){       
        if (obj.value =="SI"){
         document.getElementById('oculto').style.display = 'block';
        }
        else{
          
          document.getElementById('oculto').style.display = 'none';
        }
       
        }
</g:javascript>
<div class="row">
    <div class="col-md-12">
        <div class="fieldcontain  ${hasErrors(bean: scasolInstance, field: 'promov', 'error')} required">

                <g:textField name="promov" value="${scasolInstance?.nomb} ${scasolInstance?.apepa} ${scasolInstance?.apema}" class="form-control" required=""/>
                <label for="promov" class="label-control">Promovido por</label>
        </div>
    </div>    
</div>
<div class="row"> 
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nom_intere', 'error')} required">
            </div>
            <g:textField name="nom_intere" value="${scasolInstance?.nom_intere}" class="form-control input-sm input-mini mayuscula" required="" onChange="conMayusculas(this)"/>
                <label for="nom_intere" class="label-control">
                       Nombre
                </label>
            
    </div>
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap1_intere', 'error')} ">
            <g:textField name="ap1_intere" value="${scasolInstance?.ap1_intere}" class="form-control input-sm input-mini" onChange="conMayusculas(this)"/>
            <label for="ap1_intere" class="label-control">
                   Apellido Paterno	
            </label>
        </div>
    </div>
    <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap2_intere', 'error')} ">
                <g:textField name="ap2_intere" value="${scasolInstance?.ap2_intere}" class="form-control input-sm input-mini" onChange="conMayusculas(this)"/>
                    <label for="ap2_intere" class="label-control">
                            Apellido Materno
                    </label>
                    
            </div>    
    </div>
          <div class="col-md-1">
                <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'sexintere', 'error')} ">
                        <g:select name="sexintere" from="${scasolInstance.constraints.sexintere.inList}" value="${scasolInstance?.sexintere}" valueMessagePrefix="scasol.sexintere"  class ="form-control input-sm input-mini"/>
                        <label for="sexintere" class="label-control">                           
                                Sexo
                        </label>                       
                </div>          
          </div>
</div>
<div class="row">
    <div class="col-md-4">
                <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchsol', 'error')} ">
                <div class="form-control">                                  
                    <g:datePicker name="fchsol" precision="day"  value="${scasolInstance?.fchsol}" noSelection="['': '']" />
                </div>
                <label for="fchsol" class="label-control">            
                        Fecha de aclaración:
                </label>

            </div>
    </div>
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'condonado', 'error')} ">       
             <g:select name="condonado" from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" onchange="mostrar(this)"  class="form-control"/>
            <label for="condonado" class="label-control">
                    Pago condonado		
            </label>             
        </div>
    </div>
    <div class="col-md-3">
        <div id="oculto" style='display:none;'>
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi_recibido', 'error')} required">
            <g:field name="ofi_recibido" type="number" value="${scasolInstance.ofi_recibido}" required="" class="form-control"/>
                <label for="ofi_recibido" class="label-control">
                        Oficio Recibido
                        <span class="required-indicator">*</span>
                </label>
                
        </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'typact', 'error')}required ">
            <g:select id="typact" name="typact.id" from="${catalogos.Tipoactas.list()}" optionKey="id" value="${scasolInstance?.typact?.id}"  class="form-control"required=""  />
            <label for="typact" class="label-control">
                    Tipo de acta	
            </label>
        </div>
    </div>
    <div class="col-md-1"></div>
    <div class="col-md-2">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
         <g:field name="numact" type="number" value="${scasolInstance.numact}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
            <label for="numact" class="label-control">
                No. de acta
            </label>
           
        </div>
    </div>
    <div class="col-md-1"></div>   
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchact', 'error')} ">
            <div class="form-control">
                <g:datePicker name="fchact" precision="day"  value="${scasolInstance?.fchact}"  class="form-control"/>
            </div>                
            <label for="fchact" class="label-control">
                   Fecha de Registro:	
            </label>
        </div>    
    </div>

</div>
<div class="row">
    <div class="col-md-12">
     <div class="fieldcontain">
                <g:select id="dto" name="dto.id" from="${catalogos.Scadto.list()}" optionKey="id" required=""
                    noSelection="['':'Selecciona un Distrito']"
                    onchange="${remoteFunction(
                    controller:'scampo',
                    action:'getMunicipio',
                    params:'\'id=\' + this.value',
                    update:'munici'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one form-control" />
            <label for="dto" class="label-control">
                Distrito
                <span class="required-indicator">*</span>            
            </label>                    
        </div>    
    </div>
</div>
<div class="row">
    <div class="col-md-12">
            <div id="munici">  
                    <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'mpo', 'error')} ">
                            <g:select id="mpo" name="mpo.id" from="${catalogos.Scampo.list()}" optionKey="id" value="${scasolInstance?.mpo?.id}" class="form-control" />
                            <label for="mpo">
                                   Municipio
                            </label>                                                     
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'loc', 'error')} ">
                            <g:select id="loc" name="loc.id" from="${catalogos.Localidades.list()}" optionKey="id" value="${scasolInstance?.loc?.id}" class="form-control" />
                            <label for="loc">
                                    Localidades
                            </label>
                    </div>                
            </div>  
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div id="localidadess">
                <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi', 'error')} ">
                        <g:select id="ofi" name="ofi.id" from="${catalogos.Scaofi.list()}" optionKey="id" value="${scasolInstance?.ofi?.id}" class="form-control" />
                        <label class="label-control">
                                Oficialia
                        </label>                        
                </div>
        </div>    
    </div>
</div>     
<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'anexo', 'error')} ">
   
	<label for="anexo" class="label-control">
		<g:message code="scasol.anexo.label" default="Anexo" />		
	</label>
 <g:textField name="anexo" value="${scasolInstance?.anexo}" class="form-control"/>
</div>

 <div class="invisible">
	<g:field name="expano" type="number" value="${scasolInstance.expano}" required=""/>
	<g:field name="expro" type="number" value="${scasolInstance.expro}" required=""/>
	<g:datePicker name="fchcam" precision="day"  value="${scasolInstance?.fchcam}" default="none" noSelection="['': '']" />
	<g:field name="isprint" type="number" value="${scasolInstance.isprint}" required=""/>
	<g:checkBox name="proced" value="${scasolInstance?.proced}" />
	<g:select id="dic2" name="dic2.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"  />
	<g:select id="val" name="val.id" from="${com.testapp.User.list()}" optionKey="id" value="${scasolInstance?.val?.id}"  noSelection="['null': '']"/>
	<g:select id="cap" name="cap.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${scasolInstance?.cap?.id}" />
</div>
<div>
     <g:actionSubmit class="btn btn-primary" action="update2" value="${message(code: 'default.button.update.label', default: 'Guardar')}" />
</div>