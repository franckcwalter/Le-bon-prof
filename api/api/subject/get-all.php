<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../../config/database.php';
include_once '../../class/subject.php';
$database = new Database();
$db = $database->getConnection();
$items = new Subject($db);
$stmt = $items->getSubjects();
$itemCount = $stmt->rowCount();

// echo json_encode($itemCount);
if ($itemCount > 0) {

    $subjectArr = array();
    $subjectArr["subjects"] = array();
    $subjectArr["itemCount"] = $itemCount;
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        extract($row);
        $e = array(
            "id" => $id,
            "code" => $code,
            "name" => $name
        );
        array_push($subjectArr["subjects"], $e);
    }
    echo json_encode($subjectArr);
} else {
    http_response_code(404);
    echo json_encode(
        array("message" => "No record found.")
    );
}
