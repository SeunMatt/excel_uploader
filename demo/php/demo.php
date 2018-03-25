<?php

    require "BulkDataHandler.php";

    $rawData = json_decode(file_get_contents("php://input"), true);
//    print_r($rawData);

    $data = $rawData['data'];
    $columnMap = $rawData['column_map'];

    $bulkDataHandler = new BulkDataHandler();
    $bulkDataHandler->uploadUserData($data, $columnMap);