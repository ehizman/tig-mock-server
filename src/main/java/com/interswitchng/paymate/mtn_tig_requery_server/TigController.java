package com.interswitchng.paymate.mtn_tig_requery_server;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TigController {
    private Logger log = LoggerFactory.getLogger(TigController.class);

    @PostMapping(value = "/airtime", produces = "application/json")
    public ResponseEntity<?> fetchPendingAirtimeTransaction(){
        String json = Util.createPendingAirtimeTransaction();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    @PostMapping(value = "/data", produces = "application/json")
    public ResponseEntity<?> fetchPendingDataTransaction(){
        String json = Util.createPendingDataTransaction();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping(value = "transaction-by-ref/{id}", produces = "application/json")
    public ResponseEntity<?> fetchStatus(@PathVariable("id") String  id){
        String json = Util.createSuccessRequeryObject(id);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    @GetMapping(value = "transaction/success/{id}", produces = "application/json")
    public ResponseEntity<?> fetchSuccessStatus(@PathVariable("id") String  id){
        String json = Util.createSuccessRequeryObject(id);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping(value = "transaction/failed/{id}", produces = "application/json")
    public ResponseEntity<?> fetchFailedStatus(@PathVariable("id") String  id){
        String json = Util.createFailedRequeryObject(id);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping(value = "transaction/not-found/{id}", produces = "application/json")
    public ResponseEntity<?> fetchNotFoundStatus(@PathVariable("id") int id){
        String json = Util.createNotFoundRequeryObject();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping(value = "transaction/error/{id}", produces = "application/json")
    public ResponseEntity<?> fetchErrorStatus(@PathVariable("id") int id){
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/vend")
    public ResponseEntity<?> confirmStatus(HttpServletRequest request){
        log.info("Request --> {}", request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
