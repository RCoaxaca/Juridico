<%@ page contentType="text/html;charset=UTF-8" %>

  <div class="form-group">
        
            
           <label for="debeser" class="label-control col-md-3">
		Debeser		
	</label>
	
	<div class="col-md-9"> 
	<g:select name="debeser" id="debeser" from="${catalogos.Nacionalidad.list()}" optionKey="nombre" required="" class="form-control input-sm"
                  onchange="${remoteFunction(
                    controller:'scaerr',
                    action:'nacionalidad',
                    params:'\'id=\' + this.value',
                    update:'mun'          
                    )}" />
        </div>
                
        </div>