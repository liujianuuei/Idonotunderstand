<html>
<head>
    <title>BookHub - Book</title>
</head>
<body>
<h3>Welcome, Enter The Book Details</h3>
<form method="post" enctype="multipart/form-data" action="/BookHub/book/batch">
    <input type="file" name="file" accept=".xls,.xlsx,.csv" />
    <input type="submit" value="Upload file" />
</form>
</body>
</html>