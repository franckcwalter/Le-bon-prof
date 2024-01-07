<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
include_once '../../config/database.php';
include_once '../../class/user.php';
$database = new Database();
$db = $database->getConnection();
$item = new User($db);

$data = json_decode(file_get_contents("php://input"));
$item->email = $data->email;
$item->password = $data->password;
$item->first_name = $data->first_name;
$item->idRole = $data->idRole;

$lastInsertId = $item->createUser();

$userArr = array();

if ($lastInsertId > 0) {
    //  echo json_encode(array("status" => "1"));

    $userArr["status"] = 1;
    $userArr["user"] = array(
        "id" =>  $lastInsertId,
        "email" => $item->email,
        "first_name" => $item->first_name,
        "idRole" => $item->idRole
    );
} else if ($lastInsertId == -1) {
    $userArr["status"] = -1;
    $userArr["user"] = null;
} else {
    $userArr["status"] = 0;
    $userArr["user"] = null;
}

echo json_encode($userArr);
