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

<div class="col-md-12 form-horizontal"> 


    
 <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'promov', 'error')} ">
            <label for="promov">
                    
                    <g:message code="scasol.promov.label" default="Promovido por:" class="col-lg-1 control-label" />

            </label>
            <g:textField name="promov" value="${scasolInstance?.promov}" class="form-control input-sm col-sm-7"/>
</div>
<h5><b> En favor de:</b></h5>
 <div class="row"> 
          <div class="col-md-3">
                    <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'nom_intere', 'error')} ">
                            <label for="nom_intere">             
                                    <g:textField name="nom_intere" value="${scasolInstance?.nom_intere}"class="form-control input-sm" required=""/>
                                    <g:message code="scasol.nom_intere.label" default="Nombre" class="col-lg-2 control-label" />
                            </label>
                            
                    </div>
          </div>
          <div class="col-md-4">
                    <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'ap1_intere', 'error')} ">
                            <label for="ap1_intere">
                                     <g:textField name="ap1_intere" value="${scasolInstance?.ap1_intere}" class="form-control input-sm" required=""/>
                                    <g:message code="scasol.ap1_intere.label" default="Apellido paterno" class="col-lg-2 control-label"/>
                            </label>
                           
                    </div>
          </div>
          <div class="col-md-4">
                <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'ap2_intere', 'error')} ">
                        <label for="ap2_intere">
                                <g:textField name="ap2_intere" value="${scasolInstance?.ap2_intere}" class="form-control input-sm" required=""/>
                                <g:message code="scasol.ap2_intere.label" default="Apellido materno" class="col-lg-2 control-label" />

                        </label>   
                </div>
          </div>
          <div class="col-md-1">
                <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'sexintere', 'error')} ">
                        <label for="sexintere">
                             <g:select name="sexintere" from="${scasolInstance.constraints.sexintere.inList}" value="${scasolInstance?.sexintere}" valueMessagePrefix="scasol.sexintere" noSelection="['': '']" class ="form-control input-sm input-mini" required=""/>
                                <g:message code="scasol.sexintere.label" default="Sexo"class="col-lg-1 control-label" />

                        </label>
                       
                </div>
          
          </div>
</div>
<div class="row">
    <div class="col-md-5">
              <div class="row control-group ${hasErrors(bean: scasolInstance, field: 'fchsol', 'error')}">
                <label for="fchsol"class="control-label" >
                        <g:message code="scasol.fchsol.label" default="Fecha de aclaracíon:"/>
                        <span class="required-indicator">*</span>
                        <div class="form-control">
                             <g:datePicker name="fchsol"  precision="day"  value="${scasolInstance?.fchsol}" />
                        </div>
                </label>             
          </div>
    </div>
    <div class="col-md-3">
                    <div class="row control-group ${hasErrors(bean: scasolInstance, field: 'condonado', 'error')} ">
                        <label for="condonado" class="control-label">
                                <g:message code="scasol.condonado.label" default="Pago condonado:"/>
                                <g:select name="condonado"  from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" noSelection="['': '']" class ="form-control input-sm input-mini" required="" onchange="mostrar(this)"/>            
                        </label>                      
                   </div>    
    </div>
    <div class="col-md-3"> 
           <div id="oculto" style='display:none;' >

                <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'ofi_recibido', 'error')}">
                    <label for="ofi_recibido" class="control-label">
                            <g:message code="scasol.ofi_recibido.label" default="Recibo. No. de Autorizacion:" />                  
                    </label>
                    <g:field name="ofi_recibido" type="number" value="${scasolInstance.ofi_recibido}" required="" class="form-control"/>
                </div>                     
        </div>        
    </div>
</div>
  
<div class="row">
<div class="col-md-3">    
    <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'typact', 'error')} required">
        <label for="typact" class="control-label">
                    <g:message code="scasol.typact.label" default="Tipo acta:" />
                    
            </label>
            <g:select id="typact" name="typact.id" from="${catalogos.Tipoactas.list()}" optionKey="id" value="${scasolInstance?.typact?.id}" class="form-control"  required=""/>
      </div>
