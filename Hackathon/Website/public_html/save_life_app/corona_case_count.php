<?php

header('Content-Type: application/json');
//database constants
define('DB_HOST', 'localhost');
define('DB_USER', 'id13752670_user');
define('DB_PASS', 'Vijay$$$12345');
define('DB_NAME', 'id13752670_corona');


//connecting to database and getting the connection object
$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

//Checking if any error occured while connecting
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    die();
}

$stmt = "SELECT total, recovery, death FROM cases";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'total'=> $row[0],
        'recovery'=> $row[1],
        'death'=> $row[2]));
}
// Parsing DB data to Json Format
$jsonData = json_encode(array('cases' => $response));
echo $jsonData;
mysqli_close($conn);
?>
