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

$data = json_decode(file_get_contents("php://input"));

$item->ad_reference = $data->ad_reference;
$item->title = $data->title;
$item->photo = $data->photo;
$item->description = $data->description;
$item->place = $data->place;
$item->location = $data->location;
$item->price = $data->price;
$item->created_at = $data->created_at;
$item->approved = $data->approved;
$item->idUser = $data->idUser;


$lastInsertId = $item->createAd();

$adArr = array();

if ($lastInsertId > 0) {
    $adArr["status"] = 1;
    $adArr["id"] =  $lastInsertId;
} else {
    $adArr["status"] = 0;
    $adArr["id"] = null;
}

echo json_encode($adArr);
