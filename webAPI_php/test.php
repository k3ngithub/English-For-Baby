<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Untitled Document</title>
</head>

<body>
<?php
$con=mysqli_connect("localhost","root","","android_db_1");
$sql="select * from user where email='beo@yahoo.com'";
		$result=mysqli_query($con,$sql);
		
		$rows=mysqli_num_rows($result);//lay so hang
		echo $rows;
	
?>
</body>
</html>