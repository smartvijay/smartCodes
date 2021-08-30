<?php

if (isset($_POST['name']) && isset($_POST['mobile']) && isset($_POST['email']) && isset($_POST['role'])
    && isset($_POST['gender']) && isset($_POST['problem']) && isset($_POST['date'])
    && isset($_POST['country']) && isset($_POST['state']) && isset($_POST['city'])
     && isset($_POST['pin']) && isset($_POST['address'])) {
  
    include("app/db_connection.php");
    
    $response = array();
  
    $name     = $_POST['name'];
    $mobile      = $_POST['mobile'];
    $email   = $_POST['email'];
    $role       = $_POST['role'];
    $gender     = $_POST['gender'];
    $problem      = $_POST['problem'];
    $date   = $_POST['date'];
    $country      = $_POST['country'];
    $state  = $_POST['state'];
    $city      = $_POST['city'];
    $pin   = $_POST['pin'];
    $address       = $_POST['address'];

    $query = "INSERT INTO present_student_details(name,mobile,email,role,gender,problem,date,country,state,city,pin,address) VALUES('$name','$mobile','$email','$role','$gender','$problem','$date','$country','$state','$city','$pin','$address')";
    $result = $conn->query($query) or die($conn->error . __LINE__);
    
    if (!$result) {
        $response['success'] = true;
        $response['Reference ID']    = "Fail";
    } else {
        $lastInsertId          = $conn->insert_id;
        $response['successfylly Registered'] = true;
        $response['Reference ID']    = $lastInsertId;
    }
    
    echo json_encode($response);
}
?>