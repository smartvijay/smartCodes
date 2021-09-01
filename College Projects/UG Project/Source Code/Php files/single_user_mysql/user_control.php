<?php

include_once 'connection.php';

class User
{

    private $db;
    private $connection;

    function __construct()
    {
        $this->db = new DB_Connection();
        $this->connection = $this->db->getConnection();
    }

    public function does_user_exist($register_n, $password)
    {
        $query = "Select * from login where roll_no='$register_n' and password = '$password' ";
        $result = mysqli_query($this->connection, $query);
        if (mysqli_num_rows($result) > 0) {
            $json['success'] = ' Welcome ' . $register_n;
            echo json_encode($json);
            mysqli_close($this->connection);
        } else {
            $json['error'] = 'User Does Not Exist';
            echo json_encode($json);
            mysqli_close($this->connection);
        }

    }

}

$user = new User();
if (isset($_POST['roll_no'], $_POST['password'])) {
    $register_no = $_POST['roll_no'];
    $password = $_POST['password'];

    if (!empty($register_no) && !empty($password)) {

        $user->does_user_exist($register_no, $password);

    } else {
        $json['error'] = 'Fields are Empty';
        echo json_encode($json);
    }

}
?>
