<%@ page import="com.testapp.User" %>
<div class="row">
    <div class="col-md-6">
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
                <g:textField name="username" required="" value="${userInstance?.username}" disabled="disabled" class="form-control"/>
                <label for="username" class="label-control">
                    Nombre de usuario
                </label>        
        </div>
    </div>
    <div class="col-md-6">
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
                <input type="password" name="password" required="" class="form-control"/>
                <label for="password" class="label-control">
                    Nueva contraseña
                </label>        
        </div>
   </div>    
</div>   
<div>
     <legend class="text-center">Responsable de la cuenta:</legend>
</div>

<div class="row">
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'nombre', 'error')} ">
                <g:textField name="nombre" value="${userInstance?.nombre}" disabled="disabled" class="form-control" />
                <label for="nombre">
                        Nombre:		
                </label>        
        </div>        
    </div>
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'apellpa', 'error')} ">
                <g:textField name="apellpa" value="${userInstance?.apellpa}" disabled="disabled" class="form-control" />
                <label for="apellpa">
                        Apellido Paterno

                </label>        
        </div>        
    </div>
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'apellma', 'error')} ">
                <g:textField name="apellma" value="${userInstance?.apellma}" disabled="disabled" class="form-control" />
                <label for="apellma">
                        Apellido Materno		
                </label>        
        </div>        
    </div>
    
</div> 


