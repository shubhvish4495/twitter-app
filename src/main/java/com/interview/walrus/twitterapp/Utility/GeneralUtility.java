package com.interview.walrus.twitterapp.Utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class GeneralUtility {

    public static ResponseEntity<Map<String, Object>> formatResponse(Boolean success, String message, Object dataObject){
        Map<String, Object> obj = new LinkedHashMap<String, Object>();
        obj.put("success", success);
        obj.put("message", message);
        obj.put("data",dataObject);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    public static int countCharacters(String postVal){
        int count = 0;
        for(int i = 0; i < postVal.length(); i++) {
            if(postVal.charAt(i) != ' ')
                count++;
        }
        return count;
    }

    public static String generateRandomToken(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
