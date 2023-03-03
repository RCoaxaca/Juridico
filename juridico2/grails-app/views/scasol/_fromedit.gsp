<%@ page import="tablas.Scasol" %>
<%@ page import="catalogos.Scamposcaofi" %>
<%@ page import="catalogos.Localidadofi" %>
<%@ page import="catalogos.Localidades" %>
<script>
    window.onload = function() {
   document.getElementById('nom_intere').focus();
};
   </script>
<g:javascript>
        function mostrar(obj){       
        if (obj.value =="SI"){
         document.getElementById('oculto').style.display = 'block';
        }
        else{
          
          document.getElementById('oculto').style.display = 'none';
        }
       
        }
        
        function municipios(data) {
            var $element = $('#mpo');
            $element.empty();
            $.each(data, function(key, value) {
              var option = $('<option/>').val(data[key].id).text(data[key].descrip);
              $element.append(option)
            });
            $element.removeAttr('disabled');
            if(data.length>0){
                var url=$("#urlLocalidades").attr("url");
                var params={
                    id:$("#mpo").val()
                }
                $.post(url,params, function(data){
                    localidades(data);
                })
            }
        }
        
         function localidades(data) {
            var $element;
            if(data.localidades.length>0){
                $element = $('#loc');
                $element.empty();
                $.each(data.localidades, function(key, value) {
                  var option = $('<option/>').val(data.localidades[key].id).text(data.localidades[key].localidad);
                  $element.append(option)
                });

            }
                $element = $('#ofi');
                $element.empty();
                $.each(data.oficialias, function(key, value) {
                  var option = $('<option/>').val(data.oficialias[key].id).text(data.oficialias[key].descrip);
                  $element.append(option)
                });
            
        }
        
</g:javascript>

<div class="row" style="border-width: 1px; border-style: ridge; border-color: #c4ba69; ">
    <center>SOLICITUD DE ACLARACION DE ACTA. EXPEDIENTE No. <strong> ${scasolInstance?.expro}</strong> </center>
  </div>
    <br> 
<div class="row">
    <div class="col-md-12">
        <div class="fieldcontain  ${hasErrors(bean: scasolInstance, field: 'promov', 'error')} required">
                <label for="promov" class="label-control">Promovido por</label>
                <g:textField name="promov" value="${scasolInstance?.nomb} ${scasolInstance?.apepa} ${scasolInstance?.apema}" class="form-control" required=""/>
                
        </div>
    </div>    
</div>
<div class="row"> 
    <div class="col-md-12">
         <label for="afavor" class="label-control">En Favor de</label>
    </div>
</div>
<div class="row"> 
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nom_intere', 'error')} required">
           
            <g:textField name="nom_intere" id="nom_intere" value="${scasolInstance?.nom_intere}" class="form-control input-sm input-mini mayuscula" required="" onChange="conMayusculas(this)"/>
                <label for="nom_intere" class="label-control">
                       Nombre
                </label>
           </div>
    </div>
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap1_intere', 'error')} ">
            <g:textField name="ap1_intere" value="${scasolInstance?.ap1_intere}" class="form-control input-sm input-mini mayuscula" onChange="conMayusculas(this)"/>
            <label for="ap1_intere" class="label-control">
                   Apellido Paterno	
            </label>
        </div>
    </div>
    <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap2_intere', 'error')} ">
                <g:textField name="ap2_intere" value="${scasolInstance?.ap2_intere}" class="form-control input-sm input-mini mayuscula" onChange="conMayusculas(this)"/>
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
<hr width="50%" size="4" align="center">
<div class="row">
    <div class="col-md-4">
                <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchsol', 'error')} ">
                <label for="fchsol" class="label-control">            
                        Fecha de aclaración:
                </label>
                <div class="form-control">                                  
                    <g:datePicker name="fchsol" precision="day"  value="${scasolInstance?.fchsol}" noSelection="['': '']" />
                </div>
               

            </div>
    </div>
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'condonado', 'error')} ">       
            <label for="condonado" class="label-control">
                    Pago condonado		
            </label>     
             <g:select name="condonado" from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" onchange="mostrar(this)"  class="form-control"/>
                        
        </div>
    </div>
    <div class="col-md-3">
        <div id="oculto" style='display:none;'>
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi_recibido', 'error')} required">
             <label for="ofi_recibido" class="label-control">
                        Oficio Recibido
                        <span class="required-indicator">*</span>
              </label>
            <g:field name="ofi_recibido" type="number" value="${scasolInstance.ofi_recibido}" required="" class="form-control"/>
        </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'typact', 'error')}required ">
            <label for="typact" class="label-control">
                    Tipo de acta	
            </label>
            <g:select id="typact" name="typact.id" from="${catalogos.Tipoactas.list()}" optionKey="id" value="${scasolInstance?.typact?.id}"  class="form-control"required=""  />
        </div>
    </div>
    <div class="col-md-1"></div>
    <div class="col-md-2">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
            <label for="numact" class="label-control">
                No. de acta
            </label>
            <g:field name="numact" type="number" value="${scasolInstance.numact}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
        </div>
    </div>
    <div class="col-md-1"></div>   
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchact', 'error')} ">
            <label for="fchact" class="label-control">
                   Fecha de Registro	
            </label>
            <div class="form-control">
                <g:datePicker name="fchact" precision="day"  value="${scasolInstance?.fchact}"  class="form-control"/>
            </div>                
        </div>    
    </div>

