package com.interswitchng.paymate.mtn_tig_requery_server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@RestController
@RequestMapping("/api/transaction")
public class TigController {

    @PostMapping(value = "/airtime", produces = "application/json")
    public ResponseEntity<?> producePendingAirtimeTransaction(){
        String json = Util.createPendingAirtimeTransaction();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    @PostMapping(value = "/data", produces = "application/json")
    public ResponseEntity<?> producePendingDataTransaction(){
        String json = Util.createPendingDataTransaction();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    @GetMapping(value = "/{type}", produces = "application/json")
    public ResponseEntity<?> fetchStatus(@PathVariable("type") int type){
        String json = null;
        if (type == 0){
            json = Util.createSuccessRequeryObject();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else if (type == 1) {
            json = Util.createFailedRequeryObject();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else if (type == 2){
            json = Util.createErrorRequeryObject();
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
