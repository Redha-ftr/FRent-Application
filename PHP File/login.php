<?php

include_once 'dbconnect.php';

$response = array("error" => FALSE);

if ($_POST) {
 
    $username = $_POST["username"];
    $password = $_POST["password"];

    //$encrypted_password = hash("sha256", $password);// encrypted password
        
    $sql = $con->query("SELECT * FROM user WHERE username='$username' AND password='$password'");

    if(mysqli_num_rows($sql) > 0){
        while($row = $sql->fetch_array()){
            $response["status"] = TRUE;
            $response["message"] = "Login Berhasil";
            $response["data"]["id"] = $row['id'];
            $response["data"]["nama"] = $row['nama'];
            $response["data"]["username"] = $row['username'];
            $response["data"]["no_telp"] = $row['no_telp'];
        }

        echo json_encode($response);
   }else{
        $response["status"] = FALSE;
        $response["message"] = "Username atau Password salah!";

        echo json_encode($response);
   }
}

?>