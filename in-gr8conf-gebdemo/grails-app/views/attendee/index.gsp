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

    <f:table collection="${attendeeList}" />

    <script>
	    $('table').addClass('table').addClass('table-bordered');
    </script>

    <div class="pagination">
        <g:paginate total="${attendeeCount ?: 0}" />
    </div>
    </body>
</html>