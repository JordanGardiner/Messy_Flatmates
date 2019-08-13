package com.example.messy_flatmates.db;


import org.json.JSONObject;

/**
 * @version 1.0
 * handles all of the put requests.
 *
 */
public class Put_requests extends Connections {

    public JSONObject removeUserFromFlat(String token){

        String requestString = "/api/flat/leave";
        return SendRequest(requestString, null, token, "PUT");
    }



}
