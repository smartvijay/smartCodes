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

$stmt = "SELECT onduty_title_1, from_date, to_date, total_days, period, subject, reason FROM onduty ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'onduty_title_1' => $row[0],
        'from_date'=> $row[1],
        'to_date'=> $row[2],
        'total_days'=> $row[3],
        'period'=> $row[4],
        'subject'=> $row[5],
        'reason'=> $row[6]));


}
// Parsing DB data to Json Format
$jsonData = json_encode(array('onduty' => $response));
echo $jsonData;
mysqli_close($conn);
?>
