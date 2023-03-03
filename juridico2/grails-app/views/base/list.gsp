
<%@ page import="catalogos.Base" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'base.label', default: 'Base')}" />
		<g:javascript>
        function mostrar(obj){       
        if (obj.value =="SI"){
         document.getElementById('oculto').style.display = 'block';
        }
        else{
          
          document.getElementById('oculto').style.display = 'none';
        }
       
        }
        
        function municipios(data) {
            var $element = $('#CampoList');
            $element.empty();
            $.each(data, function(key, value) {
              var option = $('<option/>').val(data[key].id).text(data[key].nombre);
              $element.append(option)
            });
            $element.removeAttr('disabled');
            if(data.length>0){
                var url=$("#urlLocalidades").attr("url");
                var params={
                    id:$("#CampoList").val()
                }
                $.post(url,params, function(data){
                    localidades(data);
                })
            }
        }
        
         function localidades(data) {
            var $element;
            if(data.localidades.length>0){
                $element = $('#loc');
                $element.empty();
                $.each(data.localidades, function(key, value) {
                  var option = $('<option/>').val(data.localidades[key].id).text(data.localidades[key].localidad);
                  $element.append(option)
                });

            }
                $element = $('#ofi');
                $element.empty();
                $.each(data.oficialias, function(key, value) {
                  var option = $('<option/>').val(data.oficialias[key].id).text(data.oficialias[key].descrip);
                  $element.append(option)
                });
            
        }
        
</g:javascript>
	</head>
	<body>
		<h1 class="text-center">Bases</h1>
<div class="row"> 
  
   
    
    <div class="col-md-2">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nom_intere', 'error')} required">
            </div>
            <label for="nom_intere" class="label-control">
                       Acta
                </label>
            <g:select id="typact" name="typact.id" from="${catalogos.Tipoactas.list()}"             
            noSelection="['':'Seleccione Tipo de Acta']"
                    onchange="${remoteFunction(
                    controller:'fields',
                    action:'buscaFields',
                    onSuccess: 'municipios(data)', 
                    params:'\'id=\' + this.value'                          
                    )}"
    optionKey="id" value="${scasolInstance?.typact?.id}"  class="form-control"required=""  />
                
            
    </div>

    <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap2_intere', 'error')} ">
                 <label for="ap2_intere" class="label-control">
                            Campo
                    </label>
                 <g:select id="CampoList" name="mpo.id" from="" optionKey="id" required=""
                            noSelection="['':'Selecciona un Municipio']"
                             from="${scasolInstance?.dto?.municipios}"
                            onchange="${remoteFunction(
                            controller:'base',
                            action:'getBases',
                            update:'list-base',                            
                            params:'\'id=\' + this.value'

                            )}"
                            
                            value="${scasolInstance?.mpo?.id}" class="form-control" />
                   
                    
            </div>    
    </div>
          
</div>
                <label class="label-control"></label>
		<div id="list-base" class="content scaffold-list" role="main">
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
                                                <th><g:message code="base.acta.label" default="Acta" /></th>
                                                <th><g:message code="base.campo.label" default="Campo" /></th>					
						<g:sortableColumn property="base" title="${message(code: 'base.base.label', default: 'Base')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${baseInstanceList}" status="i" var="baseInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: baseInstance, field: "acta")}</td>
                                                <td>${fieldValue(bean: baseInstance, field: "campo")}</td>
						<td><g:link action="edit" id="${baseInstance.id}">${fieldValue(bean: baseInstance, field: "base")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${baseInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
