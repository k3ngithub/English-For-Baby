<?php
	class DB_Functions{
		public  $db;
		
		function __construct(){
			require_once("DB_Connect.php");
			$this->db=new DB_Connect();
			$this->db->connect();
		}
		
		function __destruct()
		{
		}
		
		//luu user va database
		public function storeUser($avatar, $fullname, $username, $password, $highscore, $account_type){
			$sql="INSERT INTO user
				(avatar,fullname,username,password,highscore,account_type)VALUES 
				('$avatar','$fullname','$username', '$password', '$highscore','$account_type')";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			

			if($result==true){
				//chu y: phai dung chung connect thi moi lay duoc id
				$id=mysqli_insert_id($con);//id cuoi cung cung la dulieu vua them

				$result=mysqli_query($this->db->connect(),"select * from user where id='$id'");
				return mysqli_fetch_array($result);
			}
			else
				return false;
		}
		
		//lay thong tin user dua vao email va password
		public function getUser($username,$password){
			$sql="select * from user where username='$username' and password='$password'";
			$result=mysqli_query($this->db->connect(),$sql) ;
			$rows=mysqli_num_rows($result);//lay so hang
			if($rows>0) //neu co hang tuc la co user
			{
				$result=mysqli_fetch_array($result);
				//echo "co user ne";
				return $result;
			}
			else //khong co user
			{
				//echo "khong co user ne ba";
				return false;
			}
		}
		
		//kiem tra email da co nguoi dung chua
		public function checkUser($username){
			$sql="select * from user where username='$username'";
			$this->db->close();
			$con=$this->db->connect();
			//$result=mysqli_query($this->db->connect(),$sql);
			$result=mysqli_query($con,$sql);
			$rows=mysqli_num_rows($result);//lay so hang
		
			if($rows>0) 
			
				return true; //user da ton tai
			else
				return false; //chua co user nay
		}
		
			//ham ghi bai hoc vao database
		public function storeUnit($unit_name,$unit_img,$lesson1_img,$lesson1,$lesson2_img,$lesson2,$lesson3_img,$lesson3){
			$sql="insert into unit
			(unit_name,unit_img,lesson1_img,lesson1,lesson2_img,lesson2,lesson3_img,lesson3) values
			('$unit_name','$unit_img','$lesson1_img','$lesson1','$lesson2_img','$lesson2','$lesson3_img','$lesson3',NOW())";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}
		//Exam 
		public function storeExam($image,$questions,$answer1,$answer2,$resultans,$score){
			$sql="insert into exam
			(image,questions,answer1,answer2,resultans,score) values
			('$image','$questions','$answer1','$answer2','$resultans','$score',NOW())";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}
		
		
		//ham lay chi tiet san pham dua vao id
		public function getUnitDetail($id){
			$sql="select * from unit where id='$id'";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}


	//ham lay tat ca cac san pham
		public function getAllUnit(){
			$sql="select * from unit";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}
		//Exam getall
		public function getAllExam(){
			$sql="select * from exam";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}
		//get Title Unit
		public function getTitleUnit(){
			$sql="select id, unit_name, unit_img from unit";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}

		public function updateUnit($unit_name,$unit_img,$lesson1_img,$lesson1,$lesson2_img,$lesson2,$lesson3_img,$lesson3){
			$sql="update unit set unit_name='$unit_name',
				unit_img='$unit_img',
				lesson1_img='$lesson1_img',
				lesson1='$lesson1',
				lesson2_img='$lesson2_img',
				lesson2='$lesson2',
				lesson3_img='$lesson3_img',
				lesson3='$lesson3',
				update_date=NOW() where id='$id'";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}
		//Exam exam
			public function updateExam($image,$questions,$answer1,$answer2,$resultans,$score){
			$sql="update exam set image='$image',
				questions='$questions',
				answer1='$answer1',
				answer2='$answer2',
				resultans='$resultans',
				score='$score',,
				update_date=NOW() where id='$id'";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			return $result;
		}

		//ham xoa mot bai hoc
		public function deleteUnit($id){
			$sql="delete from unit where id='$id' ";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			
			//ham affected_rows tra ve so record bi
			//anh huong boi cau lenh insert, update,delete
			return mysqli_affected_rows($con);
		}
		//Exam delete
		public function deleteExam($id){
			$sql="delete from exam where id='$id' ";
			$con=$this->db->connect();
			$result=mysqli_query($con,$sql);
			
			//ham affected_rows tra ve so record bi
			//anh huong boi cau lenh insert, update,delete
			return mysqli_affected_rows($con);
		}

	}
?>