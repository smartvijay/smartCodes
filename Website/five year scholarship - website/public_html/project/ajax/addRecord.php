<?php

if (isset($_POST['name']) && isset($_POST['gender']) && isset($_POST['age']) && isset($_POST['cc'])) {
  
    include("app/db_connection.php");
    
    $response = array();
  
    $name     = $_POST['name'];
    $age      = $_POST['age'];
    $gender   = $_POST['gender'];
    $cc       = $_POST['cc'];

    $query = "INSERT INTO tbl_covid_participant(name,age,gender,cc) VALUES('$name','$age','$gender','$cc')";
    $result = $conn->query($query) or die($conn->error . __LINE__);
    
    if (!$result) {
        $response['success'] = true;
        $response['data']    = "Fail";
    } else {
        $lastInsertId          = $conn->insert_id;
        $response['success'] = true;
        $response['data']    = $lastInsertId;
    }
    
    echo json_encode($response);
}
?>