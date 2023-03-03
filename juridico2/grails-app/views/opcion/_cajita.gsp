            <g:if test="${scasolInstance}">
 
            <g:link controller="scasol" action="editval" id="${scasolInstance.id}" class="btn btn-default">Editar solicitud..</g:link>
                    
            </g:if>
                <g:else>
           
                    No existen la solicitud favor de ingresar de nuevo el numero 
                </g:else>
                