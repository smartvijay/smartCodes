<?php

include 'DatabaseConfig.php' ;
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $email = $_POST['email'];
 $password = $_POST['password'];
 

 $Sql_Query = "insert into tbl_login (email,password) values ('$email','$password')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Registered Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
 
?>