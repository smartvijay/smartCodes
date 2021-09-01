<?php

header('Content-Type: application/json');
//database constants
define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'student_details');

//connecting to database and getting the connection object
$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

//Checking if any error occured while connecting
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    die();
}

$stmt = "SELECT absent_title_1, leave_from,leave_to,reason FROM absent ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'absent_title_1' => $row[0],
        'leave_from'=> $row[1],
        'leave_to'=> $row[2],
        'reason'=> $row[3]));


}
// Parsing DB data to Json Format
$jsonData = json_encode(array('absent' => $response));
echo $jsonData;
mysqli_close($conn);
?>
