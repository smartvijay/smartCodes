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

$stmt = "SELECT detaila_title_1 , date_1, period_1, subject_1, period_2, subject_2, period_3,
 subject_3, period_4, subject_4 FROM details;";


$result = mysqli_query($conn, $stmt);
$response = array();


while ($row = mysqli_fetch_row($result)) {

    array_push($response, array(

        'detaila_title_1' => $row[0],
        'date_1' => $row[1],
        'period_1' => $row[2],
        'subject_1' => $row[3],
        'period_2' => $row[4],
        'subject_2' => $row[5],
        'period_3' => $row[6],
        'subject_3' => $row[7],
        'period_4' => $row[8],
        'subject_4' => $row[9]));



}
// Parsing DB data to Json Format
$jsonData = json_encode(array('details' => $response));
echo $jsonData;
mysqli_close($conn);
?>
