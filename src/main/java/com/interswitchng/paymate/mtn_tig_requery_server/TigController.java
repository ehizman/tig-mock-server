package com.interswitchng.paymate.mtn_tig_requery_server;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TigController {
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> fetchStatus(){
        SecureRandom random = new SecureRandom();
        int type = random.nextInt(4);
        String json = null;
        if (type == 0){
            json = Util.createSuccessObject();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else if (type == 1) {
            json = Util.createFailedObject();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else if (type == 2){
            json = Util.createErrorObject();
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
