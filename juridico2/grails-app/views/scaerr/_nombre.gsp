<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <g:javascript library='jquery' />
    <r:layoutResources/>
</head>
<script>
    function obtenerDatos(){
    
    document.getElementById("tot").style.display ='none';
    
    }

</script>
<body>
    <div id="tot">
        <div class="col-md-3">
            <label class="label-control">Debe ser: </label>
        </div>
        
            <div id="todo" class="col-md-9">        
                <div class="form-group col-md-4">
                        <g:textField id="nomb" name="nombre"  value="" onblur="conMayusculas(this)" class="form-control mayuscula"/>        
                        <label id="1" class="label-control">Nombre</label>                        
                </div>
                        <div class="col-md-1"></div>
                <div class="form-group col-md-4">
                        <g:textField id="app" name="appat" onblur="conMayusculas(this)" value="" class="form-control mayuscula"/>        
                        <label id="2" class="lable-control">Apellido Paterno</label>                        
                </div>
                    <div class="col-md-1"></div>
                <div class="form-group col-md-4">
                        <g:textField id="apm" name="apmat" onBlur="conMayusculas(this)" onBlur="obtenerDatos(), 
                            ${remoteFunction(
                    controller:'scaerr',
                    action:'getNombre',
                    params:'\'nombre=\' + nombre.value +\'&appat=\'+ appat.value+\'&apmat=\'+ apmat.value',
                    update:'mun'          
                    )}"
                            value="" class="form-control mayuscula"/>             
                        <label id="3" class="label-control">Apellido Materno</label>                        
                </div>         
            </div>
        
   </div>
        <label id="nombress" class="label-control"></label>
        <g:hiddenField  name="debeser" id="debeser" type="hidden" value="${scaerrInstance?.debeser}" class="form-control"/>
</body>
</html>







