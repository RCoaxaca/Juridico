<g:checkBox name="oficialia" value="${true}" onchange="${remoteFunction(action:'verificaPapeleta2',update:'opciones',params:'\'id=\' + this.value')}" />Oficialia                                
<g:checkBox name="archivo" value="${false}" onchange="${remoteFunction(action:'verificaPapeleta',update:'opciones',params:'\'id=\' + this.value')}" />Archivo Central  
           