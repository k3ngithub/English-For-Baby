<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	$result=$db->getAllProducts();
	if(mysqli_num_rows($result)>0)//co san pham
	{
		$json["thanhcong"]=1;
		$json["sanpham"]=array(); //mang con
		
		//duyet tat ca san pham dua vao json
		while($row=mysqli_fetch_array($result))
		{
			$sanpham=array();
			$sanpham["id"]=$row["id"];
			$sanpham["name"]=$row["name"];
			$sanpham["price"]=$row["price"];
			$sanpham["image"]=$row["image"];
			$sanpham["description"]=$row["description"];
			$sanpham["create_date"]=$row["create_date"];
			$sanpham["update_date"]=$row["update_date"];
			
			//dua san pham vao mang
			array_push($json["sanpham"],$sanpham);
		}
	}
	else //khong co san pham
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="khong co san pham";
	}
	echo json_encode($json);

?>
