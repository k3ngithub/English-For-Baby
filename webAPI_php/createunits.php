<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['unit_name'])&& $_POST['unit_name']!='')
	{
		$unit_name=$_POST['unit_name'];
		$unit_img=$_POST['unit_img'];
		$lesson1_img=$_POST['lesson1_img'];
		$lesson1=$_POST['lesson1'];
		$lesson2_img=$_POST['lesson2_img'];
		$lesson2=$_POST['lesson2'];
		$lesson3_img=$_POST['lesson3_img'];
		$lesson3=$_POST['lesson3'];
		$result=$db->storeUnit($unit_name,$unit_img,$lesson1_img,$lesson1,$lesson2_img,$lesson2,$lesson3_img,$lesson3);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="tao bai hoc thanh cong";
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
		$json["thongbao"]="chua nhap ten bai hoc";
	}
	
	echo json_encode($json);

?>
