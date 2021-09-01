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

$stmt = "SELECT fees_title_1, year_1, semester, due_date_1, fees_amount_1, concession_amount_1, net_amount_1,
            fees_title_2, year_2, semester_2, due_date_2, fees_amount_2, concession_amount_2, net_amount_2,
            fees_title_3, year_3, semester_3, due_date_3, fees_amount_3, concession_amount_3, net_amount_3, total FROM fees ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'fees_title_1' => $row[0],
        'year_1' => $row[1],
        'semester' => $row[2],
        'due_date_1' => $row[3],
        'fees_amount_1' => $row[4],
        'concession_amount_1' => $row[5],
        'net_amount_1' => $row[6],
        'fees_title_2' => $row[7],
        'year_2' => $row[8],
        'semester_2' => $row[9],
        'due_date_2' => $row[10],
        'fees_amount_2' => $row[11],
        'concession_amount_2' => $row[12],
        'net_amount_2' => $row[13],
        'fees_title_3' => $row[14],
        'year_3' => $row[15],
        'semester_3' => $row[16],
        'due_date_3' => $row[17],
        'fees_amount_3' => $row[18],
        'concession_amount_3' => $row[19],
        'net_amount_3' => $row[20],
        'total' => $row[21]));


}
// Parsing DB data to Json Format
$jsonData = json_encode(array('fees' => $response));
echo $jsonData;
mysqli_close($conn);
?>
