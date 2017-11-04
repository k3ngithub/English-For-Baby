<?php
	class DB_Connect{
		function __construct(){}
		
		function __destruct(){}
		
		function connect(){
			require_once "config.php";
			$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
			//mysqli_select_db($con,DB_DATABASE);
			return $con;
		}

		function close(){
			//mysqli_close();
		}
	}
?>
