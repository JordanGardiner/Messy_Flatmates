package com.example.messy_flatmates.db;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * @version 1.0
 * Sub class of Connections.
 * Holds all get specific requests to the server.
 * @Author Jordan Gardiner
 */
public class Get_requests extends Connections {

    /**
     * fetches a user from the database, If the token doesn't match, then it will only return
     * limited information(i.e your information)
     * @param token users login token
     * @return JSONObject response
     *
     */
    public JSONObject Get_user(String token, String user_id){
        if(user_id == null){
            String requestString = "/api/user";
            return SendGetRequest(requestString, token);
        } else {
            String requestString = "/api/user/?id=" + user_id;
            return SendGetRequest(requestString, token);
        }
    }

    /**
     * returns a users flat
     * @param token
     * @return response in JSONObject format
     */
    public JSONObject Get_Flat(String token){
        String request = "/api/flat";
        return SendGetRequest(request, token);

    }

    /**
     * Flat token is the invite token used to join a flat
     * @param token the users current session token
     * @return JSONObject response
     */
    public JSONObject Get_Flat_Token(String token){
        String request = "/api/flat/inviteToken";
        return SendGetRequest(request, token);

    }
}
