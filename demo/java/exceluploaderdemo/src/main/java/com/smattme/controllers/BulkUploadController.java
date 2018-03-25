package com.smattme.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smattme.service.BulkUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Seun Matt on 24-Mar-18
 */
@Controller
public class BulkUploadController {

    private Logger logger = Logger.getLogger(BulkUploadController.class.getName());
    private BulkUploadService bulkUploadService;

    @Autowired
    public BulkUploadController(BulkUploadService bulkUploadService) {
        this.bulkUploadService = bulkUploadService;
    }

    @GetMapping(value = {"/", ""})
    public String index() {
        return "index";
    }

    @PostMapping(value = {"/process"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity processData(@RequestBody String rawJson) {


        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseMap = new HashMap<>();

        try {

            //parse the raw JSON
            JsonNode node = mapper.readTree(rawJson);

            //parse the column_map as a Map
            Map columnMap = mapper.readValue(node.get("column_map").toString(), Map.class);

            //parse the data as a List of List
            List<List<String>> data = mapper.readValue(node.get("data").toString(), new TypeReference<List<List<String>>>(){});

            //process the data and store any errors that might have arise
            List<List<String>> errors = bulkUploadService.uploadUserData(columnMap, data);


            if(errors.isEmpty()) {
                //all is well during the process
                responseMap.put("success", "OK");
                return ResponseEntity.ok(responseMap);
            }

            //there are errors and some data are not processed.
            // Let's send those data back to the client
            //note that we're sending the OK response 200
            //it's just that we're appending some error data that might have been present
            responseMap.put("data", errors);
            return ResponseEntity.ok(mapper.writeValueAsString(responseMap));

        } catch (IOException e) {
            e.printStackTrace();
            //something bad really went wrong
            responseMap.put("error", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap);
        }
    }

}
