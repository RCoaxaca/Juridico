<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
	<head>
		<meta name="layout" content="main">
		 
	</head>
		    <div id="list-capturados" class="content scaffold-list" role="main">	
                         
                  <div class="container">
     <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>Capturas del dia</h3>
                     </div>
                     <g:form name="expedientes"  >
                            <div class="panel-body">
                                <div class="form-horizontal col-md-12">
                         			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
					
						<th>Interesado</th>
					
						<th>Número de Expediente</th>                                                
                                              
						<!--<g:sortableColumn property="exapro" title="${message(code: 'opcion.exapro.label', default: 'Exapro')}" />-->
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scaactInstanceList}" status="i" var="scaactInstance">				
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                                 <td>${fieldValue(bean: scaactInstance, field: "nom_intere")} ${fieldValue(bean: scaactInstance, field: "ap1_intere")} ${fieldValue(bean: scaactInstance, field: "ap2_intere")}</td>
                                                 
						 <td>${fieldValue(bean: scaactInstance, field: "expro")} <g:checkBox name="progresivos" value="${scaactInstanceList.expro[i]}" /></td>                                               
                                                
                                          
                                                 </tr>
				</g:each>                                     

                             
				</tbody>
			</table>
                   <!-- <g:select id="usuarioactual" name="val.id" from="${com.testapp.User.list()}" optionKey="id" value="${sec.loggedInUserInfo(field: 'id')}"/>-->
                                                   
		</div>
                <div id="btnImprimeTodo">
                 <sec:ifLoggedIn>
                 <g:actionSubmit action="muestraDocumentos" clas="btn btn-primary" name="imprimirTodo" value="Imprimir Considerando"/>
                 <g:actionSubmit action="buscaPapeleta" clas="btn btn-primary" name="imprimirPapeletas" value="Imprimir Papeleta"/>
                 </sec:ifLoggedIn>  
                </div>  
                  

               <!--<g:remoteLink  onmouseup="${remoteFunction( action:'autoinc')}"> nueva funcion</g:remoteLink>-->
          </div>
         </g:form>  

            </div>
             
        </div>
     </div>
 </div>
    </div>
 </div>
