
  function soloLetras(e){
       key = window.keyCode || e.which;
       tecla = String.fromCharCode(key).toLowerCase();
       letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
       especiales = [];//8,37,39,46

       tecla_especial = false
       for(var i in especiales){
            if(key == especiales[i]){
                tecla_especial = true;
                break;
            }
        }
 
        if(letras.indexOf(tecla)==-1 && !tecla_especial){
            return false;
        }
    }
    function soloNumeros(e){    
       key = window.keyCode || e.which;
       tecla = String.fromCharCode(key).toLowerCase();
       letras = "0123456789";
       especiales = [];

       tecla_especial = false
       for(var i in especiales){
            if(key == especiales[i]){
                tecla_especial = true;
                break;
            }
        }

        if(letras.indexOf(tecla)==-1 && !tecla_especial){
            return false;
        }
    }
    function fechaNoValida(obj) 
    {        
        cadena = "^\d{1,2}\/\d{1,2}\/\d{2,4}$"; 
        re = new RegExp(cadena);
 
        if (document.getElementById(obj).value.match(re))
            return false
        else 
            return true
    }
    function horaNoValida(obj) 
    {        
        cadena = "^(0[1-9]|1\d|2[0-3]):([0-5]\d):([0-5]\d)$";
        re = new RegExp(cadena);
 
        if (document.getElementById(obj).value.match(re))
            return false
        else 
            return true
    }
    function curpNoValida(obj) 
    {        
        cadena = "/^([a-z]{4})([0-9]{6})([a-z]{6})([0-9]{2})$/i";
        re = new RegExp(cadena);
 
        if (!(document.getElementById(obj).value.match(re)))
            return false
        else 
            return true
    }
    function kzk_validacion_saveInscripcion1_padre()
    {   
        /*
       completo=true;
    if( curpNoValida("curp") )
      { completo=false;alert("Curp No Valida");document.getElementById("curp").focus(); }  
      */
     
     
      completo=false;
      return completo;            
    }
    
    function kzk_validacion()
    {
      completo=true;
    if( vacio("acta") )
      { completo=false;alert("Capture Acta");document.getElementById("acta").focus(); }  
    if( vacio("nombre") )
      { completo=false;alert("Capture Nombre");document.getElementById("nombre").focus(); }
      else if (vacio("paterno"))
      { completo=false;alert("Capture Apellido Paterno");document.getElementById("paterno").focus(); }
      else if(vacio("materno"))
      { completo=false;alert("Capture Apellido Materno");document.getElementById("materno").focus(); }       
      else if(vacio("fechanacimiento"))
      { completo=false;alert("Capture Fecha");document.getElementById("fechanacimiento").focus(); }
    else if(vacio("certificado"))
      { completo=false;alert("Capture Certificado");document.getElementById("certificado").focus(); }
     //else if(fechaNoValida("fechanacimiento"))
     // { completo=false;alert("Fecha Incorrecta");document.getElementById("fechanacimiento").focus(); }       
    else if(vacio("hora"))
      { completo=false;alert("Capture Hora");document.getElementById("hora").focus(); }     
    //  else if(horaNoValida("hora"))
      //{ completo=false;alert("Hora Incorrecta");document.getElementById("hora").focus(); }
        
      if(completo){return true;}
      else {return false;}

    }
    function vacio(obj) {       
        miCampoTexto = document.getElementById(obj).value;        
        if (miCampoTexto.length == 0 || /^\s+$/.test(miCampoTexto)) {
            return true;
        }
        return false;
    }
  
