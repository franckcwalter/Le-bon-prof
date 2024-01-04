<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../../config/database.php';
include_once '../../class/user.php';
$database = new Database();
$db = $database->getConnection();
$items = new User($db);
$stmt = $items->getUsers();
$itemCount = $stmt->rowCount();

// echo json_encode($itemCount);
if ($itemCount > 0) {

    $userArr = array();
    $userArr["users"] = array();
    $userArr["itemCount"] = $itemCount;
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        extract($row);
        $e = array(
            "id" => $id,
            "email" => $email,
            "first_name" => $first_name,
            "idRole" => $idRole
        );
        array_push($userArr["users"], $e);
    }
    echo json_encode($userArr);
} else {
    http_response_code(404);
    echo json_encode(
        array("message" => "No record found.")
    );
}
