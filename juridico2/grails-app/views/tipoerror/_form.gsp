<%@ page import="catalogos.Tipoerror" %>

<div id="principal">
<table border="1">
    <tr>
    <td>tipo de error</td>
    
    <td>dato a corregir</td></tr>
    <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" 
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er1'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td><div id="er1"></div>
        </td>
       </tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er2'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er2"></div>
        </td>
        </tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er3'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er3"></div>
        </td>
      </tr>
     
     
       <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" 
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er4'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er4"></div>
        </td>
       </tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er5'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er5"></div>
        </td>
      </tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er6'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er6"></div>
        </td>
        </tr>
     
     
     
     
      <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er7'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er7"></div>
        </td>
       </tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er8'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er8"></div>
        </td>
       </tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" 
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er9'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er9"></div>
        </td>
      </tr>
     
     
       <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er10'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er10"></div>
        </td>
      </tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er11'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er11"></div>
        </td>
       </tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id"
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'er12'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
         <td><div id="er12"></div>
        </td>
      </tr>
    
        </table>


</div>
