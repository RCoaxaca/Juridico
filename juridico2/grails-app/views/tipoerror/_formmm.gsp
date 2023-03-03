<%@ page import="catalogos.Tipoerror" %>


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
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici2'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici2">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici3'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici3">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
     
     
       <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici4'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici4">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici5'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici5">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici6'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici6">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
     
     
     
     
      <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici7'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici7">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici8'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici8">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici9'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici9">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
     
     
       <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici10'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici10">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici11'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici11">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
     <tr>
        <td>
  <div class="fieldcontain">
            <label for="error">
                <g:message code="tipoerror.error.label" default="Tipo de correccion" />
                           
            </label>
                <g:select id="terror" name="terror.id" from="${catalogos.Tipoerror.list()}" optionKey="id" required=""
                    noSelection="['':'']"
                    onchange="${remoteFunction(
                    controller:'erroresperado',
                    action:'getErrores',
                    params:'\'id=\' + this.value',
                    update:'munici12'          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one" />
        </div></td>
        <td>
        <div id="munici12">
        <g:select name="tipoerr" from="${catalogos.Tipoerror.list()}" optionKey="id" noSelection="['':'']" />
        </div></td></tr>
    
    
    
    
    
    
        </table>










