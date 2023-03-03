<%@ page import="tablas.Scaerr" %>



        <g:if test="${scaerrInstance.contiene=="*"}">
        <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'tcorrect', 'error')} required">
	<!--<label for="tcorrect">
		<g:message code="scaerr.terror.label" default="Tipo de error:" class="label-control"/>
		<span class="required-indicator">*</span>
	</label> -->
            <label>Tipo de Error:</label>
        <br>
                <label class="form-control">TESTAR DE OFICIO</label>
            <!--<g:select id="tcorrect" name="tcorrect.id" from="${catalogos.Tipoerror.findAllByPant(2)}" optionKey="id" required="" 
         noSelection="['':'']" class="form-control" value="${scaerrInstance?.tcorrect?.id}" class="form-control input-sm"/>-->
        </div>

        
        
        <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'tcorrect', 'error')} required">
	<label for="tcorrect">
		<g:message code="scaerr.terror.label" default="Datos Relativos a:" class="label-control"/>
		<span class="required-indicator">*</span>
	</label>
                <g:if test="${scasolInstance.typact.toString() == "MATRIMONIO"}">
                <g:select name="tcorrect.id" from="${catalogos.Tipoerror.findAllByPant(3, [sort: "tipoerror", order: "desc"])}" optionKey="id" required="" noSelection="['':'']" value="${scaerrInstance?.tcorrect?.id}" class="form-control input-sm"/>
                </g:if>
                <g:else>
                    <g:select name="tcorrect.id" from="${catalogos.Tipoerror.findAllByPant(2, [sort: "tipoerror", order: "desc"])}" optionKey="id" required="" noSelection="['':'']" value="${scaerrInstance?.tcorrect?.id}" class="form-control input-sm"/>
                </g:else>
            	
        </div>
        
        <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'procede', 'error')} ">
	<label for="procede">
		<g:message code="scaerr.procede.label" default="Procede:" class="label-control" />		
	</label>
       
            <select name="procede" class="form-control input-sm">
                <g:if test="${scaerrInstance.procede == 'SI'}">
                    <option value="SI">${scaerrInstance?.procede}</option>
             <option value="NO">NO</option>
                </g:if>
                <g:else>
                    <option value="NO">${scaerrInstance?.procede}</option>
             <!--<option value="NO">NO</option>-->
             <option value="SI">SI</option>
                    </g:else>
             
            </select>
        </div>
        
        
        <div id="muni" class="group-control">

        </div>
        <div id="mun" class="form-group ${hasErrors(bean: scaerrInstance, field: 'debeser', 'error')} ">
	<label for="debeser" class="label-control">
		<g:message code="scaerr.debeser.label" default="En Virtud de:" class="form-control mayuscula" />
		
	</label>
	<g:textField name="debeser" onblur="conMayusculas(this)" value="${scaerrInstance?.debeser}" class="form-control mayuscula"/>
        </div>
        
        
        <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'base', 'error')} ">
        <label for="base" class="label-control">
		<g:message code="scaerr.base.label" default="De acuerdo a:" />		
	</label>
	<g:textField name="base" onblur="conMayusculas(this)" value="${scaerrInstance?.base}" class="form-control mayuscula"/>
        </div>
        
        </g:if>
        <g:else>
              <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'tcorrect', 'error')} required">
	<label for="tcorrect">
		<g:message code="scaerr.terror.label" default="Tipo de error:" class="label-control"/>
		<span class="required-indicator">*</span>
	</label>
            	<g:select id="tcorrect" name="tcorrect.id" from="${catalogos.Tipoerror.findAllByPant(1)}" optionKey="id" required="" value="${scaerrInstance?.tcorrect?.id}" noSelection="['':'']" class="form-control input-sm"/>
        </div>
        
        <div class="form-group ${hasErrors(bean: scaerrInstance, field: 'campo', 'error')}">
	<label for="campo" classs="label-control">
		<g:message code="scaerr.campo.label" default="Campo:" />
		<span class="required-indicator">*</span>
                 
	</label>
	<g:select id="campo" name="campo.id" from="${catalogos.Fields.findAllByActaAndSexo(scasolInstance.typact,scasolInstance.sexintere)}" optionKey="id"  
          noSelection="['':'']"   onchange="${remoteFunction(
                    controller:'scaerr',
                    action:'verTipo',
                    params:'\'id=\' + this.value',
                    update:'mun'          
                    )}"       
        value="${scaerrInstance?.campo?.id}" class="form-control" />
