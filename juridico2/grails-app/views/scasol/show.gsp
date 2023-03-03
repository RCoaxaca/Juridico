
<%@ page import="tablas.Scasol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scasol.label', default: 'Scasol')}" />
		
	</head>
	<body>
<!--		<a href="#show-scasol" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>-->
		<div id="show-scasol" class="content scaffold-show" role="main">
			<!--<h1><g:message code="default.show.label" args="[entityName]" /></h1>-->
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scasol">
			
                            
                            <table border="0px">
                                <tr>
                                    <td><g:message code="scasol.folioexp.label" default="Expediente     " /> </td>
                                    <td></td>
                                    <td>${scasolInstance.expro+"/"+scasolInstance.expano}</td>
                                </tr>
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Promovido por  " /></td>
                                    <td></td>
                                    <td><g:fieldValue bean="${scasolInstance}" field="promov"/></td>
                                </tr>
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Interesado  " /></td>
                                    <td></td>
                                    <td><g:fieldValue bean="${scasolInstance}" field="nom_intere"/> <g:fieldValue bean="${scasolInstance}" field="ap1_intere"/>  <g:fieldValue bean="${scasolInstance}" field="ap2_intere"/></td>
                                </tr>
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Sexo  " /></td>
                                    <td></td>
                                    <td><g:fieldValue bean="${scasolInstance}" field="sexintere"/> </td>
                                </tr>
                                 <tr>
                                    <td><g:message code="scasol.promov.label" default="Fecha de aclaración  " /></td>
                                    <td></td>
                                    <td><g:formatDate format="dd-MM-yyyy" date="${scasolInstance.fchsol}" /> </td>
                                </tr>
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Pago condonado " /></td>
                                    <td></td>
                                    <td><g:fieldValue bean="${scasolInstance}" field="condonado"/> </td>
                                </tr>
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Tipo de acta  " /></td>
                                    <td></td>
                                    <td><g:fieldValue bean="${scasolInstance}" field="typact"/> </td>
                                </tr>
                                 <tr>
                                    <td><g:message code="scasol.promov.label" default="No. de acta " /></td>
                                    <td></td>
                                    <td><g:fieldValue bean="${scasolInstance}" field="numact"/> </td>
                                </tr> 
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Fecha de registro " /></td>
                                    <td></td>
                                   
                                    <g:field name="fchact" type="date" value="${scasolInstance?.fchact.toString()}"  class="form-control"/>
                                </tr>
                                 <tr>
                                    <td><g:message code="scasol.promov.label" default="Oficialia" /></td>
                                    <td></td>
                                    <td>${scasolInstance?.ofi?.descrip}</td>
                                </tr>
                                 <tr>
                                    <td><g:message code="scasol.promov.label" default="Lugar" /></td>
                                    <td></td>
                                    <td>${scasolInstance?.loc?.localidad.equals(scasolInstance?.mpo?.descrip)?scasolInstance?.mpo?.descrip:(scasolInstance?.loc?.localidad+", "+scasolInstance?.mpo)}, ${scasolInstance?.dto?.descc}</td>
                                </tr>
                                 <tr>
                                    <td><g:message code="scasol.promov.label" default="Status" /></td>
                                    <td></td>
                                    <td>${scasolInstance?.estado?.docuestado}</td>
                                </tr>
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Observaciones" /></td>
                                    <td></td>
                                    <td>${scasolInstance?.obser}</td>
                                </tr>
                                <tr>
                                    <td><g:message code="scasol.promov.label" default="Validador" /></td>
                                    <td></td>
                                    <td>${scasolInstance?.val?.username}</td>
                                </tr>
                            </table>
                               
                              
				
			
				
			
				
			
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scasolInstance?.id}" />
					 <g:link action="editval" class="btn btn-primary" id="${scasolInstance.id}"><img src="${createLinkTo(dir:'images',file:'edit.png')}"/> Editar</g:link>
                                         <g:link action="archivo" class="btn btn-primary" id="${scasolInstance.id}"><img src="${createLinkTo(dir:'images',file:'pdf1.png')}"/> Notificación</g:link>
   
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