</div>
            
            
<div class="col-md-1"></div>
    
    <div class="col-md-2">
            <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
            <label for="numact" class="control-label">
                    <g:message code="scasol.numact.label" default="No. de acta:" />
                    <span class="required-indicator">*</span>
                    
            </label>
            <g:field name="numact" type="number" value="${scasolInstance.numact}" required="" class="form-control" />
    </div>
    </div>
   <div class="col-md-1"></div>
   
    <div class="col-md-5">
        <div class="row control-group ${hasErrors(bean: scasolInstance, field: 'fchact', 'error')} required">
            <label for="fchact" class="control-label">
                    <g:message code="scasol.fchact.label" default="Fecha del acta:" />.
                    <span class="required-indicator">*</span>
                    <div class="form-control">
                        <g:datePicker name="fchact" precision="day"  value="${scasolInstance?.fchact}"/>
                    </div>
            </label>
              
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


                                        <div class="form-group ${hasErrors(bean: scasolInstance, field: 'mpo', 'error')} required">
                                                <label for="mpo" class="label-control">
                                                        <g:message code="scasol.mpo.label" default="Municipio" />
                                                        <span class="required-indicator">*</span>
                                                </label>
                                                <g:select id="mpo" name="mpo.id" from="${catalogos.Scampo.list()}" optionKey="id" required="" value="${scasolInstance?.mpo?.id}" class="form-control"/>
                                        </div>
                                        <div class="form-group  ${hasErrors(bean: scasolInstance, field: 'ofi', 'error')} required">
                                                <label for="ofi" class="label-control">
                                                        <g:message code="scasol.ofi.label" default="Oficialias" />
                                                        <span class="required-indicator">*</span>
                                                </label>
                                                <g:select id="ofi" name="ofi.id" from="${catalogos.Scaofi.list()}" optionKey="id" required="" value="${scasolInstance?.ofi?.id}" class="form-control"/>
                                        </div>
                                        <div class="form-group ${hasErrors(bean: scasolInstance, field: 'loc', 'error')} required">
                                                <label for="loc" class="label-control">
                                                        <g:message code="scasol.loc.label" default="Loccalidades" />
                                                        <span class="required-indicator">*</span>
                                                </label>
                                                <g:select id="loc" name="loc.id" from="${catalogos.Localidades.list()}" optionKey="id" required="" value="${scasolInstance?.loc?.id}" class="form-control"/>
                                        </div>
        
                    </div>                  
        <div id="localidadess">
   
        </div>

    <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'anexo', 'error')} ">
            <label for="anexo" class="control-label">
                    <g:message code="scasol.anexo.label" default="Anexo" />

            </label>
            <g:textField name="anexo" value="${scasolInstance?.anexo}" class="form-control"/>
    </div>                          
    
  <div class="row">
   <div class="col-md-2"> 
    <div class="invisible ${hasErrors(bean: scasolInstance, field: 'dic', 'error')} ">
            <label for="dic">
                    <g:message code="scasol.dic.label" default="Dic" />
            </label>
            <g:textField name="dic" value="${scasolInstance?.dic}"/>
    </div>
   </div>
   <div class="col-md-2">  
           <div class="invisible ${hasErrors(bean: scasolInstance, field: 'expano', 'error')} required">
            <label for="expano">
                    <g:message code="scasol.expano.label" default="Expano" />
                    <span class="required-indicator">*</span>
            </label>
            <g:field name="expano" type="number" value="${scasolInstance.expano}" required=""/>
    </div>
   </div>
   <div class="col-md-2"> 
    <div class="invisible ${hasErrors(bean: scasolInstance, field: 'expro', 'error')} required">
            <label for="expro">
                    <g:message code="scasol.expro.label" default="Expro" />
                    <span class="required-indicator">*</span>
            </label>
            <g:field name="expro" type="number" value="${scasolInstance.expro}" required=""/>
    </div>
   </div>
      <div class="col-md-2"> 
    <div class="invisible ${hasErrors(bean: scasolInstance, field: 'fchcam', 'error')} required">
            <label for="fchcam">
                    <g:message code="scasol.fchcam.label" default="Fchcam" />
                    <span class="required-indicator">*</span>
            </label>
            <g:datePicker name="fchcam" precision="day"  value="${scasolInstance?.fchcam}"  />
    </div>
     </div>
    <div class="col-md-2">  
         <div class="invisible ${hasErrors(bean: scasolInstance, field: 'isprint', 'error')} required">
            <label for="isprint">
                    <g:message code="scasol.isprint.label" default="Isprint" />
                    <span class="required-indicator">*</span>
            </label>
            <g:field name="isprint" type="number" value="${scasolInstance.isprint}" required=""/>
    </div>
 </div>
 <div class="col-md-2"> 
    <div class="invisible ${hasErrors(bean: scasolInstance, field: 'proced', 'error')} ">
            <label for="proced">
                    <g:message code="scasol.proced.label" default="Proced" />

            </label>
            <g:checkBox name="proced" value="${scasolInstance?.proced}" />
    </div>
     </div>

  
   
   
   </div>
   
   
    









