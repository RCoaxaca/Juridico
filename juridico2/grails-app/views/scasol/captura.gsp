<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
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
            var $element = $('#mpo');
            $element.empty();
            $.each(data, function(key, value) {
              var option = $('<option/>').val(data[key].id).text(data[key].descrip);
              $element.append(option)
            });
            $element.removeAttr('disabled');
            if(data.length>0){
                var url=$("#urlLocalidades").attr("url");
                var params={
                    id:$("#mpo").val()
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
                <link href='http://www.registrocivil.oaxaca.gob.mx/imagenes/logomini.jpg' rel='shortcut icon' type='image/x-icon'>

	</head>
	<body>
		<!--<div id="edit-scasol" class="content scaffold-edit" role="main">-->
		<div id="edit-scasol" class="content scaffold-edit">
			<g:if test="${flash.message}">
                                <div class="message" role="status">Numero de solicitud: ${flash.message}</div>
			</g:if>
                        <input id="id" name="id" value="${flash.message}" class="invisible">
			<g:hasErrors bean="${scasolInstance}">
			<ul class="errors" role="alert">
			</ul>
			</g:hasErrors>
                          <div class="container">
                            <div class="panel panel-default">
                               <div class="panel-body">
                                   <div class="col-md-offset-1 col-md-10">
                                       
                                                       <!-- <ul class="nav nav-tabs">
                                                            <li class="active"><a onclick="${remoteFunction(action:'create',params:'\'id=\'+ this.value',update:'err')}">Solicitud</a></li>
                                                            <li><a onclick="${remoteFunction(action:'show',params:'\'id=\'+ this.value',update:'err')}">Errores</a></li>
                                                        </ul> -->
                                                                                                       
                                       <div class="panel panel-default">                                      
                                           <div class="panel-body">  
                                          <g:form action="save">
                                                     <g:render template="nueva"/>
                                          </g:form>   
                                                
                                           </div>
                                       </div>                                                                              
                                    </div>
                                </div>
                            </div>
                        </div>

		</div>
	</body>
</html>