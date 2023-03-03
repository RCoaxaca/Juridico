<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link href='http://www.registrocivil.oaxaca.gob.mx/imagenes/logomini.jpg' rel='shortcut icon' type='image/x-icon'>
              
<!--<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">-->
                <!--<link rel="stylesheet" href="${resource(dir: 'css', file: 'estilos.css')}" type="text/css">-->
                
	</head>       
	<body>                  
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div class="container">
               <div class="panel panel-default">
                  <div class="panel-body">
                      <div class="col-md-offset-1 col-md-10">
                          <div class="panel panel-default">
                                      <div class="panel-body">
                                          
                                          <div class="form-horizontal col-md-12">  
                                            <h5 class="text-center"> Bienvenido...  <sec:username></sec:username></h5> 
                                            <sec:ifNotLoggedIn>
                                            <g:link controller='login' action='auth'>Login</g:link>
                                            </sec:ifNotLoggedIn>  
                                          </div>
                                      </div>
                          </div>
                       </div>
                   </div>
               </div>
           </div>
        </body>		
</html>
