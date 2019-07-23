package com.example.messy_flatmates.db;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class Post_requests extends Connections {

    public JSONObject Create_user(String firstName, String lastName, String email, String password, String dob){
        JSONObject jsonBody = new JSONObject();
        try {

            jsonBody.put("firstName", firstName);
            jsonBody.put("lastName", lastName);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("dob", dob);
        } catch (JSONException e) {

        }

        String requestString = "/api/user";
        return SendPostRequest(requestString, jsonBody);


    }

    public JSONObject Login(String email, String password){
        JSONObject loginJSON = new JSONObject();
        try{
            loginJSON.put("email", email);
            loginJSON.put("password", password);

        } catch (JSONException e){
            System.out.println(e.getMessage());
        }
        return SendPostRequest("/api/user/login", loginJSON);

    }
}

