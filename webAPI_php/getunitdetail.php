<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['id'])&& $_POST['id']!='')//co id
	{
		$id=$_POST['id'];
		$result=$db->getUnitDetail($id);
		
		//co du lieu
		if(!empty($result) && mysqli_num_rows($result))
		{
			$result=mysqli_fetch_array($result);
			$unit=array();
			$unit["id"]=$result["id"];
			$unit["unit_name"]=$result["unit_name"];
			$unit["unit_img"]=$result["unit_img"];
			$unit["lesson1_img"]=$result["lesson1_img"];
			$unit["lesson1"]=$result["lesson1"];
			$unit["lesson2_img"]=$result["lesson2_img"];
			$unit["lesson2"]=$result["lesson2"];
			$unit["lesson3_img"]=$result["lesson3_img"];
			$unit["lesson3"]=$result["lesson3"];
			
			//ghi vao json
			$json["thanhcong"]=1;
			$json["unit"]=array();
			array_push($json["unit"],$unit);
		}
		else//khong co du lieu
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="khong co bai hoc";
		}
		
	}
	else //khong co post id
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="khong co id de lay";
	}
	
	echo json_encode($json);

?>
