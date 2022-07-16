<?php
include_once 'dbconnect.php';
$query = "SELECT * FROM gedung";
$execute = mysqli_query($con,$query);
$check = mysqli_affected_rows($con);
$response= array();
if ($check>0) {
    $data = array();
    while ($retrieve= mysqli_fetch_object($execute)){
        $bind["id"]= $retrieve -> id;
        $bind["nama"]= $retrieve -> nama;
        $bind["alamat"]= $retrieve -> alamat;
        $bind["harga"]= $retrieve -> harga;
        $bind["keterangan"]= $retrieve -> keterangan;
        $bind["gambar"]= $retrieve -> gambar;
        $bind["no_telp"]= $retrieve -> no_telp;
        array_push($response, $bind);
    }
}else {
    $response["code"]= 0;
    $response["message"]= "data gedung tidak ada";
}
echo json_encode($response);
mysqli_close($con);
?>