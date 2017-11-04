<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();

	if(isset($_POST['tag'])&& $_POST['tag']!='')
	{
		$tag=$_POST['tag'];
	
		$json=array("tag"=>$tag,"successful"=>0,"err"=>0);	
	
		if($tag=='login')
		{
			xulydangnhap($json,$db);
		}
		else if($tag=='register')
		{
			xulydangki($json,$db);
		}
		else 
		{
			echo "Invalid request!";
		}
	}
	else
	{
		echo "Cannot access!";
	}
	
	
	function xulydangnhap($json,$db)
{
	$username=$_POST['username'];
	$password=$_POST['password'];
	$user=$db->getUser($username,$password);
	
	if($user!=false) // tim thay user
	{
		$json["successful"]=1;
		$json["user"]["username"]=$user["username"];
		$json["user"]["password"]=$user["password"];
		
		echo json_encode($json);
	}
	else //tim khong thay user
	{
		$json["err"]=1;
		$json["thongbaoloi"]="Username or password incorrect!";
		
		echo json_encode($json);
	}
}

function xulydangki($json,$db)
{
	$avatar=$_POST["avatar"];
	$fullname=$_POST["fullname"];
	$username=$_POST["username"];
	$password=$_POST["password"];
	$highscore=$_POST["highscore"];
	$account_type=$_POST["account_type"];
	
	if($db->checkUser($username))//true : da ton tai
	{
		$json["err"]=2;
		$json["thongbaoloi"]="Username exist!";
		
		echo json_encode($json);
	}
	else//user chua ton tai, luu du lieu
	{
		$user=$db->storeUser($avatar,$fullname,$username,$password,$highscore,$account_type);
		
		if($user!=false)//neu luu thanh cong
		{
			$json["successful"]=1;
			$json["user"]["avatar"]=$user["avatar"];
			$json["user"]["fullname"]=$user["fullname"];
			$json["user"]["username"]=$user["username"];
			$json["user"]["password"]=$user["password"];
			$json["user"]["highscore"]=$user["highscore"];
			$json["user"]["account_type"]=$user["account_type"];
			
			echo json_encode($json);
		}
		else //neu luu that bai
		{
			$json["err"]=1;
			$json["thongbaoloi"]="Registration failed!";
			
			echo json_encode($json);
		}
	}
}

	
?>
