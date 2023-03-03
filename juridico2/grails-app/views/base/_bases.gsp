 <label for="base" class="label-control col-md-3">
         De acuerdo a:
	</label>
    <div class="col-md-9">
        <!--<g:textField name="base" value="${scaerrInstance?.base}"class="form-control input-sm" required=""  onChange="conMayusculas(this)" />-->
         <g:select id="baseacl" name="base" from="${basess}" onblur="conMayusculas(this)" 
             noSelection="['':'']"
                        onchange="${remoteFunction(
                        controller:'base',
                        action:'verBase',
                        params:'\'id=\' + this.value',
                        update:'baseaclara'          
                        )}"     
            value="${scaerrInstance?.base}" class="form-control input-sm" required="" />
    </div>