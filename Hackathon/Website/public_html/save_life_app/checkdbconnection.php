<?php

include 'mysqldbconnection.php' ;


if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    die();
}
else{
     echo "Connected successfully";
}

?>