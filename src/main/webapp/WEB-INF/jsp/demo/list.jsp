<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="/demo">

<input type="text" name="name1">
<input type="text" name="name2">
<input type="text" name="name3">
<input type="text" name="name4">
<input type="checkbox" name="chk[]" value="11">
<input type="checkbox" name="chk[]" value="22">
<input type="checkbox" name="chk[]" value="33">
<button>전송</button>
</form>
</body>
</html>