<?php

include 'DatabaseConfig.php' ;
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $name = $_POST['name'];
 $mobile = $_POST['mobile'];
 $email = $_POST['email'];
 $role = $_POST['role'];
 $gender = $_POST['gender'];
 $purpose = $_POST['purpose'];
 $date = $_POST['date'];
 $country = $_POST['country'];
 $state = $_POST['state'];
 $city = $_POST['city'];
 $pin = $_POST['pin'];
 $address = $_POST['address'];

 $Sql_Query = "insert into epass (name,mobile,email,role,gender,purpose,date,country,state,city,pin,address) values ('$name','$mobile','$email','$role','$gender','$purpose','$date','$country','$state','$city','$pin','$address')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Registered Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
 
?>