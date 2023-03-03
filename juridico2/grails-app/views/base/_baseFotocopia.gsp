<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<div class="col-md-12">
    <div class="col-md-9"><label for="fotocopia" class="label-control col-md-9">
		DE ACUERDO A LA FOTOCOPIA DEL LIBRO DE:	
	</label></div>
 
<div class="col-md-3" name="fotocopia"  onclick="showCheckboxes()" noSelection="['':'']">
            <select name="base" onblur="${remoteFunction(
                        controller:'base',
                        action:'verBase2',
                        params:'\'id=\' + this.value',
                        update:'baseaclara'          
                        )}" class="form-control"> 
                <option value="LA FOTOCOPIA DEL LIBRO DE LA OFICIALIA PROCEDIENDO A IGUALAR EJEMPLARES">Oficialia</option>
                <option value="LA FOTOCOPIA DEL LIBRO ARCHIVO CENTRAL PROCEDIENDO A IGUALAR EJEMPLARES">Archivo Central</option>
                <option value="LA FOTOCOPIA DEL LIBRO MUNICIPIO PROCEDIENDO A IGUALAR EJEMPLARES">Municipio</option>
            </select>
            <div class="overSelect"></div>
</div>
</div>