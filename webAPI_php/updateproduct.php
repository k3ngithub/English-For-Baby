<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['id']) && $_POST['id']!='' &&
			isset($_POST['name']) && $_POST['name']!='' )
	{
		$id=$_POST['id'];
		$name=$_POST['name'];
		$price=$_POST['price'];
		$image=$_POST['image'];
		$description=$_POST['description'];
		
		$result=$db->updateProduct($id,$name,$price,$image,$description);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="cap nhat san pham thanh cong";
		}
	}
	else
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="khong co id hoac ten";
	}
	
	echo json_encode($json);
?>
