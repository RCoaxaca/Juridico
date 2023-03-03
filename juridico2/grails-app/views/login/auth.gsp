<html>
<head>
    <meta name="layout" content="main2">
	
	<title>Aclaracion de acta</title>
	<style type='text/css' media='screen'>
	#login {
		margin: 15px 0px;
		padding: 0px;
		text-align: center;
	}

	#login .inner {
		width: 340px;
		padding-bottom: 6px;
		margin: 60px auto;
		text-align: left;
		border: 1px solid #aab;
		background-color: #f0f0fa;
		-moz-box-shadow: 2px 2px 2px #eee;
		-webkit-box-shadow: 2px 2px 2px #eee;
		-khtml-box-shadow: 2px 2px 2px #eee;
		box-shadow: 2px 2px 2px #eee;
	}

	#login .inner .fheader {
		padding: 18px 26px 14px 26px;
		background-color: #f7f7ff;
		margin: 0px 0 14px 0;
		color: #2e3741;
		font-size: 18px;
		font-weight: bold;
	}

	#login .inner .cssform p {
		clear: left;
		margin: 0;
		padding: 4px 0 3px 0;
		padding-left: 105px;
		margin-bottom: 20px;
		height: 1%;
	}

	#login .inner .cssform input[type='text'] {
		width: 120px;
	}

	#login .inner .cssform label {
		font-weight: bold;
		float: left;
		text-align: right;
		margin-left: -105px;
		width: 110px;
		padding-top: 3px;
		padding-right: 10px;
	}

	#login #remember_me_holder {
		padding-left: 120px;
	}

	#login #submit {
		margin-left: 15px;
	}

	#login #remember_me_holder label {
		float: none;
		margin-left: 0;
		text-align: left;
		width: 200px
	}

	#login .inner .login_message {
		padding: 6px 25px 20px 25px;
		color: #c33;
	}

	#login .inner .text_ {
		width: 120px;
	}

	#login .inner .chk {
		height: 12px;
	}
	</style>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'estilos2.css')}" type="text/css">
        
</head>
<body>
    <br/>
        <br/>
        <br/>
        <br/>

    <div class="col-md-offset-4 col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <!--<h3 class="panel-title"><span class="glyphicon glyphicon-log-in"></span> Acceso al modulo de aclaracion de actas</h3>-->
                    <h3 class="panel-title"><span class="glyphicon glyphicon-log-in"></span> Acceso al sistema</h3>
                </div>
                <div class="panel-body">
                    <div id="errorLogin">
                        <g:if test='${flash.message}'>
                            <div class="alert alert-danger">Nombre de usuario y contraseña no validos</div>
                            <!-- <div class='login_message error'>${flash.message}</div> -->
                        </g:if>
                    </div>
                    <form action='${postUrl}' class="form-horizontal" role="form"  method='POST' id='loginForm'autocomplete='off'>
                        <div class="form-group">
                            <div class="col-sm-10 col-md-offset-1 ">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                    <input type='text' class='form-control' name='j_username' placeholder="Usuario" id='username'/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-10 col-md-offset-1 ">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input type='password' required class='form-control' placeholder="Contraseña" name='j_password' id='password'/>
                                </div>
                            </div>
                        </div>

                        <div class="pull-left col-sm-12">
                            <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                            <label for='remember_me'>Recordar usuario</label>
                        </div>
                     
                        <div class="col-sm-offset-8">
                            <input type='submit' class="btn btn-primary" id="submit" value='${message(code: "springSecurity.login.button", default: 'Ingresar' )}'/>
                        </div>
                    </form>
                </div>
            </div>
        </div>

<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
</body>
</html>
