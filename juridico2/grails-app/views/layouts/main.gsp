<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
  <head>
     <!--<resource:dateChooser />-->
     <calendar:resources lang="en" theme="tiger"/>
     
      <sec:ifLoggedIn>	
<!--Inicio session:<sec:username></sec:username>-->

</sec:ifLoggedIn>
<!--<session-management invalid-session-url="/login/auth" />-->
<sec:ifAllGranted roles="ROLE_ADMIN">Administrador</sec:ifAllGranted>
<sec:ifAllGranted roles="ROLE_VENTA">Ventanilla</sec:ifAllGranted>
<sec:ifAllGranted roles="ROLE_DICTAMI">Validador</sec:ifAllGranted>
<sec:ifAllGranted roles="ROLE_USER">Dictaminador</sec:ifAllGranted>
<sec:ifAllGranted roles="ROLE_ARCHIVO">Archivo</sec:ifAllGranted>
<sec:ifAllGranted roles="ROLE_ANONYMOUS">Archivo</sec:ifAllGranted>
<sec:ifAllGranted roles="ROLE_CONSULTA">Archivo</sec:ifAllGranted>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Dirección del Registro Civil"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link rel="icon" href="images/icono.png" sizes="32x32">    -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
    <!--<link rel="stylesheet" href="${resource(dir: 'css', file: 'estilos.css')}" type="text/css">-->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'estilos2.css')}" type="text/css">
    <link href='http://www.registrocivil.oaxaca.gob.mx/imagenes/logomini.jpg' rel='shortcut icon' type='image/x-icon'>
    <script src="${resource(dir:'js',file:'jquery-1.10.2.js')}"></script>
    <script src="${resource(dir:'js',file:'bootstrap.js')}"></script>
    <script src="${resource(dir:'js',file:'jquery.js')}"></script>
    <script src="${resource(dir:'js',file:'funciones.js')}"></script>
<script type="text/javascript">
  
  function seleccion(){
    $('#myModal').modal('show');
   
  } 
</script>
    <style type='text/css'>
	#rotator {
		border: 1px solid;
		border-color:#614021;
		padding:0;
		margin: 0px auto;
		overflow: hidden;
		position: relative;
		width: 288px;/*cambio de tamaño de la imagen*/
		height: 80px;/*cambio de tamaño de la imagen*/
	}
	#rotator img {
		border: 0;
		padding: 0;
		width: 100%;
	}
        #slideshow {
		border: 1px solid;
		border-color:#614021;
		padding:0;
		margin: 0px auto;
		overflow: hidden;
		position: relative;
		width: 288px;/*cambio de tamaño de la imagen*/
		height: 80px;/*cambio de tamaño de la imagen*/
}

#slideshow DIV {
    position:absolute;
    top:0;
    left:0;
    z-index:8;
    opacity:0.0;
	width: 288px;/*cambio de tamaño de la imagen*/
	height: 80px;/*cambio de tamaño de la imagen*/

}

#slideshow DIV.active {
    z-index:10;
    opacity:1.0;
}

#slideshow DIV.last-active {
    z-index:9;
}

#slideshow DIV IMG {
	width: 288px;/*cambio de tamaño de la imagen*/
	height: 80px;/*cambio de tamaño de la imagen*/
    display: block;
    border: 0;
 
}

</style>
  <g:layoutHead/>
  <r:layoutResources />

