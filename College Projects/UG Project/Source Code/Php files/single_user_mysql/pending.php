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

$stmt = "SELECT fees_title_1, year_1, semester_1, due_date_1, pending_amount_1,
            fees_title_2, year_2, semester_2, due_date_2, pending_amount_2, total FROM pending ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'fees_title_1'=> $row[0],
        'year_1'=> $row[1],
        'semester_1'=> $row[2],
        'due_date_1'=> $row[3],
        'pending_amount_1'=> $row[4],
        'fees_title_2'=> $row[5],
        'year_2'=> $row[6],
        'semester_2'=> $row[7],
        'due_date_2'=> $row[8],
        'pending_amount_2'=> $row[9],
        'total'=> $row[10]));


}
// Parsing DB data to Json Format
$jsonData = json_encode(array('pending' => $response));
echo $jsonData;
mysqli_close($conn);
?>
