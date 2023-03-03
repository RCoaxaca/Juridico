<%@ page import="com.testapp.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
    <label for="username" >
		Nombre de usuario
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}" class="form-control label-control"/>
        
</div>
<label class="label-control" >
		
</label>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
    <label for="password">
		Contraseña
	</label> 
        <input name="password" type="password" required="" value="${userInstance?.password}" class="form-control label-control">
	<!--<g:textField name="password" type="password"required="" value="${userInstance?.password}" class="form-control"/>-->
	       
</div>
<label class="label-control"></label>
<div class="row">
    <div class="col-md-4"> 
        <label for="nombre">
                            Nombre		
        </label> 
            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'nombre', 'error')} ">
                    <g:textField name="nombre" onblur="conMayusculas(this)"  value="${userInstance?.nombre}" class="form-control mayuscula" />
                                       
            </div>    
    </div>
    <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'apellpa', 'error')} ">
                    <label for="apellpa">
                            Apellido Paterno		
                    </label> 
                    <g:textField name="apellpa" onblur="conMayusculas(this)"  value="${userInstance?.apellpa}" class="form-control mayuscula" />
                                       
            </div>    
    </div>
    <div class="col-md-4"> 
            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'apellma', 'error')} ">
                <label for="apellma">
                            Apellido Materno		
                    </label>
                    <g:textField name="apellma" onblur="conMayusculas(this)" value="${userInstance?.apellma}" class="form-control mayuscula"/>
                                        
            </div>    
    </div>
</div>
<div>
 
   
    <div>
                            <br>
                            <legend>
    <label>Permisos de Usuario</label>
    </div>
    <div class="row">
        
     <div class="col-md-2">
        <div class="fieldcontain">
                <label>
                        Administrador

                </label>
                <g:checkBox name="peradmin" value="${permisoadmin}"/>
        </div>    
    </div>
    
     <div class="col-md-2">
        <div class="fieldcontain">
                <label>
                        Archivo
                </label>
                <g:checkBox name="perarchivo" value="${permisoarchivo}" />
        </div>    
    </div>
    
    <div class="col-md-2">
        <div class="fieldcontain">
                <label>
                        Ventanilla

                </label>
                <g:checkBox name="perventanilla" value="${permisoventanilla}"/>
        </div>    
    </div>
    
     <div class="col-md-2">
        <div class="fieldcontain">
                <label>
                        Dictaminador

                </label>
                <g:checkBox name="perdictaminador" value="${permisodictaminador}"/>
        </div>    
    </div>
    
     <div class="col-md-3">
        <div class="fieldcontain">
                <label>
                        Validador
                </label>
                <g:checkBox name="pervalidador"  value="${permisovalidador}"/>
        </div>    
    </div>
    </div>
    
</div>





<div class="invisible">
	<g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />



	<g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />
</div>

  
    
    
    
       <div class="col-md-4">
            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
                    <label for="enabled">
                            Activo

                    </label>
                    <g:checkBox name="enabled" value="${userInstance?.enabled}" />
            </div>    
    </div>
    <div class="col-md-4">
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
                <label for="passwordExpired">
                        Expirar Contraseña

                </label>
                <g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
        </div>    
    </div>
    
    
    
    
    <div class="col-md-4 invisible">
        <div class="fieldcontain ${hasErrors(bean: userRoleInstance, field: 'role', 'error')} required">
                <label for="passwordExpired">
                        Rol de Usuario
                </label>
                <g:select id="role" name="roleid" from="${com.testapp.Role.list()}" optionKey="id" required="" value="${userRoleInstance?.role?.id}"/>
                
        </div>    
    </div>


