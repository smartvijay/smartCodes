<?php
include("app/db_connection.php");

$response = array();

$query = "select * from volunteer order by id";
$result = $conn->query($query) or die($conn->error . __LINE__);
$responseData = array();

if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $responseData[] = $row;
        }
        $response['success']  = true;
        $response['data']  = $responseData;
    }
    else {
    $response['success']  = true;
    $response['data'] = $responseData;
    }

echo json_encode($response);
?>