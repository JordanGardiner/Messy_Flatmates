package com.example.messy_flatmates.db;


import org.json.JSONException;
import org.json.JSONObject;

public class Get_requests extends Connections {

    /**
     * fetches a user from the database, If token is null, then it will only return limited information
     * (i.e your information
     * @param token
     * @return JSONObject response
     */
    public JSONObject Get_UserSelf(String token){
        String requestString = "/api/user";
        return SendGetRequest(requestString, token);
    }


}
