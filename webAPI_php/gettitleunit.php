<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	$result=$db->getTitleUnit();
	if(mysqli_num_rows($result)>0)//co san pham
	{
		$json["thanhcong"]=1;
		$json["unit"]=array(); //mang con
		
		//duyet tat ca san pham dua vao json
		while($row=mysqli_fetch_array($result))
		{
			$unit=array();
			$unit["id"]=$row["id"];
			$unit["unit_name"]=$row["unit_name"];
			$unit["unit_img"]=$row["unit_img"];
		
			//dua san pham vao mang
			array_push($json["unit"],$unit);
		}
	}
	else //khong co san pham
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="khong co bai hoc";
	}
	echo json_encode($json);

?>
