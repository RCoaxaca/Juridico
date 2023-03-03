<%@ page import="catalogos.Venta" %>


<div class="row">
    <div class="col-md-8"></div>
    <div class="col-md-4">
        <h4>Folio: ${ventaInstance?.folioexp}</h4>    
    </div>
</div>

<div class="row">
   <div class="col-md-4">
            <div class="form-group ${hasErrors(bean: ventaInstance, field: 'nomb', 'error')} ">
                 <g:textField name="nomb" value="${ventaInstance?.nomb}" class="form-control input-sm"/>
                   <label for="nomb" class="control-label">
                           <g:message code="venta.nomb.label" default="Nombre:" />
                   </label>
           </div>              
   </div>    
   <div class="col-md-4">
            <div class="form-group ${hasErrors(bean: ventaInstance, field: 'apema', 'error')} ">
                    <g:textField name="apema" value="${ventaInstance?.apema}" class="form-control input-sm"/>
                    <label for="apema" class="control-label">
                            <g:message code="venta.apema.label" default="Apellido Paterno:"/>
                    </label>
            </div>   
   </div>
   <div class="col-md-4">
        <div class="form-group ${hasErrors(bean: ventaInstance, field: 'apepa', 'error')} ">
                 <g:textField name="apepa" value="${ventaInstance?.apepa}"class="form-control input-sm"/>
                 <label for="apepa" class="control-label">
                        <g:message code="venta.apepa.label" default="Apellido Materno:" />
                </label>              
        </div>
   </div>
</div>
<div class="row">
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'folio', 'error')} required">
            <g:field name="folio" type="number" value="${ventaInstance.folio}" class="form-control input-sm"/>
                <label for="folio" class="label-control">
                        <g:message code="venta.folio.label" default="Folio solicitud:" />		
                </label>
            </div>
    </div>
    <div class="col-md-4">            
            <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'estado', 'error')} ">
                   <g:select id="estado" name="estado.id" from="${catalogos.Docesta.list()}" optionKey="id" value="${ventaInstance?.estado?.id}" class="form-control" onchange="mostrar(this)"/>
           </div>
           <label>Estado</label>
    </div>
</div>
<div id="oculto" style='display:none;'>
    <label>Observaciones</label>
    <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'obser', 'error')} ">
               <textarea name="obser" value="${ventaInstance?.obser}"></textarea>
    </div>           
</div>

<div id="boton" style='display:none;'>
     <g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
</div>
<div>
    <div class="row" >
        <div class="col-md-4">

        </div>
        <div class="col-md-3">

        </div>
        <div class="col-md-3">

        </div>
    </div>
    <div class="row invisible">
        <div class="col-md-3">
                <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'dic', 'error')} ">
                        <g:select id="dic" name="dic.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}" class="many-to-one" noSelection="['null': '']"/>
                </div>
        </div>
        <div class="col-md-3">
                <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'cap', 'error')} required">
                        <g:select id="cap" name="cap.id" from="${com.testapp.User.list()}" optionKey="id" value="${ventaInstance?.cap?.id}" class="many-to-one"/>
                </div>
        </div>
        <div class="col-md-6">
            <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'fechasol', 'error')} required">
                <g:datePicker  name="fechasol" precision="day"  value="${ventaInstance?.fechasol}"/>
            </div>
        </div>
    </div>
</div>






