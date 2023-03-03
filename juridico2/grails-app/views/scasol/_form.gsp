<%@ page import="tablas.Scasol" %>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'condonado', 'error')} ">
	<label for="condonado">
		<g:message code="scasol.condonado.label" default="Condonado" />
		
	</label>
	<g:select name="condonado" from="${scasolInstance.constraints.condonado.inList}" value="${scasolInstance?.condonado}" valueMessagePrefix="scasol.condonado" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'sexintere', 'error')} ">
	<label for="sexintere">
		<g:message code="scasol.sexintere.label" default="Sexintere" />
		
	</label>
	<g:select name="sexintere" from="${scasolInstance.constraints.sexintere.inList}" value="${scasolInstance?.sexintere}" valueMessagePrefix="scasol.sexintere" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="scasol.folio.label" default="Folio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folio" type="number" value="${scasolInstance.folio}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'folioexp', 'error')} ">
	<label for="folioexp">
		<g:message code="scasol.folioexp.label" default="Folioexp" />
		
	</label>
	<g:textField name="folioexp" value="${scasolInstance?.folioexp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="scasol.estado.label" default="Estado" />
		
	</label>
	<g:select id="estado" name="estado.id" from="${catalogos.Docesta.list()}" optionKey="id" value="${scasolInstance?.estado?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'obser', 'error')} ">
	<label for="obser">
		<g:message code="scasol.obser.label" default="Obser" />
		
	</label>
	<g:textField name="obser" onblur="conMayusculas(this)" value="${scasolInstance?.obser}" clas="mayuscula"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'dic', 'error')} ">
	<label for="dic">
		<g:message code="scasol.dic.label" default="Dic" />
		
	</label>
	<g:select id="dic" name="dic.id" from="${com.testapp.User.list()}" optionKey="id" value="${scasolInstance?.dic?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'expano', 'error')} required">
	<label for="expano">
		<g:message code="scasol.expano.label" default="Expano" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="expano" type="number" value="${scasolInstance.expano}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'expro', 'error')} required">
	<label for="expro">
		<g:message code="scasol.expro.label" default="Expro" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="expro" type="number" value="${scasolInstance.expro}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'dic2', 'error')} ">
	<label for="dic2">
		<g:message code="scasol.dic2.label" default="Dic2" />
		
	</label>
	<g:select id="dic2" name="dic2.id" from="${com.testapp.User.list()}" optionKey="id" value="${scasolInstance?.dic2?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'val', 'error')} ">
	<label for="val">
		<g:message code="scasol.val.label" default="Val" />
		
	</label>
	<g:select id="val" name="val.id" from="${com.testapp.User.list()}" optionKey="id" value="${scasolInstance?.val?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchsol', 'error')} ">
	<label for="fchsol">
		<g:message code="scasol.fchsol.label" default="Fchsol" />
		
	</label>
	<g:datePicker name="fchsol" precision="day"  value="${scasolInstance?.fchsol}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'promov', 'error')} ">
	<label for="promov">
		<g:message code="scasol.promov.label" default="Promov" />
		
	</label>
	<g:textField name="promov" onblur="conMayusculas(this)" class="mayuscula" value="${scasolInstance?.promov}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nom_intere', 'error')} ">
	<label for="nom_intere">
		<g:message code="scasol.nom_intere.label" default="Nomintere" />
		
	</label>
	<g:textField name="nom_intere" onblur="conMayusculas(this)" value="${scasolInstance?.nom_intere}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap1_intere', 'error')} ">
	<label for="ap1_intere">
		<g:message code="scasol.ap1_intere.label" default="Ap1intere" />
		
	</label>
	<g:textField name="ap1_intere" onblur="conMayusculas(this)" value="${scasolInstance?.ap1_intere}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ap2_intere', 'error')} ">
	<label for="ap2_intere">
		<g:message code="scasol.ap2_intere.label" default="Ap2intere" />
		
	</label>
	<g:textField name="ap2_intere" onblur="conMayusculas(this)" value="${scasolInstance?.ap2_intere}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'typact', 'error')} ">
	<label for="typact">
		<g:message code="scasol.typact.label" default="Typact" />
		
	</label>
	<g:select id="typact" name="typact.id" from="${catalogos.Tipoactas.list()}" optionKey="id" value="${scasolInstance?.typact?.id}" class="many-to-one" />
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'numact', 'error')} required">
	<label for="numact">
		<g:message code="scasol.numact.label" default="Numact" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numact" type="number" value="${scasolInstance.numact}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchact', 'error')} ">
	<label for="fchact">
		<g:message code="scasol.fchact.label" default="Fchact" />
		
	</label>
	<g:datePicker name="fchact" precision="day"  value="${scasolInstance?.fchact}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'anexo', 'error')} ">
	<label for="anexo">
		<g:message code="scasol.anexo.label" default="Anexo" />
		
	</label>
	<g:textField name="anexo" onblur="conMayusculas(this)" value="${scasolInstance?.anexo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'proced', 'error')} ">
	<label for="proced">
		<g:message code="scasol.proced.label" default="Proced" />
		
	</label>
	<g:checkBox name="proced" value="${scasolInstance?.proced}" />
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'dto', 'error')} ">
	<label for="dto">
		<g:message code="scasol.dto.label" default="Dto" />
		
	</label>
	<g:select id="dto" name="dto.id" from="${catalogos.Scadto.list()}" optionKey="id" value="${scasolInstance?.dto?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'mpo', 'error')} ">
	<label for="mpo">
		<g:message code="scasol.mpo.label" default="Mpo" />
		
	</label>
	<g:select id="mpo" name="mpo.id" from="${catalogos.Scampo.list()}" optionKey="id" value="${scasolInstance?.mpo?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'loc', 'error')} ">
	<label for="loc">
		<g:message code="scasol.loc.label" default="Loc" />
		
	</label>
	<g:select id="loc" name="loc.id" from="${catalogos.Localidades.list()}" optionKey="id" value="${scasolInstance?.loc?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fchcam', 'error')} ">
	<label for="fchcam">
		<g:message code="scasol.fchcam.label" default="Fchcam" />
		
	</label>
	<g:datePicker name="fchcam" precision="day"  value="${scasolInstance?.fchcam}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'isprint', 'error')} required">
	<label for="isprint">
		<g:message code="scasol.isprint.label" default="Isprint" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="isprint" type="number" value="${scasolInstance.isprint}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi', 'error')} ">
	<label for="ofi">
		<g:message code="scasol.ofi.label" default="Ofi" />
		
	</label>
	<g:select id="ofi" name="ofi.id" from="${catalogos.Scaofi.list()}" optionKey="id" value="${scasolInstance?.ofi?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'ofi_recibido', 'error')} required">
	<label for="ofi_recibido">
		<g:message code="scasol.ofi_recibido.label" default="Ofirecibido" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ofi_recibido" type="number" value="${scasolInstance.ofi_recibido}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'apema', 'error')} ">
	<label for="apema">
		<g:message code="scasol.apema.label" default="Apema" />
		
	</label>
	<g:textField name="apema" onblur="conMayusculas(this)" value="${scasolInstance?.apema}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'apepa', 'error')} ">
	<label for="apepa">
		<g:message code="scasol.apepa.label" default="Apepa" />
		
	</label>
	<g:textField name="apepa" onblur="conMayusculas(this)" value="${scasolInstance?.apepa}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'cap', 'error')} required">
	<label for="cap">
		<g:message code="scasol.cap.label" default="Cap" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cap" name="cap.id" from="${com.testapp.User.list()}" optionKey="id" required="" value="${scasolInstance?.cap?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'fechasol', 'error')} required">
	<label for="fechasol">
		<g:message code="scasol.fechasol.label" default="Fechasol" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechasol" precision="day"  value="${scasolInstance?.fechasol}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: scasolInstance, field: 'nomb', 'error')} ">
	<label for="nomb">
		<g:message code="scasol.nomb.label" default="Nomb" />
		
	</label>
	<g:textField name="nomb" onblur="conMayusculas(this)" value="${scasolInstance?.nomb}"/>
</div>

