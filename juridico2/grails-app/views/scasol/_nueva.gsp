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
    function most(obj){       
        if (obj.value == 1){
         document.getElementById('ocul').style.display = 'none';
         document.getElementById('bot').style.display = 'none';
        }
        if (obj.value == 2 ){
          
          alert("dudas..");
         <!-- document.getElementById('oculto').style.display = 'none';-->
        }
        if (obj.value == 3){
            document.getElementById('bot').style.display = 'block';
            document.getElementById('ocul').style.display = 'none';
        }
        if(obj.value == 4){
            document.getElementById('bot').style.display = 'none';
            document.getElementById('ocul').style.display = 'block';
        }
        else{
        document.getElementById('ocul').style.display = 'none';
         document.getElementById('obot').style.display = 'none';
        }
}
      
</g:javascript>
<div class="row">
    <div class="col-md-12">
        <div class="fieldcontain  ${hasErrors(bean: scasolInstance, field: 'promov', 'error')} required">
                <label for="promov" class="label-control">Promovido por</label>
                <g:textField name="promov" value="${scasolInstance?.promov}" class="form-control mayuscula"  onblur="conMayusculas(this)" required=""/>
               
        </div>
    </div>    
</div>
<div class="row"> 
  
   
    <label class="col-md-12">En favor de:</label>
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nom_intere', 'error')} required">
            </div>
            <label for="nom_intere" class="label-control">
                       Nombre
                </label>
            <g:textField name="nom_intere" value="${scasolInstance?.nom_intere}" class="form-control input-sm input-mini mayuscula" onblur="conMayusculas(this)" required="" />
                
            
    </div>
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap1_intere', 'error')} ">
            <label for="ap1_intere" class="label-control">
                   Apellido Paterno	
            </label>
            <g:textField name="ap1_intere" value="${scasolInstance?.ap1_intere}" class="form-control input-sm input-mini mayuscula"onblur="conMayusculas(this)" />
            
        </div>
    </div>
    <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap2_intere', 'error')} ">
                 <label for="ap2_intere" class="label-control">
                            Apellido Materno
                    </label>
                <g:textField name="ap2_intere" value="${scasolInstance?.ap2_intere}" class="form-control input-sm input-mini mayuscula" onblur="conMayusculas(this)"/>
                   
                    
            </div>    
    </div>
          <div class="col-md-1">
                <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'sexintere', 'error')} ">
                    <label for="sexintere" class="label-control">                           
                                Sexo
                        </label>   
                        <g:select name="sexintere" from="${scasolInstance.constraints.sexintere.inList}" value="${scasolInstance?.sexintere}" valueMessagePrefix="scasol.sexintere"  class ="form-control input-sm input-mini"/>
                                            
                </div>          
          </div>
</div>
<div class="row">
    <div class="col-md-4">
                <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchsol', 'error')} ">
                    <label for="fchsol" class="label-control">            
                        Fecha de aclaración:
                </label>
                <div class="form-control">                                  
                    <g:datePicker name="fchsol" precision="minute"  value="${scasolInstance?.fchsol}" years="${new Date().year+1900}"  />
                </div>
                

            </div>
    </div>
    <div class="col-md-3">
        <div class="row form-group ${hasErrors(bean: scasolInstance, field: 'condonado', 'error')} ">  
             <label for="condonado" class="label-control">
                    Pago condonado		
            </label>
             <g:select name="condonado" from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" onchange="mostrar(this)"  class="form-control"/>
                        
        </div>
    </div>
    <div class="col-md-3">
        <div id="oculto" style='display:none;'>
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi_recibido', 'error')} required">
            <label for="ofi_recibido" class="label-control">
                        Oficio Recibido
                        <span class="required-indicator">*</span>
                </label>
            <g:field name="ofi_recibido" type="number" value="${scasolInstance.ofi_recibido}" required="" class="form-control"/>
        </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'typact', 'error')}required ">
            <label for="typact" class="label-control">
                    Tipo de acta	
            </label>
            <g:select id="typact" name="typact.id" from="${catalogos.Tipoactas.list()}" optionKey="id" value="${scasolInstance?.typact?.id}"  class="form-control"required=""  />
            
        </div>
    </div>
    <div class="col-md-1"></div>
    <div class="col-md-2">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
            <label for="numact" class="label-control">
                No. de acta
            </label>
         <g:field name="numact" type="number" value="${scasolInstance.numact}" required="" class="form-control"  onkeypress="return isNumberKey(event)"/>
            
           
        </div>
    </div>
    <div class="col-md-1"></div>   
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchact', 'error')} ">
            <label for="fchact" class="label-control">
                   Fecha de Registro	
            </label>
            <div >
               <!--<calendar:datePicker name="fchact" defaultValue="${new Date()}"/> -->
                <!--<richui:dateChooser name="birthday" format="dd.MM.yyyy" />-->
               <!--<g:datePicker name="fchact" precision="day"  value="${scasolInstance?.fchact}" years="${1910..new Date().year+1900}" class="form-control"/>-->
               <!--<g:textField name="fchact" type="date" value="${scasolInstance?.fchact}" years="${1910..new Date().year+1900}"/>-->
                <input type="date" name="fchact" class="form-control" required="">
                <!--   <richui:dateChooser name="fchact" value="${scasolInstance?.fchact}" years="${1910..new Date().year+1900}" class="form-control" format="dd.MM.yyyy" />-->
            </div>                
            
        </div>    
    </div>

