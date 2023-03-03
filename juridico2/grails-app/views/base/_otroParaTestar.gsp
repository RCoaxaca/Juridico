<div class="form-group">
<div class="col-md-14">
    <label for="debeser" class="col-md-3">
		En virtud de:		
	</label>
        <div class="col-md-9">
        <g:textField name="debeser" value="${scaerrInstance?.debeser}"class="form-control input-sm" required=""  onChange="conMayusculas(this)" />
        </div>
</div>
</div>

<div class="form-group">
    <label for="base" class="col-md-3">
		Con fundamento en:		
	</label>
        <div class="col-md-9">
            <g:textField name="base" id="base" value="${scaerrInstance?.base}"class="form-control input-sm mayuscula" onChange="conMayusculas(this)" />
        </div>
</div>