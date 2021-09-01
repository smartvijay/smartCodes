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

$stmt = "SELECT internal_title,  sub_code_1,  sub_dis_1,  sub_mark_1,  sub_result_1,  sub_code_2,  sub_dis_2,  sub_mark_2,  sub_result_2,
             sub_code_3,  sub_dis_3,  sub_mark_3,  sub_result_3,  sub_code_4,  sub_dis_4,  sub_mark_4,  sub_result_4,
             sub_code_5,  sub_dis_5,  sub_mark_5,  sub_result_5,  sub_code_6,  sub_dis_6,  sub_mark_6,  sub_result_6 FROM internal ";
$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

    array_push($response,array(

        'internal_title' => $row[0],
        'sub_code_1'=> $row[1],
        'sub_dis_1'=> $row[2],
        'sub_mark_1'=> $row[3],
        'sub_result_1'=> $row[4],

        'sub_code_2'=> $row[5],
        'sub_dis_2'=> $row[6],
        'sub_mark_2'=> $row[7],
        'sub_result_2'=> $row[7],

        'sub_code_3'=> $row[9],
        'sub_dis_3'=> $row[10],
        'sub_mark_3'=> $row[11],
        'sub_result_3'=> $row[12],

        'sub_code_4'=> $row[13],
        'sub_dis_4'=> $row[14],
        'sub_mark_4'=> $row[15],
        'sub_result_4'=> $row[16],

        'sub_code_5'=> $row[17],
        'sub_dis_5'=> $row[18],
        'sub_mark_5'=> $row[19],
        'sub_result_5'=> $row[20],

        'sub_code_6'=> $row[21],
        'sub_dis_6'=> $row[22],
        'sub_mark_6'=> $row[23],
        'sub_result_6'=> $row[24]));



}
// Parsing DB data to Json Format
$jsonData = json_encode(array('internal' => $response));
echo $jsonData;
mysqli_close($conn);
?>
