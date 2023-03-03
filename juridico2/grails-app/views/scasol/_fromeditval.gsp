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

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'promov', 'error')} ">
    
	<label for="promov" class="label-control">
		<g:message code="scasol.promov.label" default="Promovido por:" />
		
	</label>
        <g:textField name="promov" value="${scasolInstance?.nomb} ${scasolInstance?.apepa} ${scasolInstance?.apema}" class="form-control"/>
</div>
<div class="row"> 
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nom_intere', 'error')} ">
            
                <label for="nom_intere" class="label-control">
                        <g:message code="scasol.nom_intere.label" default="Nombre" />
                </label>
            </div>
            <g:textField name="nom_intere" value="${scasolInstance?.nom_intere}" class="form-control input-sm input-mini"/>
    </div>
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap1_intere', 'error')} ">
        
            <label for="ap1_intere" class="label-control">
                    <g:message code="scasol.ap1_intere.label" default="Apellido Paterno" />	
            </label>
            <g:textField name="ap1_intere" value="${scasolInstance?.ap1_intere}" class="form-control input-sm input-mini"/>
        </div>
    </div>
    <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap2_intere', 'error')} ">
                
                    <label for="ap2_intere" class="label-control">
                            <g:message code="scasol.ap2_intere.label" default="Apellido Materno" />

                    </label>
                    <g:textField name="ap2_intere" value="${scasolInstance?.ap2_intere}"class="form-control input-sm input-mini"/>
            </div>    
    </div>
          <div class="col-md-1">
                <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'sexintere', 'error')} ">
                        <label for="sexintere">
                             
                                <g:message code="scasol.sexintere.label" default="Sexo"class="col-lg-1 control-label" />
                                <g:select name="sexintere" from="${scasolInstance.constraints.sexintere.inList}" value="${scasolInstance?.sexintere}" valueMessagePrefix="scasol.sexintere"  class ="form-control input-sm input-mini" required=""/>
                        </label>
                       
                </div>
          
          </div>
</div>
<div class="row">
    <div class="col-md-4">
                <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchsol', 'error')} ">

                <label for="fchsol" class="label-control">            
                        <g:message code="scasol.fchsol.label" default="fecha de aclaracion:" />		
                </label>
                <div class="form-control">                                  
                 <g:datePicker name="fchsol" precision="day"  value="${scasolInstance?.fchsol}" default="none" noSelection="['': '']" />
                  </div>
            </div>
    </div>
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'condonado', 'error')} ">
       
            <label for="condonado" class="label-control">
                    <g:message code="scasol.condonado.label" default="Pago condonado:" />		
            </label>
             <g:select name="condonado" from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" noSelection="['': '']" onchange="mostrar(this)"  class="form-control"/>
        </div>
    </div>
    <div class="col-md-3">
        <div id="oculto" style='display:none;'>
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi_recibido', 'error')} required">
            
                <label for="ofi_recibido" class="label-control">
                        <g:message code="scasol.ofi_recibido.label" default="Ofirecibido" />
                        <span class="required-indicator">*</span>
                </label>
                <g:field name="ofi_recibido" type="number" value="${scasolInstance.ofi_recibido}" required="" class="form-control"/>
        </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'typact', 'error')} ">
        
            <label for="typact" class="label-control">
                    <g:message code="scasol.typact.label" default="Tipo de acta:" />		
            </label>
            <g:select id="typact" name="typact.id" from="${catalogos.Tipoactas.list()}" optionKey="id" value="${scasolInstance?.typact?.id}" class="many-to-one" noSelection="['null': '']" class="form-control"/>
        </div>
    </div>
    <div class="col-md-1"></div>
    <div class="col-md-2">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
        
            <label for="numact" class="label-control">
                    <g:message code="scasol.numact.label" default="No. de acta:" />
                    <span class="required-indicator">*</span>
            </label>
            <g:field name="numact" type="number" value="${scasolInstance.numact}" required="" class="form-control"/>
        </div>
    </div>
    <div class="col-md-1"></div>   
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchact', 'error')} ">

                <label for="fchact" class="label-control">
                        <g:message code="scasol.fchact.label" default="Fecha del acta:" />		
                </label>
            <div class="form-control">
            <g:datePicker name="fchact" precision="day"  value="${scasolInstance?.fchact}" default="none" noSelection="['': '']" class="form-control"/>
            </div>                
            
        </div>    
    </div>

</div>

     <div class="fieldcontain form-group">
            <label for="dto" class="label-control">
                <g:message code="scasol.dto.label" default="Distrito" />
                <span class="required-indicator">*</span>            
            </label>
                <g:select id="dto" name="dto.id" from="${catalogos.Scadto.list()}" optionKey="id" required=""
                    noSelection="['':'Selecciona un Distrito']"
                    onchange="${remoteFunction(
                    controller:'scampo',
                    action:'getMunicipio',
                    params:'\'id=\' + this.value',
                    update:'munici'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one form-control" />
        </div>
    
        <div id="munici">
             <label for="mpo" class="control-label">
                    <g:message code="scasol.mpo.label" default="Municipio" />
                    <span class="required-indicator">*</span>
            </label>
                        <g:if test="${scasolInstance.mpo}">
                            <g:include controller="scampo" action="getMunicipio" id="${scasolInstance.mpo.id}" />                         
                        </g:if>                  
                    </div>                  
        <div id="localidadess">
          <label for="loc" class="control-label">
                    <g:message code="scasol.loc.label" default="Localidad" />
                    <span class="required-indicator">*</span>
            </label>
        </div>
<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'anexo', 'error')} ">
   
	<label for="anexo" class="label-control">
		<g:message code="scasol.anexo.label" default="Anexo" />		
	</label>
 <g:textField name="anexo" value="${scasolInstance?.anexo}" class="form-control"/>
</div>

<g:field name="isprint" type="number" value="1" />

 <div class="invisible">
	<g:field name="expano" type="number" value="${scasolInstance.expano}" required=""/>
	<g:field name="expro" type="number" value="${scasolInstance.expro}" required=""/>
	<g:datePicker name="fchcam" precision="day"  value="${scasolInstance?.fchcam}" default="none" noSelection="['': '']" />	
	<g:checkBox name="proced" value="${scasolInstance?.proced}" />
	<g:select id="val" name="val.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}" class="many-to-one" noSelection="['null': '']"/>

</div>
<div>
     <g:actionSubmit class="btn btn-primary" action="update2" value="Continuar" />
</div>