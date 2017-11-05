<?php
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	$result=$db->getAllExam();
	if(mysqli_num_rows($result)>0)//co san pham
	{
		$json["thanhcong"]=1;
		$json["exam"]=array(); //mang con
		
		//duyet tat ca san pham dua vao json
		while($row=mysqli_fetch_array($result))
		{
			$exam=array();
			$exam["id"]=$row["id"];
			$exam["image"]=$row["image"];
			$exam["questions"]=$row["questions"];
			$exam["answer1"]=$row["answer1"];
			$exam["answer2"]=$row["answer2"];
			$exam["resultans"]=$row["resultans"];
			$exam["score"]=$row["score"];
		
			//dua san pham vao mang
			array_push($json["exam"],$exam);
		}
	}
	else //khong co san pham
	{
		$json["thanhcong"]=0;
		$json["thongbao"]="khong co bai kiem tra";
	}
	echo json_encode($json);

?>
