 $('body').on('keydown', 'input, select, textarea', function(e) {
    var self = $(this)
      , form = self.parents('form:eq(0)')
      , focusable
      , next
      ;
	   if (e.keyCode == 113) {
		//return e.keyCode=40;
		//alert("presiono f2");
		//var resultado="Esta seleccionado el input...";
		if(document.activeElement.name)
		{
			//resultado+="<br>con nombre: <b>"+document.activeElement.name+"</b>";
			//alert(resultado);
			var nombre=document.activeElement.name.split(".");
			//alert(nombre[0])
			//document.getElementsByName(document.activeElement.name);
			var x = document.getElementsByName(document.activeElement.name).length;
			//alert(x);
			var posicion=document.getElementById(nombre[0]).options.selectedIndex;
			//alert(posicion);
			document.getElementById(nombre[0]).options.selectedIndex=posicion+1;
			//alert("The length of this select box is " + document.myForm.mySelect.length);
		}
		
    }
    if (e.keyCode == 13) {
        focusable = form.find('input,a,select,button,textarea').filter(':visible');
        next = focusable.eq(focusable.index(this)+1);
        if (next.length) {
            next.focus();
        } else {
            form.submit();
        }
        return false;
    }

	
});
        
        

function conMayusculas(field) {
        field.value = field.value.toUpperCase()
      }
      function isNumberKey(evt)
            {
            var charCode = (evt.which) ? evt.which : event.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
            return true;
            }
			
			

			
function buscaBase()
            {
            //alert(field)
            var combo = document.getElementById("debeser");
            var selected = combo.options[combo.selectedIndex].text;
            //alert(selected);
            if (selected.indexOf("CARECER") >=0) 
                {
                    //alert(selected)
                    document.getElementById("base").value = "LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988";
                }
            else if(selected.indexOf("IMPROCEDENTE") >=0){document.getElementById("base").value = "EL ARTICULO 47 SEGUNDO PARRAFO DEL CODIGO CIVIL VIGENTE EN EL ESTADO";}
            else { document.getElementById("base").value = "EL ARTICULO 385 PARRAFO PRIMERO DEL CODIGO CIVIL VIGENTE EN EL ESTADO";}
         
            }
			
			
function foco(){
 document.getElementById("bases1").focus();
}
			
		
