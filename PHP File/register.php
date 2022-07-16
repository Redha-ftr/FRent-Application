<?php

include 'dbconnect.php';

if($_POST){

    //POST DATA
    $nama = filter_input(INPUT_POST, 'nama', FILTER_SANITIZE_STRING);
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);
    $telepon = filter_input(INPUT_POST, 'telepon', FILTER_SANITIZE_STRING);
    

    $response = [];
    
    $userQuery = $con->query("SELECT * FROM user where username = '$username'");

    // Cek username apakah ada atau tidak
    if(mysqli_num_rows($userQuery) > 0){
        // Beri Response
        $response['status']= false;
        $response['message']='Akun sudah digunakan';
    } else {

        try{
            
            $query = "INSERT INTO user (nama,username,password, no_telp) VALUES ('$nama', '$username', '$password', '$telepon')";
            $execute = mysqli_query($con,$query);
            $check = mysqli_affected_rows($con);
            if($check>0) {
                $response["status"] = true;
                $response["message"] = "Register berhasil";
                $response['data'] = [
                    'nama' => $nama,
                    'username' => $username
                ];
            }else {
                $response["status"] = false;
                $response["message"] = "Register gagal";
            }
    
        } catch (Exception $e){
            die($e->getMessage());
        }

    }
    
    //Jadikan data JSON
    $json = json_encode($response, JSON_PRETTY_PRINT);

    //Print JSON
    echo $json;
}
