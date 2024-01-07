<?php
header("Access-Control-Allow-Origin: https://fwadevidfinalproject.alwaysdata.net/");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once  '../../config/database.php';
include_once  '../../class/ad.php';

$database = new Database();
$db = $database->getConnection();

$item = new Ad($db);

// $data = json_decode(file_get_contents("php://input"));
// $item->id = $data->id;

$item->id = isset($_GET['id']) ? $_GET['id'] : die();

if ($item->deleteAd()) {
    echo json_encode(array("status" => "1"));
} else {
    echo json_encode(array("status" => "0"));
}
