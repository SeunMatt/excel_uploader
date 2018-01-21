<?php

    require "BulkDataHandler.php";

//    echo "SERVER HIT! " . $_SERVER["REQUEST_METHOD"] . " <br>"x
//    echo "HEADER " . json_encode(getallheaders()) . "<br>";

 //   echo(file_get_contents("php://input"));

//    $rawData = json_encode(file_get_contents("php://input"));
//    $rawData = json_decode($rawData, true);
    $rawData = json_decode(file_get_contents("php://input"), true);

//    echo "<br><br>raw json<br>";


//    print_r($rawData);


//    echo json_encode($rawData);
//
    $data = $rawData['data'];
//
    $columnMap = $rawData['column_map'];

//    print_r($columnMap);

//
//    echo json_encode($data);
//
//    echo json_encode($columnMap);
//   exit();

    $bulkDataHandler = new BulkDataHandler();
    $bulkDataHandler->uploadUserData($data, $columnMap);