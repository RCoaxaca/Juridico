
<%@ page import="tablas.Scaact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scaact.label', default: 'Scaact')}" />
		
	</head>
	<body>
		<a href="#show-scaact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-scaact" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scaact">
			
				<g:if test="${scaactInstance?.numacta}">
				<li class="fieldcontain">
					<span id="numacta-label" class="property-label"><g:message code="scaact.numacta.label" default="Numacta" /></span>
					
						<span class="property-value" aria-labelledby="numacta-label"><g:fieldValue bean="${scaactInstance}" field="numacta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.fechaacta}">
				<li class="fieldcontain">
					<span id="fechaacta-label" class="property-label"><g:message code="scaact.fechaacta.label" default="Fechaacta" /></span>
					
						<span class="property-value" aria-labelledby="fechaacta-label"><g:formatDate date="${scaactInstance?.fechaacta}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.dto}">
				<li class="fieldcontain">
					<span id="dto-label" class="property-label"><g:message code="scaact.dto.label" default="Dto" /></span>
					
						<span class="property-value" aria-labelledby="dto-label"><g:link controller="scadto" action="show" id="${scaactInstance?.dto?.id}">${scaactInstance?.dto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mpo}">
				<li class="fieldcontain">
					<span id="mpo-label" class="property-label"><g:message code="scaact.mpo.label" default="Mpo" /></span>
					
						<span class="property-value" aria-labelledby="mpo-label"><g:link controller="scampo" action="show" id="${scaactInstance?.mpo?.id}">${scaactInstance?.mpo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.loc}">
				<li class="fieldcontain">
					<span id="loc-label" class="property-label"><g:message code="scaact.loc.label" default="Loc" /></span>
					
						<span class="property-value" aria-labelledby="loc-label"><g:link controller="localidades" action="show" id="${scaactInstance?.loc?.id}">${scaactInstance?.loc?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pnombre}">
				<li class="fieldcontain">
					<span id="pnombre-label" class="property-label"><g:message code="scaact.pnombre.label" default="Pnombre" /></span>
					
						<span class="property-value" aria-labelledby="pnombre-label"><g:fieldValue bean="${scaactInstance}" field="pnombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pap1}">
				<li class="fieldcontain">
					<span id="pap1-label" class="property-label"><g:message code="scaact.pap1.label" default="Pap1" /></span>
					
						<span class="property-value" aria-labelledby="pap1-label"><g:fieldValue bean="${scaactInstance}" field="pap1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pap2}">
				<li class="fieldcontain">
					<span id="pap2-label" class="property-label"><g:message code="scaact.pap2.label" default="Pap2" /></span>
					
						<span class="property-value" aria-labelledby="pap2-label"><g:fieldValue bean="${scaactInstance}" field="pap2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pedad}">
				<li class="fieldcontain">
					<span id="pedad-label" class="property-label"><g:message code="scaact.pedad.label" default="Pedad" /></span>
					
						<span class="property-value" aria-labelledby="pedad-label"><g:fieldValue bean="${scaactInstance}" field="pedad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pnac}">
				<li class="fieldcontain">
					<span id="pnac-label" class="property-label"><g:message code="scaact.pnac.label" default="Pnac" /></span>
					
						<span class="property-value" aria-labelledby="pnac-label"><g:link controller="nacionalidad" action="show" id="${scaactInstance?.pnac?.id}">${scaactInstance?.pnac?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab1}">
				<li class="fieldcontain">
					<span id="pab1-label" class="property-label"><g:message code="scaact.pab1.label" default="Pab1" /></span>
					
						<span class="property-value" aria-labelledby="pab1-label"><g:fieldValue bean="${scaactInstance}" field="pab1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab1ap1}">
				<li class="fieldcontain">
					<span id="pab1ap1-label" class="property-label"><g:message code="scaact.pab1ap1.label" default="Pab1ap1" /></span>
					
						<span class="property-value" aria-labelledby="pab1ap1-label"><g:fieldValue bean="${scaactInstance}" field="pab1ap1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab1ap2}">
				<li class="fieldcontain">
					<span id="pab1ap2-label" class="property-label"><g:message code="scaact.pab1ap2.label" default="Pab1ap2" /></span>
					
						<span class="property-value" aria-labelledby="pab1ap2-label"><g:fieldValue bean="${scaactInstance}" field="pab1ap2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab1nac}">
				<li class="fieldcontain">
					<span id="pab1nac-label" class="property-label"><g:message code="scaact.pab1nac.label" default="Pab1nac" /></span>
					
						<span class="property-value" aria-labelledby="pab1nac-label"><g:link controller="nacionalidad" action="show" id="${scaactInstance?.pab1nac?.id}">${scaactInstance?.pab1nac?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab2}">
				<li class="fieldcontain">
					<span id="pab2-label" class="property-label"><g:message code="scaact.pab2.label" default="Pab2" /></span>
					
						<span class="property-value" aria-labelledby="pab2-label"><g:fieldValue bean="${scaactInstance}" field="pab2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab2ap1}">
				<li class="fieldcontain">
					<span id="pab2ap1-label" class="property-label"><g:message code="scaact.pab2ap1.label" default="Pab2ap1" /></span>
					
						<span class="property-value" aria-labelledby="pab2ap1-label"><g:fieldValue bean="${scaactInstance}" field="pab2ap1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab2ap2}">
				<li class="fieldcontain">
					<span id="pab2ap2-label" class="property-label"><g:message code="scaact.pab2ap2.label" default="Pab2ap2" /></span>
					
						<span class="property-value" aria-labelledby="pab2ap2-label"><g:fieldValue bean="${scaactInstance}" field="pab2ap2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.pab2nac}">
				<li class="fieldcontain">
					<span id="pab2nac-label" class="property-label"><g:message code="scaact.pab2nac.label" default="Pab2nac" /></span>
					
						<span class="property-value" aria-labelledby="pab2nac-label"><g:link controller="nacionalidad" action="show" id="${scaactInstance?.pab2nac?.id}">${scaactInstance?.pab2nac?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mnom}">
				<li class="fieldcontain">
					<span id="mnom-label" class="property-label"><g:message code="scaact.mnom.label" default="Mnom" /></span>
					
						<span class="property-value" aria-labelledby="mnom-label"><g:fieldValue bean="${scaactInstance}" field="mnom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.map1}">
				<li class="fieldcontain">
					<span id="map1-label" class="property-label"><g:message code="scaact.map1.label" default="Map1" /></span>
					
						<span class="property-value" aria-labelledby="map1-label"><g:fieldValue bean="${scaactInstance}" field="map1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.map2}">
				<li class="fieldcontain">
					<span id="map2-label" class="property-label"><g:message code="scaact.map2.label" default="Map2" /></span>
					
						<span class="property-value" aria-labelledby="map2-label"><g:fieldValue bean="${scaactInstance}" field="map2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.medad}">
				<li class="fieldcontain">
					<span id="medad-label" class="property-label"><g:message code="scaact.medad.label" default="Medad" /></span>
					
						<span class="property-value" aria-labelledby="medad-label"><g:fieldValue bean="${scaactInstance}" field="medad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mnac}">
				<li class="fieldcontain">
					<span id="mnac-label" class="property-label"><g:message code="scaact.mnac.label" default="Mnac" /></span>
					
						<span class="property-value" aria-labelledby="mnac-label"><g:link controller="nacionalidad" action="show" id="${scaactInstance?.mnac?.id}">${scaactInstance?.mnac?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mab1}">
				<li class="fieldcontain">
					<span id="mab1-label" class="property-label"><g:message code="scaact.mab1.label" default="Mab1" /></span>
					
						<span class="property-value" aria-labelledby="mab1-label"><g:fieldValue bean="${scaactInstance}" field="mab1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mab1ap1}">
				<li class="fieldcontain">
					<span id="mab1ap1-label" class="property-label"><g:message code="scaact.mab1ap1.label" default="Mab1ap1" /></span>
					
						<span class="property-value" aria-labelledby="mab1ap1-label"><g:fieldValue bean="${scaactInstance}" field="mab1ap1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mab1ap2}">
				<li class="fieldcontain">
					<span id="mab1ap2-label" class="property-label"><g:message code="scaact.mab1ap2.label" default="Mab1ap2" /></span>
					
						<span class="property-value" aria-labelledby="mab1ap2-label"><g:fieldValue bean="${scaactInstance}" field="mab1ap2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mab1nac}">
				<li class="fieldcontain">
					<span id="mab1nac-label" class="property-label"><g:message code="scaact.mab1nac.label" default="Mab1nac" /></span>
					
						<span class="property-value" aria-labelledby="mab1nac-label"><g:link controller="nacionalidad" action="show" id="${scaactInstance?.mab1nac?.id}">${scaactInstance?.mab1nac?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mba2}">
				<li class="fieldcontain">
					<span id="mba2-label" class="property-label"><g:message code="scaact.mba2.label" default="Mba2" /></span>
					
						<span class="property-value" aria-labelledby="mba2-label"><g:fieldValue bean="${scaactInstance}" field="mba2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mab2ap1}">
				<li class="fieldcontain">
					<span id="mab2ap1-label" class="property-label"><g:message code="scaact.mab2ap1.label" default="Mab2ap1" /></span>
					
						<span class="property-value" aria-labelledby="mab2ap1-label"><g:fieldValue bean="${scaactInstance}" field="mab2ap1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mab2ap2}">
				<li class="fieldcontain">
					<span id="mab2ap2-label" class="property-label"><g:message code="scaact.mab2ap2.label" default="Mab2ap2" /></span>
					
						<span class="property-value" aria-labelledby="mab2ap2-label"><g:fieldValue bean="${scaactInstance}" field="mab2ap2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.mab2nac}">
				<li class="fieldcontain">
					<span id="mab2nac-label" class="property-label"><g:message code="scaact.mab2nac.label" default="Mab2nac" /></span>
					
						<span class="property-value" aria-labelledby="mab2nac-label"><g:link controller="nacionalidad" action="show" id="${scaactInstance?.mab2nac?.id}">${scaactInstance?.mab2nac?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.procede}">
				<li class="fieldcontain">
					<span id="procede-label" class="property-label"><g:message code="scaact.procede.label" default="Procede" /></span>
					
						<span class="property-value" aria-labelledby="procede-label"><g:fieldValue bean="${scaactInstance}" field="procede"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.bases}">
				<li class="fieldcontain">
					<span id="bases-label" class="property-label"><g:message code="scaact.bases.label" default="Bases" /></span>
					
						<span class="property-value" aria-labelledby="bases-label"><g:fieldValue bean="${scaactInstance}" field="bases"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.datofalta}">
				<li class="fieldcontain">
					<span id="datofalta-label" class="property-label"><g:message code="scaact.datofalta.label" default="Datofalta" /></span>
					
						<span class="property-value" aria-labelledby="datofalta-label"><g:fieldValue bean="${scaactInstance}" field="datofalta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.donde}">
				<li class="fieldcontain">
					<span id="donde-label" class="property-label"><g:message code="scaact.donde.label" default="Donde" /></span>
					
						<span class="property-value" aria-labelledby="donde-label"><g:fieldValue bean="${scaactInstance}" field="donde"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.expano}">
				<li class="fieldcontain">
					<span id="expano-label" class="property-label"><g:message code="scaact.expano.label" default="Expano" /></span>
					
						<span class="property-value" aria-labelledby="expano-label"><g:fieldValue bean="${scaactInstance}" field="expano"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.exppro}">
				<li class="fieldcontain">
					<span id="exppro-label" class="property-label"><g:message code="scaact.exppro.label" default="Exppro" /></span>
					
						<span class="property-value" aria-labelledby="exppro-label"><g:fieldValue bean="${scaactInstance}" field="exppro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scaactInstance?.tipoerresp}">
				<li class="fieldcontain">
					<span id="tipoerresp-label" class="property-label"><g:message code="scaact.tipoerresp.label" default="Tipoerresp" /></span>
					
						<span class="property-value" aria-labelledby="tipoerresp-label"><g:fieldValue bean="${scaactInstance}" field="tipoerresp"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scaactInstance?.id}" />
					<g:link class="edit" action="edit" id="${scaactInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
