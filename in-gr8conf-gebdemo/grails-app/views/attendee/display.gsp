<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="bootstrap">
	<g:set var="entityName" value="${message(code: 'attendee.label', default: 'Attendee')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
	</div>
</nav>

<div id="show-attendee" class="content scaffold-show" role="main">
	<h1><g:message code="default.show.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>


	<ol class="property-list attendee">

		<li class="fieldcontain">
			<span id="name-label" class="property-label">Name</span>
			<span class="property-value" aria-labelledby="name-label">${attendee.name}</span>
		</li>

		<li class="fieldcontain">
			<span id="email-label" class="property-label">Email</span>
			<span class="property-value" aria-labelledby="email-label">${attendee.email}</span>
		</li>

		<li class="fieldcontain">
			<span id="nationality-label" class="property-label">Nationality</span>
			<span class="property-value" aria-labelledby="nationality-label">${attendee.nationality}</span>
		</li>

		<li class="fieldcontain">
			<span id="talks-label" class="property-label">Talks</span>
			<span class="property-value" aria-labelledby="talks-label">
				<ul>
					<g:each in="${attendee.talks}" var="talk">
						<li>${talk.title}</li>
					</g:each>
				</ul>
			</span>
		</li>
	</ol>

	<g:form resource="${attendee}" method="DELETE">
		<fieldset class="well">
			<g:link class="btn btn-primary" action="edit" resource="${attendee}"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <g:message code="default.button.edit.label" default="Edit" /></g:link>
			<button class="btn btn-danger" type="submit" value="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> <g:message code="default.button.delete.label" default="Delete"/></button>
		</fieldset>
	</g:form>
</div>
</body>
</html>