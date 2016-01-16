<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="bootstrap">
	<title>Upload Example</title>
</head>
<body>



<h1>Upload form</h1>
<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
</g:if>


<g:form action="upload" enctype="multipart/form-data">
	<input type="file" name="filename" />
	<fieldset class="well">
		<button class="btn btn-primary" type="submit" value="" ><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> Upload</button>
	</fieldset>
</g:form>



</body>
</html>