  <label for="base" class="label-control col-md-3">
         De acuerdo a:
	</label>
    <div class="col-md-9">
        <g:textField name="base" value="${scaerrInstance?.base}"class="form-control input-sm" required=""  onChange="conMayusculas(this)" />
    </div>
  
  <div id ="agregados" style="display: none" class="col-md-9 invisible">
        <g:textField  name="agregarbase" value="0" />
  </div>