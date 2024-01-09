<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
include_once  '../../config/database.php';
include_once  '../../class/ad.php';
$database = new Database();
$db = $database->getConnection();
$item = new Ad($db);

$item->id = isset($_GET['id']) ? $_GET['id'] : die();

$idloggedInUser = isset($_GET['idUser']) ? $_GET['idUser'] : die();

$item->getAd($idloggedInUser);
if ($item->ad_reference != null) {

    // create array
    $emp_arr = array(
        "id" => $item->id,
        "ad_reference" => $item->ad_reference,
        "title" => $item->title,
        "photo" => $item->photo,
        "description" => $item->description,
        "place" => $item->place,
        "location" => $item->location,
        "price" => $item->price,
        "created_at" => $item->created_at,
        "approved" => $item->approved,
        "idUser" => $item->idUser,
        "first_name" => $item->first_name,
        "isFav" => $item->isFav
    );

    http_response_code(200);
    echo json_encode($emp_arr);
} else {
    http_response_code(404);
    echo json_encode("Ad not found.");
}
