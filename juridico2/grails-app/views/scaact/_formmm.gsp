<%@ page import="tablas.Scaact" %>

<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'datofalta', 'error')} required">
	<label for="datofalta">
		<g:message code="scaact.datofalta.label" default="Datofalta" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-lg-10">
            <select name="datofalta" name="datofalta.id"  optionKey="id" value="${scaactInstance?.datofalta?.id}" class="many-to-one form-control input-sm" required=""noSelection="['null': '']">
                 <option id="1" value="1" data-toggle="modal" data-target="#myModal1" >DATOS RELATIVOS AL PADRE</option>
                 <option id="2" value="2" data-toggle="modal" data-target="#myModal2">DATOS RELATIVOS A ALA MADRE</option>
                 <option id="3" value="3" data-toggle="modal" data-target="#myModal3">DATOS RELATIVOS AL PADRE Y LA MADRE</option>
                </select> 
    </div>
</div>
<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'donde', 'error')} required">
	<label for="donde">
		<g:message code="scaact.donde.label" default="Donde" />
		<span class="required-indicator">*</span>
	</label>
	
<div >
            <select name="donde" name="donde.id"  optionKey="id" onchange="${remoteFunction(action:'getMostrar',params:'\'id=\'+ this.value',update:'formulario')}" value="${scaactInstance?.donde?.id}" class="many-to-one form-control input-sm" required=""noSelection="['null': '']">
                 <option id="1" value="1" >OFICIALIA</option>
                 <option id="2" value="2" >ARCHIVO CENTRAL</option>
                 <option id="3" value="3" >OFICIALIA Y ARCHIVO CENTRAL</option>
                </select> 
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'procede', 'error')} ">
	<label for="procede">
		<g:message code="scaact.procede.label" default="Procede" />
		
	</label>
	<g:select name="procede" from="${scaactInstance.constraints.procede.inList}" value="${scaactInstance?.procede}" valueMessagePrefix="scaact.procede" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'bases', 'error')} ">
	<label for="bases">
		<g:message code="scaact.bases.label" default="Bases" />
		
	</label>
	<g:textField name="bases" onblur="conMayusculas(this)" value="${scaactInstance?.bases}"/>
</div>





<div class="fieldcontain">
            <label for="dto">
                <g:message code="scasol.dto.label" default="Distrito" />
                <span class="required-indicator">*</span>            
            </label>
                <g:select id="dto" name="dto.id" from="${catalogos.Scadto.list()}" optionKey="id" 
                    noSelection="['':'Selecciona un Distrito']"
                    onchange="${remoteFunction(
                    controller:'scampo',
                    action:'getMunici',
                    params:'\'id=\' + this.value',
                    update:'munici'          
                    )}"
                    value="${scaactInstance?.dto?.id}" class="many-to-one" />
 </div>
 
        <div id="munici">
             <label for="mpo" class="control-label">
                    <g:message code="scasol.mpo.label" default="Municipio" />
                    <span class="required-indicator">*</span>
            </label>
                        <g:if test="${scaactInstance.mpo}">
                            <g:include controller="scampo" action="getMunici" id="${scaactInstance.mpo.id}" />
                          
                        </g:if>                                
         </div>
        
           <div id="localida">
          <label for="loc" class="control-label">
                    <g:message code="scaact.loc.label" default="Localidad" />
                   
            </label>
                    
                        <g:if test="${scaactInstance.loc}">
                            <g:include controller="localidades" action="getlocalis" id="${scaactInstance.mpo.id}" />
                          
                        </g:if>
        </div>  


 
 
 
<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'expano', 'error')} required">
	<label for="expano">
		<g:message code="scaact.expano.label" default="Expano" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="expano" type="number" value="${scaactInstance.expano}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'exppro', 'error')} required">
	<label for="exppro">
		<g:message code="scaact.exppro.label" default="Exppro" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="exppro" type="number" value="${scaactInstance.exppro}" required=""/>
</div>



<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'tipoerresp', 'error')} required">
	<label for="tipoerresp">
		<g:message code="scaact.tipoerresp.label" default="Tipoerresp" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tipoerresp" type="number" value="${scaactInstance.tipoerresp}" required=""/>
</div>

    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">DATOS RELATIVOS AL PADRE</h4>
      </div>
      <div class="modal-body">
          <div id="formulario"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerra</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">guardar</button>
      </div>
    </div>
  </div>
</div>
