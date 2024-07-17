package com.interswitchng.paymate.mtn_tig_requery_server;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> fetchStatus(){
        SecureRandom random = new SecureRandom();
        int type = random.nextInt(4);
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
