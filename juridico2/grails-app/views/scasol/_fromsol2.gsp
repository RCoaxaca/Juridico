
<%@ page import="tablas.Scasol" %>
<div class="row">
    <div class="col-md-4 col-md-offset-5">
        <label class="text-center">Folio: ${scasolInstance?.folioexp}</label>
    </div>
</div>
  
<div class="row">
    <div class="col-md-4">
        
	<label for="nomb" class="label-control">
		<g:message code="scasol.nomb.label" default="Promovido Por" />		
	</label>
        </div>
    </div>
<div class="row">
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nomb', 'error')} ">
        <g:textField name="nomb" value="${scasolInstance?.nomb}" class="form-control"/>
	<label for="nomb" class="label-control">
		<g:message code="scasol.nomb.label" default="Nombre" />		
	</label>
        </div>
    </div>
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'apepa', 'error')} ">
                 <g:textField name="apepa" value="${scasolInstance?.apepa}" class="form-control"/>
            <label for="apepa" class="label-control">
                    <g:message code="scasol.apepa.label" default="Apellido paterno" />
            </label>
           
            </div>        
    </div>
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'apema', 'error')} ">
                <g:textField name="apema" value="${scasolInstance?.apema}" class="form-control"/>
                <label for="apema" class="label-control">
                        <g:message code="scasol.apema.label" default="Apellido materno"  />

                </label>
            </div>
    </div>
</div>
<hr width="50%" size="4" align="center">
<div class="row">
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'estado', 'error')} ">
            <label for="estado" class="label-control">
		<g:message code="scasol.estado.label" default="Estado" />		
	</label>
        <g:select id="estado" name="estado.id" from="${catalogos.Docesta.findAllByDc(1)}" optionKey="id" value="${scasolInstance?.estado?.id}" class="form-control" onchange="mostrar(this)"/>    
	
        </div>
    </div>
    <div class="col-md-4">
    </div>
    <div class="col-md-4"> 
    </div>
</div>

<div>
    <div class="row invisible"> 
        <div class="col-md-2">
            <g:field name="folio" type="number" value="${scasolInstance.folio}" required=""/>
            <g:field name="expro" type="number" value="${scasolInstance.expro}" required=""/>
        </div>
        <div class="col-md-2">
            <g:select id="dicc" name="dicc.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"   class="many-to-one" noSelection="['null': '']"/>
        </div>
        <div class="col-md-2">
            <g:select id="cap" name="cap.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${scasolInstance?.cap?.id}" class="many-to-one"/>
        </div>
        <div class="col-md-2">
            <g:select name="condonado" from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" noSelection="['': '']"/>
        </div>
        <div class="col-md-2">
            <g:select name="sexintere" from="${scasolInstance.constraints.sexintere.inList}" value="${scasolInstance?.sexintere}" valueMessagePrefix="scasol.sexintere" noSelection="['': '']"/>
        </div>        
    </div>
</div>
   
<div id="oculto" style='display:none;'>
    <label>Observaciones</label>
    <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'obser', 'error')} ">
        <textarea name="obser" value="${scasolInstance?.obser}" class="form-control" rows="3"  ></textarea>
        <g:actionSubmit class="btn btn-primary" action="actualizar" value="${message(code: 'default.button.update.label', default: 'Actualizar')}" />
    </div>           
</div>
<div id="boton" style='display:none;'>
     <g:actionSubmit class="btn btn-primary" action="update" value="Actualizar" />
</div>




