<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="bootstrap">
        <g:set var="entityName" value="${message(code: 'attendee.label', default: 'Attendee')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
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


    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${attendee}">
	    <ul class="errors" role="alert">
	        <g:eachError bean="${attendee}" var="error">
	        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	        </g:eachError>
	    </ul>
    </g:hasErrors>

    <g:form resource="${attendee}" method="PUT">
        <g:hiddenField name="version" value="${attendee?.version}" />
        <fieldset class="form">
            <f:all bean="attendee"/>
        </fieldset>

        <fieldset class="well">
	        <button class="btn btn-primary" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}"><span class="glyphicon glyphicon-save" aria-hidden="true"></span> <g:message code="default.button.update.label" default="Update"/></button>
        </fieldset>
    </g:form>
    </body>
</html>
