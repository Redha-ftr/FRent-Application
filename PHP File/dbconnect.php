<?php
define('DB_HOST', 'fdb32.awardspace.net');
define('DB_USER', '4134504_frent');
define('DB_PASS', ']nw)i9#P0^}Z%eA!');
define('DB_NAME', '4134504_frent');
$con = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);


if(mysqli_connect_errno()){
    echo "Failed to connect to MySQL Server".mysqli_connect_error();
    die();
}
?>
