<%@ page import="tablas.Scasol" %>
 <label for="nomb"class="label-control" >
                        <g:message code="scasol.nomb.label" default="Promovida Por:"  />
 </label>
<div class="row">
    <div class="col-md-4">
            <div class="${hasErrors(bean: scasolInstance, field: 'nomb', 'error')} ">
                
                   <g:textField name="nomb" value="${scasolInstance?.nomb}" class="form-control mayuscula" required="" onChange="conMayusculas(this)"  />
                    <g:message code="scasol.nomb.label" default="Nombre"  />
       
            </div>
    </div>
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'apepa', 'error')} ">
            <g:textField name="apepa" value="${scasolInstance?.apepa}" class="form-control mayuscula" required="" onChange="conMayusculas(this)" />    
            <g:message code="scasol.apepa.label" default="Apellido paterno"  />
            </div>
           </div>        
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'apema', 'error')} ">
              <g:textField name="apema" value="${scasolInstance?.apema}" class="form-control mayuscula" required=""  onChange="conMayusculas(this)" />
               <g:message code="scasol.apema.label" default="Apellido materno"  />
            </div>
            </div>        
</div>
<div class="row">
    <br>
     <div class="col-md-4">
        <label for="folio" class="label-control">
                        <g:message code="scasol.folio.label" default="Folio"  />		
        </label>
    </div>
</div>
<div class="row">
    
    <div class="col-md-4" id="cambio" >
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'folio', 'error')} required">
            <g:field name="folio" type="number" value="${scasolInstance.folio}" required="" class="form-control" onkeypress="return isNumberKey(event)" />
	
        </div>
    </div>
</div>

<div class="invisible">
<div class="row">
    <div class="col-md-4">
        <g:textField name="folioexp" value="${scasolInstance?.folioexp}"/>
    </div>
    <div class="col-md-4">
        <g:select id="estado" name="estado.id" from="${catalogos.Docesta.list()}" optionKey="id" value="${scasolInstance?.estado?.id}" class="many-to-one" onchange="mostrar(this)"/>
    </div>
    <div class="col-md-4">
        <g:select id="cap" name="cap.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${sec.loggedInUserInfo(field: 'id')}" class="many-to-one"/>
    </div>

</div>
<div class="row">
    <div class="col-md-4">
        <g:datePicker name="fechasol" precision="day"  value="${scasolInstance?.fechasol}"  />
        <g:field name="expro" type="number" value="${new Date().year +1900}" required=""/>
    </div>
    <div class="col-md-4">
       <g:select name="condonado" from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" noSelection="['': '']"/>
    </div>
        <div class="col-md-4">
       <g:select name="sexintere" from="${scasolInstance.constraints.sexintere.inList}" value="${scasolInstance?.sexintere}" valueMessagePrefix="scasol.sexintere" noSelection="['': '']"/>
    </div>
</div>
</div>




	



	




	




	





	