</div>
<hr width="50%" size="4" align="center">
<div class="row">
    <div class="col-md-12">
     <div class="fieldcontain">
         <label for="dto" class="label-control">
                Distrito
                <span class="required-indicator">*</span>            
            </label>        
                <g:select id="dto" name="dto.id" from="${catalogos.Scadto.list()}" optionKey="id" required=""
                    noSelection="['':'Selecciona un Distrito']"
                    onchange="${remoteFunction(
                    controller:'scampo',
                    action:'getMunicipio3',
                    onSuccess: 'municipios(data)', 
                    params:'\'id=\' + this.value'
                          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one form-control" />
                        
        </div>    
    </div>
</div>
<div class="row">
    <div class="col-md-12">
            <div id="munici">  
                    <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'mpo', 'error')} ">
                        <label for="mpo">
                                   Municipio
                            </label>  
                            <g:select id="mpo" name="mpo.id" from="" optionKey="id" required=""
                            noSelection="['':'Selecciona un Municipio']"
                             from="${scasolInstance?.dto?.municipios}"
                            onchange="${remoteFunction(
                            controller:'localidades',
                            action:'getLocalidadesMunpio',
                            onSuccess: 'localidades(data)', 
                            params:'\'id=\' + this.value'

                            )}"
                            
                            value="${scasolInstance?.mpo?.id}" class="form-control" />
                                                                               
                    </div>
                    <g:hiddenField name="urlLocalidades" url="${createLink(controller:'localidades',action:'getLocalidadesMunpio')}"/>
                    <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'loc', 'error')} ">
                        <label for="loc">
                                    Localidad
                            </label>
                            <g:select id="loc" name="loc.id" from=""  
                            noSelection="['':'Seleccione una Localidad']" optionKey="id" required=""
                             from="${scasolInstance?.mpo?.localidad}"
                            onchange="${remoteFunction(
                                controller:'localidades',
                                action:'getOficialiaLocalidad',
                                onSuccess: 'localidades(data)', 
                                params:'\'id=\' + this.value'
                            )}"
                            value="${scasolInstance?.loc?.id}" class="form-control" />
                            
                    </div>                
            </div>  
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div id="localidadess">
                <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi', 'error')} ">
                        <label class="label-control">
                                Oficialia
                        </label>    
                         <%
                            def locali=Localidades.get(scasolInstance?.loc?.id)
                            def listOficilia=Localidadofi.findAllByLocalidad(locali)
                            if(!listOficilia){
                                def mp=locali?.mpo
                                listOficilia=Scamposcaofi.findAllByMunicipio(mp)
                                if(!listOficilia)
                                    listOficilia<<mp?.oficialia
                                else
                                    listOficilia=listOficilia?.oficialia
                            }
                            else
                               listOficilia= listOficilia?.oficialia
                        %>
                        <g:select id="ofi" name="ofi.id" from="" optionKey="id" from="${listOficilia?.unique()}"  required="" noSelection="['':'Seleccione una Oficialia']" value="${scasolInstance?.ofi?.id}" class="form-control" />
                                           
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
	<g:select id="dic2" name="dic2.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}" class="many-to-one" />
	<g:select id="val" name="val.id" from="${com.testapp.User.list()}" optionKey="id" value="${scasolInstance?.val?.id}" class="many-to-one" noSelection="['null': '']"/>
	<g:select id="cap" name="cap.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${scasolInstance?.cap?.id}" class="many-to-one"/>
</div>
<div>
     <g:actionSubmit class="btn btn-primary" action="update2" value="Guardar" />
</div>
