<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	$result=$db->getAllUnit();
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
			$unit["lesson1_img"]=$row["lesson1_img"];
			$unit["lesson1"]=$row["lesson1"];
			$unit["lesson2_img"]=$row["lesson2_img"];
			$unit["lesson2"]=$row["lesson2"];
			$unit["lesson3_img"]=$row["lesson3_img"];
			$unit["lesson3"]=$row["lesson3"];
			
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
