<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['name'])&& $_POST['name']!='')
	{
		$name=$_POST['name'];
		$price=$_POST['price'];
		$image=$_POST['image'];
		$description=$_POST['description'];
		$result=$db->storeProduct($name,$price,$image,$description);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="tao san pham thanh cong";
		}
		else
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="tao khong thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="chua nhap ten san pham";
	}
	
	echo json_encode($json);

?>
