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

$stmt = "SELECT summary_title_1,month_1,days_1,absent_1,present_1,month_2,days_2,absent_2,present_2,
month_3,days_3,absent_3,present_3,total,days_4,absent_4,present_4,present,days_5,absent_5,present_5 FROM summary ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'summary_title_1' => $row[0],
        'month_1' => $row[1],
        'days_1' => $row[2],
        'absent_1' => $row[3],
        'present_1' => $row[4],
        'month_2' => $row[5],
        'days_2' => $row[6],
        'absent_2' => $row[7],
        'present_2' => $row[8],
        'month_3' => $row[9],
        'days_3' => $row[10],
        'absent_3' => $row[11],
        'present_3' => $row[12],
        'total' => $row[13],
        'days_4' => $row[14],
        'absent_4' => $row[15],
        'present_4' => $row[16],
        'present' => $row[17],
        'days_5' => $row[18],
        'absent_5' => $row[19],
        'present_5'  => $row[20]));



}
// Parsing DB data to Json Format
$jsonData = json_encode(array('summary' => $response));
echo $jsonData;
mysqli_close($conn);
?>
