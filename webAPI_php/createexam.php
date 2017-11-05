<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['image'])&& $_POST['image']!='')
	{
		$image=$_POST['image'];
		$questions=$_POST['questions'];
		$answer1=$_POST['answer1'];
		$answer2=$_POST['answer2'];
		$result=$_POST['resultans'];
		$score=$_POST['score'];
		$result=$db->storeExam($image,$questions,$answer1,$answer2,$resultans,$score);
		if($result)
		{
			$json["thanhcong"]=1;
			$json["thongbao"]="tao bai kiem tra thanh cong";
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
		$json["thongbao"]="chua nhap ten bai kiem tra";
	}
	
	echo json_encode($json);

?>