</div>
<div class="row">
    <div class="col-md-12">
     <div class="fieldcontain">
         <label for="dto" class="label-control">
                Distrito
                <span class="required-indicator">*</span>            
            </label> 
                <g:select id="dto" name="dto.id" from="${catalogos.Scadto.list()}" optionKey="id" required=""
                    noSelection="['':'Selecciona un Distrito']"
                    onchange="${remoteFunction(
                    controller:'scampo',
                    action:'getMunicipio3',
                    onSuccess: 'municipios(data)', 
                    params:'\'id=\' + this.value'
                          
                    )}"
                    onblur="${remoteFunction(
                    controller:'scampo',
                    action:'getMunicipio3',
                    onSuccess: 'municipios(data)', 
                    params:'\'id=\' + this.value'
                          
                    )}"
                    value="${scasolInstance?.dto?.id}" class="many-to-one form-control" />
                               
        </div>    
    </div>
</div>
<div class="row">
    <div class="col-md-12">
            <div id="munici">  
                    <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'mpo', 'error')} ">
                        <label for="mpo">
                                   Municipio
                            </label>  
                            <g:select id="mpo" name="mpo.id" from="" optionKey="id" required=""
                            noSelection="['':'Selecciona un Municipio']"
                             from="${scasolInstance?.dto?.municipios}"
                              onchange="${remoteFunction(
                            controller:'localidades',
                            action:'getLocalidadesMunpio',
                            onSuccess: 'localidades(data)', 
                            params:'\'id=\' + this.value'

                            )}"
                             onblur="${remoteFunction(
                            controller:'localidades',
                            action:'getLocalidadesMunpio',
                            onSuccess: 'localidades(data)', 
                            params:'\'id=\' + this.value'

                            )}"
                            
                            value="${scasolInstance?.mpo?.id}" class="form-control" />
                                                                               
                    </div>
                    <g:hiddenField name="urlLocalidades" url="${createLink(controller:'localidades',action:'getLocalidadesMunpio')}"/>
                    <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'loc', 'error')} ">
                        <label for="loc">
                                    Localidad
                            </label>
                            <g:select id="loc" name="loc.id" from=""  
                            noSelection="['':'Seleccione una Localidad']" optionKey="id" required=""
                             from="${scasolInstance?.mpo?.localidad}"
                            onchange="${remoteFunction(
                                controller:'localidades',
                                action:'getOficialiaLocalidad',
                                onSuccess: 'localidades(data)', 
                                params:'\'id=\' + this.value'
                            )}"
                            onblur="${remoteFunction(
                                controller:'localidades',
                                action:'getOficialiaLocalidad',
                                onSuccess: 'localidades(data)', 
                                params:'\'id=\' + this.value'
                            )}"
                            value="${scasolInstance?.loc?.id}" class="form-control" />
                            
                    </div>                
            </div>  
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div id="localidadess">
                <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi', 'error')} ">
                        <label class="label-control">
                                Oficialia
                        </label>    
                        <% 
                           def listOficilia =(scasolInstance?.mpo?.locofi?.oficialia)
                           if(listOficilia)
                             listOficilia=listOficilia+scasolInstance?.mpo?.munici?.oficialia
                           else
                              listOficilia=scasolInstance?.mpo?.munici?.oficialia
                        %>
                        <g:select id="ofi" name="ofi.id" from="" optionKey="id" from="${listOficilia?.unique()}"  required="" noSelection="['':'Seleccione una Oficialia']" value="${scasolInstance?.ofi?.id}" class="form-control" />
                                           
                </div>
        </div>    
    </div>
</div>   
<div class="row">
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'estado', 'error')} ">
            <label for="estado" class="label-control">
		<g:message code="scasol.Status.label" default="Status" />		
	</label>
        <g:select id="estado" name="estado.id" from="${catalogos.Docesta.findAllByDc(1)}" optionKey="id" value="${scasolInstance?.estado?.id}" class="form-control" onchange="most(this)" noSelection="['': '']" />    
	
        </div>
    </div>
    <div class="col-md-4">
    </div>
    <div class="col-md-4"> 
    </div>
</div>
<br>
<div id="ocul" style='display:none;'>
    <label>Observaciones</label>
    <div class="fieldcontain ${hasErrors(bean: ventaInstance, field: 'obser', 'error')} ">
        <textarea name="obser" onblur="conMayusculas(this)" value="${scasolInstance?.obser}" class="form-control" rows="3"  ></textarea>
        <br>
              <sec:ifLoggedIn>	
<!--<g:submitButton name="create" class="btn btn-primary" value="Guardar" />-->
        <button type="submit" class="btn btn-primary" value="Guardar">
            Guardar
</button>
        
</sec:ifLoggedIn>

        
       
    </div>           
</div>
<div id="bot" style='display:none;'>
    <sec:ifLoggedIn>	
    <!--<g:submitButton name="create" class="btn btn-primary" value="Guardar" />-->
             <button class="btn btn-primary " type="submit">Guardar</button>
    </sec:ifLoggedIn>
</div>

        
 <div class="invisible">

	<g:select id="val" name="val.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>
        <g:select id="dicc" name="dicc.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${sec.loggedInUserInfo(field: 'id')}"/>
        <g:textField name="folioexp" value="${scasolInstance?.folioexp}"/>
        <g:textField name="dic" value="1"/>
        <g:select id="cap" name="cap.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${sec.loggedInUserInfo(field: 'id')}"/>
        <g:datePicker name="fechasol" precision="day"  value="${scasolInstance?.fechasol}"  />
        <g:textField name="nomb" value="${scasolInstance?.nom_intere}" />
        <g:textField name="apepa" value="${scasolInstance?.ap1_intere}" />    
        <g:textField name="apema" value="${scasolInstance?.ap2_intere}"  />
        <g:field name="folio" type="number" value="${scasolInstance.folio}" />      


        
</div>
<div>
</div>