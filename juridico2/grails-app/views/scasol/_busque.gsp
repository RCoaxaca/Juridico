<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
      <g:if test="${p}">
         <table class="table  table-striped table-bordered" >
             <thead>
                 <tr>
                     <th>Exp</th>
                     <th>Folio</th>
                     <th>Estado</th>
                     <th>Observaciones</th>
                     <th>Nombre</th>
                     <th>No. Solicitud</th> 

                 </tr>
                 <tr>
                     <td>${p.id}</td>
                     <td>${p.folioexp}</td>
                     <td>${p.estado} </td>
                     <td>${p.obser} </td>
                     <td>${p.nomb} ${p.apema}  ${p.apepa} </td>
                     <td>${p.folio} </td>     
                 </tr>
             </thead>
         </table>
        </g:if><g:else>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="alert alert-danger alert-dismissable">
                      <button type="button" class="close" data-dismiss="alert">&times;</button>
                      No hay resultados en la busqueda.
                    </div>                    
                </div>   
            </div>
                 
        </g:else>

    </body>
</html>
