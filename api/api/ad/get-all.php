<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once  '../../config/database.php';
include_once  '../../class/ad.php';

$database = new Database();
$db = $database->getConnection();
$items = new Ad($db);


$idloggedInUser = isset($_GET['idUser']) ? $_GET['idUser'] : die();


$stmt = $items->getAds($idloggedInUser);
$itemCount = $stmt->rowCount();


// echo json_encode($itemCount);
if ($itemCount > 0) {

    $adArr = array();
    $adArr["ads"] = array();
    $adArr["itemCount"] = $itemCount;
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        extract($row);
        $e = array(
            "id" => $id,
            "ad_reference" => $ad_reference,
            "title" => $title,
            "photo" => $photo,
            "description" => $description,
            "place" => $place,
            "location" => $location,
            "price" => $price,
            "created_at" => $created_at,
            "approved" => $approved,
            "idUser" => $idUser,
            "first_name" => $first_name,
            "isFav" => $isFav
        );
        array_push($adArr["ads"], $e);
    }
    echo json_encode($adArr);
} else {
    http_response_code(404);
    echo json_encode(
        array("message" => "No record found.")
    );
}
