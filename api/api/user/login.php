<?php
header("Access-Control-Allow-Origin: https://fwadevidfinalproject.alwaysdata.net/");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../config/database.php';
include_once '../../class/user.php';

$database = new Database();
$db = $database->getConnection();

$item = new User($db);


$email = (isset($_POST['email'])) ? $_POST['email'] : die();
$password = (isset($_POST['password'])) ? $_POST['password'] : die();

$item->email = $email;

$item->getUserFromEmail();

$userArr = array();

if ($item->password == $password) {

    $userArr["status"] = 1;
    $userArr["user"] = array(
        "id" =>  $item->id,
        "email" => $item->email,
        "first_name" => $item->first_name,
        "idRole" => $item->idRole
    );
} else {
    $userArr["status"] = 0;
    $userArr["user"] = null;
}

echo json_encode($userArr);
