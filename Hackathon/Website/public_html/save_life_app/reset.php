<?php

include 'DatabaseConfig.php' ;
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $email = $_POST['email'];
 

 $Sql_Query = "insert into reset (email) values ('$email')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Registered Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
 
?>