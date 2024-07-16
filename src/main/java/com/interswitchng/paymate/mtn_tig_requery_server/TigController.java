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
    private Gson gson;

    public TigController() {
        gson = new Gson();
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> fetchStatus(){
        SecureRandom random = new SecureRandom();
        int type = random.nextInt(4);
        String json = null;
        if (type == 0){
            json = createSuccessObject();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else if (type == 1) {
            json = createFailedObject();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else if (type == 2){
            json = createErrorObject();
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    private String createErrorObject() {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Transaction not found");
        return gson.toJson(map);
    }

    private String createSuccessObject() {
        // Telco Log Map
        Map<String, Object> telcoLog = new HashMap<>();
        telcoLog.put("error", "");
        telcoLog.put("response_http_code", 200);
        telcoLog.put("response_status_code", "0");
        telcoLog.put("response_transaction_id", "2024071116413771609600345");
        telcoLog.put("status", "Successful");
        telcoLog.put("transaction_id", "3466630149949886464");

        // Transaction Map
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("transaction_id", "3466630149949886464");
        transaction.put("amount", 10000);
        transaction.put("network", "mtn");
        transaction.put("msisdn", "08164168842");
        transaction.put("type", "Data");
        transaction.put("data_bundle_code", "M0901");
        transaction.put("data_bundle_desc", "DataPlan 100MB Daily");
        transaction.put("external_ref", "224876");
        transaction.put("status", "Successful");
        transaction.put("status_code", "00");
        transaction.put("created_at", "2024-07-11T16:41:37.326+01:00");
        transaction.put("balance_before", 406800);
        transaction.put("commission_amount", 0);
        transaction.put("commission_to", "");
        transaction.put("refunded_at", null);

        // Data Map
        Map<String, Object> data = new HashMap<>();
        data.put("reversal_log", null);
        data.put("telco_log", telcoLog);
        data.put("transaction", transaction);

        // Main Map
        Map<String, Object> mainMap = new HashMap<>();
        mainMap.put("data", data);
        mainMap.put("message", "Transaction Successful");
        mainMap.put("status", "Successful");
        mainMap.put("status_code", "00");

        return gson.toJson(mainMap);
    }

    private String createFailedObject() {
        // Reversal Log Map
        Map<String, Object> reversalLog = new HashMap<>();
        reversalLog.put("amount", 2);
        reversalLog.put("action", "credit");
        reversalLog.put("transaction_id","3466922022484578304");
        reversalLog.put("transaction_type", "Airtime");
        reversalLog.put("transaction_msisdn", "09167226284");
        reversalLog.put("transaction_network", "mtn");
        reversalLog.put("description", "Rvsl: 3466922022484578304 - airtime purchase for 09167226284");
        reversalLog.put("balance_before", 396798);
        reversalLog.put("created_at", "2024-07-12T12:01:25.686+01:00");
        reversalLog.put("updated_at", "2024-07-12T12:01:25.686+01:00");

        // Telco Log Map
        Map<String, Object> telcoLog = new HashMap<>();
        telcoLog.put("error", "");
        telcoLog.put("response_http_code", 200);
        telcoLog.put("response_status_code", "1072");
        telcoLog.put("response_transaction_id", "2024071212012555110577646");
        telcoLog.put("status", "Failed");
        telcoLog.put("transaction_id", "3466922022484578304");

        // Transaction Map
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("transaction_id", "3466922022484578304");
        transaction.put("amount", 2);
        transaction.put("network", "mtn");
        transaction.put("msisdn", "09167226284");
        transaction.put("type", "Airtime");
        transaction.put("data_bundle_code", "");
        transaction.put("data_bundle_desc", "");
        transaction.put("external_ref", "224878");
        transaction.put("status", "Failed");
        transaction.put("status_code", "01");
        transaction.put("created_at", "2024-07-12T12:01:25.16+01:00");
        transaction.put("balance_before", 396800);
        transaction.put("commission_amount", 0);
        transaction.put("commission_to", "");
        transaction.put("refunded_at", "2024-07-12T12:01:25.681+01:00");

        // Data Map
        Map<String, Object> data = new HashMap<>();
        data.put("reversal_log", reversalLog);
        data.put("telco_log", telcoLog);
        data.put("transaction", transaction);

        // Main Map
        Map<String, Object> mainMap = new HashMap<>();
        mainMap.put("data", data);
        mainMap.put("message", "Transaction Failed");
        mainMap.put("status", "Failed");
        mainMap.put("status_code", "01");

        return gson.toJson(mainMap);
    }

}
