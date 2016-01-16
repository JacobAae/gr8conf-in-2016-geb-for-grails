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
				    <li><a class="home" href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> <g:message code="default.home.label"/></a></li>
				    <li><g:link class="list" action="index"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> <g:message code="default.list.label" args="[entityName]" /></g:link></li>
				    <li><g:link class="create" action="create"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> <g:message code="default.new.label" args="[entityName]" /></g:link></li>
			    </ul>
		    </div>
	    </div>
    </nav>


    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>

    <f:display bean="attendee" />

    <g:form resource="${attendee}" method="DELETE">
	    <fieldset class="well">
		    <g:link class="btn btn-primary" action="edit" resource="${attendee}"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> <g:message code="default.button.edit.label" default="Edit" /></g:link>
		    <button class="btn btn-danger" type="submit" value="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> <g:message code="default.button.delete.label" default="Delete"/></button>
	    </fieldset>
    </g:form>



    </body>
</html>