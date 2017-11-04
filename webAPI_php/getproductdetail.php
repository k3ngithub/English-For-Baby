<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['id'])&& $_POST['id']!='')//co id
	{
		$id=$_POST['id'];
		$result=$db->getProductDetail($id);
		
		//co du lieu
		if(!empty($result) && mysqli_num_rows($result))
		{
			$result=mysqli_fetch_array($result);
			$sanpham=array();
			$sanpham["id"]=$result["id"];
			$sanpham["name"]=$result["name"];
			$sanpham["price"]=$result["price"];
			$sanpham["image"]=$result["image"];
			$sanpham["description"]=$result["description"];
			$sanpham["create_date"]=$result["create_date"];
			$sanpham["update_date"]=$result["update_date"];
			
			//ghi vao json
			$json["thanhcong"]=1;
			$json["sanpham"]=array();
			array_push($json["sanpham"],$sanpham);
		}
		else//khong co du lieu
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="khong co san pham";
		}
		
	}
	else //khong co post id
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="khong co id de lay";
	}
	
	echo json_encode($json);

?>
