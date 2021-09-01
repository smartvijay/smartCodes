<?php

if (isset($_POST['name']) && isset($_POST['mobile']) && isset($_POST['email']) && isset($_POST['role'])
             && isset($_POST['gender']) && isset($_POST['purpose']) && isset($_POST['date'])
              && isset($_POST['country']) && isset($_POST['state']) && isset($_POST['city'])
              && isset($_POST['pin']) && isset($_POST['address'])) {
  
    include("app/db_connection.php");
    
    $response = array();
  
    $name     = $_POST['name'];
    $mobile      = $_POST['mobile'];
    $email   = $_POST['email'];
    $role       = $_POST['role'];
    $gender     = $_POST['gender'];
    $purpose      = $_POST['purpose'];
    $date   = $_POST['date'];
    $country       = $_POST['country'];
    $state     = $_POST['state'];
    $city      = $_POST['city'];
    $pin   = $_POST['pin'];
    $address       = $_POST['address'];

    $query = "INSERT INTO epass(name,mobile,email,role,gender,purpose,date,country,state,city,pin,address) VALUES('$name','$mobile','$email','$role','$gender','$purpose','$date','$country','$state','$city','$pin','$address')";
    $result = $conn->query($query) or die($conn->error . __LINE__);
    
    if (!$result) {
        $response['success'] = true;
        $response['data']    = "Fail";
    } else {
        $lastInsertId          = $conn->insert_id;
        $response['success'] = true;
        $response['data']    = $lastInsertId;
    }
    
    print  "<h1 align=center> Thanks! You have successfully registered</h1>" ; 
    print  "<h1 align=center> State : Tamilnadu </h1>" ; 
    print  "<h1 align=center> Track ID : $lastInsertId </h1>" ;   
}
?>