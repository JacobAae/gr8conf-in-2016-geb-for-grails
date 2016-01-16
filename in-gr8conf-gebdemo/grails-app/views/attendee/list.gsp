<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="bootstrap">
        <g:set var="entityName" value="${message(code: 'attendee.label', default: 'Attendee')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

    <nav class="navbar navbar-default">
	    <div class="container-fluid">
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav">
				    <li><a class="home" href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> <g:message code="default.home.label"/></a></li>
				    <li><g:link class="create" action="create"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> <g:message code="default.new.label" args="[entityName]" /></g:link></li>
			    </ul>
		    </div>
	    </div>
    </nav>

    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="fade-me-in" style="display: none; background-color: #006dba;">Hi - are yo waiting for me?</div>
    <br/>

    <f:table collection="${attendeeList}" />

    <script>
	    $('table').addClass('table').addClass('table-bordered');

	    $('div.fade-me-in').delay(3000).slideDown();

    </script>

    <fieldset class="well" style="display: none">
	    <g:link class="btn btn-primary" action="index" resource="${attendee}"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> Paginated list</g:link>
    </fieldset>


    </body>
</html>