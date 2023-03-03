
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
  <head>
      <sec:ifLoggedIn>	
Inicio session:<sec:username></sec:username>

</sec:ifLoggedIn>




    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Dirección del Registro Civil:: Unidad Jurídica</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="images/icono.png" sizes="32x32">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
    <!--<link rel="stylesheet" href="${resource(dir: 'css', file: 'estilos.css')}" type="text/css">-->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'estilos2.css')}" type="text/css">
   
    <script src="${resource(dir:'js',file:'jquery-1.10.2.js')}"></script>
    <script src="${resource(dir:'js',file:'bootstrap.js')}"></script>
    <script src="${resource(dir:'js',file:'jquery.js')}"></script>
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
     
   
   <div id="gobiernoOaxaca" title="Gobierno de Oaxaca" ></div> 
   </div>
        <!--<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div>-->
  <div class="container">

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
