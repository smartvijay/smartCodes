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

$stmt = "SELECT fees_title_1, receipt_no, date_1, year_1, amount_1, net_amount_1 FROM receipt ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'fees_title_1' => $row[0],
        'receipt_no' => $row[1],
        'date_1' => $row[2],
        'year_1' => $row[3],
        'amount_1' => $row[4],
        'net_amount_1' => $row[5]));


}
// Parsing DB data to Json Format
$jsonData = json_encode(array('receipt' => $response));
echo $jsonData;
mysqli_close($conn);
?>
