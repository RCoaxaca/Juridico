
  <g:if test="${ofi}">
               <label for="ofi" class="label-control">
                Oficialia
            </label> 
                <g:select id="ofi" name="ofi.id" from="${ofi}" optionKey="id" required=""
                    
                    value="${scasolInstance?.ofi?.id}" class="form-control" />
                   
                            
            </g:if>
                <g:else>
                    No existen oficialias para esta localidad
                </g:else>
 
 