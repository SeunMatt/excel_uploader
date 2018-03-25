<?php

/**
 * Created by Seun Matt
 * Date: 20-Jan-18
 * Time: 5:17 AM
 */
class BulkDataHandler {

    private $mysqli;

    public function __construct() {
        $this->initMysql();
    }

    private function initMysql() {
        $this->mysqli = new mysqli("localhost", "root", "root", "test", 3306);
        if($this->mysqli->connect_errno) {
            throw new Exception("Error Connecting to the Database " . $this->mysqli->connect_error . " " . $this->mysqli->connect_errno);
        }

        //disable autocommit for now
        $this->mysqli->autocommit(false);
    }

    private function cleanUp() {
        $this->mysqli->autocommit(true);
        $this->mysqli->close();
    }

    private function respond($message, $code = null) {
        header("Content-Type: application/json");
        http_response_code((!is_null($code)) ? $code : 200);
        echo $message;
        exit;
    }

    public function uploadUserData($data, $columnMap) {

        $errorArray = [];

        //first grab the first data from the array and then do some validation
        //we are expecting three columns mappings and shouldn't be less
        //alternatively you can check each column mappings just to be double sure
        if(count($data[0]) < 3 || count($columnMap) < 3) {
            $this->respond(json_encode([
              "error" => "The Data Seems not to be complete! " . count($columnMap) . " columns were mapped instead of 3"]), 200);
        }

        //this is where the data will be handled and process
        foreach ($data as $datum) {
           if(!empty($datum) && !$this->doUserUpload($datum,  $columnMap)) {
             //that means there was an error processing the file
             //so we will just add this $datum to the errorArray
             array_push($errorArray, $datum);
           }
        }

        //now that we've process all the data let's return to the client
        $this->cleanUp();

        if(!empty($errorArray)) {
            //there was an error somewhere. Let's send the user some data that are affected.
            $this->respond(json_encode(["data" => $errorArray]));
        } else{
            $this->respond(json_encode(["success" => "OK"]));
        }
    }

    private function doUserUpload(array $data, $columnMap) {

        if(empty($data) || empty($columnMap)) {
            //log an error message here or do something else
            return false;
        }

        //start a transaction
        $this->mysqli->begin_transaction();

        try {

            //ATTEMPT INSERTING DATA INTO THE DATABASE
            $query  = "INSERT INTO `test`.`users` (`email`, `password`, `name`, `phone`) VALUES ( "
                       . "'".$data[$columnMap['email']] . "',"
                       . "'". password_hash(random_bytes(8), PASSWORD_BCRYPT) . "',"
                       . "'". $data[$columnMap['name']] . "',"
                       . "'". $data[$columnMap['phone_number']] . "'".
                       " )";

            $res = $this->mysqli->query($query);

            //perform some other intensive operations like
            //queueing an email
            //setting up the user subscription plan and other stuffs

            //finally return true if the commit is successful or false otherwise
            //this could have been simplified as return $this->mysqli->commit();
            //but for the sake of the obvious let's show it this way

            if(!$res || !$this->mysqli->commit()) {
                //log the error message $this->mysqli->error, before rolling back;
                $this->mysqli->rollback();
                return false;
            } else {
                //all is well with this data
                return true;
            }
        } catch (\Exception $e) {
            //oops an error has occurred. Maybe data conflicts or some others
            //log error messages from the $e object and then rollback and definitely return false
            echo "Exception While Processing " . $e->getMessage();
            $this->mysqli->rollback();
            return false;
        }

    }

}