</head>
<body>
 
  <div class="container">  
               <div id="gobiernoOaxaca" title="Gobierno de Oaxaca" class="hidden-xs hidden-sm  col-md-2 col-lg-2"></div>                  
   </div>
 

        <!--<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div>-->
  <div class="container">
    <div class="navbar navbar-default" role="navigation"> 
              <sec:ifAllGranted roles="ROLE_VENTA">
     
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Captura de Solicitud<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <!--<li><a href="${createLink(controller: 'scasol', action: 'create')}">Nueva solicitud</a></li>-->
              <li><a href="${createLink(controller: 'scasol', action: 'listado')}">Solicitudes</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'busqueda')}">Buscar solicitud</a></li>
              <li><a href="${createLink( controller : "scasol", action : "reporte", id : sec.loggedInUserInfo(field: "id") )}">Reporte</a></li>
            </ul>
          </li>                                                       
        </ul>  
      </sec:ifAllGranted>
      
       <sec:ifAllGranted roles="ROLE_DICTAMI">
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Generar<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'scasol', action: 'busquedic')}">Buscar Solicitud</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'listadodic')}">Solicitudes Pendientes</a></li>              
              <li><a href="${createLink(controller: 'scasol', action: 'listadodic2')}">Solicitudes Procedidas</a></li> 
            </ul>
          </li>                                                       
        </ul>
      </sec:ifAllGranted>    
      
      <sec:ifAllGranted roles="ROLE_USER">
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Verificar<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'scasol', action: 'captura')}">Captura de solicitud</a></li>  
              <li><a href="${createLink(controller: 'opcion', action: 'consulta')}">Buscar solicitud</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'valsliata')}">Listado</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'valsliata2')}">Procedidas</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'valsliata3')}">Negativas</a></li>
            </ul>
          </li>                                                       
        </ul>

        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Generar Documentos<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'opcion', action: 'buscaResolucion')}">Modificar Resolucion</a></li>  
              <li><a href="${createLink(controller: 'opcion', action: 'buscaPapeleta')}">Reimprimir Papeleta</a></li>
              <li><a href="${createLink(controller: 'opcion', action: 'buscaResolucionNegativa')}">Modificar Negativa</a></li>
            </ul>
          </li>                                                       
        </ul>  
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Imprimir Documentos<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'nota', action: 'busca' )}">Imprimir Considerandos</a></li>  
              
            </ul>
          </li>                                                       
        </ul> 
         <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reporte de Aclaraciones<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'scasol', action: 'reporteAclaraciones' )}">Consulta de Totales</a></li>  
              
            </ul>
          </li>                                                       
        </ul> 
        
      </sec:ifAllGranted>
      
      <sec:ifAllGranted roles="ROLE_CONSULTA">
          <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reporte de Aclaraciones<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'scasol', action: 'reporteAclaraciones' )}">Consulta de Totales</a></li>  
              
            </ul>
          </li>                                                       
        </ul> 
      </sec:ifAllGranted>
      
       <sec:ifAllGranted roles="ROLE_ARCHIVO">
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'resguardo', action: 'create')}">Agregar Archivo</a></li>  
              <li><a href="${createLink(controller: 'movimiento', action: 'create')}">Prestar Archivo</a></li>
              <li><a href="${createLink(controller: 'movimiento', action: 'create2')}">Recibir Archivo</a></li>
              <!--<li><a href="${createLink(controller: 'scasol', action: 'valsliata2')}">Procedidas</a></li>-->
              <!--<li><a href="${createLink(controller: 'scasol', action: 'valsliata3')}">Negativas</a></li>-->
            </ul>
          </li>                                                       
        </ul>  
      </sec:ifAllGranted>
      
      
      <sec:ifAllGranted roles="ROLE_ADMIN">
          
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Seguimiento<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'scasol', action: 'seguimiento')}">Segimiento</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'impresas')}">Impresos</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'resolucionesnega')}">Resoluciones Negativas</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'resolucionesproce')}">Resoluciones Procedidas</a></li>
              <li><a href="${createLink(controller: 'scasol', action: 'resolucionespendiente')}">Resoluciones  Pendientes</a></li>
            </ul>
          </li>                                                       
        </ul>  
        
        
        
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Control de Usuarios <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'user', action: 'list')}">Usuarios</a></li>
              <li><a href="${createLink(controller: 'user', action: 'create')}">Nuevo usuario</a></li>           
            </ul>
          </li>                                                       
        </ul>
        
        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Control de Jefe de Archivo <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'encargadoArchivo', action: 'list')}">Ver Jefes</a></li>
              <li><a href="${createLink(controller: 'encargadoArchivo', action: 'create')}">Nuevo Jefe</a></li>           
            </ul>
          </li>                                                       
        </ul>

        <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Control de Jefe de Unidad Juridica <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'encargadoJuridico', action: 'list')}">Ver Jefes</a></li>
              <li><a href="${createLink(controller: 'encargadoJuridico', action: 'create')}">Nuevo Jefe</a></li>           
            </ul>
          </li>                                                       
        </ul>

        
        
         <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Control de Jefe de Aclaraciones <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'encargadoAclaraciones', action: 'list')}">Ver Jefes</a></li>
              <li><a href="${createLink(controller: 'encargadoAclaraciones', action: 'create')}">Nuevo Jefe</a></li>           
            </ul>
          </li>                                                       
        </ul> 
        
        
           <ul class="nav navbar-nav navbar-left">                    
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Bases para aclaracion <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="${createLink(controller: 'base', action: 'list')}">Bases</a></li>
              <li><a href="${createLink(controller: 'base', action: 'create')}">Agregar base</a></li>           
            </ul>
          </li>                                                       
        </ul> 
        
        
      </sec:ifAllGranted>

      <sec:ifNotLoggedIn>
          <g:link controller='login' action='auth'>Login</g:link>
          <script>
          entrar();
          </script>
          <!--<div>
          <script> 
loadImage(); 
</script>
          </div>-->
          <!--<div onload="${remoteFunction(
                    controller:'login',
                    action:'auth'                              
                    )}">
          Reingresar    
          </div>-->
          <!--<g:remoteFunction controller="login" action="auth"/>-->
        <!--entrar()-->
<!--<g:link controller='login' action='auth'>Login</g:link>-->
<!--redic(controller:"Secure", action: "index")-->
</sec:ifNotLoggedIn>  
      
      <sec:ifLoggedIn>
          <ul class="nav navbar-nav navbar-right">                    
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cuenta<b class="caret"></b></a>
              <ul class="dropdown-menu">
               
                <li><g:link controller="logout">Cerrar sesion</g:link></li>

                <li><a href="${createLink( controller : "user", action : "edit", id : sec.loggedInUserInfo(field: "id") )}">Cambiar contraseña</a></li>                                     
               </ul>
            </li>                                                       
          </ul>
      </sec:ifLoggedIn>
    </div>
    <g:layoutBody/>
  </div>
  <div class="footer" role="contentinfo"></div>
  <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<g:javascript library="application"/>
<r:layoutResources />
<div class="footer">
                <div id="contenedorPie"><br>
            Gobierno del Estado de Oaxaca<br>
            Garcia Vigil No. 602<br>
            Centro, Oaxaca de Juárez            
            
    	 </div>
</div>

</body>
</html>
