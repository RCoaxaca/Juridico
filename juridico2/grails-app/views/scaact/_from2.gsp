<%@ page import="tablas.Scaact" %>
<div class="form-horizontal">


<div class="fieldcontain form-group ${hasErrors(bean: scaactInstance, field: 'datofalta', 'error')} ">
	<label for="datofalta" class="label-control">
		<g:message code="scaact.datofalta.label" default="Dato falta" />
		
	</label>
	 <g:select id="datofalta" name="datofalta.id" from="${catalogos.Campo.list()}" optionKey="id" value="${scaactInstance?.datofalta?.id}" class="many-to-one form-control" noSelection="['null': '']" onchange="${remoteFunction(action:'getMostrar',params:'\'id=\'+ this.value',update:'modal')}"/>
</div>
      

<div id="modal" class="form-group"> 
    <g:if test="${scaactInstance?.pnombre}">
            <g:render template="padre"/>
    </g:if>
    <g:if test="${scaactInstance?.mnom}">
            <g:render template="madre"/>
    </g:if>
    <g:if test="${scaactInstance.numacta}">
            <g:render template="acta"/>
    </g:if>   
</div>



<div class="fieldcontain form-group ${hasErrors(bean: scaactInstance, field: 'donde', 'error')} ">
	<label for="donde"class="label-control">
		<g:message code="scaact.donde.label" default="Se envia a:" />
		
	</label>
	 <g:select id="donde" name="donde.id" from="${catalogos.Terror.list()}" optionKey="id" value="${scaactInstance?.donde?.id}" class="many-to-one form-control" noSelection="['null': '']"/>
</div>

<div class="fieldcontain form-group ${hasErrors(bean: scaactInstance, field: 'procede', 'error')} ">
	<label for="procede"class="label-control">
		<g:message code="scaact.procede.label" default="Procede" />
		
	</label>
	<g:select name="procede" class="form-control" from="${scaactInstance.constraints.procede.inList}" value="${scaactInstance?.procede}" valueMessagePrefix="scaact.procede" noSelection="['': '']"/>
</div>


<div class="fieldcontain form-group ${hasErrors(bean: scaactInstance, field: 'bases', 'error')} ">
	<label for="bases" class="label-control">
		<g:message code="scaact.bases.label" default="Bases" />
		
	</label>
	<g:textField name="bases" onblur="conMayusculas(this)" value="${scaactInstance?.bases}" class="form-control"/>
</div>

<div class="row invisible">
    
   <div class="col-md-3 ">
 </div>
   <div class="col-md-3">
     <div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'exppro', 'error')} required">
	<g:field name="exppro" type="number" value="${scaactInstance.exppro}" required=""/>
    </div>
    </div>
   <div class="col-md-3">   
<!--<div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'tipoerresp', 'error')} required">
	<g:field name="tipoerresp" type="number" value="${scaactInstance?.tipoerresp}" required=""/>
</div>-->
  </div>
    <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: scaactInstance, field: 'expano', 'error')} required">
                <g:field name="expano" type="number" value="${scaactInstance.expano}" required=""/>
            </div>
    </div>

    </div>
</div>





 



