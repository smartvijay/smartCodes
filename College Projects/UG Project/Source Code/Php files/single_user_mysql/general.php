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

$stmt = "SELECT name, roll_no, admission_date, batch, course ,
 semester , department ,age, dob, blood_group, height, weight, health_status, gender, mother_tongue, nationality, religion, community, caste,
category, medium_of_study, address, city, state, country, pincode, mobile_no, father_name,f_occupation,f_monthly_income,f_contact_no,mother_name,m_occupation,
m_monthly_income,m_contact_no FROM general;";

$result = mysqli_query($conn,$stmt);
$response = array();

while ($row = mysqli_fetch_row($result)) {

        array_push($response, array(

            'name ' => $row[0],
            'roll_no' => $row[1],
            'admission_date' => $row[2],
            'batch' => $row[3],
            'course' => $row[4],
            'semester' => $row[5],
            'department' => $row[6],
            'age' => $row[7],
            'dob' => $row[8],
            'blood_group' => $row[9],
            'height' => $row[10],
            'weight' => $row[11],
            'health_status' => $row[12],
            'gender' => $row[13],
            'mother_tongue' => $row[14],
            'nationality' => $row[15],
            'religion' => $row[16],
            'community' => $row[17],
            'caste' => $row[18],
            'category' => $row[19],
            'medium_of_study' => $row[20],
            'address' => $row[21],
            'city' => $row[22],
            'state' => $row[23],
            'country' => $row[24],
            'pincode' => $row[25],
            'mobile_no' => $row[26],
            'father_name' => $row[27],
            'f_occupation' => $row[28],
            'f_monthly_income' => $row[29],
            'f_contact_no' => $row[30],
            'mother_name' => $row[31],
            'm_occupation' => $row[32],
            'm_monthly_income' => $row[33],
            'm_contact_no' => $row[34]));
}
// Parsing DB data to Json Format
$jsonData = json_encode(array('general' => $response));
echo $jsonData;
mysqli_close($conn);
?>
