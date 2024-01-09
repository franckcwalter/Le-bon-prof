<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../config/database.php';
include_once '../../class/ad.php';

$database = new Database();
$db = $database->getConnection();

$item = new Ad($db);

$item->id = isset($_GET['idAd']) ? $_GET['idAd'] : die();
$item->idUser = isset($_GET['idUser']) ? $_GET['idUser'] : die();



if ($item->toggleFav()) {
    echo json_encode(array("status" => "1"));
} else {
    echo json_encode(array("status" => "0"));
}
