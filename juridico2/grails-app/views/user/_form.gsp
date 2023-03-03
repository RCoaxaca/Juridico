<%@ page import="com.testapp.User" %>
<%@ page import="com.testapp.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="user.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="user.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'apellma', 'error')} ">
	<label for="apellma">
		<g:message code="user.apellma.label" default="Apellma" />
		
	</label>
	<g:textField name="apellma" value="${userInstance?.apellma}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'apellpa', 'error')} ">
	<label for="apellpa">
		<g:message code="user.apellpa.label" default="Apellpa" />
		
	</label>
	<g:textField name="apellpa" value="${userInstance?.apellpa}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="user.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${userInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="user.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${userInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="user.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'usuario', 'error')} ">
	<label for="usuario">
		<g:message code="user.usuario.label" default="Usuario" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.usuario?}" var="u">
    <li><g:link controller="venta" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="venta" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'venta.label', default: 'Venta')])}</g:link>
</li>
</ul>

</div>