</div>


<!--<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'base', 'error')} ">
	<label for="base" class="label-control">
		<g:message code="scaerr.base.label" default="Base" />		
	</label>
	<g:textField name="base" value="${scaerrInstance?.base}" class="form-control"/>
</div>-->

<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'contiene', 'error')} ">
	<label for="contiene">
		<g:message code="scaerr.contiene.label" default="Contiene:" class="label-control"/>
		
	</label>
	<g:textField name="contiene" onblur="conMayusculas(this)" value="${scaerrInstance?.contiene}" class="form-control mayuscula"/>
</div>

<div id="muni" class="group-control">

</div>
<div id="mun" class="form-group ${hasErrors(bean: scaerrInstance, field: 'debeser', 'error')} ">
	<label for="debeser" class="label-control">
		<g:message code="scaerr.debeser.label" default="Lo correcto es:" class="form-control mayuscula" />
		
	</label>
                
                	<g:textField name="debeser" onblur="conMayusculas(this)" onfocus="${remoteFunction(
                            controller:'scaerr',  action:'buscarTipoErr',
                            params:'\'id=\' + campo.value +\'&contiene=\'+ this.value',
                            update:'mun')}" value="${scaerrInstance?.debeser}" class="form-control mayuscula"/>
                

</div>

<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'procede', 'error')} ">
	<label for="procede">
		<g:message code="scaerr.procede.label" default="Procede:" class="label-control" />		
	</label>
       
            <select name="procede" class="form-control input-sm">
                <g:if test="${scaerrInstance.procede == 'SI'}">
                    <option value="SI">${scaerrInstance?.procede}</option>
             <option value="NO">NO</option>
                </g:if>
                <g:else>
                    <option value="NO">${scaerrInstance?.procede}</option>
             <!--<option value="NO">NO</option>-->
             <option value="SI">SI</option>
                    </g:else>
             
            </select>
</div>
<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'base', 'error')} ">
    <label for="base" class="label-control">
		<g:message code="scaerr.base.label" default="De acuerdo a:" />		
	</label>
	<g:textField name="base" id="bases1" onblur="conMayusculas(this)" value="${scaerrInstance?.base}" class="form-control mayuscula"/>
</div>
        </g:else>




<div class="form-group ${hasErrors(bean: scaerrInstance, field: 'donde', 'error')} required ">
	<label for="donde">
		<g:message code="scaerr.donde.label" default="Se envia a:" class="label-control"/>
		<span class="required-indicator">*</span>
	</label>
	<!--<g:field name="donde" type="number" value="${scaerrInstance.donde}" required="" class="form-control"/>-->
        <g:select name="donde" from="${catalogos.Terror.list()}" optionKey="id" value="${scaerrInstance.donde}" class="form-control" noSelection="['null': '']" />
</div>

<div class="row invisible">
   <div class="col-md-3">
       <div class="row ">
                <div class="fieldcontain ${hasErrors(bean: scaerrInstance, field: 'expano', 'error')} required">
                <g:field name="expano" type="number" value="${scaerrInstance.expano}" required=""/>
            </div>
        </div>
    </div>    
   <div class="col-md-3">
       <div class="row">
                <div class="fieldcontain ${hasErrors(bean: scaerrInstance, field: 'expro', 'error')} required">
                <g:field name="expro" type="number" value="${scaerrInstance.expro}" required=""/>
            </div>   
       </div>
   </div>
   <div class="col-md-3">
       <div class="row">
            <div class="fieldcontain ${hasErrors(bean: scaerrInstance, field: 'tcorrect', 'error')} required">
           <!-- <g:select id="tcorrect" name="tcorrect.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required="" value="${scaerrInstance?.tcorrect?.id}" />-->
        </div>              
       </div>
   </div>
</div>












