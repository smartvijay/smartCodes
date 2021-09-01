<?php

$servername = "localhost";
$username = "id13752670_user";
$password = "Vijay$$$12345";
$dbname = "id13752670_corona";


try {
    	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }
catch(PDOException $e)
    {
    	die("OOPs something went wrong");
    }

?>


