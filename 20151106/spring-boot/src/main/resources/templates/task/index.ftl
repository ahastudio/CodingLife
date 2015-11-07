<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ToDo</title>
</head>
<body>
	<#list tasks as task>
		<p>${task.title}</p>
	</#list>
	<form action="" method="post">
		<input name="title" type="text">
		<button type="submit">Save</button>
	</form>
</body>
</html>
