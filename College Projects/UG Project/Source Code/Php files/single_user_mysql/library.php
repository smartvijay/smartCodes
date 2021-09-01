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

$stmt = "SELECT book_title, book_ac_no, book_resource_type, book_issue_date, book_due_date, book_return_date, book_status FROM library ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'book_title'=> $row[0],
        'book_ac_no'=> $row[1],
        'book_resource_type'=> $row[2],
        'book_issue_date'=> $row[3],
        'book_due_date'=> $row[4],
        'book_return_date'=> $row[5],
        'book_status'=> $row[6]));


}
// Parsing DB data to Json Format
$jsonData = json_encode(array('library' => $response));
echo $jsonData;
mysqli_close($conn);
?>
